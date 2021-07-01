package com.projectm.task.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.framework.security.util.UserUtil;
import com.projectm.common.DateUtil;
import com.projectm.member.domain.ProjectMember;
import com.projectm.member.service.ProjectMemberService;
import com.projectm.project.domain.Project;
import com.projectm.project.service.ProjectService;
import com.projectm.task.domain.Task;
import com.projectm.task.domain.TaskMember;
import com.projectm.task.mapper.TaskMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskMemberService  extends ServiceImpl<TaskMemberMapper, TaskMember> {

    @Autowired
    TaskService taskService;
    @Autowired
    ProjectMemberService projectMemberService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskWorkflowService taskWorkflowService;
    
    public IPage<Map> getTaskMemberByTaskCode(IPage iPage,String taskCode){
        return baseMapper.selectTaskMemberByTaskCode(iPage,taskCode);
    }

    @Transactional
    public TaskMember inviteMember(String memberCode,String taskCode,Integer isExecutor,Integer isOwner,boolean fromCreate ,boolean isRobot){
        memberCode = StringUtils.isEmpty(memberCode)?"":memberCode;
        Task task = taskService.lambdaQuery().eq(Task::getCode,taskCode).eq(Task::getDeleted,0).one();
        if(ObjectUtils.isEmpty(task)){
            throw new CustomException("任务已失效！");
        }
        TaskMember taskExecutor = lambdaQuery().eq(TaskMember::getIs_executor,1).eq(TaskMember::getTask_code,taskCode).one();
        if(null != taskExecutor && taskExecutor.getMember_code().equals(memberCode)){
            return new TaskMember();
        }
        if(isExecutor>0){
            lambdaUpdate().set(TaskMember::getIs_executor,0).eq(TaskMember::getTask_code,taskCode).update();
        }
        if(StringUtils.isNotEmpty(memberCode)){
            TaskMember hasJoined = lambdaQuery().eq(TaskMember::getMember_code,memberCode).eq(TaskMember::getTask_code,taskCode).one();
            if(!ObjectUtils.isEmpty(hasJoined)){
                taskService.lambdaUpdate().set(Task::getAssign_to,memberCode).eq(Task::getCode,taskCode).update();
                taskWorkflowService.queryRule(task.getProject_code(), task.getStage_code(), task.getCode(), memberCode, 3);
                
                lambdaUpdate().set(TaskMember::getIs_executor,1).eq(TaskMember::getTask_code,taskCode).eq(TaskMember::getMember_code,memberCode).update();
                String logType ="assign";
                if(UserUtil.getLoginUser().getUser().getCode().equals(memberCode)){
                    logType="claim";
                }
                taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,logType,memberCode,0,
                        "","","",new HashMap(){{
                            put("is_robot",isRobot);
                        }},null);
                return new TaskMember();
            }
        }
        if(StringUtils.isEmpty(memberCode)){
            taskService.lambdaUpdate().set(Task::getAssign_to,memberCode).eq(Task::getCode,taskCode).update();
            if(!fromCreate){
                if(ObjectUtil.isNotEmpty(taskExecutor)){
                    taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"removeExecutor",taskExecutor.getMember_code(),0,
                            "","","",new HashMap(){{
                                put("is_robot",isRobot);
                            }},null);
                }
            }
            return new TaskMember();
        }
        TaskMember taskMember = TaskMember.builder().member_code(memberCode).task_code(taskCode).
                is_executor(isExecutor).is_owner(isOwner).join_time(DateUtil.getCurrentDateTime()).build();
        save(taskMember);
        if(isExecutor>0){
            taskService.lambdaUpdate().eq(Task::getCode,taskCode).set(Task::getAssign_to,memberCode).update();
            if(UserUtil.getLoginUser().getUser().getCode().equals(memberCode)){
                taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"claim","",0,
                        "","","",new HashMap(){{
                            put("is_robot",isRobot);
                        }},null);
            }else{
                taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"claim",memberCode,0,
                        "","","",new HashMap(){{
                            put("is_robot",isRobot);
                        }},null);
            }
        }
        if(StringUtils.isNotEmpty(memberCode)){
            Project project = projectService.lambdaQuery().eq(Project::getCode,task.getProject_code()).one();
            projectMemberService.inviteMember(memberCode,project==null?"":project.getCode(),0);
        }
        return taskMember;
    }


    @Transactional
    public  void inviteMemberBatch(String memberCodes,String taskCode){
        Task task = taskService.lambdaQuery().eq(Task::getCode,taskCode).one();
        if(ObjectUtils.isEmpty(task)){
            throw new CustomException("该任务已失效！");
        }
        boolean isAll = false;
        JSONArray memberCodeArray = JSON.parseArray(memberCodes);
        //List<>
        List<String> memberCodesList = new ArrayList<>();
        if(memberCodes.indexOf("all") != -1){
            isAll = true;
            List<ProjectMember> list= projectMemberService.lambdaQuery().eq(ProjectMember::getProject_code,task.getProject_code()).list();
            if(CollectionUtil.isNotEmpty(list)){
                list.forEach(projectMember -> {
                    memberCodesList.add(projectMember.getMember_code());
                });
            }
        }else{
            if(StringUtils.isNotEmpty(memberCodeArray)) {
                for (Object obj : memberCodeArray) {
                    if(ObjectUtil.isNotEmpty(obj)){
                        memberCodesList.add(String.valueOf(obj));
                    }
                }
            }
        }
        TaskMember taskMember = lambdaQuery().eq(TaskMember::getIs_owner,1)
                .eq(TaskMember::getTask_code,taskCode).one();
        boolean finalIsAll = isAll;
        memberCodesList.forEach(memberCode ->{
            if(!memberCode.equals(taskMember.getMember_code())){
                TaskMember hasJoined = lambdaQuery().eq(TaskMember::getMember_code,memberCode)
                        .eq(TaskMember::getTask_code,taskCode).one();
                if(ObjectUtil.isNotEmpty(hasJoined)){
                    if(!finalIsAll){
                        if(hasJoined.getIs_executor()>0){
                            taskService.lambdaUpdate().eq(Task::getCode,taskCode).set(Task::getAssign_to,"").update();
                            taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"removeExecutor",memberCode,0,
                                    "","","",null,null);
                        }
                        lambdaUpdate().eq(TaskMember::getTask_code,taskCode).eq(TaskMember::getMember_code,memberCode).remove();
                        taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"removeMember",memberCode,0,
                                "","","",null,null);
                    }
                }else{
                    TaskMember saveTaskMember = TaskMember.builder().member_code(memberCode)
                            .task_code(taskCode).is_executor(0).join_time(DateUtil.getCurrentDateTime()).build();
                    save(saveTaskMember);
                    taskService.taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"inviteMember",memberCode,0,
                            "","","",null,null);
                }
            }
        });
    }
}
