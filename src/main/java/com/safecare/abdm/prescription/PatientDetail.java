package com.safecare.abdm.prescription;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDetail {
	private String patientName;
	private String age;
	private String dateOfBirth;
	private String gender;
	private String uhid;
	private String phoneNo;
}
