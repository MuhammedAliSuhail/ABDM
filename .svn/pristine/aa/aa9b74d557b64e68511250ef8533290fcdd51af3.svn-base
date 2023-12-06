package com.safecare.abdm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.PatientHistoryModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PatientHistoryRepo {
	@Autowired
	EntityManager entityManager;

	public List<PatientHistoryModel> getPatientHistory(String patientId) {
		String sqlString = "Select \r\n" + "    pathis.ID as Id,\r\n"
				+ "    pathis.maritalStatusID as maritalStatusID,\r\n" + "    pathis.occupationID as occupationID,\r\n"
				+ "    pathis.family as family,\r\n" + "    pathis.social as social,\r\n"
				+ "    pathis.perinatal as perinatal,\r\n" + "    pathis.immunization as immunization,\r\n"
				+ "    pathis.development as development,\r\n" + "    pathis.remarks as remarks,\r\n"
				+ "    occ.Occupation as occupation,\r\n" + "    occ.SnomedID as snomedID,\r\n"
				+ "    mar.Name as maritalstatus,\r\n" + "    occ.ConceptID as conceptID\r\n" + "FROM\r\n"
				+ "    his_patienthistory as pathis\r\n"
				+ " LEFT JOIN occupationmaster occ ON occ.ID = pathis.occupationID \r\n"
				+ " LEFT JOIN maritalstatusmaster mar ON mar.ID = pathis.maritalStatusID \r\n"
				+ "where pathis.PatientId='" + patientId + "' ";
		Query query = entityManager.createNativeQuery(sqlString);
		List<Object[]> objects = query.getResultList();
		List<PatientHistoryModel> patientHistoryModels = new ArrayList<>();
		for (Object[] object : objects) {
			PatientHistoryModel patientHistoryModel = new PatientHistoryModel();
			patientHistoryModel.setOccupation(object[2].toString());
			patientHistoryModel.setFamilyHistory(object[3].toString());
			patientHistoryModel.setSocial(object[4].toString());
			patientHistoryModel.setPerinatal(object[5].toString());
			patientHistoryModel.setImmunization(object[6].toString());
			patientHistoryModel.setDevelopment(object[7].toString());
			patientHistoryModel.setRemark(object[8].toString());
			patientHistoryModel.setOccupation(object[9].toString());
			patientHistoryModel.setMaritialStatus(object[11].toString());
		}
		return patientHistoryModels;
	}
}
