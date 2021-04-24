package com.hackathon.consumer.integration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class JobData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="job_id")
	private Long job_id;
	@Override
	public String toString() {
		return "JobData [job_id=" + job_id + ", created_date=" + created_date + ", modified_date=" + modified_date
				+ ", ordr_date=" + ordr_date + ", job_status=" + job_status + ", file_type=" + file_type
				+ ", is_active=" + is_active + "]";
	}
	public JobData(String created_date, String modified_date, String ordr_date, String job_status,
			String file_type, boolean is_active, String user_name) {
		super();
		
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.ordr_date = ordr_date;
		this.job_status = job_status;
		this.file_type = file_type;
		this.is_active = is_active;
		this.user_name = user_name;
	}
	public Long getJob_id() {
		return job_id;
	}
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getOrdr_date() {
		return ordr_date;
	}
	public void setOrdr_date(String ordr_date) {
		this.ordr_date = ordr_date;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	private String created_date;
	private String modified_date;
	private String ordr_date;
	private String job_status;
	private String file_type;
	private boolean is_active;
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

}
