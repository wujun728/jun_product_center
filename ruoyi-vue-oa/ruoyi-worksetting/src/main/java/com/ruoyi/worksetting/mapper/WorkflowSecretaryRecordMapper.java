package com.ruoyi.worksetting.mapper;

import java.util.List;

import com.ruoyi.worksetting.domain.WorkflowSecretaryRecord;

/**
 * 流程办理秘书记录Mapper接口
 *
 * @author wocurr.com
 */
public interface WorkflowSecretaryRecordMapper {
    /**
     * 查询流程办理秘书记录
     *
     * @param id 流程办理秘书记录主键
     * @return 流程办理秘书记录
     */
    public WorkflowSecretaryRecord selectWorkflowSecretaryRecordById(String id);

    /**
     * 查询流程办理秘书记录列表
     *
     * @param workflowSecretaryRecord 流程办理秘书记录
     * @return 流程办理秘书记录集合
     */
    public List<WorkflowSecretaryRecord> selectWorkflowSecretaryRecordList(WorkflowSecretaryRecord workflowSecretaryRecord);

    /**
     * 新增流程办理秘书记录
     *
     * @param workflowSecretaryRecord 流程办理秘书记录
     * @return 结果
     */
    public int insertWorkflowSecretaryRecord(WorkflowSecretaryRecord workflowSecretaryRecord);

    /**
     * 修改流程办理秘书记录
     *
     * @param workflowSecretaryRecord 流程办理秘书记录
     * @return 结果
     */
    public int updateWorkflowSecretaryRecord(WorkflowSecretaryRecord workflowSecretaryRecord);

    /**
     * 删除流程办理秘书记录
     *
     * @param id 流程办理秘书记录主键
     * @return 结果
     */
    public int deleteWorkflowSecretaryRecordById(String id);

    /**
     * 批量删除流程办理秘书记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowSecretaryRecordByIds(String[] ids);

    /**
     * 批量插入秘书记录
     *
     * @param secretaryRecords 秘书记录
     */
    void batchInsertWorkflowSecretaryRecord(List<WorkflowSecretaryRecord> secretaryRecords);
}
