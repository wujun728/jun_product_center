//package com.erp.filter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * 
// */
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Bean
//    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
//        return new AccessTokenVerifyInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//    	// 允许所有请求通过
//        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("swagger-ui.html")
////                .addResourceLocations("classpath:/META-INF/resources/");
////
////        registry.addResourceHandler("/webjars/**")
////                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//}
