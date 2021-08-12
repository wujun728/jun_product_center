package com.sanri.tools.modules.websocket.service.decode.messages;

import lombok.Data;

/**
 * 聊天消息
 */
@Data
public class ChatPayload {
    private String message;
    // 消息类型 1 表示文本消息,2 表示图片消息(服务器访问图片地址)
    private int messageType;
    // from to 表示 sessionId
    private String from;
    private String to;
    private String [] files;

    public ChatPayload() {
    }

    public ChatPayload(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
