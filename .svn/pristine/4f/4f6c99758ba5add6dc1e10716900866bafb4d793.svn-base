package com.safecare.abdm.fhirDataTransfer;

import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.safecare.abdm.ServerMessage;
import com.safecare.abdm.notify.CareContext;
import com.safecare.abdm.notify.NotifyRequestModel;
import com.safecare.abdm.prescription.PrescriptionService;
import com.safecare.abdm.request.RequestModel;
import com.safecare.abdm.transaction.ABDMTransaction;
import com.safecare.abdm.transaction.ABDMTransactionRepo;
import com.safecare.abdm.transaction.ConsentDetails;
import com.safecare.abdm.transaction.ConsentDetailsRepo;

@Service
public class DataTransferService extends DHKeyExchangeCrypto {

	@Autowired
	ConsentDetailsRepo consentDetailsRepo;
	@Autowired
	ABDMTransactionRepo abdmTransactionRepo;
	@Autowired
	PrescriptionService prescriptionService;
//	@Autowired
//	OpConsultRecordService opConsultRecordService;
//	@Autowired
//	WellnessRecord wellnessRecord;

	@Autowired
	Gson json;

	public void transferData() throws Exception {
		List<ConsentDetails> consentDetailsList = consentDetailsRepo.findAllByStatus(2);
		for (ConsentDetails consentDetails : consentDetailsList) {
			ABDMTransaction consentRequest = abdmTransactionRepo.findByRequestId(consentDetails.getConsentRequestId());
			ABDMTransaction transactionRequest = abdmTransactionRepo
					.findByRequestId(consentDetails.getTransactionRequestId());
			NotifyRequestModel notifyRequestModel = json.fromJson(consentRequest.getRequestBody(),
					NotifyRequestModel.class);
			RequestModel requestModel = json.fromJson(transactionRequest.getRequestBody(), RequestModel.class);
			List<CareContext> careContexts = notifyRequestModel.getNotification().getConsentDetail().getCareContexts();
			List<String> hiTypes = notifyRequestModel.getNotification().getConsentDetail().getHiTypes();

			for (CareContext careContext : careContexts) {
				String patientId = careContext.getPatientReference();
				String careContextId = careContext.getCareContextReference();
				String visitId = careContextId.substring(5);
				for (String hiType : hiTypes) {
					String strToPerformActionOn = "";
					KeyPair senderKeyPair = generateKeyPair();
					String senderPrivateKey = getBase64String(getEncodedPrivateKey(senderKeyPair.getPrivate()));
					String senderPublicKey = getBase64String(getEncodedPublicKey(senderKeyPair.getPublic()));
					String randomSender = generateRandomKey();
					String randomReceiver = requestModel.getHiRequest().getKeyMaterial().getNonce();
					String receiverPublicKey = requestModel.getHiRequest().getKeyMaterial().getDhPublicKey()
							.getKeyValue();
					byte[] xorOfRandom = xorOfRandom(randomSender, randomReceiver);
					String encryptedData = "";
					String keyToShare = getBase64String(getEncodedHIPPublicKey(getKey(senderPublicKey)));
					switch (hiType) {
					case "OPConsultation":
//						strToPerformActionOn = opConsultRecordService.populateOPConsultNoteBundle(visitId);
						break;
					case "Prescription":
						strToPerformActionOn = prescriptionService.prescriptionBundle(visitId,
								notifyRequestModel.getNotification().getConsentDetail().getPermission().getDateRange());

						break;
					case "DischargeSummary":
						break;
					case "DiagnosticReport":
						break;
					case "ImmunizationRecord":
						break;
					case "HealthDocumentRecord":
						break;
					case "WellnessRecord":
//						strToPerformActionOn = wellnessRecord.wellnessRecord(visitId, 0);
						break;
					}
					encryptedData = encrypt(xorOfRandom, senderPrivateKey, receiverPublicKey, strToPerformActionOn);

					TransferRequestModel transferRequestModel = new TransferRequestModel();
					Entry entry = new Entry();
					entry.setContent(encryptedData);
					entry.setCareContextReference(careContextId);
					entry.setMedia("application/fhir+json");
					List<Entry> entries = new ArrayList<>();
					entries.add(entry);

					KeyMaterial keyMaterial = new KeyMaterial();
					keyMaterial.setCryptoAlg(ALGORITHM);
					keyMaterial.setCurve(CURVE);
					keyMaterial.setNonce(randomSender);
					DhPublicKey dhPublicKey = new DhPublicKey();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
					formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

					final Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					String text = formatter.format(calendar.getTime());
					dhPublicKey.setExpiry(text);
					dhPublicKey.setParameters("Curve25519/32byte random key");
					dhPublicKey.setKeyValue(keyToShare);
					keyMaterial.setDhPublicKey(dhPublicKey);
					transferRequestModel.setEntries(entries);
					transferRequestModel.setKeyMaterial(keyMaterial);
					transferRequestModel.setPageCount(0);
					transferRequestModel.setPageNumber(0);
					transferRequestModel.setTransactionId(requestModel.getTransactionId());
					transferData(requestModel.getHiRequest().getDataPushUrl(), transferRequestModel);

				}
			}

		}
	}

	public ServerMessage transferData(String URL, TransferRequestModel transferRequestModel) {
		System.out.println("data url" + URL + "data" + json.toJson(transferRequestModel));
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");

		HttpEntity<TransferRequestModel> httpEntity = new HttpEntity<TransferRequestModel>(transferRequestModel,
				headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity,
					String.class);

			if (responseEntity.getStatusCode().equals(HttpStatus.ACCEPTED)) {
				serverMessage = new ServerMessage(ServerMessage.SUCCESS, "Accepted");
			} else {
				serverMessage = new ServerMessage(ServerMessage.FAILED,
						"Status Code:" + responseEntity.getStatusCode() + ",Message:");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			serverMessage = new ServerMessage(ServerMessage.FAILED, ex.getMessage());
		}
		System.out.println("ServerMessage" + serverMessage.toString());
		return serverMessage;
	}

}
