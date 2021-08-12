package com.sanri.tools.modules.websocket.service;

import com.alibaba.fastjson.JSON;
import com.sanri.tools.modules.websocket.service.config.CustomSpringConfigurator;
import com.sanri.tools.modules.websocket.service.decode.messages.ChatPayload;
import com.sanri.tools.modules.websocket.service.decode.messages.LoginUser;
import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;
import com.sanri.tools.modules.websocket.service.service.ChatGroup;
import com.sanri.tools.modules.websocket.service.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/listen/{loginName}",configurator = CustomSpringConfigurator.class)
@Component
@Slf4j
public class ServiceEndpoint {
    private Map<String,WebSocketClient> clientMap = new HashMap<>();

    @Autowired
    private MessageDispatch messageDispatch;
    @Autowired
    private ChatService chatService;

    private IdGenerator idGenerator = new AlternativeJdkIdGenerator();

    @OnOpen
    public void onOpen(Session session , @PathParam("loginName") String loginName){
        WebSocketClient client = new WebSocketClient(session, new LoginUser(session.getId(), loginName));

        // 获取当前登录人的组 , 然后组内通知上线
        List<ChatGroup> chatGroups = chatService.chatGroups(loginName);
        for (ChatGroup chatGroup : chatGroups) {
            // 广播成员上线消息
            String traceId = idGenerator.generateId().toString().replaceAll("-", "");
            ClientMessage clientMessage = new ClientMessage("chat", "online", traceId);
            ChatPayload chatPayload = new ChatPayload(session.getId(),"");
            clientMessage.setPayload(chatPayload);
            chatGroup.broadcast(clientMessage,client);

            chatGroup.addMember(client);
        }
        clientMap.put(session.getId(),client);
    }

    @OnClose
    public void onClose(Session session, @PathParam("loginName") String loginName){
        clientMap.remove(session.getId());
        broadcastOffline(session, loginName);
    }

    public void broadcastOffline(Session session, @PathParam("loginName") String loginName) {
        // 获取当前登录人的组 , 获取组内广播下线
        List<ChatGroup> chatGroups = chatService.chatGroups(loginName);
        for (ChatGroup chatGroup : chatGroups) {
            String traceId = idGenerator.generateId().toString().replaceAll("-", "");
            ClientMessage clientMessage = new ClientMessage("chat", "offline", traceId);
            ChatPayload chatPayload = new ChatPayload(session.getId(),"");
            clientMessage.setPayload(chatPayload);
        }
    }

    @OnMessage
    public void onMessage(String message,Session session){
        ClientMessage clientMessage = JSON.parseObject(message, ClientMessage.class);
        clientMessage.setTimestamp(System.currentTimeMillis());
        WebSocketClient webSocketClient = clientMap.get(session.getId());
        messageDispatch.onMessage(clientMessage,webSocketClient);
    }

    @OnError
    public void onError(Session session, Throwable error, @PathParam("loginName") String loginName) {
        clientMap.remove(session.getId());
        log.error("发生错误：{}，Session ID： {}",error.getMessage(),session.getId());
        error.printStackTrace();
        broadcastOffline(session,loginName);
    }

    /**
     * 获取登录的客户端列表
     * @return
     */
    public Collection<WebSocketClient> clients(){
        return clientMap.values();
    }

    /**
     * 获取某一个客户端
     * @param sessionId
     * @return
     */
    public WebSocketClient client(String sessionId){
        return clientMap.get(sessionId);
    }
}
