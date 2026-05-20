package com.ruoyi.flowable.domain.vo;

import lombok.Data;

/**
 * 任务详情
 *
 * @author wocurr.com
 */
@Data
public class TaskInfoVo {
	
	String taskId;
	
	String processInstanceId;
	
	String executionId;
	
	String businessKey;
	
	String processName;
	
	String taskName;
	
	String starter;
	
	String assignee;
	
	String startTime;

	String endTime;
	
	String createTime;

	String formKey;

	String comment;

	Integer pageSize;

	Integer pageNum;
}
