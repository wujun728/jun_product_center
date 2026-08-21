package com.ruoyi.message.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p> 消息通知 </p>
 *
 * @Author wocurr.com
 */
@Data
@Builder
public class MessageNotice {

    /**
     * 发送者id
     */
    private String senderId;

    /**
     * 接收者id列表
     */
    private List<String> recvIds;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息模板id
     */
    private String templateId;
}
