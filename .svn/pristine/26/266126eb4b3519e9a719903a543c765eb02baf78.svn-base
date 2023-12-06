package com.safecare.abdm.request;

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
import com.safecare.abdm.callback.Resp;
import com.safecare.abdm.transaction.ABDMTransaction;
import com.safecare.abdm.transaction.ABDMTransactionRepo;
import com.safecare.abdm.transaction.ConsentDetails;
import com.safecare.abdm.transaction.ConsentDetailsRepo;
import com.safecare.abdm.utilities.AbdmConfig;

@Service
public class RequestService {
	@Autowired
	ABDMTransactionRepo abdmTransactionRepo;
	@Autowired
	Gson json;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	ConsentDetailsRepo consentDetailsRepo;

	public RequestModel request(RequestModel requestModel) {
		ABDMTransaction abdmTransaction = new ABDMTransaction();
		abdmTransaction.setRequestBody(json.toJson(requestModel));
		abdmTransaction.setRequestId(requestModel.getRequestId());
		abdmTransaction.setTimeStamp(requestModel.getTimestamp());
		abdmTransaction.setStatus(3);
		abdmTransactionRepo.save(abdmTransaction);
		ConsentDetails consentDetails = consentDetailsRepo
				.findByConsentId(requestModel.getHiRequest().getConsent().getId());
		consentDetails.setTransactionId(requestModel.getTransactionId());
		consentDetails.setTransactionRequestId(requestModel.getRequestId());
		consentDetails.setStatus(2);
		return requestModel;
	}

	public RequestResponseModel onRequest(com.safecare.abdm.request.RequestModel requestModel) {
		RequestResponseModel requestResponseModel = new RequestResponseModel();
		requestResponseModel.setRequestId(com.safecare.abdm.utilities.RequestModel.getRequestId());
		requestResponseModel.setTimestamp(com.safecare.abdm.utilities.RequestModel.getTimeStamp());
		HiRequest hiRequest = new HiRequest();
		hiRequest.setTransactionId(requestModel.getTransactionId());
		hiRequest.setSessionStatus("ACKNOWLEDGED");
		requestResponseModel.setHiRequest(hiRequest);
		Resp resp = new Resp();
		resp.setRequestId(requestModel.getRequestId());
		requestResponseModel.setResp(resp);

		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<RequestResponseModel> httpEntity = new HttpEntity<RequestResponseModel>(requestResponseModel,
				headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					AbdmConfig.GATEWAY + "/v0.5/health-information/hip/on-request", HttpMethod.POST, httpEntity,
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
		ABDMTransaction abdmTransaction = new ABDMTransaction();
		abdmTransaction.setRequestId(requestResponseModel.getRequestId());
		abdmTransaction.setTimeStamp(requestResponseModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(requestResponseModel));

		requestResponseModel.setServerMessage(serverMessage);
		abdmTransactionRepo.save(abdmTransaction);

		return requestResponseModel;
	}
}
