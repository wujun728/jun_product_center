package org.myframework.quartz.scheduler.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.myframework.dao.orm.BaseDomain;
@Entity
@Table(name = "TBL_QRTZ_TASK_INFO")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
public class JobInfo extends BaseDomain<String>  implements Serializable  {
	
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	
	public static final String JOBTYPE_OBJECT = "0";
	public static final String JOBTYPE_HTTP = "1";
	
	
	/**
	 *对应的redis客户端BLPOP
	 */
	public static final String JOB_MSG_TYPE_P2P  = "list";
	
	/**
	 * 对应的redis客户端SUBSCRIBE
	 */
	public static final String JOB_MSG_TYPE_PUB_SUB = "channel";
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "JOB_ID",length=40 )
	private String jobId;
	
	@Column(name = "JOB_MSG_TYPE" )
	private String jobMsgType ;
	
	/**
	 * 任务是否有状态
	 */
	@Column(name = "IS_CONCURRENT" )
	private String isConcurrent;
	
	/**
	 * 任务名称
	 */
	@Column(name = "JOB_NAME" )
	private String jobName;
	
	/**
	 * default :  Scheduler.DEFAULT_GROUP
	 * 任务分组
	 */
	@Column(name = "JOB_GROUP_NAME" )
	private String jobGroupName  ;
	
	/**
	 * default : STATUS_NOT_RUNNING
	 * 任务状态 是否启动任务
	 */
	@Column(name = "JOB_STATUS" )
	private String jobStatus ;
	
	/**
	 * cron表达式
	 */
	@Column(name = "CRON_EXPRESSION" )
	private String cronExpression;
	
	/**
	 * 描述
	 */
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	/**
	 * spring bean
	 */
	@Column(name = "SPRING_BEAN_ID" )
	private String springBeanId;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
//	private String beanClass;
	@Column(name = "JOB_CLASS_NAME" )
	private String jobClassName;
	
	/**
	 * 任务调用的方法名 
	 * 
	 * 默认方法名execute @see org.quartz.Job
	 */
	@Column(name = "METHOD_NAME" )
	private String methodName;
	
	/**
	 * apiurl
	 */
	@Column(name = "API_URL" )
	private String apiUrl;
	
//	private boolean durable;
	
/*	private boolean recoveryRequested;
	private boolean volatility;
	private boolean update;
	private Date scheduledFor;*/
	
	/**
	 * JSON字符串 default ： {}
	 */
	@Column(name = "JOB_DATA" )
	private String jobData  ;
	
	@Column(name = "TRIGGER_NAME" )
	private String triggerName;
	
	@Column(name = "TRIGGER_STATE" )  
	private String triggerState;
	
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

//	public boolean isDurable() {
//		return this.durable;
//	}
//
//	public void setDurable(boolean durable) {
//		this.durable = durable;
//	}

	public String getJobGroupName() {
		return   this.jobGroupName ;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
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

/*	public boolean isRecoveryRequested() {
		return this.recoveryRequested;
	}

	public void setRecoveryRequested(boolean recoveryRequested) {
		this.recoveryRequested = recoveryRequested;
	}

	public boolean isVolatility() {
		return this.volatility;
	}

	public void setVolatility(boolean volatility) {
		this.volatility = volatility;
	}

	public boolean isUpdate() {
		return this.update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Date getScheduledFor() {
		return this.scheduledFor;
	}

	public void setScheduledFor(Date scheduledFor) {
		this.scheduledFor = scheduledFor;
	}
*/
	public   String  getJobData() {
		return this.jobData;
	}

	public void setJobData(  String  jobData) {
		this.jobData = jobData;
	}

	

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	
	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
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
		
		builder.append(", apiUrl=");
		builder.append(apiUrl);
		builder.append(", jobData=");
		builder.append(jobData);
		builder.append(", triggerName=");
		builder.append(triggerName);
		builder.append(", triggerState=");
		builder.append(triggerState);
		builder.append("]");
		return builder.toString();
	}
}