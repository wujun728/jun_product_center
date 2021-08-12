package com.shuogesha.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyJobFactory extends org.springframework.scheduling.quartz.SpringBeanJobFactory {

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	/**
	 * 
	 * 这里覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。
	 */
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		beanFactory.autowireBean(jobInstance);
		return jobInstance;
	}

	@Bean(name = "scheduler")
	public Scheduler scheduler() throws SchedulerException {
		SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
		Scheduler sched = gSchedulerFactory.getScheduler();
		sched.setJobFactory(this);
		return sched;
	}

}
