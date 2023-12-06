package com.safecare.abdm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.ProcedureModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class ProcedureRepo {
	@Autowired
	EntityManager entityManager;

	public List<ProcedureModel> getProcedure(String visitId) {
		String sqlQuery = " SELECT \r\n" + "    pd.ID AS id,\r\n" + "    cm.ItemGroupId AS itmgrpID,\r\n"
				+ "    IF(cm.CPTCode IS NULL OR cm.CPTCode = '',\r\n" + "        cm.code,\r\n"
				+ "        cm.CPTCode) AS code,\r\n" + "    cm.Name AS itmName,\r\n"
				+ "    pd.Quantity AS quantity,\r\n" + "    pd.Remark AS remark\r\n" + "    FROM\r\n"
				+ "    proceduredetails pd\r\n" + "        INNER JOIN\r\n"
				+ "    cptmaster cm ON cm.ID = pd.ItemId       \r\n" + "        INNER JOIN\r\n"
				+ "    visitdetails vd ON vd.ID = pd.VisitId    \r\n" + "WHERE\r\n" + "    pd.VisitID ='" + visitId
				+ "'";
		Query query = entityManager.createNativeQuery(sqlQuery);
		List<Object[]> objects = query.getResultList();
		List<ProcedureModel> procedureModels = new ArrayList<>();

		for (Object[] object : objects) {
			ProcedureModel procedureModel = new ProcedureModel();
			procedureModel.setId(object[0].toString());
			procedureModel.setCode(object[2].toString());
			procedureModel.setText(object[3].toString());
			procedureModel.setQty(Integer.parseInt(object[4].toString()));
			procedureModels.add(procedureModel);
		}
		return procedureModels;
	}
}
