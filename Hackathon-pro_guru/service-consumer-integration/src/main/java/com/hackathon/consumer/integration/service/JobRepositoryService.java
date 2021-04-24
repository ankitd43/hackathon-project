package com.hackathon.consumer.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hackathon.consumer.integration.model.JobData;



@Service
public class JobRepositoryService {
	
	@Autowired
	IJobRepository jobRepository;

	
	public JobData saveJobs(JobData data) {
	
	return jobRepository.save(data);
	
	}
}
