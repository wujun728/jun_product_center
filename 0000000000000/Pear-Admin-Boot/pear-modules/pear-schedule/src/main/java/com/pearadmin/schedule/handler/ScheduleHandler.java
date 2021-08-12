package com.pearadmin.schedule.handler;

import com.pearadmin.schedule.domain.ScheduleJob;
import lombok.NoArgsConstructor;
import org.quartz.*;

/**
 * Describe: 定时任务处理器
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@NoArgsConstructor
public class ScheduleHandler {

    /**
     * 定时任务标识 Key
     * */
    private static final String SCHEDULE_NAME = "Pear_" ;

    /**
     * 触发器 KEY
     */
    public static TriggerKey getTriggerKey(Long jobId){
        return TriggerKey.triggerKey(SCHEDULE_NAME + jobId) ;
    }

    /**
     * 定时器 Key
     */
    public static JobKey getJobKey (Long jobId){
        return JobKey.jobKey(SCHEDULE_NAME+jobId) ;
    }

    /**
     * 表达式触发器
     */
    public static CronTrigger getCronTrigger (Scheduler scheduler, Long jobId){
        try {
            return (CronTrigger)scheduler.getTrigger(getTriggerKey(jobId)) ;
        } catch (SchedulerException e){
            return null;
        }
    }

    /**
     * Describe: 创建定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void createJob (Scheduler scheduler, ScheduleJob scheduleJob){
        try {
            // 构建定时器
            JobDetail jobDetail = JobBuilder.newJob(ScheduleContext.class).withIdentity(getJobKey(Long.parseLong(scheduleJob.getJobId()))).build() ;
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing() ;
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(Long.parseLong(scheduleJob.getJobId())))
                    .withSchedule(scheduleBuilder).build();
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY,scheduleJob);
            scheduler.scheduleJob(jobDetail,trigger);
            // 如果该定时器处于暂停状态
            if ("1".equals(scheduleJob.getStatus())){
                pauseJob(scheduler,Long.parseLong(scheduleJob.getJobId())) ;
            }
        } catch (SchedulerException e){
            throw new RuntimeException("createJob Fail",e) ;
        }
    }

    /**
     * Describe: 更新定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void updateJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(Long.parseLong(scheduleJob.getJobId()));
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = getCronTrigger(scheduler, Long.parseLong(scheduleJob.getJobId()));
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            trigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
            scheduler.rescheduleJob(triggerKey, trigger);
            if("1".equals(scheduleJob.getStatus())){
                pauseJob(scheduler, Long.parseLong(scheduleJob.getJobId()));
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("updateJob Fail",e);
        }
    }

    /**
     * Describe: 停止定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void pauseJob (Scheduler scheduler, Long jobId){
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("pauseJob Fail",e);
        }
    }

    /**
     * Describe: 恢复定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void resumeJob (Scheduler scheduler, Long jobId){
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("resumeJob Fail",e);
        }
    }

    /**
     * Describe: 删除定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void deleteJob (Scheduler scheduler, Long jobId){
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("deleteJob Fail",e);
        }
    }

    /**
     * Describe: 执行定时任务
     * Param: Scheduler ScheduleJobBean
     * Return: null
     * */
    public static void run (Scheduler scheduler, ScheduleJob scheduleJob){
        try {
            JobDataMap dataMap = new JobDataMap() ;
            dataMap.put(ScheduleJob.JOB_PARAM_KEY,scheduleJob);
            scheduler.triggerJob(getJobKey(Long.parseLong(scheduleJob.getJobId())),dataMap);
        } catch (SchedulerException e){
            throw new RuntimeException("run Fail",e) ;
        }
    }

}
