package com.lu.dynamicaop.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-14 20:58:00
 */
@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "111111");
//        initParams.put("allow", "127.0.0.1");
//        initParams.put("deny", "");
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter(
                "exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
        return filterRegistrationBean;
    }


/* *********************************spring监控配置********************************* */

    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        String[] ps = new String[]{"com.lu.dynamicaop.modular.*.controller.*"};
        pointcut.setPatterns(ps);
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }


}
