package com.hackathon.consumer.integration.repository.token;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackathon.consumer.integration.domain.token.TokenDetail;

public interface TokenDetailRepository extends JpaRepository<TokenDetail, Long>{

	TokenDetail findByTokenAndIsExpiredIsFalseAndIsActiveIsTrue(String token);

	@Transactional()
	@Modifying
	@Query("UPDATE TokenDetail td SET td.isExpired = true , td.isActive = false ,  td.modifiedDate = SYSDATE()  WHERE td.token =:token  AND  td.isActive = true  ") //AND isActive = false ")
	public void updateTokenAsExpired(@Param("token") String tokenString);
}
