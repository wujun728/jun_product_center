package com.ruoyi.mq.config;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.mq.constants.MqConstant;
import com.ruoyi.mq.domain.RabbitMqProperties;
import com.ruoyi.mq.listener.RabbitAsyncMessageListener;
import com.ruoyi.mq.listener.RabbitMessageListenerAdapter;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p> rabbitMq扩展配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
@ConditionalOnProperty(
        prefix = "spring.rabbitmq",
        name = "enabled",
        havingValue = "true"
)
public class RabbitExtendConfig implements InitializingBean {

    /**
     * 自定义配置
     */
    @Resource
    private RabbitMqProperties rabbitMqProperties;
    /**
     * 监听注册器
     */
    @Resource
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    /**
     * 监听器
     */
    @Resource
    private RabbitAsyncMessageListener rabbitAsyncMessageListener;
    /**
     * 监听容器工厂
     */
    @Resource
    private SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;

    /**
     * 消费者监听器方法名
     */
    private static final String LISTENER_METHOD = "consume";


    /**
     * 注册和绑定自定义队列
     */
    @Override
    public void afterPropertiesSet() {
        if (MapUtils.isEmpty(rabbitMqProperties.getQueue())) {
            return;
        }
        RabbitMessageListenerAdapter messageListenerAdapter = new RabbitMessageListenerAdapter(rabbitAsyncMessageListener);
        messageListenerAdapter.setDefaultListenerMethod(LISTENER_METHOD);
        Map<String, String> queueMap = rabbitMqProperties.getQueue();
        for (Map.Entry<String, String> entry : queueMap.entrySet()) {
            String queueKey = entry.getKey();
            if (!queueKey.contains(MqConstant.MQ_TYPE_ASYNC)) {
                continue;
            }
            String routingExchangeKey = entry.getValue();
            if (StringUtils.isBlank(routingExchangeKey)) {
                continue;
            }
            Queue queue = new Queue(queueKey.replaceAll("-", "."));
            SimpleRabbitListenerEndpoint simpleRabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
            simpleRabbitListenerEndpoint.setId(IdUtils.fastSimpleUUID());
            simpleRabbitListenerEndpoint.setQueues(queue);
            simpleRabbitListenerEndpoint.setMessageListener(messageListenerAdapter);
            // startImmediately为true时，立即启动监听器容器会导致RabbitListener注解失效
            rabbitListenerEndpointRegistry.registerListenerContainer(simpleRabbitListenerEndpoint, rabbitListenerContainerFactory, false);
        }
    }
}
