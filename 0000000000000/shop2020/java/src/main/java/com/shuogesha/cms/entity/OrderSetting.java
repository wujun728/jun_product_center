package com.shuogesha.cms.entity;

import java.io.Serializable;

public class OrderSetting implements Serializable {
	
		private Long id;
	private String name;
	private Integer orderTimeout;
	private Integer payTimeout;
	private Integer shippingTimeout;
	private Integer commentTimeout;
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}

	public Integer getOrderTimeout() {
		return orderTimeout;
	}

	public void setOrderTimeout(Integer orderTimeout) {
		this.orderTimeout = orderTimeout;
	}

	public Integer getPayTimeout() {
		return payTimeout;
	}

	public void setPayTimeout(Integer payTimeout) {
		this.payTimeout = payTimeout;
	}

	public Integer getShippingTimeout() {
		return shippingTimeout;
	}

	public void setShippingTimeout(Integer shippingTimeout) {
		this.shippingTimeout = shippingTimeout;
	}

	public Integer getCommentTimeout() {
		return commentTimeout;
	}

	public void setCommentTimeout(Integer commentTimeout) {
		this.commentTimeout = commentTimeout;
	}
	 
	
	
}
