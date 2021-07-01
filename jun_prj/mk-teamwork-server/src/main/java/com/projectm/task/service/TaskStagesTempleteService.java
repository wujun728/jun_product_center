package com.projectm.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.mapper.CommMapper;
import com.projectm.task.domain.TaskStagesTemplete;
import com.projectm.task.mapper.TaskStagesTempleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskStagesTempleteService  extends ServiceImpl<TaskStagesTempleteMapper, TaskStagesTemplete> {

    @Autowired
    CommMapper commMapper;

    //根据项目模板编号，查询该模板下的taskstagestemplete
    public List<Integer> selectIdsByProjectTempleteCode(String projectTempleteCode){
        return baseMapper.selectIdsByProjectTempleteCode(projectTempleteCode);
    }


    public IPage<Map> getTaskStagesTemplate(IPage<Map> ipage, String code){
        String sql = "select * from team_task_stages_template as pt where pt.project_template_code='%s'";
        ipage = commMapper.customQueryItem(ipage,String.format(sql,code));
        return ipage;
    }

    public TaskStagesTemplete getTaskStageTempleteByCode(String code){
        return baseMapper.selectByCode(code);
    }

}
