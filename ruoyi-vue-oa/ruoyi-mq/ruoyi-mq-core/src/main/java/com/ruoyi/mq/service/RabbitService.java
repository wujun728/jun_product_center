package com.ruoyi.mq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * <p> rabbitmq工具类 </p>
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
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 路由发送消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey 路由key
     * @param message 消息
     */
    public <T> void convertAndSend(String exchangeName, String routingKey, T message) throws JsonProcessingException {
        log.info("convertAndSend: exchangeName:{}, routingKey:{}, message:{}", exchangeName, routingKey, message);
        CorrelationData correlationData = buildCorrelationData();
        convertAndSend(exchangeName, routingKey, message, correlationData);
    }


    /**
     * 路由发送消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey 路由key
     * @param message 消息
     * @param correlationData  消息唯一标识
     */
    public <T> void convertAndSend(String exchangeName, String routingKey, T message, CorrelationData correlationData) {
        log.info("convertAndSend: exchangeName:{}, routingKey:{}, message:{}, correlationData:{}", exchangeName, routingKey, message, correlationData);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationData);
    }

    /**
     * 特定队列发送消息
     *
     * @param queueName 队列名称
     * @param message 消息
     */
    public <T> void convertAndSend(String queueName, T message) throws JsonProcessingException {
        log.info("convertAndSend: queueName:{}, message:{}", queueName, message);
        CorrelationData correlationData = buildCorrelationData();
        convertAndSend(queueName, message, correlationData);
    }


    /**
     * 特定队列发送消息
     *
     * @param queueName 队列名称
     * @param message 消息
     */
    public <T> void convertAndSend(String queueName, T message, CorrelationData correlationData) throws JsonProcessingException {
        log.info("convertAndSend: queueName:{}, message:{}", queueName, message);
        rabbitTemplate.convertAndSend(queueName, message, correlationData);
    }

    /**
     * 构建CorrelationData
     *
     * @return
     */
    private CorrelationData buildCorrelationData(){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(result -> {
            if (Objects.isNull(result)) {
                return;
            }
            if (result.isAck()) {
                //消息成功ACK
                if (log.isDebugEnabled()) {
                    log.debug("消息发送成功，id:{}", correlationData.getId());
                }
            } else {
                //消息失败NACK
                log.error("消息发送失败，id:{}, reason:{}", correlationData.getId(), result.getReason());
            }
        }, ex -> log.error("消息发送异常，id:{}, message:{}",correlationData.getId(),ex.getMessage()));
        return correlationData;
    }
}
