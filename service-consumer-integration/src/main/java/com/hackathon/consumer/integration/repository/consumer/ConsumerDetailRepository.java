package com.hackathon.consumer.integration.repository.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.consumer.integration.domain.consumer.ConsumerDetail;

public interface ConsumerDetailRepository extends JpaRepository<ConsumerDetail, Long> {

	ConsumerDetail findByUsernameAndPasswordAndIsActiveIsTrue(String username, String password);

}
