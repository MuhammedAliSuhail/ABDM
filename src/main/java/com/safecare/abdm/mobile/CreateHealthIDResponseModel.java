package com.safecare.abdm.mobile;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateHealthIDResponseModel {
	public String token;
	public String refreshToken;
	public Object healthIdNumber;
	public Object name;
	public String gender;
	public String yearOfBirth;
	public String monthOfBirth;
	public String dayOfBirth;
	public String firstName;
	public String healthId;
	public String lastName;
	public String middleName;
	public String stateCode;
	public String districtCode;
	public Object stateName;
	public Object districtName;
	public String email;
	public Object kycPhoto;
	public String profilePhoto;
	public String mobile;
	public List<String> authMethods = null;
	public String pincode;
	public Object tags;
	public Boolean _new;
}
