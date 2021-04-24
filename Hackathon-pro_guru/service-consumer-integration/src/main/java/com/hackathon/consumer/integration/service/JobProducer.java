package com.hackathon.consumer.integration.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.consumer.integration.dao.IJobRepository;
import com.hackathon.consumer.integration.helper.common_logic.CommonUtility;
import com.hackathon.consumer.integration.model.JobData;

public class JobProducer {
	
	@Autowired
	IJobRepository jobRepo;
	
	
	private BlockingQueue<JobData> queue;
	
	public void setQueue(BlockingQueue<JobData> queue) {
		this.queue = queue;
	}
	
	public void doJobProcess() {
		
		try {
			
            for (JobData job:doProcess()) {
            	queue.put(job);
 
              }
              
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
 
    private List<JobData> doProcess() {
    	
    	        
    	//System.out.println(jobRepo);
    	
        return jobRepo.findByStatus(CommonUtility.JOB_STATUS_NEW);
    }

}
