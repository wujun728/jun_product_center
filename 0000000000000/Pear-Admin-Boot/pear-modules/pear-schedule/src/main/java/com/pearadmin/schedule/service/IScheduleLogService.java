package com.pearadmin.schedule.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.schedule.domain.ScheduleLog;

import java.util.List;

/**
 * Describe: 定时任务日志服务接口
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */

public interface IScheduleLogService {

    /**
     * Describe: 定时任务日志入库
     * Param: ScheduleJob
     * Return: Boolean 执行结果
     * */
    Boolean insert(ScheduleLog scheduleLog);

    /**
     * Describe: 定时任务列表
     * Param: ScheduleJob
     * Return: List
     * */
    List<ScheduleLog> list(ScheduleLog scheduleLogBean);

    /**
     * Describe: 定时任务列表  分页
     * Param: ScheduleJob
     * Return: pageInfo
     * */
    PageInfo<ScheduleLog> page(ScheduleLog scheduleLogBean, PageDomain pageDomain);

}
