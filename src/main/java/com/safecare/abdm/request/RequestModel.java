
package com.safecare.abdm.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {

	private String requestId;
	private String timestamp;
	private String transactionId;
	private HiRequest hiRequest;

}
