package com.deer.wms.base.system.model.bill;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 出库单详情表 bill_in_detail
 * 
 * @author cai
 * @date 2019-07-15
 */
@Table(name = "bill_out_detail")
public class BillOutDetail
{

	
	/** ID */
	@Id
	@Column(name = "bill_out_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billOutDetailId;
	/** 出库单ID */
	@Column(name = "bill_id")
	private Integer billId;
	/** 物料编码 */
	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "quantity")
	private Integer quantity;
	@Column(name="task_id")
	private String taskId;
	@Column(name="finished_code")
	private String finishedCode;
	@Column(name="priority")
	private String priority;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getFinishedCode() {
		return finishedCode;
	}

	public void setFinishedCode(String finishedCode) {
		this.finishedCode = finishedCode;
	}

	public Integer getBillOutDetailId() {
		return billOutDetailId;
	}

	public void setBillOutDetailId(Integer billOutDetailId) {
		this.billOutDetailId = billOutDetailId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("billInDetailId", getBillOutDetailId())
            .append("billId", getBillId())
            .append("itemCode", getItemCode())
            .append("quantity", getQuantity())
				.append("taskId", getTaskId())
				.append("finishedCode", getFinishedCode())
				.append("priority", getPriority())
            .toString();
    }

	public BillOutDetail() {
	}

	public BillOutDetail(Integer billOutDetailId,Integer billId, String itemCode, Integer quantity, String taskId, String finishedCode, String priority) {
		this.billOutDetailId = billOutDetailId;
		this.billId = billId;
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.taskId = taskId;
		this.finishedCode = finishedCode;
		this.priority = priority;
	}
}
