package cc.mrbird.security.config;

import cc.mrbird.common.domain.FebsConstant;
import cc.mrbird.security.code.ValidateCodeGenerator;
import cc.mrbird.security.code.img.ImageCodeFilter;
import cc.mrbird.security.code.img.ImageCodeGenerator;
import cc.mrbird.security.code.sms.DefaultSmsSender;
import cc.mrbird.security.code.sms.SmsCodeFilter;
import cc.mrbird.security.code.sms.SmsCodeSender;
import cc.mrbird.security.handler.FebsAuthenticationAccessDeniedHandler;
import cc.mrbird.security.handler.FebsAuthenticationFailureHandler;
import cc.mrbird.security.handler.FebsAuthenticationSucessHandler;
import cc.mrbird.security.handler.FebsLogoutHandler;
import cc.mrbird.security.properties.FebsSecurityProperties;
import cc.mrbird.security.service.FebsUserDetailService;
import cc.mrbird.security.session.FebsExpiredSessionStrategy;
import cc.mrbird.security.session.FebsInvalidSessionStrategy;
import cc.mrbird.security.xss.XssFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * security 配置中心
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FebsSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FebsAuthenticationSucessHandler febsAuthenticationSucessHandler;

    @Autowired
    private FebsAuthenticationFailureHandler febsAuthenticationFailureHandler;

    @Autowired
    private FebsSecurityProperties febsSecurityProperties;

    @Autowired
    private FebsSmsCodeAuthenticationSecurityConfig febsSmsCodeAuthenticationSecurityConfig;

    @Autowired
    private FebsUserDetailService febsUserDetailService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpringSocialConfigurer febsSocialSecurityConfig;

    // spring security自带的密码加密工具类
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 处理 rememberMe 自动登录认证
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(febsSecurityProperties.getAnonResourcesUrl(),",");

        ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
        imageCodeFilter.setAuthenticationFailureHandler(febsAuthenticationFailureHandler);
        imageCodeFilter.setSecurityProperties(febsSecurityProperties);
        imageCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(febsAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(febsSecurityProperties);
        smsCodeFilter.setSessionRegistry(sessionRegistry());
        smsCodeFilter.afterPropertiesSet();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()) // 权限不足处理器
             .and()
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 短信验证码校验
                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加图形证码校验过滤器
                .formLogin() // 表单方式
                .loginPage(febsSecurityProperties.getLoginUrl()) // 未认证跳转 URL
                .loginProcessingUrl(febsSecurityProperties.getCode().getImage().getLoginProcessingUrl()) // 处理登录认证 URL
                .successHandler(febsAuthenticationSucessHandler) // 处理登录成功
                .failureHandler(febsAuthenticationFailureHandler) // 处理登录失败
            .and()
                .rememberMe() // 添加记住我功能
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(febsSecurityProperties.getRememberMeTimeout()) // rememberMe 过期时间，单为秒
                .userDetailsService(febsUserDetailService) // 处理自动登录逻辑
            .and()
                .sessionManagement() // 配置 session管理器
                .invalidSessionStrategy(invalidSessionStrategy()) // 处理 session失效
                .maximumSessions(febsSecurityProperties.getSession().getMaximumSessions()) // 最大并发登录数量
                .expiredSessionStrategy(new FebsExpiredSessionStrategy()) // 处理并发登录被踢出
                .sessionRegistry(sessionRegistry()) // 配置 session注册中心
            .and()
            .and()
                .logout() // 配置登出
                .addLogoutHandler(logoutHandler()) // 配置登出处理器
                .logoutUrl(febsSecurityProperties.getLogoutUrl()) // 处理登出 url
                .logoutSuccessUrl("/") // 登出后跳转到 /
                .deleteCookies("JSESSIONID") // 删除 JSESSIONID
            .and()
                .authorizeRequests() // 授权配置
                .antMatchers(anonResourcesUrl).permitAll() // 免认证静态资源路径
                .antMatchers(
                        febsSecurityProperties.getLoginUrl(), // 登录路径
                        FebsConstant.FEBS_REGIST_URL, // 用户注册 url
                        febsSecurityProperties.getCode().getImage().getCreateUrl(), // 创建图片验证码路径
                        febsSecurityProperties.getCode().getSms().getCreateUrl(), // 创建短信验证码路径
                        febsSecurityProperties.getSocial().getSocialRedirectUrl(), // 重定向到社交账号注册（绑定）页面路径
                        febsSecurityProperties.getSocial().getSocialBindUrl(), // 社交账号绑定 URL
                        febsSecurityProperties.getSocial().getSocialRegistUrl() // 注册并绑定社交账号 URL
                ).permitAll() // 配置免认证路径
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
            .and()
                .csrf().disable()
                .apply(febsSmsCodeAuthenticationSecurityConfig) // 添加短信验证码认证流程
            .and()
                .apply(febsSocialSecurityConfig); // social 配置
    }


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(febsSecurityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsSender();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // 使用 javaconfig 的方式配置是为了注入 sessionRegistry
    @Bean
    public FebsAuthenticationSucessHandler febsAuthenticationSucessHandler() {
        FebsAuthenticationSucessHandler authenticationSucessHandler = new FebsAuthenticationSucessHandler();
        authenticationSucessHandler.setSessionRegistry(sessionRegistry());
        return authenticationSucessHandler;
    }

    // 配置登出处理器
    @Bean
    public LogoutHandler logoutHandler(){
        FebsLogoutHandler febsLogoutHandler = new FebsLogoutHandler();
        febsLogoutHandler.setSessionRegistry(sessionRegistry());
        return febsLogoutHandler;
    }

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(){
        FebsInvalidSessionStrategy febsInvalidSessionStrategy = new FebsInvalidSessionStrategy();
        febsInvalidSessionStrategy.setSecurityProperties(febsSecurityProperties);
        return febsInvalidSessionStrategy;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new FebsAuthenticationAccessDeniedHandler();
    }

    /**
     * XssFilter Bean
     */
    @Bean
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

}
