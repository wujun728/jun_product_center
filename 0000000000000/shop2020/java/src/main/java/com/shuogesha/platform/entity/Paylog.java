package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class Paylog implements Serializable {
 
	private static final long serialVersionUID = -1723063777382990001L;
	
	private String id;
	private String name;
	private String status="0";//0，1处理和未处理
	private String dateline;
	private String tradeNo;// 交易号
	private Integer payeerId;
	private String payeerUsername;
	private String tradeStatus;// 交易状态
	private String totalFee;// 交易金额
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public Integer getPayeerId() {
		return payeerId;
	}
	public void setPayeerId(Integer payeerId) {
		this.payeerId = payeerId;
	}
	public String getPayeerUsername() {
		return payeerUsername;
	}
	public void setPayeerUsername(String payeerUsername) {
		this.payeerUsername = payeerUsername;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	 

}
