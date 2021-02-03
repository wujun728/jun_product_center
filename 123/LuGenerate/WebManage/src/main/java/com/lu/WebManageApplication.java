package com.lu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program LuGenerate
 * @description: web模块对外提供服务
 * @author: zhanglu
 * @create: 2019-12-11 10:50:00
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebManageApplication.class, args);
    }

}

