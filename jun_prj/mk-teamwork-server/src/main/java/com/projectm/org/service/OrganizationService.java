package com.projectm.org.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.service.MemberAccountService;
import com.projectm.org.domain.Organization;
import com.projectm.org.mapper.OrganizationMapper;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectAuth;
import com.projectm.project.domain.ProjectAuthNode;
import com.projectm.project.service.ProjectAuthNodeService;
import com.projectm.project.service.ProjectAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OrganizationService   extends ServiceImpl<OrganizationMapper, Organization> {

    //获取用户所在的所有组织信息
    public IPage<Map> getAllOrganizationByMemberCode(IPage<Map> page, String memberCode){
        return baseMapper.getAllOrganizationByMemberCode(page,memberCode);
    }

    //根据orgCode获取organization信息
    public Organization getOrganizationByCode(String orgCode){
        LambdaQueryWrapper<Organization> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Organization::getCode, orgCode);
        return baseMapper.selectOne(queryWrapper);
    }

    @Autowired
    ProjectAuthService projectAuthService;
    @Autowired
    ProjectAuthNodeService projectAuthNodeService;
    @Autowired
    MemberAccountService memberAccountService;

    @Transactional
    public MemberAccount createOrganization(Member member){
        Integer defaultAdminAuthId = 3;//默认管理员权限id
        Integer defaultMemberAuthId = 4;//默认成员权限id
        Organization organization = Organization.builder().name(member.getName()+"的个人项目")
                .code(CommUtils.getUUID()).personal(1).create_time(DateUtil.getCurrentDateTime())
                .owner_code(member.getCode()).build();
        save(organization);
        ProjectAuth defaultAdminAuth=projectAuthService.getById(defaultAdminAuthId);
        ProjectAuth defaultMemberAuth=projectAuthService.getById(defaultMemberAuthId);
        ProjectAuth defaultAdminAuthSave=ProjectAuth.builder().create_at(DateUtil.getCurrentDateTime())
                .organization_code(organization.getCode())
                .is_default(1).type(defaultAdminAuth.getType()).title(defaultAdminAuth.getTitle())
                .status(1).sort(0).desc(defaultAdminAuth.getDesc()).create_by(defaultAdminAuth.getCreate_by())
                .build();
        projectAuthService.save(defaultAdminAuthSave);
        ProjectAuth defaultMemberAuthSave = ProjectAuth.builder().create_at(DateUtil.getCurrentDateTime())
                .organization_code(organization.getCode())
                .is_default(0).type(defaultMemberAuth.getType()).title(defaultMemberAuth.getTitle())
                .status(1).sort(0).desc(defaultMemberAuth.getDesc()).create_by(defaultMemberAuth.getCreate_by())
                .build();
        projectAuthService.save(defaultMemberAuthSave);

        List<ProjectAuthNode> defaultAdminAuthNodes = projectAuthNodeService.lambdaQuery()
                .eq(ProjectAuthNode::getAuth,defaultAdminAuthId).list();
        List<ProjectAuthNode> defaultMemberAuthNode = projectAuthNodeService.lambdaQuery()
                .eq(ProjectAuthNode::getAuth,defaultMemberAuthId).list();
        defaultAdminAuthNodes.forEach(projectAuthNode -> {
            projectAuthNodeService.save(ProjectAuthNode.builder().auth(defaultAdminAuthSave.getId()).node(projectAuthNode.getNode()).build());
        });
        defaultMemberAuthNode.forEach(projectAuthNode -> {
            projectAuthNodeService.save(ProjectAuthNode.builder().auth(defaultMemberAuthSave.getId()).node(projectAuthNode.getNode()).build());
        });
        MemberAccount defaultMemberAccount = MemberAccount.builder().position(member.getPosition())
                .department("")
                .code(CommUtils.getUUID())
                .member_code(member.getCode())
                .organization_code(member.getOrgCode())
                .is_owner(0).status(1)
                .create_time(DateUtil.getCurrentDateTime())
                .name(member.getName())
                .email(member.getEmail())
                .description(member.getDescription())
                .mobile(member.getMobile())
                .authorize(String.valueOf(defaultMemberAuthSave.getId()))
                .build();
        MemberAccount adminMemberAccount =
                MemberAccount.builder()
                        .member_code(member.getCode())
                        .organization_code(organization.getCode())
                        .position("资深工程师")
                        .mobile(member.getMobile())
                        .description(member.getDescription())
                        .authorize(String.valueOf(defaultAdminAuthSave.getId())).status(1)
                        .create_time(DateUtil.getCurrentDateTime())
                        .is_owner(1).name(member.getName()).email(member.getEmail())
                        .code(CommUtils.getUUID())
                        .mobile(member.getMobile())
                        .department("某某公司－某某某事业群－某某平台部－某某技术部－BM")
                        .build();
        memberAccountService.save(defaultMemberAccount);
        memberAccountService.save(adminMemberAccount);
        return defaultMemberAccount;
    }

}
