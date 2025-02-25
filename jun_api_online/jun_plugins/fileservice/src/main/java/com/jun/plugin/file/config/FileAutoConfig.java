package com.jun.plugin.file.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@Configuration
@ComponentScan(basePackages = "com.jun.plugin.file")
@MapperScan(basePackages = "com.jun.plugin.file.mapper")
public class FileAutoConfig {
}
