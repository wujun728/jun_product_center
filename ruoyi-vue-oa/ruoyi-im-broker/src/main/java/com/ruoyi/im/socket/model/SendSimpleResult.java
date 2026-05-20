package com.ruoyi.im.socket.model;

import lombok.Data;

@Data
public class SendSimpleResult<T> {

    /**
     * 命令
     */
    private Integer cmd;

    /**
     * 发送方id
     */
    private UserInfo sender;

    /**
     * 接收方id
     */
    private UserInfo receiver;

    /**
     * 发送回执
     */
    private Boolean sendResult;

    /**
     * 发送状态编码
     */
    private Integer code;

    /**
     * 消息内容
     */
    private T data;

}
