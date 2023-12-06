package com.safecare.abdm.aadhaar;

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
public class AadhaarService {
	@Autowired
	AuthenticationService authenticationService;

	public OTPResponseModel generateAdhaarOTP(AadhaarOTPRequestModel aadhaarOTPRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<AadhaarOTPRequestModel> httpEntity = new HttpEntity<AadhaarOTPRequestModel>(aadhaarOTPRequestModel,
				headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/aadhaar/generateOtp",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public OTPResponseModel verifyAadhaarOTP(OTPValidateRequestModel otpValidateRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<OTPValidateRequestModel> httpEntity = new HttpEntity<OTPValidateRequestModel>(
				otpValidateRequestModel, headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/aadhaar/verifyOTP",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public OTPResponseModel checkAndGenerateMobileOTP(MobileOTPRequestModel mobileOTPRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<MobileOTPRequestModel> httpEntity = new HttpEntity<MobileOTPRequestModel>(mobileOTPRequestModel,
				headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v2/registration/aadhaar/checkAndGenerateMobileOTP",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public OTPResponseModel generateMobileOTP(MobileOTPRequestModel mobileOTPRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<MobileOTPRequestModel> httpEntity = new HttpEntity<MobileOTPRequestModel>(mobileOTPRequestModel,
				headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/aadhaar/generateMobileOTP",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}

	public OTPResponseModel verifyMobileOTP(OTPValidateRequestModel otpValidateRequestModel) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
		HttpEntity<OTPValidateRequestModel> httpEntity = new HttpEntity<OTPValidateRequestModel>(
				otpValidateRequestModel, headers);
		ResponseEntity<OTPResponseModel> responseEntity = restTemplate.exchange(
				AbdmConfig.BASE_URI + "/" + AbdmConfig.BASE_PATH + "/v1/registration/aadhaar/verifyMobileOTP",
				HttpMethod.POST, httpEntity, OTPResponseModel.class);

		OTPResponseModel otpResponseModel = null;
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			otpResponseModel = responseEntity.getBody();
		}
		return otpResponseModel;
	}
}
