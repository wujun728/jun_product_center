package org.myframework.support.taskclient;

import java.io.Serializable;


public class TaskInfo    implements Serializable  {
	
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	
	public static final String JOBTYPE_OBJECT = "0";
	public static final String JOBTYPE_HTTP = "1";
	
	
	public static final String JOB_MSG_TYPE_P2P  = "p2p";
	public static final String JOB_MSG_TYPE_PUB_SUB = "PubSub";
	
	private String jobId;
	
	private String jobMsgType ;
	
	/**
	 * 任务是否有状态
	 */
	private String isConcurrent;
	
	/**
	 * 任务名称
	 */
	private String jobName;
	
	/**
	 * default :  Scheduler.DEFAULT_GROUP
	 * 任务分组
	 */
	private String jobGroupName  ;
	
	/**
	 * default : STATUS_NOT_RUNNING
	 * 任务状态 是否启动任务
	 */
	private String jobStatus ;
	
	/**
	 * cron表达式
	 */
	private String cronExpression;
	
	/**
	 * 描述
	 */
	private String description;
	
	
	/**
	 * spring bean
	 */
	private String springBeanId;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String jobClassName;
	
	/**
	 * 任务调用的方法名 
	 * 
	 * 默认方法名execute @see org.quartz.Job
	 */
	private String methodName;
	
	
	/**
	 * JSON字符串 default ： {}
	 */
	private String jobData  ;
	
	private String triggerName;
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

 

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

 

	public String getJobGroupName() {
		return   this.jobGroupName ;
	}

 
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getJobClassName() {
		return this.jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public   String  getJobData() {
		return this.jobData;
	}

	public void setJobData(  String  jobData) {
		this.jobData = jobData;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getBeanClass() {
		return jobClassName;
	}

	public void setBeanClass(String beanClass) {
		this.jobClassName = beanClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getSpringBeanId() {
		return springBeanId;
	}

	public void setSpringBeanId(String springBeanId) {
		this.springBeanId = springBeanId;
	}

	public String getJobMsgType() {
		return jobMsgType;
	}

	public void setJobMsgType(String jobMsgType) {
		this.jobMsgType = jobMsgType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobInfo [jobId=");
		builder.append(jobId);
		builder.append(", jobStatus=");
		builder.append(jobStatus);
		builder.append(", springBeanId=");
		builder.append(springBeanId);
		builder.append(", jobClassName=");
		builder.append(jobClassName);
		builder.append(", methodName=");
		builder.append(methodName);
		builder.append(", jobName=");
		builder.append(jobName);
		builder.append(", jobGroupName=");
		builder.append(jobGroupName);
		builder.append(", cronExpression=");
		builder.append(cronExpression);
		builder.append(", isConcurrent=");
		builder.append(isConcurrent);
		
		builder.append(", description=");
		builder.append(description);
		builder.append(", jobData=");
		builder.append(jobData);
		builder.append(", triggerName=");
		builder.append(triggerName);
	 
		builder.append("]");
		return builder.toString();
	}
}