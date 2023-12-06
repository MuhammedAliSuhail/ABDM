package com.safecare.abdm.opConsultNote;

import lombok.Data;

@Data
public class OpVisitDetail {
	private String visitId;
	private String visitDate;
	private String uhid;
	private String patientName;
	private String doctorCode;
	private String doctorName;
	private String departmentCode;
	private String departmentName;
	private String hospitalCode;
	private String hospitalName;
}
