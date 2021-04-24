package com.hackathon.consumer.integration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.consumer.integration.model.ConsumerDetailRequest;

@RestController
public class ConsumerIntegrationController {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerIntegrationController.class);

	public ResponseEntity<Object> login(@RequestBody ConsumerDetailRequest consumerDetailRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
		
		return null;
	}
}
