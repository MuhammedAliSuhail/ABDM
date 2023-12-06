package com.safecare.abdm.prescription;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import ca.uhn.fhir.parser.DataFormatException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PrescriptionController {
	@Autowired
	PrescriptionBundleService service;
	@Autowired
	PrescriptionService prescriptionService;

	@Autowired
	PrescriptionRepo repo;

	@GetMapping("/export-to-pdf")
	public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		List<PrescriptionDetail> listofStudents = repo.getPrecriptionList("81004", null);
		PrescriptionReport report = new PrescriptionReport();
		report.generate(listofStudents, response);
	}

	@GetMapping("getPrescriptionBundle")
	public String getBundle(@RequestParam("visitId") String visitId)
			throws DataFormatException, IOException, ParseException {
		return prescriptionService.prescriptionBundle(visitId, null);
	}
}
