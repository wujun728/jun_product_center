package org.myframework.quartz.scheduler.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.myframework.commons.util.StringUtils;
import org.myframework.commons.util.config.AppConfigUtils;
import org.myframework.quartz.scheduler.dao.JobInfoDao;
import org.myframework.quartz.scheduler.entities.JobInfo;
import org.myframework.quartz.scheduler.exception.FrameworkSchedulerException;
import org.myframework.quartz.scheduler.job.MsgBrokerJob;
import org.myframework.quartz.scheduler.job.QuartzJobFactory;
import org.myframework.quartz.scheduler.job.QuartzJobFactoryDisallowConcurrentExecution;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <ol>
 * 对QUARTZ操作的封装
 * <li>{@link }</li>
 * 
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月24日
 *
 */
@Service("quartzService")
public class QuartzService {
	private static final String JOB_RUN_TYPE_LOCAL = "local";

	private static final Logger logger = LoggerFactory
			.getLogger(QuartzService.class);

	@Autowired
	private Scheduler scheduler;

	@Resource(name = "jobInfoDao")
	private JobInfoDao jobInfoDao;

	/**
	 * 初始化定时任务
	 *
	 */
	@PostConstruct
	public void init() throws Exception {
		String cleanall = AppConfigUtils.getConfig()
				.getString("quartz.jobs.cleanall");

		if ("true".equalsIgnoreCase(cleanall)) {
			clearJobTrigger(null);
		}
		// 获取任务信息数据，并初始化启动
		logger.info("##############START 初始化TBL_QRTZ_JOB_INFO表中 配置的JOB列表...");
		startJob(null);
		logger.info("##############END QUARTZ JOB 列表加载完毕！");
	}

	private void startJob(String domainId) throws Exception {
		List<JobInfo> jobList;
		if (StringUtils.isNullOrBlank(domainId)) {
			jobList = jobInfoDao.findByJobStatus(JobInfo.STATUS_RUNNING);
		} else {
			jobList = jobInfoDao.findByJobStatusAndDomainId(
					JobInfo.STATUS_RUNNING, domainId);
		}
		//初始化JOB...
		for (JobInfo jobInfo : jobList) {
			logger.debug("初始化JOB..." + jobInfo);
			createJobAndTrigger(jobInfo);
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domainId", null);
		param.put("jobStatus", JobInfo.STATUS_RUNNING);

	}

	void clearJobTrigger(String domainId) {
		List<JobInfo> jobs = null;
		if (!StringUtils.isNullOrBlank(domainId))
			jobs = jobInfoDao.findByDomainId(domainId);
		else
			jobs = jobInfoDao.findAll();
		if (jobs != null)
			for (JobInfo jobInfo : jobs) {
				try {
					logger.debug("重置JOB运行时信息..." + jobInfo);
					deleteJob(jobInfo);
				} catch (SchedulerException e) {
					logger.error("重置JOB运行时信息..." + jobInfo);
				}
			}
	}

	/**
	 * 添加定时任务到quartz服务器
	 * 
	 * @throws SchedulerException
	 *
	 */
	public void createJobAndTrigger(JobInfo jobInfo) throws Exception {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(MsgBrokerJob.class)
					.withIdentity(jobInfo.getJobName(),
							jobInfo.getJobGroupName())
					.withDescription(jobInfo.getDescription()).build();
			// 添加运行参数
			jobDetail.getJobDataMap().put(JobInfo.class.getSimpleName(),
					jobInfo);
			//
			trigger = newTrigger()
					.withIdentity(jobInfo.getJobName(),
							jobInfo.getJobGroupName())
					.withSchedule(cronSchedule(jobInfo.getCronExpression()))
					.build();
			logger.debug("scheduleJob triggerKey=" + triggerKey);
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			logger.debug("rescheduleJob triggerKey=" + triggerKey);
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(jobInfo.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}

	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(JobInfo jobInfo) throws SchedulerException {
		// Trigger已存在，那么更新相应的定时设置
		TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());
		// 按新的cronExpression表达式重新构建trigger
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(cronSchedule(jobInfo.getCronExpression()))
				.build();
		logger.debug("rescheduleJob triggerKey=" + triggerKey);
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 恢复定时任务
	 *
	 */
	public void resumeJob(JobInfo jobInfo) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());
		logger.debug("resumeJob jobkey=" + jobKey);
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除任务
	 * 
	 * @throws SchedulerException
	 *
	 */
	public void deleteJob(JobInfo jobInfo) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());
		logger.debug("deleteJob jobkey=" + jobKey);
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 立即执行，等同于手工执行一次
	 */
	public void triggerJob(JobInfo jobInfo) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());
		logger.debug("triggerJob jobkey=" + jobKey);
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 暂停
	 *
	 */
	public void pauseJob(JobInfo jobInfo) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(),
				jobInfo.getJobGroupName());
		logger.debug("pauseJob jobkey=" + jobKey);
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 所有的任务组名
	 *
	 */
	public List<String> findJobGroupNames() throws SchedulerException {
		List<String> list = this.scheduler.getJobGroupNames();
		return list;
	}

	/**
	 * 所有的定时任务
	 *
	 */
	public List<JobInfo> getAllJob() throws SchedulerException {
		List<JobInfo> allJobs = new ArrayList<JobInfo>();
		List<String> groupNames = findJobGroupNames();
		for (String gn : groupNames) {
			GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(gn);
			Set<JobKey> jobKeys = this.scheduler.getJobKeys(groupMatcher);
			for (JobKey jobKey : jobKeys) {
				JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
				List<? extends Trigger> triggerList = scheduler
						.getTriggersOfJob(jobKey);
				Trigger trigger = triggerList.get(0);
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				JobInfo jobInfo = new JobInfo();
				jobInfo.setJobName(jobKey.getName());
				jobInfo.setJobGroupName(jobKey.getGroup());
				jobInfo.setDescription(jobDetail.getDescription());
				jobInfo.setJobClassName(jobDetail.getJobClass().getName());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					jobInfo.setCronExpression(cronTrigger.getCronExpression());
				}
				jobInfo.setTriggerName(trigger.getKey().getName());
				jobInfo.setTriggerState(triggerState.name());
				allJobs.add(jobInfo);
			}
		}
		return allJobs;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<JobInfo> getRunningJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler
				.getCurrentlyExecutingJobs();
		List<JobInfo> jobList = new ArrayList<JobInfo>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			JobInfo job = new JobInfo();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			Trigger.TriggerState triggerState = scheduler
					.getTriggerState(trigger.getKey());
			job.setJobName(jobKey.getName());
			job.setJobGroupName(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			job.setJobStatus(triggerState.name());
			job.setTriggerName(trigger.getKey().getName());
			job.setTriggerState(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 查找定时任务
	 *
	 */
	public JobInfo findJob(String groupName, String jobName)
			throws SchedulerException {
		List<String> groupNames = findJobGroupNames();
		for (String gn : groupNames) {
			GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(gn);
			Set<JobKey> jobKeys = this.scheduler.getJobKeys(groupMatcher);
			for (JobKey jobKey : jobKeys) {
				if ((jobKey.getName().equals(jobName))
						&& (jobKey.getGroup().equals(groupName))) {
					JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
					List<? extends Trigger> triggerList = scheduler
							.getTriggersOfJob(jobKey);
					Trigger trigger = triggerList.get(0);
					JobInfo jobInfo = new JobInfo();
					jobInfo.setJobName(jobKey.getName());
					jobInfo.setJobGroupName(jobKey.getGroup());
					jobInfo.setDescription(jobDetail.getDescription());
					jobInfo.setJobClassName(jobDetail.getJobClass().getName());
					jobInfo.setTriggerName(trigger.getKey().getName());
					jobInfo.setTriggerState(
							scheduler.getTriggerState(trigger.getKey()).name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						jobInfo.setCronExpression(cronExpression);
					}
					return jobInfo;
				}
			}
		}
		logger.info("没有任务名为" + jobName + "的任务");
		return null;
	}

	/**
	 * 查找定时任务
	 * 
	 * @throws SchedulerException
	 *
	 */
	public JobDetail findJob(JobInfo jobInfo) throws SchedulerException {
		String jobName = jobInfo.getJobName();
		String groupName = jobInfo.getJobGroupName();
		List<String> groupNames = findJobGroupNames();
		for (String gn : groupNames) {
			GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(gn);
			Set<JobKey> jobKeys = this.scheduler.getJobKeys(groupMatcher);
			for (JobKey jobKey : jobKeys) {
				if ((jobKey.getName().equals(jobName))
						&& (jobKey.getGroup().equals(groupName))) {
					JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
					return jobDetail;
				}
			}
		}
		logger.info("没有任务名为" + jobName + "的任务");
		return null;

	}

	/**
	 * job绑定trigger
	 *
	 */
	public void scheduleJob(JobDetail jobDetail, CronTrigger cronTrigger) {
		try {
			this.scheduler.scheduleJob(jobDetail, cronTrigger);
		} catch (SchedulerException e) {
			logger.error("job绑定trigger出错");
			throw new FrameworkSchedulerException("job绑定trigger出错", e);
		}
	}

}
