package com.shuogesha.mq.order;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.shuogesha.cms.entity.Order;
import com.shuogesha.cms.service.OrderService;
import com.shuogesha.mq.OrderQueueConfig;

/**
 * 订单延迟消费类
 * 
 * @author zhaohaiyuan
 *
 */
@Component
 public class OrderDelayedReceiver {
	
	private static Logger log = LoggerFactory.getLogger(OrderDelayedReceiver.class); 
	 
	/**
	 * 订单取消类
	 * @param id
	 * @param message
	 * @param channel
	 * @throws IOException
	 */
	@RabbitListener(queues = OrderQueueConfig.ORDER_CANCEL_QUEUE_NAME)
    public void orderReceiveDealy(Long id, Message message, Channel channel) throws IOException {
        log.info("===============接收订单超时取消队列接收消息====================");
        log.info("接收时间:{},接受内容:{}", LocalDateTime.now(),id); 
        //通知 MQ 消息已被接收,可以ACK(从队列中删除)了
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        try {  
        	Order order=orderService.findById(id);
        	if(Order.ORDER_JIAOYI.equals(order.getStatus())&&Order.PAY_WEIZHIFU.equals(order.getPay())){
        		orderService.cancel(order); 
        	} 
        } catch (Exception e) {
            log.error("============消费失败,尝试消息补发再次消费!==============");
            log.error(e.getMessage());
            /**
             * basicRecover方法是进行补发操作，
             * 其中的参数如果为true是把消息退回到queue但是有可能被其它的consumer(集群)接收到，
             * 设置为false是只补发给当前的consumer
             */
            channel.basicRecover(false);
        }
    } 

	@Autowired
	private OrderService orderService;
}
