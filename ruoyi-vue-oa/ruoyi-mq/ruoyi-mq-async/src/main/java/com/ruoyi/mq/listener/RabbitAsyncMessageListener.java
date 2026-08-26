package com.ruoyi.mq.listener;

import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.consume.AsyncTaskConsumeService;
import com.ruoyi.mq.domain.AsyncLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p> rabbitMQ 消费者 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Lazy
@Component
@ConditionalOnProperty(
        prefix = "spring.rabbitmq",
        name = "enabled",
        havingValue = "true"
)
public class RabbitAsyncMessageListener {

    @Autowired
    private AsyncTaskConsumeService consumeService;

    /**
     * 监听消费
     *
     * @param msg 消息内容
     * @param channel 传输通道
     * @param message 消息体
     */
    public void consume(String msg, Channel channel, Message message) throws IOException {
        log.info("异步消息接收，consumerTag={}, consumerQueue={}, msg={}", message.getMessageProperties().getConsumerTag(), message.getMessageProperties().getConsumerQueue(), msg);
        if (StringUtils.isBlank(msg)) {
            log.error("异步消息为空");
            return;
        }
        AsyncLog asyncLog = JSONObject.parseObject(msg, AsyncLog.class);
        if (asyncLog == null) {
            log.error("异步消息解析错误，msg={}", msg);
            return;
        }
        try {
            consumeService.consume(asyncLog);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            log.error("异步任务执行失败：", e);
        }
    }
}
