package com.pearadmin.secure.process;

import com.pearadmin.common.web.session.HttpSessionContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 * Describe: 自定义用户注销处理类
 * Author: Heiky
 * CreateTime: 2020/12/17
 */
@Slf4j
public class SecureLogoutHandler implements LogoutHandler {

    private boolean invalidateHttpSession = true;
    private boolean clearAuthentication = true;

    private HttpSessionEventPublisher httpSessionEventPublisher;

    public SecureLogoutHandler() {
    }

    public SecureLogoutHandler(HttpSessionEventPublisher httpSessionEventPublisher) {
        this.httpSessionEventPublisher = httpSessionEventPublisher;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Assert.notNull(request, "HttpServletRequest required");
        if (httpSessionEventPublisher != null) {
            // 构建要销毁的session
            HttpSessionEvent sessionEvent = new HttpSessionEvent(request.getSession());
            // 发布session销毁事件
            httpSessionEventPublisher.sessionDestroyed(sessionEvent);
        }
        // 销毁session
        if (invalidateHttpSession) {
            HttpSession session = request.getSession(false);
            // 移除HttpSessionContext中的session信息
            HttpSessionContextHolder.currentSessionContext().removeSession(session);
            if (session != null) {
                log.debug("Invalidating session: " + session.getId());
                session.invalidate();
            }
        }
        // 清空Authentication
        if (clearAuthentication) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(null);
        }
        SecurityContextHolder.clearContext();
    }

    public boolean isInvalidateHttpSession() {
        return invalidateHttpSession;
    }

    public void setInvalidateHttpSession(boolean invalidateHttpSession) {
        this.invalidateHttpSession = invalidateHttpSession;
    }

    public void setClearAuthentication(boolean clearAuthentication) {
        this.clearAuthentication = clearAuthentication;
    }

}
