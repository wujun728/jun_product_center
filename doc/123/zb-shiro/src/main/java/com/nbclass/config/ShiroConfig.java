package com.nbclass.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nbclass.shiro.MyShiroRealm;
import com.nbclass.shiro.ShiroService;
import com.nbclass.shiro.filter.KickoutSessionControlFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private ShiroService shiroService;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    private final int EXPIRE = 1800;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        /*记住我*/
        securityManager.setRememberMeManager(rememberMeManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        // 配置缓存过期时间
        redisManager.setTimeout(timeout);
        if(StringUtils.isNotBlank(password)){
            redisManager.setPassword(password);
        }
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 配置缓存的话要求放在session里面的实体类必须有个id标识 注：这里id为用户表中的主键，否-> 报：User must has getter for field: xx
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认5；比如5的意思是同一个用户允许最多同时五个人登录；
        kickoutSessionControlFilter.setMaxSession(5);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }

}
