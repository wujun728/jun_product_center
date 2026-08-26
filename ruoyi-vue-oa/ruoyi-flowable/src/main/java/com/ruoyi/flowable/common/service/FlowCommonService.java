package com.ruoyi.flowable.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.CommonConstants;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.domain.dto.FlowIdentityLinkDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.qo.FlowIdentityLinkQo;
import com.ruoyi.flowable.domain.qo.FlowVariableInstanceQo;
import com.ruoyi.flowable.enums.SelectRangeEnum;
import com.ruoyi.flowable.factory.FlowServiceFactory;
import com.ruoyi.flowable.mapper.FlowableSourceTargetMapper;
import com.ruoyi.flowable.service.IFlowHistoryService;
import com.ruoyi.flowable.service.IFlowVariableInstanceService;
import com.ruoyi.flowable.utils.AssigneeUtil;
import com.ruoyi.flowable.utils.FlowableUtil;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.identitylink.api.IdentityLinkInfo;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p> 流程公共服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FlowCommonService extends FlowServiceFactory {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IFlowHistoryService flowHistoryService;
    @Autowired
    private IFlowVariableInstanceService flowVariableService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 获取任务处理人
     *
     * @param taskList 任务列表
     * @return Map<String, List < SysUser>> 按taskId分组
     */
    public Map<String, List<SysUser>> getTaskAssigneeMap(List<Task> taskList) {
        if (CollectionUtils.isEmpty(taskList)) {
            return Collections.emptyMap();
        }
        Map<String, List<FlowIdentityLinkDto>> identityLinkMap = getIdentityLinkMap(taskList);

        Map<String, Set<String>> assigneeIdMap = new HashMap<>();
        Set<String> allAssigneeSet = new HashSet<>();
        for (Task task : taskList) {
            Set<String> assigneeList = assigneeIdMap.computeIfAbsent(task.getId(), k -> new HashSet<>());
            String assignee = task.getAssignee();
            if (StringUtils.isNotBlank(assignee)) {
                addAssigneesFromString(assignee, assigneeList, allAssigneeSet);
                continue;
            }
            List<FlowIdentityLinkDto> linksForTask = identityLinkMap.get(task.getId());
            if (CollectionUtils.isNotEmpty(linksForTask)) {
                addAssigneesFromLinks(linksForTask, assigneeList, allAssigneeSet);
            }
        }
        return batchGetAssignee(new ArrayList<>(allAssigneeSet), assigneeIdMap);
    }

    /**
     * 获取历史流程审批处理人
     *
     * @param historicActivities 历史环节列表
     * @return Map<String, List < SysUser>> 按taskId分组
     */
    public Map<String, List<SysUser>> getHistoricAssigneeMap(List<HistoricTaskInstance> historicActivities) {
        if (CollectionUtils.isEmpty(historicActivities)) {
            return Collections.emptyMap();
        }

        Map<String, Set<String>> assigneeIdMap = new HashMap<>();
        Set<String> allAssigneeSet = new HashSet<>();
        for (HistoricTaskInstance histIns : historicActivities) {
            Set<String> assigneeList = assigneeIdMap.computeIfAbsent(histIns.getId(), k -> new HashSet<>());
            if (StringUtils.isNotBlank(histIns.getAssignee())) {
                addHistoricAssigneesFromString(histIns.getAssignee(), assigneeList, allAssigneeSet);
                continue;
            }
            List<HistoricIdentityLink> linksForTask = historyService.getHistoricIdentityLinksForTask(histIns.getId());
            if (CollectionUtils.isNotEmpty(linksForTask)) {
                addHistoricAssigneesFromLinks(linksForTask, assigneeList, allAssigneeSet);
            }
        }
        return batchGetAssignee(new ArrayList<>(allAssigneeSet), assigneeIdMap);
    }

    /**
     * 批量获取处理人
     *
     * @param allAssigneeList
     * @param assigneeIdMap
     * @return
     */
    public Map<String, List<SysUser>> batchGetAssignee(List<String> allAssigneeList, Map<String, Set<String>> assigneeIdMap) {
        if (CollectionUtils.isEmpty(allAssigneeList) || assigneeIdMap.isEmpty()) {
            return Collections.emptyMap();
        }
        //批量查询用户信息并转为Map
        List<SysUser> sysUsers = sysUserService.selectDetailByUserIds(allAssigneeList);
        Map<String, SysUser> userMap = sysUsers.stream()
                .collect(Collectors.toMap(SysUser::getUserId, u -> u, (a, b) -> a));
        // 构建结果
        Map<String, List<SysUser>> assigneeMap = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : assigneeIdMap.entrySet()) {
            List<SysUser> userList = entry.getValue().stream()
                    .map(userMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            assigneeMap.put(entry.getKey(), userList);
        }
        return assigneeMap;
    }

    /**
     * 处理人表达式
     *
     * @param userTask UserTask
     * @return String
     */
    public String getExpression(UserTask userTask) {
        String expression = extractExpression(userTask);
        if (isElExpression(expression)) {
            return stripElBrackets(expression);
        }
        return userTask.getId() + ProcessConstants.FIXED_TASK_SUFFIX;
    }

    /**
     * 获取下一个环节处理人
     *
     * @param userType  处理人类型
     * @param dataType  数据类型
     * @param userTask  用户任务
     * @param variables 流程变量
     * @return List<SysUser>
     */
    public List<SysUser> getNextAssignees(String userType, String dataType, UserTask userTask, Map<String, Object> variables) {
        if (ProcessConstants.DYNAMIC.equals(dataType)) {
            return Collections.emptyList();
        }
        return resolveNextAssigneeUserIds(userType, dataType, userTask, variables);
    }

    /**
     * 获取流程当前任务
     *
     * @param procInsId 流程实例ID
     * @return 当前任务列表
     */
    public List<FlowTaskDto> getCurrentTaskList(String procInsId) {
        if (StringUtils.isBlank(procInsId)) {
            return Collections.emptyList();
        }
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procInsId).list();
        if (CollectionUtils.isEmpty(taskList)) {
            return Collections.emptyList();
        }
        Map<String, List<SysUser>> taskAssigneeMap = getTaskAssigneeMap(taskList);
        ProcessDefinition pd = getProcessDefinitionByTaskList(taskList);
        return taskList.stream()
                .map(task -> buildFlowTaskDto(task, pd, taskAssigneeMap))
                .collect(Collectors.toList());
    }

    /**
     * 获取流程变量
     *
     * @param procInstId   流程实例ID
     * @param variableName 变量名称
     * @return Object
     */
    public Object getProcessVariables(String procInstId, String variableName) {
        return runtimeService.getVariable(procInstId, variableName);
    }

    /**
     * 处理历史多人审批记录
     *
     * @param taskId          任务ID
     * @param flowTask        流程任务
     * @param hisFlowList     历史任务列表
     * @param flowAssigneeMap 流程处理人map
     */
    public void handleMultiAssignee(String taskId, FlowTaskDto flowTask, List<FlowTaskDto> hisFlowList, Map<String, List<SysUser>> flowAssigneeMap) {
        List<SysUser> sysUsers = flowAssigneeMap.get(taskId);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return;
        }
        sysUsers.stream()
                .map(sysUser -> {
                    FlowTaskDto dto = FlowableSourceTargetMapper.INSTANCE.copyFlowTaskDto(flowTask);
                    dto.setAssigneeId(sysUser.getUserId());
                    dto.setAssigneeName(sysUser.getNickName());
                    dto.setCandidate(sysUser.getNickName());
                    dto.setDeptName(sysUser.getDept() != null ? sysUser.getDept().getDeptName() : StringUtils.EMPTY);
                    return dto;
                })
                .forEach(hisFlowList::add);
    }

    /**
     * 获取任务处理人
     *
     * @param taskList 任务列表
     * @return Map<String, List < FlowIdentityLinkDto>> 按taskId分组
     */
    private Map<String, List<FlowIdentityLinkDto>> getIdentityLinkMap(List<Task> taskList) {
        List<String> taskIds = taskList.stream().map(Task::getId).collect(Collectors.toList());
        FlowIdentityLinkQo qo = new FlowIdentityLinkQo();
        qo.setTaskIds(taskIds);
        List<FlowIdentityLinkDto> identityLinks = flowHistoryService.getIdentityLinks(qo);
        return identityLinks.stream()
                .collect(Collectors.groupingBy(FlowIdentityLinkDto::getTaskId));
    }

    /**
     * 从字符串中提取用户ID并添加到集合
     *
     * @param assignee       处理人字符串
     * @param assigneeList   当前任务处理人集合
     * @param allAssigneeSet 全部处理人集合
     */
    private void addAssigneesFromString(String assignee, Set<String> assigneeList, Set<String> allAssigneeSet) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> assignees = mapper.readValue(assignee, new TypeReference<List<String>>() {});
            for (String id : assignees) {
                assigneeList.add(id);
                allAssigneeSet.add(id);
            }
        } catch (JsonProcessingException e) {
            for (String id : assignee.split(Constants.COMMA)) {
                if (StringUtils.isNotBlank(id)) {
                    assigneeList.add(id.trim());
                    allAssigneeSet.add(id.trim());
                }
            }
        }
    }

    /**
     * 从IdentityLink中提取用户ID并添加到集合
     *
     * @param linksForTask   当前任务的IdentityLink列表
     * @param assigneeList   当前任务处理人集合
     * @param allAssigneeSet 全部处理人集合
     */
    private void addAssigneesFromLinks(List<FlowIdentityLinkDto> linksForTask, Set<String> assigneeList, Set<String> allAssigneeSet) {
        Set<String> ids = linksForTask.stream()
                .filter(l -> Objects.nonNull(l.getUserId()))
                .map(FlowIdentityLinkDto::getUserId)
                .collect(Collectors.toSet());
        assigneeList.addAll(ids);
        allAssigneeSet.addAll(ids);
    }

    /**
     * 从字符串中提取用户ID并添加到集合
     *
     * @param assignee       历史处理人字符串
     * @param assigneeList   当前任务处理人集合
     * @param allAssigneeSet 全部处理人集合
     */
    private void addHistoricAssigneesFromString(String assignee, Set<String> assigneeList, Set<String> allAssigneeSet) {
        addAssigneesFromString(assignee, assigneeList, allAssigneeSet);
    }

    /**
     * 从历史IdentityLink中提取用户ID并添加到集合
     *
     * @param linksForTask   当前任务的历史IdentityLink列表
     * @param assigneeList   当前任务处理人集合
     * @param allAssigneeSet 全部处理人集合
     */
    private void addHistoricAssigneesFromLinks(List<HistoricIdentityLink> linksForTask, Set<String> assigneeList, Set<String> allAssigneeSet) {
        Set<String> ids = linksForTask.stream()
                .filter(l -> l.getUserId() != null)
                .map(IdentityLinkInfo::getUserId)
                .collect(Collectors.toSet());
        assigneeList.addAll(ids);
        allAssigneeSet.addAll(ids);
    }

    /**
     * 提取用户任务中的处理人表达式
     *
     * @param userTask 用户任务
     * @return 处理人表达式
     */
    private String extractExpression(UserTask userTask) {
        if (StringUtils.isNotBlank(userTask.getAssignee())) {
            return userTask.getAssignee();
        }
        if (CollectionUtils.isNotEmpty(userTask.getCandidateUsers()) && userTask.getCandidateUsers().size() == 1) {
            return userTask.getCandidateUsers().get(0);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 判断表达式是否为EL表达式
     *
     * @param expression 表达式
     * @return boolean 是否为EL表达式
     */
    private boolean isElExpression(String expression) {
        return StringUtils.isNotBlank(expression) && expression.contains(CommonConstants.EL_PREFIX) && expression.contains(CommonConstants.EL_SUFFIX);
    }

    /**
     * 去除EL表达式的${}括号
     *
     * @param expression EL表达式
     * @return String 去除括号后的表达式
     */
    private String stripElBrackets(String expression) {
        return expression.replace(CommonConstants.EL_PREFIX, StringUtils.EMPTY).replace(CommonConstants.EL_SUFFIX, StringUtils.EMPTY);
    }

    /**
     * 解析下一个环节处理人ID集合
     *
     * @param userType  处理人类型
     * @param dataType  数据类型
     * @param userTask  用户任务
     * @param variables 流程变量
     * @return List<String> 处理人ID集合
     */
    private List<SysUser> resolveNextAssigneeUserIds(String userType, String dataType, UserTask userTask, Map<String, Object> variables) {
        // 固定处理人，直接返回
        if (ProcessConstants.ASSIGNEE.equals(userType)) {
            return getAssigneeUsers(userTask, variables);
        }
        // 动态选人和角色选人，按选人范围进行过滤
        List<String> userIds = new ArrayList<>();
        if (ProcessConstants.ROLES.equals(dataType)) {
            userIds = getRoleUserIds(userTask);
        } else if (ProcessConstants.CANDIDATE_USERS.equals(userType) && CollectionUtils.isNotEmpty(userTask.getCandidateUsers())) {
            userIds.addAll(userTask.getCandidateUsers().stream().map(String::valueOf).collect(Collectors.toList()));
        }
        return filterAssigneeUserByDept(userIds, variables);
    }

    /**
     * 根据部门过滤用户
     *
     * @param userIds 用户ID
     * @param variables 流程变量
     * @return
     */
    private List<SysUser> filterAssigneeUserByDept(List<String> userIds, Map<String, Object> variables) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        List<SysUser> assigneeUsers = sysUserService.selectDetailByUserIds(userIds);
        Object selectRangeObj = variables.get(ProcessConstants.SELECT_RANGE);
        if (Objects.nonNull(selectRangeObj)) {
            SelectRangeEnum selectRangeEnum = SelectRangeEnum.getByCode(selectRangeObj.toString());
            switch (selectRangeEnum) {
                case CORP: // 当前用户单位
                    break;
                case DEPT: // 当前用户部门
                    LoginUser loginUser = SecurityUtils.getLoginUser();
                    assigneeUsers = assigneeUsers.stream()
                            .filter(user -> loginUser.getDeptId().equals(user.getDeptId()))
                            .collect(Collectors.toList());
                    break;
                case DEPT_LEADER: // 当前用户部门负责人
                    SysDept dept = sysDeptService.selectDeptById(SecurityUtils.getLoginUser().getDeptId());
                    if (dept != null && StringUtils.isNotBlank(dept.getLeader())) {
                        List<String> leaders = Arrays.asList(dept.getLeader().split(Constants.COMMA));
                        assigneeUsers = assigneeUsers.stream()
                                .filter(user -> leaders.contains(user.getUserId()))
                                .collect(Collectors.toList());
                    }
                    break;
            }
        }
        return assigneeUsers;
    }

    /**
     * 获取固定处理人集合
     *
     * @param userTask  用户任务
     * @param variables 流程变量
     * @return List<String> 处理人ID集合
     */
    private List<SysUser> getAssigneeUsers(UserTask userTask, Map<String, Object> variables) {
        String assignee = userTask.getAssignee();
        if (StringUtils.isBlank(assignee)) {
            throw new BaseException("流程处理人为空，请联系管理员！");
        }
        assignee = parseAssigneeExpression(assignee, userTask, variables);
        List<String> userIds = parseAssigneeToUserIds(assignee);
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return sysUserService.selectDetailByUserIds(userIds);
    }

    /**
     * 获取角色用户ID集合
     *
     * @param userTask 用户任务
     * @return List<String> 角色用户ID集合
     */
    private List<String> getRoleUserIds(UserTask userTask) {
        String expId = userTask.getAttributeValue(ProcessConstants.NAMESPACE, ProcessConstants.PROCESS_CUSTOM_EXP_ID);
        if (StringUtils.isBlank(expId)) {
            throw new BaseException("流程配置错误，请联系管理员！");
        }
        List<String> roleIds = Arrays.stream(expId.split(Constants.COMMA))
                .map(String::valueOf)
                .collect(Collectors.toList());

        List<SysUserRole> sysUserRoles = sysUserService.selectRoleUserInfos(roleIds);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return Collections.emptyList();
        }
        return sysUserRoles.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
    }

    /**
     * 解析处理人表达式
     *
     * @param assignee  处理人表达式
     * @param userTask  用户任务
     * @param variables 流程变量
     * @return 处理人ID字符串
     */
    private String parseAssigneeExpression(String assignee, UserTask userTask, Map<String, Object> variables) {
        if (assignee.startsWith(CommonConstants.EL_PREFIX) && assignee.endsWith(CommonConstants.EL_SUFFIX)) {
            String expStr = assignee.substring(CommonConstants.EL_PREFIX.length(), assignee.length() - CommonConstants.EL_SUFFIX.length()).trim();
            if (ProcessConstants.PARALLEL_GATEWAY_INITIATOR.equals(expStr)) {
                assignee = getParallelGatewayInitiator(userTask, variables);
                if (StringUtils.isBlank(assignee)) {
                    throw new BaseException("并行网关入口环节处理人为空，请联系管理员！");
                }
            } else {
                Object execute = FlowableUtil.executeExpression(assignee, variables);
                if (execute == null || StringUtils.isBlank(String.valueOf(execute))) {
                    throw new BaseException("流程表达式(" + expStr + ")解析失败错误，请联系管理员！");
                }
                assignee = String.valueOf(execute);
            }
        }
        return assignee;
    }

    /**
     * 解析处理人字符串为用户ID列表
     *
     * @param assignee 处理人字符串
     * @return List<String> 用户ID列表
     */
    private List<String> parseAssigneeToUserIds(String assignee) {
        List<String> userIds = new ArrayList<>();
        if (assignee.startsWith(CommonConstants.LEFT_BRACKET) && assignee.endsWith(CommonConstants.RIGHT_BRACKET)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<String> assignees = mapper.readValue(assignee, new TypeReference<List<String>>() {
                });
                userIds.addAll(assignees);
            } catch (JsonProcessingException e) {
                log.error("处理人ID解析异常:", e);
                throw new BaseException("处理人ID解析异常:", e.getMessage());
            }
        } else {
            for (String id : assignee.split(Constants.COMMA)) {
                if (StringUtils.isNotBlank(id)) {
                    userIds.add(id.trim());
                }
            }
        }
        return userIds;
    }

    /**
     * 获取并行网关入口环节处理人
     *
     * @param source    并行网关上一个环节
     * @param variables 流程变量
     * @return String   处理人ID
     */
    private String getParallelGatewayInitiator(UserTask source, Map<String, Object> variables) {
        if (source == null || variables == null || !variables.containsKey(ProcessConstants.PROCESS_INSTANCE_ID)) {
            log.warn("getParallelGatewayInitiator: 参数不合法，source或variables为空");
            return StringUtils.EMPTY;
        }
        // 获取并行网关上一个环节
        UserTask prevUserTask = FlowableUtil.iteratorGetParallelGatewayTarget(source, new HashSet<>());
        if (prevUserTask == null) {
            log.warn("getParallelGatewayInitiator: 未找到并行网关上一个UserTask");
            return StringUtils.EMPTY;
        }
        String procInstId = String.valueOf(variables.get(ProcessConstants.PROCESS_INSTANCE_ID));
        if (StringUtils.isBlank(procInstId)) {
            log.warn("getParallelGatewayInitiator: 流程实例ID为空，无法查询历史环节");
            return StringUtils.EMPTY;
        }
        List<HistoricActivityInstance> activityList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(procInstId)
                .activityId(prevUserTask.getId())
                .orderByHistoricActivityInstanceStartTime().desc().list();
        if (CollectionUtils.isEmpty(activityList)) {
            log.warn("getParallelGatewayInitiator: 未找到历史环节，procInstId={}, activityId={}", procInstId, prevUserTask.getId());
            return StringUtils.EMPTY;
        }
        // 优先返回第一个有assignee的
        return activityList.stream()
                .map(HistoricActivityInstance::getAssignee)
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    /**
     * 获取流程定义
     *
     * @param taskList 任务列表
     * @return ProcessDefinition 流程定义
     */
    private ProcessDefinition getProcessDefinitionByTaskList(List<Task> taskList) {
        return repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(taskList.get(0).getProcessDefinitionId())
                .singleResult();
    }

    /**
     * 构建FlowTaskDto对象
     *
     * @param task            任务对象
     * @param pd              流程定义对象
     * @param taskAssigneeMap 任务处理人映射
     * @return FlowTaskDto      流程任务DTO
     */
    private FlowTaskDto buildFlowTaskDto(Task task, ProcessDefinition pd, Map<String, List<SysUser>> taskAssigneeMap) {
        FlowTaskDto flowTask = new FlowTaskDto();
        flowTask.setTaskId(task.getId());
        flowTask.setTaskName(task.getName());
        flowTask.setTaskDefKey(task.getTaskDefinitionKey());
        flowTask.setProcInsId(task.getProcessInstanceId());
        flowTask.setExecutionId(task.getExecutionId());
        flowTask.setCreateTime(task.getCreateTime());
        flowTask.setDeployId(pd.getDeploymentId());
        flowTask.setProcDefId(task.getProcessDefinitionId());
        flowTask.setProcDefName(pd.getName());
        flowTask.setProcDefVersion(pd.getVersion());
        List<SysUser> assignees = taskAssigneeMap.get(task.getId());
        AssigneeUtil.fillAssigneeInfo(
                assignees,
                flowTask::setAssignee,
                flowTask::setAssigneeName,
                flowTask::setAssigneeDeptName
        );
        return flowTask;
    }

    /**
     * 根据流程实例ID获取全局流程变量
     *
     * @param procInsId 流程实例Id
     * @return
     */
    public Map<String, Object> getProcessVariablesByProcInstId(String procInsId) {
        if (StringUtils.isBlank(procInsId)) {
            return Collections.emptyMap();
        }
        FlowVariableInstanceQo qo = new FlowVariableInstanceQo();
        qo.setProcessInstanceId(procInsId);
        return flowVariableService.getVariableInstanceMap(qo);
    }

    /**
     * 根据任务ID获取当前任务流程变量
     *
     * @param taskId 流程实例Id
     * @return
     */
    public Map<String, Object> getProcessVariablesByTaskId(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            return Collections.emptyMap();
        }
        FlowVariableInstanceQo qo = new FlowVariableInstanceQo();
        qo.setTaskId(taskId);
        return flowVariableService.getVariableInstanceMap(qo);
    }
}
