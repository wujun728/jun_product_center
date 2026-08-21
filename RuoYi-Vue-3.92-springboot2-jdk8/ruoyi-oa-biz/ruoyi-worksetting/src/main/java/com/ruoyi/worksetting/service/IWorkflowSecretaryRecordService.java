package com.ruoyi.worksetting.service;

import java.util.List;
import com.ruoyi.worksetting.domain.WorkflowSecretaryRecord;

/**
 * 流程办理秘书记录Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowSecretaryRecordService {

    /**
     * 查询流程办理秘书记录列表
     * 
     * @param workflowSecretaryRecord 流程办理秘书记录
     * @return 流程办理秘书记录集合
     */
    public List<WorkflowSecretaryRecord> listWorkflowSecretaryRecord(WorkflowSecretaryRecord workflowSecretaryRecord);

    /**
     * 批量插入秘书记录
     *
     * @param secretaryRecords 秘书记录
     */
    void saveBatchSecretaryRecordList(List<WorkflowSecretaryRecord> secretaryRecords);
}
