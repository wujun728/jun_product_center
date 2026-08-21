package com.ruoyi.im.socket.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p> Socket服务器配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-im-broker.properties")
@ConditionalOnProperty(
        prefix = "socket.server",
        name = "enabled",
        havingValue = "true"
)
public class SocketServerProperties {

    /**
     * 端口
     */
    @Value("${socket.server.port}")
    private String port;

    /**
     * 认证uri
     */
    @Value("${socket.server.uri}")
    private String uri;

    /**
     * 服务器名称
     */
    @Value("${socket.server.name}")
    private String name;

    /**
     * 系统消息存储交换机
     */
    @Value("${socket.server.message.exchange.store.system}")
    private String systemStoreExchange;

    /**
     * 系统消息存储routingKey
     */
    @Value("${socket.server.message.routing.store.system}")
    private String systemStoreRoutingKey;

    /**
     * 系统消息发送topic
     */
    @Value("${socket.server.message.topic.system}")
    private String systemTopic;
}
