package com.safecare.abdm.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class NotifyThread extends Thread {
	@Autowired
	NotifyService notifyService;
	@Setter
	private NotifyRequestModel notifyRequestModel;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		notifyService = new NotifyService();
		notifyService.onNotify(notifyRequestModel);
	}
}
