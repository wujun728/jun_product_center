package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.SourceLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface SourceLinkMapper  extends BaseMapper<SourceLink> {

    @Select("SELECT code,source_type,source_code,link_type,link_code,organization_code,create_by,create_time,sort FROM team_source_link WHERE link_code = #{linkCode} AND link_type = #{linkType} ORDER BY id DESC")
    List<Map> selectSourceLinkByLinkCodeAndType(@Param("linkCode") String linkCode, @Param("linkType") String linkType);
}
