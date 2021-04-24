package com.hackathon.consumer.integration.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.consumer.integration.model.OrderData;

public class ReportGenerationJSON {
	
	BufferedWriter fileWriter = null;
	
	public void doReportProcess(List<OrderData> orderData,Long jobId,File jsonFile){
		try {
			fileWriter = new BufferedWriter(new FileWriter(jsonFile));
		
		ObjectMapper mapper =new ObjectMapper();
		orderData.stream().forEach((x)->{
			try {
				fileWriter.write(mapper.writeValueAsString(x));
				fileWriter.newLine();
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		fileWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
