package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectPlan;

/**
 * 项目计划Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjProjectPlanMapper 
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
     * 删除项目计划
     * 
     * @param id 项目计划主键
     * @return 结果
     */
    public int deletePjProjectPlanById(String id);

    /**
     * 批量删除项目计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjProjectPlanByIds(String[] ids);
}
