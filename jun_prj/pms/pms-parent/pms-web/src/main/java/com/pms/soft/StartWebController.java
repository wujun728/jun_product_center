package com.pms.soft;

import javax.sql.DataSource;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.pms.soft.filter.PageFilter;
import com.pms.soft.interceptor.LoginInterceptor;


 
@SpringBootApplication //@SpringBootApplication = @EnableAutoConfiguration+@ComponentScan
//@ComponentScan("com.pms.soft.controller") 扫描该表下所有controller，如果启动类放在父包下。可以不适用该注解
public class StartWebController extends WebMvcConfigurerAdapter{

	@Autowired
    private Environment env;
	
	
	public static void main(String[] args){
		SpringApplication.run(StartWebController.class, args);
	}
	
	
	/**
	 * 全局错误页面处理
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/pages/error/401.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/pages/error/404.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/pages/error/500.html");
	            container.addErrorPages(error401Page, error404Page, error500Page);
	        }
	    };
	}
	 
	/**
	 * filter过滤器
	 * @return
	 */
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new PageFilter());
        registration.addUrlPatterns("/*");
        /**
         * 再有一个过滤器的话，可以设置成 registration.setOrder(Integer.MAX_VALUE - 1)
         * spring boot 会按照order值的大小，从小到大的顺序来依次过滤
         */
        registration.setOrder(Integer.MAX_VALUE);
        
        return registration;
    }
	
	
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);//当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的
        configurer.setUseTrailingSlashMatch(false);
    }
    
    /**
     * 配置spring boot 默认请求页面
     */
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/login.html" );
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
    
    /**
	* 拦截器
	* @param registry
	*/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("********************进入拦截器****************");
		///InterceptorRegistration addInterceptor = registry.addInterceptor(new LoginInterceptor());
		//addInterceptor.addPathPatterns("/**");//添加拦截规则
		//addInterceptor.excludePathPatterns("/login"); //排除拦截
		super.addInterceptors(registry);
	}
    
	/**
	 * 数据源配置
	 * destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
	 * @return
	 */
    @Bean(destroyMethod =  "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setInitialSize(2);//初始化时建立物理连接的个数
        dataSource.setMaxActive(20);//最大连接池数量
        dataSource.setMinIdle(0);//最小连接池数量
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }
    
   
}
