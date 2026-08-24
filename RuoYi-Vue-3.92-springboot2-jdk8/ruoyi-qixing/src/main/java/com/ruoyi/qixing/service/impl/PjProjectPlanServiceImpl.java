package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectPlanMapper;
import com.ruoyi.qixing.domain.PjProjectPlan;
import com.ruoyi.qixing.service.IPjProjectPlanService;

/**
 * 项目计划Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectPlanServiceImpl implements IPjProjectPlanService
{
    @Autowired
    private PjProjectPlanMapper pjProjectPlanMapper;

    /**
     * 查询项目计划
     *
     * @param id 项目计划主键
     * @return 项目计划
     */
    @Override
    public PjProjectPlan selectPjProjectPlanById(String id)
    {
        return pjProjectPlanMapper.selectPjProjectPlanById(id);
    }

    /**
     * 查询项目计划列表
     *
     * @param pjProjectPlan 项目计划
     * @return 项目计划
     */
    @Override
    public List<PjProjectPlan> selectPjProjectPlanList(PjProjectPlan pjProjectPlan)
    {
        return pjProjectPlanMapper.selectPjProjectPlanList(pjProjectPlan);
    }

    /**
     * 新增项目计划
     *
     * @param pjProjectPlan 项目计划
     * @return 结果
     */
    @Override
    public int insertPjProjectPlan(PjProjectPlan pjProjectPlan)
    {if (pjProjectPlan.getId() == null || pjProjectPlan.getId().length() == 0)
        {
            pjProjectPlan.setId(String.valueOf(IdWorker.getId()));
        }

        pjProjectPlan.setCreateTime(DateUtils.getNowDate());
        return pjProjectPlanMapper.insertPjProjectPlan(pjProjectPlan);
    }

    /**
     * 修改项目计划
     *
     * @param pjProjectPlan 项目计划
     * @return 结果
     */
    @Override
    public int updatePjProjectPlan(PjProjectPlan pjProjectPlan)
    {
        pjProjectPlan.setUpdateTime(DateUtils.getNowDate());
        return pjProjectPlanMapper.updatePjProjectPlan(pjProjectPlan);
    }

    /**
     * 批量删除项目计划
     *
     * @param ids 需要删除的项目计划主键
     * @return 结果
     */
    @Override
    public int deletePjProjectPlanByIds(String[] ids)
    {
        return pjProjectPlanMapper.deletePjProjectPlanByIds(ids);
    }

    /**
     * 删除项目计划信息
     *
     * @param id 项目计划主键
     * @return 结果
     */
    @Override
    public int deletePjProjectPlanById(String id)
    {
        return pjProjectPlanMapper.deletePjProjectPlanById(id);
    }
}
