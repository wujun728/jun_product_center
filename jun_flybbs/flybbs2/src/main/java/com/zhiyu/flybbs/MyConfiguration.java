package com.zhiyu.flybbs;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhiyu.flybbs.interceptor.MyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**
 * @author Wujun
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(MyConfiguration.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("add interceptors");
        registry.addInterceptor(new MyInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/layui/**", "/res/**");
    }
}
