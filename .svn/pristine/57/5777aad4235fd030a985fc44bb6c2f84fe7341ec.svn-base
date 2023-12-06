package com.safecare.abdm.opConsultNote;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class OpConsultRepo {
	@Autowired
	EntityManager entityManager;

	public OpVisitDetail getOpVisitDetail(String visitId) {
		Query query = entityManager.createNativeQuery("SELECT \r\n" + "    vd.id as visitId,\r\n"
				+ "    vd.CreatedDate as visitDate,\r\n" + "    pm.PatientID as uhid,\r\n"
				+ "    getpatientfullname(pm.id) as patientName,\r\n" + "    doc.Code as doctorCode,\r\n"
				+ "    doc.ClinicianName as doctorName,\r\n" + "    dep.DeptCode as departmentCode,\r\n"
				+ "    dep.name as departmentName,\r\n" + "    ph.pharmacyId as hospitalCode,\r\n"
				+ "    ph.Name as hospitalName\r\n" + "    \r\n" + "FROM\r\n" + "    visitdetails vd\r\n"
				+ "        INNER JOIN\r\n" + "    patientmaster pm ON pm.id = vd.PatientID\r\n"
				+ "        INNER JOIN\r\n" + "    doctormaster doc ON doc.id = vd.doctorid\r\n"
				+ "        INNER JOIN\r\n" + "    department dep ON dep.DeptID = vd.DepartmentId\r\n"
				+ "        INNER JOIN\r\n" + "    pharmacymaster ph ON ph.PharmacyID = vd.CompanyId\r\n"
				+ "WHERE vd.id=" + visitId + "\r\n" + "\r\n" + "");
		Object[] object = (Object[]) query.getSingleResult();
		int j = 0;
		OpVisitDetail opVisitDetail = new OpVisitDetail();
		opVisitDetail.setVisitId(object[j++].toString());
		opVisitDetail.setVisitDate(object[j++].toString());
		opVisitDetail.setUhid(object[j++].toString());
		opVisitDetail.setPatientName(object[j++].toString());
		opVisitDetail.setDoctorCode(object[j++].toString());
		opVisitDetail.setDoctorName(object[j++].toString());
		opVisitDetail.setDepartmentCode(object[j++].toString());
		opVisitDetail.setDepartmentName(object[j++].toString());
		opVisitDetail.setHospitalCode(object[j++].toString());
		opVisitDetail.setHospitalName(object[j++].toString());
		return opVisitDetail;

	}

	public List<ComplientDetail> getComplientDetails(String visitId) {
		Query query = entityManager.createNativeQuery(
				" SELECT \r\n" + "    cl.id AS ID,\r\n" + "    cm.Name AS cmpName,\r\n" + "    cm.code as cmpCode,\r\n"
						+ "    sd.name as snomedText,\r\n" + "    sd.SNOMEDId as snomedId\r\n" + "    \r\n" + "FROM\r\n"
						+ "    complaintlist cl\r\n" + "        LEFT JOIN\r\n"
						+ "    complaintmaster cm ON cm.ID = cl.ComplaintMasterID\r\n" + "       LEFT JOIN \r\n"
						+ "       snomed_dictionary sd on sd.SNOMEDId=cm.SNOMEDId and sd.ConceptId=cm.ConceptId\r\n"
						+ "WHERE\r\n" + "     cl.VisitID = '" + visitId + "'\r\n" + "        AND cl.Status = '1'");
		List<ComplientDetail> complientDetails = new ArrayList<>();
		List<Object[]> objects = query.getResultList();
		for (Object[] objects2 : objects) {
			ComplientDetail complientDetail = new ComplientDetail();
			int j = 0;
			complientDetail.setId(objects2[j++].toString());
			complientDetail.setComplientName(objects2[j++].toString());
			complientDetail.setComplientCode(objects2[j++].toString());
			complientDetail.setSnomedName(objects2[j++].toString());
			complientDetail.setSnomedCode(objects2[j++].toString());
			complientDetails.add(complientDetail);

		}
		return complientDetails;

	}
}
