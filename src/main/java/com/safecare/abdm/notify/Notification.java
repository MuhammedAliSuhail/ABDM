
package com.safecare.abdm.notify;

import lombok.Data;

@Data
public class Notification {

	private String status;
	private String consentId;
	private ConsentDetail consentDetail;
	private String signature;
	private Boolean grantAcknowledgement;

}
