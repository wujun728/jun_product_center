package com.projectm.task.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.task.domain.TaskWorkTime;
import com.projectm.task.mapper.TaskWorkTimeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskWorkTimeService  extends ServiceImpl<TaskWorkTimeMapper, TaskWorkTime> {

    //根据taskCode获取taskworktime
    public List<Map> getTaskWorkTimeByTaskCode(String taskCode){
        return baseMapper.selectTaskWorkTimeByTaskCode(taskCode);
    }
    //根据code获取taskworktime
    public Map getTaskWorkTimeByCode(String code){
        return baseMapper.selectTaskWorkTimeByCode(code);
    }
    //根据code删除taskworktime
    public Integer delTaskWorkTimeByCode(String code){
        return baseMapper.deleteTaskWorkTimeByCode(code);
    }
}
