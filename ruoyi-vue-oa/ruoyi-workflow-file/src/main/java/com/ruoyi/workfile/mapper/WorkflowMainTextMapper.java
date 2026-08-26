package com.ruoyi.workfile.mapper;


import com.ruoyi.workfile.domain.WorkflowMainText;

import java.util.List;

/**
 * 正文Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-04-15
 */
public interface WorkflowMainTextMapper {
    /**
     * 查询正文
     * 
     * @param id 正文主键
     * @return 正文
     */
    public WorkflowMainText selectWorkflowMainTextById(String id);

    /**
     * 根据业务id查询
     * @param businessId
     * @return
     */
    public WorkflowMainText getByBusinessId(String businessId);

    /**
     * 查询正文列表
     * 
     * @param workflowMainText 正文
     * @return 正文集合
     */
    public List<WorkflowMainText> selectWorkflowMainTextList(WorkflowMainText workflowMainText);

    /**
     * 新增正文
     * 
     * @param workflowMainText 正文
     * @return 结果
     */
    public int insertWorkflowMainText(WorkflowMainText workflowMainText);

    /**
     * 修改正文
     * 
     * @param workflowMainText 正文
     * @return 结果
     */
    public int updateWorkflowMainText(WorkflowMainText workflowMainText);

    /**
     * 删除正文
     * 
     * @param id 正文主键
     * @return 结果
     */
    public int deleteWorkflowMainTextById(String id);

    /**
     * 批量删除正文
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowMainTextByIds(String[] ids);

    /**
     * 根据业务id删除
     * @param businessId
     * @return
     */
    public int deleteByBusinessId(String businessId);

    /**
     * 还原印章
     * @param workflowMainText
     * @return
     */
    public int restoreSeal(WorkflowMainText workflowMainText);
}
