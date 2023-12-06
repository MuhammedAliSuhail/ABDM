package com.safecare.abdm.prescription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safecare.abdm.notify.DateRange;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PrescriptionRepo {
	@Autowired
	EntityManager en;

	public List<PrescriptionDetail> getPrecriptionList(String visitId, DateRange dateRange) {
		String search = "";
		if (null != dateRange) {
			search = " ";
		}
		Query query = en.createNativeQuery("SELECT \r\n"
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
		List<PrescriptionDetail> dto = new ArrayList<>();
		for (Object[] obj : entries) {
			var j = 0;
			PrescriptionDetail entry = new PrescriptionDetail();
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

	public PatientDetail getPatientDetailsFromVisitId(String visitId) {
		Query query = en.createNativeQuery("SELECT \r\n" + "    GETPATIENTFULLNAME(pm.id),\r\n"
				+ "    DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), pm.DOB)), '%Y') + 0 AS age,\r\n"
				+ "    pm.dob AS dateOfBirth,\r\n" + "    pm.gender AS gender,\r\n" + "    pm.patientId AS uhid,\r\n"
				+ "    pm.PhoneNo\r\n" + "FROM\r\n" + "    visitdetails vd\r\n" + "        INNER JOIN\r\n"
				+ "    patientmaster pm ON pm.id = vd.patientId WHERE vd.id=" + visitId);

		Object[] obj = (Object[]) query.getSingleResult();
		PatientDetail patientDetail = new PatientDetail();
		int j = 0;
		patientDetail.setPatientName(obj[j++].toString());
		patientDetail.setAge(obj[j++].toString());
		patientDetail.setDateOfBirth(obj[j++].toString());
		patientDetail.setGender(obj[j++].toString());
		patientDetail.setUhid(obj[j++].toString());
		patientDetail.setPhoneNo(obj[j++].toString());
		return patientDetail;
	}

	public DoctorDetail getDoctorDetailsByVisitId(String visitId) {
		Query query = en.createNativeQuery("SELECT \r\n" + "    cli.Name AS doctorName,\r\n"
				+ "    cli.LicenseCode AS licenseId,\r\n" + "    doc.Code AS doctorCode,\r\n"
				+ "    doc.id AS docId,date(vd.CreatedDate) as visitDate,\r\n" + "    vd.CreatedDate as createDate \r\n"
				+ "FROM\r\n" + "    visitdetails vd\r\n" + "        INNER JOIN\r\n"
				+ "    doctormaster doc ON doc.id = vd.DoctorId\r\n" + "        INNER JOIN\r\n"
				+ "    clinicianmaster cli ON cli.id = doc.ClinicianId\r\n" + "    where vd.id=" + visitId);

		Object[] obj = (Object[]) query.getSingleResult();
		DoctorDetail doctorDetail = new DoctorDetail();
		int j = 0;
		doctorDetail.setDoctorName(obj[j++].toString());
		doctorDetail.setLicenseId(obj[j++].toString());
		doctorDetail.setDoctorCode(obj[j++].toString());
		doctorDetail.setDoctorId(obj[j++].toString());
		doctorDetail.setVisitDate(obj[j++].toString());
		doctorDetail.setCreateDate(obj[j++].toString());
		return doctorDetail;
	}

	public List<DiagnosisDetail> getDiagnosisDetails(String visitId) {
		Query query = en.createNativeQuery(" SELECT \r\n" + "    dd.Orderno AS orderNo,\r\n" + "    dd.ID AS id,\r\n"
				+ "    dm.ID AS diagMasterID,\r\n" + "    dd.PrimarySecondary AS diagnosisType,\r\n"
				+ "    dm.Code AS cptCode,\r\n" + "    dm.ShortDesc AS diagnosisName\r\n" + "    \r\n" + "FROM\r\n"
				+ "    diagnosisdetails dd\r\n" + "        INNER JOIN\r\n"
				+ "    diagnosismaster dm ON dm.ID = dd.IcdId\r\n" + "     \r\n" + " WHERE\r\n" + "    dd.VisitID ="
				+ visitId + " \r\n" + "AND (dd.docorAppr = 'Approved'\r\n" + "        OR dd.docorAppr IS NULL)\r\n"
				+ "        AND dd.status = 0\r\n" + " AND dm.IcdVersion != 'SNOMED' \r\n" + "ORDER BY dd.Orderno ASC");

		List<Object[]> objs = query.getResultList();
		List<DiagnosisDetail> diagnosisDetails = new ArrayList<>();
		for (Object[] obj : objs) {
			DiagnosisDetail diagnosisDetail = new DiagnosisDetail();
			int j = 0;
			diagnosisDetail.setOrderNo(obj[j++].toString());
			diagnosisDetail.setId(obj[j++].toString());
			diagnosisDetail.setDiagMasterID(obj[j++].toString());
			diagnosisDetail.setDiagnosisType(obj[j++].toString());
			diagnosisDetail.setCptCode(obj[j++].toString());
			diagnosisDetail.setDiagnosisName(obj[j++].toString());
			diagnosisDetails.add(diagnosisDetail);
		}
		return diagnosisDetails;
	}
}
