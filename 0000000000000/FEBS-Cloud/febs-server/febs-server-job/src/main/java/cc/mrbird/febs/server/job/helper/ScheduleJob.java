package cc.mrbird.febs.server.job.helper;


import cc.mrbird.febs.server.job.entity.Job;
import cc.mrbird.febs.server.job.entity.JobLog;
import cc.mrbird.febs.server.job.service.IJobLogService;
import cc.mrbird.febs.server.job.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * 定时任务
 *
 * @author MrBird
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ThreadPoolTaskExecutor scheduleJobExecutorService = SpringContextUtil.getBean(ThreadPoolTaskExecutor.class);
        Job scheduleJob = (Job) context.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);
        IJobLogService scheduleJobLogService = SpringContextUtil.getBean(IJobLogService.class);
        JobLog jobLog = new JobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = scheduleJobExecutorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            jobLog.setStatus(JobLog.JOB_SUCCESS);
            log.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            jobLog.setStatus(JobLog.JOB_FAIL);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.saveJobLog(jobLog);
        }
    }
}
