package com.pearadmin.secure;

import com.pearadmin.secure.process.*;
import com.pearadmin.secure.support.SecureCaptchaSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.pearadmin.common.config.proprety.SecurityProperty;
import com.pearadmin.secure.domain.SecureUserDetailsService;
import com.pearadmin.secure.domain.SecureUserTokenService;
import javax.annotation.Resource;

/**
 * Describe: Security 安全配置
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperty.class)
public class SecureConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 配置未登录自定义处理类
     */
    @Resource
    private SecureAuthenticationEntryPoint securityAuthenticationEntryPoint;

    /**
     * 登录成功处理类
     */
    @Resource
    private SecureAuthenticationSuccessHandler securityAccessSuccessHandler;

    /**
     * 登录失败处理类
     */
    @Resource
    private SecureAuthenticationFailureHandler securityAccessFailureHandler;

    /**
     * 退出登录处理类
     */
    @Resource
    private SecureLogoutSuccessHandler securityAccessLogoutHandler;

    /**
     * 没有权限处理类
     */
    @Resource
    private SecureAccessDeniedHandler securityAccessDeniedHandler;

    /**
     * 配置不拦截url
     */
    @Resource
    private SecurityProperty securityProperty;

    /**
     * 实现userservice
     */
    @Resource
    private SecureUserDetailsService securityUserDetailsService;

    /**
     * remember me redis持久化
     */
    @Resource
    private SecureUserTokenService securityUserTokenService;

    /**
     * 自定义验证码验证
     */
    @Resource
    private SecureCaptchaSupport securityCaptchaSupport;

    @Resource
    private SecureSessionExpiredHandler securityExpiredSessionHandler;

    /**
     * 密码加密
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用于统计用户在线
     */
    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    private SecureLogoutHandler securityLogoutHandler;

    @Resource
    private SecureRememberMeHandler rememberMeAuthenticationSuccessHandler;


    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder);
    }


    /**
     * Describe: 配置 Security 控制逻辑
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(securityProperty.getOpenApi()).permitAll()
                // 其他的需要登录后才能访问
                .anyRequest().authenticated()
                .and()
                // 验证码验证类
                .addFilterBefore(securityCaptchaSupport, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                .and()
                .formLogin()
                // 登录页面
                .loginPage("/login")
                // 登录接口
                .loginProcessingUrl("/login")
                // 配置登录成功自定义处理类
                .successHandler(securityAccessSuccessHandler)
                // 配置登录失败自定义处理类
                .failureHandler(securityAccessFailureHandler)
                .and()
                .logout()
                .addLogoutHandler(securityLogoutHandler)
                // 退出登录删除 cookie缓存
                .deleteCookies("JSESSIONID")
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(securityAccessLogoutHandler)
                .and()
                .exceptionHandling()
                // 配置没有权限自定义处理类
                .accessDeniedHandler(securityAccessDeniedHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("rememberme-token")
                .authenticationSuccessHandler(rememberMeAuthenticationSuccessHandler)
                .tokenRepository(securityUserTokenService)
                .key(securityProperty.getRememberKey())
                .and()
                .sessionManagement()
                .sessionFixation()
                .migrateSession()
                // 在需要使用到session时才创建session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // 同时登陆多个只保留一个
                .maximumSessions(securityProperty.getMaximum())
                .maxSessionsPreventsLogin(false)
                // 踢出用户操作
                .expiredSessionStrategy(securityExpiredSessionHandler)
                // 用于统计在线
                .sessionRegistry(sessionRegistry);

        // 取消跨站请求伪造防护
        http.csrf().disable();
        // 防止iframe 造成跨域
        http.headers().frameOptions().disable();
    }
}
