package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectReportMapper;
import com.ruoyi.qixing.domain.PjProjectReport;
import com.ruoyi.qixing.service.IPjProjectReportService;

/**
 * 项目报告Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectReportServiceImpl implements IPjProjectReportService
{
    @Autowired
    private PjProjectReportMapper pjProjectReportMapper;

    /**
     * 查询项目报告
     *
     * @param id 项目报告主键
     * @return 项目报告
     */
    @Override
    public PjProjectReport selectPjProjectReportById(String id)
    {
        return pjProjectReportMapper.selectPjProjectReportById(id);
    }

    /**
     * 查询项目报告列表
     *
     * @param pjProjectReport 项目报告
     * @return 项目报告
     */
    @Override
    public List<PjProjectReport> selectPjProjectReportList(PjProjectReport pjProjectReport)
    {
        return pjProjectReportMapper.selectPjProjectReportList(pjProjectReport);
    }

    /**
     * 新增项目报告
     *
     * @param pjProjectReport 项目报告
     * @return 结果
     */
    @Override
    public int insertPjProjectReport(PjProjectReport pjProjectReport)
    {
        pjProjectReport.setCreateTime(DateUtils.getNowDate());
        return pjProjectReportMapper.insertPjProjectReport(pjProjectReport);
    }

    /**
     * 修改项目报告
     *
     * @param pjProjectReport 项目报告
     * @return 结果
     */
    @Override
    public int updatePjProjectReport(PjProjectReport pjProjectReport)
    {
        pjProjectReport.setUpdateTime(DateUtils.getNowDate());
        return pjProjectReportMapper.updatePjProjectReport(pjProjectReport);
    }

    /**
     * 批量删除项目报告
     *
     * @param ids 需要删除的项目报告主键
     * @return 结果
     */
    @Override
    public int deletePjProjectReportByIds(String[] ids)
    {
        return pjProjectReportMapper.deletePjProjectReportByIds(ids);
    }

    /**
     * 删除项目报告信息
     *
     * @param id 项目报告主键
     * @return 结果
     */
    @Override
    public int deletePjProjectReportById(String id)
    {
        return pjProjectReportMapper.deletePjProjectReportById(id);
    }
}
