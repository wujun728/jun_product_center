package com.deer.wms.base.system.model.task;


import com.deer.wms.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 任务表 task_info
 * 
 * @author guo
 * @date 2019-06-03
 */


@Table(name = "task_info")
public class TaskInfo
{

	/** ID */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** GUID */
	private String taskId;
	/** 起始位置 */
	private String startPosition;
	/** 结束位置 */
	private String endPosition;
	/**
	 * 任务类型:
     *
	 * 1 - 入库任务(出空框)
	 *
	 * 2 - 入库任务(装货完毕后入库；或者入空框)
	 *
	 * 3 - 出库任务(将合适的托盘移到点数机内）
	 *
	 * 4 - 出库任务（点数）
	 *
	 * 5 - 出库任务（将托盘从点数机放回到空货位上）
	 *
	 * 6 - 出库任务（将有合适货物的载具调度到AGV）
	 *
	 */
	private Integer type;
	/**
	 * 状态
	 * 		0-已下发
	 * 		1-执行中
	 * 		2-报错
	 * 		3-已完成
	 */
	private Integer state;
	/** 点数数量 */
	private Integer quantity;
	/** 完成数量 */
	private Integer completeQuantity;
	/** 条码信息（贴标使用） */
	private String barCode;
	/** 托盘/料箱编码 */
	private String boxCode;
	/** 外键 */
	private Integer billInDetailId;
	/** 是否置顶  0-否 1-是 */
	@Column(name = "istop")
	private String isTop;
	/** 任务开始时间 */
	@Column(name="card_no")
	private String cardNo;
	/** 任务开始时间 */
	@Column(name = "task_start_time")
	private String taskStartTime;
	/** 任务结束时间 * */
	@Column(name = "task_end_time")
	private String taskEndTime;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	private Integer billOutDetailId;

	public Integer getBillOutDetailId() {
		return billOutDetailId;
	}

	public void setBillOutDetailId(Integer billOutDetailId) {
		this.billOutDetailId = billOutDetailId;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

	public String getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(String endPosition) {
		this.endPosition = endPosition;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCompleteQuantity() {
		return completeQuantity;
	}

	public void setCompleteQuantity(Integer completeQuantity) {
		this.completeQuantity = completeQuantity;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public Integer getBillInDetailId() {
		return billInDetailId;
	}

	public void setBillInDetailId(Integer billInDetailId) {
		this.billInDetailId = billInDetailId;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("startPosition", getStartPosition())
            .append("endPosition", getEndPosition())
            .append("type", getType())
            .append("state", getState())
            .append("quantity", getQuantity())
            .append("completeQuantity", getCompleteQuantity())
            .append("barCode", getBarCode())
            .append("boxNo", getBoxCode())
			.append("cardNo",getCardNo())
			.append("taskStartTime",getTaskStartTime())
			.append("taskEndTime",getTaskEndTime())
            .toString();
    }

	public TaskInfo() {

	}

	public TaskInfo(Integer id,String taskId, String startPosition, String endPosition, Integer type, Integer state, Integer quantity,String boxCode) {
		this.id = id;
		this.taskId = taskId;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.type = type;
		this.state = state;
		this.quantity = quantity;
		this.boxCode = boxCode;
	}

	public TaskInfo(String taskId, String startPosition, String endPosition, Integer type, Integer state, Integer quantity, String boxCode, String isTop, Integer billOutDetailId) {
		this.taskId = taskId;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.type = type;
		this.state = state;
		this.quantity = quantity;
		this.boxCode = boxCode;
		this.isTop = isTop;
		this.billOutDetailId = billOutDetailId;
	}
}
