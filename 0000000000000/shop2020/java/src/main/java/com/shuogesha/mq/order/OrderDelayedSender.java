package com.shuogesha.mq.order;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shuogesha.cms.entity.OrderSetting;
import com.shuogesha.cms.service.OrderSettingService;
import com.shuogesha.mq.OrderQueueConfig;
/**
 * 订单延迟生产类
 * @author zhaohaiyuan
 *
 */
@Component
public class OrderDelayedSender implements RabbitTemplate.ConfirmCallback {
	private static Logger log = LoggerFactory.getLogger(OrderDelayedSender.class);
	@Autowired
	private  RabbitTemplate rabbitTemplate;
	
	public void cancelOrder(Long id) {
 		OrderSetting orderSetting =orderSettingService.findOne();
		if(orderSetting!=null&&orderSetting.getPayTimeout()>0) { 
			rabbitTemplate.setConfirmCallback(this);
			log.info("===============发送订单超时取消队列消息====================");
	        log.info("发送时间:{},发送内容:{}", LocalDateTime.now(),id); 
 	 		rabbitTemplate.convertAndSend(OrderQueueConfig.ORDER_CANCEL_EXCHANGE_NAME, OrderQueueConfig.ORDER_CANCEL_ROUTE_KEY, id, new MessagePostProcessor() {
	            @Override
	            public Message postProcessMessage(Message message) throws AmqpException {
	            	//1000*60
	                message.getMessageProperties().setHeader("x-delay", orderSetting.getPayTimeout()*60*1000);
 	                message.getMessageProperties().setCorrelationId(id.toString());//唯一表示
	                return message;
	            }
	        }); 
		} 
	}
	
	@Autowired
	private  OrderSettingService orderSettingService;
	
	/**
	 * 发送队列成功以后那么记录缓存的
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
	 	if(correlationData!=null) {
	 		log.info("CallBackConfirm UUID: " + correlationData.getId());
	 	}
        if(ack) {
        	log.info("CallBackConfirm 消息成功！");
        }else {
        	log.info("CallBackConfirm 消息失败！");
        } 
        if(cause!=null) {
        	log.info("CallBackConfirm Cause: " + cause);
        } 
	}
}
