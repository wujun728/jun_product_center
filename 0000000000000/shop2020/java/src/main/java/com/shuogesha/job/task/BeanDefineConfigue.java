package com.shuogesha.job.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.shuogesha.platform.service.ScheduleJobService;

 
@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements
		ApplicationListener<ContextRefreshedEvent> {// ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用
	private static Boolean schedule= false;
	 @Autowired
	 private ScheduleJobService scheduleJobService;

	/**
	 * 当一个ApplicationContext被初始化或刷新触发
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//只执行一次任务调度
		if(!schedule){
			scheduleJobService.init();
			schedule= true;
		} 
		System.out
				.println("定时任务启动================================================");
	}

}