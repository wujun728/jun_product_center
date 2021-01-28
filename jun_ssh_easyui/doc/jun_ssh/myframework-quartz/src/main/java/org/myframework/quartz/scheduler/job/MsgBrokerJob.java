package org.myframework.quartz.scheduler.job;

import org.myframework.quartz.scheduler.entities.JobInfo;
import org.myframework.support.spring.SpringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MsgBrokerJob implements Job{
	
	public static final String DEFAULT_QUEUE ="DEFAULT_QUEUE";
	
	public static final String DEFAULT_TOPIC = "DEFAULT_TOPIC";
	
	public static final String MESSAGE_PRIX_MSGBROKERJOB=  MsgBrokerJob.class.getSimpleName() +":"; 
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobInfo jobInfo = (JobInfo) context.getMergedJobDataMap().get(JobInfo.class.getSimpleName());
		try {
			MsgSender msgSender = SpringUtils.getBean(MsgSender.class);
			//step 1
			String param = JSONObject.toJSONString(jobInfo);
			//step 2
			String domainId = jobInfo.getDomainId();
			String msgType = jobInfo.getJobMsgType();
			if( JobInfo.JOB_MSG_TYPE_PUB_SUB.equals(msgType) ) {
				//发送到主题
				msgSender.sendMessageToTopic(domainId==null ? MESSAGE_PRIX_MSGBROKERJOB+DEFAULT_TOPIC:domainId, param);
			}else {
				//发送到消息队列
				msgSender.sendMessageToQueue(domainId==null ? MESSAGE_PRIX_MSGBROKERJOB+DEFAULT_QUEUE:domainId, param);
			}
		} catch (Exception e) {
			throw new JobExecutionException(e.getCause());
		}
	}
}
