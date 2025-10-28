package com.jun.plugin.aoplog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.aoplog")
@MapperScan(basePackages = "com.jun.plugin.aoplog.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.aoplog.compoent"})
public class LogAutoConfig {
}
