package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.WorkflowTimeoutJob;

import java.util.Date;
import java.util.List;

/**
 * 流程任务超时记录Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowTimeoutJobService {

    /**
     * 新增流程任务超时记录
     * 
     * @param workflowTimeoutJob 流程任务超时记录
     * @return 结果
     */
    public int saveWorkflowTimeoutJob(WorkflowTimeoutJob workflowTimeoutJob);

    /**
     * 删除流程任务超时记录信息
     * 
     * @param id 流程任务超时记录主键
     * @return 结果
     */
    public int deleteWorkflowTimeoutJobById(String id);

    /**
     * 查询当前时间之前的超时任务
     *
     * @param date 当前时间
     * @return 结果
     */
    List<WorkflowTimeoutJob> listWorkflowTimeoutJobByCurrentTime(Date date);
}
