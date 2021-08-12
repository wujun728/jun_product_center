package com.sanri.tools.modules.websocket.service;

import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;

public interface WebsocketMessageListener {
    /**
     * 客户端上报消息
     * @param clientMessage
     * @param webSocketClient
     */
    void listen(ClientMessage clientMessage,WebSocketClient webSocketClient);

    /**
     * 处理消息异常时回调
     * @param e
     * @param clientMessage
     */
    void exceptionCaught(Exception e,ClientMessage clientMessage);

    /**
     * 支持的消息
     * @param routingKey
     * @return
     */
    boolean support(String routingKey);
}
