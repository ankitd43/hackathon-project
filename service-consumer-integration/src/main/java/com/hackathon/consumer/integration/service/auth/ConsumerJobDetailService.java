package com.hackathon.consumer.integration.service.auth;

import com.hackathon.consumer.integration.model.ConsumerDetailResponse;

public interface ConsumerJobDetailService {

	ConsumerDetailResponse checkConsumerStatus(Long jobId);

}
