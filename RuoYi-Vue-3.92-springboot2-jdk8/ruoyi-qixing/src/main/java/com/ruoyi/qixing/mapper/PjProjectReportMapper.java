package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectReport;

/**
 * 项目报告Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjProjectReportMapper 
{
    /**
     * 查询项目报告
     * 
     * @param id 项目报告主键
     * @return 项目报告
     */
    public PjProjectReport selectPjProjectReportById(String id);

    /**
     * 查询项目报告列表
     * 
     * @param pjProjectReport 项目报告
     * @return 项目报告集合
     */
    public List<PjProjectReport> selectPjProjectReportList(PjProjectReport pjProjectReport);

    /**
     * 新增项目报告
     * 
     * @param pjProjectReport 项目报告
     * @return 结果
     */
    public int insertPjProjectReport(PjProjectReport pjProjectReport);

    /**
     * 修改项目报告
     * 
     * @param pjProjectReport 项目报告
     * @return 结果
     */
    public int updatePjProjectReport(PjProjectReport pjProjectReport);

    /**
     * 删除项目报告
     * 
     * @param id 项目报告主键
     * @return 结果
     */
    public int deletePjProjectReportById(String id);

    /**
     * 批量删除项目报告
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjProjectReportByIds(String[] ids);
}
