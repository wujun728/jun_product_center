package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.ProjectFeatures;
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
public interface ProjectFeaturesMapper  extends BaseMapper<ProjectFeatures> {

    @Select("SELECT * FROM team_project_features WHERE project_code = #{projectCode} ORDER BY id DESC")
    List<Map> selectProjectFeaturesByProjectCode(@Param("projectCode") String projectCode);

    @Select("SELECT * FROM team_project_features WHERE code = #{code} ")
    Map selectProjectFeaturesByCode(@Param("code") String code);

    @Select("SELECT * FROM team_project_features WHERE name = #{name} AND project_code = #{projectCode} LIMIT 1 ")
    Map selectProjectFeaturesOneByNameAndProjectCode(@Param("name") String name,@Param("projectCode") String projectCode);

    @Delete("DELETE FROM team_project_features WHERE code = #{code}")
    Integer deleteProjectFeaturesByCode(@Param("code") String code);
}
