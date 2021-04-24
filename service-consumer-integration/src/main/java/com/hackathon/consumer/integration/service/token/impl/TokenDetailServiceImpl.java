package com.hackathon.consumer.integration.service.token.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.consumer.integration.domain.token.TokenDetail;
import com.hackathon.consumer.integration.helper.common_logic.CommonUtility;
import com.hackathon.consumer.integration.helper.common_logic.CommonUtility.DateDiffType;
import com.hackathon.consumer.integration.helper.exception.ConsumerIntegrationException;
import com.hackathon.consumer.integration.model.ConsumerDetailRequest;
import com.hackathon.consumer.integration.repository.token.TokenDetailRepository;
import com.hackathon.consumer.integration.service.token.TokenDetailService;

@Service
public class TokenDetailServiceImpl implements TokenDetailService{

	private static final Logger logger = LoggerFactory.getLogger(TokenDetailServiceImpl.class);
	
	@Autowired
	private TokenDetailRepository tokenDetailRepository;

	@Transactional
	@Override
	public String generateToken(ConsumerDetailRequest consumerDetailRequest) throws ConsumerIntegrationException{
		logger	.info("Enter in getToken() ");
		
		String token = null;
			logger.info("Start Generating token ");
			try {
			token = CommonUtility.generateToken(consumerDetailRequest.getPassword(), consumerDetailRequest.getUsername() );
			}catch (UnsupportedEncodingException | GeneralSecurityException  e) {
				throw new ConsumerIntegrationException("error while generating token", e);
			}
			logger.info("------------------End Generating token ---------------------");
			if (!CommonUtility.isObjectNullOrEmpty(token)) {
				logger.info("Saving... Generated token");
				saveToken(token);
				logger.info("-------------------- Successfully saved Generated token --------------------");
			}
		logger.info("Exist from  getToken()");
		return token;
		
	}
	
	@Transactional
	@Override
	public boolean checkTokenExpiration(String token) {
		logger.info("Enter in checkTokenExpiration() ");
		
		TokenDetail tokenDetail = tokenDetailRepository.findByTokenAndIsExpiredIsFalseAndIsActiveIsTrue(token);
		if (tokenDetail == null) {
			logger.info("No Token Details FOund For");
			return false;
		}

		Integer tokenExpireTime = 5; //Integer.parseInt(tokenTime);
		Long dateDifference = CommonUtility.getDateDifference(tokenDetail.getCreatedDate(), new Date(),  DateDiffType.MINUTES);
		logger.info("Date Diff in Minutes [{}]",dateDifference);
		logger.info("Token Expiry Time [{}]",tokenExpireTime);
		
		if (dateDifference > tokenExpireTime) {
			logger.info("-------------------token is expired . Start saving... checkTokenExpiration() ------------------------");
			tokenDetailRepository.updateTokenAsExpired(token);
			logger.info("-------------------End Successfully  update expired token... checkTokenExpiration()  ------------------------");
			return false;
		}
		
		logger.info(" Exist from checkTokenExpiration()");
		return true;
	}
	
	private void saveToken(String token) {
		logger.info("Enter in saveToken()");
		TokenDetail tokenDetail = new TokenDetail();
		tokenDetail.setToken(token);
		tokenDetail.setCreatedDate(new Date());
		tokenDetail.setIsExpired(false);
		tokenDetail.setIsActive(true);
		tokenDetailRepository.save(tokenDetail);
		logger.info("Exist from saveToken()");

	}
}
