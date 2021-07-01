package com.projectm.org.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.framework.security.util.UserUtil;
import com.projectm.common.DateUtil;
import com.projectm.login.entity.LoginUser;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.service.MemberAccountService;
import com.projectm.member.service.MemberService;
import com.projectm.org.domain.Department;
import com.projectm.org.domain.DepartmentMember;
import com.projectm.org.mapper.OrgMapper;
import com.projectm.project.domain.ProjectAuth;
import com.projectm.project.domain.ProjectMenu;
import com.projectm.project.service.ProjectAuthService;
import com.projectm.project.service.ProjectMenuService;
import com.projectm.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrgService{

    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMemberService departmentMemberService;
    @Autowired
    private ProjectAuthService projectAuthService;
    @Autowired
    private ProjectMenuService projectMenuService;

    public List<Map> selectOrgByMemCode(Map params) {
        return orgMapper.selectOrgByMemCode(params);
    }

    public List<Map> _getOrgList(Map params){
        return orgMapper._getOrgList(params);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<MemberVo> searchInviteMember(String organizationcode, String keyword, String departmentCode) {
        if (StrUtil.isEmpty(departmentCode)) {
            List<Member> memberList = memberService.lambdaQuery().select(Member::getCode, Member::getName, Member::getEmail, Member::getAvatar)
                    .like(Member::getName, keyword).or().eq(Member::getEmail, keyword).list();
            if (CollUtil.isNotEmpty(memberList)) {
                List<String> memberCodeList = memberList.parallelStream().map(Member::getCode).collect(Collectors.toList());
                Map<String, MemberAccount> memberMap = memberAccountService.lambdaQuery().in(MemberAccount::getMember_code, memberCodeList)
                        .eq(MemberAccount::getOrganization_code, organizationcode).list()
                        .parallelStream().collect(Collectors.toMap(MemberAccount::getMember_code, o -> o));
                List<MemberVo> memberVoList = new ArrayList<>();
                memberList.forEach(o -> {
                    MemberVo memberVo;
                    MemberVo.MemberVoBuilder voBuilder = MemberVo.builder().name(o.getName()).accountCode(o.getCode()).avatar(o.getAvatar()).email(o.getEmail());
                    if (ObjectUtil.isNotEmpty(memberMap.get(o.getCode()))) {
                        memberVo = voBuilder.joined(true).build();
                    } else {
                        memberVo = voBuilder.joined(false).build();
                    }
                    memberVoList.add(memberVo);
                });
                return memberVoList;
            }
        } else {
            List<MemberAccount> memberAccountList = memberAccountService.lambdaQuery().eq(MemberAccount::getOrganization_code, organizationcode)
                    .like(MemberAccount::getName, keyword).or().eq(MemberAccount::getEmail, keyword).list();
            List<MemberVo> memberVoList = new ArrayList<>();
            memberAccountList.forEach(o -> {
                MemberVo memberVo;
                MemberVo.MemberVoBuilder voBuilder = MemberVo.builder().name(o.getName()).accountCode(o.getCode()).avatar(o.getAvatar()).email(o.getEmail());
                
                if (StrUtil.isNotEmpty(o.getDepartment_code()) && o.getDepartment_code().contains(departmentCode)) {
                    memberVo = voBuilder.joined(true).build();
                } else {
                    memberVo = voBuilder.joined(false).build();
                }
                memberVoList.add(memberVo);
            });
            return memberVoList;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public String inviteMember(String organizationcode, String accountCode, String departmentCode) {
        if (StrUtil.isNotEmpty(departmentCode)) {
            Department department = departmentService.lambdaQuery().eq(Department::getCode, departmentCode).one();
            MemberAccount one = memberAccountService.lambdaQuery().eq(MemberAccount::getCode, accountCode).one();
            DepartmentMember saveDepartMember = DepartmentMember.builder().code(IdUtil.fastSimpleUUID()).department_code(departmentCode).organization_code(organizationcode)
                    .account_code(accountCode).is_owner(one.getIs_owner()).is_principal(one.getIs_owner()).authorize(one.getAuthorize())
                    .join_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME))).build();
            boolean save = departmentMemberService.save(saveDepartMember);
            String depCode = StrUtil.isNotEmpty(one.getDepartment_code()) ? one.getDepartment_code() + "," + department.getCode() : department.getCode();
            String depStr = StrUtil.isNotEmpty(one.getDepartment()) ? one.getDepartment() + "-" + department.getName() : department.getName();
            boolean update = memberAccountService.lambdaUpdate().set(MemberAccount::getDepartment, depStr).set(MemberAccount::getDepartment_code, depCode).eq(MemberAccount::getCode, accountCode).update();
            log.info("保存新部门：{}，更新用户信息：{}", save, update);
            return null;
        } else {
            ProjectAuth projectAuth = projectAuthService.lambdaQuery().select(ProjectAuth::getId).eq(ProjectAuth::getOrganization_code, organizationcode)
                    .eq(ProjectAuth::getIs_default, "1").one();
            Member one = memberService.lambdaQuery().eq(Member::getCode, accountCode).one();
            MemberAccount saveMemberAccount = MemberAccount.builder().code(IdUtil.fastSimpleUUID()).member_code(accountCode).organization_code(organizationcode)
                    .authorize(projectAuth.getId().toString()).name(one.getName()).mobile(one.getMobile()).email(one.getEmail()).avatar(one.getAvatar())
                    .status(1).create_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME))).build();
            boolean save = memberAccountService.save(saveMemberAccount);
            log.info("添加用户到组织：{}", save);
            return null;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public Object removeMember(String organizationcode, String accountCode, String departmentCode) {
        MemberAccount one = memberAccountService.lambdaQuery().eq(MemberAccount::getCode, accountCode).one();
        List<String> depList = Arrays.stream(one.getDepartment_code().split(",")).collect(Collectors.toList());
//        for (String str : depList) {
//            if (StrUtil.equals(str, departmentCode)) {
//                depList.remove(str);
//            }
//        }
       depList.removeIf(str -> StrUtil.equals(str, departmentCode));

        String depCode = null;
        String depStr = null;
        if (CollUtil.isNotEmpty(depList)) {
            List<Department> list = departmentService.lambdaQuery().select(Department::getCode, Department::getName).in(Department::getCode, depList).list();
            depCode = list.stream().map(Department::getCode).collect(Collectors.joining(","));
            depStr = list.stream().map(Department::getName).collect(Collectors.joining("-"));
        }
        boolean remove = departmentMemberService.remove(Wrappers.<DepartmentMember>lambdaQuery().eq(DepartmentMember::getAccount_code, accountCode)
                .eq(DepartmentMember::getDepartment_code, departmentCode).eq(DepartmentMember::getOrganization_code, organizationcode));
        boolean update = memberAccountService.lambdaUpdate().set(MemberAccount::getDepartment, depStr).set(MemberAccount::getDepartment_code, depCode).eq(MemberAccount::getCode, accountCode).update();
        log.info("移除部门：{}，更新用户信息：{}", remove, update);
        return null;
    }

    public Map<String, Object> getCurrentUserMenu(String orgCode) {
        List<ProjectMenu> menuList = projectMenuService.getCurrentUserMenu();
        Member member = UserUtil.getLoginUser().getUser();
        Map<String, Object> result = new HashMap<>(4);
        result.put("menuList", menuList);
        result.put("member", member);
        return result;


    }
}
