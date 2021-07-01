package com.projectm.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.org.domain.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

    @Select("SELECT B.*FROM team_member_account A, team_organization B WHERE A.organization_code = B.code AND A.member_code=#{memberCode}")
    IPage<Map> getAllOrganizationByMemberCode(IPage<Map> page, @Param("memberCode") String memberCode);

    @Select("select a.id,a.name,a.avatar,a.description,a.owner_code,a.create_time,a.personal,a.code,a.address,a.province,a.city,a.area from team_organization a where a.code = #{code}")
    List<Map> selectOrganizationByCode(@Param("code") String code);

    List<Organization> selectOrganizationByMemberCode(@Param("memberCode") String memberCode);

}
