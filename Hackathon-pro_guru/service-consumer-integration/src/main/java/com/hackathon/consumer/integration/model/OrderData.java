package com.hackathon.consumer.integration.model;

import javax.persistence.Entity;

@Entity
public class OrderData {
	
	private Long oid;
	public OrderData(Long oid, String orderDate, String customerId, String orderStatus, String orderFrom) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.orderFrom = orderFrom;
	}
	@Override
	public String toString() {
		return "JobData [oid=" + oid + ", orderDate=" + orderDate + ", customerId=" + customerId + ", orderStatus="
				+ orderStatus + ", orderFrom=" + orderFrom + "]";
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderFrom() {
		return orderFrom;
	}
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}
	private String orderDate;
	private String customerId;
	private String orderStatus;
	private String orderFrom;

}
