package com.safecare.abdm.healthid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.safecare.abdm.auth.AuthenticationService;
import com.safecare.abdm.utilities.AbdmConfig;

@Service
public class HealthIDService {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	HealthIDDetailsRepo healthIDDetailsRepo;

	public HealthIDCreationResponseModel createHealthID(HealthIDCreationRequestModel healtIDCreationRequestModel) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<HealthIDCreationRequestModel> httpEntity = new HttpEntity<HealthIDCreationRequestModel>(
				healtIDCreationRequestModel, headers);
		ResponseEntity<HealthIDCreationResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH
						+ "/v1/registration/aadhaar/createHealthIdWithPreVerified",
				HttpMethod.POST, httpEntity, HealthIDCreationResponseModel.class);

		HealthIDCreationResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
			HealthIDDetails healthIDDetails = new HealthIDDetails(otpResponseModel);
			healthIDDetailsRepo.save(healthIDDetails);
		}
		return otpResponseModel;

	}

	public HealthIDIsExistResponseModel isHealthIDExists(HealthIDRequestModel healthIDRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<HealthIDRequestModel> httpEntity = new HttpEntity<HealthIDRequestModel>(healthIDRequestModel,
				headers);
		ResponseEntity<HealthIDIsExistResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/search/existsByHealthId", HttpMethod.POST,
				httpEntity, HealthIDIsExistResponseModel.class);

		HealthIDIsExistResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public HealthIDCreationResponseModel searchWithHealtID(HealthIDRequestModel healthIDRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<HealthIDRequestModel> httpEntity = new HttpEntity<HealthIDRequestModel>(healthIDRequestModel,
				headers);
		ResponseEntity<HealthIDCreationResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/search/searchByHealthId", HttpMethod.POST,
				httpEntity, HealthIDCreationResponseModel.class);

		HealthIDCreationResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public HealthIDCreationResponseModel createHealthID1(HealthIDCreationResponseModel healtIDCreationRequestModel) {

		HealthIDDetails healthIDDetails = new HealthIDDetails(healtIDCreationRequestModel);
		healthIDDetailsRepo.save(healthIDDetails);

		return healtIDCreationRequestModel;

	}

}
