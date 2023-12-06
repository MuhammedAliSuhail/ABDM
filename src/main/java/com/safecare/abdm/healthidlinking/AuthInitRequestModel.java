package com.safecare.abdm.healthidlinking;

import com.safecare.abdm.ServerMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInitRequestModel {
	private String requestId;
	private String timestamp;
	private QueryModel query;
	private ServerMessage serverMessage;

}
