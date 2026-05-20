package com.ruoyi.workflow.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.common.service.FlowCommonService;
import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.service.IFlowInstanceService;
import com.ruoyi.flowable.service.IFlowMonitorService;
import com.ruoyi.flowable.service.IFlowTaskService;
import com.ruoyi.im.chat.enums.BusinessMessageType;
import com.ruoyi.message.domain.MessageNotice;
import com.ruoyi.message.service.IBusinessSystemMessageService;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import com.ruoyi.sms.domain.SmsAsyncDTO;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateMessageNotice;
import com.ruoyi.template.enums.MessageNoticeTypeEnum;
import com.ruoyi.template.service.ITemplateMessageNoticeService;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.enums.TodoHandleTypeEnum;
import com.ruoyi.todo.enums.TodoTypeEnum;
import com.ruoyi.todo.service.IDoneService;
import com.ruoyi.todo.service.ITodoService;
import com.ruoyi.workflow.domain.ReceTemplate;
import com.ruoyi.workflow.domain.WorkflowRecycle;
import com.ruoyi.workflow.module.FlowRecordParam;
import com.ruoyi.workflow.service.IFlowHandleService;
import com.ruoyi.workflow.service.IReceTemplateService;
import com.ruoyi.workflow.service.IWorkflowRecycleService;
import com.ruoyi.worksetting.api.WorkflowSettingService;
import com.ruoyi.worksetting.domain.EntrustResult;
import com.ruoyi.worksetting.domain.WorkflowSecretaryRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程处理Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class FlowHandleServiceImpl implements IFlowHandleService {

    @Autowired
    private ITemplateService templateService;
    @Autowired
    private IFlowInstanceService flowInstanceService;
    @Autowired
    private IReceTemplateService receTemplateService;
    @Autowired
    private IFlowTaskService flowTaskService;
    @Autowired
    private ITodoService todoService;
    @Autowired
    private IDoneService doneService;
    @Autowired
    private IWorkflowRecycleService workflowRecycleService;
    @Autowired
    private IFlowMonitorService flowMonitorService;
    @Autowired
    private FlowCommonService flowCommonService;
    @Autowired
    private IBusinessSystemMessageService systemMessageService;
    @Autowired
    private ISyncPush syncPush;
    @Autowired
    private WorkflowSettingService workflowSettingService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITemplateMessageNoticeService templateMessageNoticeService;

    /**
     * 启动流程
     *
     * @param flowTaskVo 流程对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public FlowTaskDto startFlow(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTemplateId())) {
            throw new BaseException("参数错误");
        }
        Template template = templateService.getTemplateById(flowTaskVo.getTemplateId());
        if (StringUtils.isBlank(template.getDefKey())) {
            throw new BaseException("流程模板未配置");
        }
        //插入最近使用模板记录
        saveReceTemplate(template.getId());
        Map<String, Object> variables = new HashMap<>(16);
        if (flowTaskVo.getVariables() != null) {
            variables.putAll(flowTaskVo.getVariables());
        }
        //启动流程
        String procInstId = flowInstanceService.startProcessInstanceByKey(template.getDefKey(), variables);
        if (StringUtils.isBlank(procInstId)) {
            throw new BaseException("流程启动失败！");
        }

        List<FlowTaskDto> currentTaskList = flowCommonService.getCurrentTaskList(procInstId);
        if (CollectionUtils.isEmpty(currentTaskList)) {
            throw new BaseException("流程当前任务为空！");
        }
        return currentTaskList.get(0);
    }

    /**
     * 提交流程任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void completeTask(FlowTaskVo flowTaskVo, String createId) {
        validateCompleteTaskParam(flowTaskVo);
        // 从流程变量中处理委托关系
        handleEntrustByVariable(flowTaskVo);
        // 完成任务，新增审批意见
        flowTaskService.complete(flowTaskVo);
        // 处理委托，特别处理下个环节处理人为固定人员的情况
        handleEntrustAfterNextTask(flowTaskVo);
        // 生成下一个环节待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, createId, false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, createId, true);
    }

    /**
     * 更新催办（指定某个审批，非当前环节所有人）
     *
     * @param flowTaskVo
     */
    @Override
    public int urge(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            return 0;
        }
        List<Todo> list = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        List<String> ids = list.stream().map(Todo::getId).collect(Collectors.toList());
        todoService.updateBatchUrge(Constants.YES_VALUE, ids);
        return 1;
    }

    /**
     * 撤回流程
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revokeProcess(FlowTaskVo flowTaskVo) {
        validateRevokeParam(flowTaskVo);
        flowTaskService.revokeProcess(flowTaskVo, false);
        flowTaskService.deleteTaskByParallelGateway(flowTaskVo);
        // 获取撤回任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, SecurityUtils.getUserId(), false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, SecurityUtils.getUserId(), true);
    }

    /**
     * 驳回任务
     *
     * @param flowTaskVo 流程对象
     * @param createId   创建人ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void taskReject(FlowTaskVo flowTaskVo, String createId) {
        validateRejectParam(flowTaskVo);
        flowTaskService.taskReject(flowTaskVo);
        flowTaskService.deleteTaskByParallelGateway(flowTaskVo);
        // 获取驳回任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, createId, false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, createId, true);
    }

    /**
     * 抄送
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void copyTask(FlowTaskVo flowTaskVo) {
        validateCopyParam(flowTaskVo);
        // 处理委托
        handleEntrustByAssignee(flowTaskVo);
        // 生成抄送阅办
        List<Todo> copyTaskTodos = todoService.createCopyTaskTodo(flowTaskVo);
        handleNextTodos(copyTaskTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(copyTaskTodos, SecurityUtils.getUserId(), true);
    }

    /**
     * 设置抄送阅办完成
     *
     * @param id 阅办ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readCopyTodo(String id) {
        Todo todo = getReadCopyTodo(id);
        todoService.updateTodo(todo);
        handleOtherOperation(null, SecurityUtils.getUserId(), false);
    }

    /**
     * 退回任务
     *
     * @param flowTaskVo 流程对象
     * @param createId   创建人ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void taskReturn(FlowTaskVo flowTaskVo, String createId) {
        validateReturnParam(flowTaskVo);
        flowTaskService.taskReturn(flowTaskVo);
        flowTaskService.deleteTaskByParallelGateway(flowTaskVo);
        // 获取退回任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, createId, false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, createId, true);
    }

    /**
     * 终止流程
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void terminateProcess(FlowTaskVo flowTaskVo) {
        Todo todo = getTerminateProcessTodo(flowTaskVo);
        flowTaskService.terminateProcess(flowTaskVo);
        todoService.deleteTodoByProcInsId(todo.getProcInstId());
        // 放入回收站
        workflowRecycleService.saveWorkflowRecycle(buildWorkflowRecycle(todo));
        updateMyDraft(flowTaskVo);
    }

    /**
     * 管理员终止流程
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void terminateProcessByAdmin(FlowTaskVo flowTaskVo) {
        if (StringUtils.isNotBlank(flowTaskVo.getTaskId())) {
            // 终止单个任务
            List<String> taskIds = Arrays.stream(flowTaskVo.getTaskId()
                    .split(Constants.COMMA)).collect(Collectors.toList());
            flowTaskService.deleteTask(flowTaskVo.getProcInsId(), taskIds);
            todoService.deleteTodoByTaskIds(taskIds);
        } else if (StringUtils.isNotBlank(flowTaskVo.getProcInsId())) {
            // 终止所有任务
            flowTaskService.terminateProcess(flowTaskVo);
            List<Todo> todos = todoService.listTodoByProcInsId(flowTaskVo.getProcInsId());
            if (CollectionUtils.isEmpty(todos)) {
                return;
            }
            todoService.deleteTodoByProcInsId(flowTaskVo.getProcInsId());
            Todo todo = todos.get(0);
            workflowRecycleService.saveWorkflowRecycle(buildWorkflowRecycle(todo));
            setFlowTaskVoByTodo(todo, flowTaskVo);
            updateMyDraft(flowTaskVo);
        }
    }

    /**
     * 管理员取回任务
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnFinishTaskByAdmin(FlowTaskVo flowTaskVo) {
        if (StringUtils.isNotBlank(flowTaskVo.getTaskId())) {
            // 取回单个任务
            FlowTaskDto flowTask = flowTaskService.getFlowTask(flowTaskVo.getTaskId());
            checkReturnFinishTask(flowTask);
            List<String> taskIds = Arrays.stream(flowTaskVo.getTaskId().split(Constants.COMMA))
                    .collect(Collectors.toList());
            todoService.createReturnFinishTaskTodo(flowTaskVo, taskIds, SecurityUtils.getUserId());
        } else if (StringUtils.isNotBlank(flowTaskVo.getProcInsId())) {
            // 取回所有任务
            checkLastDone(flowTaskVo);
            flowTaskVo.setHandleType(TodoHandleTypeEnum.REVOKE.getCode());
            flowTaskService.revokeProcess(flowTaskVo, true);
            flowTaskService.deleteTaskByParallelGateway(flowTaskVo);
            List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, SecurityUtils.getUserId(), false);
            handleNextTodos(nextTodos, flowTaskVo);
        }
    }

    /**
     * 取回提交
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnSubmit(FlowTaskVo flowTaskVo) {
        //更新意见
        flowTaskService.updateTaskComment(flowTaskVo);
        //删除待办，生成已办
        todoService.deleteReturnTodo(flowTaskVo.getTodoId());
    }

    /**
     * 检查提交条件
     *
     * @param flowTaskVo 流程对象
     * @return Todo
     */
    @Override
    public Boolean checkCompleteCondition(FlowTaskVo flowTaskVo) {
        return flowTaskService.checkCompleteCondition(flowTaskVo);
    }

    /**
     * 检查退回条件
     *
     * @param flowTaskVo 流程对象
     * @return Todo
     */
    @Override
    public Boolean checkReturnCondition(FlowTaskVo flowTaskVo) {
        return flowTaskService.checkReturnCondition(flowTaskVo);
    }

    /**
     * 检查驳回条件
     *
     * @param flowTaskVo 流程对象
     * @return Boolean
     */
    @Override
    public Boolean checkRejectCondition(FlowTaskVo flowTaskVo) {
        return flowTaskService.checkRejectCondition(flowTaskVo);
    }

    /**
     * 跳转流程
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void jumpActivity(FlowTaskVo flowTaskVo) {
        validateJumpParam(flowTaskVo);
        handleEntrustByVariable(flowTaskVo);
        flowMonitorService.jumpActivity(flowTaskVo);
        handleEntrustAfterNextTask(flowTaskVo);
        // 获取任务，插入新待办
        List<Todo> jumpTodos = todoService.createJumpTodo(flowTaskVo, SecurityUtils.getUserId());
        handleNextTodos(jumpTodos, flowTaskVo);
    }

    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo 流程对象
     * @return List<UserTask>
     */
    @Override
    public List<UserTask> findReturnTaskList(FlowTaskVo flowTaskVo) {
        return flowTaskService.findReturnTaskList(flowTaskVo);
    }

    /**
     * 认领任务
     *
     * @param flowTaskVo 流程对象
     * @param createId   创建人ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claim(FlowTaskVo flowTaskVo, String createId) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        flowTaskService.claim(flowTaskVo);
        // 删除其他人待办(逻辑删除)
        deleteClaimOtherTodo(flowTaskVo, createId);
    }

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo 流程对象
     * @param createId   创建人
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unClaim(FlowTaskVo flowTaskVo, String createId) {
        validateUnClaimParam(flowTaskVo);
        flowTaskService.unClaim(flowTaskVo);
        // 获取任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, createId, true);
    }

    /**
     * 委派任务
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delegateTask(FlowTaskVo flowTaskVo) {
        validateDelegateParam(flowTaskVo);
        Todo delegateTask = getDelegateTask(flowTaskVo);
        // 如果有多个候选人，则把当前候选人设置为处理人
        flowTaskService.setAssignee(flowTaskVo, SecurityUtils.getUserId());
        // 处理委托
        handleEntrustByAssignee(flowTaskVo);
        flowTaskService.delegateTask(flowTaskVo);
        // 获取委派任务，插入新待办
        List<Todo> nextTodos = todoService.createDelegateTaskTodo(flowTaskVo, delegateTask);
        // 处理其他操作
        handleOtherOperation(nextTodos, SecurityUtils.getUserId(), true);
    }

    /**
     * 转办任务
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignTask(FlowTaskVo flowTaskVo) {
        validateAssignParam(flowTaskVo);
        // 处理委托
        handleEntrustByAssignee(flowTaskVo);
        flowTaskService.assignTask(flowTaskVo);
        // 获取转办任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, SecurityUtils.getUserId(), true);
    }

    /**
     * 多实例加签
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMultiInstanceExecution(FlowTaskVo flowTaskVo) {
        // 校验加签入参
        validateAddMultiParam(flowTaskVo);
        // 处理委托
        handleEntrustByAssignee(flowTaskVo);
        // 加签
        flowTaskService.addMultiInstanceExecution(flowTaskVo);
        String createId = SecurityUtils.getUserId();
        // 获取多实例任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, createId, false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 处理其他操作
        handleOtherOperation(nextTodos, createId, true);
    }

    /**
     * 多实例减签
     *
     * @param flowTaskVo 流程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMultiInstanceExecution(FlowTaskVo flowTaskVo) {
        validateDeleteMultiParam(flowTaskVo);
        flowTaskService.deleteMultiInstanceExecution(flowTaskVo);
        String createId = SecurityUtils.getUserId();
        // 获取多实例任务，插入新待办
        List<Todo> nextTodos = todoService.createNextTodo(flowTaskVo, createId, false);
        handleNextTodos(nextTodos, flowTaskVo);
        // 发送待办列表更新消息
        String[] assignees = flowTaskVo.getAssignee().split(Constants.COMMA);
        List<String> assigneeList = new ArrayList<>();
        Collections.addAll(assigneeList, assignees);
        sendTodoListUpdateMsg(createId, assigneeList);
    }

    /**
     * 获取减签的任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @Override
    public List<FlowTaskDto> getDeleteMultiTasks(FlowTaskVo flowTaskVo) {
        List<Todo> todos = todoService.getCurrentAddMultiTodos(flowTaskVo);
        List<String> taskIds = todos.stream().map(Todo::getTaskId).collect(Collectors.toList());
        return flowTaskService.getTaskList(flowTaskVo.getProcInsId(), taskIds);
    }

    /**
     * 获取下一个流程节点
     *
     * @param flowTaskVo 流程对象
     * @return
     */
    @Override
    public List<FlowNextDto> getNextFlowNode(FlowTaskVo flowTaskVo) {
        return flowTaskService.getNextFlowNode(flowTaskVo);
    }

    /**
     * 获取流程变量
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public Map<String, Object> processVariables(String taskId) {
        return flowTaskService.processVariables(taskId);
    }

    /**
     * 获取流程图
     *
     * @param processId 流程定义ID
     * @param response 响应
     */
    @Override
    public void diagram(String processId, HttpServletResponse response) {
        InputStream inputStream = flowTaskService.diagram(processId);
        OutputStream os = null;
        BufferedImage image;
        try {
            image = ImageIO.read(inputStream);
            response.setContentType("image/png");
            os = response.getOutputStream();
            if (image != null) {
                ImageIO.write(image, "png", os);
            }
        } catch (Exception e) {
            log.error("获取流程图异常：", e);
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                log.error("关闭流异常：", e);
            }
        }
    }

    /**
     * 获取流程XML和节点信息
     *
     * @param procInsId 流程实例ID
     * @param deployId  部署ID
     * @return
     */
    @Override
    public Map<String, Object> flowXmlAndNode(String procInsId, String deployId) {
        return flowTaskService.flowXmlAndNode(procInsId, deployId);
    }

    /**
     * 获取流程任务
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public FlowTaskDto getFlowTask(String taskId) {
        return flowTaskService.getFlowTask(taskId);
    }

    /**
     * 获取历史流程任务
     *
     * @param taskId 任务ID
     * @return
     */
    @Override
    public FlowTaskDto getHistoryFlowTask(String taskId) {
        return flowTaskService.getHistoryFlowTask(taskId);
    }

    /**
     * 获取流程记录
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flowRecord(FlowRecordParam param) {
        return flowTaskService.flowHistoryRecord(param.getProcInsId(), param.getActId(), param.getPageNum(), param.getPageSize());
    }

    /**
     * 获取流程评论
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flowCmts(FlowRecordParam param) {
        return flowTaskService.flowCmts(param.getProcInsId(), param.getActId(), param.getPageNum(), param.getPageSize());
    }

    /**
     * 设置默认的待办信息
     *
     * @param todo       待办
     * @param flowTaskVo 请求参数
     */
    private void setFlowTaskVoByTodo(Todo todo, FlowTaskVo flowTaskVo) {
        flowTaskVo.setTitle(todo.getTitle());
        flowTaskVo.setBusinessId(todo.getBusinessId());
        flowTaskVo.setTemplateId(todo.getTemplateId());
        flowTaskVo.setTemplateName(todo.getTemplateName());
        flowTaskVo.setTemplateType(todo.getTemplateType());
        flowTaskVo.setUrgencyStatus(todo.getUrgencyStatus());
        flowTaskVo.setType(todo.getType());
        flowTaskVo.setHandleType(todo.getHandleType());
        if (StringUtils.isBlank(flowTaskVo.getUserId())) {
            flowTaskVo.setUserId(todo.getCurHandler());
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            flowTaskVo.setProcInsId(todo.getProcInstId());
        }
    }

    /**
     * 设置默认的已办信息
     *
     * @param done       已办
     * @param flowTaskVo 请求参数
     */
    private void setDefaultDone(Done done, FlowTaskVo flowTaskVo) {
        if (flowTaskVo.getUserId() == null) {
            flowTaskVo.setUserId(done.getHandler());
        }
        flowTaskVo.setBusinessId(done.getBusinessId());
        flowTaskVo.setTitle(done.getTitle());
        flowTaskVo.setType(done.getType());
        flowTaskVo.setTemplateId(done.getTemplateId());
        flowTaskVo.setTemplateName(done.getTemplateName());
        flowTaskVo.setTemplateType(done.getTemplateType());
        flowTaskVo.setProcInsId(done.getProcInstId());
        flowTaskVo.setTaskId(done.getTaskId());
    }

    /**
     * 构建回收站类
     *
     * @param todo
     * @return
     */
    private WorkflowRecycle buildWorkflowRecycle(Todo todo) {
        WorkflowRecycle recycle = new WorkflowRecycle();
        recycle.setTitle(todo.getTitle());
        recycle.setTemplateId(todo.getTemplateId());
        recycle.setTemplateName(todo.getTemplateName());
        recycle.setProcInstId(todo.getProcInstId());
        recycle.setTaskId(todo.getTaskId());
        recycle.setBusinessId(todo.getBusinessId());
        return recycle;
    }

    /**
     * 处理其他操作
     *
     * @param nextTodos       下一个待办列表
     * @param createId        创建人ID
     * @param isMessageNotice 是否消息通知
     */
    private void handleOtherOperation(List<Todo> nextTodos, String createId, Boolean isMessageNotice) {
        try {
            //发送更新待办列表的webSocket消息通知
            List<String> recvIds = CollectionUtils.isNotEmpty(nextTodos) ? nextTodos.stream().map(Todo::getCurHandler).collect(Collectors.toList()) : Collections.emptyList();
            systemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_LIST_REFRESH.getCode(), createId, recvIds, "您的待办任务已更新！");
            if (isMessageNotice && CollectionUtils.isNotEmpty(recvIds)) {
                handleMessageNotice(nextTodos.get(0), createId, recvIds);
            }
        } catch (Exception e) {
            log.error("处理其他操作失败", e);
        }
    }

    /**
     * 处理消息通知
     *
     * @param createId   创建人ID
     * @param recvIds    接收人ID列表
     */
    private void handleMessageNotice(Todo todo, String createId, List<String> recvIds) {
        String templateId = todo.getTemplateId();
        Template template = templateService.getTemplateById(templateId);
        if (template != null && WhetherStatus.YES.getCode().equals(template.getMessageNoticeFlag())) {
            TemplateMessageNotice templateMessageNotice = templateMessageNoticeService.getByTemplateId(template.getId());
            if (templateMessageNotice == null) {
                return;
            }
            MessageNoticeTypeEnum noticeType = MessageNoticeTypeEnum.getByCode(templateMessageNotice.getType());
            // 目前实现了短信、站内通知，其他可自行扩展
            switch(noticeType) {
                case SMS:
                    Map<String, String> params = new HashMap<>();
                    // 目前配置的短信模板只包含了title，如需其他，请自行扩展
                    params.put("title", todo.getTitle());
                    SmsAsyncDTO smsAsyncDTO = SmsAsyncDTO.builder()
                            .recIds(recvIds)
                            .params(params)
                            .templateCode(templateMessageNotice.getMsgTemplate())
                            .build();
                    syncPush.push(QueueEnum.ASYNC_SMS_QUEUE.getConsumerBeanName(), JSON.toJSONString(smsAsyncDTO), QueueEnum.ASYNC_SMS_QUEUE.getQueueName(), createId);
                    break;
                case SYSTEM_MSG:
                    MessageNotice messageNotice = MessageNotice.builder()
                            .senderId(createId)
                            .recvIds(recvIds)
                            .templateId(templateId)
                            .msgContent("您有新的待办，请查收！")
                            .build();
                    syncPush.push(QueueEnum.ASYNC_MESSAGE_QUEUE.getConsumerBeanName(), JSON.toJSONString(messageNotice), QueueEnum.ASYNC_MESSAGE_QUEUE.getQueueName(), createId);
                    break;
                default:
                    log.error("消息通知类型不存在，消息类型：{}", noticeType);
            }
        }
    }

    /**
     * 异步处理我起草的流程
     */
    private void updateMyDraft(FlowTaskVo flowTaskVo) {
        JSONObject formDataObj = new JSONObject();
        formDataObj.put("businessId", flowTaskVo.getBusinessId());
        formDataObj.put("title", flowTaskVo.getTitle());
        formDataObj.put("status", "3");
        syncPush.push(QueueEnum.ASYNC_MY_DRAFT_QUEUE.getConsumerBeanName(), JSON.toJSONString(formDataObj), QueueEnum.ASYNC_MY_DRAFT_QUEUE.getQueueName());
    }

    /**
     * 处理环节待办，如果存在工作联系人，则需要进行替换
     *
     * @param nextTodos
     */
    private void handleNextTodos(List<Todo> nextTodos, FlowTaskVo flowTaskVo) {
        if (CollectionUtils.isEmpty(nextTodos)) {
            return;
        }
        List<String> userIds = nextTodos.stream().map(Todo::getCurHandler).collect(Collectors.toList());
        Map<String, String> secretaryIdsMap = workflowSettingService.handleSecretary(userIds);

        Map<String, List<SysUser>> secretaryListMap = new HashMap<>();
        if (!secretaryIdsMap.isEmpty()) {
            List<String> secretaryIds = secretaryIdsMap.entrySet().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            List<SysUser> secretaries = sysUserService.selectByUserIds(secretaryIds);
            secretaryListMap = secretaries.stream().
                    collect(Collectors.groupingBy(SysUser::getUserId));
        }

        List<WorkflowSecretaryRecord> secretaryRecords = new ArrayList<>();
        Map<String, String> entrustIdMap = flowTaskVo.getEntrustIdMap();
        for (Todo todo : nextTodos) {
            if (isHasEntrustRelationship(entrustIdMap, todo.getCurHandler())) {
                todo.setHandleType(TodoHandleTypeEnum.ENTRUST.getCode());
            }
            if (!secretaryIdsMap.containsKey(todo.getCurHandler())) {
                continue;
            }
            String secretaryId = secretaryIdsMap.get(todo.getCurHandler());
            secretaryRecords.add(buildSecretaryRecord(todo, secretaryId));
            todo.setCurHandler(secretaryId);
            todo.setCurHandlerName(secretaryListMap.get(secretaryId).get(0).getNickName());
            todo.setHandleType(TodoHandleTypeEnum.SECRETARY.getCode());
        }
        todoService.saveBatch(nextTodos);
        if (CollectionUtils.isNotEmpty(secretaryRecords)) {
            workflowSettingService.saveBatchSecretaryRecordList(secretaryRecords);
        }
    }

    /**
     * 从流程变量中处理委托关系
     * 注意：如果多个候选人的被委托人是同一个人，或者其中有候选人的被委托人是候选人，那么flowable流程会去重，终于只生成一个任务
     *
     * @param flowTaskVo 流程参数
     */
    private void handleEntrustByVariable(FlowTaskVo flowTaskVo) {
        flowTaskVo.setEntrustIdMap(new HashMap<>());
        for (Map.Entry<String, Object> entry : flowTaskVo.getVariables().entrySet()) {
            if (!entry.getKey().contains(ProcessConstants.PROCESS_HANDLER_SUFFIX)
                    && !entry.getKey().contains(ProcessConstants.PARALLEL_GATEWAY_INITIATOR)
                    && !entry.getKey().contains(ProcessConstants.FIXED_TASK_SUFFIX)) {
                continue;
            }
            EntrustResult entrustResult = workflowSettingService.handleEntrust(flowTaskVo.getTemplateId(), entry.getValue());
            if (CollectionUtils.isEmpty(entrustResult.getEntrustIds())) {
                continue;
            }
            entry.setValue(StringUtils.join(entrustResult.getEntrustIds(), Constants.COMMA));
            flowTaskVo.getEntrustIdMap().putAll(entrustResult.getEntrustIdMap());
        }
    }

    /**
     * 提交任务后处理委托，特别处理下个环节处理人为固定人员的情况
     *
     * @param flowTaskVo 流程参数
     */
    private void handleEntrustAfterNextTask(FlowTaskVo flowTaskVo) {
        for (Map.Entry<String, Object> entry : flowTaskVo.getVariables().entrySet()) {
            if (!entry.getKey().contains(ProcessConstants.FIXED_TASK_SUFFIX)) {
                continue;
            }
            flowTaskService.handleFixedAssigneeByEntrust(flowTaskVo);
        }
    }

    /**
     * 处理当前工作联系人/秘书的审批人
     *
     * @param flowTaskVo
     */
    private void handleSecretaryAssignee(FlowTaskVo flowTaskVo) {
        List<WorkflowSecretaryRecord> secretaryRecords = workflowSettingService.getWorkflowSecretaryRecordList(flowTaskVo.getProcInsId(), flowTaskVo.getTaskId(), flowTaskVo.getTodoId(), flowTaskVo.getUserId());
        if (CollectionUtils.isEmpty(secretaryRecords)) {
            return;
        }
        WorkflowSecretaryRecord secretaryRecord = secretaryRecords.get(0);
        flowTaskVo.setUserId(secretaryRecord.getLeaderId());
    }

    /**
     * 构建工作联系人/秘书记录
     *
     * @param todo
     * @param secretaryId
     * @return
     */
    private WorkflowSecretaryRecord buildSecretaryRecord(Todo todo, String secretaryId) {
        WorkflowSecretaryRecord secretaryRecord = new WorkflowSecretaryRecord();
        secretaryRecord.setId(IdUtils.fastSimpleUUID());
        secretaryRecord.setProcInstId(todo.getProcInstId());
        secretaryRecord.setTaskId(todo.getTaskId());
        secretaryRecord.setTodoId(todo.getId());
        secretaryRecord.setLeaderId(todo.getCurHandler());
        secretaryRecord.setSecretaryId(secretaryId);
        secretaryRecord.setCreateId(todo.getCreateId());
        secretaryRecord.setCreateTime(DateUtils.getNowDate());
        return secretaryRecord;
    }

    /**
     * 处理委托
     *
     * @param flowTaskVo
     */
    private void handleEntrustByAssignee(FlowTaskVo flowTaskVo) {
        String[] entrustIds = StringUtils.split(flowTaskVo.getAssignee(), Constants.COMMA);
        EntrustResult entrustResult = workflowSettingService.handleEntrust(flowTaskVo.getTemplateId(), entrustIds);
        if (CollectionUtils.isEmpty(entrustResult.getEntrustIds())) {
            return;
        }
        flowTaskVo.setAssignee(StringUtils.join(entrustResult.getEntrustIds(), Constants.COMMA));
        flowTaskVo.setEntrustIdMap(entrustResult.getEntrustIdMap());
    }

    /**
     * 判断是否存在委托关系
     *
     * @param entrustIdMap
     * @param curHandler
     * @return
     */
    private boolean isHasEntrustRelationship(Map<String, String> entrustIdMap, String curHandler) {
        if (MapUtils.isEmpty(entrustIdMap)) {
            return false;
        }
        return entrustIdMap.containsKey(curHandler) && !Objects.equals(curHandler, entrustIdMap.get(curHandler));
    }

    /**
     * 校验完成任务的参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateCompleteTaskParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (TodoHandleTypeEnum.SECRETARY.getCode().equals(flowTaskVo.getHandleType())) {
            handleSecretaryAssignee(flowTaskVo);
        }
        flowTaskVo.setType(TodoTypeEnum.TODO.getCode());
        flowTaskVo.setHandleType(TodoHandleTypeEnum.AUDIT.getCode());
    }

    /**
     * 校验取回参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateRevokeParam(FlowTaskVo flowTaskVo) {
        Done done = doneService.getDoneById(flowTaskVo.getDoneId());
        if (done == null) {
            throw new BaseException("已办为空！");
        }
        setDefaultDone(done, flowTaskVo);
        flowTaskVo.setHandleType(TodoHandleTypeEnum.REVOKE.getCode());
    }

    /**
     * 校验驳回参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateRejectParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        flowTaskVo.setHandleType(TodoHandleTypeEnum.REJECT.getCode());
    }

    /**
     * 校验抄送参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateCopyParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (StringUtils.isBlank(flowTaskVo.getAssignee())) {
            throw new BaseException("抄送人为空！");
        }
        flowTaskVo.setHandleType(TodoHandleTypeEnum.COPY.getCode());
    }

    /**
     * 获取待办的只读副本
     *
     * @param id 待办ID
     * @return Todo
     */
    private Todo getReadCopyTodo(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("id为空！");
        }
        Todo todo = todoService.getTodoById(id);
        if (todo == null) {
            throw new BaseException("待办为空！");
        }
        todo.setDelFlag(WhetherStatus.YES.getCode());
        return todo;
    }

    /**
     * 校验退回参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateReturnParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        flowTaskVo.setHandleType(TodoHandleTypeEnum.BACK.getCode());
    }

    /**
     * 获取终止流程的待办
     *
     * @param flowTaskVo 流程对象
     * @return Todo
     */
    private Todo getTerminateProcessTodo(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTodoId())) {
            throw new BaseException("待办ID不能为空！");
        }
        Todo todo = todoService.getTodoById(flowTaskVo.getTodoId());
        if (todo == null) {
            throw new BaseException("待办为空！");
        }
        // 流程创建人才能终止
        if (!flowTaskService.checkIsFlowCreator(todo.getProcInstId())) {
            throw new BaseException("非流程创建者，不能终止流程！");
        }
        setFlowTaskVoByTodo(todo, flowTaskVo);
        return todo;
    }

    /**
     * 校验跳转参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateJumpParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            throw new BaseException("procInsId不能为空");
        }
        if (StringUtils.isBlank(flowTaskVo.getTaskDefKey())) {
            throw new BaseException("taskDefKey不能为空");
        }
        if (StringUtils.isBlank(flowTaskVo.getDefId())) {
            throw new BaseException("defId不能为空");
        }
        List<Todo> todos = todoService.listTodoByProcInsId(flowTaskVo.getProcInsId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前环节待办不存在，跳转失败！");
        }
        setFlowTaskVoByTodo(todos.get(0), flowTaskVo);
        // 待办类型
        flowTaskVo.setType(TodoTypeEnum.TODO.getCode());
        // 审批
        flowTaskVo.setHandleType(TodoHandleTypeEnum.AUDIT.getCode());
    }

    /**
     * 删除其他人待办
     *
     * @param flowTaskVo 流程对象
     * @param createId   创建人ID
     */
    private void deleteClaimOtherTodo(FlowTaskVo flowTaskVo, String createId) {
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        List<Todo> filterTodo = todos.stream()
                .filter(e -> !e.getCurHandler().equals(createId))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filterTodo)) {
            return;
        }
        filterTodo.forEach(e -> {
            e.setDelFlag(WhetherStatus.YES.getCode());
            e.setUpdateId(SecurityUtils.getUserId());
            e.setUpdateTime(DateUtils.getNowDate());
        });
        todoService.batchDelete(filterTodo);
        // 处理其他操作
        handleOtherOperation(filterTodo, createId, false);
    }

    /**
     * 校验取消认领任务参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateUnClaimParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前环节待办不存在！");
        }
        setFlowTaskVoByTodo(todos.get(0), flowTaskVo);
    }

    /**
     * 获取委派任务
     *
     * @param flowTaskVo 流程对象
     * @return
     */
    private Todo getDelegateTask(FlowTaskVo flowTaskVo) {
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前任务待办为空！");
        }
        return todos.stream()
                .filter(todo -> todo.getCurHandler().equals(SecurityUtils.getUserId()))
                .findFirst()
                .orElseThrow(() -> new BaseException("当前处理人待办为空！"));
    }

    /**
     * 校验委派参数
     *
     * @param flowTaskVo 流程对象
     */
    private void validateDelegateParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (StringUtils.isBlank(flowTaskVo.getAssignee())) {
            throw new BaseException("委派人为空！");
        }
        flowTaskVo.setHandleType(TodoHandleTypeEnum.DELEGATE.getCode());
    }

    /**
     * 校验提交任务入参
     *
     * @param flowTaskVo 流程对象
     */
    private void validateAssignParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (StringUtils.isBlank(flowTaskVo.getAssignee())) {
            throw new BaseException("转办人为空！");
        }
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前环节待办不存在！");
        }
        setFlowTaskVoByTodo(todos.get(0), flowTaskVo);
        flowTaskVo.setHandleType(TodoHandleTypeEnum.ASSIGN.getCode());
    }

    /**
     * 校验多实例加签入参
     *
     * @param flowTaskVo 流程对象
     */
    private void validateAddMultiParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            throw new BaseException("procInsId为空");
        }
        if (StringUtils.isBlank(flowTaskVo.getAssignee())) {
            throw new BaseException("加签人员为空！");
        }
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前环节待办不存在！");
        }
        setFlowTaskVoByTodo(todos.get(0), flowTaskVo);
        // 加签类型
        flowTaskVo.setHandleType(TodoHandleTypeEnum.ADD_MULTI.getCode());
    }

    /**
     * 校验减签入参
     *
     * @param flowTaskVo 流程对象
     */
    private void validateDeleteMultiParam(FlowTaskVo flowTaskVo) {
        if (StringUtils.isBlank(flowTaskVo.getTaskId())) {
            throw new BaseException("taskId为空！");
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            throw new BaseException("procInsId为空");
        }
        if (StringUtils.isBlank(flowTaskVo.getCurrentChildExecutionIds())) {
            throw new BaseException("减签子执行流程主键为空");
        }
        if (StringUtils.isBlank(flowTaskVo.getAssignee())) {
            throw new BaseException("减签人员主键为空");
        }
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isEmpty(todos)) {
            throw new BaseException("当前环节待办不存在！");
        }
        setFlowTaskVoByTodo(todos.get(0), flowTaskVo);
    }

    /**
     * 新增最近使用模板记录
     *
     * @param templateId 模板ID
     */
    private void saveReceTemplate(String templateId) {
        ReceTemplate receTemplate = new ReceTemplate();
        receTemplate.setTemplateId(templateId);
        receTemplate.setUserId(SecurityUtils.getLoginUser().getUserId());
        receTemplate.setCreateId(SecurityUtils.getLoginUser().getUserId());
        receTemplateService.saveReceTemplate(receTemplate);
    }

    /**
     * 发送待办列表更新消息
     *
     * @param createId   创建人ID
     * @param recvIds    接收人ID列表
     */
    private void sendTodoListUpdateMsg(String createId, List<String> recvIds) {
        if (StringUtils.isBlank(createId) && CollectionUtils.isEmpty(recvIds)) {
            return;
        }
        try {
            //发送更新待办列表的webSocket消息通知
            systemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_LIST_REFRESH.getCode(), createId, recvIds, "您的待办任务已更新！");
        } catch (Exception e) {
            log.error("发送待办列表更新消息失败", e);
        }
    }

    /**
     * 检查上一个环节已办是否存在
     *
     * @param flowTaskVo 流程对象
     */
    private void checkLastDone(FlowTaskVo flowTaskVo) {
        List<Done> dones = doneService.listLastDoneByProcInstIds(flowTaskVo.getProcInsId());
        if (CollectionUtils.isEmpty(dones)) {
            throw new BaseException("上一个环节已办为空，取回失败！");
        }
        setDefaultDone(dones.get(0), flowTaskVo);
    }

    /**
     * 检查是否可以取回已办任务
     *
     * @param flowTask 流程任务
     */
    private void checkReturnFinishTask(FlowTaskDto flowTask) {
        if (Objects.isNull(flowTask)) {
            throw new BaseException("流程任务为空，取回失败！");
        }
        if (Objects.isNull(flowTask.getFinishTime())) {
            throw new BaseException("流程任务未完成，取回失败！");
        }
    }
}
