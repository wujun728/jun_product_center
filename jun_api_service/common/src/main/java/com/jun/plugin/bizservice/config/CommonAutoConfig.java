package com.jun.plugin.bizservice.config;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@Configuration
@ComponentScan(basePackages = "com.jun.plugin.bizservice")
//@MapperScan(basePackages = "com.jun.plugin.bizservice.mapper")
public class CommonAutoConfig {
}
