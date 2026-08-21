package com.ruoyi.mq.domain;

import lombok.Data;

/**
 * <p> 异步任务消息体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class AsyncTaskMessage {

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容（json）
     */
    private String msgContent;

    /**
     * 执行异步任务bean名称
     */
    private String beanName;
}
