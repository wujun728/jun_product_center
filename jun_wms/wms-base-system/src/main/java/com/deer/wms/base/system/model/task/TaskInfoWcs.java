package com.deer.wms.base.system.model.task;


/**
 * 任务表 task_info
 * 
 * @author guo
 * @date 2019-07-03
 */


public class TaskInfoWcs {

	private String taskNo;
	private String fromStation;
	private String toStation;
	private String type;
	private String state;
	private String createTime;
	private String level;
	private String barcode;
	private Integer number;



	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public TaskInfoWcs() {
	}

	public TaskInfoWcs(String taskNo, String fromStation, String toStation, String type, String state, String createTime, String level, String barcode, Integer number) {
		this.taskNo = taskNo;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.type = type;
		this.state = state;
		this.createTime = createTime;
		this.level = level;
		this.barcode = barcode;
		this.number = number;
	}
}
