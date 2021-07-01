package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.Collection;
import com.projectm.project.domain.InviteLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

    @Select("SELECT * FROM `team_collection` WHERE `source_code` = #{sourceCode} AND `type` = 'task' AND `member_code` = #{memberCode} LIMIT 1")
    Map selectCollection(@Param("sourceCode") String sourceCode,@Param("memberCode") String memberCode);
}
