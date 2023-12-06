package com.safecare.abdm.fhirDataTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataTransferController {
	@Autowired
	DataTransferService dataTransferService;

	@PostMapping("dataTransfer")
	public String dataTransfer() throws Exception {
		dataTransferService.transferData();
		return "success";
	}
}
