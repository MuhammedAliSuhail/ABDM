package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.PatientModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PatientRepo {
	private static final int OP = 0;
	@Autowired
	EntityManager entityManager;

	public PatientModel getPatient(String visitId, int encounterType) {
		PatientModel patientModel = null;
		if (encounterType == OP) {
			String queryString = "SELECT \r\n" + "    pm.PatientID as patientId,\r\n"
					+ "    GETPATIENTFULLNAME(pm.id) as patientName,\r\n"
					+ "    DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), pm.DOB)), '%Y') + 0 AS age,\r\n"
					+ "    pm.dob AS dateOfBirth,\r\n" + "    pm.gender AS gender,\r\n"
					+ "    pm.PhoneNo as contactNo\r\n" + "FROM\r\n" + "    visitdetails vd\r\n"
					+ "        INNER JOIN\r\n" + "    patientmaster pm ON pm.id = vd.PatientID\r\n"
					+ "    WHERE vd.id='" + visitId + "'";

			Query query = entityManager.createNativeQuery(queryString);
			Object[] result = (Object[]) query.getSingleResult();
			patientModel = new PatientModel();
			patientModel.setPatientId(result[0].toString());
			patientModel.setPatientName(result[1].toString());
			patientModel.setAge(Integer.parseInt(result[2].toString()));
			patientModel.setDob(result[3].toString());
			patientModel.setGender(result[4].toString());
			patientModel.setContactNumber(result[5].toString());

		}
		return patientModel;
	}
}
