package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.project.domain.ProjectTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectTemplateMapper extends BaseMapper<ProjectTemplate> {

    @Select({"<script>",
            "SELECT id, name, description, sort, create_time, code,organization_code, cover, member_code, is_system",
            " FROM team_project_template ",
            "WHERE organization_code = #{params.orgCode}" ,
            "<if test='params.isSystem!=null and params.isSystem != -1 '>",
            "AND is_system <![CDATA[ = ]]> #{params.isSystem}",
            "</if>","</script>"})
    IPage<Map> getProTemplateByOrgCode(IPage<Map> iPage, @Param("params") Map params);

    @Select("SELECT * FROM team_project_template A WHERE A.code = #{code} ")
    Map getProjectTemplateByCode(@Param("code") String code);
}



