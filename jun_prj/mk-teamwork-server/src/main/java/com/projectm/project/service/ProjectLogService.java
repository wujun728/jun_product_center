package com.projectm.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.utils.StringUtils;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.common.DateUtils;
import com.projectm.member.domain.Member;
import com.projectm.member.service.MemberService;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectLog;
import com.projectm.project.mapper.ProjectLogMapper;
import com.projectm.system.domain.Notify;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.CommandAPDU;
import java.util.Map;

@Service
public class ProjectLogService   extends ServiceImpl<ProjectLogMapper, ProjectLog> {

    @Autowired
    ProjectService projectService;
    @Autowired
    MemberService memberService;
    public IPage<Map> getProjectLogByParam(IPage<Map> ipage,Map params){
        return baseMapper.selectProjectLogByParam(ipage,params);
    }

    public Project run(Map param){
        ProjectLog projectLog = ProjectLog.builder().action_type(MapUtils.getString(param,"action_type")).code(CommUtils.getUUID())
                .create_time(DateUtil.getCurrentDateTime()).to_member_code(MapUtils.getString(param,"to_member_code"))
                .is_comment(MapUtils.getInteger(param,"is_comment")).content(MapUtils.getString(param,"content",""))
                .type(MapUtils.getString(param,"type")).source_code(MapUtils.getString(param,"source_code"))
                .member_code(MapUtils.getString(param,"member_code")).project_code(MapUtils.getString(param,"project_code")).build();
        Project project = projectService.getProjectProjectByCode(projectLog.getProject_code());
        projectLog.setProject_code(project.getCode());
        Member toMember = new Member();
        if(StringUtils.isNotEmpty(projectLog.getTo_member_code())){
            toMember = memberService.getMemberByCode(projectLog.getTo_member_code());
        }
        Notify notify = new Notify();
        String type = projectLog.getType();
        if("create".equals(type)){
            projectLog.setIcon("plus");
            projectLog.setRemark("创建了项目");
            projectLog.setContent(project.getName());
        }else if("edit".equals(type)){
            projectLog.setIcon("edit");
            projectLog.setRemark("编辑了项目");
            projectLog.setContent(project.getName());
        }else if("name".equals(type)){
            projectLog.setIcon("edit");
            projectLog.setRemark("修改了项目名称");
            projectLog.setContent(project.getName());
        }else if("content".equals(type)){
            projectLog.setIcon("file-text");
            projectLog.setRemark("更新了备注");
            projectLog.setContent(project.getDescription());
        }else if("clearContent".equals(type)){
            projectLog.setIcon("file-text");
            projectLog.setRemark("清空了备注");
        }else if("inviteMember".equals(type)){
            projectLog.setIcon("user-add");
            projectLog.setRemark("邀请"+toMember.getName()+"加入项目");
            projectLog.setContent(toMember.getName());
        }else if("removeMember".equals(type)){
            projectLog.setIcon("user-delete");
            projectLog.setRemark("移除了成员"+toMember.getName());
            projectLog.setContent(toMember.getName());
        }else if("recycle".equals(type)){
            projectLog.setIcon("delete");
            projectLog.setRemark("把项目移到了回收站");
        }else if("recovery".equals(type)){
            projectLog.setIcon("undo");
            projectLog.setRemark("恢复了项目");
        }else if("archive".equals(type)){
            projectLog.setIcon("delete");
            projectLog.setRemark("归档了项目");
        }else if("recoveryArchive".equals(type)){
            projectLog.setIcon("undo");
            projectLog.setRemark("恢复了项目");
        }else if("uploadFile".equals(type)){
            projectLog.setIcon("link");
            projectLog.setRemark("上传了文件文件");
            projectLog.setContent("<a target=\"_blank\" class=\"muted\" href=\""+MapUtils.getString(param,"url")+" \">\""+MapUtils.getString(param,"title")+"</a>");
        }else if("deleteFile".equals(type)){
            projectLog.setIcon("disconnect");
            projectLog.setRemark("删除了文件");
            projectLog.setContent("<a target=\"_blank\" class=\"muted\" href=\""+MapUtils.getString(param,"url")+" \">\""+MapUtils.getString(param,"title")+"</a>");
        }else{
            projectLog.setIcon("plus");
            projectLog.setRemark("创建了文件");
        }
        baseMapper.insert(projectLog);
        return project;
    }


}
