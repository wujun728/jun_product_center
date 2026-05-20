package com.ruoyi.flowable.service.impl;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.common.constant.QueryConstants;
import com.ruoyi.flowable.common.enums.ActivityTypeEnum;
import com.ruoyi.flowable.common.service.FlowCommonService;
import com.ruoyi.flowable.domain.dto.*;
import com.ruoyi.flowable.domain.qo.*;
import com.ruoyi.flowable.domain.vo.FlowActivityVo;
import com.ruoyi.flowable.domain.vo.FlowInstanceInfoVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.domain.vo.TaskInfoVo;
import com.ruoyi.flowable.service.*;
import com.ruoyi.flowable.utils.FlowableUtil;
import com.ruoyi.tools.utils.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.db.SuspensionState;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p> 流程监控服务实现 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FlowMonitorServiceImpl implements IFlowMonitorService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IFlowHistoryService flowHistoryService;
    @Autowired
    private FlowCommonService flowCommonService;
    @Autowired
    private IFlowCommentService flowCommentService;
    @Autowired
    private IFlowRuntimeService flowRuntimeService;
    @Autowired
    private IFlowVariableInstanceService flowVariableService;

    private static final String ADD_COMMENT = "AddComment";

    /**
     * 查询所有正在运行的流程实例列表
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @param pageSize     页大小
     * @param pageNum      页码
     * @return TableDataInfo
     */
    @Override
    public TableDataInfo getProcessInstanceList(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum) {
        // 查询流程实例
        List<FlowProcessInstanceDto> processList =  getProcessInstances(procInstId, businessKey, name);
        if (CollectionUtils.isEmpty(processList)) {
            return getTableDataInfo(Collections.emptyList(), Collections.emptyList(), true);
        }

        List<String> procInstIds = processList.stream()
                .map(FlowProcessInstanceDto::getProcInstId)
                .collect(Collectors.toList());
        // 查看当前活动任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceIdIn(procInstIds).list();
        Map<String, List<Task>> taskByProcInstIdMap = tasks.stream()
                .collect(Collectors.groupingBy(Task::getProcessInstanceId));

        // 查询任务处理人
        Map<String, List<SysUser>> assigneeByTaskIdMap = flowCommonService.getTaskAssigneeMap(tasks);

        List<FlowInstanceInfoVo> flows = new ArrayList<>();
        processList.forEach(p -> {
            flows.add(bulidFlowInstanceInfoVo(p, taskByProcInstIdMap, assigneeByTaskIdMap));
        });
        return getTableDataInfo(flows, processList, true);
    }

    /**
     * 查询所有流程实例列表-包含在运行和已结束
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @param pageSize     页大小
     * @param pageNum      页码
     * @return TableDataInfo
     */
    @Override
    public TableDataInfo getHistoryListProcess(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum) {
        // 查询流程历史实例
        List<FlowHistoricProcessInstanceDto> processList = getHistoricProcessInstances(procInstId, businessKey, name);
        if (CollectionUtils.isEmpty(processList)) {
            return getTableDataInfo(Collections.emptyList(), Collections.emptyList(), true);
        }

        List<String> procInstIds = processList.stream().map(FlowHistoricProcessInstanceDto::getProcInstId).collect(Collectors.toList());
        // 查看当前活动任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceIdIn(procInstIds).list();
        Map<String, List<Task>> taskByProcInstIdMap = tasks.stream()
                .collect(Collectors.groupingBy(Task::getProcessInstanceId));
        // 查询任务处理人
        Map<String, List<SysUser>> assigneeByTaskIdMap = flowCommonService.getTaskAssigneeMap(tasks);

        List<FlowInstanceInfoVo> flows = new ArrayList<>();
        for (FlowHistoricProcessInstanceDto p : processList) {
            flows.add(buildFlowHistoricInstanceInfoVo(p, taskByProcInstIdMap, assigneeByTaskIdMap));
        }
        return getTableDataInfo(flows, processList, true);
    }

    /**
     * 获取流程实例信息
     *
     * @param processInstanceId 流程实例ID
     * @return FlowInstanceInfoVo
     */
    @Override
    public TableDataInfo getHistoryList(String processInstanceId, Integer pageSize, Integer pageNum) {
        // 查询流程环节历史活动实例
        List<FlowHistoricActivityInstanceDto> history = getHistoricActivityInstances(processInstanceId, false);
        if (CollectionUtils.isEmpty(history)) {
            return getTableDataInfo(Collections.emptyList(), null, false);
        }
        // 查询历史任务流程实例
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime().desc()
                .list();
        // 查询任务处理人
        Map<String, List<SysUser>> assigneeByTaskIdMap = flowCommonService.getHistoricAssigneeMap(historicTaskInstances);
        // 获取意见评论内容
        FlowCommentQo commentQo = new FlowCommentQo();
        commentQo.setProcInstId(processInstanceId);
        List<FlowCommentDto> commentList = flowCommentService.getComments(commentQo);
        Map<String, List<FlowCommentDto>> commentMap = commentList.stream()
                .collect(Collectors.groupingBy(FlowCommentDto::getTaskId));
        // 构建任务信息列表
        List<TaskInfoVo> infos = history.stream()
                .map(h -> buildTaskVoInfo(h, assigneeByTaskIdMap, commentMap))
                .collect(Collectors.toList());
        return getTableDataInfo(infos, null, false);
    }

    /**
     * 挂起流程实例
     *
     * @param processInstanceId 流程实例ID
     */
    @Override
    public void suspend(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    /**
     * 激活流程实例
     *
     * @param processInstanceId 流程实例ID
     */
    @Override
    public void reRun(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
    }

    /**
     * 查询跳转环节列表
     *
     * @param flowActivityVo 流程活动信息
     * @return
     */
    @Override
    public List<UserTask> getJumpActivityList(FlowActivityVo flowActivityVo) {
        if (StringUtils.isBlank(flowActivityVo.getDefId())) {
            throw new FlowableException("defId不能为空");
        }
        // 获取所有节点信息
        Process process = repositoryService.getBpmnModel(flowActivityVo.getDefId()).getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        if (CollectionUtils.isEmpty(flowElements)) {
            return Collections.emptyList();
        }
        // 可跳转的节点列表
        List<UserTask> userTaskList = new ArrayList<>();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                userTaskList.add((UserTask) flowElement);
            }
        }
        return userTaskList;
    }

    /**
     * 跳转流程环节
     *
     * @param flowTaskVo 流程任务信息
     */
    @Override
    public void jumpActivity(FlowTaskVo flowTaskVo) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(flowTaskVo.getProcInsId()).list();
        if (CollectionUtils.isEmpty(taskList)) {
            throw new FlowableException("流程未启动或已执行完成，无法跳转");
        }
        Task task = taskList.get(0);
        if (task.isSuspended()) {
            throw new BaseException("当前任务处于挂起状态，操作失败");
        }
        // 获取所有节点信息
        BpmnModel bpmnModel = repositoryService.getBpmnModel(flowTaskVo.getDefId());
        // 获取当前任务节点元素
        FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
        // 获取跳转的节点元素
        FlowElement target = bpmnModel.getFlowElement(flowTaskVo.getTargetKey());
        if (source == null ) {
            throw new FlowableException("当前节点为空，跳转失败!");
        }
        if (target == null ) {
            throw new FlowableException("跳转节点为空，跳转失败!");
        }

        // 从当前节点向前扫描，如果存在路线上不存在目标节点，说明目标节点是在网关上或非同一路线上，不可跳转；否则目标节点相对于当前节点，属于串行
        boolean isSequential = FlowableUtil.iteratorCheckSequentialReferTarget(source, flowTaskVo.getTargetKey(), null, null)
                || FlowableUtil.iteratorFindTargetInPathExcludeParallel(source, flowTaskVo.getTargetKey(), null, null);
        if (!isSequential) {
            throw new FlowableException("当前节点相对于目标节点，不属于串行关系，无法跳转");
        }

        try {
            runtimeService.createChangeActivityStateBuilder()
                    .processInstanceId(flowTaskVo.getProcInsId())
                    .processVariables(flowTaskVo.getVariables()) //设置动态处理人
                    .moveActivityIdTo(source.getId(), target.getId())
                    .changeState();
        } catch (FlowableObjectNotFoundException e) {
            throw new FlowableException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            throw new FlowableException("无法取消或开始活动");
        }
    }

    /**
     * 获取跳转节点信息
     *
     * @param flowActivityVo 流程活动信息
     * @return List<FlowNextDto>
     */
    @Override
    public List<FlowNextDto> getJumpActivityNode(FlowActivityVo flowActivityVo) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(flowActivityVo.getDefId());
        FlowElement flowElement = bpmnModel.getFlowElement(flowActivityVo.getTargetKey());
        FlowVariableInstanceQo qo = new FlowVariableInstanceQo();
        qo.setProcessInstanceId(flowActivityVo.getProcInsId());
        flowActivityVo.getVariables().putAll(flowVariableService.getVariableInstanceMap(qo));
        flowActivityVo.getVariables().put(ProcessConstants.PROCESS_INSTANCE_ID, flowActivityVo.getProcInsId());
        return getFlowAttribute(Collections.singletonList((UserTask) flowElement), flowActivityVo.getVariables());
    }

    /**
     * 获取流程环节任务列表
     *
     * @param flowTaskVo 流程任务信息
     * @return
     */
    @Override
    public List<FlowTaskDto> getFlowNodeTasks(FlowTaskVo flowTaskVo) {
        return flowCommonService.getCurrentTaskList(flowTaskVo.getProcInsId());
    }

    /**
     * 获取完成流程环节任务列表
     *
     * @param flowTaskVo 流程任务信息
     * @return
     */
    @Override
    public List<FlowTaskDto> getFinishFlowNodeTasks(FlowTaskVo flowTaskVo) {
        // 查询上一个环节实例
        List<FlowHistoricActivityInstanceDto> historicActivityInstances = getHistoricActivityInstances(flowTaskVo.getProcInsId(), true);
        if (CollectionUtils.isEmpty(historicActivityInstances)) {
            return Collections.emptyList();
        }

        FlowHistoricActivityInstanceDto historicActivityInstance = historicActivityInstances.get(historicActivityInstances.size() - 1);
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(historicActivityInstance.getProcInstId())
                .taskDefinitionKey(historicActivityInstance.getActivityId())
                .list();
        // 对于同一个环节（activityId），只保留最新的历史任务实例（即createTime最大）
        List<HistoricTaskInstance> distinctHistoricTaskInstances = getDistinctHistoricTaskInstances(historicTaskInstances);

        Map<String, List<SysUser>> historicAssigneeMap = flowCommonService.getHistoricAssigneeMap(distinctHistoricTaskInstances);

        List<FlowTaskDto> hisFlowList = new ArrayList<>();
        for (HistoricTaskInstance histIns : historicTaskInstances) {
            FlowTaskDto flowTask = getFlowTaskDto(histIns, historicActivityInstance);
            flowCommonService.handleMultiAssignee(histIns.getId(), flowTask, hisFlowList, historicAssigneeMap);
        }
        return hisFlowList;
    }

    /**
     * 获取流程实例列表
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @return List<FlowProcessInstanceDto>
     */
    private List<FlowProcessInstanceDto> getProcessInstances(String procInstId, String businessKey, String name) {
        FlowProcessInstanceQo qo = new FlowProcessInstanceQo();
        qo.setProcInstId(procInstId);
        qo.setProcDefName(name);
        qo.setIsParentIdEmpty(true);
        qo.setOrderBy(QueryConstants.START_TIME + QueryConstants.DESC);
        if (StringUtils.isNotBlank(businessKey)) {
            qo.setBusinessKey(businessKey);
        } else {
            qo.setIsBusinessKeyNotEmpty(true);
        }
        return flowRuntimeService.getProcessInstances(qo);
    }

    /**
     * 构建流程实例信息
     *
     * @param p                     流程实例DTO
     * @param taskByProcInstIdMap   任务列表按流程实例ID分组
     * @param assigneeByTaskIdMap   任务处��人映射
     * @return FlowInstanceInfoVo
     */
    private FlowInstanceInfoVo bulidFlowInstanceInfoVo(FlowProcessInstanceDto p, Map<String, List<Task>> taskByProcInstIdMap, Map<String, List<SysUser>> assigneeByTaskIdMap) {
        FlowInstanceInfoVo info = new FlowInstanceInfoVo();
        info.setProcessInstanceId(p.getProcInstId());
        info.setProcDefId(p.getProcDefId());
        info.setDeployId(p.getDeploymentId());
        info.setBusinessKey(p.getBusinessKey());
        info.setName(p.getProcDefName());
        info.setStartTime(p.getStartTime());
        info.setStartUserId(p.getStartUserId());
        info.setSuspended(SuspensionState.SUSPENDED.getStateCode() == p.getSuspensionState());

        List<Task> taskByProcInstIds = taskByProcInstIdMap.get(p.getProcInstId());
        if (CollectionUtils.isEmpty(taskByProcInstIds)) {
            return info;
        }

        // 设置任务信息
        setTaskInfo(info, taskByProcInstIds, assigneeByTaskIdMap);
        return info;
    }

    /**
     * 设置任务信息
     *
     * @param info                流程实例信息
     * @param taskByProcInstIds   当前流程实例的任务列表
     * @param assigneeByTaskIdMap 任务处理人映射
     */
    private void setTaskInfo(FlowInstanceInfoVo info, List<Task> taskByProcInstIds, Map<String, List<SysUser>> assigneeByTaskIdMap) {
        StringJoiner taskIds = new StringJoiner(Constants.COMMA);
        StringJoiner taskDefKeys = new StringJoiner(Constants.COMMA);
        StringJoiner taskNames = new StringJoiner(Constants.COMMA);
        StringJoiner assignee = new StringJoiner(Constants.COMMA);
        Set<String> taskIdSet = new HashSet<>();
        Set<String> taskDefKeySet = new HashSet<>();
        Set<String> taskNameSet = new HashSet<>();
        for (Task t : taskByProcInstIds) {
            if (taskIdSet.add(t.getId())) {
                taskIds.add(t.getId());
            }
            if (taskDefKeySet.add(t.getTaskDefinitionKey())) {
                taskDefKeys.add(t.getTaskDefinitionKey());
            }
            if (taskNameSet.add(t.getName())) {
                taskNames.add(t.getName());
            }
            List<SysUser> assignees = assigneeByTaskIdMap.getOrDefault(t.getId(), Collections.emptyList());
            assignees.forEach(sysUser -> assignee.add(sysUser.getNickName()));
        }
        info.setCurrentTaskIds(taskIds.length() > 0 ? taskIds.toString() : StringUtils.EMPTY);
        info.setCurrentTaskDefKeys(taskDefKeys.length() > 0 ? taskDefKeys.toString() : StringUtils.EMPTY);
        info.setCurrentTask(taskNames.length() > 0 ? taskNames.toString() : StringUtils.EMPTY);
        info.setAssignee(assignee.length() > 0 ? assignee.toString() : StringUtils.EMPTY);
    }

    /**
     * 获取流程历史实例
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @return List<FlowHistoricProcessInstanceDto>
     */
    private List<FlowHistoricProcessInstanceDto> getHistoricProcessInstances(String procInstId, String businessKey, String name) {
        FlowHistoricProcessInstanceQo qo = new FlowHistoricProcessInstanceQo();
        qo.setProcInstId(procInstId);
        qo.setBusinessKey(businessKey);
        qo.setProcDefName(name);
        qo.setOrderBy(QueryConstants.START_TIME + QueryConstants.DESC);
        return flowHistoryService.getHistoricProcessInstances(qo);
    }

    /**
     * 构建流程历史实例信息
     *
     * @param p                     流程历史实例DTO
     * @param taskByProcInstIdMap   任务列表按流程实例ID分组
     * @param assigneeByTaskIdMap   任务处理人映射
     * @return FlowInstanceInfoVo
     */
    private FlowInstanceInfoVo buildFlowHistoricInstanceInfoVo(FlowHistoricProcessInstanceDto p, Map<String, List<Task>> taskByProcInstIdMap, Map<String, List<SysUser>> assigneeByTaskIdMap) {
        FlowInstanceInfoVo info = new FlowInstanceInfoVo();
        info.setProcessInstanceId(p.getId());
        info.setBusinessKey(p.getBusinessKey());
        info.setName(p.getProcDefName());
        info.setStartTime(p.getStartTime());
        info.setEndTime(p.getEndTime());
        info.setStartUserId(p.getStartUserId());
        info.setEnded(p.getEndTime() != null);
        List<Task> taskByProcInstIds = taskByProcInstIdMap.get(p.getProcInstId());
        if (CollectionUtils.isEmpty(taskByProcInstIds)) {
            return info;
        }
        StringJoiner taskNames = new StringJoiner(Constants.COMMA);
        StringJoiner assignee = new StringJoiner(Constants.COMMA);
        Set<String> taskNameSet = new HashSet<>();
        Set<String> assigneeSet = new HashSet<>();
        for (Task t : taskByProcInstIds) {
            if (taskNameSet.add(t.getName())) {
                taskNames.add(t.getName());
            }
            List<SysUser> assignees = assigneeByTaskIdMap.getOrDefault(t.getId(), Collections.emptyList());
            assignees.stream().map(SysUser::getNickName).filter(assigneeSet::add).forEach(assignee::add);
        }
        info.setCurrentTask(taskNames.length() > 0 ? taskNames.toString() : StringUtils.EMPTY);
        info.setAssignee(assignee.length() > 0 ? assignee.toString() : StringUtils.EMPTY);
        return info;
    }

    /**
     * 获取流程环节历史活动实例
     *
     * @param processInstanceId 流程实例ID
     * @return List<FlowHistoricActivityInstanceDto>
     */
    private List<FlowHistoricActivityInstanceDto> getHistoricActivityInstances(String processInstanceId, Boolean isFinished) {
        FlowHistoricActivityInstanceQo qo = new FlowHistoricActivityInstanceQo();
        qo.setProcInstId(processInstanceId);
        qo.setIsFinished(isFinished);
        qo.setActivityType(ActivityTypeEnum.USER_TASK.getCode());
        qo.setOrderBy(QueryConstants.START_TIME + QueryConstants.ASC);
        return flowHistoryService.getHistoricActivityInstances(qo);
    }

    /**
     * 构建任务信息
     *
     * @param h                     流程环节历史活动实例DTO
     * @param assigneeByTaskIdMap   任务处理人映射
     * @param commentMap            评论内容映射
     * @return TaskInfoVo
     */
    private TaskInfoVo buildTaskVoInfo(FlowHistoricActivityInstanceDto h, Map<String, List<SysUser>> assigneeByTaskIdMap, Map<String, List<FlowCommentDto>> commentMap) {
        TaskInfoVo info = new TaskInfoVo();
        info.setProcessInstanceId(h.getProcInstId());
        info.setStartTime(LocalDateTimeUtil.formatTime(LocalDateTimeUtil.toLocalDateTime(h.getStartTime()), LocalDateTimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        if (h.getEndTime() != null) {
            info.setEndTime(LocalDateTimeUtil.formatTime(LocalDateTimeUtil.toLocalDateTime(h.getEndTime()), LocalDateTimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        }
        info.setTaskName(h.getActivityName());
        // 优化处理人拼接与去重
        List<SysUser> assignees = assigneeByTaskIdMap.getOrDefault(h.getTaskId(), Collections.emptyList());
        String assigneeStr = assignees.stream()
                .map(SysUser::getNickName)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.joining(Constants.COMMA));
        info.setAssignee(StringUtils.isNotBlank(assigneeStr) ? assigneeStr : StringUtils.EMPTY);
        // 优化评论获取
        List<FlowCommentDto> flowCommentDtos = commentMap.getOrDefault(h.getTaskId(), Collections.emptyList())
                .stream()
                .filter(c -> ADD_COMMENT.equals(c.getAction()))
                .collect(Collectors.toList());
        info.setComment(CollectionUtils.isNotEmpty(flowCommentDtos) ? flowCommentDtos.get(0).getFullMessage() : StringUtils.EMPTY);
        return info;
    }

    /**
     * 获取任务节点属性,包含自定义属性等
     *
     * @param nextUserTask 节点任务
     * @param variables    流程变量
     */
    private List<FlowNextDto> getFlowAttribute(List<UserTask> nextUserTask, Map<String, Object> variables) {
        if (CollectionUtils.isEmpty(nextUserTask)) {
            return Collections.emptyList();
        }
        List<FlowNextDto> flowNextList = new ArrayList<>(nextUserTask.size());
        for (UserTask userTask : nextUserTask) {
            FlowNextDto flowNextDto = new FlowNextDto();
            flowNextDto.setNodeId(userTask.getId());
            flowNextDto.setNodeName(userTask.getName());
            // 会签节点(多实例)
            MultiInstanceLoopCharacteristics multiInstance = userTask.getLoopCharacteristics();
            if (multiInstance != null) {
                flowNextDto.setVars(multiInstance.getInputDataItem());
                flowNextDto.setType(ProcessConstants.PROCESS_MULTI_INSTANCE);
                flowNextDto.setDataType(ProcessConstants.DYNAMIC);
                flowNextList.add(flowNextDto);
                continue;
            }
            // 非会签节点，读取自定义节点属性 判断是否需要动态指定任务接收人员、组
            String dataType = userTask.getAttributeValue(ProcessConstants.NAMESPACE, ProcessConstants.PROCESS_CUSTOM_DATA_TYPE);
            String userType = userTask.getAttributeValue(ProcessConstants.NAMESPACE, ProcessConstants.PROCESS_CUSTOM_USER_TYPE);
            flowNextDto.setVars(flowCommonService.getExpression(userTask));
            flowNextDto.setType(userType);
            flowNextDto.setDataType(dataType);
            // 只在非动态节点时获取处理人，提升性能
            if (!ProcessConstants.DYNAMIC.equals(dataType)) {
                List<SysUser> assignees = flowCommonService.getNextAssignees(userType, dataType, userTask, variables);
                flowNextDto.setAssignees(assignees);
            } else {
                flowNextDto.setAssignees(Collections.emptyList());
            }
            flowNextList.add(flowNextDto);
        }
        return flowNextList;
    }

    /**
     * 获取流程环节任务DTO
     *
     * @param histIns                        历史任务实例
     * @param flowHistoricActivityInstanceDto 流程历史活动实例DTO
     * @return FlowTaskDto
     */
    private FlowTaskDto getFlowTaskDto(HistoricTaskInstance histIns, FlowHistoricActivityInstanceDto flowHistoricActivityInstanceDto) {
        FlowTaskDto flowTask = new FlowTaskDto();
        flowTask.setTaskId(histIns.getId());
        flowTask.setTaskName(histIns.getName());
        flowTask.setCreateTime(histIns.getCreateTime());
        flowTask.setFinishTime(histIns.getEndTime());
        flowTask.setStatus(histIns.getEndTime() == null ? Constants.NO_VALUE : Constants.YES_VALUE);
        flowTask.setActId(flowHistoricActivityInstanceDto.getActivityId());
        Long duration = histIns.getDurationInMillis();
        flowTask.setDuration((duration == null || duration == 0) ? null : FlowableUtil.getDate(duration));
        return flowTask;
    }

    /**
     * 获取列表数据
     *
     * @param infos 列表数据
     * @param isPage 是否分页
     * @return
     */
    private TableDataInfo getTableDataInfo(List<?> infos, List<?> totals, Boolean isPage) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(infos);
        if (!isPage) {
            rspData.setTotal(infos.size());
            return rspData;
        }
        rspData.setTotal(new PageInfo(totals).getTotal());
        return rspData;
    }

    /**
     * 对于同一个环节（activityId），只保留最新的历史任务实例（即createTime最大）
     *
     * @param historicTaskInstances
     * @return
     */
    private List<HistoricTaskInstance> getDistinctHistoricTaskInstances(List<HistoricTaskInstance> historicTaskInstances) {
        // 对于多实例环节，按 activityId + assignee 保留每个处理人最新的任务
        Map<String, HistoricTaskInstance> latestTaskMap = new HashMap<>();
        for (HistoricTaskInstance task : historicTaskInstances) {
            String key = task.getTaskDefinitionKey() + Constants.HASH + (task.getAssignee() == null ? StringUtils.EMPTY : task.getAssignee());
            HistoricTaskInstance exist = latestTaskMap.get(key);
            if (exist == null || task.getCreateTime().after(exist.getCreateTime())) {
                latestTaskMap.put(key, task);
            }
        }
        return new ArrayList<>(latestTaskMap.values());
    }
}
