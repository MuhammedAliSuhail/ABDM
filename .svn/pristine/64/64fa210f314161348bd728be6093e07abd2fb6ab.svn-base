package com.safecare.abdm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.AllergyModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class AllergyRepo {
	@Autowired
	EntityManager entityManager;

	public List<AllergyModel> getAllergy(String patientId) {
		String sqlString = " Select \r\n" + "    allerHis.ID as Id,\r\n" + "    allerType.Type as allergyType,\r\n"
				+ "    allerMas.Allergen as allergen,\r\n" + "    allerHis.Reactions as reactions,\r\n"
				+ "    allerHis.SeverityID as severityID,\r\n" + "    allerHis.Remarks as remarks\r\n" + "FROM\r\n"
				+ "    his_patientallergyhistory as allerHis\r\n"
				+ " INNER JOIN allergenmaster allerMas ON allerMas.ID = allerHis.`AllergenID` \r\n"
				+ " LEFT JOIN allergytypemaster allerType ON allerType.ID = allerHis.`TypeID` \r\n"
				+ "where allerHis.PatientID='" + patientId + "'  ";
		Query query = entityManager.createNativeQuery(sqlString);
		List<Object[]> object = query.getResultList();
		List<AllergyModel> allergyModels = new ArrayList<>();
		for (Object[] objects : object) {
			AllergyModel allergyModel = new AllergyModel();
			allergyModel.setId(objects[0].toString());
			allergyModel.setAllergyType(objects[1].toString());
			allergyModel.setAllergen(objects[2].toString());
			allergyModels.add(allergyModel);

		}
		return allergyModels;

	}
}
