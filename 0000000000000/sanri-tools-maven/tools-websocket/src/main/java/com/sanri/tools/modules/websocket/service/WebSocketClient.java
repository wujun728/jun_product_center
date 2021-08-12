package com.sanri.tools.modules.websocket.service;

import com.alibaba.fastjson.JSON;
import com.sanri.tools.modules.websocket.service.decode.messages.LoginUser;
import com.sanri.tools.modules.websocket.service.decode.protocol.ClientMessage;
import lombok.Data;
import lombok.Getter;

import javax.websocket.Session;

@Getter
public class WebSocketClient {
    private Session session;
    private LoginUser loginUser;

    public WebSocketClient(Session session,LoginUser loginUser) {
        this.session = session;
        this.loginUser = loginUser;
    }

    /**
     * 客户端绑定用户名
     * @param loginName
     */
    public void bindLoginName(String loginName){
        loginUser.setLoginName(loginName);
    }

    public String getSessionId(){
        return loginUser.getSessionId();
    }

    /**
     * 发送回复简单消息
     * @param request
     * @param message
     */
    public void sendReplayPlainMessage(ClientMessage request,String message){
        ClientMessage clientMessage = new ClientMessage(request.getRoutingKey(), request.getCommand(), request.getTraceId());
        clientMessage.setTimestamp(System.currentTimeMillis());
        clientMessage.setPayload(message);
        sendMessage(clientMessage);
    }

    /**
     * 直接发送消息
     * @param message
     */
    public void sendMessage(ClientMessage message){
        if (session.isOpen()) {
            session.getAsyncRemote().sendText(JSON.toJSONString(message));
        }
    }
}
