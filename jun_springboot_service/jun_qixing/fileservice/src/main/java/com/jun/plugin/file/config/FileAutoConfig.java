package com.jun.plugin.file.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.file")
@MapperScan(basePackages = "com.jun.plugin.file.mapper")
public class FileAutoConfig {
}
