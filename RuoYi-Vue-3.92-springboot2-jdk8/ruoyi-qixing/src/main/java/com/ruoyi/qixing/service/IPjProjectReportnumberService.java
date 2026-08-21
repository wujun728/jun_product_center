package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectReportnumber;

/**
 * 项目报告文号Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectReportnumberService 
{
    /**
     * 查询项目报告文号
     * 
     * @param id 项目报告文号主键
     * @return 项目报告文号
     */
    public PjProjectReportnumber selectPjProjectReportnumberById(String id);

    /**
     * 查询项目报告文号列表
     * 
     * @param pjProjectReportnumber 项目报告文号
     * @return 项目报告文号集合
     */
    public List<PjProjectReportnumber> selectPjProjectReportnumberList(PjProjectReportnumber pjProjectReportnumber);

    /**
     * 新增项目报告文号
     * 
     * @param pjProjectReportnumber 项目报告文号
     * @return 结果
     */
    public int insertPjProjectReportnumber(PjProjectReportnumber pjProjectReportnumber);

    /**
     * 修改项目报告文号
     * 
     * @param pjProjectReportnumber 项目报告文号
     * @return 结果
     */
    public int updatePjProjectReportnumber(PjProjectReportnumber pjProjectReportnumber);

    /**
     * 批量删除项目报告文号
     * 
     * @param ids 需要删除的项目报告文号主键集合
     * @return 结果
     */
    public int deletePjProjectReportnumberByIds(String[] ids);

    /**
     * 删除项目报告文号信息
     * 
     * @param id 项目报告文号主键
     * @return 结果
     */
    public int deletePjProjectReportnumberById(String id);
}
