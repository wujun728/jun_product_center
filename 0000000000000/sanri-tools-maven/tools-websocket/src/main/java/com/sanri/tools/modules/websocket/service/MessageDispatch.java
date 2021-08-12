package com.sanri.tools.modules.websocket.service;

import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageDispatch {
    private List<WebsocketMessageListener> websocketMessageListeners = new ArrayList<>();

    public void register(WebsocketMessageListener websocketMessageListener){
        websocketMessageListeners.add(websocketMessageListener);
    }

    void onMessage(ClientMessage clientMessage,WebSocketClient webSocketClient){
        for (WebsocketMessageListener websocketMessageListener : websocketMessageListeners) {
            try {
                if (websocketMessageListener.support(clientMessage.getRoutingKey())){

                    websocketMessageListener.listen(clientMessage,webSocketClient);
                }
            } catch (Exception e) {
                websocketMessageListener.exceptionCaught(e,clientMessage);
            }
        }
    }
}
