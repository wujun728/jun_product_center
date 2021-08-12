package com.shuogesha.mq.message;

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
import com.shuogesha.mq.MessageQueueConfig;
import com.shuogesha.mq.SystemLogQueueConfig;
import com.shuogesha.mq.order.OrderDelayedReceiver;

/**
 * 日志记录监听
 * 
 * @author zhaohaiyuan
 *
 */
@Component
public class MessageSender {
	private static Logger log = LoggerFactory.getLogger(MessageSender.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendLog(com.shuogesha.cms.entity.Message bean) {
		log.info("===============发送系统消息队列消息====================");
        log.info("发送时间:{},发送内容:{}", LocalDateTime.now(),""); 
		Message message = MessageBuilder.withBody(JSON.toJSONString(bean).getBytes())
				.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
		message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME,
				MessageProperties.CONTENT_TYPE_JSON); 
		rabbitTemplate.convertAndSend(MessageQueueConfig.SYSTEM_MESSAGE_EXCHANGE_NAME, MessageQueueConfig.SYSTEM_MESSAGE_ROUTE_KEY, message);

	}

}
