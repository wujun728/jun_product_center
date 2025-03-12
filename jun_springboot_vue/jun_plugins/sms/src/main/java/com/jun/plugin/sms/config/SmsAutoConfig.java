package com.jun.plugin.sms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.sms")
@MapperScan(basePackages = "com.jun.plugin.sms.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.sms.compoent"})
public class SmsAutoConfig {
}
