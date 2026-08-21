package com.ruoyi.im.chat.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p> IM客戶端配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-im-process.properties")
public class ChatProcessProperties {

    /**
     * 系统消息发送交换机
     */
    @Value("${chat.server.message.exchange.system}")
    private String systemExchange;

    /**
     * 系统消息发送routingKey
     */
    @Value("${chat.server.message.routing.system}")
    private String systemRoutingKey;

    /**
     * 系统消息存储topic
     */
    @Value("${chat.client.message.topic.store.system}")
    private String systemStoreTopic;
}
