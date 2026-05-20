package com.ruoyi.im.chat.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 业务系统消息
 */
@Data
public class BusinessSystemMessage {

    /**
     * 业务类型，1-聊天系统消息
     */
    private int type;

    /**
     * 发送人ID
     */
    private String senderId;

    /**
     * 接收者id列表，为空表示向所有在线用户广播
     */
    private List<String> recvIds = new LinkedList<>();

    /**
     * 消息内容
     */
    private String content;
}
