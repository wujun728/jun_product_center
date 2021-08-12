package com.shuogesha.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shuogesha.common.util.UtilDate;
import com.shuogesha.platform.entity.ScheduleJob;
 
@Component
public class QuartzManager {
	public static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	public static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	/**
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName 任务名
	 * @param cls     任务
	 * @param time    时间设置，参考quartz说明文档
	 * 
	 * @Title: QuartzManager.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-6-26 下午03:47:44
	 * @version V2.0
	 */
	@SuppressWarnings("unchecked")
	public void addJob(String jobName, Class cls, String time, ScheduleJob scheduleJob) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				if (scheduleJob.getStatus().equals("0")) { // 如果是禁用，则不用创建触发器
					return;
				}
				JobDetail jobDetail = null;
				try {
					JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
					// 创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
					jobDetail = JobBuilder.newJob(cls).withIdentity(jobKey).withDescription(UtilDate.getNow()).build();
					// 表达式调度构建器
					CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(time)
							.withMisfireHandlingInstructionDoNothing();
					// 按新的cronExpression表达式构建一个新的trigger
					trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(UtilDate.getNow())
							.withSchedule(schedBuilder).build();
					jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
					// 把trigger和jobDetail注入到调度器
					scheduler.scheduleJob(jobDetail, trigger);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (scheduleJob.getStatus().equals("0")) { // 如果是禁用，从quartz中删除这条任务
					JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
					scheduler.deleteJob(jobKey);
					return;
				}
				String currentCron = trigger.getCronExpression();
				if (!time.equals(currentCron)) { // 说明该任务有变化，需要更新quartz中的对应的记录
					// 表达式调度构建器
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);

					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
							.build();

					// 按新的trigger重新设置job执行
					scheduler.rescheduleJob(triggerKey, trigger);
				}

			}
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 添加一个定时任务
	 * 
	 * @param jobName          任务名
	 * @param jobGroupName     任务组名
	 * @param triggerName      触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass         任务
	 * @param time             时间设置，参考quartz说明文档
	 * 
	 * @Title: QuartzManager.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-6-26 下午03:48:15
	 * @version V2.0
	 */
	@SuppressWarnings("unchecked")
	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
			String time, ScheduleJob scheduleJob) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				if (scheduleJob.getStatus().equals("0")) { // 如果是禁用，则不用创建触发器
					return;
				}
				JobDetail jobDetail = null;
				try {
					JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
					// 创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
					jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(UtilDate.getNow())
							.build();
					// 表达式调度构建器
					CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(time)
							.withMisfireHandlingInstructionDoNothing();
					// 按新的cronExpression表达式构建一个新的trigger
					trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(UtilDate.getNow())
							.withSchedule(schedBuilder).build();
					jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
					// 把trigger和jobDetail注入到调度器
					scheduler.scheduleJob(jobDetail, trigger);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (scheduleJob.getStatus().equals("0")) { // 如果是禁用，从quartz中删除这条任务
					JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
					scheduler.deleteJob(jobKey);
					return;
				}
				String currentCron = trigger.getCronExpression();
				if (!time.equals(currentCron)) { // 说明该任务有变化，需要更新quartz中的对应的记录
					// 表达式调度构建器
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);

					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
							.build();

					// 按新的trigger重新设置job执行
					scheduler.rescheduleJob(triggerKey, trigger);
				}

			}
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 * 
	 * @Title: QuartzManager.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-6-26 下午03:49:21
	 * @version V2.0
	 */
	@SuppressWarnings("unchecked")
	public void modifyJobTime(String jobName, String time) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				Class objJobClass = jobDetail.getJobClass();
				addJob(jobName, objJobClass, time, (ScheduleJob) jobDetail.getJobDataMap().get("scheduleJob"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * 
	 * @Title: QuartzManager.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-6-26 下午03:49:51
	 * @version V2.0
	 */
	public void removeJob(String jobName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);

			scheduler.pauseJob(jobKey);
			scheduler.pauseTrigger(triggerKey);
			;// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(jobKey);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public void pauseJob(String jobName, String jobGroupName) {
		try {
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public void resumeJob(String jobName, String jobGroupName) {
		try {
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 立即执行一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public void runAJobNow(String jobName, String jobGroupName) {
		try {
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private Scheduler scheduler; 
	

}