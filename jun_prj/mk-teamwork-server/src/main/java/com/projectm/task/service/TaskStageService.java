package com.projectm.task.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.framework.common.exception.CustomException;
import com.projectm.task.domain.Task;
import com.projectm.task.domain.TaskStage;
import com.projectm.task.mapper.TaskStageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
@Service
public class TaskStageService  extends ServiceImpl<TaskStageMapper, TaskStage> {

    @Autowired
    TaskService taskService;
    //根据 项目编号查询taskStage
    public List<Map> selectTaskStageByProjectCode(String projectCode){
        return baseMapper.selectTaskStageByProjectCode(projectCode);
    }

    //根据 项目编号查询taskStage
    public IPage<TaskStage> selectTaskStageByProjectCode(IPage ipage, Map params){
        return baseMapper.selectTaskStageByProjectCodeForPage(ipage,params);
    }

    public TaskStage getTaskStageByCode(String code){
        LambdaQueryWrapper<TaskStage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskStage::getCode, code);
        return baseMapper.selectOne(queryWrapper);
    }

    public void deleteStage(String code){
        TaskStage taskStage = lambdaQuery().eq(TaskStage::getCode,code).one();
        if(ObjectUtils.isEmpty(taskStage)){
            throw new CustomException("该列表不存在！");
        }
        List<Task> tasks = taskService.lambdaQuery().eq(Task::getStage_code,code).eq(Task::getDeleted,0).list();
        if(!CollectionUtils.isEmpty(tasks)){
            throw new CustomException("请先清空此列表上的任务，然后再删除这个列表！");
        }
        lambdaUpdate().eq(TaskStage::getCode,code).remove();
    }

}
