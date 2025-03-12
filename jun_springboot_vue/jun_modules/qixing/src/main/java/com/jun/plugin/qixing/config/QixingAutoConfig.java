package com.jun.plugin.qixing.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.qixing")
@MapperScan(basePackages = "com.jun.plugin.qixing.mapper")
public class QixingAutoConfig {
}
