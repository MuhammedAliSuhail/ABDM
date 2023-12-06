
package com.safecare.abdm.callback;

import lombok.Data;

@Data
public class OnConfirmModel {

	public String requestId;
	public String timestamp;
	public Auth auth;
	public Error error;
	public Resp resp;

}
