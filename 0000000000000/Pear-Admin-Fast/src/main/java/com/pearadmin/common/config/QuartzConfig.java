package com.pearadmin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Describe: 定 时 任 务 配 置 文 件
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Configuration
public class QuartzConfig {

    @Resource
    private DataSource dataSource;

    /**
     * Describe: 定时任务工厂
     * Param: DataSource
     * Return: ScheduleFactoryBean
     * */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "PearScheduler");
        // 设置为AUTO时使用，默认的实现org.quartz.scheduler.SimpleInstanceGenerator是基于主机名称和时间戳生成。
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // JobStoreTX在每次执行任务后都使用commit或者rollback来提交更改。
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        // 集群配置：如果有多个调度器实体的话则必须设置为true
        prop.put("org.quartz.jobStore.isClustered", "false");
        // 集群配置：检查集群下的其他调度器实体的时间间隔
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        // 设置一个频度(毫秒)，用于实例报告给集群中的其他实例
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "5000");
        // 触发器触发失败后再次触犯的时间间隔
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 数据库表前缀
        prop.put("org.quartz.jobStore.tablePrefix", "schedule_");
        // 从 LOCKS 表查询一行并对这行记录加锁的 SQL 语句
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(prop);
        factory.setSchedulerName("PearScheduler");
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        return factory;
    }
}
