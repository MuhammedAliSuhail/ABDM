package com.safecare.abdm.opConsultNote;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplientDetail {
	private String id;
	private String complientName;
	private String complientCode;
	private String snomedCode;
	private String snomedName;
	private String complientAddedDate;
}
