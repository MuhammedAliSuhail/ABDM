package com.safecare.abdm.healthidlinking;

import com.safecare.abdm.ServerMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareContextLinkingRequestModel {
	private String requestId;
	private String timestamp;
	private Link link;
	private ServerMessage serverMessage;
}
