/**
 * 
 */
package com.safecare.abdm.listener;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.safecare.abdm.auth.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Safecare
 *
 */

@Component
public class ReqListener implements HandlerInterceptor {

	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle starts "+ new Date());
		System.out.println(request.getRequestId());
		String requestPath = request.getRequestURI();
		System.out.println(requestPath);
		System.out.println(request.getRequestURL());
		System.out.println(request.getProtocolRequestId());
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		    ipAddress = request.getRemoteAddr();  
		}

		// printBody(request);

		if (requestPath.equals("/v0.5/users/auth/on-fetch-modes")) {
			// printBody(request);
		}

		if (requestPath.equals("/v0.5/users/auth/on-init")) {
			// printBody(request);
		}
		if (requestPath.equals("/v0.5/consents/hip/notify")) {
			// printBody(request);
		}

		if (requestPath.equals("/v0.5/care-contexts/discover")) {
			// printBody(request);
		}

		if (requestPath.equals("/v0.5/consents/hip/notify")) {}

		// return HandlerInterceptor.super.preHandle(request, response, handler);
		System.out.println("preHandle ends "+ new Date());
		return true;
	}

	private void printBody(HttpServletRequest request) throws IOException {
		// String jsonString = IOUtils.toString(request.getInputStream());
		System.out.println("*********");
		// System.out.println(jsonString);
		System.out.println("*********");
	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("--postHandle");
//		// HandlerInterceptor.super.postHandle(request, response, handler,
//		// modelAndView);
//
//	}

//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("--afterCompletion");
//		// HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
}
