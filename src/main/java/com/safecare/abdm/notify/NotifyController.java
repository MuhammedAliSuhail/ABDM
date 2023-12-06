package com.safecare.abdm.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyController {
	@Autowired
	NotifyService notifyService;

	@RequestMapping(value = "/v0.5/consents/hip/notify", method = { RequestMethod.GET, RequestMethod.POST })
	public void notify(@RequestBody NotifyRequestModel obj) {
		notifyService.notify(obj);
		notifyService.onNotify(obj);
	}

	@RequestMapping(value = "testNotify", method = { RequestMethod.GET, RequestMethod.POST })
	public void notifyTest(@RequestBody NotifyRequestModel obj) {
		notifyService.onNotify(obj);

	}
}
