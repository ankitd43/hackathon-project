package com.hackathon.consumer.integration.helper.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackathon.consumer.integration.model.ConsumerDetailResponse;

@ControllerAdvice 
@RestController
public class ExceptionHandlerController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = ConsumerIntegrationException.class)
	public ResponseEntity<ConsumerDetailResponse> handleRetailException(ConsumerIntegrationException ex) {
		logger.error("Error occured", ex);
		HttpStatus httpStatus = ex.getHttpStatus();
		
		ConsumerDetailResponse body = new ConsumerDetailResponse();
		body.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(body, httpStatus);
	}
	
	@ExceptionHandler(value = {NoHandlerFoundException.class, Exception.class})
    public ResponseEntity<ConsumerDetailResponse> requestHandlingNoHandlerFound(Exception ex ,HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
		ConsumerDetailResponse body = new ConsumerDetailResponse();
		HttpStatus httpStatus = HttpStatus.OK;
		if(ex.getCause() instanceof NoHandlerFoundException) {
			body.setMessage("Invaid URL");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
		}else if(ex.getCause() instanceof NullPointerException) {
			body.setMessage("Mandatory details not found");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			
		}else if(ex.getCause() instanceof JsonProcessingException) {
			body.setMessage("Invalid json formate");
			
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		
		}else if(ex.getCause() instanceof GeneralSecurityException || ex.getCause() instanceof UnsupportedEncodingException) {
			body.setMessage("Invalid request or data improper or encrpyption error ");
		response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		
		return new ResponseEntity<>(body, httpStatus);
		
    }
	
}