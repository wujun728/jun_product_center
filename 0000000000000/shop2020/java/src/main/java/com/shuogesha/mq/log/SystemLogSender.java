package com.shuogesha.mq.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.shuogesha.mq.SystemLogQueueConfig;
import com.shuogesha.mq.order.OrderDelayedReceiver;
import com.shuogesha.platform.entity.SystemLog;

/**
 * 日志记录监听
 * 
 * @author zhaohaiyuan
 *
 */
@Component
public class SystemLogSender {
	private static Logger log = LoggerFactory.getLogger(OrderDelayedReceiver.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendLog(SystemLog bean) {
		log.info("===============发送系统日志队列消息====================");
        log.info("发送时间:{},发送内容:{}", LocalDateTime.now(),""); 
		Message message = MessageBuilder.withBody(JSON.toJSONString(bean).getBytes())
				.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
		message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME,
				MessageProperties.CONTENT_TYPE_JSON); 
		rabbitTemplate.convertAndSend(SystemLogQueueConfig.USER_LOG_EXCHANGE_NAME, SystemLogQueueConfig.USER_LOG_ROUTE_KEY, message);

	} 

}
