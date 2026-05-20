package com.ruoyi.workflow.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.workflow.domain.WorkflowMyDraft;
import com.ruoyi.workflow.mapper.WorkflowMyDraftMapper;
import com.ruoyi.workflow.service.IWorkflowMyDraftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我起草的流程表Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowMyDraftServiceImpl implements IWorkflowMyDraftService {
    @Autowired
    private WorkflowMyDraftMapper workflowMyDraftMapper;
    @Autowired
    private ITemplateService templateService;

    /**
     * 查询我起草的流程表列表
     *
     * @param workflowMyDraft 我起草的流程表
     * @return 我起草的流程表
     */
    @Override
    public List<WorkflowMyDraft> listWorkflowMyDraft(WorkflowMyDraft workflowMyDraft) {
        workflowMyDraft.setCreateId(SecurityUtils.getUserId());
        return workflowMyDraftMapper.selectWorkflowMyDraftList(workflowMyDraft);
    }

    /**
     * 新增我起草的流程表
     *
     * @param workflowMyDraft 我起草的流程表
     * @return 结果
     */
    @Override
    public int saveWorkflowMyDraft(WorkflowMyDraft workflowMyDraft) {
        workflowMyDraft.setId(IdUtils.fastSimpleUUID());
        workflowMyDraft.setCreateTime(DateUtils.getNowDate());
        return workflowMyDraftMapper.insertWorkflowMyDraft(workflowMyDraft);
    }

    /**
     * 创建我起草的流程表
     */
    @Override
    public void createMyDraft(WorkflowMyDraft workflowMyDraft) {
        Template template = templateService.getTemplateById(workflowMyDraft.getTemplateId());
        if (template == null) {
            log.error("## 处理我起草的流程，模板部存在：{}", workflowMyDraft.getTemplateId());
            return;
        }
        workflowMyDraft.setTemplateName(template.getName());
        workflowMyDraft.setStatus("0");
        saveWorkflowMyDraft(workflowMyDraft);
    }

    /**
     * 更新我起草的流程表状态
     */
    @Override
    public void updateMyDraft(WorkflowMyDraft workflowMyDraft) {
        WorkflowMyDraft draft = workflowMyDraftMapper.selectByBizId(workflowMyDraft.getBizId());
        if (draft == null) {
            return;
        }
        draft.setBizTitle(workflowMyDraft.getBizTitle());
        draft.setStatus(workflowMyDraft.getStatus());
        workflowMyDraftMapper.updateWorkflowMyDraft(draft);
    }

    /**
     * 统计我起草的流程表数量
     *
     * @return
     */
    @Override
    public int count() {
        return workflowMyDraftMapper.count(SecurityUtils.getUserId());
    }

    /**
     * 批量查询我起草的流程表
     *
     * @param businessKeys
     * @return 结果
     */
    @Override
    public List<WorkflowMyDraft> listWorkflowMyDraftByBizIds(List<String> businessKeys) {
        return workflowMyDraftMapper.selectWorkflowMyDraftByBizIds(businessKeys);
    }
}
