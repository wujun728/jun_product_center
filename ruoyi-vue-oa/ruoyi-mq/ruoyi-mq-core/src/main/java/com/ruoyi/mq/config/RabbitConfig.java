package com.ruoyi.mq.config;

import com.ruoyi.mq.domain.RabbitMqProperties;
import com.ruoyi.mq.enums.RabbitMqTypeEnum;
import com.ruoyi.mq.managment.MessageQueueManagement;
import com.ruoyi.mq.utils.BeanUtil;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p> rabbitMQ配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
@EnableRabbit
@PropertySource(value = "classpath:application-mq.properties")
@ConfigurationProperties(value = "mq.rabbit")
@EnableConfigurationProperties({RabbitMqProperties.class})
@ConditionalOnProperty(
        prefix = "spring.rabbitmq",
        name = "enabled",
        havingValue = "true"
)
public class RabbitConfig implements InitializingBean {

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
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private MessageQueueManagement mqQueueManagement;

    /**
     * 交换机类型
     */
    @Value("${mq.rabbit.async.exchange.type}")
    private String exchangeType;

    /**
     * 交换机key
     */
    @Value("${mq.rabbit.async.exchange.key}")
    private String exchangeKey;

    /**
     * 是否持久化
     */
    @Value("${mq.rabbit.async.durable}")
    private Boolean durable;

    /**
     * 交换机key
     */
    @Value("${mq.rabbit.async.queue.key}")
    private String queueKey;

    /**
     * 路由key
     */
    @Value("${mq.rabbit.async.router.key}")
    private String routerKey;

    /**
     * 应用名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 注册异步队列
     *
     * @return Queue
     */
    @Bean("asyncQueue")
    public Queue asyncQueue() {
        return QueueBuilder
                .durable(queueKey + "." + applicationName)
                .build();
    }

    /**
     * 注册异步交换机
     * 四种类型：direct 定向交换机, topic 主题交换机, headers 头部交换机, fanout 广播交换机
     *
     * @return Exchange
     */
    @Bean("asyncExchange")
    public Exchange asyncExchange() {
        return new ExchangeBuilder(exchangeKey, exchangeType)
                .durable(durable)
                .build();
    }

    /**
     * 获取交换机
     * 四种类型：direct 定向交换机, topic 主题交换机, headers 头部交换机, fanout 广播交换机
     *
     * @return Exchange
     */
    private Exchange getExchange(String exchangeKey, String exchangeType) {
        return getExchange(exchangeKey, exchangeType, true);
    }

    /**
     * 获取交换机
     * 四种类型：direct 定向交换机, topic 主题交换机, headers 头部交换机, fanout 广播交换机
     *
     * @return Exchange
     */
    private Exchange getExchange(String exchangeKey, String exchangeType, Boolean durable) {
        return new ExchangeBuilder(exchangeKey, exchangeType)
                .durable(durable)
                .build();
    }

    /**
     * 绑定关联关系
     *
     * @return Binding
     */
    @Bean("asyncBinding")
    public Binding asyncBinding() {
        return BindingBuilder
                .bind(asyncQueue())
                .to(asyncExchange())
                .with(routerKey + "." + applicationName).noargs();
    }

    /**
     * 注册和绑定自定义队列
     */
    @Override
    public void afterPropertiesSet() {
        if (MapUtils.isEmpty(rabbitMqProperties.getQueue())) {
            return;
        }
        mqQueueManagement.setDefaultQueueKey(queueKey);
        Map<String, String> queueMap = rabbitMqProperties.getQueue();
        for (Map.Entry<String, String> entry : queueMap.entrySet()) {
            String routingExchangeKey = entry.getValue();
            if (StringUtils.isBlank(routingExchangeKey)) {
                continue;
            }
            String queueKey = entry.getKey();
            Queue queue = new Queue(queueKey.replaceAll("-", "."));
            BeanUtil.registerBean(applicationContext, queueKey, queue);
            String[] routingExchanges = routingExchangeKey.split(",");
            if (routingExchanges.length > 1 && StringUtils.isNotBlank(routingExchanges[1])) {
                String[] exchangeStrArray = routingExchanges[1].split(":");
                Exchange exchange;
                String exchangeName;
                if (exchangeStrArray.length > 1) {
                    exchangeName = exchangeStrArray[0].replaceAll("-", ".");
                    exchange = getExchange(exchangeName, exchangeStrArray[1]);
                } else {
                    exchangeName = routingExchanges[1].replaceAll("-", ".");
                    exchange = getExchange(exchangeName, RabbitMqTypeEnum.DIRECT.getCode());
                }
                Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingExchanges[0]).noargs();
                BeanUtil.registerBean(applicationContext, exchangeName, exchange);
                BeanUtil.registerBean(applicationContext, routingExchanges[0], binding);
                mqQueueManagement.getExchangeMap().put(queueKey, exchangeName);
                mqQueueManagement.getRouterMap().put(queueKey, routingExchanges[0]);
            } else {
                Binding binding = BindingBuilder.bind(queue).to(asyncExchange()).with(routerKey).noargs();
                BeanUtil.registerBean(applicationContext, routerKey, binding);
                mqQueueManagement.getExchangeMap().put(queueKey, exchangeKey);
                mqQueueManagement.getRouterMap().put(queueKey, routerKey);
            }
        }
    }

    /**
     * 监听容器工厂
     *
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
     * 自定义监听容器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setDefaultRequeueRejected(false);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setExposeListenerChannel(true);
        return container;
    }
}
