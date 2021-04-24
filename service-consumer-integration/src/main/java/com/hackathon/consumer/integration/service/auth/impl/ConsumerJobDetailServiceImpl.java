package com.hackathon.consumer.integration.service.auth.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.consumer.integration.domain.consumer.ConsumerJobDetail;
import com.hackathon.consumer.integration.model.ConsumerDetailResponse;
import com.hackathon.consumer.integration.repository.consumer.ConsumerJobDetailRepository;
import com.hackathon.consumer.integration.service.auth.ConsumerJobDetailService;

@Service
public class ConsumerJobDetailServiceImpl implements ConsumerJobDetailService {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerAuthServiceImpl.class);

	@Autowired
	private ConsumerJobDetailRepository consumerJobDetailRepository;

	@Transactional
	@Override
	public ConsumerDetailResponse checkConsumerStatus(Long jobId) {
		logger.info("Enter into checkConsumerStatus() jpbId [{}]", jobId);
		ConsumerDetailResponse consumerDetailResponse = new ConsumerDetailResponse();
		consumerDetailResponse.setJobId(jobId);
		ConsumerJobDetail consumerJobDetail =  consumerJobDetailRepository.findByIdAndIsActiveIsTrue(jobId);
		if(consumerJobDetail == null) {
			consumerDetailResponse.setMessage("Invalid Job Id");
		}else {
			consumerDetailResponse.setJobStatus(consumerJobDetail.getJobStatus());
			consumerDetailResponse.setMessage("Success");
		}
		logger.info("Exit from checkConsumerStatus()");
		return consumerDetailResponse;
	}
}
