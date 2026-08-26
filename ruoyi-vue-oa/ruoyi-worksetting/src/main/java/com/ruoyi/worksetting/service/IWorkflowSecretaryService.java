package com.ruoyi.worksetting.service;

import java.util.List;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.vo.WorkflowSecretaryVO;

/**
 * 流程办理秘书Service接口
 * 
 * @author ruoyi
 */
public interface IWorkflowSecretaryService {
    /**
     * 查询流程办理秘书
     * 
     * @param id 流程办理秘书主键
     * @return 流程办理秘书
     */
    public WorkflowSecretary getWorkflowSecretaryById(String id);

    /**
     * 查询流程办理秘书
     * @param id
     * @return
     */
    public WorkflowSecretaryVO getWorkflowSecretaryVOById(String id);

    /**
     * 查询流程办理秘书列表
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 流程办理秘书集合
     */
    public List<WorkflowSecretary> listWorkflowSecretary(WorkflowSecretary workflowSecretary);

    /**
     * 新增流程办理秘书
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    public int saveWorkflowSecretary(WorkflowSecretaryVO workflowSecretary);

    /**
     * 修改流程办理秘书
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    public int updateWorkflowSecretary(WorkflowSecretaryVO workflowSecretary);

    /**
     * 批量删除流程办理秘书
     * 
     * @param ids 需要删除的流程办理秘书主键集合
     * @return 结果
     */
    public int deleteWorkflowSecretaryByIds(String[] ids);

    /**
     * 查询领导秘书
     *
     * @param leaderIds 领导ID集合
     * @return 结果
     */
    List<WorkflowSecretary> listByLeaderIds(List<String> leaderIds);

    /**
     * 修改启用状态
     * @param workflowSecretary
     * @return
     */
    public int changeEnableFlag(WorkflowSecretary workflowSecretary);
}
