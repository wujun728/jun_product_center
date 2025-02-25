package com.jun.plugin.generator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.generator")
@MapperScan(basePackages = "com.jun.plugin.generator.mapper")
@Configuration
public class DevtoolsAutoConfig {
}
