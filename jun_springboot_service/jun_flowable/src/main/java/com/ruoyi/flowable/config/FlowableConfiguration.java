package com.ruoyi.flowable.config;

import com.ruoyi.flowable.core.web.FlowableWebFilter;
import com.ruoyi.flowable.service.definition.BpmFormService;
import com.ruoyi.flowable.service.definition.impl.BpmFormServiceImpl;
import com.ruoyi.flowable.service.task.BpmTaskService;
import com.ruoyi.flowable.service.task.impl.BpmTaskServiceImpl;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class FlowableConfiguration {

    /**
     * 参考 {@link org.flowable.spring.boot.FlowableJobConfiguration} 类，创建对应的 AsyncListenableTaskExecutor Bean
     *
     * 如果不创建，会导致项目启动时，Flowable 报错的问题
     */
    @Bean
    public AsyncListenableTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("flowable-task-Executor-");
        executor.setAwaitTerminationSeconds(30);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAllowCoreThreadTimeOut(true);
        executor.initialize();
        return executor;
    }

    /**
     * 配置 flowable Web 过滤器
     */
    @Bean
    public FilterRegistrationBean<FlowableWebFilter> flowableWebFilter() {
        FilterRegistrationBean<FlowableWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FlowableWebFilter());
        // Spring Security Filter 默认为 -100，可见 org.springframework.boot.autoconfigure.security.SecurityProperties 配置属性类
        // 需要保证在 Spring Security 过滤后面
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }

    @Bean(name = "flowableTaskService")
    public TaskService flowableTaskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }
    @Bean(name = "bpmFormService")
    public BpmFormService bpmFormService() {
        return new BpmFormServiceImpl();
    }
}
