package com.pearadmin.schedule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.schedule.domain.ScheduleLog;
import com.pearadmin.schedule.mapper.ScheduleLogMapper;
import com.pearadmin.schedule.service.IScheduleLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 定时任务日志服务
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Service("scheduleLogService")
public class ScheduleLogServiceImpl implements IScheduleLogService {

    @Resource
    private ScheduleLogMapper scheduleLogMapper ;

    /**
     * Describe: 定时任务日志入库
     * Param: ScheduleJob
     * Return: Boolean 执行结果
     * */
    @Override
    public Boolean insert(ScheduleLog scheduleLogBean) {
        int  i = scheduleLogMapper.insert(scheduleLogBean);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 定时任务列表
     * Param: ScheduleJob
     * Return: List
     * */
    @Override
    public List<ScheduleLog> list(ScheduleLog scheduleLogBean) {
        return scheduleLogMapper.selectList(scheduleLogBean);
    }

    /**
     * Describe: 定时任务列表  分页
     * Param: ScheduleJob
     * Return: pageInfo
     * */
    @Override
    public PageInfo<ScheduleLog> page(ScheduleLog scheduleLogBean, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<ScheduleLog> list = scheduleLogMapper.selectList(scheduleLogBean);
        return new PageInfo<>(list);
    }
}
