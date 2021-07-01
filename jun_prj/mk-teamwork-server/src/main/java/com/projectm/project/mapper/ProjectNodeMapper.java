package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.ProjectNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectNodeMapper   extends BaseMapper<ProjectNode> {

    @Select("SELECT * FROM team_project_node A WHERE A.node LIKE CONCAT('%',#{node},'%') ")
    List<Map> selectProjectNodeByNodeLike(@Param("node")  String node);

    @Select("SELECT * FROM team_project_node")
    List<Map> selectAllProjectNode();
}
