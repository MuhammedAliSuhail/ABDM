package com.safecare.abdm.opConsultNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpConsultController {
	@Autowired
	OpConsultRepo opConsultRepo;

	@GetMapping("getOpVisitDetails")
	public OpVisitDetail getOpVisitDetail(@RequestParam("visitId") String visitId) {
		return opConsultRepo.getOpVisitDetail(visitId);
	}
}
