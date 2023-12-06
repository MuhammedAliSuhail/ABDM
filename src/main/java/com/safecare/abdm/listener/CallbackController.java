package com.safecare.abdm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.safecare.abdm.auth.AuthenticationService;

@RestController
public class CallbackController {

	@Autowired
	AuthenticationService authenticationService;

//	@RequestMapping(value = "/v0.5/users/auth/on-fetch-modes", method = { RequestMethod.GET, RequestMethod.POST })
//	public String onfetchmodes(@RequestBody OnFetchModeModal obj) {
//		String requestId = obj.getRequestId(); // .getParameter("requestId");
//		System.out.println("inside on-fetch-modes");
//
//		try {
//			System.out.println("requestId:" + requestId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "OK";
//	}
//
//	@RequestMapping(value = "/v0.5/users/auth/on-init", method = { RequestMethod.GET, RequestMethod.POST })
//	public String oninit(@RequestBody OnInitModal obj) {
//		String transactionId = obj.getTransactionId(); // .getParameter("requestId");
//		System.out.println("inside oninit");
//
//		// steps.trigger.event.body.auth.transactionId
//
//		try {
//			System.out.println("transactionId:" + transactionId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "OK";
//	}
//
//	@RequestMapping(value = "/v0.5/users/auth/on-confirm", method = { RequestMethod.GET, RequestMethod.POST })
//	public String onconfirm(@RequestBody OnConfirmModal obj) {
//		String accessToken = obj.getAccessToken(); // .getParameter("requestId");
//		System.out.println("inside onconfirm");
//
//		// steps.trigger.event.body.auth.accessToken
//		// add-contexts is used in /gateway/v0.5/links/link/add-contexts
//
//		try {
//			System.out.println("accessToken:" + accessToken);
//			System.out.println("getPatientId:" + obj.getPatientId());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "OK";
//	}
//
//	@RequestMapping(value = "/v0.5/links/link/on-add-contexts", method = { RequestMethod.GET, RequestMethod.POST })
//	public String onaddcontexts(@RequestBody OnAddContextModal obj) {
//
//		System.out.println("inside on-add-contexts");
//		// steps.trigger.event.body.acknowledgement.status
//		try {
//
//			System.out.println("acknowledgementStatus:" + obj.getAcknowledgementStatus());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "OK";
//	}
//
//	@RequestMapping(value = "/v0.5/consents/hip/notify", method = { RequestMethod.GET, RequestMethod.POST })
//	public String notify(@RequestBody NotifyModal obj) {
//
//		// printBody(request);
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
//		headers.add("Authorization", "Bearer " + authenticationService.getAccessToken());
//		headers.add("X-CM-ID", "sbx");
//
//		OnNotifyRequestModal onNotifyObj = new OnNotifyRequestModal();
//
//		UUID uuid = UUID.randomUUID();
//		System.out.println("Notify Req ID UUID: " + uuid.toString());
//		System.out.println("Consent ID: " + obj.getConsentId());
//		System.out.println("Resp Obj Request ID: " + obj.getRequestId());
//		System.out.println("Time Stamp: " + obj.getTimestamp());
//
//		AcknowledgementModal ackObj = new AcknowledgementModal();
//		ResponseModal respObj = new ResponseModal();
//
//		ackObj.setConsentId(obj.getConsentId());
//		ackObj.setStatus("OK");
//		respObj.setRequestId(obj.getRequestId());
//
//		onNotifyObj.setRequestId(uuid.toString());
//		onNotifyObj.setTimestamp(obj.getTimestamp());
//		onNotifyObj.setAcknowledgement(ackObj);
//		onNotifyObj.setResp(respObj);

//		HttpEntity<OnNotifyRequestModal> httpEntity = new HttpEntity<OnNotifyRequestModal>(onNotifyObj, headers);
//		String ON_NOTIFY_API = "https://dev.abdm.gov.in/gateway/v0.5/consents/hip/on-notify";
//		System.out.println(ON_NOTIFY_API + " " + new Date());
//		ResponseEntity<ResponseModal> responseEntity = restTemplate.exchange(ON_NOTIFY_API, HttpMethod.POST, httpEntity,
//				ResponseModal.class);
//
//		ResponseModal otpResponseModel = null;
////		HttpStatusCode statusCode = responseEntity.getStatusCode();
////		System.out.println("statusCode:" + statusCode);
//		try {
//			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
//				System.out.println("inside:"+HttpStatus.OK);
//				otpResponseModel = responseEntity.getBody();
//			} else
//				System.out.println("else");
//			if (otpResponseModel == null) {
//				System.out.println("No Response");
//			} else {
//				System.out.println("code:" + otpResponseModel.getCode());
//				System.out.println("message:" + otpResponseModel.getMessage());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return HttpStatus.OK.toString();
//
//	}

}
