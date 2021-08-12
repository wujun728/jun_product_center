package com.shuogesha.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 系统消息
 * @author zhaohaiyuan
 *
 */
@Configuration
public class MessageQueueConfig {
	public final static String SYSTEM_MESSAGE_QUEUE_NAME="system.message";
	public final static String SYSTEM_MESSAGE_EXCHANGE_NAME ="system.message.exchange";
 	public final static String SYSTEM_MESSAGE_ROUTE_KEY ="system.message.routing.key";//chat消息
 

	@Bean
	public Queue messageQueue() {
		Queue queue = new Queue(SYSTEM_MESSAGE_QUEUE_NAME, true);
		return queue;
	}
	@Bean
	public TopicExchange messageExchange() {
 		return new TopicExchange(SYSTEM_MESSAGE_EXCHANGE_NAME,true,false);
	}
	@Bean
	public Binding binding1() {
		return BindingBuilder.bind(messageQueue()).to(messageExchange()).with(SYSTEM_MESSAGE_QUEUE_NAME);
	}
	/**
	 * 绑定交换机队列
	 * @return
	 */
	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(messageQueue()).to(messageExchange()).with(SYSTEM_MESSAGE_ROUTE_KEY);
	}
}
