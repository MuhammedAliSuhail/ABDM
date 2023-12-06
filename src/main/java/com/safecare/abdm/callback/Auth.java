
package com.safecare.abdm.callback;

import java.util.List;

import lombok.Data;

@Data
public class Auth {
	private String transactionId;
	private String purpose;
	private List<String> modes = null;
	private String mode;
	private Meta meta;
	public String accessToken;
	public Validity validity;
	public Patient patient;

}
