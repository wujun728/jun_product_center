package com.shuogesha.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 

/**
 * 管理员后台操作系统消息队列存储
 * @author zhaohaiyuan
 *
 */
 
@Configuration
public class SystemLogQueueConfig {
 	public final static String USER_LOG_QUEUE_NAME="user.log";
	public final static String USER_LOG_EXCHANGE_NAME ="user.log.exchange";
	public final static String USER_LOG_ROUTE_KEY ="user.log.routing.key";
 

	@Bean
	public Queue userLogQueue() {
		Queue queue = new Queue(USER_LOG_QUEUE_NAME, true);
		return queue;
	}
	@Bean
	public DirectExchange userLogExchange() {
 		return new DirectExchange(USER_LOG_EXCHANGE_NAME,true,false);
	}
	/**
	 * 绑定交换机队列
	 * @return
	 */
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(userLogQueue()).to(userLogExchange()).with(USER_LOG_ROUTE_KEY);
	}
}
