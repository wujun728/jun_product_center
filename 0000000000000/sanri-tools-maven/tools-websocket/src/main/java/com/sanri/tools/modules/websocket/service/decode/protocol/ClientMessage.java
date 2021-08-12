package com.sanri.tools.modules.websocket.service.decode.protocol;

import lombok.Data;

@Data
public class ClientMessage {
    // 路由位置,一般用于模块消息
    private String routingKey;
    // 模块指令
    private String command;
    // 跟踪标识,每一个指令一个跟踪标识, 由前端生成
    private String traceId;

    private long timestamp;
    private Object payload;

    public ClientMessage() {
    }

    public ClientMessage(String routingKey,String command, String traceId) {
        this.routingKey = routingKey;
        this.traceId = traceId;
        this.command = command;
    }

    public ClientMessage(String routingKey, String command, String traceId, long timestamp, Object payload) {
        this.routingKey = routingKey;
        this.command = command;
        this.traceId = traceId;
        this.timestamp = timestamp;
        this.payload = payload;
    }
}
