package com.safecare.abdm.aadhaar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OTPValidateRequestModel {
	private Integer otp;
	private String txnId;
}
