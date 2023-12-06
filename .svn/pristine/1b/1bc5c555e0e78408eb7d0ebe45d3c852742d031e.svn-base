package com.safecare.abdm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.model.PrescriptionModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PrescriptionMasRepo {
	@Autowired
	EntityManager entityManager;

	public List<PrescriptionModel> getPrescriptions(String visitId) {

		String search = "";

		Query query = entityManager.createNativeQuery("SELECT \r\n"
				+ "    epd.PlugId AS productId,dm.ProductName as productName,\r\n" + "    epd.id AS prescriptionId,\r\n"
				+ "    epd.Quantity AS quantity,\r\n" + "    rm.Description AS routeOfAdmission,\r\n"
				+ "    fm.FrequencyName AS instruction,\r\n" + "    epd.CreatedDate AS createdDate,\r\n"
				+ "    epd.visitid AS visitId,\r\n" + "    epd.Instructions AS direction,\r\n"
				+ "    IFNULL(epins.Instruction, 'N/A') AS additionalInstruction, date(epd.CreatedDate) as orderedDate \r\n"
				+ "FROM\r\n" + "    eprescriptiondetails epd\r\n" + "        LEFT JOIN\r\n"
				+ "    productmaster dm ON epd.PlugID = dm.ProductID\r\n" + "        LEFT JOIN\r\n"
				+ "    eprescriptionfrequencytypemaster fm ON fm.id = epd.FrequencyId\r\n" + "        LEFT JOIN\r\n"
				+ "    eprescriptionadditionalinstruction epins ON epins.ID = epd.AdditionalInstructionID\r\n"
				+ "        INNER JOIN\r\n" + "    visitdetails vd ON vd.ID = epd.visitid\r\n" + "        LEFT JOIN\r\n"
				+ "    roa_master rm ON rm.ID = epd.roa\r\n" + "        LEFT JOIN\r\n"
				+ "    substitute_product_pharmacy sub ON sub.rowId = epd.id AND sub.visitType = 1\r\n" + "WHERE\r\n"
				+ "    epd.VisitID = " + visitId + "\r\n" + "GROUP BY epd.id");

		List<Object[]> entries = query.getResultList();
		List<PrescriptionModel> dto = new ArrayList<>();
		for (Object[] obj : entries) {
			var j = 0;
			PrescriptionModel entry = new PrescriptionModel();
			entry.setProductId(Integer.parseInt(obj[j++].toString()));
			entry.setProductName(obj[j++].toString());
			entry.setPrescriptionId(Integer.parseInt(obj[j++].toString()));
			entry.setQuantity(Double.parseDouble(obj[j++].toString()));
			entry.setRouteOfAdmission(obj[j++].toString());
			entry.setInstruction(obj[j++].toString());
			entry.setDirection(obj[j++].toString());
			entry.setAdditionalInstruction(obj[j++].toString());
			entry.setOrderedDate(obj[j++].toString());
			dto.add(entry);
		}

		return dto;
	}

}
