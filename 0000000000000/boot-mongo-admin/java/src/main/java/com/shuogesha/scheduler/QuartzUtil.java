package com.shuogesha.scheduler;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import com.shuogesha.platform.entity.ScheduleJob;

@Component
public class QuartzUtil {
	
	public static String JOB_NAME="name";

	@Resource
	private Scheduler scheduler;

	/**
	 * 增加一个job
	 *
	 * @param jobDetail    任务实现类
	 * @param jobName      任务名称
	 * @param jobGroupName 任务组名
	 * @param jobTime      时间表达式 (这是每隔多少秒为一次任务)
	 * @param jobTimes     运行的次数 （<0:表示不限次数）
	 */
	public void addJob(ScheduleJob bean) {
		try { 
			JobKey jobKey = new JobKey(bean.getId(),bean.getGroupName());
			JobDetailImpl jobDetail = new JobDetailImpl();
			jobDetail.setKey(jobKey);
			jobDetail.setName(bean.getName());
			jobDetail.setJobClass((Class<? extends Job>) Class.forName(bean.getClassName()));  
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(QuartzUtil.JOB_NAME, bean.getName());
			jobDetail.setJobDataMap(jobDataMap);
			
			Trigger trigger = new CronTriggerImpl(bean.getName(),bean.getGroupName(),bean.getName(),bean.getGroupName(), bean.getCronExpression()).getTriggerBuilder()
					.startNow().build();
			scheduler.scheduleJob(jobDetail,trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除任务一个job
	 *
	 * @param jobName      任务名称
	 * @param jobGroupName 任务组名
	 */
	public void deleteJob(ScheduleJob bean) {
		try { 
			JobKey jobKey = new JobKey(bean.getName(),bean.getGroupName()); 
			
			System.out.println(scheduler.deleteJob(jobKey)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
