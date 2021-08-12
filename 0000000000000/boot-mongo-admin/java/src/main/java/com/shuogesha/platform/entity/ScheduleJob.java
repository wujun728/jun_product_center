package com.shuogesha.platform.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 调度任务
 */
@Document(collection="scheduleJob")
public class ScheduleJob implements Serializable {
	
	public static String ON="1";
	public static String OFF="0";
	
	@Id 
	private String id;
	
	private String name;
	private String className;
	private String groupName;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务描述 */
    private String description;
    /** 任务状态 */
    private String status="0";//0 未启用 1，启用中
    private String dateline;

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

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
