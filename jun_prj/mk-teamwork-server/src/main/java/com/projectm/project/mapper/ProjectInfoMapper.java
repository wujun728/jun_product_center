package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectInfoMapper  extends BaseMapper<ProjectInfo> {

    @Select("SELECT * FROM team_project_info WHERE project_code = #{projectCode} ORDER BY id DESC")
    List<Map> selectProjectInfoByProjectCode(@Param("projectCode") String projectCode);

}
