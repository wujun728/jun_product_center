package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.task.domain.TaskTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface TaskTagMapper  extends BaseMapper<TaskTag> {

    @Select("SELECT * FROM team_task_tag WHERE project_code = #{projectCode} ORDER BY name ASC")
    List<Map> selectTaskTagByProjectCode(@Param("projectCode") String projectCode);

    @Select("SELECT * FROM team_task_tag WHERE code = #{code} LIMIT 1")
    Map selectTaskTagByCode(@Param("code") String code);

    @Select("SELECT * FROM team_task_tag WHERE name = #{params.name} AND project_code = #{params.projectCode} LIMIT 1")
    Map selectTaskTagByNameAndProjectCode(@Param("params") Map params);
}
