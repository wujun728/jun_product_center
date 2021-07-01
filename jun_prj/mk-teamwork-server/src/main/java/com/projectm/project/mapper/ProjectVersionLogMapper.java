package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.project.domain.ProjectVersionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectVersionLogMapper  extends BaseMapper<ProjectVersionLog> {

    @Select("SELECT * FROM team_project_version_log WHERE source_code = #{sourceCode} ORDER BY id ASC")
    IPage<Map> selectProjectVersionLogBySourceCode(IPage<Map> page, @Param("sourceCode") String sourceCode);

    @Select("SELECT * FROM team_project_version_log WHERE source_code = #{sourceCode} ORDER BY id ASC")
    List<Map> selectProjectVersionLogBySourceCodeAll(@Param("sourceCode") String sourceCode);


}
