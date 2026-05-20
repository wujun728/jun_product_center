package com.ruoyi.flowable.service.impl;


import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.common.constant.QueryConstants;
import com.ruoyi.flowable.common.enums.ActivityTypeEnum;
import com.ruoyi.flowable.common.enums.FlowCommentEnum;
import com.ruoyi.flowable.common.service.FlowCommonService;
import com.ruoyi.flowable.domain.dto.*;
import com.ruoyi.flowable.domain.qo.FlowCommentQo;
import com.ruoyi.flowable.domain.qo.FlowHistoricActivityInstanceQo;
import com.ruoyi.flowable.domain.vo.FlowCommentVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.factory.FlowServiceFactory;
import com.ruoyi.flowable.flow.CustomProcessDiagramGenerator;
import com.ruoyi.flowable.service.IFlowCommentService;
import com.ruoyi.flowable.service.IFlowHistoryService;
import com.ruoyi.flowable.service.IFlowTaskService;
import com.ruoyi.flowable.utils.AssigneeUtil;
import com.ruoyi.flowable.utils.FindNextNodeUtil;
import com.ruoyi.flowable.utils.FlowableUtil;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cmd.AddMultiInstanceExecutionCmd;
import org.flowable.engine.impl.cmd.DeleteMultiInstanceExecutionCmd;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 流程任务服务实现
 *
 * @author wocurr.com
 **/
@Service
@Slf4j
public class FlowTaskServiceImpl extends FlowServiceFactory implements IFlowTaskService {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IFlowCommentService flowCommentService;
    @Autowired
    private IFlowHistoryService flowHistoryService;
    @Autowired
    private FlowCommonService flowCommonService;

    /**
     * 完成任务
     *
     * @param taskVo 请求实体参数
     */
    @Override
    public void complete(FlowTaskVo taskVo) {
        Task task = taskService.createTaskQuery().taskId(taskVo.getTaskId()).singleResult();
        if (Objects.isNull(task)) {
            throw new BaseException("任务不存在");
        }
        if (DelegationState.PENDING.equals(task.getDelegationState())) {
            // 委派
            taskService.addComment(taskVo.getTaskId(), taskVo.getProcInsId(), FlowCommentEnum.DELEGATE.getType(), taskVo.getComment());
            taskService.resolveTask(taskVo.getTaskId(), taskVo.getVariables());
        } else {
            taskService.addComment(taskVo.getTaskId(), taskVo.getProcInsId(), FlowCommentEnum.NORMAL.getType(), taskVo.getComment());
            taskService.setAssignee(taskVo.getTaskId(), taskVo.getUserId());
            taskService.complete(taskVo.getTaskId(), taskVo.getVariables());
        }
    }

    /**
     * 驳回任务
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void taskReject(FlowTaskVo flowTaskVo) {
        try {
            Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            // 获取当前任务节点元素
            FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

            // 获取当前节点的所有父级用户任务节点
            List<UserTask> parentUserTaskList = FlowableUtil.iteratorFindParentUserTasks(source, null, null);
            List<String> parentUserTaskKeyList = parentUserTaskList.stream()
                    .map(UserTask::getId).collect(Collectors.toList());

            // 清洗历史任务实例数据
            List<String> lastHistoricTaskInstanceList = historicTaskInstanceClean(task, bpmnModel);
            // 获取目标节点ID列表
            List<String> targetIds = getTargetIds(lastHistoricTaskInstanceList, parentUserTaskKeyList);
            // 获取当前任务ID列表
            List<String> currentTaskIds = getCurrentUserTaskIds(task, parentUserTaskList.get(0), FlowCommentEnum.REJECT, flowTaskVo.getComment());
            // 获取已处理的任务
            Map<String, List<ActivityInstance>> activityInstanceListMap = getActivityInstanceMapByProcInsId(flowTaskVo.getProcInsId());

            if (CollectionUtils.isEmpty(currentTaskIds)) {
                log.error("未找到当前节点，可能是流程已发生变化");
                throw new BaseException("未找到当前节点，可能是流程已发生变化");
            }

            if (CollectionUtils.isEmpty(targetIds)) {
                log.error("未找到目标节点，可能是流程已发生变化");
                throw new BaseException("未找到目标节点，可能是流程已发生变化");
            }

            // 如果父级任务只有一个，因此当前任务可能为网关中的任务
            if (targetIds.size() == 1) {
                // 1 对 1 或 多 对 1 情况，currentIds 当前要跳转的节点列表(1或多)，targetIds.get(0) 跳转到的节点(1)
                runtimeService.createChangeActivityStateBuilder()
                        .processInstanceId(task.getProcessInstanceId())
                        .moveActivityIdsToSingleActivityId(currentTaskIds, targetIds.get(0))
                        .changeState();
            }

            // 如果父级任务多于 1 个，说明当前节点不是并行节点，原因为不考虑多对多情况
            if (targetIds.size() > 1) {
                // 1 对 多任务跳转，currentIds 当前节点(1)，targetIds 跳转到的节点(多)
                runtimeService.createChangeActivityStateBuilder()
                        .processInstanceId(task.getProcessInstanceId())
                        .moveSingleActivityIdToActivityIds(currentTaskIds.get(0), targetIds)
                        .changeState();
            }

            // 设置任务为节点已处理过的人员
            setNewActivityAssigneeByBeforeActivity(flowTaskVo.getProcInsId(), activityInstanceListMap);

        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("驳回任务异常：", e);
            throw new BaseException("驳回任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 退回任务
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void taskReturn(FlowTaskVo flowTaskVo) {
        try {
            Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
            // 获取所有节点信息
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            // 获取跳转的节点元素
            FlowElement target = bpmnModel.getFlowElement(flowTaskVo.getTargetKey());
            // 循环获取节点的ID
            List<String> currentTaskIds = getCurrentUserTaskIds(task, target, FlowCommentEnum.REBACK, flowTaskVo.getComment());
            // 获取已处理的任务
            Map<String, List<ActivityInstance>> activityInstanceListMap = getActivityInstanceMapByProcInsId(flowTaskVo.getProcInsId());

            // 1 对 1 或 多 对 1 情况，currentIds 当前要跳转的节点列表(1或多)，targetKey 跳转到的节点(1)
            runtimeService.createChangeActivityStateBuilder()
                    .processInstanceId(task.getProcessInstanceId())
                    .moveActivityIdsToSingleActivityId(currentTaskIds, flowTaskVo.getTargetKey())
                    .changeState();
            // 设置任务为节点已处理过的人员
            setNewActivityAssigneeByBeforeActivity(flowTaskVo.getProcInsId(), activityInstanceListMap);
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("退回任务异常：", e);
            throw new BaseException("退回任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo 请求实体参数
     * @return List<UserTask>
     */
    @Override
    public List<UserTask> findReturnTaskList(FlowTaskVo flowTaskVo) {
        Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
        // 获取所有节点信息
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        // 获取当前任务节点元素
        FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

        // 获取流程变量
        Map<String, Object> variables = flowCommonService.getProcessVariablesByProcInstId(task.getProcessInstanceId());
        if (flowTaskVo.getVariables() != null) {
            variables.putAll(flowTaskVo.getVariables());
        }
        // 获取节点的所有路线
        return FlowableUtil.findReturnRoad(source, variables);
    }

    /**
     * 删除任务
     *
     * @param procInsId
     * @param taskIds   请求实体参数
     */
    @Override
    public void deleteTask(String procInsId, List<String> taskIds) {
        if (StringUtils.isBlank(procInsId) && CollectionUtils.isEmpty(taskIds)) {
            throw new BaseException("删除任务失败，参数异常");
        }
        try {
            List<String> executionIds = new ArrayList<>();
            String procDefId = StringUtils.EMPTY;
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInsId).list();
            for (Task task : tasks) {
                checkTaskSuspend(task);
                if (!taskIds.contains(task.getId())) {
                    continue;
                }
                procDefId = task.getProcessDefinitionId();
                executionIds.add(task.getExecutionId());
            }
            BpmnModel bpmnModel = repositoryService.getBpmnModel(procDefId);
            Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
            FlowElement endEvent = FlowableUtil.findEndEvent(flowElements);
            runtimeService.createChangeActivityStateBuilder()
                    .moveExecutionsToSingleActivityId(executionIds, endEvent.getId())
                    .changeState();
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("删除任务异常：", e);
            throw new BaseException("删除任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 认领/签收任务
     * 认领以后,这个用户就会成为任务的执行人,任务会从其他成员的任务列表中消失
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void claim(FlowTaskVo flowTaskVo) {
        try {
            taskService.claim(flowTaskVo.getTaskId(), flowTaskVo.getUserId());
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("认领/签收任务异常：", e);
            throw new BaseException("认领/签收任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void unClaim(FlowTaskVo flowTaskVo) {
        try {
            taskService.unclaim(flowTaskVo.getTaskId());
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("取消认领/签收任务异常：", e);
            throw new BaseException("取消认领/签收任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 委派任务
     * 任务委派只是委派人将当前的任务交给被委派人进行审批，处理任务后又重新回到委派人身上。
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void delegateTask(FlowTaskVo flowTaskVo) {
        try {
            Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
            checkTaskSuspend(task);
            if (Objects.isNull(task.getAssignee())) {
                throw new BaseException("当前任务没有指定办理人");
            }
            taskService.delegateTask(flowTaskVo.getTaskId(), flowTaskVo.getAssignee());
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("委派任务异常：", e);
            throw new BaseException("委派任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 转办任务
     * 直接将办理人换成别人，这时任务的拥有者不再是转办人
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void assignTask(FlowTaskVo flowTaskVo) {
        try {
            Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
            checkTaskSuspend(task);
            // 直接转派就可以覆盖掉之前的
            taskService.setAssignee(flowTaskVo.getTaskId(), flowTaskVo.getAssignee());
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("转办任务异常：", e);
            throw new BaseException("转办任务失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 多实例加签
     * act_ru_task、act_ru_identitylink各生成一条记录
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void addMultiInstanceExecution(FlowTaskVo flowTaskVo) {
        try {
            Task task = getMultiInstanceTask(flowTaskVo);
            checkTaskSuspend(task);
            UserTask userTask = getMultiInstanceElement(task);
            Map<String, Object> multiUserTaskVariables = getMultiUserTaskVariables(flowTaskVo, userTask, task);
            managementService.executeCommand(new AddMultiInstanceExecutionCmd(userTask.getId(), flowTaskVo.getProcInsId(), multiUserTaskVariables));
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("多实例加签异常：", e);
            throw new BaseException("多实例加签失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 多实例减签
     * act_ru_task减1、act_ru_identitylink不变
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void deleteMultiInstanceExecution(FlowTaskVo flowTaskVo) {
        try {
            Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
            checkTaskSuspend(task);
            String[] currentChildExecutionIds = flowTaskVo.getCurrentChildExecutionIds().split(Constants.COMMA);
            for (String executionId : currentChildExecutionIds) {
                managementService.executeCommand(new DeleteMultiInstanceExecutionCmd(executionId, false));
            }
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("多实例减签异常：", e);
            throw new BaseException("多实例减签失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 取回流程
     *
     * @param flowTaskVo 请求实体参数
     * @param isAdmin    是否为管理员
     */
    @Override
    public void revokeProcess(FlowTaskVo flowTaskVo, Boolean isAdmin) {
        try {
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(flowTaskVo.getProcInsId()).list();
            checkTaskSuspend(taskList);
            Task task = taskList.get(0);
            // 获取上一个历史任务实例
            AtomicReference<HistoricTaskInstance> hisTaskInstance = getHistoricTaskInstance(flowTaskVo.getProcInsId(), flowTaskVo.getTaskId());
            // 获取上一个历史任务实例的任务定义Key列表
            Set<String> taskDefKeys = taskList.stream().map(Task::getTaskDefinitionKey).collect(Collectors.toSet());
            Set<String> targetTaskDefKeys = new HashSet<>();
            // 获取流程定义模型
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            String taskDefinitionKey = hisTaskInstance.get().getTaskDefinitionKey();
            //并行分支情况下存在多个任务
            if (taskDefKeys.size() > 1) {
                // 判断是否在同一个分支上，如果是，则可以取回
                int parallelCount = 0;
                for (String taskDefKey : taskDefKeys) {
                    // 获取当前任务节点元素
                    FlowElement source = bpmnModel.getFlowElement(taskDefKey);
                    FlowElement target = bpmnModel.getFlowElement(taskDefinitionKey);
                    ParallelGateway parallelGateway = FlowableUtil.getParallelGatewayByNextNode(source);
                    if (FlowableUtil.checkImcomingParallelGateway(parallelGateway)){
                        parallelCount++;
                        continue;
                    }
                    // 在子流程中
                    if (Objects.nonNull(source.getSubProcess()) && Objects.nonNull(target.getSubProcess())) {
                        // 在同一个子流程中，可取回
                        if (source.getSubProcess() == target.getSubProcess()) {
                            targetTaskDefKeys.add(taskDefKey);
                        }
                        continue;
                    } else if (Objects.nonNull(source.getSubProcess()) && Objects.isNull(target.getSubProcess())){
                        // 当前节点在子流程中，目标节点不在子流程中，不可取回
                        continue;
                    } else if (Objects.isNull(source.getSubProcess()) && Objects.nonNull(target.getSubProcess())){
                        // 当前节点不在子流程中，目标节点在子流程中，不可取回
                        continue;
                    }
                    // 从当前节点向前扫描, 如果存在路线上不存在目标节点
                    Boolean isSequential = FlowableUtil.iteratorCheckSequentialReferTarget(source, target.getId(), null, null);
                    if (!isSequential) {
                        targetTaskDefKeys.add(taskDefKey);
                    }
                }
                // 汇入并行网关分支节点，可取回
                if (parallelCount == taskDefKeys.size()) {
                    targetTaskDefKeys.addAll(taskDefKeys);
                }
                if (targetTaskDefKeys.isEmpty()) {
                    throw new BaseException("当前节点相对于目标节点，不属于串行关系，无法取回");
                }
            } else {
                // 判断是否汇总并行网关
                FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
                ParallelGateway parallelGateway = FlowableUtil.getParallelGatewayByNextNode(source);
                if (FlowableUtil.checkOutgoingParallelGateway(parallelGateway)){
                    throw new BaseException("当前节点已汇总并行网关节点，无法取回");
                }
                if (Objects.isNull(source.getSubProcess())) {
                    FlowElement startEventByNextNode = FlowableUtil.getStartEventByNextNode(source);
                    if (Objects.nonNull(startEventByNextNode)) {
                        throw new BaseException("上一个节点为开始环节，无法取回");
                    }
                }
                // 当前节点还在并行分支上
                Boolean isSequential = FlowableUtil.iteratorCheckSequentialReferTarget(source, taskDefinitionKey, null, null);
                if (!isSequential) {
                    throw new BaseException("当前节点相对于目标节点，不属于串行关系，无法取回");
                }
                targetTaskDefKeys.add(task.getTaskDefinitionKey());
            }

            // 获取上一个历史任务实例的办理人列表
            List<String> assigneeList = getHistoricAssigneeList(hisTaskInstance);
            if (!isAdmin && !assigneeList.contains(SecurityUtils.getUserId())) {
                throw new BaseException("该任务非当前用户提交，无法取回");
            }

            // 跳转节点
            runtimeService.createChangeActivityStateBuilder()
                    .processInstanceId(flowTaskVo.getProcInsId())
                    .moveActivityIdsToSingleActivityId(new ArrayList<>(targetTaskDefKeys), taskDefinitionKey)
                    .changeState();
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("取回流程异常：", e);
            throw new BaseException("取回流程失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 终止流程
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void terminateProcess(FlowTaskVo flowTaskVo) {
        try {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(flowTaskVo.getProcInsId()).singleResult();
            checkProcessInstanceSuspend(processInstance);
            runtimeService.deleteProcessInstance(flowTaskVo.getProcInsId(), flowTaskVo.getComment());
        } catch (FlowableObjectNotFoundException e) {
            log.error("未找到流程实例，流程可能已发生变化", e);
            throw new BaseException("未找到流程实例，流程可能已发生变化");
        } catch (FlowableException e) {
            log.error("终止流程异常：", e);
            throw new BaseException("终止流程失败，异常信息：" + e.getMessage());
        }
    }

    /**
     * 流程历史流转记录
     *
     * @param procInsId 流程实例Id
     * @return
     */
    @Override
    public Map<String, Object> flowHistoryRecord(String procInsId, String actId, int pageNum, int pageSize) {
        if (StringUtils.isBlank(procInsId)) {
            return new HashMap<>();
        }
        List<FlowRecordFilterDto> actFilters = new ArrayList<>();
        List<FlowHistoricActivityInstanceDto> realList = getHistoricActivityInstances(procInsId, actId, actFilters, false);
        if (CollectionUtils.isEmpty(realList)) {
            return new HashMap<>();
        }
        // 这里使用手动分页方式
        int total = realList.size();
        int index = pageNum <= 1 ? 0 : (pageNum - 1) * pageSize; // 分页起始位置

        Map<String, List<SysUser>> flowCmtAssigneeMap = getFlowAssigneeMap(realList, index, pageSize, false);
        List<FlowTaskDto> hisFlowList = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            if (index >= total) {
                break;
            }
            FlowHistoricActivityInstanceDto histIns = realList.get(index);
            FlowTaskDto flowTask = getFlowTaskDto(histIns);
            flowCommonService.handleMultiAssignee(histIns.getTaskId(), flowTask, hisFlowList, flowCmtAssigneeMap);
            index++;
        }
        return getResultMap(hisFlowList, actFilters, total, pageNum, pageSize);
    }

    /**
     * 流程评论记录
     *
     * @param procInsId 流程实例Id
     * @param actId     环节Id
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return
     */
    @Override
    public Map<String, Object> flowCmts(String procInsId, String actId, int pageNum, int pageSize) {
        if (StringUtils.isBlank(procInsId)) {
            return new HashMap<>();
        }
        List<FlowRecordFilterDto> actFilters = new ArrayList<>();
        List<FlowHistoricActivityInstanceDto> realList = getHistoricActivityInstances(procInsId, actId, actFilters, true);
        if (CollectionUtils.isEmpty(realList)) {
            return new HashMap<>();
        }

        // 这里使用手动分页方式
        int total = realList.size();
        int index = pageNum <= 1 ? 0 : (pageNum - 1) * pageSize; // 分页起始位置
        // 获取意见评论内容
        Map<String, List<FlowCommentDto>> flowCommentMap = getFlowCommentMap(procInsId);
        // 获取流程意见处理人
        Map<String, List<SysUser>> flowCmtAssigneeMap = getFlowAssigneeMap(realList, index, pageSize, true);

        Map<String, List<FlowTaskDto>> groupedMap = new LinkedHashMap<>();
        // 记录首次出现的actId顺序
        List<String> actIdOrder = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            if (index >= total) {
                break;
            }
            FlowHistoricActivityInstanceDto histIns = realList.get(index);
            buildFlowCmtTask(histIns, groupedMap, actIdOrder, flowCmtAssigneeMap, flowCommentMap);
            index++;
        }
        // 合并分组数据
        List<FlowTaskDto> sortedList = new ArrayList<>();
        actIdOrder.forEach(id -> sortedList.addAll(groupedMap.get(id)));
        return getResultMap(sortedList, actFilters, total, pageNum, pageSize);
    }

    /**
     * 获取流程过程图
     *
     * @param processId 流程实例ID
     * @return
     */
    @Override
    public InputStream diagram(String processId) {
        String processDefinitionId;
        // 获取当前的流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        // 如果流程已经结束，则得到结束节点
        if (Objects.isNull(processInstance)) {
            HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();

            processDefinitionId = pi.getProcessDefinitionId();
        } else {
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
            processDefinitionId = pi.getProcessDefinitionId();
        }

        // 获得活动的节点
        List<HistoricActivityInstance> highLightedFlowList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).orderByHistoricActivityInstanceStartTime().asc().list();

        List<String> highLightedFlows = new ArrayList<>();
        List<String> highLightedNodes = new ArrayList<>();
        //高亮线
        for (HistoricActivityInstance tempActivity : highLightedFlowList) {
            if (ActivityTypeEnum.SEQUENCE_FLOW.getCode().equals(tempActivity.getActivityType())) {
                //高亮线
                highLightedFlows.add(tempActivity.getActivityId());
            } else {
                //高亮节点
                highLightedNodes.add(tempActivity.getActivityId());
            }
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration configuration = processEngine.getProcessEngineConfiguration();
        //获取自定义图片生成器
        ProcessDiagramGenerator diagramGenerator = new CustomProcessDiagramGenerator();
        return diagramGenerator.generateDiagram(bpmnModel, "png", highLightedNodes, highLightedFlows, configuration.getActivityFontName(), configuration.getLabelFontName(), configuration.getAnnotationFontName(), configuration.getClassLoader(), 1.0, true);
    }

    /**
     * 获取流程任务变量
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public Map<String, Object> processVariables(String taskId) {
        return getTaskVariables(taskId);
    }

    /**
     * 审批任务获取下一个节点
     *
     * @param flowTaskVo 任务
     * @return
     */
    @Override
    public List<FlowNextDto> getNextFlowNode(FlowTaskVo flowTaskVo) {
        // 1. 获取当前任务
        Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
        if (Objects.isNull(task)) {
            throw new BaseException("任务不存在或已被审批!");
        }
        // 2. 获取当前流程所有流程变量(网关节点时需要校验表达式)
        Map<String, Object> variables = flowCommonService.getProcessVariablesByProcInstId(task.getProcessInstanceId());
        variables.putAll(flowTaskVo.getVariables());
        variables.put(ProcessConstants.PROCESS_INSTANCE_ID, task.getProcessInstanceId());
        // 3. 获取下一个环节任务
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        List<UserTask> nextUserTask = FindNextNodeUtil.getNextUserTasks(bpmnModel, task.getTaskDefinitionKey(), variables);
        return getFlowAttribute(nextUserTask, variables, bpmnModel);
    }

    /**
     * 流程节点信息
     *
     * @param procInsId 流程实例ID
     * @return
     */
    @Override
    public Map<String, Object> flowXmlAndNode(String procInsId, String deployId) {
        try {
            List<FlowViewerDto> flowViewerList = new ArrayList<>();
            // 获取已经完成的节点
            List<HistoricActivityInstance> listFinished = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(procInsId)
                    .finished()
                    .orderByHistoricActivityInstanceStartTime()
                    .asc()
                    .list();

            // 获取待办节点
            List<HistoricActivityInstance> listUnFinished = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(procInsId)
                    .unfinished()
                    .orderByHistoricActivityInstanceStartTime()
                    .asc()
                    .list();

            Map<String, List<HistoricActivityInstance>> unFinishedListMap = listUnFinished.stream().collect(Collectors.groupingBy(HistoricActivityInstance::getActivityId));

            // 保存已经完成的流程节点编号
            for (HistoricActivityInstance historicActivityInstance : listFinished) {
                //过滤掉重启激活的待办节点之后的已完成节点
                if (unFinishedListMap.containsKey(historicActivityInstance.getActivityId())) {
                    break;
                }
                FlowViewerDto flowViewerDto = new FlowViewerDto();
                flowViewerDto.setKey(historicActivityInstance.getActivityId());
                flowViewerDto.setCompleted(true);
                flowViewerList.add(flowViewerDto);
            }

            // 保存需要代办的节点编号
            listUnFinished.forEach(s -> {
                FlowViewerDto flowViewerDto = new FlowViewerDto();
                flowViewerDto.setKey(s.getActivityId());
                flowViewerDto.setCompleted(false);
                flowViewerList.add(flowViewerDto);
            });
            Map<String, Object> result = new HashMap<>();
            // xmlData 数据
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
            InputStream inputStream = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName());
            String xmlData = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            result.put("nodeData", flowViewerList);
            result.put("xmlData", xmlData);
            return result;
        } catch (Exception e) {
            log.error("流程实例ID:{}, 部署ID:{}, 高亮历史任务失败: ", procInsId, deployId, e);
            throw new BaseException("高亮历史任务失败");
        }
    }

    /**
     * 获取任务
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public FlowTaskDto getFlowTask(String taskId) {
        FlowTaskDto flowTask = new FlowTaskDto();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (Objects.isNull(task)) {
            HistoricTaskInstance histTaskInst = historyService.createHistoricTaskInstanceQuery()
                    .taskId(taskId).singleResult();
            // 当前流程信息
            flowTask.setTaskId(histTaskInst.getId());
            flowTask.setTaskDefKey(histTaskInst.getTaskDefinitionKey());
            flowTask.setProcDefId(histTaskInst.getProcessDefinitionId());
            flowTask.setExecutionId(histTaskInst.getExecutionId());
            flowTask.setTaskName(histTaskInst.getName());
            flowTask.setProcInsId(histTaskInst.getProcessInstanceId());
            flowTask.setProcDefId(histTaskInst.getProcessDefinitionId());
            flowTask.setAssignee(histTaskInst.getAssignee());
            flowTask.setCreateTime(histTaskInst.getCreateTime());
            flowTask.setFinishTime(histTaskInst.getEndTime());
        } else {
            // 当前流程信息
            flowTask.setTaskId(task.getId());
            flowTask.setTaskDefKey(task.getTaskDefinitionKey());
            flowTask.setProcDefId(task.getProcessDefinitionId());
            flowTask.setExecutionId(task.getExecutionId());
            flowTask.setTaskName(task.getName());
            flowTask.setProcInsId(task.getProcessInstanceId());
            flowTask.setProcDefId(task.getProcessDefinitionId());
            flowTask.setAssignee(task.getAssignee());
            flowTask.setCreateTime(task.getCreateTime());
        }

        // 流程定义信息
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(flowTask.getProcDefId()).singleResult();
        flowTask.setDeployId(pd.getDeploymentId());
        flowTask.setProcDefName(pd.getName());
        flowTask.setProcDefVersion(pd.getVersion());
        return flowTask;
    }

    /**
     * 获取历史任务
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public FlowTaskDto getHistoryFlowTask(String taskId) {
        HistoricTaskInstance historicTask = historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId).singleResult();
        FlowTaskDto flowTask = new FlowTaskDto();
        // 当前流程信息
        flowTask.setTaskId(historicTask.getId());
        flowTask.setTaskName(historicTask.getName());
        flowTask.setCreateTime(historicTask.getCreateTime());
        flowTask.setProcDefId(historicTask.getProcessDefinitionId());
        flowTask.setExecutionId(historicTask.getExecutionId());
        // 流程定义信息
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(historicTask.getProcessDefinitionId()).singleResult();
        flowTask.setDeployId(pd.getDeploymentId());
        flowTask.setProcDefName(pd.getName());
        flowTask.setProcDefVersion(pd.getVersion());
        flowTask.setProcInsId(historicTask.getProcessInstanceId());
        return flowTask;
    }

    /**
     * 判断当前用户是否为流程创建人
     * initiator为流程创建者固定参数
     *
     * @param procInsId 流程实例ID
     * @return
     */
    @Override
    public boolean checkIsFlowCreator(String procInsId) {
        List<HistoricVariableInstance> variableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(procInsId).list();
        variableInstanceList = variableInstanceList.stream()
                .filter(variableInstance ->
                        variableInstance.getVariableName().equals(ProcessConstants.PROCESS_INITIATOR))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(variableInstanceList)) {
            return false;
        }
        HistoricVariableInstance variableInstance = variableInstanceList.get(0);
        if (variableInstance.getValue() != null) {
            return variableInstance.getValue().toString().equals(SecurityUtils.getUserId());
        }
        return false;
    }

    /**
     * 更新任务审批意见
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void updateTaskComment(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getComment())) {
            return;
        }
        // 1. 获取历史任务实例
        HistoricTaskInstance historicTask = historyService.createHistoricTaskInstanceQuery()
                .taskId(flowTaskVo.getTaskId())
                .singleResult();

        // 2. 权限校验（示例：校验当前用户是否为原处理人）
        if (!StringUtils.equals(historicTask.getAssignee(), SecurityUtils.getUserId())) {
            throw new BaseException("无权修改该任务审批意见");
        }

        // 3.查询正常意见
        List<Comment> taskComments = taskService.getTaskComments(flowTaskVo.getTaskId(), FlowCommentEnum.NORMAL.getType());
        if (CollectionUtils.isEmpty(taskComments)) {
            return;
        }
        Comment comment = taskComments.get(0);
        comment.setFullMessage(flowTaskVo.getComment());
        // 4. 更新意见
        taskService.saveComment(comment);
    }

    /**
     * 检查完成提交条件
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    @Override
    public Boolean checkCompleteCondition(FlowTaskVo flowTaskVo) {
        Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
        checkTaskSuspend(task);
        return true;
    }

    /**
     * 检查退回条件
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    @Override
    public Boolean checkReturnCondition(FlowTaskVo flowTaskVo) {
        Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
        checkTaskSuspend(task);
        // 获取所有节点信息
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        // 获取跳转的节点元素
        FlowElement target = bpmnModel.getFlowElement(flowTaskVo.getTargetKey());
        // 获取当前任务节点元素
        FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

        // 从当前节点向前扫描，如果存在路线上不存在目标节点，说明目标节点是在网关上或非同一路线上，不可跳转；否则目标节点相对于当前节点，属于串行
        Boolean isSequential = FlowableUtil.iteratorCheckSequentialReferTarget(source, flowTaskVo.getTargetKey(), null, null);
        if (!isSequential) {
            throw new BaseException("当前节点相对于目标节点，不属于串行关系，无法退回!");
        }

        // 如果当前节点在并行网关分支上，并且退回到并行网关前，检查并行网关其他分支是否已执行
        checkImcomingParallelGateway(task, source, bpmnModel);

        // 获取当前任务节点的用户任务列表
        List<UserTask> currentUserTaskList = getCurrentUserTaskList(task, target);

        if (CollectionUtils.isEmpty(currentUserTaskList)) {
            log.error("未找到当前节点，可能是流程已发生变化");
            throw new BaseException("未找到当前节点，可能是流程已发生变化");
        }
        return true;
    }

    /**
     * 检查驳回条件
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    @Override
    public Boolean checkRejectCondition(FlowTaskVo flowTaskVo) {
        Task task = taskService.createTaskQuery().taskId(flowTaskVo.getTaskId()).singleResult();
        checkTaskSuspend(task);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        // 获取当前任务节点元素
        FlowElement source = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

        // 如果当前节点在并行网关分支上，并且撤回到并行网关前，检查并行网关其他分支是否已执行
        checkImcomingParallelGateway(task, source, bpmnModel);

        // 获取当前节点的所有父级用户任务节点
        List<UserTask> parentUserTaskList = FlowableUtil.iteratorFindParentUserTasks(source, null, null);
        List<String> parentUserTaskKeyList = parentUserTaskList.stream()
                .map(UserTask::getId).collect(Collectors.toList());

        // 清洗历史任务实例数据
        List<String> lastHistoricTaskInstanceList = historicTaskInstanceClean(task, bpmnModel);
        // 获取目标节点ID列表
        List<String> targetIds = getTargetIds(lastHistoricTaskInstanceList, parentUserTaskKeyList);

        if (CollectionUtils.isEmpty(targetIds)) {
            log.error("未找到目标节点，可能是流程已发生变化");
            throw new BaseException("未找到目标节点，可能是流程已发生变化");
        }

        // 获取当前任务节点的用户任务列表
        List<UserTask> currentUserTaskList = getCurrentUserTaskList(task, parentUserTaskList.get(0));

        if (CollectionUtils.isEmpty(currentUserTaskList)) {
            log.error("未找到当前节点，可能是流程已发生变化");
            throw new BaseException("未找到当前节点，可能是流程已发生变化");
        }

        // 规定：并行网关之前节点必须需存在唯一用户任务节点，如果出现多个任务节点，则并行网关节点默认为结束节点，原因为不考虑多对多情况
        if (targetIds.size() > 1 && currentUserTaskList.size() > 1) {
            throw new BaseException("任务出现多对多情况，无法撤回");
        }
        return true;
    }


    /**
     * 设置任务处理人
     *
     * @param flowTaskVo 流程任务参数
     * @param assigneeId 处理人ID
     */
    @Override
    public void setAssignee(FlowTaskVo flowTaskVo, String assigneeId) {
        checkTaskSuspend(flowTaskVo.getTaskId());
        taskService.setAssignee(flowTaskVo.getTaskId(), assigneeId);
    }

    /**
     * 获取任务列表
     *
     * @param procInsId 流程实例ID
     * @param taskIds   任务ID列表
     * @return
     */
    @Override
    public List<FlowTaskDto> getTaskList(String procInsId, List<String> taskIds) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(procInsId).list();
        if (CollectionUtils.isEmpty(taskList)) {
            return Collections.emptyList();
        }
        // 获取任务处理人Map
        Map<String, List<SysUser>> taskAssigneeMap = flowCommonService.getTaskAssigneeMap(taskList);
        // 流程定义信息
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(taskList.get(0).getProcessDefinitionId())
                .singleResult();
        List<FlowTaskDto> flowList = new ArrayList<>();
        taskList.stream()
                .filter(task -> taskIds.contains(task.getId()))
                .forEach(task -> {
                    flowList.add(buildFlowTaskDto(task, pd, taskAssigneeMap));
                });
        return flowList;
    }

    /**
     * 获取当前任务变量（包含扩展参数）
     *
     * @param taskId 任务ID
     * @return
     */
    public Map<String, Object> getTaskVariables(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> extendVarMap = flowCommonService.getProcessVariablesByTaskId(taskId);
        if (Objects.isNull(task)) {
            return extendVarMap;
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        extendVarMap.putAll(FlowableUtil.getExtendVarByTaskDefinitionKey(bpmnModel, task.getTaskDefinitionKey()));
        return extendVarMap;
    }

    /**
     * 处理委托，特别处理下个环节处理人为固定人员的情况
     *
     * @param flowTaskVo 流程参数
     * @return
     */
    @Override
    public void handleFixedAssigneeByEntrust(FlowTaskVo flowTaskVo) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(flowTaskVo.getProcInsId()).list();
        if (CollectionUtils.isEmpty(taskList)) {
            return;
        }
        Map<String, List<Task>> taskListMap = taskList.stream().collect(Collectors.groupingBy(Task::getTaskDefinitionKey));
        BpmnModel bpmnModel = repositoryService.getBpmnModel(flowTaskVo.getDefId());
        Map<String, Object> processVariablesByProcInstId = flowCommonService.getProcessVariablesByProcInstId(flowTaskVo.getProcInsId());
        if (processVariablesByProcInstId != null) {
            processVariablesByProcInstId.putAll(flowTaskVo.getVariables());
            flowTaskVo.setVariables(processVariablesByProcInstId);
        }
        List<UserTask> nextUserTasks = FindNextNodeUtil.getNextUserTasks(bpmnModel, flowTaskVo.getTaskDefKey(), flowTaskVo.getVariables());
        if (CollectionUtils.isEmpty(nextUserTasks)) {
            return;
        }
        for (UserTask nextUserTask : nextUserTasks) {
            // 多实例
            if (nextUserTask.hasMultiInstanceLoopCharacteristics()) {
                continue;
            }
            String nextUserTaskId = nextUserTask.getId();
            List<Task> tasks = taskListMap.get(nextUserTaskId);
            if (CollectionUtils.isNotEmpty(tasks)) {
                tasks.forEach(task -> {
                    taskService.setAssignee(task.getId(), (String) flowTaskVo.getVariables().get(nextUserTaskId + ProcessConstants.FIXED_TASK_SUFFIX));
                });
            }
        }
    }

    /**
     * 检查任务是否挂起
     *
     * @param taskId 请求实体参数
     * @return
     */
    @Override
    public void checkTaskSuspend(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            return;
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        checkTaskSuspend(task);
    }

    /**
     * 删除并行网关重复任务
     *
     * @param flowTaskVo 请求实体参数
     */
    @Override
    public void deleteTaskByParallelGateway(FlowTaskVo flowTaskVo) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(flowTaskVo.getProcInsId()).list();
        if (CollectionUtils.isEmpty(tasks) || tasks.size() == 1) {
            return;
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(tasks.get(0).getProcessDefinitionId());
        List<Task> parallelTask = getTaskByImcomingParallelGateway(tasks, bpmnModel);
        if (CollectionUtils.isEmpty(parallelTask)) {
            return;
        }
        // 多实例任务直接过滤
        Map<String, Task> taskMap = new HashMap<>();
        List<String> executionIds = tasks.stream()
                .filter(task -> {
                    String key = task.getTaskDefinitionKey();
                    if (taskMap.containsKey(key)) {
                        return true;
                    } else {
                        taskMap.put(key, task);
                        return false;
                    }
                })
                .map(Task::getExecutionId)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(executionIds)) {
            Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
            FlowElement endEvent = FlowableUtil.findEndEvent(flowElements);
            runtimeService.createChangeActivityStateBuilder()
                    .moveExecutionsToSingleActivityId(executionIds, endEvent.getId())
                    .changeState();
        }
    }

    /**
     * 检查任务是否挂起
     *
     * @param task 任务
     * @return
     */
    public void checkTaskSuspend(Task task) {
        if (Objects.isNull(task)) {
            throw new BaseException("当前任务为空!");
        }
        if (task.isSuspended()) {
            throw new BaseException("当前任务处于挂起状态，操作失败!");
        }
    }

    /**
     * 检查任务是否挂起
     *
     * @param tasks 任务列表
     * @return
     */
    public void checkTaskSuspend(List<Task> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            throw new BaseException("当前任务为空!");
        }
        for (Task task : tasks) {
            if (task.isSuspended()) {
                throw new BaseException("当前任务处于挂起状态，操作失败!");
            }
        }
    }

    /**
     * 检查任务是否挂起
     *
     * @param processInstance 流程实例
     * @return
     */
    public void checkProcessInstanceSuspend(ProcessInstance processInstance) {
        if (Objects.isNull(processInstance)) {
            throw new BaseException("流程实例为空!");
        }
        if (processInstance.isSuspended()) {
            throw new BaseException("当前任务处于挂起状态，操作失败!");
        }
    }

    /**
     * 获取多实例任务的变量
     *
     * @param flowTaskVo 请求实体参数
     * @param userTask   UserTask
     * @param task       Task
     * @return
     */
    private Map<String, Object> getMultiUserTaskVariables(FlowTaskVo flowTaskVo, UserTask userTask, Task task) {
        MultiInstanceLoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();
        if (loopCharacteristics == null) {
            throw new BaseException("当前任务不是多实例任务，不能执行加签操作");
        }
        Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
        String elementVariable = loopCharacteristics.getElementVariable();
        if (!variables.containsKey(elementVariable)) {
            throw new BaseException("加签失败，变量" + elementVariable + "不存在");
        }
        variables.put(elementVariable, flowTaskVo.getAssignee());
        String inputDataItem = loopCharacteristics.getInputDataItem();
        Object itemValue = variables.get(inputDataItem);
        if (Objects.isNull(itemValue)) {
            throw new BaseException("加签失败，变量" + inputDataItem + "不存在");
        }
        if (itemValue instanceof List) {
            ((List) variables.get(inputDataItem)).add(flowTaskVo.getAssignee());
        }
        return variables;
    }

    /**
     * 获取多实例任务
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    private Task getMultiInstanceTask(FlowTaskVo flowTaskVo) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(flowTaskVo.getProcInsId()).list();
        if (CollectionUtils.isEmpty(taskList)) {
            throw new BaseException("流程未启动或已执行完成，无法加签");
        }
        boolean match = taskList.stream()
                .anyMatch(task -> flowTaskVo.getAssignee().equals(task.getAssignee()));
        if (match) {
            SysUser assignee = sysUserService.selectUserById(flowTaskVo.getAssignee());
            throw new BaseException("加签失败，加签人" + assignee.getNickName() + "已经存在");
        }
        return taskList.stream()
                .filter(task1 -> task1.getId().equals(flowTaskVo.getTaskId()))
                .findFirst()
                .orElseThrow(() -> new BaseException("当前任务不存在！"));
    }

    /**
     * 获取多实例任务元素
     *
     * @param task Task
     * @return UserTask
     */
    private UserTask getMultiInstanceElement(Task task) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        return (UserTask) bpmnModel.getFlowElement(task.getTaskDefinitionKey());
    }

    /**
     * 获取流程审批处理人
     *
     * @param realList 实际列表
     * @param index    起始位置
     * @param pageSize 每页大小
     * @param isCmt    是否审批
     * @return Map<String, List < SysUser>> 按taskId分组
     */
    private Map<String, List<SysUser>> getFlowAssigneeMap(List<FlowHistoricActivityInstanceDto> realList, int index, int pageSize, Boolean isCmt) {
        Map<String, Set<String>> assigneeIdMap = new HashMap<>();
        Set<String> allAssigneeSet = new HashSet<>();
        int total = realList.size();
        for (int i = 0; i < pageSize && index < total; i++, index++) {
            FlowHistoricActivityInstanceDto histIns = realList.get(index);
            Set<String> assigneeList = new HashSet<>();
            String assigneeStr = histIns.getAssignee();
            if (StringUtils.isNotBlank(assigneeStr)) {
                Arrays.stream(assigneeStr.split(Constants.COMMA))
                        .filter(StringUtils::isNotBlank)
                        .forEach(assigneeList::add);
            } else {
                List<HistoricIdentityLink> linksForTask = historyService.getHistoricIdentityLinksForTask(histIns.getTaskId());
                if (CollectionUtils.isNotEmpty(linksForTask)) {
                    linksForTask.stream()
                            .map(HistoricIdentityLink::getUserId)
                            .filter(Objects::nonNull)
                            .forEach(assigneeList::add);
                }
            }
            assigneeIdMap.put(histIns.getTaskId(), assigneeList);
            allAssigneeSet.addAll(assigneeList);
        }
        return flowCommonService.batchGetAssignee(new ArrayList<>(allAssigneeSet), assigneeIdMap);
    }

    /**
     * 清洗历史任务实例数据
     *
     * @param task      当前任务
     * @param bpmnModel 流程模型
     * @return List<String> 返回清洗后的历史任务实例Key列表
     */
    private List<String> historicTaskInstanceClean(Task task, BpmnModel bpmnModel) {
        Process process = bpmnModel.getProcesses().get(0);
        // 获取全部节点列表，包含子节点
        Collection<FlowElement> allElements = FlowableUtil.getAllElements(process.getFlowElements(), null);
        // 获取全部历史节点活动实例，即已经走过的节点历史，数据采用开始时间升序
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();
        // 数据清洗，将回滚导致的脏数据清洗掉
        return FlowableUtil.historicTaskInstanceClean(allElements, historicTaskInstanceList);
    }

    /**
     * 获取目标节点ID列表
     *
     * @param lastHistoricTaskInstanceList 历史任务实例列表
     * @param parentUserTaskKeyList        父级用户任务Key列表
     * @return List<String> 返回目标节点ID列表
     */
    private List<String> getTargetIds(List<String> lastHistoricTaskInstanceList, List<String> parentUserTaskKeyList) {
        // 此时历史任务实例为倒序，获取最后走的节点
        List<String> targetIds = new ArrayList<>();
        StringBuilder parentHistoricTaskKey = new StringBuilder();
        for (String historicTaskInstanceKey : lastHistoricTaskInstanceList) {
            // 当会签时候会出现特殊的，连续都是同一个节点历史数据的情况，这种时候跳过
            if (parentHistoricTaskKey.toString().equals(historicTaskInstanceKey)) {
                continue;
            }
            parentHistoricTaskKey = new StringBuilder(historicTaskInstanceKey);
            // 如果当前历史节点，属于父级的节点，说明最后一次经过了这个点，需要退回这个点
            if (parentUserTaskKeyList.contains(historicTaskInstanceKey)) {
                targetIds.add(historicTaskInstanceKey);
            }
        }
        return targetIds;
    }

    /**
     * 获取当前用户任务ID列表
     *
     * @param task            当前任务
     * @param targetTask      目标任务
     * @param flowCommentEnum 流程评论枚举
     * @param comment         评论内容
     * @return List<String> 返回当前用户任务ID列表
     */
    private List<String> getCurrentUserTaskIds(Task task, FlowElement targetTask, FlowCommentEnum flowCommentEnum, String comment) {
        List<Task> runTaskList = taskService.createTaskQuery()
                .processInstanceId(task.getProcessInstanceId()).list();
        List<String> runTaskKeyList = runTaskList.stream()
                .map(Task::getTaskDefinitionKey).collect(Collectors.toList());
        // 通过父级网关的出口连线，结合 runTaskList 比对，获取任务
        List<UserTask> currentUserTaskList = FlowableUtil.iteratorFindChildUserTasks(targetTask, runTaskKeyList, null, null);
        if (CollectionUtils.isEmpty(currentUserTaskList)) {
            return Collections.emptyList();
        }
        List<String> currentIds = new ArrayList<>();
        currentUserTaskList.forEach(currentUserTask -> runTaskList.forEach(runTask -> {
            if (currentUserTask.getId().equals(runTask.getTaskDefinitionKey())) {
                taskService.addComment(runTask.getId(), runTask.getProcessInstanceId(), flowCommentEnum.getType(), comment);
            }
            currentIds.add(currentUserTask.getId());
        }));
        return currentIds;
    }


    /**
     * 获取上一个历史任务实例
     *
     * @param procInsId 流程实例ID
     * @param taskId
     * @return
     */
    private AtomicReference<HistoricTaskInstance> getHistoricTaskInstance(String procInsId, String taskId) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(procInsId)
                .finished()
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();

        //获取当前节点上一个节点
        AtomicReference<HistoricTaskInstance> hisTaskInstance = new AtomicReference<>();
        historicTaskInstanceList.forEach(hti -> {
            String deleteReason = hti.getDeleteReason();
            if ((StringUtils.isBlank(taskId) || hti.getId().equals(taskId)) //管理员取回，taskId为空
                    && !ProcessConstants.MI_END.equals(deleteReason)
                    && !ProcessConstants.DELETE_MI_EXECUTION.equals(deleteReason)
                    && (deleteReason == null || !deleteReason.contains(ProcessConstants.CHANGE_ACTIVITY_EVENT))) {
                hisTaskInstance.set(hti);
            }
        });
        return hisTaskInstance;
    }

    /**
     * 获取上一个历史任务实例的办理人列表
     *
     * @param hisTaskInstance 上一个历史任务实例
     * @return
     */
    private List<String> getHistoricAssigneeList(AtomicReference<HistoricTaskInstance> hisTaskInstance) {
        if (Objects.isNull(hisTaskInstance.get())) {
            throw new BaseException("不存在上一个节点，无法取回");
        }
        List<String> assigneeList = new ArrayList<>();
        if (StringUtils.isNotBlank(hisTaskInstance.get().getAssignee())) {
            assigneeList.add(hisTaskInstance.get().getAssignee());
            return assigneeList;
        }
        historyService.getHistoricIdentityLinksForTask(hisTaskInstance.get().getId())
                .forEach(identityLink -> {
                    if (identityLink.getUserId() != null) {
                        assigneeList.add(identityLink.getUserId());
                    }
                });
        return assigneeList;
    }

    /**
     * 获取流程任务DTO
     *
     * @param histIns 历史环节实例
     * @return FlowTaskDto
     */
    private FlowTaskDto getFlowTaskDto(FlowHistoricActivityInstanceDto histIns) {
        FlowTaskDto flowTask = new FlowTaskDto();
        flowTask.setTaskId(histIns.getTaskId());
        flowTask.setTaskName(histIns.getActivityName());
        flowTask.setCreateTime(histIns.getStartTime());
        flowTask.setFinishTime(histIns.getEndTime());
        flowTask.setStatus(histIns.getEndTime() == null ? Constants.NO_VALUE : Constants.YES_VALUE);
        flowTask.setActId(histIns.getActivityId());
        Long durationInMillis = histIns.getDurationInMillis();
        flowTask.setDuration(Objects.nonNull(durationInMillis) && durationInMillis > 0 ? FlowableUtil.getDate(durationInMillis) : null);
        return flowTask;
    }

    /**
     * 获取结果Map
     *
     * @param hisFlowList  历史任务列表
     * @param actFilters   过滤
     * @param total        总页数
     * @param pageNum      当前页数
     * @param pageSize     当前每页数量
     * @return
     */
    private Map<String, Object> getResultMap(List<FlowTaskDto> hisFlowList, List<FlowRecordFilterDto> actFilters, int total, int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("flowList", hisFlowList);
        map.put("actFilters", actFilters);
        map.put("total", total);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return map;
    }

    /**
     * 获取流程意见Map
     *
     * @param procInsId 流程实例ID
     * @return
     */
    private Map<String, List<FlowCommentDto>> getFlowCommentMap(String procInsId) {
        FlowCommentQo qo = new FlowCommentQo();
        qo.setProcInstId(procInsId);
        List<FlowCommentDto> commentList = flowCommentService.getComments(qo);
        return commentList.stream()
                .collect(Collectors.groupingBy(FlowCommentDto::getTaskId));
    }

    /**
     * 构建流程评论任务
     *
     * @param histIns            历史环节实例
     * @param groupedMap         分组的任务Map
     * @param actIdOrder         环节ID顺序列表
     * @param flowCmtAssigneeMap 流程评论处理人Map
     * @param flowCommentMap     流程评论内容Map
     */
    private void buildFlowCmtTask(FlowHistoricActivityInstanceDto histIns, Map<String, List<FlowTaskDto>> groupedMap,
                                  List<String> actIdOrder, Map<String, List<SysUser>> flowCmtAssigneeMap, Map<String, List<FlowCommentDto>> flowCommentMap) {
        FlowTaskDto flowTask = new FlowTaskDto();
        flowTask.setTaskId(histIns.getTaskId());
        flowTask.setTaskName(histIns.getActivityName());
        flowTask.setCreateTime(histIns.getStartTime());
        flowTask.setFinishTime(histIns.getEndTime());
        flowTask.setActId(histIns.getActivityId());
        setFlowCmtAssignee(histIns, flowTask, flowCmtAssigneeMap);

        List<FlowCommentDto> flowCommentDtos = flowCommentMap.get(histIns.getTaskId());
        if (CollectionUtils.isNotEmpty(flowCommentDtos)) {
            flowCommentDtos.forEach(comment -> {
                flowTask.setComment(FlowCommentVo.builder().type(comment.getType()).comment(comment.getFullMessage()).build());
            });
        }

        String activityId = histIns.getActivityId();
        if (!groupedMap.containsKey(activityId)) {
            groupedMap.put(activityId, new ArrayList<>());
            actIdOrder.add(activityId);
        }
        groupedMap.get(activityId).add(flowTask);
    }

    /**
     * 构建流程任务DTO
     *
     * @param task            任务
     * @param pd              流程定义
     * @param taskAssigneeMap 任务处理人Map
     * @return
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
        List<SysUser> assignees = taskAssigneeMap.getOrDefault(task.getId(), Collections.emptyList());
        AssigneeUtil.fillAssigneeInfo(
                assignees,
                flowTask::setAssignee,
                flowTask::setAssigneeName,
                flowTask::setAssigneeDeptName
        );
        return flowTask;
    }

    /**
     * 获取当前节点ID列表
     *
     * @param task 当前任务
     * @param target 目标节点元素
     * @return
     */
    private List<UserTask> getCurrentUserTaskList(Task task, FlowElement target) {
        List<Task> runTaskList = taskService.createTaskQuery()
                .processInstanceId(task.getProcessInstanceId()).list();
        List<String> runTaskKeyList = runTaskList.stream()
                .map(Task::getTaskDefinitionKey).collect(Collectors.toList());
        // 通过父级网关的出口连线，结合 runTaskList 比对，获取任务
        return FlowableUtil.iteratorFindChildUserTasks(target, runTaskKeyList, null, null);
    }

    /**
     * 获取流程历史环节实例
     *
     * @param procInsId 流程实例ID
     * @param isCmt     是否审批
     * @return
     */
    private List<FlowHistoricActivityInstanceDto> getHistoricActivityInstances(String procInsId, Boolean isCmt) {
        if (StringUtils.isBlank(procInsId)) {
            return Collections.emptyList();
        }
        FlowHistoricActivityInstanceQo activityInstanceQo = new FlowHistoricActivityInstanceQo();
        activityInstanceQo.setProcInstId(procInsId);
        activityInstanceQo.setIsFinished(isCmt);
        activityInstanceQo.setOrderBy(QueryConstants.START_TIME + QueryConstants.DESC);
        List<FlowHistoricActivityInstanceDto> list = flowHistoryService.getHistoricActivityInstances(activityInstanceQo);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        // 过滤掉非审批过程数据
        return list.stream()
                .filter(h -> {
                    String deleteReason = h.getDeleteReason();
                    return StringUtils.isNotBlank(h.getTaskId())
                            && !ProcessConstants.MI_END.equals(deleteReason)
                            && !ProcessConstants.DELETE_MI_EXECUTION.equals(deleteReason)
                            && ( deleteReason == null || !deleteReason.contains(ProcessConstants.CHANGE_ACTIVITY_EVENT));
                })
                .collect(Collectors.toList());
    }

    /**
     * 获取流程历史环节
     *
     * @param procInsId  流程实例ID
     * @param actId      环节ID
     * @param actFilters 过滤的环节列表
     * @param isCmt      是否审批
     * @return
     */
    private List<FlowHistoricActivityInstanceDto> getHistoricActivityInstances(String procInsId, String actId, List<FlowRecordFilterDto> actFilters, Boolean isCmt) {
        // 获取流程历史环节实例
        List<FlowHistoricActivityInstanceDto> realList = getHistoricActivityInstances(procInsId, isCmt);
        if (CollectionUtils.isEmpty(realList)) {
            return Collections.emptyList();
        }
        // 办理环节（去重）
        Map<String, String> actMap = new HashMap<>();
        realList.forEach(h -> {
            if (!actMap.containsKey(h.getActivityId())) {
                FlowRecordFilterDto act = new FlowRecordFilterDto();
                act.setActId(h.getActivityId());
                act.setActName(h.getActivityName());
                actMap.put(h.getActivityId(), h.getActivityName());
                actFilters.add(act);
            }
        });
        // 筛选条件过滤
        if (StringUtils.isNotBlank(actId)) {
            realList = realList.stream()
                    .filter(h -> h.getActivityId().equals(actId))
                    .collect(Collectors.toList());
        }
        return realList;
    }

    /**
     * 设置流程意见处理人
     *
     * @param histIns            历史环节实例
     * @param flowTask           流程任务
     * @param flowCmtAssigneeMap 处理人Map
     */
    private void setFlowCmtAssignee(FlowHistoricActivityInstanceDto histIns, FlowTaskDto flowTask, Map<String, List<SysUser>> flowCmtAssigneeMap) {
        if (!flowCmtAssigneeMap.containsKey(histIns.getTaskId())) {
            return;
        }
        List<SysUser> sysUsers = flowCmtAssigneeMap.get(histIns.getTaskId());
        if (CollectionUtils.isNotEmpty(sysUsers)) {
            SysUser sysUser = sysUsers.get(0);
            flowTask.setAssigneeId(sysUser.getUserId());
            flowTask.setAssigneeName(sysUser.getNickName());
            flowTask.setDeptName(Objects.nonNull(sysUser.getDept()) ? sysUser.getDept().getDeptName() : "");
        }
    }

    /**
     * 获取任务节点属性,包含自定义扩展属性等
     *
     * @param nextUserTask 下一个节点任务
     * @param variables    流程变量
     * @param bpmnModel    流程模型
     */
    private List<FlowNextDto> getFlowAttribute(List<UserTask> nextUserTask, Map<String, Object> variables, BpmnModel bpmnModel) {
        List<FlowNextDto> flowNextList = new ArrayList<>();
        if (CollectionUtils.isEmpty(nextUserTask)) {
            FlowNextDto flowNextDto = new FlowNextDto();
            flowNextDto.setDataType(ProcessConstants.FINISH);
            flowNextDto.setNodeId(ProcessConstants.FINISH);
            flowNextList.add(flowNextDto);
            return flowNextList;
        }
        for (UserTask userTask : nextUserTask) {
            FlowNextDto flowNextDto = new FlowNextDto();
            flowNextDto.setNodeId(userTask.getId());
            flowNextDto.setNodeName(userTask.getName());
            flowNextDto.setSelectRange(getSelectRangeByUserTask(bpmnModel, userTask, variables));
            // 会签节点(多实例)
            if (handleMultiInstanceForUserTask(flowNextDto, userTask)) {
                flowNextList.add(flowNextDto);
                continue;
            }
            // 非会签节点，读取自定义节点属性 判断是否需要动态指定任务接收人员、组
            String dataType = userTask.getAttributeValue(ProcessConstants.NAMESPACE, ProcessConstants.PROCESS_CUSTOM_DATA_TYPE);
            String userType = userTask.getAttributeValue(ProcessConstants.NAMESPACE, ProcessConstants.PROCESS_CUSTOM_USER_TYPE);
            flowNextDto.setVars(flowCommonService.getExpression(userTask));
            flowNextDto.setType(userType);
            flowNextDto.setDataType(dataType);
            flowNextDto.setAssignees(flowCommonService.getNextAssignees(userType, dataType, userTask, variables));
            flowNextList.add(flowNextDto);
        }
        return flowNextList;
    }

    /**
     * 会签节点处理
     *
     * @param flowNextDto 下一个节点信息
     * @param userTask   用户任务
     * @return
     */
    private Boolean handleMultiInstanceForUserTask(FlowNextDto flowNextDto, UserTask userTask) {
        if (Objects.isNull(userTask.getLoopCharacteristics())) {
            return false;
        }
        MultiInstanceLoopCharacteristics multiInstance = userTask.getLoopCharacteristics();
        flowNextDto.setVars(multiInstance.getInputDataItem());
        flowNextDto.setType(ProcessConstants.PROCESS_MULTI_INSTANCE);
        flowNextDto.setDataType(ProcessConstants.DYNAMIC);
        return true;
    }

    /**
     * 获取任务节点选人范围
     *
     * @param bpmnModel 流程模型
     * @param userTask  任务节点
     * @param variables
     * @return
     */
    private String getSelectRangeByUserTask(BpmnModel bpmnModel, UserTask userTask, Map<String, Object> variables) {
        Map<String, Object> extendVarMap = FlowableUtil.getExtendVarByTaskDefinitionKey(bpmnModel, userTask.getId());
        if (MapUtils.isEmpty(extendVarMap)) {
            return StringUtils.EMPTY;
        }
        variables.putAll(extendVarMap);
        Object selectRangeObj = extendVarMap.get(ProcessConstants.SELECT_RANGE);
        return Objects.nonNull(selectRangeObj) ? selectRangeObj.toString() : StringUtils.EMPTY;
    }

    /**
     * 获取所有节点信息
     *
     * @param procInsId 流程实例ID
     * @return 所有节点信息
     */
    private Map<String, List<ActivityInstance>> getActivityInstanceMapByProcInsId(String procInsId) {
        return runtimeService.createActivityInstanceQuery()
                .processInstanceId(procInsId)
                .orderByActivityInstanceStartTime()
                .desc() //倒序排序
                .list()
                .stream()
                .collect(Collectors.groupingBy(ActivityInstance::getActivityId));
    }

    /**
     * 设置任务为节点已处理过的人员
     *
     * @param procInsId               流程实例ID
     * @param activityInstanceListMap 节点实例列表
     */
    private void setNewActivityAssigneeByBeforeActivity(String procInsId, Map<String, List<ActivityInstance>> activityInstanceListMap) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInsId).list();
        if (CollectionUtils.isEmpty(tasks) || MapUtils.isEmpty(activityInstanceListMap)) {
            return;
        }
        Map<String, List<Task>> taskListMap = tasks.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Task::getTaskDefinitionKey));
        taskListMap.forEach((key, taskList) -> {
            List<ActivityInstance> activityInstances = activityInstanceListMap.get(key);
            if (CollectionUtils.isEmpty(activityInstances) || taskList.size() > activityInstances.size()) {
                return;
            }
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String assignee = activityInstances.get(i).getAssignee();
                if (StringUtils.isNotBlank(assignee)) {
                    taskService.setAssignee(task.getId(), assignee);
                }
            }
        });
    }

    /**
     * 获取汇入并行网关下的任务
     *
     * @param tasks      任务列表
     * @param bpmnModel 流程模型
     * @return 过滤后的任务列表
     */
    private List<Task> getTaskByImcomingParallelGateway(List<Task> tasks, BpmnModel bpmnModel) {
        tasks.removeIf( task -> {
            // 判断任务下一个节点是否是并行网关
            FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
            if (!(flowElement instanceof UserTask)) {
                return true;
            }
            UserTask userTask = (UserTask) flowElement;
            MultiInstanceLoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();
            if (loopCharacteristics != null && loopCharacteristics.isSequential()) {
                // 如果是多实例任务，跳过
                return true;
            }
            for (SequenceFlow outgoingFlow : userTask.getOutgoingFlows()) {
                FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
                // 如果下一个节点是并行网关
                if (targetFlowElement instanceof ParallelGateway) {
                    ParallelGateway parallelGateway = (ParallelGateway) targetFlowElement;
                    if (FlowableUtil.checkImcomingParallelGateway(parallelGateway)) {
                        return false;
                    }
                }
            }
            return true;
        });
        return tasks;
    }

    /**
     * 检查汇入并行网关，如果当前任务在并行网关分支上，检查其他分支是否已执行
     *
     * @param task
     * @param source
     * @param bpmnModel
     */
    private void checkImcomingParallelGateway(Task task, FlowElement source, BpmnModel bpmnModel) {
        if (Objects.isNull(task) || Objects.isNull(source) || !(source instanceof UserTask)) {
            return;
        }
        ParallelGateway parallelGateway = FlowableUtil.getParallelGatewayByNextNode(source);

        if (Objects.nonNull(parallelGateway) && parallelGateway.getOutgoingFlows().size() > 1) {
            String procInstId = task.getProcessInstanceId();
            Set<String> parallelGatewayFinishedUserTask = getParallelGatewayFinishedUserTask(procInstId, parallelGateway, bpmnModel);
            if (CollectionUtils.isNotEmpty(parallelGatewayFinishedUserTask)) {
                throw new BaseException("操作失败，当前节点在并行网关分支上，存在部分分支节点已执行，不允许退回或驳回!");
            }
        }
    }

    /**
     * 获取并行网关之后的用户任务
     *
     * @param processInstanceId 流程实例Id
     * @param parallelGateway 并行网关Id
     * @param bpmnModel 流程模型
     * @return
     */
    private Set<String> getParallelGatewayFinishedUserTask(String processInstanceId, ParallelGateway parallelGateway, BpmnModel bpmnModel) {
        // 1. 获取并行网关所有分支用户任务定义key
        Set<String> branchUserTaskKeys = new HashSet<>();
        for (SequenceFlow flow : parallelGateway.getOutgoingFlows()) {
            FlowElement target = bpmnModel.getFlowElement(flow.getTargetRef());
            if (target instanceof UserTask) {
                branchUserTaskKeys.add(target.getId());
            } else if (target instanceof FlowNode) {
                branchUserTaskKeys.addAll(FlowableUtil.findUserTaskKeysInBranch((FlowNode) target, bpmnModel));
            }
        }
        // 2. 获取所有已完成的用户任务历史实例，按开始时间升序
        List<HistoricTaskInstance> allFinishedTasks = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .finished()
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();
        // 3. 找到最近一次到达并行网关的历史节点
        List<HistoricActivityInstance> gatewayActivities = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .activityId(parallelGateway.getId())
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();
        if (gatewayActivities.isEmpty()) {
            return Collections.emptySet();
        }
        // 取最近一次到达并行网关的时间
        Date lastGatewayTime = gatewayActivities.get(gatewayActivities.size() - 1).getStartTime();
        // 4. 过滤：只保留分支UserTask，且开始时间在lastGatewayTime之后
        return allFinishedTasks.stream()
                .filter(task -> (StringUtils.isBlank(task.getDeleteReason())
                        || !task.getDeleteReason().contains("Change activity to"))
                        && branchUserTaskKeys.contains(task.getTaskDefinitionKey())
                        && task.getStartTime().after(lastGatewayTime))
                .map(HistoricTaskInstance::getTaskDefinitionKey)
                .collect(Collectors.toSet());
    }
}
