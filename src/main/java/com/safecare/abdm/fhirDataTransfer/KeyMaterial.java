
package com.safecare.abdm.fhirDataTransfer;

import lombok.Data;

@Data
public class KeyMaterial {

	private String cryptoAlg;
	private String curve;
	private DhPublicKey dhPublicKey;
	private String nonce;

}
