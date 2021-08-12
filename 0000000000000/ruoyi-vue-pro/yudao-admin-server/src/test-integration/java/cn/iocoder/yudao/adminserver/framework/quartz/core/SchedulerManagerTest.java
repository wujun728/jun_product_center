package cn.iocoder.yudao.adminserver.framework.quartz.core;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.adminserver.BaseDbUnitTest;
import cn.iocoder.yudao.adminserver.modules.system.job.auth.SysUserSessionTimeoutJob;
import cn.iocoder.yudao.framework.quartz.core.scheduler.SchedulerManager;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;

import javax.annotation.Resource;

class SchedulerManagerTest extends BaseDbUnitTest {

    @Resource
    private SchedulerManager schedulerManager;

    @Test
    public void testAddJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.addJob(1L, jobHandlerName, "test", "0/10 * * * * ? *", 0, 0);
    }

    @Test
    public void testUpdateJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.updateJob(jobHandlerName, "hahaha", "0/20 * * * * ? *", 0, 0);
    }

    @Test
    public void testDeleteJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.deleteJob(jobHandlerName);
    }

    @Test
    public void testPauseJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.pauseJob(jobHandlerName);
    }

    @Test
    public void testResumeJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.resumeJob(jobHandlerName);
    }

    @Test
    public void testTriggerJob() throws SchedulerException {
        String jobHandlerName = StrUtil.lowerFirst(SysUserSessionTimeoutJob.class.getSimpleName());
        schedulerManager.triggerJob(1L, jobHandlerName, "niubi!!!");
    }

}
