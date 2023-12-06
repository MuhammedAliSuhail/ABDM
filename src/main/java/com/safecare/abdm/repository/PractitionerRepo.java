package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.PractitionerModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PractitionerRepo {
	@Autowired
	EntityManager entityManager;

	public PractitionerModel getPractitioner(String visitId, int encounterType) {
		String queryString = "SELECT \r\n" + "    cli.Name AS doctorName,\r\n" + "    cli.LicenseCode AS licenseId,\r\n"
				+ "    doc.Code AS doctorCode,\r\n" + "    doc.id AS docId,\r\n"
				+ "    DATE(vd.CreatedDate) AS visitDate,\r\n" + "    vd.CreatedDate AS createDate\r\n" + "FROM\r\n"
				+ "    visitdetails vd\r\n" + "        INNER JOIN\r\n"
				+ "    doctormaster doc ON doc.id = vd.DoctorId\r\n" + "        INNER JOIN\r\n"
				+ "    clinicianmaster cli ON cli.id = doc.ClinicianId\r\n" + "WHERE\r\n" + "    vd.id = " + visitId;
		Query query = entityManager.createNativeQuery(queryString);
		Object[] object = (Object[]) query.getSingleResult();
		PractitionerModel practitionerModel = new PractitionerModel();
		practitionerModel.setPractitionerName(object[0].toString());
		practitionerModel.setLicenseCode(object[1].toString());
		return practitionerModel;
	}
}
