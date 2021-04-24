package com.hackathon.consumer.integration.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.consumer.integration.model.JobData;

@Repository
public interface IJobRepository extends JpaRepository<JobData, Long>{
	
	@Query("select j from JobData j where j.job_status=?1")
	public List<JobData> findByStatus(String status);
	
	@Query("select j.jobStatus from JobData j where j.job_id=?1")
	public String findStatusById(Long jobId);


}
