package com.pearadmin;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Pear Admin Fast 启动类
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
public class EntranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceApplication.class, args);
    }

}
