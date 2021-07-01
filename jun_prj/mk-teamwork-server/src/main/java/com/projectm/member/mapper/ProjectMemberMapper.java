package com.projectm.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.member.domain.ProjectMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface ProjectMemberMapper  extends BaseMapper<ProjectMember> {

    List<Map> selectMemberByLoginParam(@Param("params") Map params);

    List<Map> selectMemberCountByMemberCode(@Param("params") Map params);

    List<Map> getMemberById(@Param("userCode") String userCode);

    @Select("SELECT * FROM team_project_member A  WHERE A.is_owner = 1 and A.project_code = #{projectCode}")
    Map getProjectMemberByProjectCodeOwner(@Param("projectCode") String projectCode);

    @Select("SELECT * FROM team_project_member A  WHERE A.project_code = #{projectCode}")
    IPage<Map> getProjectMemberByProjectCode(IPage<Map> page, @Param("projectCode") String projectCode);

    @Select("SELECT * FROM team_project_member A  WHERE A.project_code = #{projectCode} AND A.member_code = #{memberCode}")
    List<Map> getProjectMemberByProjectCodeAndMemberCode(@Param("projectCode") String projectCode, @Param("memberCode") String memberCode);

    @Select("SELECT member_code, name FROM team_project_member pm LEFT JOIN team_member m ON pm.member_code = m.code WHERE pm.project_code = #{projectCode} AND is_owner = 1 LIMIT 1")
    Map selectMemberCodeAndNameByProjectCode(@Param("projectCode") String projectCode);

    @Select("select b.name from team_project_member a,team_member b where a.member_code = b.code and a.project_code=#{projectCode} and b.code=#{memberCode} and a.is_owner = 1 limit 1")
    String selectMemberNameByProjectMember(@Param("projectCode") String projectCode,@Param("memberCode") String memberCode);


}



