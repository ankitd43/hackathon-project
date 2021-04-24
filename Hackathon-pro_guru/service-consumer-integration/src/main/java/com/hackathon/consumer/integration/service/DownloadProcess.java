package com.hackathon.consumer.integration.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.consumer.integration.helper.common_logic.CommonUtility;
import com.hackathon.consumer.integration.model.JobData;

public class DownloadProcess {
	
	@Autowired
	IJobRepository jobRepo;
	
public byte[] jobDownload(String jobId) {
		
		String filePath = jobId;
		ByteArrayOutputStream byteArrayOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;
        
		JobData job =  jobRepo.getOne(Long.parseLong(jobId));
		String jobStatus = job.getJob_status();
		String fileExtn = job.getFile_type();
		if(fileExtn.equalsIgnoreCase("CSV")) {
			filePath += ".csv";
		}else if(fileExtn.equalsIgnoreCase("JSON")) {
			filePath += ".json";
		}else {
			filePath += "."+fileExtn;
			System.out.println(fileExtn);
		}
		String newFolder = "C://Users//hackathon//Documents//Hackathon//";
		
		if( jobStatus.equalsIgnoreCase(CommonUtility.JOB_STATUS_DONE)) {
			File f = new File(newFolder+filePath);
			
			
	         byteArrayOutputStream = new ByteArrayOutputStream();
	         bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
	         zipOutputStream = new ZipOutputStream(bufferedOutputStream);

	        
	            try {
					zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
				      fileInputStream = new FileInputStream(f);
				      IOUtils.copy(fileInputStream, zipOutputStream);
					fileInputStream.close();
					zipOutputStream.closeEntry();
					if (zipOutputStream != null) {
			            zipOutputStream.finish();
			            zipOutputStream.flush();
			            IOUtils.closeQuietly(zipOutputStream);
			        }
					IOUtils.closeQuietly(bufferedOutputStream);
			        IOUtils.closeQuietly(byteArrayOutputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      
			
		}
		return byteArrayOutputStream.toByteArray();
	}

}
