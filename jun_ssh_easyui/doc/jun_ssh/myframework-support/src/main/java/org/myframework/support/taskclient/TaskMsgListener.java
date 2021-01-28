package org.myframework.support.taskclient;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.myframework.commons.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSONObject;

public class TaskMsgListener implements InitializingBean{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TaskMsgListener.class);

	private RedisTemplate<String, String> redisTemplate = null;

	String domainId;

	Executor executor;

	

	/**
	 * 收到消息后的处理方法
	 * @param message
	 */
	public void handleMessage(Serializable message) {
		if (message == null) {
			logger.debug("null");
		} else if (message.getClass().isArray()) {
			logger.debug(Arrays.toString((Object[]) message));
		} else if (message instanceof List<?>) {
			logger.debug("{}",message);
		} else if (message instanceof Map<?, ?>) {
			logger.debug("{}",message);
		} else if (message instanceof String) {
			String msg = (String) message ;
			TaskInfo jobInfo = JSONObject.parseObject(msg,TaskInfo.class);
			logger.debug("   msg from  {}  {} , msginfo :{}" ,domainId ,jobInfo.getJobMsgType(),msg);
			try {
//				executor.execute(new TaskRunShell(jobInfo));
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
//		init();
	}
	
	/**
	 * 定时收取消息队列中的消息
	 * @throws Exception
	 */
	public void init()   {
		logger.info("TaskMsgListener init .......");
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor( );
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				popMsg();
			}

		}, 10, 2, TimeUnit.MILLISECONDS);
	}

//	@Scheduled(fixedDelay=1000)
	public void popMsg() {
 		logger.debug(">>>>>>>>>>>");
		ListOperations<String, String> opsForList = redisTemplate
				.opsForList();
		if (opsForList.size(domainId) > 0) {
			try {
				String msg = opsForList.leftPop(domainId);
				
				handleMessage(msg);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

}
