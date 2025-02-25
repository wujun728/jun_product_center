package com.jun.plugin.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@Configuration
@ComponentScan(basePackages = "com.jun.plugin.shiro")
@MapperScan(basePackages = "com.jun.plugin.shiro.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.shiro.compoent"})
public class ShiroAutoConfig {
}
