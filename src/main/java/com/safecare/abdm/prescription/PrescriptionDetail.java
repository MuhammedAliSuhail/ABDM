package com.safecare.abdm.prescription;

import lombok.Data;

@Data
public class PrescriptionDetail {
	private Integer productId;
	private String productName;
	private Integer prescriptionId;
	private Double quantity;
	private String routeOfAdmission;
	private String instruction;
	private String createdDate;
	private String visitId;
	private String direction;
	private String additionalInstruction;
	private String orderedDate;
}
