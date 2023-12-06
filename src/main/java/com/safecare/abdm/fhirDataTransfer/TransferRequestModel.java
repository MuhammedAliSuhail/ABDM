
package com.safecare.abdm.fhirDataTransfer;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferRequestModel {

	private Integer pageNumber;
	private Integer pageCount;
	private String transactionId;
	private List<Entry> entries;
	private KeyMaterial keyMaterial;

}
