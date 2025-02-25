package com.jun.plugin.shiro;

import com.jun.plugin.shiro.compoent.CustomAccessControlFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.jun.plugin.system.common.comfig.FileUploadProperties;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Configuration
//@EnableConfigurationProperties(FileUploadProperties.class)
public class ShiroConfig {

//    @Resource
//    private FileUploadProperties fileUploadProperties;

    @Value("${shiro.enable}")
    private boolean shiroEnable;

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public CustomHashedCredentialsMatcher customHashedCredentialsMatcher() {
        return new CustomHashedCredentialsMatcher();
    }

    /**
     * 创建realm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(customHashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(customRealm());
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        //整个shiro执行过程： 过滤器、认证、授权

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        if(shiroEnable){
            //设置安全管理器
            LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
            //用来校验token
            filtersMap.put("token", new CustomAccessControlFilter());
            //filtersMap.put("jwt", new JwtFilter()); // TODO JWT认证，Wujun 添加自己的过滤器并且取名为jwt
            shiroFilterFactoryBean.setFilters(filtersMap);

            Map<String, String> filterRuleMap = new LinkedHashMap<>();
            // 配置不会被拦截的链接 顺序判断
            filterRuleMap.put("/*.html", "anon");
            filterRuleMap.put("/sys/user/token", "anon");
            filterRuleMap.put("/sys/user/login", "anon");
            filterRuleMap.put("/sys/getVerify", "anon");
            filterRuleMap.put("/sys/checkVerify", "anon");
            filterRuleMap.put("/sysDict/getType/*", "anon");
            filterRuleMap.put("/sms/sendCode", "anon");
            filterRuleMap.put("/index/**", "anon");
            filterRuleMap.put("/admin/**", "anon");
            filterRuleMap.put("/flow/**", "anon");
            filterRuleMap.put("/ext/**", "anon");
            filterRuleMap.put("/configInfo/**", "anon");
            filterRuleMap.put("/pages/*.html", "anon");
            filterRuleMap.put("/**/*.ttf", "anon");
            filterRuleMap.put("/**/*.wottf", "anon");
            filterRuleMap.put("/assets/**", "anon");
            filterRuleMap.put("/prj/**", "anon");
            filterRuleMap.put("/**/*.html", "anon");
            filterRuleMap.put("/**/*.js", "anon");
            filterRuleMap.put("/**/*.css", "anon");
            filterRuleMap.put("/**/*.json", "anon");
            filterRuleMap.put("/static/**", "anon");
            filterRuleMap.put("/rest/**", "anon");
            filterRuleMap.put("/doc.html", "anon");
            filterRuleMap.put("/swagger-resources/**", "anon");
            filterRuleMap.put("/v2/api-docs", "anon");
            filterRuleMap.put("/v2/api-docs-ext", "anon");
            filterRuleMap.put("/webjars/**", "anon");
            filterRuleMap.put("/druid/**", "anon");
            filterRuleMap.put("/favicon.ico", "anon");
            filterRuleMap.put("/captcha.jpg", "anon");
            filterRuleMap.put("/csrf", "anon");
            filterRuleMap.put("/public/**", "anon");
            //文件上传可直接访问
//        filterRuleMap.put(fileUploadProperties.getAccessUrl(), "anon");
            filterRuleMap.put("/images/**", "anon");
            filterRuleMap.put("/js/**", "anon");
            filterRuleMap.put("/layui/**", "anon");
            filterRuleMap.put("/css/**", "anon");
            filterRuleMap.put("/layui-ext/**", "anon");
            filterRuleMap.put("/api/**", "anon");
            filterRuleMap.put("/lib/**", "anon");
            filterRuleMap.put("/component/**", "anon");
            filterRuleMap.put("/**", "token,authc");// TODO 所有请求通过我们自己的JWT Filter
            //filterRuleMap.put("/**", "token,authc,jwt");// TODO 所有请求通过我们自己的JWT Filter
            shiroFilterFactoryBean.setLoginUrl("/login.html");
            shiroFilterFactoryBean.setUnauthorizedUrl("/401"); // 可设置无需鉴权的路径
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        }
        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}

