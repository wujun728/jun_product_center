package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectPlan;

/**
 * 项目计划Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectPlanService 
{
    /**
     * 查询项目计划
     * 
     * @param id 项目计划主键
     * @return 项目计划
     */
    public PjProjectPlan selectPjProjectPlanById(String id);

    /**
     * 查询项目计划列表
     * 
     * @param pjProjectPlan 项目计划
     * @return 项目计划集合
     */
    public List<PjProjectPlan> selectPjProjectPlanList(PjProjectPlan pjProjectPlan);

    /**
     * 新增项目计划
     * 
     * @param pjProjectPlan 项目计划
     * @return 结果
     */
    public int insertPjProjectPlan(PjProjectPlan pjProjectPlan);

    /**
     * 修改项目计划
     * 
     * @param pjProjectPlan 项目计划
     * @return 结果
     */
    public int updatePjProjectPlan(PjProjectPlan pjProjectPlan);

    /**
     * 批量删除项目计划
     * 
     * @param ids 需要删除的项目计划主键集合
     * @return 结果
     */
    public int deletePjProjectPlanByIds(String[] ids);

    /**
     * 删除项目计划信息
     * 
     * @param id 项目计划主键
     * @return 结果
     */
    public int deletePjProjectPlanById(String id);
}
