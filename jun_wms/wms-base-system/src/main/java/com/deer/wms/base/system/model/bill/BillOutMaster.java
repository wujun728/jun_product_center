package com.deer.wms.base.system.model.bill;

import org.springframework.stereotype.Controller;

import javax.persistence.*;


/**
 * 出库单表 bill_out_master
 * 
 * @author cai
 * @date 2019-07-15
 */

@Table(name = "bill_out_master")
public class BillOutMaster
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

	/** 创建时间 */
	@Column(name = "create_time")
	private String createTime;

	/** 创建人员 */
	@Column(name = "create_user_name")
	private String createUserName;
	/** 领用部门账户别名 */
	@Column(name = "account_alias_id")
	private Integer accountAliasId;

	@Column(name = "create_user_id")
	private Integer createUserId;

	/** 状态
	 * 	0-等待任务下发
	 * 	1-任务已下发
	 * 	2-任务已完成
	 */
	@Column(name = "state")
	private Integer state;
	/** 备注 */
	@Column(name = "memo")
	private String memo;

	/** 仓库表ID外键 */
	@Column(name = "ware_id")
	private Integer wareId;

	/**
	 * 出库类型
	 * 1-Mes工单出库
	 * 2-退货出库
	 * 3-报废出库
	 * 4-非工单出库
	 * 5-异常出库
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getAccountAliasId() {
		return accountAliasId;
	}

	public void setAccountAliasId(Integer accountAliasId) {
		this.accountAliasId = accountAliasId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getWareId() {
		return wareId;
	}

	public void setWareId(Integer wareId) {
		this.wareId = wareId;
	}

	public BillOutMaster() {
	}

	public BillOutMaster(Integer billId, String billNo, String contractNo, String createTime, String createUserName, Integer createUserId, Integer state, String memo, Integer wareId, Integer type) {
		this.billId = billId;
		this.billNo = billNo;
		this.contractNo = contractNo;
		this.createTime = createTime;
		this.createUserName = createUserName;
		this.createUserId = createUserId;
		this.state = state;
		this.memo = memo;
		this.wareId = wareId;
		this.type = type;
	}
}
