package com.itcqm.cake21;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@MapperScan("com.itcqm.cake21.mapper")
@SpringBootApplication
public class SpringBootUniapp21cakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUniapp21cakeApplication.class, args);
    }

}
