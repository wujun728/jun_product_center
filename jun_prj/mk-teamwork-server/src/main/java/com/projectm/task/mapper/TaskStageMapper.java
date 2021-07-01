package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.task.domain.TaskStage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface TaskStageMapper extends BaseMapper<TaskStage> {

    @Select("SELECT * FROM team_task_stages A  WHERE A.project_code = #{projectCode}")
    List<Map> selectTaskStageByProjectCode(@Param("projectCode")  String projectCode);

    @Select("SELECT * FROM team_task_stages A  WHERE A.project_code = #{params.projectCode} order by sort asc,id asc")
    IPage<TaskStage> selectTaskStageByProjectCodeForPage(IPage page, @Param("params") Map params);
}



