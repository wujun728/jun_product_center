package com.ruoyi.worksetting.service;

import com.ruoyi.worksetting.domain.WorkflowEntrustTemplate;

import java.util.List;

/**
 * 委托关联模板Service接口
 *
 * @author wocurr.com
 */
public interface IWorkflowEntrustTemplateService {
    /**
     * 查询委托关系模板列表
     *
     * @param entrustIds 委托关系ID集合
     * @return 结果
     */
    List<WorkflowEntrustTemplate> listByEntrustIds(List<String> entrustIds);

    /**
     * 批量插入
     *
     * @param templateList
     */
    void saveBatch(List<WorkflowEntrustTemplate> templateList);


    /**
     * 根据委托关系ID批量更新删除标记
     *
     * @param entrustIds 需要更新的委托关系ID
     * @return
     */
    int updateDelFlagByEntrustIds(String[] entrustIds);
}
