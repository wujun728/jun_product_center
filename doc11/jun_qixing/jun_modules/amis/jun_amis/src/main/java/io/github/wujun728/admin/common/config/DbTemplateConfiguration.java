package io.github.wujun728.admin.common.config;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class DbTemplateConfiguration {
    /*@Bean("dbVelocityEngine")
    public VelocityEngine velocityEngine(){
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        // ve.setProperty(Velocity.OUTPUT_ENCODING, charsetStr);
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, false); // 不使用缓存
        ve.setProperty(Velocity.RESOURCE_LOADER, "str");
        ve.setProperty("str.resource.loader.class", DbStringResourceLoader.class.getName());
        ve.init();
        return ve;
    }*/

    @Bean("dbVelocityEngine")
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        //props.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "/path/to/templates");
        props.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_CACHE, String.valueOf(false)); // 不使用缓存
        props.setProperty(VelocityEngine.RESOURCE_LOADER, "str");
        props.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
//        props.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
//        props.setProperty("str.resource.loader.class", DbStringResourceLoader.class.getName());
        velocityEngine.init(props);
        return velocityEngine;
    }

    /*@Bean
    public VelocityEngine velocityEngine(){
        VelocityEngine ve =new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        return ve;
    }  */

}
