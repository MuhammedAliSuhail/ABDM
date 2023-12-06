package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.WomenHealthModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class WomenHealthRepo {
	@Autowired
	EntityManager entityManager;

	public WomenHealthModel getWomenHealth(String visitId) {
		String sqlString = "SELECT \r\n" + "    MenarcheAge,\r\n" + "    MenopauseAge\r\n" + "FROM\r\n"
				+ "    gynacogeneraldetails\r\n" + "    where VissitID='" + visitId + "'";
		Query query = entityManager.createNativeQuery(sqlString);
		Object[] object = (Object[]) query.getSingleResult();
		WomenHealthModel womenHealthModel = new WomenHealthModel();
		womenHealthModel.setMenarcheAge(object[0].toString());
		womenHealthModel.setMenopauseAge(object[1].toString());
		sqlString = "SELECT \r\n" + "    Lmp, DATE_FORMAT(Lmp, '%Y%m%d')\r\n" + "FROM\r\n"
				+ "    gynacomenstrualhistory\r\n" + "WHERE\r\n" + "    visitid = '" + visitId + "'";
		query = entityManager.createNativeQuery(sqlString);
		object = (Object[]) query.getSingleResult();
		womenHealthModel.setLastMenstrualPeriod(object[0].toString());
		womenHealthModel.setLastMenstrualPeriodValue(object[1].toString());
		return womenHealthModel;
	}
}
