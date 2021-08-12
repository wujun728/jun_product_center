package com.pearadmin.schedule.mapper;

import com.pearadmin.schedule.domain.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Describe: 定时任务接口
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface ScheduleJobMapper {

    /**
     * Describe: 定时任务入库
     * Param: ScheduleJob
     * Return: Integer 影响条数
     * */
    Integer insert(ScheduleJob scheduleJob);

    /**
     * Describe: 根据条件查询定时任务列表
     * Param: ScheduleJob
     * Return: ScheduleJob 列表
     * */
    List<ScheduleJob> selectList(ScheduleJob param);

    /**
     * Describe: 根据 jobId 查询定时任务
     * Param: jobId
     * Return: ScheduleJob
     * */
    ScheduleJob selectById(String jobId);

    /**
     * Describe: 根据 JobId 修改定时任务
     * Param: ScheduleJob
     * Return: Integer 影响条数
     * */
    Integer updateById(ScheduleJob scheduleJob);

    /**
     * Describe: 根据 JobId 删除定时任务
     * Param: ScheduleJob
     * Return: Integer 影响条数
     * */
    Integer deleteById(String jobId);
}