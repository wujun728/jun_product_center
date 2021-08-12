package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 调度任务
 * @author zhaohy
 *
 */
public class ScheduleJob implements Serializable { 
	
	private static final long serialVersionUID = -1133337404351090458L;

	private Long id;
	private String name;
	private String className;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务描述 */
    private String description;
    /** 任务状态 */
    private String status="0";//0 未启用 1，启用中
    private Date dateline;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateline() {
		return dateline;
	}
	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}
	 
}
