package com.ruoyi.im.chat.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ChatSystemMessage<T> {

    /**
     * 接收者id列表，为空表示向所有在线用户广播
     */
    private List<String> recvIds = new LinkedList<>();

    /**
     * 是否需要回推发送结果,默认true
     */
    private Boolean sendResult = true;

    /**
     * 消息内容
     */
    private T data;
}
