// 由于magic-api依赖已被移除，此配置类不再需要
//package io.github.wujun728.admin.common.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.ServletContext;
//
///**
// * MagicAPI配置类
// * 解决MagicSwaggerConfiguration中缺少ServletContext的问题
// */
//@Configuration
//public class MagicApiConfig {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Bean
//    public ServletContext servletContext() {
//        return webApplicationContext.getServletContext();
//    }
//}