package com.ruoyi.im.socket.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import com.ruoyi.im.socket.config.SocketServerProperties;
import com.ruoyi.im.socket.enums.SendCodeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.*;
import com.ruoyi.im.socket.utils.JsonUtil;
import com.ruoyi.im.socket.utils.RedisLock;
import com.ruoyi.mq.service.RabbitService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 系统消息处理监听器 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Component
@ConditionalOnProperty(
        name = {"spring.rabbitmq.enabled","socket.server.enabled"},
        havingValue = "true"
)
public class SystemMessageDispatchListener {

    @Autowired(required = false)
    private RabbitService rabbitService;
    @Autowired
    private SocketServerProperties socketServerProperties;
    @Autowired
    private RedisLock redisLock;

    private static final String SYSTEM_MESSAGE_DISPATCH_LOCK_KEY = "system:message:dispatch:lock:";

    /**
     * 监听队列，处理系统消息（并发消费）
     *
     * @param channel 通道
     * @param message 消息对象
     */
    @RabbitListener(queues = "${socket.server.message.topic.system}", concurrency = "3-5")
    public void consume(Channel channel, Message message) throws IOException {
        log.info("异步消息接收，consumerTag={}, consumerQueue={}", message.getMessageProperties().getConsumerTag(), message.getMessageProperties().getConsumerQueue());
        if (message.getBody() == null || message.getBody().length == 0) {
            log.error("消息体为空，丢弃消息");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            return;
        }

        BusinessRecvInfo recvInfo = parseMessage(message);
        if (recvInfo == null) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            return;
        }
        log.info("收到系统消息: {}", recvInfo);

        //幂等性处理：在某些情况下（如消息重新入队、网络问题等），同一条消息可能会被再次投递。
        redisLock.doLock(SYSTEM_MESSAGE_DISPATCH_LOCK_KEY + message.getMessageProperties().getMessageId(), () -> {
            try {
                processMessage(recvInfo);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                log.error("消息处理失败：", e);
            }
        });
    }

    private BusinessRecvInfo parseMessage(Message message) {
        try {
            return JsonUtil.decode(
                    new String(message.getBody(), StandardCharsets.UTF_8),
                    BusinessRecvInfo.class
            );
        } catch (Exception e) {
            log.error("JSON decoding failed. Message: {} Error: {}", message, e.getMessage());
            return null;
        }
    }

    private void processMessage(BusinessRecvInfo recvInfo) throws IOException {
        if (recvInfo.getData() == null) {
            log.warn("Message data is null from sender: {}", recvInfo.getSender());
            return;
        }

        List<String> receiverIds = processReceivers(recvInfo);
        storeMessageResult(recvInfo, receiverIds);
    }

    private List<String> processReceivers(BusinessRecvInfo recvInfo) {
        List<String> receiverIds = new ArrayList<>(recvInfo.getReceivers().size());

        recvInfo.getReceivers().forEach(receiver -> {
            try {
                ChannelHandlerContext ctx = UserChannelCtxMap.getChannelCtx(
                        receiver.getId(),
                        receiver.getToken()
                );

                if (ctx != null && ctx.channel().isActive()) {
                    sendToReceiver(recvInfo, receiver);
                    receiverIds.add(receiver.getId());
                }
            } catch (RuntimeException e) {
                log.error("Failed to send to receiver: {}. Error: {}",
                        receiver.getId(), e.getMessage());
            }
        });

        return receiverIds;
    }

    private void sendToReceiver(BusinessRecvInfo recvInfo, UserInfo receiver) {
        SystemMessage originalData = recvInfo.getData();
        SystemMessage clonedData = cloneDataWithRecvId(originalData, receiver.getId());

        SendInfo<Object> sendInfo = new SendInfo<>();
        sendInfo.setCmd(recvInfo.getCmd());
        sendInfo.setData(clonedData);

        UserChannelCtxMap.getChannelCtx(receiver.getId(), receiver.getToken())
                .channel()
                .writeAndFlush(sendInfo);
    }

    private SystemMessage cloneDataWithRecvId(SystemMessage original, String receiverId) {
        // 实现深拷贝逻辑
        SystemMessage cloned = new SystemMessage();
        cloned.setId(original.getId());
        cloned.setType(original.getType());
        cloned.setRecvId(receiverId);
        cloned.setContent(original.getContent());
        cloned.setSendId(original.getSendId());
        cloned.setSendTime(original.getSendTime());
        cloned.setStatus(original.getStatus());
        cloned.setCreateId(original.getCreateId());
        cloned.setCreateTime(original.getCreateTime());
        return cloned;
    }

    private void storeMessageResult(BusinessRecvInfo recvInfo, List<String> receiverIds) throws JsonProcessingException {
        if (CollectionUtils.isEmpty(receiverIds)) return;

        SystemMessageSendResult<Object> result = new SystemMessageSendResult<>();
        result.setCmd(recvInfo.getCmd());
        result.setSender(recvInfo.getSender());
        result.setReceiver(receiverIds);
        result.setCode(SendCodeEnum.SUCCESS.code());
        result.setData(recvInfo.getData());

        rabbitService.convertAndSend(
                socketServerProperties.getSystemStoreExchange(),
                socketServerProperties.getSystemStoreRoutingKey(),
                JsonUtil.encode(result)
        );
    }
}
