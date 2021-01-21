package org.myframework.quartz.scheduler.job;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;

public class MsgSenderImpl implements MsgSender {
	private RedisTemplate<String, Object> redisTemplate = null;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*
	 * 
	 * 左进右出
	 */
	@Override
	public void sendMessageToQueue(String queue, Serializable message) {
		redisTemplate.opsForList().leftPush(queue, message);
	}

	@Override
	public void sendMessageToTopic(String topic, Serializable message) {
		redisTemplate.convertAndSend(topic, message);
	}

}
