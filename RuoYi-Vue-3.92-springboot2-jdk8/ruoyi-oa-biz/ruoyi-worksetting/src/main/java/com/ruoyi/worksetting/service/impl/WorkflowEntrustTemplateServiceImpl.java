package com.ruoyi.worksetting.service.impl;

import com.ruoyi.worksetting.domain.WorkflowEntrustTemplate;
import com.ruoyi.worksetting.mapper.WorkflowEntrustTemplateMapper;
import com.ruoyi.worksetting.service.IWorkflowEntrustTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 委托关联模板Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowEntrustTemplateServiceImpl implements IWorkflowEntrustTemplateService {
    @Autowired
    private WorkflowEntrustTemplateMapper workflowEntrustTemplateMapper;

    /**
     * 查询委托关系模板列表
     *
     * @param entrustIds 委托关系ID集合
     * @return 结果
     */
    @Override
    public List<WorkflowEntrustTemplate> listByEntrustIds(List<String> entrustIds) {
        return workflowEntrustTemplateMapper.selectListByEntrustIds(entrustIds);
    }

    /**
     * 批量插入委托关系模板
     *
     * @param templateList 模板列表
     */
    @Override
    public void saveBatch(List<WorkflowEntrustTemplate> templateList) {
        workflowEntrustTemplateMapper.batchInsert(templateList);
    }

    /**
     * 根据委托关系ID批量更新删除标记
     *
     * @param entrustIds 需要更新的委托关系ID
     * @return
     */
    @Override
    public int updateDelFlagByEntrustIds(String[] entrustIds) {
        return workflowEntrustTemplateMapper.updateDelFlagByEntrustIds(entrustIds);
    }
}
