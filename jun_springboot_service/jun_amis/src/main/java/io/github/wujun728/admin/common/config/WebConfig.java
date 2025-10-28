package io.github.wujun728.admin.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private AdminInterceptor adminInterceptor;
    @Resource
    private AllInterceptor allInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(adminInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(
                "/admin/css/**",
                "/admin/js/**",
                "/admin/images/**",
                "/admin/fonts/**",
                "/doc/**",
                "/test/**",
                "/assets/**",
                "/admin/lyear_pages_login.html",
                "/admin/login.html",
                "/login.html",
                "/index.html",
                "/**/*.html",
                "/admin/choose_enterprise.html",
                "/captcha.png",
                "/admin/user/login",
                "/admin/user/postLogin",
                "/admin/user/login/*",
                "/admin/user/logout",
                "/amis/**",
                "/amis-editor/**",
                "/admin/article/**",
                "/admin/error",

                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/openApi/**",
                "/v2/api-docs/**",

                "/*/*.json"
                );

        InterceptorRegistration allInterceptorRegistration = registry.addInterceptor(allInterceptor);
        allInterceptorRegistration.addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    /**
     * 注册Filter
     */
    @Bean
    public FilterRegistrationBean xssSpringFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new XssSpringFilter());
        bean.addUrlPatterns("/api/*");
        return bean;
    }
}
