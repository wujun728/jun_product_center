package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectAppraise;

/**
 * 项目总结及评价Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjProjectAppraiseMapper 
{
    /**
     * 查询项目总结及评价
     * 
     * @param id 项目总结及评价主键
     * @return 项目总结及评价
     */
    public PjProjectAppraise selectPjProjectAppraiseById(String id);

    /**
     * 查询项目总结及评价列表
     * 
     * @param pjProjectAppraise 项目总结及评价
     * @return 项目总结及评价集合
     */
    public List<PjProjectAppraise> selectPjProjectAppraiseList(PjProjectAppraise pjProjectAppraise);

    /**
     * 新增项目总结及评价
     * 
     * @param pjProjectAppraise 项目总结及评价
     * @return 结果
     */
    public int insertPjProjectAppraise(PjProjectAppraise pjProjectAppraise);

    /**
     * 修改项目总结及评价
     * 
     * @param pjProjectAppraise 项目总结及评价
     * @return 结果
     */
    public int updatePjProjectAppraise(PjProjectAppraise pjProjectAppraise);

    /**
     * 删除项目总结及评价
     * 
     * @param id 项目总结及评价主键
     * @return 结果
     */
    public int deletePjProjectAppraiseById(String id);

    /**
     * 批量删除项目总结及评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjProjectAppraiseByIds(String[] ids);
}
