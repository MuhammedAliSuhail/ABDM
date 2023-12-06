package com.safecare.abdm.prescription;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnosisDetail {
	private String orderNo;
	private String id;
	private String diagMasterID;
	private String diagnosisType;
	private String cptCode;
	private String diagnosisName;

}
