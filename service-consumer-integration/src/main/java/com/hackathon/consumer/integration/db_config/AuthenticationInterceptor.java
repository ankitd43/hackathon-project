package com.hackathon.consumer.integration.db_config;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.consumer.integration.service.auth.ConsumerAuthService;

public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Autowired
	private ConsumerAuthService consumerAuthService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		if(requestURI.startsWith("/consumer-integration")){
			if(requestURI.startsWith("/consumer-integration/login")) {
				return true;
			}else {
				String header = request.getHeader("token");
				if(header == null || header.isEmpty()) {
					response.setStatus(HttpStatus.FORBIDDEN.value());
					setCustomeHttpServletResponse("Yyou are not authorised consumer", response);
				}else {
					return consumerAuthService.verifyToken(header);
				}
			}
		}else {
			response.setStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value());
			setCustomeHttpServletResponse("", response);
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Do nothing because of X and Y.

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Do nothing because of X and Y.

	}
	
	 void setCustomeHttpServletResponse(String msg, HttpServletResponse httpServletResponse) throws IOException {
		 httpServletResponse.setHeader("Pragma","public");
		 httpServletResponse.setHeader("Expires","0");
		 httpServletResponse.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
		 httpServletResponse.setHeader("Cache-Control","public");
		 httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
		 httpServletResponse.setHeader("X-XSS-Protection", "1; mode=block ");
		 httpServletResponse.setHeader("X-Frame-Options", "DENY");
		 httpServletResponse.setHeader("Location", "urmc.com");
		 
		 ObjectMapper mapper = new ObjectMapper();
		 try (OutputStream outputStream = httpServletResponse.getOutputStream()){
			 if(msg != null ) {
				 mapper.writeValue(outputStream, msg);
			 }
				 
		 } catch (Exception e) {
			 logger.error("Exception while write data in output stream [{}]", e);
		 } 
	 }
}
