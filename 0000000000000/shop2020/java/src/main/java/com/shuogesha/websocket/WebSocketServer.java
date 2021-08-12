package com.shuogesha.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 一对一的聊天例子 
 * @author zhaohaiyuan
 *
 */
@ServerEndpoint("/im/{uid}")
@Component
public class WebSocketServer {
	private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	 // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0; 
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
 
    // 接收uid
    private Long uid = null; 
	/**
	 * 存活的session集合（使用线程安全的map保存）
	 */
	private static Map<Long, WebSocketServer> websocketList = new ConcurrentHashMap<>();
	
	 /**
     * 建立连接的回调方法
     *
     * @param session 与客户端的WebSocket连接会话
     * @param userId  用户名，WebSocket支持路径参数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") Long uid) {
        this.session = session;
        if(websocketList.get(uid)!=null) {
        	addOnlineCount();           // 在线数加1
        }
    	websocketList.put(uid, this);  
        log.info("客户端: " + uid + " 连接成功, 当前在线人数为：" + getOnlineCount());
        this.uid = uid;
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("发送消息异常：", e);
//        } 
    }
    
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
    	if(websocketList.get(this.uid)!=null){
            websocketList.remove(this.uid);
            subOnlineCount();           //在线数减1
            log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        }  
    } 
     
//    
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+this.uid+"的信息:"+message);
        if(StringUtils.isBlank(message)){
        	 JSONObject object = new JSONObject();
      	   	 object.put("type", 100);//心跳
              try {
				sendMessage(this.session,JSON.toJSONString(object));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            JSONArray list=JSONArray.parseArray(message);
//            for (int i = 0; i < list.size(); i++) {
//                try {
//                    //解析发送的报文
//                    JSONObject object = list.getJSONObject(i);
//                    String toUserId=object.getString("toUserId");
//                    String contentText=object.getString("contentText");
//                    object.put("fromUserId",this.uid);
//                    //传送给对应用户的websocket
//                    if(StringUtils.isNotBlank(toUserId)&&StringUtils.isNotBlank(contentText)){
//                    	WebSocketServer socketx=websocketList.get(toUserId);
//                        //需要进行转换，userId
//                        if(socketx!=null){
//                            socketx.sendMessage(socketx.session,JSON.toJSONString(new JsonResult(ResultCode.SUCCESS,  object)));
//                            //此处可以放置相关业务代码，例如存储到数据库
//                        }
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
        }
    } 
    
    /**
    *
    * @param session
    * @param error
    */
   @OnError
   public void onError(Session session, Throwable error) {
       log.error("发生错误");
       error.printStackTrace();
   }
   /**
    * 实现服务器主动推送
    */
   public static void sendMessage(Session session,String message) throws IOException {
//	   session.getBasicRemote().sendText(message);
       ByteBuffer bf=ByteBuffer.wrap(message.getBytes("utf-8"));
       session.getBasicRemote().sendBinary(bf);
   } 
   
   /**
    * 实现服务器主动推送
    */
   public static void sendMessageToUser(Long userId,String message) throws IOException {
	   if(userId!=null&&userId>0){
       	WebSocketServer socketx=websocketList.get(userId);
           //需要进行转换，userId
           if(socketx!=null){
        	   JSONObject object = new JSONObject();
        	   object.put("method", "chat");
        	   object.put("data",JSON.parse(message));
               socketx.sendMessage(socketx.session,JSON.toJSONString(object));
               //此处可以放置相关业务代码，例如存储到数据库
           }
       }
   } 
   
   /**
    * 群发消息
    *
    * @param message
    */
   public static void sendMessageToAll(String message) {
	   websocketList.forEach((sessionId, session) -> {
		   try {
			sendMessage((Session) session, message);
		} catch (IOException e) { 
		}
       });
   }
   
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	WebSocketServer.onlineCount--;
    } 
}
