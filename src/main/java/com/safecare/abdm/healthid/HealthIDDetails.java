package com.safecare.abdm.healthid;

import com.safecare.abdm.mobile.CreateHealthIDResponseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "health_id_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthIDDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String healthIdNumber;
	private String name;
	private String gender;
	private String yearOfBirth;
	private String monthOfBirth;
	private String dayOfBirth;
	private String firstName;
	private String healthId;
	private String lastName;
	private String middleName;
	private String stateCode;
	private String districtCode;
	private String stateName;
	private String districtName;
	private String email;
	private String kycPhoto;
	private String profilePhoto;
	private String mobile;
	private String pincode;

	public HealthIDDetails(HealthIDCreationResponseModel creationResponseModel) {
		super();
		this.healthIdNumber = creationResponseModel.healthIdNumber;
		this.name = creationResponseModel.name;
		this.gender = creationResponseModel.gender;
		this.yearOfBirth = creationResponseModel.yearOfBirth;
		this.monthOfBirth = creationResponseModel.monthOfBirth;
		this.dayOfBirth = creationResponseModel.dayOfBirth;
		this.firstName = creationResponseModel.firstName;
		this.healthId = creationResponseModel.healthId;
		this.lastName = creationResponseModel.lastName;
		this.middleName = creationResponseModel.middleName;
		this.stateCode = creationResponseModel.stateCode;
		this.districtCode = creationResponseModel.districtCode;
		this.stateName = creationResponseModel.stateName;
		this.districtName = creationResponseModel.districtName;
		this.email = creationResponseModel.email;
		this.kycPhoto = creationResponseModel.kycPhoto;
		this.profilePhoto = creationResponseModel.profilePhoto;
		this.mobile = creationResponseModel.mobile;
		this.pincode = creationResponseModel.pincode;
	}

	public HealthIDDetails(CreateHealthIDResponseModel creationResponseModel) {
		super();
//		this.healthIdNumber = creationResponseModel.healthIdNumber;
//		this.name = creationResponseModel.name;
		this.gender = creationResponseModel.gender;
		this.yearOfBirth = creationResponseModel.yearOfBirth;
		this.monthOfBirth = creationResponseModel.monthOfBirth;
		this.dayOfBirth = creationResponseModel.dayOfBirth;
		this.firstName = creationResponseModel.firstName;
		this.healthId = creationResponseModel.healthId;
		this.lastName = creationResponseModel.lastName;
		this.middleName = creationResponseModel.middleName;
		this.stateCode = creationResponseModel.stateCode;
		this.districtCode = creationResponseModel.districtCode;
//		this.stateName = creationResponseModel.stateName;
//		this.districtName = creationResponseModel.districtName;
		this.email = creationResponseModel.email;
//		this.kycPhoto = creationResponseModel.kycPhoto;
		this.profilePhoto = creationResponseModel.profilePhoto;
		this.mobile = creationResponseModel.mobile;
		this.pincode = creationResponseModel.pincode;
	}

}
