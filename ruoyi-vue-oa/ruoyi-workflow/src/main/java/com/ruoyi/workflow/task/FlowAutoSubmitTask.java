package com.ruoyi.workflow.task;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.enums.FLowOperateTypeEnum;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.service.ITodoService;
import com.ruoyi.tools.lock.RedisLock;
import com.ruoyi.workflow.domain.WorkflowTimeoutJob;
import com.ruoyi.workflow.service.IWorkflowTimeoutJobService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p> 流程自动提交任务 </p>
 *
 * @Author wocurr.com
 */
@Component("flowAutoSubmitTask")
public class FlowAutoSubmitTask {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ITodoService todoService;
    @Autowired
    private ISyncPush syncPush;
    @Autowired
    private IWorkflowTimeoutJobService workflowTimeoutJobService;
    @Autowired
    private RedisLock redisLock;

    private static final String AUTO_SUBMIT_COMMENT = "自动提交";
    private static final String AUTO_SUBMIT_LOCK_KEY = "flow:auto:submit:lock:";

    @Transactional(rollbackFor = Exception.class)
    public void execute() {
        List<WorkflowTimeoutJob> timeoutJobs = workflowTimeoutJobService.listWorkflowTimeoutJobByCurrentTime(new Date());
        if (CollectionUtils.isEmpty(timeoutJobs)) {
            return;
        }
        for (WorkflowTimeoutJob timeoutJob : timeoutJobs) {
            redisLock.doLock(AUTO_SUBMIT_LOCK_KEY + timeoutJob.getId(), () -> {
                Task task = taskService.createTaskQuery().taskId(timeoutJob.getTaskId()).singleResult();
                if (task == null || task.getDueDate() == null) {
                    //删除已经提交的数据
                    workflowTimeoutJobService.deleteWorkflowTimeoutJobById(timeoutJob.getId());
                    return;
                }
                FlowTaskVo flowTaskVo = getFlowTaskVo(task);
                List<Todo> todos = todoService.listTodoByTaskId(task.getId());
                if (CollectionUtils.isNotEmpty(todos)) {
                    setDefaultTodo(todos.get(0), flowTaskVo);
                }
                String userId = StringUtils.isBlank(flowTaskVo.getUserId()) ? SecurityUtils.getUserId() : flowTaskVo.getUserId();
                syncPush.push(QueueEnum.ASYNC_FLOW_QUEUE.getConsumerBeanName(), JSON.toJSONString(flowTaskVo), QueueEnum.ASYNC_FLOW_QUEUE.getQueueName(), userId);
                todoService.completeCurrentTodo(task.getId(), userId);
                workflowTimeoutJobService.deleteWorkflowTimeoutJobById(timeoutJob.getId());
            });
        }
    }

    /**
     * 设置默认的流程信息
     *
     * @param task
     * @return
     */
    private FlowTaskVo getFlowTaskVo(Task task) {
        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setTaskId(task.getId());
        flowTaskVo.setTaskDefKey(task.getTaskDefinitionKey());
        flowTaskVo.setProcInsId(task.getProcessInstanceId());
        flowTaskVo.setOperateType(FLowOperateTypeEnum.COMPLETE.getType());
        //设置超时自动提交的审批意见
        flowTaskVo.setComment(AUTO_SUBMIT_COMMENT);
        return flowTaskVo;
    }

    /**
     * 设置默认的待办信息
     *
     * @param todo 待办
     * @param flowTaskVo 请求参数
     */
    private void setDefaultTodo(Todo todo, FlowTaskVo flowTaskVo) {
        flowTaskVo.setTitle(todo.getTitle());
        flowTaskVo.setBusinessId(todo.getBusinessId());
        flowTaskVo.setTemplateId(todo.getTemplateId());
        flowTaskVo.setTemplateName(todo.getTemplateName());
        flowTaskVo.setTemplateType(todo.getTemplateType());
        flowTaskVo.setUrgencyStatus(todo.getUrgencyStatus());
        // 表单保存时，默认type为草稿，表单提交时，将草稿改为待办
        flowTaskVo.setType(StringUtils.equals(Constants.NO_VALUE, todo.getType()) ? Constants.YES_VALUE : todo.getType());
        if (StringUtils.isBlank(flowTaskVo.getUserId())) {
            flowTaskVo.setUserId(todo.getCurHandler());
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            flowTaskVo.setProcInsId(todo.getProcInstId());
        }
        if (StringUtils.isBlank(flowTaskVo.getHandleType())) {
            flowTaskVo.setHandleType(todo.getHandleType());
        }
    }
}
