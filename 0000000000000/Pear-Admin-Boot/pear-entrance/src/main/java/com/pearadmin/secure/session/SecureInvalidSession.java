package com.pearadmin.secure.session;

import com.pearadmin.common.web.session.HttpSessionContextHolder;
import com.pearadmin.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Describe: Security 在线用户监测任务
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 */
@Slf4j
@Component
public class SecureInvalidSession implements CommandLineRunner {

    @Resource(name = "manageSessionThreadPool")
    private ScheduledThreadPoolExecutor manageSessionThreadPool;

    @Resource
    private SessionRegistry sessionRegistry;

    @Value("${server.servlet.session.timeout}")
    private Duration duration;

    @Override
    public void run(String... args) throws Exception {
        manageSessionThreadPool.scheduleWithFixedDelay(() -> {
            // 从sessionRegistry中获取所有的用户信息
            List<Object> principals = sessionRegistry.getAllPrincipals();
            for (Object principal : principals) {
                SysUser userDetails = (SysUser) principal;
                // 获取用户最近一次的登录时间
                LocalDateTime lastTime = userDetails.getLastTime();
                // 获取session的目标过期时间
                LocalDateTime expiredTime = lastTime.plusSeconds(duration.getSeconds());
                // 若session过期
                if (Duration.between(LocalDateTime.now(), expiredTime).toMinutes() <= 0) {
                    List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(userDetails, false);
                    if (CollectionUtil.isNotEmpty(sessionInformationList)) {
                        for (SessionInformation sessionInformation : sessionInformationList) {
                            // 清除已经过期的session（SessionRegistry）
                            sessionInformation.expireNow();
                            sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                            log.info(String.format("HttpSessionId[%s]------>已从SessionRegistry中移除", sessionInformation.getSessionId()));
                            // 销毁已经过期的session
                            HttpSessionContextHolder.currentSessionContext().getSession(sessionInformation.getSessionId()).invalidate();
                            log.info(String.format("HttpSessionId[%s]------>已从HttpSessionContext中移除", sessionInformation.getSessionId()));
                        }
                    }
                } else {
                    log.info("[在线用户检测] 目前sessionRegistry中，没有已经过期的httpSession");
                }
            }
        }, 60, 10, TimeUnit.SECONDS);
    }
}
