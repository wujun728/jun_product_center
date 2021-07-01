package com.framework.security.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.framework.common.ResultJson;
import com.framework.common.utils.ServletUtils;
import com.projectm.config.WebSocketServer;

import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security.handler
 * @description: 注销
 * @author: lzd
 * @create: 2020-06-26 13:24
 **/
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	 @Resource
     WebSocketServer webSocketServer;
  
     @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        WebSocketServer.logOut(request.getSession().getId());
        
        ServletUtils.renderString(response, ResultJson.ok().toString());
    }
}
