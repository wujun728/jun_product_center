package com.deer.wms.base.system.model.bill;

import com.deer.wms.common.core.service.QueryCriteria;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */

public class BillInDetailCriteria extends QueryCriteria
{
	private String billNo;
	private Integer itemId;
	private Integer billInState;
	private String batch;

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getBillInState() {
		return billInState;
	}

	public void setBillInState(Integer billInState) {
		this.billInState = billInState;
	}

	public BillInDetailCriteria() {
	}

	public BillInDetailCriteria(String billNo, Integer itemId,Integer billInState) {
		this.billNo = billNo;
		this.itemId = itemId;
		this.billInState = billInState;
	}

	public BillInDetailCriteria(String billNo, Integer itemId) {
		this.billNo = billNo;
		this.itemId = itemId;
	}

	public BillInDetailCriteria(Integer billInState) {
		this.billInState = billInState;
	}
}
