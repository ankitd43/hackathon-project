package com.hackathon.consumer.integration.service.token;

import com.hackathon.consumer.integration.helper.exception.ConsumerIntegrationException;
import com.hackathon.consumer.integration.model.ConsumerDetailRequest;

public interface TokenDetailService {

	String generateToken(ConsumerDetailRequest consumerDetailRequest) throws ConsumerIntegrationException;

	boolean checkTokenExpiration(String token);

}
