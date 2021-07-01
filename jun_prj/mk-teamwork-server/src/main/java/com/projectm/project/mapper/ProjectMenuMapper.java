package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.ProjectMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectMenuMapper  extends BaseMapper<ProjectMenu> {

    //查询所有菜单
    List<ProjectMenu> selectAllProjectMenu(@Param("params") Map params);

    //根据PID查询菜单
    List<ProjectMenu> selectProjectMenuByPid(@Param("params") Map params);

    //查询所有不为根的菜单
    List<ProjectMenu> selectAllNotBase(@Param("params") Map params);


}
