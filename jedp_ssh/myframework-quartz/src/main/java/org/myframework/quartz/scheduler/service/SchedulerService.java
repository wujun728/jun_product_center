package org.myframework.quartz.scheduler.service;

import javax.annotation.Resource;

import org.myframework.commons.beanutil.BeanUtils2;
import org.myframework.quartz.scheduler.dao.JobInfoDao;
import org.myframework.quartz.scheduler.entities.JobInfo;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 定时任务服务类，封装quartz的相关操作！
 * <ol>
 * <li>{@link }手工运行一次JOB</li>
 * <li>{@link }动态添加任务</li>
 * <li>{@link }动态删除任务</li>
 * <li>{@link }暂停任务</li>
 * <li>{@link }恢复任务</li>
 * <li>{@link }获取所有已提交的任务</li>
 * <li>{@link }获取所有正在运行的任务</li>
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月14日
 *
 */
@Service("schedulerService")
public class SchedulerService   {

	private static final Logger logger = LoggerFactory
			.getLogger(SchedulerService.class);

	@Resource(name = "quartzService")
	private QuartzService quartzService;

	@Resource(name = "jobInfoDao")
	private JobInfoDao jobInfoDao;

	/**
	 * 保存任务信息到数据库
	 * 
	 * @param job
	 * @throws Exception
	 */
	public void saveJob(JobInfo job) {
		String jobName = job.getJobName();
		String jobGroupName = job.getJobGroupName();
		// 保证jobName, jobGroupName的唯一性
		JobInfo jobInfo = jobInfoDao.findByJobNameAndJobGroupName(jobName,
				jobGroupName);
		if (jobInfo != null) {
			BeanUtils2.copyProperties(job, jobInfo);
		}
		jobInfoDao.save(job);
	}

	/**
	 * 添加定时任务到quartz服务器
	 * 
	 * @throws SchedulerException
	 *
	 */
	public void createJobAndTrigger(JobInfo jobInfo) throws Exception {
		// 如果任务是需要执行的，则添加到调度服务器中执行
		if (JobInfo.STATUS_RUNNING.equals(jobInfo.getJobStatus()))
			quartzService.createJobAndTrigger(jobInfo);
		saveJob(jobInfo);
	}

	/**
	 * 更新job 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJob(JobInfo jobInfo) throws  Exception {
		// 停止JOB，从QUARTZ服务中清除JOB任务
		quartzService.deleteJob(jobInfo);
		// 重新保存JOB内容
		saveJob(jobInfo);
		// 重新添加任务到QUARTZ
		createJobAndTrigger(jobInfo);
	}
	
	/**
	 * 启动或完全停止JOB任务
	 * @param jobInfo
	 * @throws Exception
	 */
	public void changeJobStatus(JobInfo jobInfo)throws  Exception {
		String jobStatu = jobInfo.getJobStatus();
		saveJob (jobInfo);
		if(JobInfo.STATUS_RUNNING.equals(jobStatu))	{
			quartzService.createJobAndTrigger(jobInfo);
		}else {
			quartzService.deleteJob(jobInfo);
		}
	}

	/**
	 * 查找定时任务
	 *
	 */
	public JobInfo findJobFromDb(String jobName, String jobGroupName) {
		return jobInfoDao.findByJobNameAndJobGroupName(jobName, jobGroupName);
	}

}