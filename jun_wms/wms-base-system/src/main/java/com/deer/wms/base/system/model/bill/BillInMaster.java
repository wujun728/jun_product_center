package com.deer.wms.base.system.model.bill;

import javax.persistence.*;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */

@Table(name = "bill_in_master")
public class BillInMaster
{

	
	/** ID */
	@Id
	@Column(name = "bill_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;
	/** 单据号 */
	@Column(name = "bill_no")
	private String billNo;
	/** 合同号 */
	@Column(name = "contract_no")
	private String contractNo;
	/** 创建人员 */
	@Column(name = "create_user_id")
	private Integer createUserId;
	@Column(name = "create_user_name")
	private String createUserName;
	/** 状态 */
	@Column(name = "state")
	private Integer state;
	/** 备注 */
	@Column(name = "memo")
	private String memo;

	private String createTime;

	/** 供应商ID */
	@Column(name = "supplier_code")
	private String supplierCode;

	/**
	 * 仓库ID
	 */
	private Integer wareId;

	/**
	 *	入库类型
	 *	1-订单入库 2-退料入库
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public void setBillId(Integer billId)
	{
		this.billId = billId;
	}

	public Integer getBillId() 
	{
		return billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getWareId() {
		return wareId;
	}

	public void setWareId(Integer wareId) {
		this.wareId = wareId;
	}

	public void setContractNo(String contractNo)
	{
		this.contractNo = contractNo;
	}

	public String getContractNo() 
	{
		return contractNo;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public void setState(Integer state)
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public BillInMaster() {
	}

	public BillInMaster(String billNo, Integer createUserId, String createUserName, Integer state, String memo, String createTime, String supplierCode, Integer wareId, Integer type) {
		this.billNo = billNo;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.state = state;
		this.memo = memo;
		this.createTime = createTime;
		this.supplierCode = supplierCode;
		this.wareId = wareId;
		this.type = type;
	}
}
