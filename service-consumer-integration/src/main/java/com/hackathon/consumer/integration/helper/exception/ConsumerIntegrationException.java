package com.hackathon.consumer.integration.helper.exception;

import org.springframework.http.HttpStatus;


public class ConsumerIntegrationException extends Exception {
	private static final long serialVersionUID = 7628658109427774183L;
	
	private HttpStatus httpStatus;
	
	public ConsumerIntegrationException(String message, Throwable cause) {
		super(message, cause);
	}
	public ConsumerIntegrationException(String message) {
		super(message);
	}
	public ConsumerIntegrationException(Throwable cause) {
		super("Exception occured", cause);
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public ConsumerIntegrationException setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}

	@Override
	public String toString() {
		return "SidbiIntegrationException [httpStatus=" + httpStatus + ", message=" + getMessage() + "]";
	}
}
