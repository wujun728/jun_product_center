package com.shuogesha.mq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 

/**
 * 延迟命令队列执行，主要用于订单超时未付款的情况
 * @author zhaohaiyuan
 *
 */
 
@Configuration
public class OrderQueueConfig {
    //=============================== 订单参数数据 ================================ 
	//订单取消
	public final static String ORDER_CANCEL_QUEUE_NAME="delayed.order.cancel";
	public final static String ORDER_CANCEL_EXCHANGE_NAME ="delayed.order.cancel.exchange";
	public final static String ORDER_CANCEL_ROUTE_KEY ="delayed.order.cancel";
 
	/**
	 * 配置默认的交换机
	 * @return
	 */
	@Bean
	public CustomExchange delayExchange() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-delayed-type", "direct");
		//使用插件必须是x-delayed-message
		return new CustomExchange(ORDER_CANCEL_EXCHANGE_NAME, "x-delayed-message", true, false, args);
	}

	@Bean
	public Queue queue() {
		Queue queue = new Queue(ORDER_CANCEL_QUEUE_NAME, true);
		return queue;
	} 
	/**
	 * 绑定交换机队列
	 * @return
	 */
	@Bean
	public Binding binding1() {
		return BindingBuilder.bind(queue()).to(delayExchange()).with(ORDER_CANCEL_ROUTE_KEY).noargs();
	}
}
