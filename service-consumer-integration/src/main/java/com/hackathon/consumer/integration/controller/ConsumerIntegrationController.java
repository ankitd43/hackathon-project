package com.hackathon.consumer.integration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.consumer.integration.helper.exception.ConsumerIntegrationException;
import com.hackathon.consumer.integration.model.ConsumerDetailRequest;
import com.hackathon.consumer.integration.model.ConsumerDetailResponse;
import com.hackathon.consumer.integration.service.auth.ConsumerAuthService;
import com.hackathon.consumer.integration.service.auth.ConsumerJobDetailService;

@RestController
public class ConsumerIntegrationController {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerIntegrationController.class);

	@Autowired
	private ConsumerAuthService consumerAuthService;
	
	@Autowired
	private ConsumerJobDetailService consumerJobDetailService;
	
	@PostMapping(value ="/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody ConsumerDetailRequest consumerDetailRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) throws  ConsumerIntegrationException{
		logger.info("Enter into login()");
		HttpStatus httpStatus = HttpStatus.OK;
		ConsumerDetailResponse consumerDetailResponse = null;
//		try {
			consumerDetailResponse = consumerAuthService.login(consumerDetailRequest);
			
//		}catch (UnsupportedEncodingException | GeneralSecurityException e) {
//			logger.error("Exception while varify consumer [{}]", e);
//			consumerDetailResponse = new ConsumerDetailResponse();
//			consumerDetailResponse.setMessage("sone thing went wrong");
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
		logger.info("Exit from login()");
		return new ResponseEntity<Object>(consumerDetailResponse, httpStatus );
	}
	
	@PostMapping(value ="/check-job-status/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> checkJobStatus( @PathVariable("jobId") Long jobId , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
		logger.info("Enter into login()");
		HttpStatus httpStatus = HttpStatus.OK;
		ConsumerDetailResponse consumerDetailResponse = null;
//		try {
			consumerDetailResponse = consumerJobDetailService.checkConsumerStatus(jobId);
			
//		}catch (ConsumerIntegrationException e) {
//			logger.error("Exception while varify consumer [{}]", e);
//			consumerDetailResponse = new ConsumerDetailResponse();
//			consumerDetailResponse.setMessage("sone thing went wrong");
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
		logger.info("Exit from login()");
		return new ResponseEntity<Object>(consumerDetailResponse, httpStatus );
	}
}
