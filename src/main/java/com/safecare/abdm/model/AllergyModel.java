package com.safecare.abdm.model;

import lombok.Data;

@Data
public class AllergyModel {
	private String id;
	private String allergyType;
	private String allergen;
	private String remark;
}
