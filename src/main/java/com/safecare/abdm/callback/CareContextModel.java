package com.safecare.abdm.callback;

import lombok.Data;

@Data
public class CareContextModel {
	private String requestId;
	private String timestamp;
	private Acknowledgement acknowledgement;
	private Error error;
	private Resp resp;
}
