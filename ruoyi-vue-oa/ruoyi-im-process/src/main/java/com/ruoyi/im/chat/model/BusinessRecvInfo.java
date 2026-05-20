package com.ruoyi.im.chat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BusinessRecvInfo {

    /**
     * 命令类型
     */
    private Integer cmd;

    /**
     * 发送方ID
     */
    private String sender;

    /**
     * 接收方用户列表
     */
    List<ChatUserInfo> receivers;

    /**
     * 当前服务名
     */
    private String serviceName;

    /**
     * 推送消息体
     */
    private Object data;

    @JsonProperty("sender")
    public String getSender() {
        return sender;
    }

    @JsonProperty("receivers")
    public List<ChatUserInfo> getReceivers() {
        return receivers;
    }

}


