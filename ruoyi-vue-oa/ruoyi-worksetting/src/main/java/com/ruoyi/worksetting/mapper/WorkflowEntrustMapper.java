package com.ruoyi.worksetting.mapper;

import java.util.List;
import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustQo;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustRelationQo;
import com.ruoyi.worksetting.domain.qo.WorkflowEntrustUpdateQo;

/**
 * 流程办理委托Mapper接口
 * 
 * @author wocurr.com
 */
public interface WorkflowEntrustMapper {
    /**
     * 查询流程办理委托
     * 
     * @param id 流程办理委托主键
     * @return 流程办理委托
     */
    public WorkflowEntrust selectWorkflowEntrustById(String id);

    /**
     * 查询流程办理委托列表
     * 
     * @param workflowEntrust 流程办理委托
     * @return 流程办理委托集合
     */
    public List<WorkflowEntrust> selectWorkflowEntrustList(WorkflowEntrust workflowEntrust);

    /**
     * 新增流程办理委托
     * 
     * @param workflowEntrust 流程办理委托
     * @return 结果
     */
    public int insertWorkflowEntrust(WorkflowEntrust workflowEntrust);

    /**
     * 修改流程办理委托
     * 
     * @param workflowEntrust 流程办理委托
     * @return 结果
     */
    public int updateWorkflowEntrust(WorkflowEntrust workflowEntrust);

    /**
     * 更新状态
     * @param workflowEntrust
     * @return
     */
    public int updateEnableFlag(WorkflowEntrust workflowEntrust);

    /**
     * 删除流程办理委托
     * 
     * @param id 流程办理委托主键
     * @return 结果
     */
    public int deleteWorkflowEntrustById(String id);

    /**
     * 批量删除流程办理委托
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkflowEntrustByIds(String[] ids);

    /**
     * 查询委托关系集合
     *
     * @param qo 查询条件
     * @return 委托集合
     */
    List<WorkflowEntrust> selectListByEntrustIds(WorkflowEntrustQo qo);

    /**
     * 按委托条件查询委托列表
     *
     * @param qo 委托条件
     * @return 委托集合
     */
    List<WorkflowEntrust> selectByEntrustRelation(WorkflowEntrustRelationQo qo);

    /**
     * 批量查询
     *
     * @param ids id集合
     * @return
     */
    List<WorkflowEntrust> selectWorkflowEntrustByIds(String[] ids);

    /**
     * 批量更新删除标识
     *
     * @param qo 更新参数
     * @return
     */
    int updateDelFlagByIds(WorkflowEntrustUpdateQo qo);
}
