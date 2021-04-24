package com.hackathon.consumer.integration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.consumer.integration.model.OrderData;

@Repository
public interface IOrderRepository extends JpaRepository<OrderData, Long>{
	
	@Query("select o from OrderData c where o.orderDate=?1")
	public List<OrderData> findByDate(String date);

}
