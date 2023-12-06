package com.safecare.abdm.model;

import lombok.Data;

@Data
public class DiagnosisModel {
	private String id;
	private String diagnosisMasterId;
	private String cptCode;
	private String name;
	private String remark;
}
