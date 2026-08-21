package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.WorkflowMainSeal;
import com.ruoyi.workflow.module.WorkflowSealResult;

import java.util.List;

/**
 * 正文印章Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowMainSealService {
    /**
     * 查询正文印章
     * 
     * @param id 正文印章主键
     * @return 正文印章
     */
    public WorkflowMainSeal getWorkflowMainSealById(String id);

    /**
     * 根据印章id查询详情
     * @param id
     * @return
     */
    public WorkflowSealResult getWorkflowDetail(String id);

    /**
     * 查询正文印章列表
     * 
     * @param workflowMainSeal 正文印章
     * @return 正文印章集合
     */
    public List<WorkflowMainSeal> listWorkflowMainSeal(WorkflowMainSeal workflowMainSeal);

    /**
     * 获取所有有效的印章
     * @return
     */
    public List<WorkflowSealResult> findAllSeals();

    /**
     * 新增正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    public int saveWorkflowMainSeal(WorkflowMainSeal workflowMainSeal);

    /**
     * 修改正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    public int updateWorkflowMainSeal(WorkflowMainSeal workflowMainSeal);

    /**
     * 批量删除正文印章
     * 
     * @param ids 需要删除的正文印章主键集合
     * @return 结果
     */
    public int deleteWorkflowMainSealByIds(String[] ids);

    /**
     * 修改启用状态
     * @param mainSeal
     * @return
     */
    public int changeEnableFlag(WorkflowMainSeal mainSeal);

    /**
     * 印章预览
     * @param mainSeal
     * @return
     */
    public WorkflowSealResult previewMainSeal(WorkflowMainSeal mainSeal);

    /**
     * 创建印章，保存印章图片到文件存储服务
     * @param mainSeal
     * @return
     */
    public int createMainSeal(WorkflowMainSeal mainSeal);
}
