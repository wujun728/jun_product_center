package com.du.lin.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {
	/**
	 * 安全管理器
	 * @param rememberMeManager
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(CookieRememberMeManager rememberMeManager){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRememberMeManager(rememberMeManager);
		securityManager.setRealm(this.shiroDbRealm());
		return securityManager;
	}
	
	
	@Bean
	public ShiroDbRealm shiroDbRealm(){
		return new ShiroDbRealm();
	}
	/**
	 * rememberMe管理器
	 * @param rememberMeCookie
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie){
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCookie(rememberMeCookie);
		return rememberMeManager;
	}
	/**
	 * 记住密码Cookie
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 *7);
		return cookie;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		//设置登录界面
		shiroFilter.setLoginUrl("/login");
		//沒有权限返回界面
		shiroFilter.setUnauthorizedUrl("/error");
		//设置登录成功界面
		shiroFilter.setSuccessUrl("/success");
        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         */
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("/login", "anon");
		map.put("/login2", "anon");
		map.put("/logout" , "anon");
		map.put("/", "anon");
		map.put("/static/**", "anon");		
		map.put("/**", "anon");
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}
	
	
	
}
