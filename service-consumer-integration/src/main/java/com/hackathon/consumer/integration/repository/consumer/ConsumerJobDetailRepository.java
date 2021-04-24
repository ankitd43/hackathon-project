package com.hackathon.consumer.integration.repository.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.consumer.integration.domain.consumer.ConsumerJobDetail;


public interface ConsumerJobDetailRepository extends JpaRepository<ConsumerJobDetail,Long> {

	ConsumerJobDetail findByIdAndIsActiveIsTrue(Long jobId);

}
