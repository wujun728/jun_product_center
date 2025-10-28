package com.jun.plugin.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.system")
@MapperScan(basePackages = "com.jun.plugin.system.mapper")
public class SystemAutoConfig {
}
