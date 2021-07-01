package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.task.domain.TaskWorkflow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface TaskWorkflowMapper extends BaseMapper<TaskWorkflow> {

    @Select("SELECT * FROM team_task_workflow A WHERE A.project_code = #{projectCode} ORDER BY A.id ASC")
    List<Map> selectTaskWorkflowByProjectCode(@Param("projectCode") String projectCode);

    @Delete("DELETE FROM team_task_workflow WHERE code = #{workflowCode}")
    int deleteTaskWorkflowByCode(@Param("workflowCode") String workflowCode);
}



