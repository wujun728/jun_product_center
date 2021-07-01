package com.projectm.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    @Select("SELECT * FROM team_member WHERE code = #{memberCode}")
    Map selectMemberByCode(@Param("memberCode") String memberCode);

    @Select("SELECT * FROM team_member WHERE account = #{account}")
    Member selectMemberByAccount(@Param("account") String account);

    @Select("select b.organization_code,a.dingtalk_openid,a.code,a.last_login_time,a.city,a.description,a.password,a.province,a.id,a.dingtalk_userid, " +
            "b.department,b.authorize,a.email,a.area,a.address,a.create_time,a.dingtalk_unionid,b.is_owner,a.sex,a.mobile,a.avatar,a.realname,b.id as account_id,a.idcard,a.name,b.position,a.account,a.status " +
            "from team_member a,team_member_account b " +
            "where a.code = b.member_code and a.account=#{account} limit 1")
    Map selectMemberAndAccountByAccount(@Param("account") String account);


}



