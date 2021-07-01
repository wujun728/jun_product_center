package com.projectm.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.framework.common.constant.Constants;
import com.framework.common.exception.CustomException;
import com.framework.security.util.RedisCache;
import com.projectm.system.domain.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;


@ServerEndpoint(value = "/imserver")
@Component
public class WebSocketServer {

    @Autowired
    RedisCache redisCache;
    static Log log=LogFactory.get(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
//    private static ConcurrentHashMap<WebSocketServer> SESSION_WEBSOCKET_MAP = new ConcurrentHashMap<>();
    private static CopyOnWriteArraySet<WebSocketServer> websocketservers = new CopyOnWriteArraySet<>();

    private static ConcurrentHashMap<String,WebSocketServer> SESSION_HTTP_WEBSOCKET_MAP = new ConcurrentHashMap<>();
    /**
     * Sessionid 与client_id的映射
     */
    private static final ConcurrentHashMap<Session, String> SESSION_USER_MAP = new ConcurrentHashMap<>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收client_id*/
    private String client_id="";

    /**接收client_id*/
    private String session_id="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        this.session_id=session.getId();
        websocketservers.add(this);
        //HttpSession httpSession =(HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        //SESSION_HTTP_WEBSOCKET_MAP.put(httpSession.getId(),this);

        addOnlineCount();
        log.info("用户连接:"+session_id+",当前在线人数为:" + getOnlineCount());
        try {
            sendMessageAll(JSONUtils.toJSONString(new HashMap(){{
                put("action","connect");
                put("data",new HashMap(){{
                    put("client_id",session_id);
                    put("online",WebSocketServer.getOnlineCount());
                }});
            }}));
        } catch (IOException e) {
            log.error("用户:"+session_id+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        websocketservers.remove(this);
        subOnlineCount();
        try {
            sendMessageAll(JSONUtils.toJSONString(new HashMap(){{
                put("action","connect");
                put("data",new HashMap(){{
                    put("client_id",session_id);
                    put("online",WebSocketServer.getOnlineCount());
                }});
            }}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("用户退出:"+session_id+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+session_id+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
//        if(StringUtils.isNotBlank(message)){
//            try {
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId",this.session_id);
//                String toUserId=jsonObject.getString("toUserId");
//                //传送给对应toUserId用户的websocket
//                if(StringUtils.isNotBlank(toUserId)&&SESSION_WEBSOCKET_MAP.containsKey(toUserId)){
//                    SESSION_WEBSOCKET_MAP.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    log.error("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.session_id+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void logOut(String httpSessionId) throws IOException {
        WebSocketServer wss = SESSION_HTTP_WEBSOCKET_MAP.get(httpSessionId);
        if(ObjectUtil.isNotEmpty(wss)){
            websocketservers.remove(wss);
        }
        subOnlineCount();
        sendMessageAll(JSONUtils.toJSONString(new HashMap(){{
            put("action","connect");
            put("data",new HashMap(){{
                //put("client_id",session_id);
                put("online",WebSocketServer.getOnlineCount());
            }});
        }}));
    }

    /**
     * 群发推送
     */
    public static void sendMessageAll(String message) throws IOException {
        for (WebSocketServer socketServer : websocketservers) {
            socketServer.session.getBasicRemote().sendText(message);
        }
    }


    /**
     * 发送自定义消息
     * */
    public void sendInfo(Notify notify, @PathParam("userId") String memberCode) {
        String message = buildInfo(notify.getAction(), notify);
        if (StrUtil.isEmpty(message)) {
            throw new CustomException("构建消息体错误！");
        }
        log.info("发送消息到:"+memberCode+"，报文:"+message);
        Set<String> sessionIds = redisCache.getCacheObject(Constants.WEBSOCKET + memberCode);
        for (WebSocketServer server : websocketservers) {
            if (CollUtil.isNotEmpty(sessionIds)) {
                sessionIds.forEach(o -> {
                    if (StrUtil.equals(server.session_id, o)) {
                        try {
                            server.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        log.error("用户"+memberCode+",不在线！");
                    }
                });
            }
        }
    }

    private String buildInfo(String action, Notify notify) {
        Map<String, Object> map = new HashMap<>(8);
        if (StrUtil.equals("notice", action)) {
            map.put("action", "notice");
        }
        if (StrUtil.equals("task", action) && notify != null) {
            Map<String, Object> avatar = new HashMap<>(8);
            avatar.put("avatar", notify.getAvatar());
            map.put("action", "task");
            map.put("title", notify.getTitle());
            map.put("msg", notify.getContent());
            map.put("data", new HashMap(){{
                put("notify", avatar);}});
        }
        return JSONUtils.toJSONString(map);
    }

    public static synchronized int getOnlineCount() {
    	return onlineCount>0?onlineCount:1;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    
    public static Set<String> cleanInfo(Set<String> sessionIds) {
        Set<String> ids = new HashSet<>();
        for (WebSocketServer server : websocketservers) {
            ids.add(server.session_id);
        }
        Set<String> newStrs = new HashSet<>();
        if (sessionIds != null) {
            for (String next : sessionIds) {
                if (ids.contains(next)) {
                    newStrs.add(next);
                }
            }
        }
        return newStrs;
    }
}