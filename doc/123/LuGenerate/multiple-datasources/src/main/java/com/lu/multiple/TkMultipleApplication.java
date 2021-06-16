package com.lu.multiple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program LuGenerate
 * @description: tkmybatis 多数据源
 * @author: zhanglu
 * @create: 2020-06-28 19:11:00
 */
@SpringBootApplication
public class TkMultipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMultipleApplication.class, args);
    }

}
