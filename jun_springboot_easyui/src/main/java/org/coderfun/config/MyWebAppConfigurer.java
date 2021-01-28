package org.coderfun.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

	Logger logger=LoggerFactory.getLogger(MyWebAppConfigurer.class);
	
	@Autowired
	WebRes webRes;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	String pathPattern = webRes.getPath()+ "**";
    	logger.info("static resource mapping [{}] -> [{}]", pathPattern,"file:"+webRes.getAbsolutePath());
        registry.addResourceHandler(pathPattern).addResourceLocations("file:"+webRes.getAbsolutePath());        
        super.addResourceHandlers(registry);
    }
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        
        super.addViewControllers( registry );
    } 
}