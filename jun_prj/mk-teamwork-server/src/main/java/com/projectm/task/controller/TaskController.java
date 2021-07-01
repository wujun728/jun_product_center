package com.projectm.task.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.framework.common.utils.StringUtils;
import com.framework.common.AjaxResult;
import com.framework.security.util.UserUtil;
import com.projectm.common.CommUtils;
import com.projectm.common.Constant;
import com.projectm.common.DateUtil;
import com.projectm.member.domain.Member;
import com.projectm.member.service.MemberAccountService;
import com.projectm.member.service.MemberService;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectLog;
import com.projectm.project.service.ProjectLogService;
import com.projectm.project.service.ProjectService;
import com.projectm.project.service.SourceLinkService;
import com.projectm.task.domain.*;
import com.projectm.task.service.*;
import com.projectm.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project")
public class TaskController  extends BaseController {

    @Autowired
    private TaskStageService taskStageService;

    @Autowired
    private TaskWorkflowService taskWorkflowService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProjectLogService projectLogService;

    @Autowired
    private TaskWorkTimeService taskWorkTimeService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SourceLinkService sourceLinkService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskTagService taskTagService;

    @Autowired
    private  TaskToTagService taskToTagService;

    @Autowired
    private TaskMemberService taskMemberService;
    @Autowired
    private MemberAccountService memberAccountService;

    /**
     *
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 设置任务结束时间、备注、优先级
     * @param mmap
     * @return
     */
    @PostMapping("/task/selfList")
    @ResponseBody
    public AjaxResult taskSelfList(@RequestParam Map<String,Object> mmap)  throws Exception {

        Integer taskType =  MapUtils.getInteger(mmap,"taskType",1);
        Integer type = MapUtils.getInteger(mmap,"type",0);

        String memberCode = MapUtils.getString(mmap,"memberCode", UserUtil.getLoginUser().getUser().getCode());

        Integer done = type;
        if(type == 0) done = 0;
        if(type ==-1) done = type;
        Integer finalDone = done;
        Map params = new HashMap(){{
            put("memberCode",memberCode);
            put("done", finalDone);
            put("taskType", taskType);
        }};
        IPage<Map> page = Constant.createPage(mmap);
        page = taskService.getMemberTasks(page,params);
        if(page != null){
            List<Map> list = page.getRecords();
            List<Map> resultList = new ArrayList<>();
            Map statusMap = new HashMap(){{
                put(0,"普通");
                put(1,"紧急");
                put(2,"非常紧急");
            }};
            if(CollectionUtils.isNotEmpty(list)){
                Map memberMap,projectMap = null;
                for(Map m:list){
                    memberMap = memberService.getMemberMapByCode(MapUtils.getString(m,"assign_to"));
                    projectMap = projectService.getProjectByCode(MapUtils.getString(m,"project_code"));
                    m.put("executor",CommUtils.getMapField(memberMap,new String[]{"name","avatar"}));
                    m.put("projectInfo",CommUtils.getMapField(projectMap,new String[]{"name","code"}));
                    m.put("priText",statusMap.get(MapUtils.getInteger(m,"pri",0)));
                    resultList.add(m);
                }
            }
            Map data= Constant.createPageResultMap(page);
            data.put("list",resultList);
            return AjaxResult.success(data);
        }
        return AjaxResult.success(new ArrayList<>());
    }

    /**
     *
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 设置任务结束时间、备注、优先级
     * @param mmap
     * @return
     */
    @PostMapping("/task_member/index")
    @ResponseBody
    public AjaxResult taskMember(@RequestParam Map<String,Object> mmap) {
        Map loginMember = getLoginMember();
        String taskCode = MapUtils.getString(mmap,"taskCode");
        Integer pageSize = MapUtils.getInteger(mmap,"pageSize");
        Integer page = MapUtils.getInteger(mmap,"page",1);
        IPage<Map> iPage = new Page<>();
        iPage.setCurrent(page);iPage.setSize(pageSize);
        IPage<Map> iPageResult = taskMemberService.getTaskMemberByTaskCode(iPage,taskCode);
        List<Map> resultList = new ArrayList<>();
        List<Map> records = iPageResult.getRecords();
        Map pc = null;
        if(!CollectionUtils.isEmpty(records)){
            String memberCode = null;
            Map memberMap,memberAccountMap = null;
            Map returnEntity = null;
            String orgCode = MapUtils.getString(loginMember,"organizationCode");
            for(Map map:records){
                memberCode = MapUtils.getString(map,"member_code");
                memberMap = memberService.getMemberMapByCode(memberCode);
                returnEntity = CommUtils.getMapField(memberMap,new String[]{"id","name","avatar","code"});
                memberAccountMap = memberAccountService.getMemberAccountByMemCodeAndOrgCode(memberCode,orgCode);

                returnEntity.put("membar_account_code",MapUtils.getString(memberAccountMap,"code"));
                returnEntity.put("is_executor",MapUtils.getInteger(map,"is_executor"));
                returnEntity.put("is_owner",MapUtils.getInteger(map,"is_owner"));
                resultList.add(returnEntity);

            }

        }
        Map data = new HashMap();
        data.put("list",resultList);
        data.put("total",iPageResult.getTotal());
        data.put("page",iPageResult.getCurrent());
        return AjaxResult.success(data);

    }

    /**
     *
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 设置任务结束时间、备注、优先级
     * @param mmap
     * @return
     */
    @PostMapping("/task/edit")
    @ResponseBody
    public AjaxResult taskEdit(@RequestParam Map<String,Object> mmap)   {

        String taskCode = MapUtils.getString(mmap,"taskCode");

        if(StringUtils.isEmpty(taskCode)){
            return AjaxResult.warn("请选择一个任务");
        }

        Task task = taskService.lambdaQuery().eq(Task::getCode,taskCode).one();
        if(ObjectUtil.isEmpty(task)){
            return AjaxResult.warn("该任务已失效");
        }
        taskService.edit(taskCode,mmap);
        return AjaxResult.success();


        /*
        Map loginMember = getLoginMember();



        Task task = new Task();
        Map taskMap = taskService.getTaskMapByCode(taskCode);
        ProjectLog projectLog = new ProjectLog();
        if(MapUtils.isNotEmpty(taskMap)){
            task.setId(MapUtils.getLong(taskMap,"id"));
            if(StringUtils.isNotEmpty(end_time)){
                task.setEnd_time(end_time);
                projectLog.setRemark("更新截止时间为 "+end_time);
                projectLog.setIcon("calendar");
                projectLog.setContent("");
                projectLog.setType("setEndTime");
            }
            if(StringUtils.isNotEmpty(description)){
                task.setDescription(description);
                projectLog.setRemark("更新了备注 ");
                projectLog.setIcon("file-text");
                projectLog.setContent(description);
                projectLog.setType("content");
            }
            if(-1!=pri){
                task.setPri(pri);
                String deRe = "";
                if(pri==1){
                    deRe = "紧急";
                }else if(pri ==2){
                    deRe = "非常紧急";
                }else{
                    deRe = "普通";
                }
                projectLog.setRemark("更新任务优先级为 "+deRe);
                projectLog.setIcon("user");
                projectLog.setContent(description);
                projectLog.setType("pri");
            }


            taskService.updateById(task);
        }
        projectLog.setMember_code(MapUtils.getString(loginMember,"memberCode")).setSource_code(taskCode);
        projectLog.setIs_comment(0).setTo_member_code("").setCreate_time(DateUtil.formatDateTime(new Date()));
        projectLog.setCode(CommUtils.getUUID()).setAction_type("task").setIs_robot(0).setProject_code(MapUtils.getString(taskMap,"project_code"));

        return AjaxResult.success(projectLogService.save(projectLog));
*/

    }

    /**
     *
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 添加评论
     * @param mmap
     * @return
     */
    @PostMapping("/task/createComment")
    @ResponseBody
    public AjaxResult taskCreateComment(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap, "taskCode");
        String comment = MapUtils.getString(mmap, "comment");
        String mentions = MapUtils.getString(mmap,"mentions");


        //Map taskCodeMap = taskService.getTaskMapByCode(taskCode);

        //ProjectLog projectLog = new ProjectLog();
        /*projectLog.setMember_code(MapUtils.getString(loginMember,"memberCode"));
        projectLog.setSource_code(taskCode);
        projectLog.setRemark(comment);
        projectLog.setType("comment");
        projectLog.setContent(comment);
        projectLog.setIs_comment(1);//是否评论，0：否
        projectLog.setTo_member_code("");
        projectLog.setCreate_time(DateUtil.formatDateTime(new Date()));
        projectLog.setCode(CommUtils.getUUID());
        projectLog.setAction_type("task");
        projectLog.setIs_robot(0);
        projectLog.setProject_code(MapUtils.getString(taskCodeMap,"project_code"));
        projectLog.setIcon("file-text");*/

        /*projectLog.setMember_code(MapUtils.getString(loginMember,"memberCode")).setSource_code(taskCode).setRemark(comment);
        projectLog.setType("comment").setContent(comment).setIs_comment(1).setTo_member_code("").setCreate_time(DateUtil.formatDateTime(new Date()));
        projectLog.setCode(CommUtils.getUUID()).setAction_type("task").setIs_robot(0).setProject_code(MapUtils.getString(taskCodeMap,"project_code"));
        projectLog.setIcon("file-text");
        return AjaxResult.success(projectLogService.save(projectLog));*/
        taskService.createComment(taskCode,comment,mentions);
        return AjaxResult.success();
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 编辑工时
     * @param mmap
     * @return
     */
    @PostMapping("/task/delTaskWorkTime")
    @ResponseBody
    public AjaxResult taskDelTaskWorkTime(@RequestParam Map<String,Object> mmap)  throws Exception {
        String code = MapUtils.getString(mmap, "code");
        return AjaxResult.success(taskWorkTimeService.delTaskWorkTimeByCode(code));
    }


    @PostMapping("/task/getListByTaskTag")
    @ResponseBody
    public AjaxResult getListByTaskTag(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskTagCode = MapUtils.getString(mmap, "taskTagCode");
        IPage<Map> page = Constant.createPage(mmap);
        page = taskTagService.selectListByTaskTag(page,taskTagCode);
        return AjaxResult.success(Constant.createPageResultMap(page));
    }

    @PostMapping("/task/dateTotalForProject")
    @ResponseBody
    public AjaxResult dateTotalForProject(@RequestParam Map<String,Object> mmap)  throws Exception {
        String projectCode = MapUtils.getString(mmap, "projectCode");
        String beginTime = MapUtils.getString(mmap, "beginTime");
        String endTime = MapUtils.getString(mmap, "endTime");
        Date now = new Date();
        if(StringUtils.isEmpty(beginTime)){
            beginTime=DateUtil.format("yyyy-MM-dd",DateUtil.add(now,5,-20));
        }
        if(StringUtils.isEmpty(endTime)){
            endTime = DateUtil.format("yyyy-MM-dd",now);
        }
        List<String> dateList = DateUtil.findDaysStr(beginTime,endTime);
        List<Map> mapList = new ArrayList<>();
        dateList.stream().forEach(s -> {
            String start = s + " 00:00:00";
            String end = s + "23:59:59";
            Integer total = taskService.getDateTaskTotalForProject(projectCode,start,end);
            mapList.add(new HashMap(){{
                put("date",s);
                put("total",total);
            }});
        });
        return AjaxResult.success(mapList);
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 编辑工时
     * @param mmap
     * @return
     */
    @PostMapping("/task/editTaskWorkTime")
    @ResponseBody
    public AjaxResult taskEditTaskWorkTime(@RequestParam Map<String,Object> mmap)  throws Exception {
        String beginTime = MapUtils.getString(mmap, "beginTime");
        Integer num = MapUtils.getInteger(mmap, "num");
        String content = MapUtils.getString(mmap, "content");
        String taskCode = MapUtils.getString(mmap, "taskCode");
        String code = MapUtils.getString(mmap, "code");
        Map taskWorkTimeMap = taskWorkTimeService.getTaskWorkTimeByCode(code);
        TaskWorkTime taskWorkTime = new TaskWorkTime();
        taskWorkTime.setId(MapUtils.getInteger(taskWorkTimeMap,"id"));
        taskWorkTime.setCode(code);
        taskWorkTime.setBegin_time(beginTime);
        taskWorkTime.setContent(content);
        taskWorkTime.setCreate_time(MapUtils.getString(taskWorkTimeMap,"create_time"));
        taskWorkTime.setMember_code(MapUtils.getString(taskWorkTimeMap,"memberCode"));
        taskWorkTime.setNum(num);taskWorkTime.setTask_code(taskCode);
        return AjaxResult.success(taskWorkTimeService.updateById(taskWorkTime));
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 添加工时
     * @param mmap
     * @return
     */
    @PostMapping("/task/saveTaskWorkTime")
    @ResponseBody
    public AjaxResult taskSaveTaskWorkTime(@RequestParam Map<String,Object> mmap)  throws Exception {

        String beginTime = MapUtils.getString(mmap, "beginTime");
        Integer num = MapUtils.getInteger(mmap, "num");
        String content = MapUtils.getString(mmap, "content");
        String taskCode = MapUtils.getString(mmap, "taskCode");


        TaskWorkTime taskWorkTime = new TaskWorkTime();
        taskWorkTime.setCode(CommUtils.getUUID());
        taskWorkTime.setBegin_time(beginTime);
        taskWorkTime.setContent(content);
        taskWorkTime.setCreate_time(DateUtil.formatDateTime(new Date()));
        taskWorkTime.setMember_code(MapUtils.getString(getLoginMember(),"memberCode"));
        taskWorkTime.setNum(num);taskWorkTime.setTask_code(taskCode);
        return AjaxResult.success(taskWorkTimeService.save(taskWorkTime));
    }
    @PostMapping("/task/assignTask")
    @ResponseBody
    public AjaxResult assignTask(@RequestParam Map<String,Object> mmap) {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        String executorCode = MapUtils.getString(mmap,"executorCode");
        if(StringUtils.isEmpty(taskCode)){
            return AjaxResult.warn("请选择任务");
        }
        return AjaxResult.success(taskService.assignTask(taskCode,executorCode));
    }

    /** 未完成
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 添加子任务
     * @param mmap
     * @return
     */
    @PostMapping("/task/save")
    @ResponseBody
    public AjaxResult taskSave(@RequestParam Map<String,Object> mmap)  throws Exception {

        String pcode = MapUtils.getString(mmap, "pcode");
        String name = MapUtils.getString(mmap, "name");
        String assign_to = MapUtils.getString(mmap, "assign_to");
        String stage_code = MapUtils.getString(mmap,"stage_code");
        String project_code = MapUtils.getString(mmap,"project_code");
        Map loginMember = getLoginMember();

        if(StringUtils.isEmpty(name)){
            return AjaxResult.warn("请填写任务标题");
        }
        Task  task = new Task();
        task.setStage_code(stage_code);
        task.setProject_code(project_code);
        if(StringUtils.isNotEmpty(pcode)){
            Map parentTask = taskService.getTaskMapByCode(pcode);
            if(MapUtils.isEmpty(parentTask)){
                return AjaxResult.warn("父目录无效");
            }
            if(MapUtils.getInteger(parentTask,"deleted",-1) == 1){
                return AjaxResult.warn("父任务在回收站中无法编辑");
            }
            if(MapUtils.getInteger(parentTask,"done",-1) == 1){
                return AjaxResult.warn("父任务已完成，无法添加新的子任务");
            }
            task.setProject_code(MapUtils.getString(parentTask,"project_code"));
            task.setStage_code(MapUtils.getString(parentTask,"stage_code"));
            task.setPcode(pcode);
        }
        task.setAssign_to(assign_to);
        task.setCreate_by(MapUtils.getString(loginMember,"memberCode"));
        task.setDescription("");
        task.setBegin_time("");
        task.setEnd_time("");
        task.setName(name);
        return taskService.createTask(task,pcode);
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 新建标签保存
     * @param mmap
     * @return
     */
    @PostMapping("/task/setTag")
    @ResponseBody
    public AjaxResult taskSetTag(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap, "taskCode");
        String tagCode = MapUtils.getString(mmap, "tagCode");
        TaskToTag taskToTag = new TaskToTag();
        taskToTag.setTask_code(taskCode);taskToTag.setTag_code(tagCode);
        taskToTag.setCreate_time(DateUtil.formatDateTime(new Date()));
        taskToTag.setCode(CommUtils.getUUID());
        Map taskToTagMap = taskToTagService.getTaskToTagByTagCodeAndTaskCode(tagCode,taskCode);
        boolean bo = true;
        if(MapUtils.isEmpty(taskToTagMap)){
            bo = taskToTagService.save(taskToTag);
        }else{
            bo = taskToTagService.removeById(MapUtils.getInteger(taskToTagMap,"id"));
        }
        return AjaxResult.success(bo);
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 编辑标签保存
     * @param mmap
     * @return
     */
    @PostMapping("/task_tag/edit")
    @ResponseBody
    public AjaxResult taskTagEdit(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String name = MapUtils.getString(mmap, "name");
        String color = MapUtils.getString(mmap, "color");
        String tagCode = MapUtils.getString(mmap, "tagCode");

        Map taskTagMap = taskTagService.getTaskTagByCode(tagCode);
        TaskTag taskTag = new TaskTag();
        taskTag.setCreate_time(MapUtils.getString(taskTagMap,"create_time"));
        taskTag.setName(name); taskTag.setColor(color);
        taskTag.setProject_code(MapUtils.getString(taskTagMap,"project_code"));
        taskTag.setCode(tagCode);taskTag.setId(MapUtils.getInteger(taskTagMap,"id"));
        boolean bo = taskTagService.updateById(taskTag);
        return AjaxResult.success(bo);
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 新建标签保存
     * @param mmap
     * @return
     */
    @PostMapping("/task_tag/save")
    @ResponseBody
    public AjaxResult taskTagSave(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String name = MapUtils.getString(mmap,"name");
        String projectCode = MapUtils.getString(mmap,"projectCode");
        String color = MapUtils.getString(mmap,"color");
        Map taskTagMap = taskTagService.getTaskTagByNameAndProjectCode(new HashMap(){{
            put("projectCode",projectCode);put("name",name);
        }});
        if(!MapUtils.isEmpty(taskTagMap)){
            return AjaxResult.warn("该标签已存在");
        }
        TaskTag taskTag = new TaskTag();
        taskTag.setCode(CommUtils.getUUID());taskTag.setProject_code(projectCode);
        taskTag.setColor(color);taskTag.setName(name);
        taskTag.setCreate_time(DateUtil.formatDateTime(new Date()));
        taskTagService.save(taskTag);
        return AjaxResult.success(taskTag);
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 添加加标签初始化
     * @param mmap
     * @return
     */
    @PostMapping("/task/taskToTags")
    @ResponseBody
    public AjaxResult taskToTags(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        List<Map> taskTagList = taskToTagService.getTaskToTagByTaskCode(taskCode);
        List<Map> resultData = new ArrayList<>();
        if(!CollectionUtils.isEmpty(taskTagList)){
            Map taskTag = null;
            for(Map map:taskTagList){
                taskTag = taskTagService.getTaskTagByCode(MapUtils.getString(map,"tag_code"));
                map.put("tag",taskTag);
                resultData.add(map);
            }
        }
        return AjaxResult.success(resultData);

    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情 添加加标签初始化
     * @param mmap
     * @return
     */
    @PostMapping("/task_tag/index")
    @ResponseBody
    public AjaxResult taskTag(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String projectCode = MapUtils.getString(mmap,"projectCode");
        List<Map> taskTagList = taskTagService.getTaskTagByProjectCode(projectCode);
        return AjaxResult.success(taskTagList);

    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情
     * @param mmap
     * @return
     */
    @PostMapping("/task/taskSources")
    @ResponseBody
    public AjaxResult taskSources(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String taskCode = MapUtils.getString(mmap,"taskCode");

        List<Map> sourceLinkList = sourceLinkService.getSourceLinkByLinkCodeAndType(taskCode,"task");
        List<Map> resultData = new ArrayList<>();
        if(!CollectionUtils.isEmpty(sourceLinkList)){
            Map file = null;Map project = null;
            for(Map map:sourceLinkList){
                file = fileService.getFileByCode(MapUtils.getString(map,"source_code"));
                project = projectService.getProjectByCode(MapUtils.getString(file,"project_code"));
                map.put("title",MapUtils.getString(file,"title"));
                file.put("fullName",MapUtils.getString(file,"title")+MapUtils.getString(file,"extension"));
                file.put("projectName",MapUtils.getString(project,"name"));
                map.put("sourceDetail",file);
                resultData.add(map);
            }
        }
        return AjaxResult.success(resultData);
    }


    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情
     * @param mmap
     * @return
     */
    @PostMapping("/task/_taskWorkTimeList")
    @ResponseBody
    public AjaxResult taskWorkTimeList(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        List<Map> mapList = taskWorkTimeService.getTaskWorkTimeByTaskCode(taskCode);
        List<Map> recordResult = new ArrayList<>();
        if(!CollectionUtils.isEmpty(mapList)){
            Map member = null;
            for(Map map : mapList){
                member = memberService.getMemberMapByCode(MapUtils.getString(map,"member_code"));
                map.put("member", CommUtils.getMapField(member,new String[]{"name","avatar"}));
                recordResult.add(map);
            }
        }
        return new AjaxResult(AjaxResult.Type.SUCCESS, "", recordResult);
    }

    @PostMapping("/task/read")
    @ResponseBody
    public AjaxResult taskRead(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Map loginMember = getLoginMember();
        String memberCode = MapUtils.getString(loginMember,"memberCode");
        String taskCode = MapUtils.getString(mmap,"taskCode");
        return AjaxResult.success(taskService.readTask(taskCode,memberCode));
    }
    @PostMapping("/task/index")
    @ResponseBody
    public AjaxResult taskIndex(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Map loginMember = getLoginMember();
        IPage<Map> page = Constant.createPage(mmap);
        mmap.put("memberCode",MapUtils.getString(loginMember,"memberCode"));
        page = taskService.taskIndex(page,mmap);
        return AjaxResult.success(Constant.createPageResultMap(page));
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单 打开任务详情
     * @param mmap
     * @return
     */
    @PostMapping("/task/taskLog")
    @ResponseBody
    public AjaxResult taskTaskLog(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        Integer pageSize = MapUtils.getInteger(mmap,"pageSize",1000);
        Integer page = MapUtils.getInteger(mmap,"page",1);
        String taskCode = MapUtils.getString(mmap,"taskCode");
        Integer showAll = MapUtils.getInteger(mmap,"all",0);
        Integer onlyComment = MapUtils.getInteger(mmap,"comment",0);
        Integer is_comment = onlyComment>0?onlyComment:0;
        IPage<ProjectLog> pagel=Constant.createPage(new Page(),mmap);
        List<ProjectLog> records = new ArrayList<>();
        if(0==showAll){
            LambdaQueryChainWrapper<ProjectLog> lqcw=projectLogService.lambdaQuery().eq(ProjectLog::getSource_code,taskCode).eq(ProjectLog::getAction_type,"task");
            if(onlyComment>0){
                lqcw.eq(ProjectLog::getIs_comment,1);
            }
            pagel=lqcw.orderByDesc(ProjectLog::getId).page(pagel);
            records = pagel.getRecords();
        }else{
            LambdaQueryChainWrapper<ProjectLog> lqcw=projectLogService.lambdaQuery().eq(ProjectLog::getSource_code,taskCode).eq(ProjectLog::getAction_type,"task");
            if(onlyComment>0){
                lqcw.eq(ProjectLog::getIs_comment,1);
            }
            records=lqcw.orderByDesc(ProjectLog::getId).list();
        }

        List<ProjectLog> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(records)){
            Member member = null;
            for(ProjectLog pl : records){
               if(pl.getIs_robot()>0 && "claim".equals(pl.getType())){
                   member = new Member();
                   member.setName("PP Robot");
                   pl.setMember(member);
                   resultList.add(pl);
                   continue;
               }
               member = memberService.lambdaQuery().eq(Member::getCode,pl.getMember_code()).one();
               pl.setMember(member);
                resultList.add(pl);
            }
        }
        Map data = new HashMap();
        data.put("list",resultList);
        data.put("total",pagel.getTotal());
        data.put("page",pagel.getCurrent());
        return AjaxResult.success(data);
    }
    @PostMapping("/task_stages/save")
    @ResponseBody
    public AjaxResult taskStagesSave(@RequestParam Map<String,Object> mmap)
    {
        String name = MapUtils.getString(mmap,"name");
        String projectCode = MapUtils.getString(mmap,"projectCode");
        if(StringUtils.isEmpty(name)){
            return AjaxResult.warn("请填写列表名称！");
        }
        Project project = projectService.lambdaQuery().eq(Project::getCode,projectCode).eq(Project::getDeleted,0).one();
        if(ObjectUtils.isEmpty(project)){
            return AjaxResult.warn("该项目已失效！");
        }
        TaskStage taskStage=TaskStage.builder().create_time(DateUtil.getCurrentDateTime()).code(CommUtils.getUUID())
                .project_code(projectCode).name(StringUtils.trim(name)).tasksLoading(false)
                .fixedCreator(false).showTaskCard(false).tasks(new ArrayList()).build();
        taskStageService.save(taskStage);
        taskStage.setSort(taskStage.getId());
        taskStageService.updateById(taskStage);
        return AjaxResult.success(taskStage);
    }
    @PostMapping("/task_stages/edit")
    @ResponseBody
    public AjaxResult taskStagesEidt(@RequestParam Map<String,Object> mmap)
    {
        String name = MapUtils.getString(mmap,"name");
        String stageCode = MapUtils.getString(mmap,"stageCode");
        if(StringUtils.isEmpty(name)){
            return AjaxResult.warn("请填写列表名称");
        }
        if(StringUtils.isEmpty(stageCode)){
            return AjaxResult.warn("请选择一个列表");
        }
        TaskStage taskStage = taskStageService.lambdaQuery().eq(TaskStage::getCode,stageCode).one();
        if(ObjectUtils.isEmpty(taskStage)){
            return AjaxResult.warn("该列表已失效！");
        }
        taskStageService.lambdaUpdate().eq(TaskStage::getCode,stageCode).set(TaskStage::getName,name).update();
        return AjaxResult.success();
    }

    @PostMapping("/task/batchAssignTask")
    @ResponseBody
    public AjaxResult batchAssignTask(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String taskCodes = MapUtils.getString(mmap,"taskCodes","[]");
        String stageCode = MapUtils.getString(mmap,"executorCode");
        taskService.batchAssignTask(taskCodes,stageCode);
        return AjaxResult.success();
    }

    /**
     * 项目管理	我的项目 项目打开 任务清单
     * @param mmap
     * @return
     */
    @PostMapping("/task_stages/tasks")
    @ResponseBody
    public AjaxResult taskStagesTasks(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        return AjaxResult.success(taskService.tasks(mmap));
        /*String stageCode = MapUtils.getString(mmap,"stageCode");
        Map loginMember = getLoginMember();
        Map params = new HashMap();
        params.put("stageCode",stageCode);
        params.put("deleted",0);params.put("pcode","");
        List<Map> listTask = taskService.getTaskByParams(params);
        List<Map> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(listTask)){
            Map memberMap = null;
            Map executor = null;
            List<Map> tags = null;
            for(Map map : listTask){
                memberMap = memberService.getMemberMapByCode(MapUtils.getString(map,"create_by"));
                executor = new HashMap();
                executor.put("name",MapUtils.getString(memberMap,"name"));
                executor.put("avatar",MapUtils.getString(memberMap,"avatar"));
                map.put("executor",executor);
                map = taskService.buildTaskMap(map,MapUtils.getString(loginMember,"memberCode"));
                resultList.add(map);
            }
        }
        return new AjaxResult(AjaxResult.Type.SUCCESS, "", resultList);*/
    }

    /**
     * 项目管理	我的项目 项目设置 任务流转 创建规则打开
     * @param mmap
     * @return
     */
    @PostMapping("/task_stages/index")
    @ResponseBody
    public AjaxResult taskStages(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String projectCode = MapUtils.getString(mmap,"projectCode");
        if(null == projectCode){
            return AjaxResult.warn("请选择一个项目");
        }
        IPage<TaskStage> ipage = Constant.createPage(mmap);
        Map params = new HashMap();
        params.put("projectCode",projectCode);
        ipage = taskStageService.selectTaskStageByProjectCode(ipage,params);

        if(null == ipage){
            ipage = new Page<>();
        }
        Map data = Constant.createPageResultMap(ipage);
        return new AjaxResult(AjaxResult.Type.SUCCESS, "", data);

    }

    /**
     * 项目管理	我的项目 项目设置 任务流转 删除任务
     * @param mmap
     * @return
     */
//    @PostMapping("/task_workflow/delete")
//    @ResponseBody
//    public AjaxResult taskWorkflowDelete(@RequestParam Map<String,Object> mmap)  throws Exception
//    {
//        String taskWorkflowCode = MapUtils.getString(mmap,"taskWorkflowCode");
//
//        int i = taskWorkflowService.deleteTaskWorkflowByCode(taskWorkflowCode);
//        return AjaxResult.success(i);
//
//    }

    /**
     * 项目管理	我的项目 项目设置 任务流转 编辑任务
     * @param mmap
     * @return
     */
    @PostMapping("/task_workflow/_getTaskWorkflowRules")
    @ResponseBody
    public AjaxResult _getTaskWorkflowRules(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String taskWorkflowCode = MapUtils.getString(mmap,"taskWorkflowCode");
        List<Map> listResult = taskWorkflowService.selectTaskWorkflowRuleByWorkflowCode(taskWorkflowCode);
        return AjaxResult.success(listResult);

    }


    /**
     * 项目管理	我的项目 项目设置 任务流转 创建规则打开
     * @param mmap
     * @return
     */
    @PostMapping("/task_stages/_getAll")
    @ResponseBody
    public AjaxResult taskStagesGetAll(@RequestParam Map<String,Object> mmap)
    {
        String projectCode = MapUtils.getString(mmap,"projectCode");
        List<Map> listResult = taskStageService.selectTaskStageByProjectCode(projectCode);
        return AjaxResult.success(listResult);

    }
    @PostMapping("/task_stages/delete")
    @ResponseBody
    public AjaxResult taskStagesDelete(@RequestParam Map<String,Object> mmap)
    {
        String code = MapUtils.getString(mmap,"code");
        taskStageService.deleteStage(code);
        return AjaxResult.success();
    }
    /**
     * 项目管理	我的项目 项目设置 任务流转 创建规则保存
     * @param mmap
     * @return
     */
    @PostMapping("/task_workflow/index")
    @ResponseBody
    public AjaxResult taskWorkflowByProjectCode(@RequestParam Map<String,Object> mmap)  throws Exception
    {
        String projectCode = MapUtils.getString(mmap,"projectCode");
        List<Map> listResult = taskWorkflowService.selectTaskWorkflowByProjectCode(projectCode);
        return AjaxResult.success(listResult);
    }

    @PostMapping("/task/like")
    @ResponseBody
    public AjaxResult like(@RequestParam Map<String,Object> mmap)  throws Exception {
        Integer data = MapUtils.getInteger(mmap,"like");
        String code = MapUtils.getString(mmap,"taskCode");
        if(StringUtils.isEmpty(code)){
            return AjaxResult.warn("请选择一个任务！");
        }
        Map taskMap = taskService.getTaskMapByCode(code);
        if(MapUtils.isEmpty(taskMap)){
            return AjaxResult.warn("该任务已失效！");
        }
        Map taskMapNoDel = taskService.getTaskByCodeNoDel(code);
        if(MapUtils.isEmpty(taskMapNoDel)){
            return AjaxResult.warn("该任务在回收站中不能点赞！");
        }
        taskService.like(taskMap,MapUtils.getString(getLoginMember(),"memberCode"),data);
        return AjaxResult.success();
    }
    @PostMapping("/task/star")
    @ResponseBody
    public AjaxResult star(@RequestParam Map<String,Object> mmap)  throws Exception {
        Integer data = MapUtils.getInteger(mmap,"star");
        String code = MapUtils.getString(mmap,"taskCode");
        if(StringUtils.isEmpty(code)){
            return AjaxResult.warn("请选择一个任务！");
        }
        Map taskMap = taskService.getTaskMapByCode(code);
        if(MapUtils.isEmpty(taskMap)){
            return AjaxResult.warn("该任务已失效！");
        }
        Map taskMapNoDel = taskService.getTaskByCodeNoDel(code);
        if(MapUtils.isEmpty(taskMapNoDel)){
            return AjaxResult.warn("该任务在回收站中不能收藏！");
        }
        taskService.star(taskMap,MapUtils.getString(getLoginMember(),"memberCode"),data);
        return AjaxResult.success();
    }
    @PostMapping("/task/setPrivate")
    @ResponseBody
    public AjaxResult setPrivate(@RequestParam Map<String,Object> mmap)  throws Exception {
        Integer private_=MapUtils.getInteger(mmap,"private");
        String taskCode = MapUtils.getString(mmap,"taskCode");
        if( 0==private_ || 1==private_){
            Task task = taskService.getTaskByCode(taskCode);
            taskService.edit(Task.builder().id(task.getId()).code(taskCode).project_code(task.getProject_code())
                    .privated(private_).build(),MapUtils.getString(getLoginMember(),"memberCode"));
        }
        return  AjaxResult.success();
    }
    @PostMapping("/task/recycle")
    @ResponseBody
    public AjaxResult recycle(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        taskService.recycle(taskCode,MapUtils.getString(getLoginMember(),"memberCode"));
        return  AjaxResult.success();
    }
    @PostMapping("/task/recycleBatch")
    @ResponseBody
    public AjaxResult recycleBatch(@RequestParam Map<String,Object> mmap)  throws Exception {
        String stageCode = MapUtils.getString(mmap,"stageCode");
        taskService.recycleBatch(stageCode);
        return  AjaxResult.success();
    }
	@PostMapping("/task/delete")
    @ResponseBody
    public AjaxResult delete(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        taskService.delete(taskCode);
        return  AjaxResult.success();
    }
    @PostMapping("/task/recovery")
    @ResponseBody
    public AjaxResult recovery(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        taskService.recovery(taskCode);
        return  AjaxResult.success();
    }

    @PostMapping("/task/taskDone")
    @ResponseBody
    public AjaxResult taskDone(@RequestParam Map<String,Object> mmap)  throws Exception {
        String taskCode = MapUtils.getString(mmap,"taskCode");
        if(StringUtils.isEmpty(taskCode)){
            return AjaxResult.warn("请选择任务");
        }
        Integer done =  MapUtils.getInteger(mmap,"done");
        try{
            taskService.taskDone(taskCode,done,MapUtils.getString(getLoginMember(),"memberCode"));
        }catch (Exception e){e.printStackTrace();
            return AjaxResult.warn(e.getMessage());
        }
        return AjaxResult.success();
    }
    @PostMapping("/task/sort")
    @ResponseBody
    public AjaxResult taskSort(@RequestParam Map<String,Object> mmap) {
        String stageCode = MapUtils.getString(mmap,"stageCode");
        String codes = MapUtils.getString(mmap,"codes");
        if(StringUtils.isEmpty(codes)){
            return AjaxResult.warn("参数有误！");
        }
        taskService.sort(stageCode, Arrays.asList(codes.split(",")));
        return AjaxResult.success();
    }

}
