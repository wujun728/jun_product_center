package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.project.domain.ProjectLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectLogMapper  extends BaseMapper<ProjectLog> {

    @Select("SELECT * FROM team_project_log WHERE source_code = #{params.sourceCode} AND action_type = #{params.actionType}")
    IPage<Map> selectProjectLogByParam(IPage<Map> iPage, @Param("params") Map params);

    @Select("select * from team_project_log where action_type='task' and source_code=#{sourceCode} and type='done' order by id desc ")
    List<Map> selectProjectLogBySourceCode(@Param("sourceCode") String sourceCode);

}
