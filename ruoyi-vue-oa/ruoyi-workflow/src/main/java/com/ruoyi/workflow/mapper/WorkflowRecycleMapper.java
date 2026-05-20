package com.ruoyi.workflow.mapper;

import java.util.List;
import com.ruoyi.workflow.domain.WorkflowRecycle;

/**
 * 回收站Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-04-03
 */
public interface WorkflowRecycleMapper {
    /**
     * 查询回收站
     * 
     * @param id 回收站主键
     * @return 回收站
     */
    public WorkflowRecycle selectWorkflowRecycleById(String id);

    /**
     * 查询回收站列表
     * 
     * @param workflowRecycle 回收站
     * @return 回收站集合
     */
    public List<WorkflowRecycle> selectWorkflowRecycleList(WorkflowRecycle workflowRecycle);

    /**
     * 新增回收站
     * 
     * @param workflowRecycle 回收站
     * @return 结果
     */
    public int insertWorkflowRecycle(WorkflowRecycle workflowRecycle);

    /**
     * 修改回收站
     * 
     * @param workflowRecycle 回收站
     * @return 结果
     */
    public int updateWorkflowRecycle(WorkflowRecycle workflowRecycle);

    /**
     * 删除回收站
     * 
     * @param id 回收站主键
     * @return 结果
     */
    public int deleteWorkflowRecycleById(String id);

    /**
     * 批量删除回收站
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowRecycleByIds(String[] ids);
}
