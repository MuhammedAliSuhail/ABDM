package com.safecare.abdm.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
	@Autowired
	RequestService requestService;

	@RequestMapping("/v0.5/health-information/hip/request")
	public void request(@RequestBody RequestModel requestModel) {

		requestService.request(requestModel);
		requestService.onRequest(requestModel);

	}
}
