package com.ruoyi.workflow.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.workflow.domain.WorkflowTimeoutJob;

/**
 * 流程任务超时记录Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-05-14
 */
public interface WorkflowTimeoutJobMapper {
    /**
     * 查询流程任务超时记录
     * 
     * @param id 流程任务超时记录主键
     * @return 流程任务超时记录
     */
    public WorkflowTimeoutJob selectWorkflowTimeoutJobById(String id);

    /**
     * 查询流程任务超时记录列表
     * 
     * @param workflowTimeoutJob 流程任务超时记录
     * @return 流程任务超时记录集合
     */
    public List<WorkflowTimeoutJob> selectWorkflowTimeoutJobList(WorkflowTimeoutJob workflowTimeoutJob);

    /**
     * 新增流程任务超时记录
     * 
     * @param workflowTimeoutJob 流程任务超时记录
     * @return 结果
     */
    public int insertWorkflowTimeoutJob(WorkflowTimeoutJob workflowTimeoutJob);

    /**
     * 修改流程任务超时记录
     * 
     * @param workflowTimeoutJob 流程任务超时记录
     * @return 结果
     */
    public int updateWorkflowTimeoutJob(WorkflowTimeoutJob workflowTimeoutJob);

    /**
     * 删除流程任务超时记录
     * 
     * @param id 流程任务超时记录主键
     * @return 结果
     */
    public int deleteWorkflowTimeoutJobById(String id);

    /**
     * 批量删除流程任务超时记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowTimeoutJobByIds(String[] ids);

    /**
     * 查询当前时间之前的超时任务
     *
     * @param currentTime 当前时间
     * @return 结果
     */
    List<WorkflowTimeoutJob> selectWorkflowTimeoutJobListByCurrentTime(Date currentTime);
}
