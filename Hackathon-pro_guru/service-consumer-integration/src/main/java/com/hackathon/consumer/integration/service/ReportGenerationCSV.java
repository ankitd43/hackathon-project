package com.hackathon.consumer.integration.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import com.hackathon.consumer.integration.model.OrderData;

public class ReportGenerationCSV {
	
	BufferedWriter fileWriter = null;
	
	public void doReportProcess(List<OrderData> orderData,Long jobId,File csvFile){
	try {	
		
		fileWriter = new BufferedWriter(new FileWriter(csvFile));
		
		fileWriter.write("OrderDate,cusomerId,orderstatus,orderfrom");
		orderData.stream().forEach((data) -> {
			try {
				fileWriter.newLine();
				fileWriter.write(data.getOrderDate()+","+data.getCustomerId()+","+data.getOrderStatus()+","+data.getOrderFrom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		fileWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
