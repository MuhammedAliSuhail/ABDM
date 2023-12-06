package com.safecare.abdm.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class RequestModel {
	public static String getRequestId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}

	public static String getTimeStamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String text = formatter.format(new Date());
		return text;
	}
}
