package com.projectm.task.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.AjaxResult;
import com.framework.common.constant.Constants;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.framework.security.util.RedisCache;
import com.framework.security.util.UserUtil;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.config.WebSocketServer;
import com.projectm.mapper.CommMapper;
import com.projectm.member.domain.Member;
import com.projectm.member.service.MemberService;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectLog;
import com.projectm.project.service.CollectionService;
import com.projectm.project.service.ProjectLogService;
import com.projectm.project.service.ProjectService;
import com.projectm.project.service.ProjectVersionService;
import com.projectm.system.domain.Notify;
import com.projectm.system.service.NotifyService;
import com.projectm.task.domain.Task;
import com.projectm.task.domain.TaskLike;
import com.projectm.task.domain.TaskMember;
import com.projectm.task.domain.TaskStage;
import com.projectm.task.domain.TaskTag;
import com.projectm.task.domain.TaskToTag;
import com.projectm.task.mapper.TaskLikeMapper;
import com.projectm.task.mapper.TaskMapper;
import com.projectm.task.mapper.TaskTagMapper;
import com.projectm.task.mapper.TaskToTagMapper;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService   extends ServiceImpl<TaskMapper, Task> {

	@Resource
	WebSocketServer webSocketServer;

    @Autowired
    CommMapper commMapper;
    @Autowired
    TaskLikeMapper taskLikeMapper;

    @Autowired
    ProjectLogService projectLogService;

    @Autowired
    NotifyService notifyService;
    @Autowired
    TaskWorkflowService taskWorkflowService;
    
    public List<Map> tasks(Map param){
        String stageCode = MapUtils.getString(param,"stageCode","");
        Integer done = MapUtils.getInteger(param,"done",-1);
        String title = MapUtils.getString(param,"title","");
        String pri = MapUtils.getString(param,"pri","");
        String stage = MapUtils.getString(param,"stage","");
        String status = MapUtils.getString(param,"status","");
        String executor = MapUtils.getString(param,"executor","");
        String creator = MapUtils.getString(param,"creator","");
        String joiner = MapUtils.getString(param,"joiner","");
        String endTime = MapUtils.getString(param,"endTime","");
        String beginTime = MapUtils.getString(param,"beginTime","");
        String createTime = MapUtils.getString(param,"createTime","");
        String doneTime = MapUtils.getString(param,"doneTime","");
        if(StringUtils.isEmpty(stageCode)){
            throw new CustomException("数据解析异常");
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" select t.* from team_task t ");
        StringBuffer where = new StringBuffer();
        //where.append(" t.stage_code = '"+stageCode+"' ");
        where.append(" where t.pcode = '' ");
        where.append(" and t.deleted = 0 ");
        if(-1 != done){
            where.append(" and  done = "+done);
        }
        if(stageCode.startsWith("[")){
            where.append(whereBuild(stageCode,"t.stage_code"," and "));
        }else{
            where.append(" and t.stage_code='"+stageCode+"' ");
        }
        if(StringUtils.isNotEmpty(pri) && pri.startsWith("[")){
            where.append(whereBuild(pri,"t.pri"," and "));
        }else if(StringUtils.isNotEmpty(pri)){
            where.append(" and t.pri="+pri);
        }
        if(StringUtils.isNotEmpty(status) && status.startsWith("[")){
            where.append(whereBuild(status,"t.status"," and "));
        }else if(StringUtils.isNotEmpty(status)){
            where.append(" and t.status="+status);
        }

        if(StringUtils.isNotEmpty(title)){
            where.append(" and  t.name like '%"+title+"%'");
        }
        if(StringUtils.isNotEmpty(endTime) && !"[]".equals(endTime)){
            String time = endTime.replace("\"","").replace("[","").replace("]","");
            where.append(" and  t.end_time between '"+time.split(",")[0]+"' and '"+time.split(",")[1]+"' ");
        }
        if(StringUtils.isNotEmpty(beginTime)  && !"[]".equals(beginTime) ){
            String time = beginTime.replace("\"","").replace("[","").replace("]","");
            where.append(" and  t.begin_time between '"+time.split(",")[0]+"' and '"+time.split(",")[1]+"' ");
        }
        if(StringUtils.isNotEmpty(createTime)  && !"[]".equals(createTime)){
            String time = createTime.replace("\"","").replace("[","").replace("]","");
            where.append(" and t.create_time between '"+time.split(",")[0]+"' and '"+time.split(",")[1]+"' ");
        }
        if(StringUtils.isNotEmpty(doneTime) && !"[]".equals(doneTime)){
            sql.append(" left join team_project_log pl on t.code = pl.source_code ");
            where.append(" and pl.action_type='task' and pl.type='done' ");
            String time = createTime.replace("\"","").replace("[","").replace("]","");
            where.append(" and pl.create_time between '"+time.split(",")[0]+"' and '"+time.split(",")[1]+"' ");
        }
        boolean joinTaskMember=false;
        if(StringUtils.isNotEmpty(executor) && !"[]".equals(executor)){
            joinTaskMember=true;
            sql.append(" left join team_task_member tm on t.code=tm.task_code");
            where.append(whereBuild(executor," tm.member_code "," and "));
            where .append(" and tm.is_executor=1 ");
        }
        boolean creatorTaskMember = false;
        if(StringUtils.isNotEmpty(creator) && !"[]".equals(creator)){
            if(!joinTaskMember){
                creatorTaskMember = true;
                sql.append(" left join team_task_member tm on t.code=tm.task_code ");
                where.append(whereBuild(creator," tm.member_code "," and "));
                where.append(" and tm.is_executor = 1 ");
            }
        }
        if(StringUtils.isNotEmpty(joiner) && !"[]".equals(joiner)){
            if(!joinTaskMember){
                if(!creatorTaskMember){
                    sql.append(" left join team_task_member tm on t.code=tm.task_code ");
                }
                where.append(whereBuild(joiner," tm.member_code "," and "));
            }
        }

        List<Map> resultList = new ArrayList<>();
        String sqlResult = sql.toString()+where.toString();
        List<Map> mapList = commMapper.customQueryItem(sqlResult);
        if(!CollectionUtils.isEmpty(mapList)){
            Map executorMap = null;
            List<Map> tags = null;
            Map taskMemberMap = new HashMap();
            for(Map map : mapList){
                String assign_to = MapUtils.getString(map,"assign_to");
                if(ObjectUtil.isNotEmpty(assign_to)){
                    if(ObjectUtil.isNotEmpty(MapUtils.getObject(taskMemberMap,assign_to))){
                        map.put("executor",MapUtils.getObject(taskMemberMap,assign_to));
                    }else {
                        Member member = memberService.lambdaQuery().eq(Member::getCode,MapUtils.getString(map,"assign_to")).one();
                        executorMap = new HashMap();
                        executorMap.put("name",member.getName());
                        executorMap.put("avatar",member.getAvatar());
                        map.put("executor",executor);
                        taskMemberMap.put(assign_to,executorMap);
                    }
                }
                map = buildTaskMap(map,UserUtil.getLoginUser().getUser().getCode());
                resultList.add(map);
            }
        }
        return resultList;
    }
    private String whereBuild(String json,String field,String gl){
        StringBuffer where = new StringBuffer();
        JSONArray jsonArray = JSON.parseArray(json);
        if(StringUtils.isNotEmpty(jsonArray)){
            where.append(field+" in(");
            for(int i=0;i<jsonArray.size();i++){
                where.append("'"+String.valueOf(jsonArray.get(i))+"'");
                if(i<jsonArray.size()-1){
                    where.append(",");
                }
            }
            where.append(")");
        }
        if(StringUtils.isNotEmpty(where.toString())){
            return " and "+where.toString();
        }
        return "";
    }

    @Transactional
    public void edit(String taskCode,Map mmap){
        String end_time = MapUtils.getString(mmap,"end_time",null);
        String description = MapUtils.getString(mmap,"description",null);
        Integer pri = MapUtils.getInteger(mmap,"pri",-1);
        String name = MapUtils.getString(mmap,"name",null);
        Integer sort = MapUtils.getInteger(mmap,"sort",-1);
        String begin_time = MapUtils.getString(mmap,"begin_time",null);
        String work_time = MapUtils.getString(mmap,"work_time",null);
        Integer status = MapUtils.getInteger(mmap,"status",-1);
        if(StringUtils.isEmpty(taskCode)){
            throw new CustomException("请选择一个任务");
        }
        Task task = lambdaQuery().eq(Task::getCode,taskCode).eq(Task::getDeleted,0).one();
        if(ObjectUtils.isEmpty(task)){
            throw new CustomException("该任务在回收站中无法编辑");
        }
        if("<p><br></p>".equals(description)){
            description="";
        }
        LambdaUpdateChainWrapper<Task> luw = lambdaUpdate().eq(Task::getCode,taskCode);
        boolean updateMark = false;
        String type = "";
        if(null != name){
            luw = luw.set(Task::getName,name);updateMark = true;
            type="name";
        }
        if(null != description){
            luw = luw.set(Task::getDescription,description);updateMark = true;
            type="content";
            if("".equals(description)){
                type="clearContent";
            }
        }
        if(-1 != pri){
            luw = luw.set(Task::getPri,pri);updateMark = true;
            type="pri";
        }
        if(-1 != status){
            luw = luw.set(Task::getStatus,status);updateMark = true;
            type="status";
        }
        if(null != begin_time){
            luw = luw.set(Task::getBegin_time,begin_time);updateMark = true;
            type="setBeginTime";
            if("".equals(begin_time)){
                type="clearBeginTime";
            }
        }
        if(null != end_time){
            luw = luw.set(Task::getEnd_time,end_time);updateMark = true;
            type="setEndTime";
            if("".equals(end_time)){
                type="clearEndTime";
            }
        }
        if(null != work_time){
            luw = luw.set(Task::getWork_time,work_time);updateMark = true;
            type="setWorkTime";
        }

        if(-1 != sort){
            luw = luw.set(Task::getSort,sort);updateMark = true;
        }
        if(updateMark)luw.update();
        taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,type,"",0,
                "","","",null,null);


    }

    public Map getTaskMapByCode(String code){
        return  baseMapper.selectTaskByCode(code);
    }
    public Map getTaskByCodeNoDel(String code){
        return  baseMapper.selectTaskByCodeNoDel(code);
    }
    public Task getTaskByCode(String code){
        LambdaQueryWrapper<Task> taskQW = new LambdaQueryWrapper<>();
        taskQW.eq(Task::getCode, code);
        return baseMapper.selectOne(taskQW);
    }
    public List<Map> getTaskByProjectCodeAndDel(String projectCode,Integer deleted){
        LambdaQueryWrapper<Task> taskQW = new LambdaQueryWrapper<>();
        taskQW.eq(Task::getProject_code, projectCode);
        taskQW.eq(Task::getDeleted,deleted);
        return baseMapper.selectTaskByProjectCodeAndDel(projectCode,deleted);
    }
    /*
    public List<Map> getTaskByParams(Map params){
        return baseMapper.selectTaskByParams(params);
    }
    public List<Map> selectTaskToTagByTaskCode(String taskCode){
        return baseMapper.selectTaskToTagByTaskCode(taskCode);
    }
    public Map selectTaskTagByCode(String code){
        return baseMapper.selectTaskTagByCode(code);
    }
    public Map selectHasUnDone(String pcode){
        return baseMapper.selectHasUnDone(pcode);
    }
    public Map selectHasComment(String pcode){
        return baseMapper.selectHasComment(pcode);
    }
    public Map selectHasSource(String pcode){
        return baseMapper.selectHasSource(pcode);
    }

    public Map selectCanRead(String taskCode,String memberCode){
        return baseMapper.selectCanRead(taskCode,memberCode);
    }
    public Map selectParentDone(String pcode){
        return baseMapper.selectParentDone(pcode);
    }*/

    public Map selectChildCount0(String pcode){
        return baseMapper.selectChildCount0(pcode);
    }
    public Map selectChildCount1(String pcode){
        return baseMapper.selectChildCount1(pcode);
    }

    public void recycleBatch(String stageCode){
        TaskStage taskStage = taskStageService.lambdaQuery().eq(TaskStage::getCode,stageCode).one();
        if(ObjectUtils.isEmpty(taskStage)){
            throw new CustomException("任务列表不存在！");
        }
        List<Task> tasks = lambdaQuery().eq(Task::getStage_code,stageCode).eq(Task::getDeleted,0).list();
        if(CollectionUtils.isNotEmpty(tasks)){
            for(Task task:tasks){
                taskHook(UserUtil.getLoginUser().getUser().getCode(),task.getCode(),"recycle","",0,
                        "","","",null,null);
            }
        }
        lambdaUpdate().eq(Task::getStage_code,stageCode).eq(Task::getDeleted,0)
                .set(Task::getDeleted,1).set(Task::getDeleted_time,DateUtil.getCurrentDateTime())
                .update();
    }

    @Transactional
    public void batchAssignTask(String taskCodes,String executorCode){
        JSONArray jsonArray = JSON.parseArray(taskCodes);
        for (Object obj : jsonArray) {
            assignTask(String.valueOf(obj),executorCode);
        }
    }
    @Transactional
    public TaskMember assignTask(String taskCode,String executorCode){
        if(StringUtils.isEmpty(taskCode)){
            throw new CustomException("请选择任务！");
        }
        Task task = lambdaQuery().eq(Task::getCode,taskCode).one();
        if(ObjectUtils.isEmpty(task)){
            throw new CustomException("请选择任务！");
        }
        if(task.getDeleted()==1){
            throw new CustomException("任务在回收站中无法进行指派！");
        }
        return taskMemberService.inviteMember(executorCode,taskCode,1,0,false,false);
    }

    public IPage<Map> getMemberTasks(IPage<Map> ipage, Map params){
        /*if(type == 0){
            return baseMapper.selectTaskSelfListNoFinish(ipage,params);
        }else{
            return baseMapper.selectTaskSelfListAll(ipage,params);
        }*/
        Integer taskType = MapUtils.getInteger(params,"taskType",1);
        Integer done = MapUtils.getInteger(params,"done",0);
        String memberCode = MapUtils.getString(params,"memberCode");
        String doneSql = "";
        if(-1!=done){
            doneSql="and t.done = " + done;
        }
        String sql = null;
        //我执行的
        if(1==taskType){
            sql = "select t.project_code,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time, t.pcode,t.sort,t.`liked`,t.star,t.deleted_time,t.pri,t.private,t.id_num,t.path,t.`schedule`,t.version_code, t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.`order`, p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.archive_time, p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule, t.create_time,t.create_by,p.description,t.id as id,t.name as name,t.code as code from team_task as t join team_project as p on t.project_code = p.code where  t.deleted = 0 "+doneSql+" and t.assign_to = '"+memberCode+"' and p.deleted = 0 order by t.id desc";
        }
        //我参与的
        if(2==taskType){
            sql = "select t.project_code,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time, t.pcode,t.sort,t.`liked`,t.star,t.deleted_time,t.pri,t.private,t.id_num,t.path,t.`schedule`,t.version_code, t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.`order`, p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.archive_time, p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule, t.create_time,t.create_by,p.description,t.id as id,t.name as name,t.code as code from team_task as t join team_project as p on t.project_code = p.code left join team_task_member as tm on tm.task_code = t.code where  t.deleted = 0 "+doneSql+" and tm.member_code = '"+memberCode+"' and p.deleted = 0 order by t.id desc";
        }
        //我创建的
        if(3==taskType){
            sql = "select t.project_code,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time, t.pcode,t.sort,t.`liked`,t.star,t.deleted_time,t.pri,t.private,t.id_num,t.path,t.`schedule`,t.version_code, t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.`order`, p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.archive_time, p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule, t.create_time,t.create_by,p.description,t.id as id,t.name as name,t.code as code from team_task as t join team_project as p on t.project_code = p.code where  t.deleted = 0 "+doneSql+" and t.create_by = '"+memberCode+"' and p.deleted = 0 order by t.id desc";
        }
        return commMapper.customQueryItem(ipage,sql);
    }

    public List<Map> getTaskListByVersionAndDelete(Map params){
        return  baseMapper.selectTaskListByVersionAndDelete(params);
    }

    @Autowired
    private  TaskStageService taskStageService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MemberService memberService;

    @Transactional
    public AjaxResult createTask(Task task,String pcode){
        TaskStage ts = taskStageService.getTaskStageByCode(task.getStage_code());
        if(ObjectUtils.isEmpty(ts)){
            return AjaxResult.warn("该任务列表无效");
        }
        Project project = projectService.getProjectByCodeNotDel(task.getProject_code());
        if(ObjectUtils.isEmpty(project)){
            return AjaxResult.warn("该项目已失效");
        }

        Member member = memberService.getMemberByCode(task.getAssign_to());
        if(ObjectUtils.isEmpty(member)){
            return AjaxResult.warn("任务执行人有误");
        }
        Map parentTask = null;
        if(StringUtils.isNotEmpty(pcode)){
            parentTask = getTaskMapByCode(pcode);
            if(ObjectUtils.isEmpty(parentTask)){
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

        Integer maxIdNum = baseMapper.selectMaxIdNumByProjectCode(task.getProject_code());
        String path = "";
        if(maxIdNum == null)maxIdNum = 0;
        if(!ObjectUtils.isEmpty(parentTask)){
            String parentPath = MapUtils.getString(parentTask,"path");
            if(StringUtils.isNotEmpty(parentPath)){
                parentPath = ","+parentPath;
            }else{
                parentPath = "";
            }
            path = MapUtils.getString(parentTask,"code")+parentPath;
        }
        task.setCreate_time(DateUtil.getCurrentDateTime());
        task.setCode(CommUtils.getUUID());
        task.setPath(path);
        task.setPri(0);
        if(null == project.getOpen_task_private() || 0 == project.getOpen_task_private()){
            task.setPrivated(0);
        }else{
            task.setPrivated(1);
        }
        task.setId_num(maxIdNum+1);
        int i = baseMapper.insert(task);
        taskHook(UserUtil.getLoginUser().getUser().getCode(),task.getCode(),"create","",0,
                "","","",null,null);
        if(StringUtils.isNotEmpty(pcode)){
            taskHook(UserUtil.getLoginUser().getUser().getCode(),pcode,"createChild","",0,
                    "","","",new HashMap(){{
                        put("taskName",task.getName());
                    }},null);
        }

        String logType = "inviteMember";
        Integer isExecutor = 0;

        if(StringUtils.isNotEmpty(task.getAssign_to())){
            if(task.getAssign_to().equals(UserUtil.getLoginUser().getUser().getCode())){
                logType="claim";
                isExecutor=1;
            }
            taskMemberService.inviteMember(task.getAssign_to(),task.getCode(),1,isExecutor,false,false);
        }
        if(StringUtils.isEmpty(task.getAssign_to()) || isExecutor==1){
            taskMemberService.inviteMember(task.getAssign_to(),task.getCode(),0,1,false,false);
        }

        if(i>0){
            Map taskMap = baseMapper.selectTaskByCode(task.getCode());
            taskWorkflowService.queryRule(task.getProject_code(), task.getStage_code(), task.getCode(), null, 0);
            return AjaxResult.success(buildTaskMap(taskMap,task.getCreate_by()));
        }
        return AjaxResult.warn("保存失败！");
    }

    protected String getPriTextAttr(String pri){
        Map<String,String> status = new HashMap(){{
           put("0","普通");
           put("1","紧急");
           put("2","非常紧急");
        }};
        if(StringUtils.isEmpty(pri)){
            pri = "0";
        }
        return status.get(pri);
    }
    protected String getStatusTextAttr(String stat){
        Map<String,String> status = new HashMap(){{
            put("0","未开始");
            put("1","已完成");
            put("2","进行中");
            put("3","挂起");
            put("4","测试中");
        }};
        if(StringUtils.isEmpty(stat)){
            stat = "0";
        }
        return status.get(stat);
    }
    @Autowired
    TaskToTagService taskToTagService;
    /**
     * 标签
     */
    @Autowired
    TaskToTagMapper taskToTagMapper;
    @Autowired
    TaskTagMapper taskTagMapper;
    protected  List<Map> getTagsAttr(String taskCode){
        List<Map> tags = new ArrayList();
        List<Map> result = new ArrayList<>();
        if(StringUtils.isNotEmpty(taskCode)){
            tags = taskToTagMapper.selectTaskToTagByTaskCode(taskCode);
            if(CollectionUtils.isNotEmpty(tags)){
                tags.stream().forEach(map -> {
                    Map tag = taskTagMapper.selectTaskTagByCode(MapUtils.getString(map,"tag_code"));
                    map.put("tag",tag);
                    result.add(map);
                });
            }

        }
        return result;
    }
    /**
     * 子任务数
     */
    protected List getChildCountAttr(String taskCode){
        List childTasks = new ArrayList();
        Map childCount0 = selectChildCount0(taskCode);
        Map childCount1 = selectChildCount1(taskCode);
        childTasks.add(childCount0.get("tp_count"));
        childTasks.add(childCount1.get("tp_count"));
        return childTasks;
    }

    public Integer getParentDoneAttr(String code){
        Integer done = 1;
        //Map parentDone=baseMapper.selectParentDone(code);
        Task parentDone = lambdaQuery().eq(Task::getCode,code).one();
        /*if(!MapUtils.isEmpty(parentDone) && MapUtils.getInteger(parentDone,"done",0)!=0 && MapUtils.getInteger(parentDone,"deleted",0)!=0){
            done = 0;
        }else{
            done = 1;
        }*/
        if(ObjectUtil.isNotEmpty(parentDone) && parentDone.getDeleted()==0&& parentDone.getDone() ==0){
            done = 0;
        }

        return done;
    }

    public Integer getHasUnDoneAttr(String code){
        Integer hasUnDone = 0;
        Map parentDone = baseMapper.selectHasUnDone(code);
        Integer tp_count = MapUtils.getInteger(parentDone,"tp_count",0);
        if(tp_count>0){
            hasUnDone = 1;
        }else{
            hasUnDone = 0;
        }
        return hasUnDone;
    }

    public Integer getHasCommentAttr(String code){
        Map hasComment = baseMapper.selectHasComment(code);
        if(!MapUtils.isEmpty(hasComment)){
            return MapUtils.getInteger(hasComment,"tp_count");
        }else{
            return 0;
        }
    }

    protected Integer getHasSourceAttr(String code){
        Map hasSource = baseMapper.selectHasSource(code);
        if(!MapUtils.isEmpty(hasSource)){
            return MapUtils.getInteger(hasSource,"tp_count");
        }else{
            return 0;
        }
    }
    protected Integer getCanReadAttr(String taskCode,String memberCode,Integer privated){

        Integer canRead = 1;
        if(null !=privated){
            if(privated > 0){
                Map canReadMap = baseMapper.selectCanRead(taskCode,memberCode);
                if(MapUtils.isEmpty(canReadMap)){
                    canRead = 0;
                }
            }
        }
        return canRead;
    }

    protected  Integer getLikedAttr(String code,String memberCode){
        Integer like = 0;
        Map taskLike = baseMapper.selectTaskLike(code,memberCode);
        if(MapUtils.isNotEmpty(taskLike)){
            like = 1;
        }
        return like;
    }

    protected Integer getStaredAttr(String code,String memberCode){
        Integer stared = 0;
        Map taskStar = baseMapper.selectTaskStared(code,memberCode);
        if(MapUtils.isNotEmpty(taskStar)){
            stared = 1;
        }
        return stared;
    }
    public Integer getDateTaskTotalForProject(String projectCode,String beginTime,String endTime){
        return baseMapper.selectDateTaskTotalForProject(projectCode,beginTime,endTime);
    }
    public void sort(String stageCode,List<String> codes){
        if(CollectionUtils.isEmpty(codes)){
            return ;
        }
        TaskStage taskStage= taskStageService.lambdaQuery().eq(TaskStage::getCode,stageCode).one();
        for(int i=0;i<codes.size();i++){
            Task task = lambdaQuery().eq(Task::getCode,codes.get(i)).one();
            lambdaUpdate().set(Task::getSort,i).set(Task::getStage_code,stageCode).eq(Task::getCode,codes.get(i)).update();
            if(!stageCode.equals(task.getStage_code())){
                taskHook(UserUtil.getLoginUser().getUser().getCode(),codes.get(i),"move","",0,
                        "","","",new HashMap(){{
                            put("stageName",taskStage.getName());
                        }},null);
            }
        }

    }

    public Map buildTaskMap(Map task,String memberCode){
        String taskCode = MapUtils.getString(task,"code");
        task.put("priText",getPriTextAttr(MapUtils.getString(task,"pri")));
        task.put("statusText",getStatusTextAttr(MapUtils.getString(task,"status")));
        task.put("liked",getLikedAttr(taskCode,memberCode));
        task.put("stared",getStaredAttr(taskCode,memberCode));
        task.put("tags",getTagsAttr(taskCode));
        task.put("childCount",getChildCountAttr(taskCode));
        task.put("hasUnDone",getHasUnDoneAttr(taskCode));
        task.put("parentDone",getParentDoneAttr(MapUtils.getString(task,"pcode")));
        task.put("hasComment",getHasCommentAttr(taskCode));
        task.put("hasSource",getHasSourceAttr(taskCode));
        task.put("canRead",getCanReadAttr(taskCode,memberCode,MapUtils.getInteger(task,"private")));
        return task;
    }

    public Map readTask(String taskCode,String memberCode){
        Map task = baseMapper.selectTaskByCode(taskCode);
        LambdaQueryWrapper<Project> projectQW = new LambdaQueryWrapper<>();
        projectQW.eq(Project::getCode, MapUtils.getString(task,"project_code"));
        Project project = projectService.getBaseMapper().selectOne(projectQW);
        LambdaQueryWrapper<TaskStage> taskStageQW = new LambdaQueryWrapper<>();
        taskStageQW.eq(TaskStage::getCode, MapUtils.getString(task,"stage_code"));
        TaskStage taskStage = taskStageService.getBaseMapper().selectOne(taskStageQW);
        task.put("executor",null);
        if(StringUtils.isNotEmpty(MapUtils.getString(task,"assign_to"))){
            Member member = memberService.getMemberByCode(MapUtils.getString(task,"assign_to"));
            task.put("executor",member);
        }
        if(StringUtils.isNotEmpty(MapUtils.getString(task,"pcode"))){
            Task pTask = baseMapper.selTaskByCode(MapUtils.getString(task,"pcode"));
            task.put("parentTask",pTask);
            List<Map> pathList = new ArrayList<>();
            if(StringUtils.isNotEmpty(MapUtils.getString(task,"path"))){
                String path = MapUtils.getString(task,"path");
                String[] paths = path.split(",");
                for(int i=paths.length-1;i>=0;i--){
                    Task t = baseMapper.selTaskByCode(paths[i]);
                    int finalI = i;
                    pathList.add(new HashMap(){{
                        put("code",paths[finalI]);
                        put("name",t.getName());
                    }});
                }
            }
            task.put("parentTasks",pathList);
        }
        task.put("openBeginTime",project.getOpen_begin_time());
        task.put("projectName",project.getName());
        task.put("stageName",taskStage.getName());
        return buildTaskMap(task,memberCode);
    }

    public IPage<Map> taskIndex(IPage<Map> page,Map param){
        page = baseMapper.selectTaskListByParam(page,param);
        String memberCode = MapUtils.getString(param,"memberCode");
        List<Map> result = new ArrayList<>();
        List<Map> taskList = page.getRecords();
        if(CollectionUtils.isNotEmpty(taskList)){
            taskList.stream().forEach(map -> {
                Member member = memberService.getMemberByCode(MapUtils.getString(map,"assign_to"));
                if(!ObjectUtils.isEmpty(member)){
                    map.put("executor",new HashMap(){{
                        put("name",member.getName());
                        put("avatar",member.getAvatar());
                    }});
                }
                map = buildTaskMap(map,memberCode);
                result.add(map);
            });
        }
        page.setRecords(result);
        return page;
    }

    @Transactional
    public void like(Map taskMap,String memberCode,Integer likeData){
        Integer like = MapUtils.getInteger(taskMap,"like");
        String code = MapUtils.getString(taskMap,"code");
        LambdaUpdateWrapper<TaskLike> taskLikeUW = new LambdaUpdateWrapper<TaskLike>();
        if(0==likeData) {
            like = like-1;
            taskLikeUW.eq(TaskLike::getMember_code,memberCode);
            taskLikeUW.eq(TaskLike::getTask_code,code);
            taskLikeMapper.delete(taskLikeUW);
        }
        if(1==likeData) {
            like = like+1;
            taskLikeMapper.insert(TaskLike.builder().create_time(DateUtil.getCurrentDateTime())
                    .member_code(memberCode).task_code(code).build());
        }
        baseMapper.updateTaskLike(like,code);
    }

    @Autowired
    TaskMemberService taskMemberService;
    @Autowired
    TaskLikeService taskLikeService;

    @Transactional
    public void delete(String taskCode){
        Task task = getTaskByCode(taskCode);
        if(ObjectUtil.isEmpty(task)){
            throw new CustomException("任务不存在");
        }
        lambdaUpdate().eq(Task::getCode,taskCode).remove();
        lambdaUpdate().eq(Task::getPcode,taskCode).remove();
        taskMemberService.lambdaUpdate().eq(TaskMember::getTask_code,taskCode).remove();
        taskLikeMapper.delete(Wrappers.<TaskLike>lambdaUpdate().eq(TaskLike::getTask_code,taskCode));
        projectLogService.lambdaUpdate().eq(ProjectLog::getSource_code,taskCode).eq(ProjectLog::getAction_type,"task").remove();
    }

    @Transactional
    public void recovery(String taskCode){
        Task task = getTaskByCode(taskCode);
        if(ObjectUtil.isEmpty(task)){
            throw new CustomException("任务不存在");
        }
        if(task.getDeleted()==0){
            throw new CustomException("任务已恢复");
        }
        lambdaUpdate().eq(Task::getCode,taskCode).set(Task::getDeleted,0).update();
        taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"recovery","",0,
                "","","",null,null);

    }

    @Transactional
    public void  recycle(String taskCode,String memberCode){
        Task task = getTaskByCode(taskCode);
        task.setDeleted(1);
        task.setDeleted_time(DateUtil.getCurrentDateTime());
        updateById(task);
        taskHook(memberCode,taskCode,"recycle","",0,
                "","","",null,null);
    }

    @Autowired
    CollectionService collectionService;

    @Transactional
    public void star(Map taskMap,String memberCode,Integer starData){
        String code = MapUtils.getString(taskMap,"code");
        Integer star = MapUtils.getInteger(taskMap,"star");
        if(1==starData){
            star = star+1;
        }else {
            star = star-1;
        }
        baseMapper.updateTaskStar(star,code);
        collectionService.starTask(code,memberCode,star);
    }

    @Transactional
    public void edit(Task task,String memberCode){

        String type = null;
        if(StringUtils.isNotEmpty(task.getDescription()) || "<p><br></p>".equals(task.getDescription())){
            task.setDescription("");
            type = "clearContent";
        }
        updateById(task);

        if(StringUtils.isNotEmpty(task.getName())){
            type = "name";
        }
        if(StringUtils.isNotEmpty(task.getDescription())){
            type = "content";
        }
        if(!ObjectUtils.isEmpty(task.getPri())){
            type = "pri";
        }
        if(!ObjectUtils.isEmpty(task.getStatus())){
            type = "status";
        }
        if(StringUtils.isNotEmpty(task.getBegin_time())){
            type = "setBeginTime";
        }
        if("".equals(task.getBegin_time())){
            type = "clearBeginTime";
        }
        if(StringUtils.isNotEmpty(task.getEnd_time())){
            type = "setEndTime";
        }
        if("".equals(task.getEnd_time())){
            type = "clearEndTime";
        }
        if(!ObjectUtils.isEmpty(task.getWork_time()) && task.getWork_time()>0){
            type = "setWorkTime";
        }
        if(StringUtils.isNotEmpty(type)){
            String finalType = type;
            projectLogService.run(new HashMap(){{
                put("member_code",memberCode);
                put("source_code",task.getProject_code());
                put("type", finalType);
                put("is_comment",0);
            }});
        }
    }

    public void taskHook(String memberCode,String taskCode,String type,String toMemberCode,Integer isComment,
                         String remark,String content,String fileCode,Object data,String tag){
        run(new HashMap(){{
            put("memberCode",memberCode);
            put("taskCode",taskCode);
            put("toMemberCode",toMemberCode);
            put("isComment",isComment);
            put("remark",remark);
            put("content",content);
            put("fileCode",fileCode);
            put("type", type);
            put("is_comment",0);
            put("data",data);
        }});
    }

    @Transactional
    public void taskDone(String taskCode,Integer done,String memberCode){
        Map taskMap = getTaskMapByCode(taskCode);
        taskMap = buildTaskMap(taskMap,memberCode);
                if(MapUtils.isEmpty(taskMap)){
            throw new CustomException("任务已失效");
        }
        if(MapUtils.getInteger(taskMap,"deleted",0)>0){
            throw new CustomException("任务在回收站中无法进行编辑");
        }
        if(StringUtils.isNotEmpty(MapUtils.getString(taskMap,"pcode")) && MapUtils.getInteger(taskMap,"parentDone",0)>0){
            throw new CustomException("父任务已完成，无法重做子任务");
        }
        if(MapUtils.getInteger(taskMap,"hasUnDone",0)>0){
            throw new CustomException("子任务尚未全部完成，无法完成父任务");
        }
        lambdaUpdate().eq(Task::getCode,taskCode).set(Task::getDone,done).update();
        String projectCode = MapUtils.getString(taskMap, "project_code");
        String stageCode = MapUtils.getString(taskMap, "stage_code");
        if (done == 1) {
        	taskWorkflowService.queryRule(projectCode, stageCode, taskCode, null, 1);
        }        
        LambdaQueryWrapper<Project> projectQW = new LambdaQueryWrapper<>();
        projectQW.eq(Project::getCode, MapUtils.getString(taskMap,"project_code"));
        Project project = projectService.getBaseMapper().selectOne(projectQW);
        if(null != project && project.getAuto_update_schedule()>0){
            Integer taskCount = baseMapper.selectCountByProjectCode(MapUtils.getString(taskMap,"project_code"));
            if(taskCount>0){
                Integer doneTaskCount = baseMapper.selectCountByProjectCodeAndDone(MapUtils.getString(taskMap,"project_code"));
                taskCount = taskCount==0?1:taskCount;
                double f1 = new BigDecimal((float)((float)doneTaskCount/(float)taskCount)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                project.setSchedule(f1);

                //project.setSchedule((double) (doneTaskCount/taskCount*100));
                projectService.updateById(project);
            }
        }
        taskHook(memberCode,taskCode,done>0?"done":"redo","",0,
                "","","",null,null);
        if(StringUtils.isNotEmpty(MapUtils.getString(taskMap,"pcode"))){
            taskHook(memberCode,MapUtils.getString(taskMap,"pcode"),done>0?"doneChild":"redoChild","",0,
                    "","","",null,null);
        }
    }
    @Autowired
    ProjectVersionService projectVersionService;


    public void run(Map data){
        int isRobot = MapUtils.getObject(data,"data")!=null && MapUtils.getString((Map)data.get("data"),"is_robot")!=null?1:0;
        ProjectLog logData = ProjectLog.builder().member_code(MapUtils.getString(data,"memberCode"))
                .source_code(MapUtils.getString(data,"taskCode"))
                .remark(MapUtils.getString(data,"remark"))
                .type(MapUtils.getString(data,"type"))
                .content(MapUtils.getString(data,"content"))
                .is_comment(MapUtils.getInteger(data,"isComment"))
                .to_member_code(MapUtils.getString(data,"toMemberCode"))
                .create_time(DateUtil.getCurrentDateTime())
                .code(CommUtils.getUUID())
                .action_type("task").is_robot(isRobot).build();
        Task task = getTaskByCode(MapUtils.getString(data,"taskCode"));
        logData.setProject_code(task.getProject_code());
        Member toMember = null;
        if(StringUtils.isNotEmpty(MapUtils.getString(data,"toMemberCode"))){
            toMember = memberService.getMemberByCode(MapUtils.getString(data,"toMemberCode"));
        }
        Notify notifyData = Notify.builder().title("").content("")
                .type("message").action("task").terminal("project").source_code(task.getCode()).build();
        String remark="";
        String content="";
        String icon = "";
        switch (MapUtils.getString(data,"type","")){
            case "create":
                icon = "plus";
                remark = "创建了任务 ";
                content = task.getName();
                break;
            case "name":
                icon = "edit";
                remark = "更新了内容 ";
                content = task.getName();
                break;
            case "move":
                icon = "drag";
                remark = "将任务移动到 "+MapUtils.getString((Map)data.get("data"),"stageName");
                content = task.getName();
                break;
            case "content":
                icon = "file-text";
                remark = "更新了备注 ";
                content = task.getDescription();
                break;
            case "clearContent":
                icon = "file-text";
                remark = "清空了备注 ";
                break;
            case "done":
                icon = "check";
                remark = "完成了任务 ";
                if (StringUtils.isNotEmpty(task.getVersion_code()) && !"0".equals(task.getVersion_code())) {
                    projectVersionService.updateSchedule(task.getVersion_code());
                }
                break;
            case "redo":
                icon = "border";
                remark = "重做了任务 ";
                if (StringUtils.isNotEmpty(task.getVersion_code()) && !"0".equals(task.getVersion_code())) {
                    projectVersionService.updateSchedule(task.getVersion_code());
                }
                break;
            case "createChild":
                icon = "bars";
                remark = "添加了子任务 "+MapUtils.getString((Map)data.get("data"),"taskName");
                break;
            case "doneChild":
                icon = "bars";
                remark = "完成了子任务 "+ task.getName();
                break;
            case "redoChild":
                icon = "undo";
                remark = "重做了子任务 "+ task.getName();
                break;
            case "claim":
                icon = "user";
                remark = "认领了任务 ";
                break;
            case "assign":
                icon = "user";
                remark = "指派给了 "+toMember.getName();
                break;
            case "pri":
                icon = "user";
                remark = "更新任务优先级为 "+getPriTextAttr(String.valueOf(task.getPri()));
                break;
            case "status":
                icon = "deployment-unit";
                remark = "修改执行状态为 " +getStatusTextAttr(String.valueOf(task.getStatus())) ;
                break;
            case "removeExecutor":
                icon = "user-delete";
                remark = "移除了执行者 ";
                break;
            case "changeState":
                icon = "edit";
                TaskStage taskStage = taskStageService.getTaskStageByCode(task.getStage_code());
                remark = "将任务移动到 "+taskStage.getName();
                break;
            case "inviteMember":
                icon = "user-add";
                remark = "添加了参与者 "+toMember.getName();
                break;
            case "removeMember":
                icon = "user-delete";
                remark = "移除了参与者 "+toMember.getName();
                break;
            case "setBeginTime":
                icon = "calendar";
                remark = "更新开始时间为 "+task.getBegin_time();
                break;
            case "clearBeginTime":
                icon = "calendar";
                remark = "清除了开始时间 ";
                break;
            case "setEndTime":
                icon = "calendar";
                remark = "更新截止时间为 "+ task.getEnd_time();
                break;
            case "clearEndTime":
                icon = "calendar";
                remark = "清除了截止时间 ";
                break;
            case "recycle":
                icon = "delete";
                remark = "把任务移到了回收站 ";
                break;
            case "recovery":
                icon = "undo";
                remark = "恢复了任务 ";
                break;
            case "setWorkTime":
                icon = "clock-circle";
                remark = "更新预估工时为 "+task.getWork_time();
                break;
            case "linkFile":
                icon = "link";
                remark = "关联了文件 ";
                content = "<a target='_blank' class='muted' href='"+MapUtils.getString((Map)data.get("data"),"url")+ "'>{$data['data']['title']}</a>";

                break;
            case "unlinkFile":
                icon = "disconnect";
                remark = "取消关联文件";
                content = "<a target='_blank' class='muted' href='"+MapUtils.getString((Map)data.get("data"),"url")+ "'>"+MapUtils.getString((Map)data.get("data"),"title")+ "</a>";
                break;
            case "comment":
                icon = "file-text";
                remark = MapUtils.getString(data,"content","");
                content = MapUtils.getString(data,"content","");
                break;
            default:
                icon = "plus";
                remark = " 创建了任务 ";
                break;
        }
        logData.setIcon(icon);
        if(logData.getIs_robot()>0){
            logData.setIcon("alert");
        }
        if(StringUtils.isNotEmpty(MapUtils.getString(data,"remark"))){
            logData.setRemark(MapUtils.getString(data,"remark"));
        }else{
            logData.setRemark(remark);
        }
        if(StringUtils.isNotEmpty(MapUtils.getString(data,"content"))){
            logData.setContent(MapUtils.getString(data,"content"));
        }else{
            logData.setContent(content);
        }
        projectLogService.save(logData);

        //工作流事件
        //触发推送的事件
        ArrayList<String> notifyActions = new ArrayList<String>(){{
            add("done");add("redo");add("assign");add("comment");
        }};
        Member member = memberService.lambdaQuery().eq(Member::getCode,MapUtils.getString(data,"memberCode")).one();
        if(ObjectUtil.isNotEmpty(member)){
            notifyData.setTitle(member.getName()+": "+remark);
            notifyData.setContent(task.getName());
            notifyData.setAvatar(member.getAvatar());
        }
        List<String> taskMembers = new ArrayList<>();
        if(notifyActions.contains(MapUtils.getString(data,"type","NULL"))){
            if(MapUtils.getString(data,"type","NULL").equals("comment")){
                notifyData.setType("notice");
                if(ObjectUtil.isNotEmpty(MapUtils.getObject(data,"data"))){
                    List<String> stringList = (List)MapUtils.getObject((Map)MapUtils.getObject(data,"data"),"list",new ArrayList());
                    for(String item:stringList){
                        item = item.split(" ")[0];
                        String memberCode = baseMapper.selectMemberCodeOne(task.getCode(),item);
                        if(StringUtils.isNotEmpty(memberCode)){
                            taskMembers.add(memberCode);
                        }
                    }
                }
            }else{
                List<TaskMember> taskMemberList = taskMemberService.lambdaQuery().eq(TaskMember::getTask_code,task.getCode()).list();
                if(CollectionUtils.isNotEmpty(taskMemberList)){
                    for(TaskMember taskMember:taskMemberList){
                        taskMembers.add(taskMember.getMember_code());
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(taskMembers)){
                for(String taskMemberCode:taskMembers){
                    if(taskMemberCode.equals(MapUtils.getString(data,"memberCode"))){
                        continue;//跳过产生者
                    }
                    //notifyService.save(Notify.builder()
                    Notify build = Notify.builder()		
                            .content(notifyData.getContent())
                            .title(notifyData.getTitle())
                            .type(notifyData.getType())
                            .from(MapUtils.getString(data,"memberCode"))
                            .to(taskMemberCode)
                            .action(notifyData.getAction())
                            .avatar(notifyData.getAvatar()).terminal(notifyData.getTerminal())
                            .from_type("system").create_time(DateUtil.getCurrentDateTime())
                            .build();
                    notifyService.save(build);
                    webSocketServer.sendInfo(build, taskMemberCode);
                }
            }
        }

        //todo 短信,消息推送
        //通知所有组织内的成员
        // todo
    }

    @Transactional
    public void createComment(String taskCode,String comment,String mentions){
        if(StringUtils.isEmpty(taskCode)){
            throw new CustomException("请选择任务");
        }
        Task task = lambdaQuery().eq(Task::getCode,taskCode).one();
        if(ObjectUtil.isEmpty(task)){
            throw new CustomException("任务已失效！");
        }
        List<String> mentionList = new ArrayList<>();
        if(StringUtils.isNotEmpty(mentions)){
            JSONArray jsonArray = JSON.parseArray(mentions);
            for (Object obj : jsonArray) {
                mentionList.add(String.valueOf(obj));
            }
        }
        taskHook(UserUtil.getLoginUser().getUser().getCode(),taskCode,"comment","",1,
                "",comment,"",new HashMap(){{
                    put("list",mentionList);
                }},null);
    }

    @Autowired
    private TaskTagService taskTagService;

    @Transactional(rollbackFor = Exception.class)
    public void saveTaskList(String memberCode, List<Task> taskList, String projectCode) {
    	List<String> assNameList = taskList.parallelStream().map(Task::getAssign_to).distinct().collect(Collectors.toList());
        //用户
    	Map<String, String> memberNameCode = memberService.lambdaQuery().select(Member::getCode, Member::getName).in(Member::getName, assNameList).list()
    			.parallelStream().collect(Collectors.toMap(Member::getName, Member::getCode));
        //任务列表
        Map<String, String> stageNameCode = taskStageService.lambdaQuery().select(TaskStage::getName, TaskStage::getCode).eq(TaskStage::getProject_code, projectCode).list()
                .parallelStream().collect(Collectors.toMap(TaskStage::getName, TaskStage::getCode));
        //标签
        StringBuffer tagStr = new StringBuffer();
        taskList.forEach(o -> tagStr.append(o.getTask_tag()).append(";"));
        List<TaskTag> taskTagList = taskTagService.lambdaQuery().eq(TaskTag::getProject_code, projectCode).list();
        List<String> collect = taskTagList.parallelStream().map(TaskTag::getName).collect(Collectors.toList());
        Map<String, String> tagNameCode = taskTagList.parallelStream().collect(Collectors.toMap(TaskTag::getName, TaskTag::getCode));
        List<String> newTagList = Arrays.asList(tagStr.toString().split(";")).parallelStream().distinct().
                filter(o -> !collect.contains(o)).collect(Collectors.toList());
        //要新增的标签
        List<TaskTag> saveTaskTagList = new ArrayList<>();
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME));
        String[] colors = {"blue", "red", "orange", "green", "brown", "purple"};
        newTagList.forEach(o -> {
            String uuid = IdUtil.fastSimpleUUID();
            tagNameCode.put(o, uuid);
            TaskTag taskTag = TaskTag.builder().code(uuid).name(o).project_code(projectCode)
                    .create_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME)))
                    .color(colors[RandomUtil.randomInt(0, 6)]).build();
            saveTaskTagList.add(taskTag);
        });
        //新增的标签与任务关系
        List<TaskToTag> saveTaskToTagList = new ArrayList<>();
        taskList.forEach(o -> {
        	o.setCreate_by(memberCode);
        	o.setCreate_time(formatDate);
            if (StrUtil.isNotEmpty(o.getPriText())) {
                switch (o.getPriText()) {
                    case Constants.GENERAL_STR:
                        o.setPri(Constants.GENERAL);
                        break;
                    case Constants.URGENT_STR:
                        o.setPri(Constants.URGENT);
                        break;
                    case Constants.VERY_URGENT_STR:
                        o.setPri(Constants.VERY_URGENT);
                        break;
                    default:
                        break;
                }
            } else {
                o.setPri(Constants.GENERAL);
            }
            o.setAssign_to(memberNameCode.get(o.getAssign_to()));
            if (StrUtil.isNotEmpty(o.getTask_tag())) {
                String[] split = o.getTask_tag().split(";");
                for (String s : split) {
                    saveTaskToTagList.add(TaskToTag.builder().code(IdUtil.fastSimpleUUID()).tag_code(tagNameCode.get(s)).task_code(o.getCode())
                            .create_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME))).build());
                }
            }
            String s = stageNameCode.get(o.getStage_code());
            if (StrUtil.isNotEmpty(s)) {
                o.setStage_code(s);
            } else {
                throw new CustomException("任务列表存在错误！");
            }

        });
        build(taskList);
        boolean b = taskTagService.saveBatch(saveTaskTagList);
        boolean b1 = taskToTagService.saveBatch(saveTaskToTagList);
        boolean b2 = saveBatch(taskList);
        log.info("新建标签：{}，新建标签任务：{}，新建任务：{}", b, b1, b2);
    }

    private void build(List<Task> pList) {
        pList.forEach(o -> {
            if (StrUtil.isNotEmpty(o.getPName())) {
                for (Task o1 : pList) {
                    if (StrUtil.equals(o.getPName(), o1.getName())) {
                        o.setPcode(o1.getCode());
                        if (StrUtil.isEmpty(o1.getPath())) {
                            o.setPath(o1.getCode());
                        } else {
                            o.setPath(o1.getCode() + "," + o1.getPath());
                        }
                        break;
                    }
                }
            }
        });
    }
}
