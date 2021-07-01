package com.projectm.org.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.framework.common.utils.security.Md5Utils;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.common.ListUtils;
import com.projectm.config.MProjectConfig;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.mapper.MemberAccountMapper;
import com.projectm.member.service.MemberAccountService;
import com.projectm.member.service.MemberService;
import com.projectm.org.domain.Department;
import com.projectm.org.domain.DepartmentMember;
import com.projectm.org.mapper.DepartmentMapper;
import com.projectm.org.mapper.DepartmentMemberMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class DepartmentMemberService extends ServiceImpl<DepartmentMemberMapper, DepartmentMember> {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    MemberAccountService memberAccountService;
    @Autowired
    MemberService memberService;
    public DepartmentMember getDepartmentMember(String accountCode,String deptCode){
        LambdaQueryWrapper<DepartmentMember> dmWQ = new LambdaQueryWrapper<DepartmentMember>();
        dmWQ.eq(DepartmentMember::getAccount_code,accountCode);
        dmWQ.eq(DepartmentMember::getDepartment_code,deptCode);
        return baseMapper.selectOne(dmWQ);
    }

    @Autowired
    MemberAccountMapper memberAccountMapper;
    public Object inviteMember(String accountCode, String deptCode, Integer isOwner, Integer isPrincipal, String orgCode){
        if(StringUtils.isNotEmpty(deptCode)){
            Map departMent = departmentService.getDepartmentByCode(deptCode);
            if(ObjectUtils.isEmpty(departMent)){
                throw new CustomException("该部门不存在");
            }
            DepartmentMember ma = getDepartmentMember(accountCode,deptCode);
            if(!ObjectUtils.isEmpty(ma)){
                throw new CustomException("已加入该部门");
            }
            ma = DepartmentMember.builder().code(CommUtils.getUUID()).account_code(accountCode).organization_code(orgCode)
                    .department_code(deptCode).is_owner(isOwner).is_principal(isPrincipal)
                    .join_time(DateUtil.getCurrentDateTime()).build();
            save(ma);
            List<String> departmentCodes = baseMapper.selectDepartmentCodes(accountCode,orgCode);
            if(null !=departmentCodes && departmentCodes.size()>0){
                String depCodes = StringUtils.join(departmentCodes,",");
                memberAccountMapper.updateDepartCodeByCode(depCodes,accountCode);
            }
            return ma;
        }else{
            return memberAccountService.inviteMember(MemberAccount.builder().member_code(accountCode).organization_code(orgCode).build());
        }
    }

    @Transactional
    public void uploadFile(String orgCode, InputStream ins) {
        int dataStartRow = 4;
        Excel07SaxReader reader = new Excel07SaxReader(createRowHandler(dataStartRow,orgCode));
        reader.read(ins,0);
    }
    @Value("${mproject.downloadServer}")
    private String downloadServer;
    @Value("${mproject.projectImg}")
    private String projectImg;
    @Value("${mproject.userImg}")
    private String userImg;

    private RowHandler createRowHandler(int dataStartRow, String orgCode) {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
                String downloadUrl = "/common/download?filePathName="+ MProjectConfig.getProfile()+"/"+userImg+"&realFileName="+userImg;
                if(rowIndex>=dataStartRow-1){
                    Member member = new Member();
                    member.setName(ListUtils.getValue(rowlist,0,String.class));
                    member.setEmail(ListUtils.getValue(rowlist,1,String.class));
                    if(StringUtils.isEmpty(member.getName())||StringUtils.isEmpty(member.getEmail())){
                        return;
                    }
                    LambdaQueryWrapper<Member> memberWQ = new LambdaQueryWrapper<>();
                    memberWQ.eq(Member::getEmail,member.getEmail());
                    //member = memberService.getOne(memberWQ);
                    //MemberAccount memberAccount=null;
                    //if(ObjectUtils.isEmpty(member)){
                        String account = ListUtils.getValue(rowlist,5,String.class);
                        if(!account.matches("[_0-9a-zA-Z]+")){
                            throw new CustomException("账户"+account+"不合法，登录账户必须以英文、数字和下划线'_'组合！");
                        }
                        Member tempMember = memberService.lambdaQuery().eq(Member::getAccount,account).one();
                        if(ObjectUtil.isNotEmpty(tempMember)){
                            throw new CustomException("账户"+account+"已存在！");
                        }
                        member = new Member();
                        member.setName(ListUtils.getValue(rowlist,0,String.class));
                        member.setEmail(ListUtils.getValue(rowlist,1,String.class));
                        member.setDepartment(ListUtils.getValue(rowlist,2,String.class));
                        member.setPosition(ListUtils.getValue(rowlist,3,String.class));
                        member.setMobile(ListUtils.getValue(rowlist,4,String.class));
                        member.setPassword(ListUtils.getValue(rowlist,6,String.class));
                        member.setDescription(ListUtils.getValue(rowlist,7,String.class));
                        member.setCode(CommUtils.getUUID());
                        member.setPassword(Md5Utils.hash(Md5Utils.hash(member.getPassword())));
                        member.setAvatar(downloadUrl + downloadUrl);
                        member.setAccount(account);member.setStatus(1);
                        member.setOrgCode(orgCode);

                        MemberAccount defaultMemberAccount = memberService.createMember(member);


                    /*}else{
                        LambdaQueryWrapper<MemberAccount> memberAcWQ = new LambdaQueryWrapper<>();
                        memberAcWQ.eq(MemberAccount::getMember_code,member.getCode());
                        memberAcWQ.eq(MemberAccount::getOrganization_code,orgCode);
                        memberAccount = memberAccountService.getOne(memberAcWQ);
                        if(ObjectUtils.isEmpty(memberAccount)){
                            memberAccount = memberAccountService.inviteMember(MemberAccount.builder().member_code(member.getCode()).organization_code(orgCode)
                                    .position(ListUtils.getValue(rowlist,3,String.class)).mobile(ListUtils.getValue(rowlist,4,String.class)).description(ListUtils.getValue(rowlist,6,String.class)).build());
                        }
                    }*/
                    String department = ListUtils.getValue(rowlist,2,String.class);
                    if(StringUtils.isNotEmpty(department)){
                        String[] deps = department.split(";");
                        if(null != deps){
                            for(String dep:deps){
                                String[] deptNames = dep.split("/");
                                if(null != deptNames){
                                    Department departMent = null;
                                    String pcode = null;
                                    for(String depName:deptNames){
                                        departMent = departmentService.getDept(depName,"",orgCode);
                                        if(!ObjectUtils.isEmpty(departMent)){
                                            break;
                                        }
                                        //pcode=departMent.getCode();
                                    }
                                    if(!ObjectUtils.isEmpty(departMent)){
                                        inviteMember(defaultMemberAccount.getCode(), departMent.getCode(),0, 0, orgCode);
                                    }
                                }
                            }
                        }else{
                            inviteMember(defaultMemberAccount.getCode(), "",0, 0, orgCode);
                        }
                    }
                }
            }
        };
    }
}
