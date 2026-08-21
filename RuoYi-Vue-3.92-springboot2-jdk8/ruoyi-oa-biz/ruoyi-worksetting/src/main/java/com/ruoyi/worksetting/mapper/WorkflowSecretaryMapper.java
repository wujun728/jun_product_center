package com.ruoyi.worksetting.mapper;

import java.util.List;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.qo.WorkflowSecretaryUpdateQo;

/**
 * 流程办理秘书Mapper接口
 * 
 * @author ruoyi
 */
public interface WorkflowSecretaryMapper {
    /**
     * 查询流程办理秘书
     * 
     * @param id 流程办理秘书主键
     * @return 流程办理秘书
     */
    public WorkflowSecretary selectWorkflowSecretaryById(String id);

    /**
     * 查询流程办理秘书列表
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 流程办理秘书集合
     */
    public List<WorkflowSecretary> selectWorkflowSecretaryList(WorkflowSecretary workflowSecretary);

    /**
     * 新增流程办理秘书
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    public int insertWorkflowSecretary(WorkflowSecretary workflowSecretary);

    /**
     * 修改流程办理秘书
     * 
     * @param workflowSecretary 流程办理秘书
     * @return 结果
     */
    public int updateWorkflowSecretary(WorkflowSecretary workflowSecretary);

    /**
     * 更新状态
     * @param workflowSecretary
     * @return
     */
    public int updateEnableFlag(WorkflowSecretary workflowSecretary);

    /**
     * 删除流程办理秘书
     * 
     * @param id 流程办理秘书主键
     * @return 结果
     */
    public int deleteWorkflowSecretaryById(String id);

    /**
     * 批量删除流程办理秘书
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowSecretaryByIds(String[] ids);

    /**
     * 查询领导秘书
     *
     * @param leaderIds 领导ID集合
     * @return 结果
     */
    List<WorkflowSecretary> selectListByLeaderIds(List<String> leaderIds);

    /**
     * 批量查询领导秘书
     *
     * @param ids 数据主键集合
     * @return
     */
    List<WorkflowSecretary> selectWorkflowSecretaryByIds(String[] ids);

    /**
     * 批量更新删除标志
     *
     * @param qo 更新参数
     * @return
     */
    int updateDelFlagByIds(WorkflowSecretaryUpdateQo qo);
}
