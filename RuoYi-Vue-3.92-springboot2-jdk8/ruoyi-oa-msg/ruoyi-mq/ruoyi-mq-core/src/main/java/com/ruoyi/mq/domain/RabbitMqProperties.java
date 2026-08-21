package com.ruoyi.mq.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <p> rabbitmq配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@ConfigurationProperties(
    prefix = "spring.rabbitmq"
)
public class RabbitMqProperties {

    /**
     * 队列map，key：队列名称， value: 路由key, exchange
     */
    private Map<String, String> queue;
}
