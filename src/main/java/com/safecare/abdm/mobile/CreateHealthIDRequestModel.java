package com.safecare.abdm.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHealthIDRequestModel {
	private String address;
	private Integer dayOfBirth;
	private Integer districtCode;
	private String email;
	private String firstName;
	private String gender;
	private String healthId;
	private String lastName;
	private String middleName;
	private String monthOfBirth;
	private String name;
	private String password;
	private String pincode;
	private String profilePhoto;
	private Integer stateCode;
	private String subdistrictCode;
	private String token;
	private String txnId;
	private String villageCode;
	private String wardCode;
	private Integer yearOfBirth;
}
