package com.deer.wms.quartz.util;

import org.quartz.JobExecutionContext;
import com.deer.wms.quartz.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author deer
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
