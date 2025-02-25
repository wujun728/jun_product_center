package com.jun.plugin.quartz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@Configuration
@ComponentScan(basePackages = "com.jun.plugin.quartz")
@MapperScan(basePackages = "com.jun.plugin.quartz.mapper")
public class QuartzAutoConfig {
}
