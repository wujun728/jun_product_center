package org.myframework.quartz.scheduler;

import java.util.Properties;

import javax.sql.DataSource;

public class SchedulerFactoryBean extends org.springframework.scheduling.quartz.SchedulerFactoryBean{

	public SchedulerFactoryBean() {

	}

	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		//设置数据源，则意味使用quartz集群模式
		Properties quartzProperties = new Properties();
		quartzProperties.put("org.quartz.jobStore.isClustered", true);
		super.setQuartzProperties(quartzProperties);
	}
	
	

}
