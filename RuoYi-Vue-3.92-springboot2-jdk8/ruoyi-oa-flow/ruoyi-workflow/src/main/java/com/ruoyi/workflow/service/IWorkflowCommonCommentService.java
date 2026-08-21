package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.WorkflowCommonComment;

import java.util.List;

/**
 * 流程常用意见Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowCommonCommentService {
    /**
     * 查询流程常用意见
     * 
     * @param id 流程常用意见主键
     * @return 流程常用意见
     */
    public WorkflowCommonComment getWorkflowCommonCommentById(String id);

    /**
     * 查询流程常用意见列表
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 流程常用意见集合
     */
    public List<WorkflowCommonComment> listWorkflowCommonComment(WorkflowCommonComment workflowCommonComment);

    /**
     * 新增流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 主键id
     */
    public String saveWorkflowCommonComment(WorkflowCommonComment workflowCommonComment);

    /**
     * 修改流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 结果
     */
    public int updateWorkflowCommonComment(WorkflowCommonComment workflowCommonComment);

    /**
     * 批量删除流程常用意见
     * 
     * @param ids 需要删除的流程常用意见主键集合
     * @return 结果
     */
    public int deleteWorkflowCommonCommentByIds(String[] ids);

    /**
     * 删除流程常用意见信息
     * 
     * @param id 流程常用意见主键
     * @return 结果
     */
    public int deleteWorkflowCommonCommentById(String id);
}
