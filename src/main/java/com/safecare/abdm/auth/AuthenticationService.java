package com.safecare.abdm.auth;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.safecare.abdm.utilities.AbdmConfig;
import com.safecare.abdm.utilities.AbdmCredintials;

@Service
public class AuthenticationService {
	public String ACCESS_TOKEN;
	public Long EXPIRES_IN;
	public String REFRESH_TOKEN;
	public Long REFRESH_EXPIRES_IN;

	public String getAccessToken() {
		if (!tokenValid()) {
			AuthRequestModel authRequestModel = new AuthRequestModel(AbdmCredintials.CLIENT_ID,
					AbdmCredintials.CLIENT_SECRET);
			HttpEntity<AuthRequestModel> httpEntity = new HttpEntity<AuthRequestModel>(authRequestModel);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<AuthResponseModel> responseEntity = restTemplate.exchange(AbdmConfig.GATEWAY_HOST,
					HttpMethod.POST, httpEntity, AuthResponseModel.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				AuthResponseModel authResponseModel = responseEntity.getBody();
				ACCESS_TOKEN = authResponseModel.getAccessToken();
				EXPIRES_IN = authResponseModel.getExpiresIn() * 1000 + Calendar.getInstance().getTimeInMillis();

			}
		}
		return ACCESS_TOKEN;

	}

	private boolean tokenValid() {
		Calendar c = Calendar.getInstance();
		Date nowDate = c.getTime();
		if (EXPIRES_IN != null)
			c.setTimeInMillis(EXPIRES_IN);
		Date expireDate = c.getTime();

		return nowDate.compareTo(expireDate) < 0;
	}

}
