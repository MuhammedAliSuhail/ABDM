package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.VitalSignsModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class VitalSignsRepo {
	@Autowired
	EntityManager entityManager;

	public VitalSignsModel getVitalSings(String visitId) {
		String sqlQuery = "SELECT \r\n" + "    Height,\r\n" + "    Weight,\r\n" + "    BMI,\r\n" + "    temp,\r\n"
				+ "    SyBP,\r\n" + "    DiBP,\r\n" + "    PulseRate,\r\n" + "    RespiratoryRate,\r\n" + "    spo\r\n"
				+ ", date_format(CreatedDate,'%Y-%m-%d') FROM\r\n" + "    nursingdetails\r\n" + "    where VisitID='"
				+ visitId + "'";
		Query query = entityManager.createNativeQuery(sqlQuery);
		Object[] object = (Object[]) query.getSingleResult();
		VitalSignsModel vitalSignsModel = new VitalSignsModel();
		vitalSignsModel.setHeight(object[0].toString());
		vitalSignsModel.setWeight(object[1].toString());
		vitalSignsModel.setBmi(object[2].toString());
		vitalSignsModel.setBodyTemparature(object[3].toString());
		vitalSignsModel.setSystolicBloodPressure(object[4].toString());
		vitalSignsModel.setDiastolicBloodPressure(object[5].toString());
		vitalSignsModel.setHeartRate(object[6].toString());
		vitalSignsModel.setRepiratoryRate(object[7].toString());
		vitalSignsModel.setOxygenSaturation(object[8].toString());
		vitalSignsModel.setEnterDate(object[9].toString());
		return vitalSignsModel;
	}
}
