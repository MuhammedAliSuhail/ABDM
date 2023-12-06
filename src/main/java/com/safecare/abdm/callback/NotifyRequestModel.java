package com.safecare.abdm.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyRequestModel {
	public String requestId;
	public String timestamp;
	public Auth auth;
}
