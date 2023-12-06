package com.safecare.abdm.model;

import lombok.Data;

@Data
public class PatientHistoryModel {
	private String id;
	private String maritialStatus;
	private String occupation;
	private String familyHistory;
	private String social;
	private String perinatal;
	private String immunization;
	private String development;
	private String remark;
}
