package com.projectm.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.member.domain.MemberAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface MemberAccountMapper extends BaseMapper<MemberAccount> {

    @Select("SELECT * FROM team_member_account WHERE organization_code = #{orgCode} ")
    List<Map> getMemberCountByOrgCode(@Param("orgCode") String orgCode);

    @Select("SELECT * FROM team_member_account A WHERE A.name LIKE CONCAT('%',#{name},'%') AND A.organization_code = #{orgCode}")
    List<Map> getMemberCountByOrgCodeAndMemberName(@Param("orgCode") String orgCode,@Param("name") String name);

    @Select("SELECT * FROM team_member_account WHERE member_code = #{memberCode} AND organization_code = #{orgCode} LIMIT 1")
    Map selectMemberAccountByMemCodeAndOrgCode(@Param("memberCode") String memberCode,@Param("orgCode") String orgCode);


    @Select("SELECT * FROM team_member_account WHERE organization_code = #{params.orgCode} AND status = #{params.status} AND department_code LIKE CONCAT('%',#{params.depCode},'%') ORDER BY id ASC")
    IPage<Map> selectMemberAccountByOrgCodeStatusDeptCode(IPage<Map> page, @Param("params") Map params);

    @Select("SELECT * FROM team_member_account WHERE organization_code = #{params.orgCode} AND status = #{params.status} ORDER BY id ASC")
    IPage<Map> selectMemberAccountByOrgCodeAndStatus(IPage<Map> page, @Param("params") Map params);

    @Select("SELECT * FROM team_member_account WHERE code = #{memAccCode} ")
    Map selectMemberAccountByCode(@Param("memAccCode") String memAccCode);

    @Select("SELECT * FROM team_member_account WHERE member_code = #{memCode} ")
    Map selectMemberAccountByMemCode(@Param("memCode") String memCode);

    @Delete("DELETE FROM team_department_member WHERE account_code = #{accCode} AND organization_code = #{orgCode}")
    Integer deleteDepartmentMemberByAccCodeAndOrgCode(@Param("accCode") String accCode,@Param("orgCode") String orgCode);

    @Update("update team_member_account a set a.department_code=#{departCodes}  WHERE a.code = #{code}")
    Integer updateDepartCodeByCode(@Param("departCodes") String departCodes,@Param("code") String code);


}



