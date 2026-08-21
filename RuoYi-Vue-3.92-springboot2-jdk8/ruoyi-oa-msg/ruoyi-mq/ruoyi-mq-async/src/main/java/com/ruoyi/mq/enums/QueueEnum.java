package com.ruoyi.mq.enums;

/**
 * 队列名枚举
 *
 * @Author wocurr.com
 */
public enum QueueEnum {
    /**
     * 流程
     */
    ASYNC_FLOW_QUEUE("async-flow-queue", "flowAsyncService"),
    /**
     * 待办处理
     */
    ASYNC_TODO_QUEUE("async-todo-queue", "todoAsyncService"),
    /**
     * 流程正文转换
     */
    ASYNC_NAME_MAIN_TEXT_CONVERT_QUEUE("async-main-text-convert-queue", "convertMainTextConsumer"),
    /**
     * 消息通知
     */
    ASYNC_MESSAGE_QUEUE("async-message-notice-queue", "messageNoticeAsyncService"),
    /**
     * 短信通知
     */
    ASYNC_SMS_QUEUE("async-sms-queue", "smsAsyncService"),
    /**
     * 我起草的流程
     */
    ASYNC_MY_DRAFT_QUEUE("async-my-draft-queue", "myDraftAsyncConsumer"),
    ;
    private final String queueName;

    private final String consumerBeanName;

    QueueEnum(String queueName,  String consumerBeanName) {
        this.queueName = queueName;
        this.consumerBeanName = consumerBeanName;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getConsumerBeanName() {
        return consumerBeanName;
    }
}
