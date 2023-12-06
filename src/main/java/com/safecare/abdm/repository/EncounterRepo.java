package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.EncounterModel;

import jakarta.persistence.EntityManager;

@Repository
public class EncounterRepo {
	@Autowired
	EntityManager entityManager;

	public EncounterModel getEncounter(String visitId, int enounterType) {
		return new EncounterModel();
	}
}
