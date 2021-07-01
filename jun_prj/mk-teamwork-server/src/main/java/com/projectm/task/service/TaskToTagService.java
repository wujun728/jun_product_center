package com.projectm.task.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.task.domain.TaskToTag;
import com.projectm.task.mapper.TaskToTagMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskToTagService   extends ServiceImpl<TaskToTagMapper, TaskToTag> {

    public List<Map> getTaskToTagByTaskCode(String taskCode){
        return baseMapper.selectTaskToTagByTaskCode(taskCode);
    }

    public Map getTaskToTagByTagCodeAndTaskCode(String tagCode,String taskCode){
        return baseMapper.selectTaskToTagByTagCodeAndTaskCode(tagCode,taskCode);
    }

    public List<TaskToTag> getTaskToTagsByTaskCode(String taskCode){
        LambdaQueryWrapper<TaskToTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskToTag::getTask_code, taskCode);
        return baseMapper.selectList(queryWrapper);
    }
}
