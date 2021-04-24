package com.hackathon.consumer.integration.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.consumer.integration.dao.IJobRepository;
import com.hackathon.consumer.integration.dao.IOrderRepository;
import com.hackathon.consumer.integration.helper.common_logic.CommonUtility;
import com.hackathon.consumer.integration.model.JobData;
import com.hackathon.consumer.integration.model.OrderData;

public class JobProcess {
	
	private BlockingQueue<JobData> queue;
	@Autowired
	IOrderRepository orderRepo;
	
	@Autowired
	public IJobRepository jobRepo;
	
	@Autowired
	public ReportGeneration report;
	
	public void setQueue(BlockingQueue<JobData> queue) {
		this.queue = queue;
		
	}
	 
	
	 
	    public void doJobProcess() {
	        try {
	 
	            while (true) {
	            	JobData jobData = queue.take();
	 
	                if (jobData==null) {
	                    break;
	                }
	 
	                if((jobRepo.findStatusById(jobData.getJob_id()).equals(CommonUtility.JOB_STATUS_NEW))){
	               
	                	doProcess(jobData);
	 
	                		                	
	                }
	            }
	 
	        } catch (InterruptedException ie) {
	            ie.printStackTrace();
	        }
	    }

	    
	   private void doProcess(JobData job){
		   //order processing
		   //get the orders for the given date.
		   List<OrderData> ordersInfo = orderRepo.findByDate(job.getOrdr_date());
		   
		   //once order is done do the reporting
		   try {
			report.doReportGeneration(ordersInfo, job.getJob_id(), job.getFile_type());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   job.setJob_status(CommonUtility.JOB_STATUS_DONE);
		   
		   jobRepo.save(job);
		   
	   }

}
