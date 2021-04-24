package com.hackathon.consumer.integration.model;

import java.io.Serializable;
import java.util.Date;

public class ConsumerDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467197385577358123L;

	private Long id;

	private Long jobId;

	private String username;

	private String password;

	private Date createdDate;

	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
