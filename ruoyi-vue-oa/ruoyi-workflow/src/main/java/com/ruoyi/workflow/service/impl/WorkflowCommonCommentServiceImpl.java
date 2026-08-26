package com.ruoyi.workflow.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.workflow.mapper.WorkflowCommonCommentMapper;
import com.ruoyi.workflow.domain.WorkflowCommonComment;
import com.ruoyi.workflow.service.IWorkflowCommonCommentService;

/**
 * 流程常用意见Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowCommonCommentServiceImpl implements IWorkflowCommonCommentService {
    @Autowired
    private WorkflowCommonCommentMapper workflowCommonCommentMapper;

    /**
     * 查询流程常用意见
     * 
     * @param id 流程常用意见主键
     * @return 流程常用意见
     */
    @Override
    public WorkflowCommonComment getWorkflowCommonCommentById(String id) {
        return workflowCommonCommentMapper.selectWorkflowCommonCommentById(id);
    }

    /**
     * 查询流程常用意见列表
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 流程常用意见
     */
    @Override
    public List<WorkflowCommonComment> listWorkflowCommonComment(WorkflowCommonComment workflowCommonComment) {
        workflowCommonComment.setUserId(SecurityUtils.getUserId());
        return workflowCommonCommentMapper.selectWorkflowCommonCommentList(workflowCommonComment);
    }

    /**
     * 新增流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 结果
     */
    @Override
    public String saveWorkflowCommonComment(WorkflowCommonComment workflowCommonComment) {
        String id = IdUtils.fastSimpleUUID();
        workflowCommonComment.setId(id);
        workflowCommonComment.setUserId(SecurityUtils.getUserId());
        workflowCommonComment.setCreateTime(DateUtils.getNowDate());
        workflowCommonCommentMapper.insertWorkflowCommonComment(workflowCommonComment);
        return id;
    }

    /**
     * 修改流程常用意见
     * 
     * @param workflowCommonComment 流程常用意见
     * @return 结果
     */
    @Override
    public int updateWorkflowCommonComment(WorkflowCommonComment workflowCommonComment) {
        return workflowCommonCommentMapper.updateWorkflowCommonComment(workflowCommonComment);
    }

    /**
     * 批量删除流程常用意见
     * 
     * @param ids 需要删除的流程常用意见主键
     * @return 结果
     */
    @Override
    public int deleteWorkflowCommonCommentByIds(String[] ids) {
        return workflowCommonCommentMapper.deleteWorkflowCommonCommentByIds(ids);
    }

    /**
     * 删除流程常用意见信息
     * 
     * @param id 流程常用意见主键
     * @return 结果
     */
    @Override
    public int deleteWorkflowCommonCommentById(String id) {
        return workflowCommonCommentMapper.deleteWorkflowCommonCommentById(id);
    }
}
