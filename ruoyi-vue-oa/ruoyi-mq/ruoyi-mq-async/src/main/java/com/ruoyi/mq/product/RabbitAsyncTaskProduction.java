package com.ruoyi.mq.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.config.RabbitConfig;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.enums.AsyncStatusEnum;
import com.ruoyi.mq.service.IAsyncLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> rabbitMQ 生产者 </p>
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
public class RabbitAsyncTaskProduction implements IAsyncTaskProduction, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    @Resource
    private RabbitConfig rabbitConfig;
    @Autowired
    private IAsyncLogService asyncLogService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${server.servlet.context-path}")
    private String apiPrefix;
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void produce(AsyncLog asyncLog) throws JsonProcessingException {
        // 1.记录日志
        fill(asyncLog);
        if (StringUtils.isBlank(asyncLog.getId())) {
            asyncLogService.saveAsyncLog(asyncLog);
        }
        // 2.生产消息
        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend(asyncLog.getExchangeKey(), asyncLog.getRoutingKey(), new ObjectMapper().writeValueAsString(asyncLog), correlationData);
    }

    @Override
    public void batchProduct(List<AsyncLog> asyncLogs) throws JsonProcessingException {
        // 1.批量记录日志
        asyncLogs.forEach(this::fill);
        asyncLogService.saveBatchAsyncLog(asyncLogs);
        // 2.批量生产消息
        CorrelationData correlationData = new CorrelationData();
        for (AsyncLog asyncLog : asyncLogs) {
            rabbitTemplate.convertAndSend(asyncLog.getExchangeKey(), asyncLog.getRoutingKey(), new ObjectMapper().writeValueAsString(asyncLog), correlationData);
        }
    }

    /**
     * 补充其他属性
     *
     * @param asyncLog 异步消息
     */
    private void fill(AsyncLog asyncLog) {
        if (StringUtils.isNotBlank(asyncLog.getApiPrefix())) {
            asyncLog.setApiPrefix(apiPrefix.replace("/", ""));
        }
        if (StringUtils.isBlank(asyncLog.getExchangeKey())) {
            asyncLog.setExchangeKey(rabbitConfig.getExchangeKey());
        }
        if (StringUtils.isBlank(asyncLog.getRoutingKey())) {
            asyncLog.setRoutingKey(rabbitConfig.getRouterKey() + "." + applicationName);
        }
        asyncLog.setStatus(AsyncStatusEnum.WAITING.getCode());
        asyncLog.setCreateTime(DateUtils.getNowDate());
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息confirm ack确认，id：{}，ack：{}", correlationData.getId(), ack);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息到达队列失败，id: {}", returnedMessage.getMessage().getMessageProperties().getMessageId());
    }
}
