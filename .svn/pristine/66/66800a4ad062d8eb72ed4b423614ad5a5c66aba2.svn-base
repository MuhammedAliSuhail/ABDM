package com.safecare.abdm.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safecare.abdm.aadhaar.MobileOTPRequestModel;
import com.safecare.abdm.aadhaar.OTPResponseModel;
import com.safecare.abdm.aadhaar.OTPValidateRequestModel;

@RestController
@RequestMapping("mobile")
public class MobileRegistrationController {
	@Autowired
	MobileNoRegistrationService mobileNoRegistrationService;

	@PostMapping("generateMobileOtp")
	public OTPResponseModel generateMobile(@RequestBody MobileOTPRequestModel mobileOTPRequestModel) {
		return mobileNoRegistrationService.generateOtp(mobileOTPRequestModel);
	}

	@PostMapping("resendMobileOtp")
	public OTPResponseModel resendMobileOtp(@RequestBody OTPResponseModel otpResponseModel) {
		return mobileNoRegistrationService.resendOtp(otpResponseModel);
	}

	@PostMapping("verifyMobileOtp")
	public MobileOTPValidateResponseModel verifyMobileOtp(
			@RequestBody OTPValidateRequestModel otpValidateRequestModel) {
		return mobileNoRegistrationService.validateMobileOtp(otpValidateRequestModel);
	}

	@PostMapping("createHealthID")
	public CreateHealthIDResponseModel createHealthID(
			@RequestBody CreateHealthIDRequestModel createHealthIDRequestModel) {
		return mobileNoRegistrationService.createHealthID(createHealthIDRequestModel);
	}
}
