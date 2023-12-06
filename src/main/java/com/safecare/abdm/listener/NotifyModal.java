package com.safecare.abdm.listener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotifyModal {

	// steps.trigger.event.body.notification.consentDetail.careContexts[0].careContextReference
	// steps.trigger.event.body.notification.consentDetail.careContexts[0].patientReference
	// steps.trigger.event.body.notification.consentDetail.consentId
	// steps.trigger.event.body.notification.consentDetail.consentManager.id
	// steps.trigger.event.body.notification.consentDetail.createdAt
	// steps.trigger.event.body.notification.consentDetail.hiTypes[0]
	// steps.trigger.event.body.notification.consentDetail.hip.id
	// steps.trigger.event.body.notification.consentDetail.hip.name
	// steps.trigger.event.body.notification.consentDetail.patient.id
	private String patientId;
	// steps.trigger.event.body.notification.consentDetail.permission.accessMode
	// steps.trigger.event.body.notification.consentDetail.permission.dataEraseAt
	// steps.trigger.event.body.notification.consentDetail.permission.dateRange.from
	// steps.trigger.event.body.notification.consentDetail.permission.dateRange.to
	// steps.trigger.event.body.notification.consentDetail.permission.frequency.repeats
	// steps.trigger.event.body.notification.consentDetail.permission.frequency.unit
	// steps.trigger.event.body.notification.consentDetail.permission.frequency.value
	// steps.trigger.event.body.notification.consentDetail.purpose.code
	// steps.trigger.event.body.notification.consentDetail.purpose.refUri
	// steps.trigger.event.body.notification.consentDetail.purpose.text
	// steps.trigger.event.body.notification.consentId
	private String consentId;
	// steps.trigger.event.body.notification.grantAcknowledgement
	// steps.trigger.event.body.notification.signature
	private String signature;
	// steps.trigger.event.body.notification.status
	// steps.trigger.event.body.timestamp
	// steps.trigger.event.body.requestId
	private String requestId;
	private String timestamp;
}
