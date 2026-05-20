package com.ruoyi.worksetting.mapper;

import java.util.List;
import com.ruoyi.worksetting.domain.WorkflowEntrustTemplate;

/**
 * 委托关联模板Mapper接口
 * 
 * @author wocurr.com
 */
public interface WorkflowEntrustTemplateMapper {
    /**
     * 查询委托关联模板
     * 
     * @param id 委托关联模板主键
     * @return 委托关联模板
     */
    public WorkflowEntrustTemplate selectWorkflowEntrustTemplateById(String id);

    /**
     * 查询委托关联模板列表
     * 
     * @param workflowEntrustTemplate 委托关联模板
     * @return 委托关联模板集合
     */
    public List<WorkflowEntrustTemplate> selectWorkflowEntrustTemplateList(WorkflowEntrustTemplate workflowEntrustTemplate);

    /**
     * 新增委托关联模板
     * 
     * @param workflowEntrustTemplate 委托关联模板
     * @return 结果
     */
    public int insertWorkflowEntrustTemplate(WorkflowEntrustTemplate workflowEntrustTemplate);

    /**
     * 修改委托关联模板
     * 
     * @param workflowEntrustTemplate 委托关联模板
     * @return 结果
     */
    public int updateWorkflowEntrustTemplate(WorkflowEntrustTemplate workflowEntrustTemplate);

    /**
     * 删除委托关联模板
     * 
     * @param id 委托关联模板主键
     * @return 结果
     */
    public int deleteWorkflowEntrustTemplateById(String id);

    /**
     * 批量删除委托关联模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowEntrustTemplateByIds(String[] ids);

    /**
     * 查询委托关系模板列表
     *
     * @param entrustIds 委托关系ID集合
     * @return 结果
     */
    List<WorkflowEntrustTemplate> selectListByEntrustIds(List<String> entrustIds);

    /**
     * 批量插入委托关系模板
     *
     * @param templateList 模板列表
     */
    void batchInsert(List<WorkflowEntrustTemplate> templateList);

    /**
     * 根据委托关系ID删除委托关系模板
     *
     * @param entrustId 委托关系ID
     */
    int deleteWorkflowEntrustTemplateByEntrustId(String entrustId);

    /**
     * 根据委托关系ID批量删除
     *
     * @param ids 需要删除的委托关系ID
     * @return
     */
    int deleteWorkflowEntrustTemplateByEntrustIds(String[] ids);

    /**
     * 根据委托关系ID批量更新删除标记
     *
     * @param entrustIds 需要更新的委托关系ID
     * @return
     */
    int updateDelFlagByEntrustIds(String[] entrustIds);
}
