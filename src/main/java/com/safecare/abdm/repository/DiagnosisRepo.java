package com.safecare.abdm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.DiagnosisModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class DiagnosisRepo {
	@Autowired
	EntityManager entityManager;

	public List<DiagnosisModel> getDiagnosis(String visitId) {
		String sqlQuery = " SELECT \r\n" + "    dd.Orderno AS Orderno,\r\n" + "    dd.ID AS id,\r\n"
				+ "    dm.ID AS diagMasterID,\r\n" + "    dd.PrimarySecondary AS diag,\r\n" + "    dm.Code AS cpt,\r\n"
				+ "    dm.ShortDesc AS name,\r\n" + "    favdaig.ID AS lastid,\r\n" + "    dd.Remarks Remarks\r\n"
				+ "FROM\r\n" + "    diagnosisdetails dd\r\n" + "        INNER JOIN\r\n"
				+ "    diagnosismaster dm ON dm.ID = dd.IcdId\r\n" + "        LEFT JOIN\r\n"
				+ "    favourtedaignosis favdaig ON favdaig.IcdId = dd.IcdId\r\n"
				+ "        AND favdaig.DoctorID =11 \r\n" + " WHERE\r\n" + "    dd.VisitID ='" + visitId + "' \r\n"
				+ "AND (dd.docorAppr = 'Approved'\r\n" + "        OR dd.docorAppr IS NULL)\r\n"
				+ "        AND dd.status = 0\r\n" + " AND dm.IcdVersion != 'SNOMED' \r\n" + "ORDER BY dd.Orderno ASC";
		Query query = entityManager.createNativeQuery(sqlQuery);
		List<DiagnosisModel> diagnosisModels = new ArrayList<>();
		List<Object[]> object = query.getResultList();
		for (Object[] objects : object) {
			DiagnosisModel diagnosisModel = new DiagnosisModel();
			diagnosisModel.setCptCode(objects[4].toString());
			diagnosisModel.setName(objects[5].toString());
			diagnosisModel.setRemark(objects[7].toString());
			diagnosisModel.setDiagnosisMasterId(objects[1].toString());
			diagnosisModels.add(diagnosisModel);
		}
		return diagnosisModels;
	}
}
