package com.ruoyi.workflow.mapper;

import java.util.List;
import com.ruoyi.workflow.domain.WorkflowMainSeal;

/**
 * 正文印章Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-04-15
 */
public interface WorkflowMainSealMapper {
    /**
     * 查询正文印章
     * 
     * @param id 正文印章主键
     * @return 正文印章
     */
    public WorkflowMainSeal selectWorkflowMainSealById(String id);

    /**
     * 查询正文印章列表
     * 
     * @param workflowMainSeal 正文印章
     * @return 正文印章集合
     */
    public List<WorkflowMainSeal> selectWorkflowMainSealList(WorkflowMainSeal workflowMainSeal);

    public List<WorkflowMainSeal> listByName(WorkflowMainSeal workflowMainSeal);

    /**
     * 新增正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    public int insertWorkflowMainSeal(WorkflowMainSeal workflowMainSeal);

    /**
     * 修改正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    public int updateWorkflowMainSeal(WorkflowMainSeal workflowMainSeal);

    /**
     * 修改启用状态
     * @param workflowMainSeal
     * @return
     */
    public int changeEnableFlag(WorkflowMainSeal workflowMainSeal);

    /**
     * 删除正文印章
     * 
     * @param id 正文印章主键
     * @return 结果
     */
    public int deleteWorkflowMainSealById(String id);

    /**
     * 批量删除正文印章
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowMainSealByIds(String[] ids);
}
