package com.safecare.abdm.callback;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallBackController {
	@Autowired
	CallBackService callBackService;

	@PostMapping("/v0.5/users/auth/on-fetch-modes")
	public void onfetchmodes(@RequestBody OnFetchModeModel requestBody, @RequestHeader Map<String, String> headers) {
		System.out.println(headers.toString());
		callBackService.onFetchMode(requestBody);
	}

	@PostMapping("/v0.5/users/auth/on-init")
	public void oninit(@RequestBody OnInitModel onInitModel) {
		callBackService.onInit(onInitModel);
	}

	@PostMapping("/v0.5/users/auth/on-confirm")
	public void onconfirm(@RequestBody OnConfirmModel onConfirmModel) {
		callBackService.onConfirm(onConfirmModel);
	}

	@PostMapping("/v0.5/links/link/on-add-contexts")
	public void onaddcontexts(@RequestBody CareContextModel obj) {
		callBackService.careContext(obj);

	}

//	@RequestMapping(value = "/v0.5/consents/hip/notify", method = { RequestMethod.GET, RequestMethod.POST })
//	public void notify(@RequestBody NotifyRequestModel obj) {
//		callBackService.notify(obj);
//
//	}

}
