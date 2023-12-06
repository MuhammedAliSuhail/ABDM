package com.safecare.abdm.notify;

import com.safecare.abdm.ServerMessage;
import com.safecare.abdm.callback.Acknowledgement;
import com.safecare.abdm.callback.Resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyResponseModel {
	private String requestId;
	private String timestamp;
	private Acknowledgement acknowledgement;

	private Resp resp;
	private ServerMessage serverMessage;
}
