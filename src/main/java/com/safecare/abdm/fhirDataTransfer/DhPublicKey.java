
package com.safecare.abdm.fhirDataTransfer;

import lombok.Data;

@Data
public class DhPublicKey {

	private String expiry;
	private String parameters;
	private String keyValue;

}
