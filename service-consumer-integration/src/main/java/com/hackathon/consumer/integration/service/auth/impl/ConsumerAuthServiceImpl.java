package com.hackathon.consumer.integration.service.auth.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.consumer.integration.domain.consumer.ConsumerDetail;
import com.hackathon.consumer.integration.helper.exception.ConsumerIntegrationException;
import com.hackathon.consumer.integration.model.ConsumerDetailRequest;
import com.hackathon.consumer.integration.model.ConsumerDetailResponse;
import com.hackathon.consumer.integration.repository.consumer.ConsumerDetailRepository;
import com.hackathon.consumer.integration.service.auth.ConsumerAuthService;
import com.hackathon.consumer.integration.service.token.TokenDetailService;

@Service
public class ConsumerAuthServiceImpl implements ConsumerAuthService{

	private static final Logger logger = LoggerFactory.getLogger(ConsumerAuthServiceImpl.class);

	@Autowired
	private ConsumerDetailRepository consumerDetailRepository;
	
	@Autowired
	private TokenDetailService tokenDetailService;
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ConsumerDetailResponse login(ConsumerDetailRequest consumerDetailRequest) throws ConsumerIntegrationException {
		logger.info("Enter into ConsumerAuthService login()");
		ConsumerDetailResponse consumerDetailResponse = new ConsumerDetailResponse();
		ConsumerDetail consumerDetail = consumerDetailRepository.findByUsernameAndPasswordAndIsActiveIsTrue(consumerDetailRequest.getUsername(), consumerDetailRequest.getPassword());
		if(consumerDetail != null ) {
//			if(consumerDetail.getModifiedDate() != null) {
//				Long dateDifference = CommonUtility.getDateDifference(consumerDetail.getModifiedDate(), new Date(),  DateDiffType.HOUR);
//				if( consumerDetail.getLoginCounter() < 6 && dateDifference > 60l ) {
//					consumerDetailResponse.setMessage("You excces max longin");
//				}
//			}
			String token = tokenDetailService.generateToken(consumerDetailRequest);
			consumerDetailResponse.setToken(token);
			consumerDetail.setModifiedDate(new Date());
			consumerDetailRepository.save(consumerDetail);
		}else {
			consumerDetailResponse.setMessage("Invalid credential");
		}
		return consumerDetailResponse;
	}
	
	@Override
	public boolean verifyToken(String token ) {
		logger.info("Enter into ConsumerAuthService login()");
		return tokenDetailService.checkTokenExpiration(token);
		
	}
	
}
