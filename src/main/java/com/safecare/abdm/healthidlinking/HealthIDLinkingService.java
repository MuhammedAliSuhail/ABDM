package com.safecare.abdm.healthidlinking;

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
import com.safecare.abdm.transaction.ABDMTransaction;
import com.safecare.abdm.transaction.ABDMTransactionRepo;
import com.safecare.abdm.utilities.AbdmConfig;
import com.safecare.abdm.utilities.RequestModel;

@Service
public class HealthIDLinkingService {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	ABDMTransactionRepo abdmTransactionRepo;
	Gson json = new Gson();

	public FetchModeRequestModel getFetchModes(FetchModeRequestModel fetchModeRequestModel) {

		fetchModeRequestModel.setRequestId(RequestModel.getRequestId());
		fetchModeRequestModel.setTimestamp(RequestModel.getTimeStamp());
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(new Gson().toJson(fetchModeRequestModel));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<FetchModeRequestModel> httpEntity = new HttpEntity<FetchModeRequestModel>(fetchModeRequestModel,
				headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					AbdmConfig.GATEWAY + "/v0.5/users/auth/fetch-modes", HttpMethod.POST, httpEntity, String.class);

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
		abdmTransaction.setRequestId(fetchModeRequestModel.getRequestId());
		abdmTransaction.setTimeStamp(fetchModeRequestModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(fetchModeRequestModel));
		abdmTransactionRepo.save(abdmTransaction);

		fetchModeRequestModel.setServerMessage(serverMessage);
		return fetchModeRequestModel;
	}

	public AuthInitRequestModel authInit(AuthInitRequestModel authInitRequestModel) {
		authInitRequestModel.setRequestId(RequestModel.getRequestId());
		authInitRequestModel.setTimestamp(RequestModel.getTimeStamp());
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<AuthInitRequestModel> httpEntity = new HttpEntity<AuthInitRequestModel>(authInitRequestModel,
				headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(AbdmConfig.GATEWAY + "/v0.5/users/auth/init",
					HttpMethod.POST, httpEntity, String.class);

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
		abdmTransaction.setRequestId(authInitRequestModel.getRequestId());
		abdmTransaction.setTimeStamp(authInitRequestModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(authInitRequestModel));
		abdmTransactionRepo.save(abdmTransaction);
		authInitRequestModel.setServerMessage(serverMessage);
		return authInitRequestModel;
	}

	public AuthConfirmRequestModel authConfirm(AuthConfirmRequestModel authConfirmRequestModel) {
		authConfirmRequestModel.setRequestId(RequestModel.getRequestId());
		authConfirmRequestModel.setTimestamp(RequestModel.getTimeStamp());
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<AuthConfirmRequestModel> httpEntity = new HttpEntity<AuthConfirmRequestModel>(
				authConfirmRequestModel, headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					AbdmConfig.GATEWAY + "/v0.5/users/auth/confirm", HttpMethod.POST, httpEntity, String.class);

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
		abdmTransaction.setRequestId(authConfirmRequestModel.getRequestId());
		abdmTransaction.setTimeStamp(authConfirmRequestModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(authConfirmRequestModel));
		abdmTransactionRepo.save(abdmTransaction);
		authConfirmRequestModel.setServerMessage(serverMessage);
		return authConfirmRequestModel;
	}

	public CareContextLinkingRequestModel careContextLinking(
			CareContextLinkingRequestModel careCarContextLinkingRequestModel) {
		careCarContextLinkingRequestModel.setRequestId(RequestModel.getRequestId());
		careCarContextLinkingRequestModel.setTimestamp(RequestModel.getTimeStamp());
		ServerMessage serverMessage = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("X-CM-ID", "sbx");
		headers.add("Authorization", "bearer " + authenticationService.getAccessToken());
		HttpEntity<CareContextLinkingRequestModel> httpEntity = new HttpEntity<CareContextLinkingRequestModel>(
				careCarContextLinkingRequestModel, headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					AbdmConfig.GATEWAY + "/v0.5/links/link/add-contexts", HttpMethod.POST, httpEntity, String.class);

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
		abdmTransaction.setRequestId(careCarContextLinkingRequestModel.getRequestId());
		abdmTransaction.setTimeStamp(careCarContextLinkingRequestModel.getTimestamp());
		abdmTransaction.setRequestBody(json.toJson(careCarContextLinkingRequestModel));
		abdmTransactionRepo.save(abdmTransaction);
		careCarContextLinkingRequestModel.setServerMessage(serverMessage);
		return careCarContextLinkingRequestModel;
	}
}
