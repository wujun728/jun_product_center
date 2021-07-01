package com.projectm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.project.domain.InviteLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface InviteLinkMapper extends BaseMapper<InviteLink> {

    @Select("SELECT * FROM team_invite_link WHERE invite_type = #{params.inviteType} AND source_code = #{params.sourceCode} AND create_by = #{params.createBy}")
    Map getInviteLinkByParams(@Param("params") Map params);
}
