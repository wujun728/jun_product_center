package com.projectm.member.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.DateUtils;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.config.MProjectConfig;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.mapper.MemberAccountMapper;
import com.projectm.member.mapper.MemberMapper;
import com.projectm.member.mapper.ProjectMemberMapper;
import com.projectm.org.domain.Organization;
import com.projectm.org.mapper.OrganizationMapper;
import com.projectm.org.service.DepartmentMemberService;
import com.projectm.org.service.OrganizationService;
import com.projectm.system.service.SystemConfigService;
import com.projectm.task.service.TaskMemberService;
import com.projectm.task.service.TaskService;

import cn.hutool.core.collection.CollUtil;

@Service
public class MemberService extends ServiceImpl<MemberMapper, Member> {

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Autowired
    private MemberAccountMapper memberAccountMapper;

    @Autowired
    private MemberAccountService memberAccountService;

    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    SystemConfigService systemConfigService;

    @Autowired
    TaskService taskService;
    @Autowired
    ProjectMemberService projectMemberService;

    @Autowired
    TaskMemberService taskMemberService;

    public List<Map> selectMemberByLoginParam(Map params) {
        return projectMemberMapper.selectMemberByLoginParam(params);
    }

    public List<Map> selectMemberCountByMemberCode(Map params){
        return projectMemberMapper.selectMemberCountByMemberCode(params);
    }
    //根据用户编号，查询用户信息
    public Map getMemberById(String userCode){
        List<Map> listMap = projectMemberMapper.getMemberById(userCode);
        if(null != listMap && listMap.size() > 0){
            return listMap.get(0);
        }
        return null;
    }

    //根据memberCode获取member信息
    public Member getMemberByCode(String memberCode){
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getCode, memberCode);
        return baseMapper.selectOne(queryWrapper);
    }


    //根据memberCode获取member信息
    public Map getMemberMapByCode(String memberCode){
        return baseMapper.selectMemberByCode(memberCode);
    }

    @Transactional
    public Integer updateMemberAccountAndMember(MemberAccount ma,Member m){
        Integer i1 = baseMapper.updateById(m);
        Integer i2 = memberAccountMapper.updateById(ma);
        return i1+i2;
    }
    @Value("${mproject.downloadServer}")
    private String downloadServer;

    @Transactional
    public Map uploadAvatar(String memberCode,String originFileName,InputStream in){
        Map resMap = new HashMap();
        String uuid = CommUtils.getUUID();
        String date = DateUtils.dateTimeNow("yyyyMMdd");
        String file_url = MProjectConfig.getProfile()+"/member/avatar/"+memberCode+"/"+date+"/";
        String uploadFileName = uuid+"-"+originFileName;
        try {
            // 这里使用Apache的FileUtils方法来进行保存
            FileUtils.copyInputStreamToFile(in, new File(file_url, uploadFileName));
            String base_url = "/member/avatar/"+memberCode+"/"+date+"/"+uploadFileName;
            String downloadUrl = "/common/image?filePathName="+base_url+"&realFileName="+originFileName;
            resMap.put("base_url", base_url);
            resMap.put("url",downloadServer+downloadUrl);
            resMap.put("filename", uploadFileName);
            memberAccountService.lambdaUpdate().eq(MemberAccount::getMember_code,memberCode)
                    .set(MemberAccount::getAvatar,downloadServer+downloadUrl).update();
            lambdaUpdate().eq(Member::getCode,memberCode).set(Member::getAvatar,downloadServer+downloadUrl).update();

        } catch (IOException e) {
            throw new CustomException(e.getMessage());
        }
        return resMap;
    }
    public Member getMemberByName(String account){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        return baseMapper.selectOne(queryWrapper);
    }

    public List<Organization> getOrgList(String memberCode){
        List<MemberAccount> list = memberAccountService.lambdaQuery().select(MemberAccount::getOrganization_code).eq(MemberAccount::getMember_code, memberCode).list();
        if (CollUtil.isNotEmpty(list)) {
            List<String> orgList = list.parallelStream().map(MemberAccount::getOrganization_code).collect(Collectors.toList());
            return organizationMapper.selectList(Wrappers.<Organization>lambdaQuery().in(Organization::getCode, orgList));
        } else {
            throw new CustomException("此用户没有组织，请先添加到某组织");
        }
    }

    /*public List<Organization> getOrgList(String memberCode,boolean newest){
        List<Organization> listResult = new ArrayList<>();
        if(StringUtils.isEmpty(memberCode)){
            return listResult;
        }
        List<MemberAccount> memberAccountList=memberAccountService.lambdaQuery().eq(MemberAccount::getMember_code,memberCode).orderByAsc(MemberAccount::getId).list();
        if(!CollectionUtils.isEmpty(memberAccountList)){


        }
    }*/



    @Autowired
    DepartmentMemberService departmentMemberService;
    @Autowired
    OrganizationService organizationService;

    @Transactional
    public MemberAccount createMember(Member member){
        member.setCreate_time(DateUtil.getCurrentDateTime());
        save(member);
        return organizationService.createOrganization(member);
    }
}
