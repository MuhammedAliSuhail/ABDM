package com.safecare.abdm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.OrganizationModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class OrganizationRepo {
	@Autowired
	EntityManager entityManager;

	public OrganizationModel getOrganization() {
		String queryString = "SELECT \r\n" + "    pharmacyId AS code, name AS organizationName, email AS email\r\n"
				+ "FROM\r\n" + "    pharmacymaster;";
		Query query = entityManager.createNativeQuery(queryString);
		Object[] object = (Object[]) query.getSingleResult();
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setOrganizationCode(object[0].toString());
		organizationModel.setOrganizationName(object[1].toString());
		organizationModel.setEmail(object[2].toString());
		return organizationModel;
	}
}
