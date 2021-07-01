package com.projectm.member.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.mapper.CommMapper;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.mapper.MemberAccountMapper;
import com.projectm.project.domain.ProjectAuth;
import com.projectm.project.mapper.ProjectAuthMapper;
import com.projectm.project.mapper.ProjectMapper;
import com.projectm.project.service.ProjectAuthService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberAccountService extends ServiceImpl<MemberAccountMapper,MemberAccount> {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CommMapper commMapper;

    //根据orgCode获取memberCount
    public List<Map> getMemberCountByOrgCode(String orgCode){
        return  baseMapper.getMemberCountByOrgCode(orgCode);
    }
    //根据orgCode和name模糊查询获取memberCount
    public List<Map> getMemberCountByOrgCodeAndMemberName(String orgCode,String name){
        return  baseMapper.getMemberCountByOrgCodeAndMemberName(orgCode,name);
    }

    public  Map getMemberAccountByMemCodeAndOrgCode(String memberCode,String orgCode){
        return baseMapper.selectMemberAccountByMemCodeAndOrgCode(memberCode,orgCode);
    }

    public IPage<Map> getMemberAccountByOrgCodeStatusDeptCode(IPage page, Map params){
        return baseMapper.selectMemberAccountByOrgCodeStatusDeptCode(page,params);
    }

    public IPage<Map> getMemberAccountByOrgCodeAndStatus(IPage page, Map params){
        return baseMapper.selectMemberAccountByOrgCodeAndStatus(page,params);
    }

    //Account.php  public function index()
    public IPage<Map> getAccountIndex(IPage<Map> page,Map params,String orgCode) {
        String memberCode = MapUtils.getString(params, "memberCode");
        String departmentCode = MapUtils.getString(params, "departmentCode");
        String account = MapUtils.getString(params, "account");
        String mobile = MapUtils.getString(params, "mobile");
        String email = MapUtils.getString(params, "email");
        String keyword = MapUtils.getString(params, "keyword");
        Integer searchType = MapUtils.getInteger(params, "searchType", -1);
        String sql = " select * from team_member_account a where a.organization_code = '"+ orgCode +"' and 1=1 ";
        if(StringUtils.isNotEmpty(keyword)){
            sql += " and a.name like '%"+keyword+"%' ";
        }
        if(1==searchType){
            sql += " and a.status = 1";
        }else if(2==searchType){
            sql+= " and a.department_code = '' ";
        }else if(3==searchType){
            sql += " and a.status=0 ";
        }else if(4==searchType){
            sql += "  and a.status=1 ";
            sql += " and a.department_code like '%"+departmentCode+"%' ";
        }else{
            sql += "  and a.status=1 ";
        }
        if(StringUtils.isNotEmpty(account)){
            sql += " and a.account like '%"+account+"% ";
        }
        if(StringUtils.isNotEmpty(mobile)){
            sql += " and a.mobile like '%"+mobile+"% ";
        }
        if(StringUtils.isNotEmpty(email)){
            sql += " and a.email like '%"+email+"% ";
        }
        sql += " order by id asc";

        return commMapper.customQueryItem(page,sql);
    }

    public Map getMemberAccountByCode(String code){
        return baseMapper.selectMemberAccountByCode(code);
    }

    @Transactional
    public Integer memberAccountDel(String accountCode,String orgCode){

        Map memAccountMap = baseMapper.selectMemberAccountByCode(accountCode);
        List<Map> listMapProject = projectMapper.selectProjectByOrgCode(orgCode);
        List<String> projectCodes = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(listMapProject)){
            for(Map m:listMapProject){
                projectCodes.add(MapUtils.getString(m,"code"));
            }
        }
//        Map params = new HashMap();
//        params.put("proCodeList",projectCodes);
//        params.put("memCode",MapUtils.getString(memAccountMap,"member_code"));
        Integer delProjectMemberResult = projectMapper.delProjectMember(projectCodes,MapUtils.getString(memAccountMap,"member_code"));
        //Integer delProjectMemberResult = projectMapper.delProjectMember(params);


        Integer delMemberAccountResult = baseMapper.deleteById(MapUtils.getInteger(memAccountMap,"id"));

        Integer delDeptMemberAccountResult=baseMapper.deleteDepartmentMemberByAccCodeAndOrgCode(accountCode,orgCode);

        return delProjectMemberResult+delMemberAccountResult+delDeptMemberAccountResult;

    }

    public Map getMemberAccountByMemCode(String memCode){
        return baseMapper.selectMemberAccountByMemCode(memCode);
    }

    @Autowired
    MemberService memberService;
    @Autowired
    ProjectAuthMapper projectAuthMapper;

    public MemberAccount inviteMember(MemberAccount memberAccount){

        MemberAccount hasJoined =lambdaQuery().eq(MemberAccount::getMember_code,memberAccount.getMember_code())
                .eq(MemberAccount::getOrganization_code,memberAccount.getOrganization_code()).one();
        if(ObjectUtils.isNotEmpty(hasJoined) && ObjectUtils.isNotEmpty(hasJoined.getId())){
            return memberAccount;
        }
        Member memberDate = memberService.lambdaQuery().eq(Member::getCode,memberAccount.getMember_code())
                .one();
        if(ObjectUtil.isEmpty(memberDate)){
            throw new CustomException("该用户不存在");
        }
        LambdaQueryWrapper<ProjectAuth> projectAuthWQ = new LambdaQueryWrapper<>();
        projectAuthWQ.eq(ProjectAuth::getOrganization_code,memberAccount.getOrganization_code());
        projectAuthWQ.eq(ProjectAuth::getIs_default,1);
        ProjectAuth pa = projectAuthMapper.selectOne(projectAuthWQ);
        if(ObjectUtils.isNotEmpty(pa)){
            memberAccount.setAuthorize(String.valueOf(pa.getId()));
        }
        memberAccount.setCode(CommUtils.getUUID());
        memberAccount.setIs_owner(0);
        memberAccount.setStatus(1);
        memberAccount.setCreate_time(DateUtil.getCurrentDateTime());
        memberAccount.setName(memberDate.getName());
        memberAccount.setEmail(memberDate.getEmail());
        save(memberAccount);
        return memberAccount;
    }
}
