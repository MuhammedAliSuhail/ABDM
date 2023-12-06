package com.safecare.abdm.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.safecare.abdm.aadhaar.MobileOTPRequestModel;
import com.safecare.abdm.aadhaar.OTPResponseModel;
import com.safecare.abdm.aadhaar.OTPValidateRequestModel;
import com.safecare.abdm.auth.AuthenticationService;
import com.safecare.abdm.healthid.HealthIDDetails;
import com.safecare.abdm.healthid.HealthIDDetailsRepo;
import com.safecare.abdm.utilities.AbdmConfig;

@Service
public class MobileNoRegistrationService {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	HealthIDDetailsRepo healthIDDetailsRepo;

	public OTPResponseModel generateOtp(MobileOTPRequestModel mobileOTPRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<MobileOTPRequestModel> httpEntity = new HttpEntity<MobileOTPRequestModel>(mobileOTPRequestModel,
				headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/mobile/generateOtp",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public OTPResponseModel resendOtp(OTPResponseModel mobileOTPRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<OTPResponseModel> httpEntity = new HttpEntity<OTPResponseModel>(mobileOTPRequestModel, headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/mobile/resendOtp", HttpMethod.POST,
				httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public MobileOTPValidateResponseModel validateMobileOtp(OTPValidateRequestModel otpValidateRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<OTPValidateRequestModel> httpEntity = new HttpEntity<OTPValidateRequestModel>(
				otpValidateRequestModel, headers);
		ResponseEntity<MobileOTPValidateResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/mobile/verifyOtp", HttpMethod.POST,
				httpEntity, MobileOTPValidateResponseModel.class);

		MobileOTPValidateResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public CreateHealthIDResponseModel createHealthID(CreateHealthIDRequestModel createHealthIDRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<CreateHealthIDRequestModel> httpEntity = new HttpEntity<CreateHealthIDRequestModel>(
				createHealthIDRequestModel, headers);
		ResponseEntity<CreateHealthIDResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/mobile/createHealthId",
				HttpMethod.POST, httpEntity, CreateHealthIDResponseModel.class);

		CreateHealthIDResponseModel response = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			response = responseEntity.getBody();
			HealthIDDetails healthIDDetails = new HealthIDDetails(response);
			healthIDDetailsRepo.save(healthIDDetails);

		}
		return response;
	}
}
