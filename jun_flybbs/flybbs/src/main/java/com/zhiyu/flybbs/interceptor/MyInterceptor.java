package com.zhiyu.flybbs.interceptor;

import com.zhiyu.flybbs.SessionConstant;
import com.zhiyu.flybbs.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiyu
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);
    private static final List<String> ACCESS_WITHOUT_LOGIN = new ArrayList<>();

    static {
//        ACCESS_WITHOUT_LOGIN.add("/home");
        ACCESS_WITHOUT_LOGIN.add("/user/login");
        ACCESS_WITHOUT_LOGIN.add("/user/register");
        ACCESS_WITHOUT_LOGIN.add("/api/user/logon");
        ACCESS_WITHOUT_LOGIN.add("/api/user/signIn");
        ACCESS_WITHOUT_LOGIN.add("/api/user/logout");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        User user = (User) request.getSession().getAttribute(SessionConstant.USER);
        String path = request.getServletPath();
        log.info("请求servletPath为：" + path);
        if (user == null && !canAccess(path)) {
            sendRedirect(request, response);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder(request.getServletPath());
        if (StringUtils.isNotEmpty(request.getQueryString())) {
            sb.append("?").append(request.getQueryString());
        }
        StringBuilder redirectPath = new StringBuilder("/user/login?redirect=").append(sb);
        try {
            log.info(redirectPath.toString());
            response.sendRedirect(redirectPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean canAccess(String requestPath) {
        if ("/".equals(requestPath)) {
            return true;
        }
        for (String path : ACCESS_WITHOUT_LOGIN) {
            if (requestPath.contains(path)) {
                return true;
            }
        }
        return false;
    }
}
