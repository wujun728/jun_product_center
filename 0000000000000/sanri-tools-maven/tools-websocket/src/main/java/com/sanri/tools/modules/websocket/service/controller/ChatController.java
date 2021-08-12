package com.sanri.tools.modules.websocket.service.controller;

import com.sanri.tools.modules.websocket.service.decode.messages.LoginUser;
import com.sanri.tools.modules.websocket.service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 展示在线用户
     * @param sessionId
     * @return
     */
    @GetMapping("/onlineUsers")
    public List<LoginUser> onlineUsers(String sessionId){
        return chatService.onlineUsers(sessionId);
    }

    /**
     * 前端获取 sessionId,做为默认登录名
     * @param request
     * @return
     */
    @GetMapping("/sessionId")
    public String sessionId(HttpServletRequest request){
        return request.getSession().getId();
    }
}
