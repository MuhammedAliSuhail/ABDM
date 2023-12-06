package com.safecare.abdm.healthidlinking;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	private String referenceNumber;
	private String display;
	List<CareContext> careContexts;
}
