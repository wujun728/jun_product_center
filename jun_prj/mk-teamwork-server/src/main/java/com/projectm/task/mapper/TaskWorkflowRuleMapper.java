package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.task.domain.TaskWorkflowRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface TaskWorkflowRuleMapper extends BaseMapper<TaskWorkflowRule> {

    @Select("SELECT * FROM team_task_workflow_rule A WHERE A.workflow_code = #{workflowCode} ORDER BY sort ASC")
    List<Map> selectTaskWorkflowRuleByWorkflowCode(@Param("workflowCode") String workflowCode);
}



