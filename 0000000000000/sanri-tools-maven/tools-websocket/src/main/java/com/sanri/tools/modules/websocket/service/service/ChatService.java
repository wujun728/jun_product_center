package com.sanri.tools.modules.websocket.service.service;

import com.alibaba.fastjson.JSONObject;
import com.sanri.tools.modules.websocket.service.MessageDispatch;
import com.sanri.tools.modules.websocket.service.ServiceEndpoint;
import com.sanri.tools.modules.websocket.service.WebSocketClient;
import com.sanri.tools.modules.websocket.service.WebsocketMessageListener;
import com.sanri.tools.modules.websocket.service.decode.messages.ChatPayload;
import com.sanri.tools.modules.websocket.service.decode.messages.LoginUser;
import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ServiceEndpoint serviceEndpoint;

    // 全员分组
    private ChatGroup chatGroup = new ChatGroup("all");

    @Autowired
    private MessageDispatch messageDispatch;

    class ChatMessageListener implements WebsocketMessageListener{
        @Override
        public void listen(ClientMessage clientMessage, WebSocketClient webSocketClient) {
            String command = clientMessage.getCommand();
            ChatCommand chatCommand = ChatCommand.valueOf(command);
            switch (chatCommand){
                case SEND_MESSAGE:
                    String sessionId = webSocketClient.getSessionId();
                    JSONObject payload = (JSONObject) clientMessage.getPayload();
                    ChatPayload chatPayload = payload.toJavaObject(ChatPayload.class);
                    chatPayload.setFrom(sessionId);
                    clientMessage.setPayload(chatPayload);

                    String to = chatPayload.getTo();
                    if (StringUtils.isBlank(to)){
                        // 如果没有接收人,则是广播消息,所有人都要接收到
                        chatGroup.broadcast(clientMessage,webSocketClient);
                    }else{
                        WebSocketClient client = serviceEndpoint.client(to);
                        client.sendMessage(clientMessage);
                    }
            }
        }

        @Override
        public void exceptionCaught(Exception e, ClientMessage clientMessage) {
            log.error("聊天模块消息异常:{}",e.getMessage(),e);
        }

        @Override
        public boolean support(String routingKey) {
            return "chat".equals(routingKey);
        }
    }

    enum ChatCommand{
        SEND_MESSAGE
    }

    /**
     * 在线用户列表
     * @param sessionId
     * @return
     */
    public List<LoginUser> onlineUsers(String sessionId){
        List<LoginUser> collect = serviceEndpoint.clients().stream()
                .map(WebSocketClient::getLoginUser)
                .filter(loginUser -> !loginUser.getSessionId().equals(sessionId))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取当前登录用户的所有组 , 现在默认只有一个组
     * @param loginName
     * @return
     */
    public List<ChatGroup> chatGroups(String loginName){
        return Collections.singletonList(chatGroup);
    }

    @PostConstruct
    public void register(){
        ChatMessageListener chatMessageListener = new ChatMessageListener();
        messageDispatch.register(chatMessageListener);
    }
}
