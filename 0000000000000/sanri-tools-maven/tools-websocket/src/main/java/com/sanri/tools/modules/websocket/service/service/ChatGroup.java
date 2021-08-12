package com.sanri.tools.modules.websocket.service.service;

import com.sanri.tools.modules.core.exception.ToolException;
import com.sanri.tools.modules.websocket.service.WebSocketClient;
import com.sanri.tools.modules.websocket.service.decode.messages.LoginUser;
import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * 聊天组
 */
public class ChatGroup {
    private String name;
    private Map<String, WebSocketClient> members = new HashMap<>();

    public ChatGroup(String name) {
        this.name = name;
    }

    /**
     * 成员上线
     * @param webSocketClient
     */
    public void addMember(WebSocketClient webSocketClient){
        members.put(webSocketClient.getSessionId(),webSocketClient);
    }

    public void dropMember(String sessionId){
        members.remove(sessionId);
    }

    /**
     * 组内广播消息,把自己排除
     * @param clientMessage
     */
    public void broadcast(ClientMessage clientMessage,WebSocketClient self){
        String sessionId = self.getSessionId();
        for (WebSocketClient client : members.values()) {
            if (client.getSessionId().equals(sessionId))continue;
            client.sendMessage(clientMessage);
        }
    }

}
