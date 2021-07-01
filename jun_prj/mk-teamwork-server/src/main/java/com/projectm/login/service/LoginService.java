package com.projectm.login.service;

import com.framework.common.AjaxResult;

import com.framework.common.utils.IdUtils;
import com.framework.common.utils.ServletUtils;
import com.projectm.common.Constant;
import com.projectm.member.mapper.MemberMapper;
import com.projectm.org.mapper.OrganizationMapper;
import com.projectm.project.mapper.ProjectMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    OrganizationMapper organizationMapper;


    public AjaxResult login(String account, String password) {


        Map member = memberMapper.selectMemberAndAccountByAccount(account);
        if(!Optional.ofNullable(member).isPresent()){
            return AjaxResult.error("账号/密码错误！");
        }

        String auth = MapUtils.getString(member,"authorize","");
        List<String> auths = Arrays.asList(auth.split(","));
        if(auths.size()>0){
            member.put(Constant.NODES,projectMapper.selectProAuthNode(auths));
        }else{
            member.put(Constant.NODES,auths);
        }
        ServletUtils.getRequest().getSession().setAttribute(Constant.CURRENT_USER,member);
        return AjaxResult.success(new HashMap(){{
            put("member",member);
            put("tokenList",new HashMap(){{
                put("accessTokenExp",(new Date()).getTime());
                put("accessToken", IdUtils.randomUUID());
                put("refreshToken", IdUtils.randomUUID());
            }});
            put("organizationList",organizationMapper.selectOrganizationByCode(MapUtils.getString(member,"organization_code")));
        }});
    }
}
