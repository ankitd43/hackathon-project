package com.hackathon.consumer.integration.service.auth;

import com.hackathon.consumer.integration.helper.exception.ConsumerIntegrationException;
import com.hackathon.consumer.integration.model.ConsumerDetailRequest;
import com.hackathon.consumer.integration.model.ConsumerDetailResponse;

public interface ConsumerAuthService {

	ConsumerDetailResponse login(ConsumerDetailRequest consumerDetailRequest) throws ConsumerIntegrationException;

	boolean verifyToken(String token);


}
