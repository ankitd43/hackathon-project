package com.hackathon.consumer.integration.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.consumer.integration.model.JobData;

@Service
public class JobOrderCreation {
	
	
	@Autowired
	JobRepositoryService jobRepository;
	
	@Autowired
	JobProducer producer;
	
	@Autowired
	JobProcess jobProcess;
	
	public String jobCreation(JobData data) {
		
		
		JobData jobData = jobRepository.saveJobs(data);
		
		if(jobData != null) {
			BlockingQueue<JobData> queue = new LinkedBlockingDeque<>();
			
			producer.setQueue(queue);
			producer.doJobProcess();
			
			jobProcess.setQueue(queue);
			jobProcess.doJobProcess();
		}
		
		
		return data.getJob_id().toString();
	}
	
	
	

}
