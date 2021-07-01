package com.projectm.member.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.AjaxResult;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.ServletUtils;
import com.framework.security.util.UserUtil;
import com.framework.utils.FileUtils;
import com.projectm.common.CommUtils;
import com.projectm.common.Constant;
import com.projectm.common.ExcelUtils;
import com.projectm.config.MProjectConfig;
import com.projectm.member.domain.Member;
import com.projectm.member.domain.MemberAccount;
import com.projectm.member.domain.ProjectMember;
import com.projectm.member.service.MemberAccountService;
import com.projectm.member.service.MemberService;
import com.projectm.member.service.ProjectMemberService;
import com.projectm.org.service.DepartmentService;
import com.projectm.project.domain.Project;
import com.projectm.project.service.ProjectAuthService;
import com.projectm.project.service.ProjectService;
import com.projectm.task.domain.Task;
import com.projectm.task.service.TaskMemberService;
import com.projectm.task.service.TaskService;
import com.projectm.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class MemberController  extends BaseController {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberAccountService memberAccountService;

    @Autowired
    private ProjectAuthService projectAuthService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    /**
     * 账号停用
     * @param mmap
     * @return
     */
    @PostMapping("/index/editPersonal")
    @ResponseBody
    public AjaxResult indexEditPersonal(@RequestParam Map<String,Object> mmap) {
        String name = MapUtils.getString(mmap,"name");
        String description = MapUtils.getString(mmap,"description");
        String code = MapUtils.getString(mmap,"code");
        String avatar = MapUtils.getString(mmap,"avatar");
        Map memberMap = memberService.getMemberMapByCode(code);
        Map memberAccountMap = memberAccountService.getMemberAccountByMemCode(code);
        Member member = new Member();
        MemberAccount memberAccount = new MemberAccount();
        member.setId(MapUtils.getInteger(memberMap,"id"));member.setName(name);member.setDescription(description);
        member.setAvatar(avatar);
        memberAccount.setId(MapUtils.getInteger(memberAccountMap,"id"));
        memberAccount.setAvatar(avatar);
        return AjaxResult.success("基本信息更新成功",memberService.updateMemberAccountAndMember(memberAccount,member));
    }

    @Autowired
    TaskService taskService;
    @Autowired
    TaskMemberService taskMemberService;

    @PostMapping("/task_member/inviteMemberBatch")
    @ResponseBody
    public AjaxResult taskMemberInviteMemberBatch(@RequestParam Map<String,Object> mmap) {
        String memberCodes = MapUtils.getString(mmap,"memberCodes");
        String taskCode = MapUtils.getString(mmap,"taskCode");
        if(StringUtils.isEmpty(memberCodes) || StringUtils.isEmpty(taskCode)){
            return AjaxResult.warn("数据异常！");
        }
        Map loginMember = getLoginMember();
        Task task = taskService.getTaskByCode(taskCode);
        if(ObjectUtils.isEmpty(task)){
            return AjaxResult.warn("该任务已失效！");
        }
        taskMemberService.inviteMemberBatch(memberCodes,taskCode);
        return AjaxResult.success();
    }

    /**
     * 账号停用
     * @param mmap
     * @return
     */
    @PostMapping("/account/forbid")
    @ResponseBody
    public AjaxResult setAccountForbid(@RequestParam Map<String,Object> mmap) {
        String accountCode = MapUtils.getString(mmap,"accountCode");
        Integer status = MapUtils.getInteger(mmap,"status");

        MemberAccount ma = new MemberAccount();
        Map memberAccountMap = memberAccountService.getMemberAccountByCode(accountCode);
        if(MapUtils.isNotEmpty(memberAccountMap)){
            ma.setId(MapUtils.getInteger(memberAccountMap,"id"));
            ma.setStatus(status);
            AjaxResult.success(memberAccountService.updateById(ma));
        }
        return AjaxResult.success();
    }

    /**
     * 账号启用
     * @param mmap
     * @return
     */
    @PostMapping("/account/resume")
    @ResponseBody
    public AjaxResult setAccountResume(@RequestParam Map<String,Object> mmap) {
        String accountCode = MapUtils.getString(mmap,"accountCode");
        Integer status = MapUtils.getInteger(mmap,"status");

        MemberAccount ma = new MemberAccount();
        Map memberAccountMap = memberAccountService.getMemberAccountByCode(accountCode);
        if(MapUtils.isNotEmpty(memberAccountMap)){
            ma.setId(MapUtils.getInteger(memberAccountMap,"id"));
            ma.setStatus(status);
            AjaxResult.success(memberAccountService.updateById(ma));
        }
        return AjaxResult.success();
    }

    /**
     * 账号删除
     * @param mmap
     * @return
     */
    @PostMapping("/account/del")
    @ResponseBody
    public AjaxResult accountDel(@RequestParam Map<String,Object> mmap) {
        String accountCode = MapUtils.getString(mmap,"accountCode");
        Map loginMember = getLoginMember();

        memberAccountService.memberAccountDel(accountCode,MapUtils.getString(loginMember,"organizationCode"));
        return AjaxResult.success();
    }

    /**
     * 账号编辑
     * @param mmap
     * @return
     */
    @PostMapping("/account/edit")
    @ResponseBody
    public AjaxResult accountEdit(@RequestParam Map<String,Object> mmap) {
        String name = MapUtils.getString(mmap,"name");
        String mobile = MapUtils.getString(mmap,"mobile");
        String email = MapUtils.getString(mmap,"email");
        String description = MapUtils.getString(mmap,"description");
        String code = MapUtils.getString(mmap,"code");


        if(!CommUtils.isChinaPhoneLegal(mobile)){
            //return AjaxResult.warn("手机号错误！");
        }

        Map memAccMap = memberAccountService.getMemberAccountByCode(code);
        if(MapUtils.isNotEmpty(memAccMap)){
            MemberAccount ma = new MemberAccount();
            ma.setId(MapUtils.getInteger(memAccMap,"id"));
            ma.setName(name);ma.setMobile(mobile);ma.setEmail(email);ma.setDescription(description);
           return AjaxResult.success(memberAccountService.updateById(ma));
        }
        return AjaxResult.success();
    }

    /**
     * 账号编辑
     * @param mmap
     * @return
     */
    @PostMapping("/account/auth")
    @ResponseBody
    public AjaxResult accountAuth(@RequestParam Map<String,Object> mmap) {
        Integer auth = MapUtils.getInteger(mmap,"auth");
        Integer id = MapUtils.getInteger(mmap,"id");

        MemberAccount ma = new MemberAccount();
        ma.setId(id);
        ma.setAuthorize(String.valueOf(auth));
        return AjaxResult.success(memberAccountService.updateById(ma));
    }

    /**
     *首页 > 成员 >vilson >任务安排
     * @param mmap
     * @return
     */
    @PostMapping("/account/read")
    @ResponseBody
    public AjaxResult accountRead(@RequestParam Map<String,Object> mmap) {
        String code = MapUtils.getString(mmap,"code");
        Map memberAccountMap= memberAccountService.getMemberAccountByCode(code);
        String[] depCodeArr ,authorizeArr= null;
        Map depMap = null;
        String  depCodes = MapUtils.getString(memberAccountMap,"department_code","");
        String authorize = MapUtils.getString(memberAccountMap,"authorize","");
        depCodeArr = depCodes.split(",");
        authorizeArr = authorize.split(",");
        Integer status = MapUtils.getInteger(memberAccountMap,"status",-1);
        if(status == 0){
            memberAccountMap.put("statusText","禁用");
        }else if(status == 1){
            memberAccountMap.put("statusText","使用中");
        }
        String depName = "";
        if(null != depCodeArr) {
            for (String deptCode : depCodeArr) {
                depMap = departmentService.getDepartmentByCode(deptCode);
                if (MapUtils.isNotEmpty(depMap)) {
                    depName = ("".equals(depName)) ? MapUtils.getString(depMap,"name") : (depName + "-" + MapUtils.getString(depMap,"name") );
                }
            }
        }
        memberAccountMap.put("departments",depName);
        List authorizeArrList  = new ArrayList();
        if(null != authorizeArr){
            Map authMap = null;
            for(int i=0;i<authorizeArr.length;i++){
                authMap = new HashMap();
                authMap.put(i,authorizeArr[i]);
                authorizeArrList.add(authMap);
            }
        }
        memberAccountMap.put("authorizeArr",authorizeArrList);
        return AjaxResult.success(memberAccountMap);
    }

    /**
     *
     * @param mmap
     * @return
     */
    @PostMapping("/account/index")
    @ResponseBody
    public AjaxResult accountIndex(@RequestParam Map<String,Object> mmap) {

        String departmentCode = MapUtils.getString(mmap, "departmentCode");
        Map loginMember = getLoginMember();
        String orgCode = MapUtils.getString(loginMember,"organizationCode");
        Integer searchType = MapUtils.getInteger(mmap, "searchType",-1);
        IPage<Map> ipage = Constant.createPage(mmap);
        ipage = memberAccountService.getAccountIndex(ipage,mmap,orgCode);
        /*Map params = new HashMap();
        Map resultData = new HashMap();
        params.put("orgCode",orgCode);
        switch (searchType){
            case 1:{
                params.put("status",1);
                resultPage =  memberAccountService.getMemberAccountByOrgCodeAndStatus(ipage,params);
                break;
            }
            case 2:{
                params.put("depCode","");
                resultPage =  memberAccountService.getMemberAccountByOrgCodeStatusDeptCode(ipage,params);
                break;
            }
            case 3:{
                params.put("status",0);
                resultPage =  memberAccountService.getMemberAccountByOrgCodeAndStatus(ipage,params);
                break;
            }
            case 4:{
                params.put("status",1);
                params.put("depCode",departmentCode);
                resultPage =  memberAccountService.getMemberAccountByOrgCodeStatusDeptCode(ipage,params);
                break;
            }
            default:{
                params.put("status",1);
                resultPage =  memberAccountService.getMemberAccountByOrgCodeAndStatus(ipage,params);
            }
        }*/

        List<Map> records = ipage.getRecords();
        List<Map> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(records)){
            String depCodes,authorize = null;
            String[] depCodeArr ,authorizeArr= null;
            Map depMap = null;
            for(Map map:records){
                depCodes = MapUtils.getString(map,"department_code","");
                authorize = MapUtils.getString(map,"authorize","");

                map.put("membar_account_code",MapUtils.getString(map,"code"));
                depCodeArr = depCodes.split(",");
                authorizeArr = authorize.split(",");
                Integer status = MapUtils.getInteger(map,"status",-1);
                if(status == 0){
                    map.put("statusText","禁用");
                }else if(status == 1){
                    map.put("statusText","使用中");
                }
                String depName = "";
                if(null != depCodeArr) {
                    for (String deptCode : depCodeArr) {
                        depMap = departmentService.getDepartmentByCode(deptCode);
                        if (MapUtils.isNotEmpty(depMap)) {
                            depName = ("".equals(depName)) ? MapUtils.getString(depMap,"name") : (depName + "-" + MapUtils.getString(depMap,"name") );
                        }
                    }
                }
                map.put("departments",depName);
                List authorizeArrList  = new ArrayList();
                if(null != authorizeArr){
                    Map authMap = null;
                    for(int i=0;i<authorizeArr.length;i++){
                        authMap = new HashMap();
                        authMap.put(i,authorizeArr[i]);
                        authorizeArrList.add(authMap);
                    }
                }
                map.put("authorizeArr",authorizeArr);

                resultList.add(map);
            }
        }
        ipage.setRecords(resultList);
        Map resultData = Constant.createPageResultMap(ipage);
        List<Map> listProjectAuth = projectAuthService.getProjectAuthByStatusAndOrgCode("1",orgCode);
        List<Map> authList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(listProjectAuth)){
            for(Map map:listProjectAuth){
                map.put("id", map.get("id").toString());
                String type = MapUtils.getString(map,"type");
                if("admin".equals(type) || "member".equals(type)){
                    map.put("canDelete",0);
                }else{
                    map.put("canDelete",1);
                }
                authList.add(map);
            }
        }
        resultData.put("authList",authList);

        return AjaxResult.success(resultData);
    }

    /**
     * 项目管理	我的项目 邀请新成员 模糊查询
     * @param mmap
     * @return
     */
    @PostMapping("/project_member/searchInviteMember")
    @ResponseBody
    public AjaxResult searchInviteMember(@RequestParam Map<String,Object> mmap) {
        Map loginMember = getLoginMember();
        String projectCode = MapUtils.getString(mmap, "projectCode");
        String orgCode = MapUtils.getString(loginMember,"organizationCode");
        String keyword = MapUtils.getString(mmap,"keyword");

        List<Map> listMemberAccounts = memberAccountService.getMemberCountByOrgCodeAndMemberName(orgCode,keyword);
        List<Map> resultData = new ArrayList<>();
        if(!CollectionUtils.isEmpty(listMemberAccounts)){
            Map tmpMap = null;
            for(Map cm:listMemberAccounts){
                tmpMap = new HashMap();
                tmpMap.put("memberCode",MapUtils.getString(cm, "member_code",""));
                tmpMap.put("status",MapUtils.getString(cm, "status",""));
                tmpMap.put("avatar",MapUtils.getString(cm, "avatar",""));
                tmpMap.put("name",MapUtils.getString(cm, "name",""));
                tmpMap.put("email",MapUtils.getString(cm, "email",""));
                tmpMap.put("joined",projectMemberService.isProjectMember(projectCode,MapUtils.getString(cm, "member_code","")));
                resultData.add(tmpMap);
            }
        }
        return AjaxResult.success(resultData);
    }

    /**
     * 项目管理	我的项目 邀请新成员页面初始化
     * @param mmap
     * @return
     */
    @PostMapping("/project_member/_listForInvite")
    @ResponseBody
    public AjaxResult _listForInvite(@RequestParam Map<String,Object> mmap) {
        Map loginMember = getLoginMember();
        String projectCode = MapUtils.getString(mmap, "projectCode");
        if(StringUtils.isEmpty(projectCode)){
            throw new CustomException("请先选择项目");
        }
        List<MemberAccount> listMemberAccounts = memberAccountService.lambdaQuery()
                .eq(MemberAccount::getOrganization_code, ServletUtils.getHeaderParam("organizationCode"))
                .list();
        List<Map> resultData = new ArrayList<>();
        if(!CollectionUtils.isEmpty(listMemberAccounts)){
            Map tmpMap = null;
            for(MemberAccount cm:listMemberAccounts){
                tmpMap = new HashMap();
                tmpMap.put("memberCode",cm.getMember_code());
                tmpMap.put("status",cm.getStatus());
                tmpMap.put("avatar",cm.getAvatar());
                tmpMap.put("name",cm.getName());
                tmpMap.put("email",cm.getEmail());
                tmpMap.put("joined",projectMemberService.isProjectMember(projectCode,cm.getMember_code()));
                resultData.add(tmpMap);
            }
        }
        return AjaxResult.success(resultData);
    }


    /**
     * 项目管理	我的项目 项目设置 任务流转  规则创建页面初始化
     * @param mmap
     * @return
     */
    @PostMapping("/project_member/index")
    @ResponseBody
    public AjaxResult getProject(@RequestParam Map<String,Object> mmap)
    {
        String projectCode = MapUtils.getString(mmap,"projectCode");
        Integer page = MapUtils.getInteger(mmap,"page",1);
        Integer pageSize = MapUtils.getInteger(mmap,"pageSize",100);

        IPage<ProjectMember> projectMemberPage = projectMemberService.lambdaQuery().eq(ProjectMember::getProject_code,projectCode)
                .orderByDesc(ProjectMember::getIs_owner).page(Constant.createPage(new Page<ProjectMember>(),mmap));

        if(ObjectUtil.isNotEmpty(projectMemberPage) && !CollectionUtils.isEmpty(projectMemberPage.getRecords())){
            List<ProjectMember> projectMemberList = projectMemberPage.getRecords();
            List<Map> mapList = new ArrayList<>();
            projectMemberList.forEach(projectMember -> {
                Member member = memberService.lambdaQuery().eq(Member::getCode,projectMember.getMember_code()).one();
                if(ObjectUtil.isNotEmpty(member)){
                    mapList.add(new HashMap(){{
                        put("name",member.getName());
                        put("avatar",member.getAvatar());
                        put("code",member.getCode());
                        put("email",member.getEmail());
                        put("is_owner",projectMember.getIs_owner());
                    }});
                }
            });
            return AjaxResult.success(Constant.createPageResultMap(mapList,projectMemberPage.getTotal(),projectMemberPage.getCurrent()));
        }

        /*ProjectMember pm = new ProjectMember();
        pm.setCurrent(page);pm.setSize(pageSize);pm.setProject_code(projectCode);

        IPage<Map> idata = projectMemberService.getProjectMemberByProjectCode(pm);
        if(null != idata){
            List<Map> list = idata.getRecords();
            List<Map> lrt = new ArrayList();
            Member member = null;Map map = null;
            Map prm = null;
            for(int i=0;i<list.size();i++){
                prm = list.get(i);
                if(null != prm){
                    member = memberService.getMemberByCode(MapUtils.getString(prm,"member_code"));
                    map = new HashMap();
                    map.put("name",member.getName());map.put("avatar",member.getAvatar());
                    map.put("code",member.getCode());map.put("email",member.getEmail());
                    lrt.add(map);
                }
            }
            return AjaxResult.success(Constant.createPageResultMap(lrt,idata.getTotal(),idata.getCurrent()));
        }*/
        return AjaxResult.success(new HashMap<>());
    }

    @PostMapping("/project_member/inviteMember")
    @ResponseBody
    public AjaxResult inviteMember(@RequestParam Map<String,Object> mmap)
    {
        String memberCode = MapUtils.getString(mmap,"memberCode");
        String projectCode = MapUtils.getString(mmap,"projectCode");
        if(StringUtils.isEmpty(memberCode) || StringUtils.isEmpty(projectCode)){
            return AjaxResult.warn("数据异常！");
        }
        /*Project project = projectService.getProjectByCodeNotDel(projectCode);
        if(ObjectUtils.isEmpty(project)){
            return AjaxResult.warn("该项目已失效！");
        }
        boolean bo = projectMemberService.isProjectMember(projectCode,memberCode);
        if(bo){
            return AjaxResult.success();
        }*/
        return AjaxResult.success(projectMemberService.inviteMember(memberCode,projectCode,0));
    }
    @PostMapping("/project_member/removeMember")
    @ResponseBody
    public AjaxResult removeMember(@RequestParam Map<String,Object> mmap)
    {
        String memberCode = MapUtils.getString(mmap,"memberCode");
        String projectCode = MapUtils.getString(mmap,"projectCode");
        if(StringUtils.isEmpty(memberCode) || StringUtils.isEmpty(projectCode)){
            return AjaxResult.warn("数据异常！");
        }
        Project project = projectService.getProjectByCodeNotDel(projectCode);
        if(ObjectUtils.isEmpty(project)){
            return AjaxResult.warn("该项目已失效！");
        }
        boolean bo = projectMemberService.isProjectMember(projectCode,memberCode);
        if(!bo){
            return AjaxResult.success();
        }
        return AjaxResult.success(projectMemberService.removeMember(memberCode,project));

    }
}
