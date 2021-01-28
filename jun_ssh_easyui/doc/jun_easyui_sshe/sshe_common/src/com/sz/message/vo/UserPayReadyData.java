package com.sz.message.vo;

import java.util.Date;

public class UserPayReadyData {
	private Integer payContext;
	private Integer payUserID;
	private Integer payType;
	private Integer payNum;
	private Date payTime;
	private Integer payDistillState;
	
	
	public Integer getPayContext() {
		return payContext;
	}
	public void setPayContext(Integer payContext) {
		this.payContext = payContext;
	}
	public Integer getPayUserID() {
		return payUserID;
	}
	public void setPayUserID(Integer payUserID) {
		this.payUserID = payUserID;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayNum() {
		return payNum;
	}
	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayDistillState() {
		return payDistillState;
	}
	public void setPayDistillState(Integer payDistillState) {
		this.payDistillState = payDistillState;
	}
	
	

}
