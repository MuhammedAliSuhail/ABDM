package com.safecare.abdm.prescription;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDetail {
	private String doctorName;
	private String licenseId;
	private String doctorCode;
	private String doctorId;
	private String visitDate;
	private String createDate;
}
