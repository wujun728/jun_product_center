package com.ruoyi.workflow.mapper;

import java.util.List;
import com.ruoyi.workflow.domain.WorkflowCommonComment;

/**
 * 流程常用意见Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-03-19
 */
public interface WorkflowCommonCommentMapper {
    /**
     * 查询流程常用意见
     * 
     * @param id 流程常用意见主键
     * @return 流程常用意见
     */
    public WorkflowCommonComment selectWorkflowCommonCommentById(String id);

    /**
     * 查询流程常用意见列表
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 流程常用意见集合
     */
    public List<WorkflowCommonComment> selectWorkflowCommonCommentList(WorkflowCommonComment workflowCommonComment);

    /**
     * 新增流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 结果
     */
    public int insertWorkflowCommonComment(WorkflowCommonComment workflowCommonComment);

    /**
     * 修改流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 结果
     */
    public int updateWorkflowCommonComment(WorkflowCommonComment workflowCommonComment);

    /**
     * 删除流程常用意见
     * 
     * @param id 流程常用意见主键
     * @return 结果
     */
    public int deleteWorkflowCommonCommentById(String id);

    /**
     * 批量删除流程常用意见
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowCommonCommentByIds(String[] ids);
}
