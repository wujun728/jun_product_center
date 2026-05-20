package com.ruoyi.workflow.service;

import java.util.List;
import com.ruoyi.workflow.domain.WorkflowRecycle;

/**
 * 回收站Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowRecycleService {
    /**
     * 查询回收站
     * 
     * @param id 回收站主键
     * @return 回收站
     */
    public WorkflowRecycle getWorkflowRecycleById(String id);

    /**
     * 查询回收站列表
     * 
     * @param workflowRecycle 回收站
     * @return 回收站集合
     */
    public List<WorkflowRecycle> listWorkflowRecycle(WorkflowRecycle workflowRecycle);

    /**
     * 新增回收站
     * 
     * @param workflowRecycle 回收站
     * @return 结果
     */
    public int saveWorkflowRecycle(WorkflowRecycle workflowRecycle);

    /**
     * 修改回收站
     * 
     * @param workflowRecycle 回收站
     * @return 结果
     */
    public int updateWorkflowRecycle(WorkflowRecycle workflowRecycle);

    /**
     * 批量删除回收站
     * 
     * @param ids 需要删除的回收站主键集合
     * @return 结果
     */
    public int deleteWorkflowRecycleByIds(String[] ids);

    /**
     * 删除回收站信息
     * 
     * @param id 回收站主键
     * @return 结果
     */
    public int deleteWorkflowRecycleById(String id);
}
