package com.pearadmin.schedule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.schedule.domain.ScheduleJob;
import com.pearadmin.schedule.mapper.ScheduleJobMapper;
import com.pearadmin.schedule.service.IScheduleJobService;
import com.pearadmin.schedule.handler.ScheduleHandler;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 定时任务服务
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Service
public class ScheduleJobServiceImpl implements IScheduleJobService {

    /**
     * 引 入 服 务
     * */
    @Resource
    private Scheduler scheduler ;
    @Resource
    private ScheduleJobMapper scheduleJobMapper ;

    /**
     * Describe: 根据编号获取定时任务
     * Param: JobId
     * Return: Schedule
     * */
    @Override
    public ScheduleJob getById(String jobId) {
        return scheduleJobMapper.selectById(jobId);
    }

    /**
     * Describe: 定时任务列表 分页
     * Param: ScheduleJob PageDomain
     * Return: pageInfo
     * */
    @Override
    public PageInfo<ScheduleJob> page(ScheduleJob param, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<ScheduleJob> list = scheduleJobMapper.selectList(param);
        return new PageInfo<>(list);
    }

    /**
     * Describe: 定时任务列表
     * Param: ScheduleJob
     * Return: list
     * */
    @Override
    public List<ScheduleJob> list(ScheduleJob param) {
        return scheduleJobMapper.selectList(param);
    }

    /**
     * Describe: 新增定时任务
     * Param: ScheduleJob
     * Return: Boolean 执行结果
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(ScheduleJob record) {
        ScheduleHandler.createJob(scheduler,record);
        int result =  scheduleJobMapper.insert(record);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 修改定时任务
     * Param: ScheduleJob
     * Return: Boolean 执行结果
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(ScheduleJob record) {
        ScheduleHandler.updateJob(scheduler,record);
        int result = scheduleJobMapper.updateById(record);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 停止定时任务
     * Param: JobId
     * Return: Boolean 执行结果
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean pause(String jobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectById(jobId);
        ScheduleHandler.pauseJob(scheduler,Long.parseLong(jobId));
        scheduleJob.setStatus("1");
        int result = scheduleJobMapper.updateById(scheduleJob);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 恢复定时任务
     * Param: JobId
     * Return: Boolean 执行结果
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean resume(String jobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectById(jobId);
        ScheduleHandler.resumeJob(scheduler,Long.parseLong(jobId));
        scheduleJob.setStatus("0");
        int result = scheduleJobMapper.updateById(scheduleJob);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 运行一次定时任务
     * Param: JobId
     * Return: 无返回值
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String jobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectById(jobId);
        ScheduleHandler.run(scheduler,scheduleJob);
    }

    /**
     * Describe: 删除定时任务
     * Param: JobId
     * Return: Boolean 执行结果
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String jobId) {
        ScheduleHandler.deleteJob(scheduler,Long.parseLong(jobId));
        int result = scheduleJobMapper.deleteById(jobId);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }
}
