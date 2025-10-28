package com.ruoyi.flowable.service.task.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.utils.NumberUtils;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.convert.task.BpmTaskConvert;
import com.ruoyi.flowable.core.enums.definition.BpmTaskRuleScriptEnum;
import com.ruoyi.flowable.core.enums.task.BpmProcessInstanceDeleteReasonEnum;
import com.ruoyi.flowable.core.enums.task.BpmProcessInstanceResultEnum;
import com.ruoyi.flowable.domain.dto.user.AdminUserRespDTO;
import com.ruoyi.flowable.domain.dto.user.DeptRespDTO;
import com.ruoyi.flowable.domain.entity.task.BpmTaskExtDO;
import com.ruoyi.flowable.domain.vo.task.*;
import com.ruoyi.flowable.mapper.task.BpmTaskExtMapper;
import com.ruoyi.flowable.service.definition.BpmModelService;
import com.ruoyi.flowable.service.message.BpmMessageService;
import com.ruoyi.flowable.service.task.BpmProcessInstanceService;
import com.ruoyi.flowable.service.task.BpmTaskService;
import com.ruoyi.flowable.service.user.AdminUserApi;
import com.ruoyi.flowable.service.user.DeptApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.common.utils.collection.CollectionUtils.convertMap;
import static com.ruoyi.common.utils.collection.CollectionUtils.convertSet;
import static com.ruoyi.flowable.core.enums.ErrorCodeConstants.*;


/**
 * 流程任务实例 Service 实现类
 * <p>
 * hasPermi
 *
 * @author jason
 */
@Slf4j
@Service("bpmTaskService")
public class BpmTaskServiceImpl implements BpmTaskService {

    @Resource
    private RuntimeService runtimeService;
//    @Autowired
//    @Qualifier("flowableTaskService")
    @Resource
    private TaskService flowableTaskService;
    @Resource
    private HistoryService historyService;

    @Resource
    private BpmProcessInstanceService processInstanceService;

    @Resource
    private BpmModelService modelService;

    @Resource
    protected IdentityService identityService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private DeptApi deptApi;

    @Resource
    private BpmTaskExtMapper taskExtMapper;
    @Resource
    private BpmMessageService messageService;

    @Override
    public PageResult<BpmTaskTodoPageItemRespVO> getTodoTaskPage(Long userId, BpmTaskTodoPageReqVO pageVO) {
        // 查询待办任务
        TaskQuery taskQuery = flowableTaskService.createTaskQuery().taskAssignee(String.valueOf(userId)) // 分配给自己
                .orderByTaskCreateTime().desc(); // 创建时间倒序
        if (StrUtil.isNotBlank(pageVO.getName())) {
            taskQuery.taskNameLike("%" + pageVO.getName() + "%");
        }
        if (pageVO.getBeginCreateTime() != null) {
            taskQuery.taskCreatedAfter(pageVO.getBeginCreateTime());
        }
        if (pageVO.getEndCreateTime() != null) {
            taskQuery.taskCreatedBefore(pageVO.getEndCreateTime());
        }
        // 执行查询
        List<Task> tasks = taskQuery.listPage(PageUtils.getStart(pageVO), pageVO.getPageSize());
        if (CollUtil.isEmpty(tasks)) {
            return PageResult.empty(taskQuery.count());
        }

        // 获得 ProcessInstance Map
        Map<String, ProcessInstance> processInstanceMap =
                processInstanceService.getProcessInstanceMap(convertSet(tasks, Task::getProcessInstanceId));
        // 获得 User Map
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertSet(processInstanceMap.values(), instance -> Long.valueOf(instance.getStartUserId())));
        // 拼接结果
        return new PageResult<>(BpmTaskConvert.INSTANCE.convertList1(tasks, processInstanceMap, userMap), taskQuery.count());
    }

    @Override
    public PageResult<BpmTaskDonePageItemRespVO> getDoneTaskPage(Long userId, BpmTaskDonePageReqVO pageVO) {
        // 查询已办任务
        // 已完成
        HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery().finished()
                // 分配给自己
                .taskAssignee(String.valueOf(userId))
                // 审批时间倒序
                .orderByHistoricTaskInstanceEndTime().desc();
        if (StrUtil.isNotBlank(pageVO.getName())) {
            taskQuery.taskNameLike("%" + pageVO.getName() + "%");
        }
        if (pageVO.getBeginCreateTime() != null) {
            taskQuery.taskCreatedAfter(pageVO.getBeginCreateTime());
        }
        if (pageVO.getEndCreateTime() != null) {
            taskQuery.taskCreatedBefore(pageVO.getEndCreateTime());
        }
        // 执行查询
        List<HistoricTaskInstance> tasks = taskQuery.listPage(PageUtils.getStart(pageVO), pageVO.getPageSize());
        if (CollUtil.isEmpty(tasks)) {
            return PageResult.empty(taskQuery.count());
        }

        // 获得 TaskExtDO Map
        List<BpmTaskExtDO> bpmTaskExtDOs =
                taskExtMapper.selectListByTaskIds(convertSet(tasks, HistoricTaskInstance::getId));
        Map<String, BpmTaskExtDO> bpmTaskExtDOMap = convertMap(bpmTaskExtDOs, BpmTaskExtDO::getTaskId);
        // 获得 ProcessInstance Map
        Map<String, HistoricProcessInstance> historicProcessInstanceMap =
                processInstanceService.getHistoricProcessInstanceMap(
                        convertSet(tasks, HistoricTaskInstance::getProcessInstanceId));
        // 获得 User Map
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(convertSet(historicProcessInstanceMap.values(), instance -> Long.valueOf(instance.getStartUserId())));
        // 拼接结果
        return new PageResult<>(BpmTaskConvert.INSTANCE.convertList2(tasks, bpmTaskExtDOMap, historicProcessInstanceMap, userMap), taskQuery.count());
    }

    @Override
    public List<Task> getTasksByProcessInstanceIds(List<String> processInstanceIds) {
        if (CollUtil.isEmpty(processInstanceIds)) {
            return Collections.emptyList();
        }
        return flowableTaskService.createTaskQuery().processInstanceIdIn(processInstanceIds).list();
    }

    @Override
    public List<BpmTaskRespVO> getTaskListByProcessInstanceId(String processInstanceId) {
        // 获得任务列表
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceStartTime().desc() // 创建时间倒序
                .list();
        if (CollUtil.isEmpty(tasks)) {
            return Collections.emptyList();
        }

        // 获得 TaskExtDO Map
        List<BpmTaskExtDO> bpmTaskExtDOs = taskExtMapper.selectListByTaskIds(convertSet(tasks, HistoricTaskInstance::getId));
        Map<String, BpmTaskExtDO> bpmTaskExtDOMap = convertMap(bpmTaskExtDOs, BpmTaskExtDO::getTaskId);
        // 获得 ProcessInstance Map
        HistoricProcessInstance processInstance = processInstanceService.getHistoricProcessInstance(processInstanceId);
        // 获得 User Map
        Set<Long> userIds = convertSet(tasks, task -> NumberUtils.parseLong(task.getAssignee()));
        userIds.add(NumberUtils.parseLong(processInstance.getStartUserId()));
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        // 获得 Dept Map
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(convertSet(userMap.values(), AdminUserRespDTO::getDeptId));

        // 拼接数据
        return BpmTaskConvert.INSTANCE.convertList3(tasks, bpmTaskExtDOMap, processInstance, userMap, deptMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveTask(Long userId, @Valid BpmTaskApproveReqVO reqVO) {
        // 校验任务存在
        Task task = checkTask(userId, reqVO.getId());
        // 校验流程实例存在
        ProcessInstance instance = processInstanceService.getProcessInstance(task.getProcessInstanceId());
        if (instance == null) {
            throw exception(PROCESS_INSTANCE_NOT_EXISTS);
        }

        // 完成任务，审批通过
        flowableTaskService.complete(task.getId(), instance.getProcessVariables());

        // 更新任务拓展表为通过
        taskExtMapper.updateByTaskId(
                new BpmTaskExtDO().setTaskId(task.getId()).setResult(BpmProcessInstanceResultEnum.APPROVE.getResult())
                        .setReason(reqVO.getReason()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectTask(Long userId, @Valid BpmTaskRejectReqVO reqVO) {
        Task task = checkTask(userId, reqVO.getId());
        // 校验流程实例存在
        ProcessInstance instance = processInstanceService.getProcessInstance(task.getProcessInstanceId());
        if (instance == null) {
            throw exception(PROCESS_INSTANCE_NOT_EXISTS);
        }

        // 更新流程实例为不通过
        processInstanceService.updateProcessInstanceExtReject(instance.getProcessInstanceId(), reqVO.getReason());

        // 更新任务拓展表为不通过
        taskExtMapper.updateByTaskId(
                new BpmTaskExtDO().setTaskId(task.getId()).setResult(BpmProcessInstanceResultEnum.REJECT.getResult())
                        .setEndTime(new Date()).setReason(reqVO.getReason()));
    }

    @Override
    public void updateTaskAssignee(Long userId, BpmTaskUpdateAssigneeReqVO reqVO) {
        // 校验任务存在
        Task task = checkTask(userId, reqVO.getId());
        // 更新负责人
        updateTaskAssignee(task.getId(), reqVO.getAssigneeUserId());
    }

    @Override
    public void updateTaskAssignee(String id, Long userId) {
        flowableTaskService.setAssignee(id, String.valueOf(userId));
    }

    /**
     * 校验任务是否存在， 并且是否是分配给自己的任务
     *
     * @param userId 用户 id
     * @param taskId task id
     */
    private Task checkTask(Long userId, String taskId) {
        Task task = getTask(taskId);
        if (task == null) {
            throw exception(TASK_COMPLETE_FAIL_NOT_EXISTS);
        }
        if (!Objects.equals(userId, NumberUtils.parseLong(task.getAssignee()))) {
            throw exception(TASK_COMPLETE_FAIL_ASSIGN_NOT_SELF);
        }
        return task;
    }

    @Override
    public void createTaskExt(Task task) {
        BpmTaskExtDO taskExtDO =
                BpmTaskConvert.INSTANCE.convert2TaskExt(task).setResult(BpmProcessInstanceResultEnum.PROCESS.getResult());
        taskExtMapper.insert(taskExtDO);
    }

    @Override
    public void updateTaskExtComplete(Task task) {
        BpmTaskExtDO taskExtDO = BpmTaskConvert.INSTANCE.convert2TaskExt(task)
                // 不设置也问题不大，因为 Complete 一般是审核通过，已经设置
                .setResult(BpmProcessInstanceResultEnum.APPROVE.getResult())
                .setEndTime(new Date());
        taskExtMapper.updateByTaskId(taskExtDO);
    }

    @Override
    public void updateTaskExtCancel(String taskId) {
        // 需要在事务提交后，才进行查询。不然查询不到历史的原因
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                // 可能只是活动，不是任务，所以查询不到
                HistoricTaskInstance task = getHistoricTask(taskId);
                if (task == null) {
                    return;
                }
                // 如果任务拓展表已经是完成的状态，则跳过
                BpmTaskExtDO taskExt = taskExtMapper.selectByTaskId(taskId);
                if (taskExt == null) {
                    log.error("[updateTaskExtCancel][taskId({}) 查找不到对应的记录，可能存在问题]", taskId);
                    return;
                }
                // 如果已经是最终的结果，则跳过
                if (BpmProcessInstanceResultEnum.isEndResult(taskExt.getResult())) {
                    log.error("[updateTaskExtCancel][taskId({}) 处于结果({})，无需进行更新]", taskId, taskExt.getResult());
                    return;
                }

                // 更新任务
                taskExtMapper.updateById(new BpmTaskExtDO().setId(taskExt.getId()).setResult(BpmProcessInstanceResultEnum.CANCEL.getResult())
                        .setEndTime(new Date()).setReason(BpmProcessInstanceDeleteReasonEnum.translateReason(task.getDeleteReason())));
            }

        });
    }

    @Override
    public void updateTaskExtAssign(Task task) {
        BpmTaskExtDO taskExtDO =
                new BpmTaskExtDO().setAssigneeUserId(NumberUtils.parseLong(task.getAssignee())).setTaskId(task.getId());
        taskExtMapper.updateByTaskId(taskExtDO);
        // 发送通知。在事务提交时，批量执行操作，所以直接查询会无法查询到 ProcessInstance，所以这里是通过监听事务的提交来实现。
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ProcessInstance processInstance =
                        processInstanceService.getProcessInstance(task.getProcessInstanceId());
                // TODO: 2022/8/1
                AdminUserRespDTO startUser = adminUserApi.getUser(Long.valueOf(processInstance.getStartUserId()));
                messageService.sendMessageWhenTaskAssigned(BpmTaskConvert.INSTANCE.convert(processInstance, startUser, task));
            }
        });
    }

    @Override
    public List<FlowNodeVo> getBackNodesByProcessInstanceId(String processInstanceId, String taskId) {
        List<FlowNodeVo> backNods = new ArrayList<>();
        TaskEntity taskEntity = (TaskEntity) flowableTaskService.createTaskQuery().taskId(taskId).singleResult();
        String currActId = taskEntity.getTaskDefinitionKey();
        //获取运行节点表中usertask
        String sql = "select t.* from act_ru_actinst t where t.ACT_TYPE_ = 'userTask' " +
                " and t.PROC_INST_ID_=#{processInstanceId} and t.END_TIME_ is not null ";
        List<ActivityInstance> activityInstances = runtimeService.createNativeActivityInstanceQuery().sql(sql)
                .parameter("processInstanceId", processInstanceId)
                .list();
        //获取运行节点表的parallelGateway节点并出重
        sql = "SELECT t.ID_, t.REV_,t.PROC_DEF_ID_,t.PROC_INST_ID_,t.EXECUTION_ID_,t.ACT_ID_, t.TASK_ID_, t.CALL_PROC_INST_ID_, t.ACT_NAME_, t.ACT_TYPE_, " +
                " t.ASSIGNEE_, t.START_TIME_, max(t.END_TIME_) as END_TIME_, t.DURATION_, t.DELETE_REASON_, t.TENANT_ID_" +
                " FROM  act_ru_actinst t WHERE t.ACT_TYPE_ = 'parallelGateway' AND t.PROC_INST_ID_ = #{processInstanceId} and t.END_TIME_ is not null" +
                " and t.ACT_ID_ <> #{actId} GROUP BY t.act_id_";
        List<ActivityInstance> parallelGatewaies = runtimeService.createNativeActivityInstanceQuery().sql(sql)
                .parameter("processInstanceId", processInstanceId)
                .parameter("actId", currActId)
                .list();
        //排序
        if (CollectionUtils.isNotEmpty(parallelGatewaies)) {
            activityInstances.addAll(parallelGatewaies);
            activityInstances.sort(Comparator.comparing(ActivityInstance::getEndTime));
        }
        //分组节点
        int count = 0;
        Map<ActivityInstance, List<ActivityInstance>> parallelGatewayUserTasks = new HashMap<>();
        List<ActivityInstance> userTasks = new ArrayList<>();
        ActivityInstance currActivityInstance = null;
        for (ActivityInstance activityInstance : activityInstances) {
            if (BpmnXMLConstants.ELEMENT_GATEWAY_PARALLEL.equals(activityInstance.getActivityType())) {
                count++;
                if (count % 2 != 0) {
                    List<ActivityInstance> datas = new ArrayList<>();
                    currActivityInstance = activityInstance;
                    parallelGatewayUserTasks.put(currActivityInstance, datas);
                }
            }
            if (BpmnXMLConstants.ELEMENT_TASK_USER.equals(activityInstance.getActivityType())) {
                if (count % 2 == 0) {
                    userTasks.add(activityInstance);
                } else {
                    if (parallelGatewayUserTasks.containsKey(currActivityInstance)) {
                        parallelGatewayUserTasks.get(currActivityInstance).add(activityInstance);
                    }
                }
            }
        }
        //组装人员名称
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).finished().list();
        Map<String, List<HistoricTaskInstance>> taskInstanceMap = new HashMap<>();
        List<String> userCodes = new ArrayList<>();
        historicTaskInstances.forEach(historicTaskInstance -> {
            userCodes.add(historicTaskInstance.getAssignee());
            String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
            if (taskInstanceMap.containsKey(historicTaskInstance.getTaskDefinitionKey())) {
                taskInstanceMap.get(taskDefinitionKey).add(historicTaskInstance);
            } else {
                List<HistoricTaskInstance> tasks = new ArrayList<>();
                tasks.add(historicTaskInstance);
                taskInstanceMap.put(taskDefinitionKey, tasks);
            }
        });
        //组装usertask的数据
        List<User> userList = identityService.createUserQuery().userIds(userCodes).list();
        Map<String, String> activityIdUserNames = this.getApplyers(processInstanceId, userList, taskInstanceMap);
        if (CollectionUtils.isNotEmpty(userTasks)) {
            userTasks.forEach(activityInstance -> {
                FlowNodeVo node = new FlowNodeVo();
                node.setNodeId(activityInstance.getActivityId());
                node.setNodeName(activityInstance.getActivityName());
                node.setEndTime(activityInstance.getEndTime());
                node.setUserName(activityIdUserNames.get(activityInstance.getActivityId()));
                backNods.add(node);
            });
        }
        //组装会签节点数据
        if (MapUtils.isNotEmpty(taskInstanceMap)) {
            parallelGatewayUserTasks.forEach((activity, activities) -> {
                FlowNodeVo node = new FlowNodeVo();
                node.setNodeId(activity.getActivityId());
                node.setEndTime(activity.getEndTime());
                StringBuffer nodeNames = new StringBuffer("会签:");
                StringBuffer userNames = new StringBuffer("审批人员:");
                if (CollectionUtils.isNotEmpty(activities)) {
                    activities.forEach(activityInstance -> {
                        nodeNames.append(activityInstance.getActivityName()).append(",");
                        userNames.append(activityIdUserNames.get(activityInstance.getActivityId())).append(",");
                    });
                    node.setNodeName(nodeNames.toString());
                    node.setUserName(userNames.toString());
                    backNods.add(node);
                }
            });
        }
        //去重合并
        List<FlowNodeVo> datas = backNods.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(nodeVo -> nodeVo.getNodeId()))), ArrayList::new));

        //排序
        datas.sort(Comparator.comparing(FlowNodeVo::getEndTime));
        return datas;
    }

    @Override
    public Boolean backToStepTask(BackTaskVo backTaskVo) {
        TaskEntity taskEntity = (TaskEntity) flowableTaskService.createTaskQuery().taskId(backTaskVo.getTaskId()).singleResult();
        //TODO 添加驳回功能
        //1.把当前的节点设置为空
        if (taskEntity != null) {
            //2.设置审批人
            taskEntity.setAssignee(backTaskVo.getUserCode());
            flowableTaskService.saveTask(taskEntity);
            //3.添加驳回意见
            //4.处理提交人节点
            FlowNode distActivity = modelService.findFlowNodeByActivityId(taskEntity.getProcessDefinitionId(), backTaskVo.getDistFlowElementId());
            if (distActivity != null) {
                if ("提交人节点名称".equals(distActivity.getName())) {
                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(taskEntity.getProcessInstanceId()).singleResult();
                    runtimeService.setVariable(backTaskVo.getProcessInstanceId(), "提交人的变量名称", processInstance.getStartUserId());
                }
            }
            //5.删除节点
            //6.判断节点是不是子流程内部的节点
            if (true) {
                //6.1 子流程内部驳回
            } else {
                //6.2 普通驳回
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private Task getTask(String id) {
        return flowableTaskService.createTaskQuery().taskId(id).singleResult();
    }

    private HistoricTaskInstance getHistoricTask(String id) {
        return historyService.createHistoricTaskInstanceQuery().taskId(id).singleResult();
    }

    private Map<String, String> getApplyers(String processInstanceId, List<User> userList, Map<String, List<HistoricTaskInstance>> taskInstanceMap) {
        Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));
        Map<String, String> applyMap = new HashMap<>();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        taskInstanceMap.forEach((activityId, taskInstances) -> {
            StringBuffer applyers = new StringBuffer();
            StringBuffer finalApplyers = applyers;
            taskInstances.forEach(taskInstance -> {
                if (!taskInstance.getName().equals(BpmTaskRuleScriptEnum.START_USER.getDesc())) {
                    User user = userMap.get(taskInstance.getAssignee());
                    if (user != null) {
                        if (StringUtils.indexOf(finalApplyers.toString(), user.getDisplayName()) == -1) {
                            finalApplyers.append(user.getDisplayName()).append(",");
                        }
                    }
                } else {
                    String startUserId = processInstance.getStartUserId();
                    User user = identityService.createUserQuery().userId(startUserId).singleResult();
                    if (user != null) {
                        finalApplyers.append(user.getDisplayName()).append(",");
                    }
                }
            });
            if (applyers.length() > 0) {
                applyers = applyers.deleteCharAt(applyers.length() - 1);
            }
            applyMap.put(activityId, applyers.toString());
        });
        return applyMap;
    }

}
