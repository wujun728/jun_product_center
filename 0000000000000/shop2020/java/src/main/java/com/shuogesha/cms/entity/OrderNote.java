package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.platform.entity.User;

public class OrderNote implements Serializable {
	
		private Long id;
	private String name;
	private Date dateline;
	private String content;
	private String remark;
	private Long orderId;
	private User user;
	
	public OrderNote() {
		// TODO Auto-generated constructor stub
	}
	
	
	public OrderNote(String status,String pay,String shippingStatus, String remark, Long orderId, User user) {
		super();
		JSONObject json=new JSONObject();
		json.put("status", status);
		json.put("pay", pay);
		json.put("shippingStatus", shippingStatus);
		this.content=json.toString();
		this.remark = remark;
		this.orderId = orderId;
		this.user = user;
	}



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
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}
	 

	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}

	public Date getDateline() {
		return dateline;
	}
	  
	
}
