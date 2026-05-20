package com.ruoyi.mq.rabbitmq;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 自定义rabbitMQ启动配置 </p>
 *
 * @Author wocurr.com
 */
@Configuration
@ConditionalOnProperty(
        prefix = "spring.rabbitmq",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class CustomRabbitAutoConfiguration extends RabbitAutoConfiguration {

}
