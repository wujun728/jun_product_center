package com.ruoyi.mq.api;

/**
 * 同步任务推送接口
 * @Author wocurr.com
 */
public interface ISyncPush {

    /**
     * 同步推送
     * @param consumerBeanName 消费者bean名称
     * @param jsonContent 消息内容（json对象字符串）
     * @param queue 消费队列
     */
    void push(String consumerBeanName, String jsonContent, String queue);

    /**
     * 同步推送
     * @param consumerBeanName 消费者bean名称
     * @param jsonContent 消息内容（json对象字符串）
     * @param queue 消费队列
     * @param creatorId 任务创建人ID
     */
    void push(String consumerBeanName, String jsonContent, String queue, String creatorId);
}
