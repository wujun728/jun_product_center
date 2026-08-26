package com.ruoyi.im.chat.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import com.ruoyi.im.chat.domain.SystemMessage;
import com.ruoyi.im.chat.enums.BusinessMessageType;
import com.ruoyi.im.chat.model.SystemMessageSendResult;
import com.ruoyi.im.chat.service.ISystemMessageService;
import com.ruoyi.im.chat.utils.JsonUtil;
import com.ruoyi.tools.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p> 系统消息持久化监听器 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Component
@ConditionalOnProperty(
        name = {"spring.rabbitmq.enabled"},
        havingValue = "true"
)
public class SystemMessagePersistentListener {

    @Autowired
    private ISystemMessageService systemMessageService;
    @Autowired
    private RedisLock redisLock;

    private static final String SYSTEM_MESSAGE_PERSISTENT_LOCK_KEY = "system:message:persistent:lock:";

    /**
     * 监听队列，处理消息入库
     *
     * @param channel 通道
     * @param message 消息对象
     */
    @RabbitListener(queues = "${chat.client.message.topic.store.system}", concurrency = "3-5")
    public void consume(Channel channel, Message message) throws IOException {
        log.info("异步消息接收，consumerTag={}, consumerQueue={}", message.getMessageProperties().getConsumerTag(), message.getMessageProperties().getConsumerQueue());
        if (message.getBody() == null || message.getBody().length == 0) {
            log.error("消息体为空，丢弃消息");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            return;
        }

        // 使用分布式锁（悲观锁），防止重复消费
        redisLock.doLock(SYSTEM_MESSAGE_PERSISTENT_LOCK_KEY + message.getMessageProperties().getMessageId(), () -> {
            try {
                handleMessage(message);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                log.error("消息处理异常，消息内容：{}，异常信息：{}", message, e.getMessage());
                try {
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                } catch (IOException ex) {
                    log.error("消息响应失败，消息内容：{}，异常信息：", message, ex);
                }
            }
        });
    }

    /**
     * 处理消息入库
     *
     * @param message 消息对象
     */
    public void handleMessage(Message message) throws JsonProcessingException {
        SystemMessageSendResult sendResult = JsonUtil.decode(
                new String(message.getBody(), StandardCharsets.UTF_8),
                SystemMessageSendResult.class
        );

        if (sendResult == null || sendResult.getData() == null) {
            log.warn("无效消息体结构");
            return;
        }

        SystemMessage systemMessage = JsonUtil.transForm(sendResult.getData(), SystemMessage.class);
        BusinessMessageType businessMessageType = BusinessMessageType.fromCode(systemMessage.getType());

        if (businessMessageType == null) {
            log.warn("未知业务类型：{}", systemMessage.getType());
            return;
        }

        if (businessMessageType.getInsertDbFlag()) {
            systemMessageService.insertSystemMessage(systemMessage);
            log.debug("已持久化类型[{}]的消息", businessMessageType);
        }
    }
}
