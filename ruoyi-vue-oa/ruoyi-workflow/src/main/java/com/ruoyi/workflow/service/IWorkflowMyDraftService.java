package com.ruoyi.workflow.service;

import java.util.List;
import com.ruoyi.workflow.domain.WorkflowMyDraft;

/**
 * 我起草的流程表Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowMyDraftService {

    /**
     * 查询我起草的流程表列表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 我起草的流程表集合
     */
    public List<WorkflowMyDraft> listWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 新增我起草的流程表
     * 
     * @param workflowMyDraft 我起草的流程表
     * @return 结果
     */
    public int saveWorkflowMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 创建我起草的流程表
     */
    public void createMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 更新我起草的流程表状态
     */
    public void updateMyDraft(WorkflowMyDraft workflowMyDraft);

    /**
     * 统计我起草的流程表数量
     * @return
     */
    public int count();

    /**
     * 批量查询我起草的流程表
     *
     * @param businessKeys
     * @return
     */
    List<WorkflowMyDraft> listWorkflowMyDraftByBizIds(List<String> businessKeys);
}
