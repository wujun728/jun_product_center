package com.ruoyi.im.socket.model;

import lombok.Data;

import java.util.List;

@Data
public class RecvSimpleInfo {

    /**
     * 命令类型
     */
    private Integer cmd;

    /**
     * 发送方id
     */
    private UserInfo sender;

    /**
     * 接收方用户id列表
     */
    List<String> receivers;

    /**
     * 是否需要回调发送结果
     */
    private Boolean sendResult;

    /**
     * 当前服务名（回调发送结果使用）
     */
    private String serviceName;
    /**
     * 推送消息体
     */
    private Object data;
}


