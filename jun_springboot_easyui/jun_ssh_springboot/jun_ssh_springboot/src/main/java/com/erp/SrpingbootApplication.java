package com.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
//@ImportResource(locations = {"classpath:config/kaptcha.xml"})  //for beans
//@EntityScan(basePackages = {"com.erp.model","com.erp.entity"})   //mybatis
//@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = "com.erp")
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class SrpingbootApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SrpingbootApplication.class);
    } 

    public static void main(String[] args) {
        SpringApplication.run(SrpingbootApplication.class, args);
    }
    
}