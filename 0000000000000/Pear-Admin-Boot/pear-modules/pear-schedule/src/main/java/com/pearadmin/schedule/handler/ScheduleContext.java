package com.pearadmin.schedule.handler;

import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.tools.spring.SpringUtil;
import com.pearadmin.schedule.domain.ScheduleJob;
import com.pearadmin.schedule.domain.ScheduleLog;
import com.pearadmin.schedule.service.IScheduleLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Describe: 定时任务执行上下文
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Slf4j
public class ScheduleContext extends QuartzJobBean {

    /**
     * Describe: 执行任务并记录日志
     * Param: JobExecutionContext
     * Return: 无返回值
     * */
    @Override
    protected void executeInternal(JobExecutionContext context) {
        Object o = context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJob jobBean = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        IScheduleLogService scheduleJobLogService = (IScheduleLogService) SpringUtil.getBean("scheduleLogService");
        ScheduleLog logBean = new ScheduleLog() ;
        logBean.setLogId(SequenceUtil.makeStringId());
        logBean.setJobId(jobBean.getJobId());
        logBean.setBeanName(jobBean.getBeanName());
        logBean.setParams(jobBean.getParams());
        logBean.setCreateTime(LocalDateTime.now());
        long beginTime = System.currentTimeMillis() ;
        try {
            Object target = SpringUtil.getBean(jobBean.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, jobBean.getParams());
            long executeTime = System.currentTimeMillis() - beginTime;
            logBean.setTimes((int) executeTime);
            logBean.setStatus(0);
            log.info("定时器 === >> " + jobBean.getJobName() + "执行成功,耗时 === >> " + executeTime);
        } catch (Exception e){
            long executeTime = System.currentTimeMillis() - beginTime;
            logBean.setTimes((int)executeTime);
            logBean.setStatus(1);
            logBean.setError(e.getMessage());
            e.getCause();
        } finally {
            scheduleJobLogService.insert(logBean);
        }
    }
}
