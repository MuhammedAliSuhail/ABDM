package com.safecare.abdm.utilities;

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

@Service
public class UtilityService {
	@Autowired
	AuthenticationService authenticationService;

	public State[] getStates() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity httpEntity = new HttpEntity(headers);
		ResponseEntity<State[]> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v2/ha/lgd/states", HttpMethod.GET, httpEntity,
				State[].class);

		State[] otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("hi");
			otpResponseModel = responseEntity.getBody();
			System.out.println("hi" + otpResponseModel.toString());

		}
		return otpResponseModel;
	}

	public District[] getDistricts(String stateCode) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity httpEntity = new HttpEntity(headers);
		ResponseEntity<District[]> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/ha/lgd/districts?stateCode=" + stateCode,
				HttpMethod.GET, httpEntity, District[].class);

		District[] otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("hi");
			otpResponseModel = responseEntity.getBody();
			System.out.println("hi" + otpResponseModel.toString());

		}
		return otpResponseModel;
	}

}
