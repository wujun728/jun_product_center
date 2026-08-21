package com.ruoyi.workflow.listener;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.workflow.domain.WorkflowTimeoutJob;
import com.ruoyi.workflow.service.IWorkflowTimeoutJobService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * 超时任务监听器
 * <p>
 * create（创建）:在任务被创建且所有的任务属性设置完成后才触发
 * assignment（指派）：在任务被分配给某个办理人之后触发
 * complete（完成）：在配置了监听器的上一个任务完成时触发
 * delete（删除）：在任务即将被删除前触发。请注意任务由completeTask正常完成时也会触发
 * </p>
 * @author wocurr.com
 */
@Slf4j
public class FlowTaskTimeOutListener implements TaskListener {

    private final IWorkflowTimeoutJobService workflowTimeoutJobService = SpringUtils.getBean(IWorkflowTimeoutJobService.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        if (delegateTask.getDueDate() == null) {
            return;
        }
        // 记录超时任务审批调度任务，定时触发
        WorkflowTimeoutJob timeoutJob = new WorkflowTimeoutJob();
        timeoutJob.setId(IdUtils.fastSimpleUUID());
        timeoutJob.setTaskId(delegateTask.getId());
        timeoutJob.setProcInstId(delegateTask.getProcessInstanceId());
        timeoutJob.setExpireTime(delegateTask.getDueDate());
        timeoutJob.setCreateTime(DateUtils.getNowDate());
        workflowTimeoutJobService.saveWorkflowTimeoutJob(timeoutJob);
    }
}
