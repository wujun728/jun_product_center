package com.pearadmin.secure.process;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Describe: 自定义 Security 同账号多端登录挤下线 跳转地址
 * Author: John Ming
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureSessionExpiredHandler implements SessionInformationExpiredStrategy {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), "/login?abnormalout=1");
    }
}
