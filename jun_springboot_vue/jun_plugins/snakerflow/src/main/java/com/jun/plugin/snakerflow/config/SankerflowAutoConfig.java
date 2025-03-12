package com.jun.plugin.snakerflow.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.snakerflow")
@MapperScan(basePackages = "com.jun.plugin.snakerflow.**.mapper")
public class SankerflowAutoConfig {
}
