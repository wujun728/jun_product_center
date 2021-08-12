package com.pearadmin.secure;

import com.pearadmin.secure.process.SecureLogoutHandler;
import com.pearadmin.secure.support.SecurePermissionSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import javax.annotation.Resource;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Describe: Security 扩展配置
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 */
@Configuration
public class SecureExtendConfiguration {

    /**
     * 注解权限
     */
    @Resource
    private SecurePermissionSupport securityPermissionEvaluator;

    /**
     * Describe: 加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注册SessionRegistry
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 注册HttpSessionEventPublisher，发布HttpSessionEvent
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * Describe: 自定义权限注解实现
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(securityPermissionEvaluator);
        return handler;
    }

    /**
     * thymeleaf security 别名注册，方便前端使用
     */
    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    /**
     * 注册自定义的LogoutHandler
     *
     * @param httpSessionEventPublisher
     * @return SecurityLogoutHandler
     */
    @Bean
    public SecureLogoutHandler securityLogoutHandler(HttpSessionEventPublisher httpSessionEventPublisher) {
        return new SecureLogoutHandler(httpSessionEventPublisher);
    }

    /**
     * 注册ScheduledThreadPoolExecutor，进行在线用户用户检测，清除过期Session
     */
    @Bean
    public ScheduledThreadPoolExecutor manageSessionThreadPool() {
        return new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setName("removeSession");
            t.setDaemon(true);
            return t;
        });
    }
}
