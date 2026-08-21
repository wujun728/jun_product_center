package com.ruoyi.workfile.mapper;


import com.ruoyi.workfile.domain.WorkflowAttachment;

import java.util.List;

/**
 * 流程业务附件Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-03-18
 */
public interface WorkflowAttachmentMapper {
    /**
     * 查询流程业务附件
     * 
     * @param id 流程业务附件主键
     * @return 流程业务附件
     */
    public WorkflowAttachment selectWorkflowAttachmentById(String id);

    /**
     * 根据业务id查询流程业务附件
     * @param businessId
     * @return
     */
    public List<WorkflowAttachment> selectWorkflowAttachmentByBusinessId(String businessId);

    /**
     * 查询流程业务附件列表
     * 
     * @param workflowAttachment 流程业务附件
     * @return 流程业务附件集合
     */
    public List<WorkflowAttachment> selectWorkflowAttachmentList(WorkflowAttachment workflowAttachment);

    /**
     * 新增流程业务附件
     * 
     * @param workflowAttachment 流程业务附件
     * @return 结果
     */
    public int insertWorkflowAttachment(WorkflowAttachment workflowAttachment);

    /**
     * 修改流程业务附件
     * 
     * @param workflowAttachment 流程业务附件
     * @return 结果
     */
    public int updateWorkflowAttachment(WorkflowAttachment workflowAttachment);

    /**
     * 删除流程业务附件
     * 
     * @param id 流程业务附件主键
     * @return 结果
     */
    public int deleteWorkflowAttachmentById(String id);

    /**
     * 删除附件
     * @param workflowAttachment
     * @return
     */
    public int remove(WorkflowAttachment workflowAttachment);

    /**
     * 批量删除流程业务附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowAttachmentByIds(String[] ids);
}
