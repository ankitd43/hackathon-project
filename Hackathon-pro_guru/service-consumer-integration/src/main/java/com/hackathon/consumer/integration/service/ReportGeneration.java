package com.hackathon.consumer.integration.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.consumer.integration.model.OrderData;

public class ReportGeneration {
	
	@Autowired
	ReportGenerationCSV csvFile;
	
	@Autowired
	ReportGenerationJSON jsonFile;
	
	public void doReportGeneration(List<OrderData> orderData,Long jobId,String fileFormat) throws IOException{
		
		String fileGen = "C://Users//hackathon//Documents//Hackathon//";
		File f = new File(fileGen);
		f.createNewFile();
		
		
		String filePath = jobId.toString();
		if(fileFormat.equalsIgnoreCase("CSV")) {
			filePath += ".csv";
			f = new File(fileGen+filePath);
			f.createNewFile();
			csvFile.doReportProcess(orderData, jobId, f);
		}else if(fileFormat.equalsIgnoreCase("JSON")) {
			filePath += ".json";
			jsonFile.doReportProcess(orderData, jobId, f);
		}else {
			System.out.println(fileFormat);
		}
		
	}
}
