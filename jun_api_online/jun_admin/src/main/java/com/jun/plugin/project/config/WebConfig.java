package com.jun.plugin.project.config;


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * spring mvc配置类
 * sunxh 2023/2/26
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Resource
//    private MyWebInterceptor myWebInterceptor;
//    @Resource
//    private LoggerInterceptor loggerInterceptor;

    /**
     * 资源跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //super.addCorsMappings(registry);
        //registry.addMapping("/api/**").allowedOrigins("*").maxAge(3600);
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }

    /**
     * 请求拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        //loggerInterceptor.addInterceptor(registry);
        //myWebInterceptor.addInterceptor(registry);
//        registry.addInterceptor(new RepeatSubmitInterceptor() {
//                    @Override
//                    public boolean isRepeatSubmit(HttpServletRequest request) {
//                        return false;
//                    }
//                }).addPathPatterns("/**")
//                .excludePathPatterns("/emp/toLogin","/emp/login","/js/**","/css/**","/images/**");
    }

    /**
     * @description: 访问静态文件,发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     * @date: 2021/4/15
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
//        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Global.getProfile() + "/");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","classpath:/static2/","classpath:/static3/","classpath:/templates/","classpath:/templates3/","classpath:/views/");
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/static2/","classpath:/static3/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        /** 文件下载映射配置,同下 */
//        registry.addResourceHandler(fileUploadProperties.getAccessUrl()).addResourceLocations("file:" + fileUploadProperties.getPath());
    }

    /**
     * 定义全局默认时间序列化
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .simpleDateFormat(DATE_TIME_FORMAT)
                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    /**
     * 视图配置
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //super.configureViewResolvers(registry);
        registry.viewResolver(resourceViewResolver());
        /*registry.jsp("/WEB-INF/jsp/",".jsp");*/
    }

    @Bean
    public InternalResourceViewResolver resourceViewResolver()
    {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //请求视图文件的前缀地址
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        //请求视图文件的后缀
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }


    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("forward:" + indexUrl);
        registry.addViewController("/").setViewName("forward:" + "login.html");
    }
}

