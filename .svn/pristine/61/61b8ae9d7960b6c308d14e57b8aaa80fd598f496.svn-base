package com.safecare.abdm.healthid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthIDController {
	@Autowired
	HealthIDService healthIDService;

	@PostMapping("createHealthIDWithPreVerified")
	public HealthIDCreationResponseModel createHealthIDWithPreVerified(
			@RequestBody HealthIDCreationRequestModel healthIDCreationRequestModel) {
		return healthIDService.createHealthID(healthIDCreationRequestModel);
	}

	@PostMapping("isHealthIDisExist")
	public HealthIDIsExistResponseModel isHealthIDIsExists(@RequestBody HealthIDRequestModel healthIDRequestModel) {
		return healthIDService.isHealthIDExists(healthIDRequestModel);
	}

	@PostMapping("searchWithHealthID")
	public HealthIDCreationResponseModel searchWithHealthID(@RequestBody HealthIDRequestModel healthIDRequestModel) {
		return healthIDService.searchWithHealtID(healthIDRequestModel);
	}

	@PostMapping("test")
	public HealthIDCreationResponseModel test(@RequestBody HealthIDCreationResponseModel healthIDCreationRequestModel) {
		return healthIDService.createHealthID1(healthIDCreationRequestModel);
	}
}
