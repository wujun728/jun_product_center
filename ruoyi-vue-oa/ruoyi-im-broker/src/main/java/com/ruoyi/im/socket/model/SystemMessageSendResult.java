package com.ruoyi.im.socket.model;

import lombok.Data;

import java.util.List;

@Data
public class SystemMessageSendResult<T> {

    /**
     * 命令
     */
    private Integer cmd;

    /**
     * 发送方id
     */
    private String sender;

    /**
     * 接收方id
     */
    private List<String> receiver;

    /**
     * 发送状态编码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private T data;

}
