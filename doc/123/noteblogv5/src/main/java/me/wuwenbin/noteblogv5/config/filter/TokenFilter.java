package me.wuwenbin.noteblogv5.config.filter;

import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于对前端用户登录作用的过滤，例如评论
 *
 * @author wuwenbin
 */
@Slf4j
public class TokenFilter extends BaseController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User sessionUser = getSessionUser(request);
        if (sessionUser == null) {
            if (isAjaxRequest(request)) {
                handleAjaxRequest(request, response);
            } else {
                response.sendRedirect("/login");
            }
            log.info("未登录用户");
            return false;
        } else {
            log.info("已登录用户，用户昵称（账号）：「{}({})」", sessionUser.getNickname(), sessionUser.getUsername());
            return true;
        }
    }
}
