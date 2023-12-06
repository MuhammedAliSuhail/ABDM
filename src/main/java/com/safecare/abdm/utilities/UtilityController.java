package com.safecare.abdm.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("utility")
public class UtilityController {
	@Autowired
	UtilityService utilityService;

	@GetMapping("states")
	public State[] getStates() {
		return utilityService.getStates();

	}

	@GetMapping("districts")
	public District[] getDistricts(@Param("stateCode") String stateCode) {
		return utilityService.getDistricts(stateCode);
	}
}
