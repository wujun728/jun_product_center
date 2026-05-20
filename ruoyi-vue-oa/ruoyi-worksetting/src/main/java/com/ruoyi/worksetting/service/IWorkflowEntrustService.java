package com.ruoyi.worksetting.service;

import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.vo.WorkflowEntrustVO;

import java.util.List;

/**
 * 流程办理委托Service接口
 * 
 * @author wocurr.com
 */
public interface IWorkflowEntrustService {
    /**
     * 查询流程办理委托
     * 
     * @param id 流程办理委托主键
     * @return 流程办理委托
     */
    public WorkflowEntrustVO getWorkflowEntrustById(String id);

    /**
     * 查询流程办理委托列表
     * 
     * @param queryParam
     * @return 流程办理委托集合
     */
    public List<WorkflowEntrustVO> listWorkflowEntrust(WorkflowEntrust queryParam);

    /**
     * 新增流程办理委托
     * 
     * @param workflowEntrustVo 流程办理委托
     * @return 结果
     */
    public int saveWorkflowEntrust(WorkflowEntrustVO workflowEntrustVo);

    /**
     * 修改流程办理委托
     * 
     * @param workflowEntrustVo 流程办理委托
     * @return 结果
     */
    public int updateWorkflowEntrust(WorkflowEntrustVO workflowEntrustVo);

    /**
     * 批量删除流程办理委托
     * 
     * @param ids 需要删除的流程办理委托主键集合
     * @return 结果
     */
    public int deleteWorkflowEntrustByIds(String[] ids);

    /**
     * 按委托人ID查询委托列表
     *
     * @param templateId
     * @param entrustIds 委托人ID集合
     * @return 委托集合
     */
    public List<WorkflowEntrust> listByEntrustIds(String templateId, List<String> entrustIds);

    /**
     * 修改启用状态
     * @param param
     * @return
     */
    public int changeEnableFlag(WorkflowEntrust param);
}
