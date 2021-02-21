package com.deer.wms.base.system.model.bill;

import com.deer.wms.common.core.service.QueryCriteria;

import javax.persistence.*;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */

public class BillInMasterCriteria extends QueryCriteria
{
	/** 订单号*/
	private String billNo;
	private String itemCode;
	private String supplierCode;
	private Integer quantity;
	private String operatorCard;
	private String productionDate;
	private String endDate;
	private String batch;
	private String boxCode;
	private String taskStartTime;
	private String taskEndTime;
	private String barCode;
	private String lastBarCode;
	/** 扫码验证时的总数量 */
	private Integer quantitys;
	private Integer autoverifyPermission;
	private Integer itemId;
	private String createUserName;

	public String getOperatorCard() {
		return operatorCard;
	}

	public void setOperatorCard(String operatorCard) {
		this.operatorCard = operatorCard;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getLastBarCode() {
		return lastBarCode;
	}

	public void setLastBarCode(String lastBarCode) {
		this.lastBarCode = lastBarCode;
	}

	public Integer getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Integer quantitys) {
		this.quantitys = quantitys;
	}

	public Integer getAutoverifyPermission() {
		return autoverifyPermission;
	}

	public void setAutoverifyPermission(Integer autoverifyPermission) {
		this.autoverifyPermission = autoverifyPermission;
	}

	public BillInMasterCriteria() {
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
