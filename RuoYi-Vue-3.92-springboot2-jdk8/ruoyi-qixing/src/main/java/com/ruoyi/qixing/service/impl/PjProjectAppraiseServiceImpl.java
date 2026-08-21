package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectAppraiseMapper;
import com.ruoyi.qixing.domain.PjProjectAppraise;
import com.ruoyi.qixing.service.IPjProjectAppraiseService;

/**
 * 项目总结及评价Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectAppraiseServiceImpl implements IPjProjectAppraiseService
{
    @Autowired
    private PjProjectAppraiseMapper pjProjectAppraiseMapper;

    /**
     * 查询项目总结及评价
     *
     * @param id 项目总结及评价主键
     * @return 项目总结及评价
     */
    @Override
    public PjProjectAppraise selectPjProjectAppraiseById(String id)
    {
        return pjProjectAppraiseMapper.selectPjProjectAppraiseById(id);
    }

    /**
     * 查询项目总结及评价列表
     *
     * @param pjProjectAppraise 项目总结及评价
     * @return 项目总结及评价
     */
    @Override
    public List<PjProjectAppraise> selectPjProjectAppraiseList(PjProjectAppraise pjProjectAppraise)
    {
        return pjProjectAppraiseMapper.selectPjProjectAppraiseList(pjProjectAppraise);
    }

    /**
     * 新增项目总结及评价
     *
     * @param pjProjectAppraise 项目总结及评价
     * @return 结果
     */
    @Override
    public int insertPjProjectAppraise(PjProjectAppraise pjProjectAppraise)
    {
        pjProjectAppraise.setCreateTime(DateUtils.getNowDate());
        return pjProjectAppraiseMapper.insertPjProjectAppraise(pjProjectAppraise);
    }

    /**
     * 修改项目总结及评价
     *
     * @param pjProjectAppraise 项目总结及评价
     * @return 结果
     */
    @Override
    public int updatePjProjectAppraise(PjProjectAppraise pjProjectAppraise)
    {
        pjProjectAppraise.setUpdateTime(DateUtils.getNowDate());
        return pjProjectAppraiseMapper.updatePjProjectAppraise(pjProjectAppraise);
    }

    /**
     * 批量删除项目总结及评价
     *
     * @param ids 需要删除的项目总结及评价主键
     * @return 结果
     */
    @Override
    public int deletePjProjectAppraiseByIds(String[] ids)
    {
        return pjProjectAppraiseMapper.deletePjProjectAppraiseByIds(ids);
    }

    /**
     * 删除项目总结及评价信息
     *
     * @param id 项目总结及评价主键
     * @return 结果
     */
    @Override
    public int deletePjProjectAppraiseById(String id)
    {
        return pjProjectAppraiseMapper.deletePjProjectAppraiseById(id);
    }
}
