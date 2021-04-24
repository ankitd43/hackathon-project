package com.hackathon.consumer.integration.model;

import java.io.Serializable;

public class ConsumerDetailResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467197385577358123L;

	private Long jobId;

	private String jobStatus;

	private String message;

	private String token;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
