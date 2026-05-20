package com.ruoyi.mq.managment;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> mq队列管理器 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
public class MessageQueueManagement {

    /**
     * 默认队列名称
     */
    private String defaultQueueKey;

    /**
     * 默认主题key
     */
    private String defaultTopicKey;

    /**
     * 存储队列的交换机key
     */
    private Map<String, String> exchangeMap = new ConcurrentHashMap<>();
    /**
     * 存储队列的路由key
     */
    private Map<String, String> routerMap = new ConcurrentHashMap<>();
}
