package com.safecare.abdm.healthidlinking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthIDLinkingController {
	@Autowired
	HealthIDLinkingService healthIDLinkingService;

	@PostMapping("fetchModes")
	public FetchModeRequestModel getFetchModes(@RequestBody FetchModeRequestModel fetchModeRequestModel) {
		return healthIDLinkingService.getFetchModes(fetchModeRequestModel);
	}

	@PostMapping("authinit")
	public AuthInitRequestModel authInit(@RequestBody AuthInitRequestModel authInitRequestModel) {
		return healthIDLinkingService.authInit(authInitRequestModel);
	}

	@PostMapping("authConfirm")
	public AuthConfirmRequestModel authConfirm(@RequestBody AuthConfirmRequestModel authConfirmRequestModel) {
		return healthIDLinkingService.authConfirm(authConfirmRequestModel);
	}

	@PostMapping("careContextLinking")
	public CareContextLinkingRequestModel careContextLinking(
			@RequestBody CareContextLinkingRequestModel carContextLinkingRequestModel) {
		return healthIDLinkingService.careContextLinking(carContextLinkingRequestModel);
	}

}
