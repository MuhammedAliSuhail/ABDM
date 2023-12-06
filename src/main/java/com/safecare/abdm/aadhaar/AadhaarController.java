package com.safecare.abdm.aadhaar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safecare.abdm.auth.AuthenticationService;

@RestController
public class AadhaarController {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	AadhaarService aadhaarService;

	@GetMapping("getToken")
	public String getToken() {
		return authenticationService.getAccessToken();
	}

	@PostMapping("generateAadhaarOTP")
	public OTPResponseModel generateAadhaarOTP(@RequestBody AadhaarOTPRequestModel aadhaarOTPRequestModel) {
		return aadhaarService.generateAdhaarOTP(aadhaarOTPRequestModel);
	}

	@PostMapping("validateAadhaarOTP")
	public OTPResponseModel validateAadhaarOTP(@RequestBody OTPValidateRequestModel otpValidateRequestModel) {
		return aadhaarService.verifyAadhaarOTP(otpValidateRequestModel);
	}

	@PostMapping("checkAndGenerateMobileOTP")
	public OTPResponseModel checkAndGenerateMobileOTP(@RequestBody MobileOTPRequestModel mobileOTPRequestModel) {
		return aadhaarService.checkAndGenerateMobileOTP(mobileOTPRequestModel);
	}

	@PostMapping("generateMobileNoOTP")
	public OTPResponseModel generateMobileOTP(@RequestBody MobileOTPRequestModel mobileOTPRequestModel) {
		return aadhaarService.generateMobileOTP(mobileOTPRequestModel);
	}

	@PostMapping("validateMobileOTP")
	public OTPResponseModel validateMobileOTP(@RequestBody OTPValidateRequestModel otpValidateRequestModel) {
		return aadhaarService.verifyMobileOTP(otpValidateRequestModel);
	}

}
