package com.hackathon.consumer.integration.controller;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.consumer.integration.helper.common_logic.CommonUtility;
import com.hackathon.consumer.integration.model.JobData;
import com.hackathon.consumer.integration.service.DownloadProcess;
import com.hackathon.consumer.integration.service.JobOrderCreation;
import com.hackathon.consumer.integration.service.JobProcess;
import com.hackathon.consumer.integration.service.JobProducer;

@RestController
public class ConsumerIntegrationController {
	
	@Autowired
	JobOrderCreation jobOrder;
	
	@Autowired
	JobData jobModel;
	
	@Autowired
	DownloadProcess downloadZip;
	
	@Autowired
	JobProducer producer;
	
	@Autowired
	JobProcess jobProcess;
	
		
	@RequestMapping(value="/getOrder")
	public String getOrderDetails(@RequestParam String userName,@RequestParam String orderDate,@RequestParam String fileType ) {
			
			String jobDate = LocalDateTime.now().toString();
			
			jobModel.setCreated_date(jobDate);
			jobModel.setFile_type(fileType);
			jobModel.setIs_active(true);
			jobModel.setJob_status(CommonUtility.JOB_STATUS_NEW);
			jobModel.setModified_date(jobDate);
			jobModel.setOrdr_date(orderDate);
			jobModel.setUser_name(userName);

			
			
			return "Job Id"+ jobOrder.jobCreation(jobModel) + "Successfully created.";
		
	}
	
	@Scheduled(fixedRate = 500000)
	@RequestMapping(value="doProcess")
	public String getJobStatus( ) {
		BlockingQueue<JobData> queue = new LinkedBlockingDeque<>();
		
		producer.setQueue(queue);
		producer.doJobProcess();
		
		jobProcess.setQueue(queue);
		jobProcess.doJobProcess();
		return "Job is in progress....";
		
		
	}
	
	@RequestMapping(value="doDownload", produces="application/zip")
	public byte[] doReportDownload(HttpServletResponse response,@RequestParam String jobId ) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"OrderReport_"+jobId+".zip\"");
		return downloadZip.jobDownload(jobId);
		
		
	}

}
