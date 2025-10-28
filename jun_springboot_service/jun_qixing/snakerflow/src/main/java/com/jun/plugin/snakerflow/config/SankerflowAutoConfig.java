package com.jun.plugin.snakerflow.config;

import org.mybatis.spring.annotation.MapperScan;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.core.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.snakerflow")
@MapperScan(basePackages = "com.jun.plugin.snakerflow.**.mapper")
@Configuration
public class SankerflowAutoConfig {
    @Bean("snakerTaskService")
    public org.snaker.engine.core.TaskService snakerTaskService(SnakerEngine snakerEngine) {
        return new TaskService();
    }
}
