package com.safecare.abdm.notify;

import java.util.Date;

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
import com.safecare.abdm.auth.AuthenticationService;
import com.safecare.abdm.callback.Acknowledgement;
import com.safecare.abdm.callback.Resp;
import com.safecare.abdm.transaction.ABDMTransaction;
import com.safecare.abdm.transaction.ABDMTransactionRepo;
import com.safecare.abdm.transaction.ConsentDetails;
import com.safecare.abdm.transaction.ConsentDetailsRepo;
import com.safecare.abdm.utilities.AbdmConfig;
import com.safecare.abdm.utilities.RequestModel;

@Service
public class NotifyService {
	@Autowired
	ABDMTransactionRepo abdmTransactionRepo;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	Gson json;
	@Autowired
	ConsentDetailsRepo consentDetailsRepo;

	public void notify(NotifyRequestModel notifyRequestModel) {
		ABDMTransaction abdmTransaction = new ABDMTransaction();
		abdmTransaction.setRequestId(notifyRequestModel.getRequestId());
		abdmTransaction.setRequestBody(json.toJson(notifyRequestModel));
		abdmTransaction.setTimeStamp(notifyRequestModel.getTimestamp());
		abdmTransaction.setStatus(2);
		abdmTransactionRepo.save(abdmTransaction);

		ConsentDetails consentDetails = new ConsentDetails();
		consentDetails.setConsentId(notifyRequestModel.getNotification().getConsentId());
		consentDetails.setConsentRequestId(notifyRequestModel.getRequestId());
		consentDetails.setCreatedDate(new Date());
		consentDetails.setStatus(1);
		consentDetailsRepo.save(consentDetails);

	}

	public NotifyResponseModel onNotify(NotifyRequestModel notifyRequestModel) {
		NotifyResponseModel notifyResponseModel = new NotifyResponseModel();
		notifyResponseModel.setRequestId(RequestModel.getRequestId());
		notifyResponseModel.setTimestamp(RequestModel.getTimeStamp());
		Acknowledgement acknowledgement = new Acknowledgement("OK",
				notifyRequestModel.getNotification().getConsentId());
		notifyResponseModel.setAcknowledgement(acknowledgement);
		Resp resp = new Resp();
		resp.setRequestId(notifyRequestModel.getRequestId());
		notifyResponseModel.setResp(resp);
		System.out.println("HI ALL");
		System.out.println("/out" + json.toJson(notifyResponseModel));
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<NotifyResponseModel> httpEntity = new HttpEntity<NotifyResponseModel>(notifyResponseModel, headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					AbdmConfig.GATEWAY + "/v0.5/consents/hip/on-notify", HttpMethod.POST, httpEntity, String.class);

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
		ABDMTransaction abdmTransaction = new ABDMTransaction();
		abdmTransaction.setRequestId(notifyResponseModel.getRequestId());
		abdmTransaction.setTimeStamp(notifyResponseModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(notifyResponseModel));
		notifyResponseModel.setServerMessage(serverMessage);
		abdmTransactionRepo.save(abdmTransaction);

		return notifyResponseModel;
	}

}
