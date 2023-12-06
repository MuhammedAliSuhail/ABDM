package com.safecare.abdm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationModel {
	private String organizationName;
	private String organizationCode;
	private String phoneNo;
	private String email;

}
