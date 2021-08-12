package com.shuogesha.mq.log;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.shuogesha.async.task.AsyncLogTaskService;
import com.shuogesha.mq.SystemLogQueueConfig;
import com.shuogesha.mq.order.OrderDelayedReceiver;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.service.SystemLogService;

/**
 * 日志记录监听
 * 
 * @author zhaohaiyuan
 *
 */
@Component
public class SystemLogReceiver {
	private static Logger log = LoggerFactory.getLogger(OrderDelayedReceiver.class);

	@Autowired
	private SystemLogService systemLogService;
	/**
	 * 异步保存日志任务
	 */
	@Autowired
	private AsyncLogTaskService asyncLogTaskService;

	@RabbitListener(queues = SystemLogQueueConfig.USER_LOG_QUEUE_NAME)
	public void logReceive(String content,Message message, Channel channel) throws IOException {
		log.info("===============接收日志队列接收消息====================");
         //通知 MQ 消息已被接收,可以ACK(从队列中删除)了
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        try {  
         	SystemLog bean=new JSONObject().parseObject(content, SystemLog.class); 
//         	systemLogService.save(bean);
         	asyncLogTaskService.executeAsyncTask(bean);
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

}
