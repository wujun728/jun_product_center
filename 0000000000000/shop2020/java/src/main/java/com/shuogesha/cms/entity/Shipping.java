package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 快递单
 * @author zhaohaiyuan
 *
 */
public class Shipping implements Serializable {
	
		private Long id; 	private String name; 	private Date dateline; 	private Long orderId; 	private String express; 	private String type; 	private String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateline() {
		return dateline;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	} 

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
 
	
}
