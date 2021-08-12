package com.shuogesha;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.shuogesha.front.filter.FrontInterceptor;
import com.shuogesha.platform.filter.AdminInterceptor;

@Configuration
@EnableAspectJAutoProxy
public class MvcInterceptorConfig extends WebMvcConfigurationSupport {

	@Value("${file.staticAccessPath}")
	private String staticAccessPath;
	@Value("${file.uploadFolder}")
	private String uploadFolder; 
//	@Autowired
//	private AppInterceptor appInterceptor;// appid拦截器
//	@Autowired
//	private UserAccessApiInterceptor userAccessApiInterceptor;// 用户token的拦截器
	
	@Autowired
	private FrontInterceptor frontInterceptor;
	@Autowired
	private AdminInterceptor adminInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(adminInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login",
				"/api/logout", "/upload/**","/favicon.ico","/swagger-ui.html","/webjars/**"); 
//		
//		registry.addInterceptor(appInterceptor).addPathPatterns("/app/**");
//		registry.addInterceptor(userAccessApiInterceptor).addPathPatterns("/app/**");
//		
		registry.addInterceptor(frontInterceptor).addPathPatterns("/**").excludePathPatterns("/favicon.ico", "/error",
				"/upload/**","/favicon.ico","/swagger-ui.html","/webjars/**");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");// 静态资源路径 css,js,img等
		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");// 视图
		registry.addResourceHandler("upload/**").addResourceLocations("file:" + uploadFolder);
		//接口文档swagger
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	private CorsConfiguration corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
//		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setMaxAge(3600L);
// 		corsConfiguration.addAllowedOrigin("http://localhost:8080");
		  String []  allowDomain= {"*","http://127.0.0.1:8080","http://localhost:8080"};
  		corsConfiguration.setAllowedOriginPatterns(Arrays.asList(allowDomain));
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig());
		return new CorsFilter(source);
	}
	/**
	 * 解决精度丢失问题
	 */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
     	for (HttpMessageConverter<?> c : converters) {
            if (c instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) c;
                ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true); 
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                // 注册Long专用的json转换器
                SimpleModule module = new SimpleModule();
                //修复Long类型太长，丢失精度问题
                module.addSerializer(Long.class, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
//                module.addSerializer(Long.TYPE, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
                module.addSerializer(BigDecimal.class, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
                 objectMapper.registerModule(module);
            }
        }
    } 

}