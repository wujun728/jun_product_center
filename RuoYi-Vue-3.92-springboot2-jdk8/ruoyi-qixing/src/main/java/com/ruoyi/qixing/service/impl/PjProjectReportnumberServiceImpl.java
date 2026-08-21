package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectReportnumberMapper;
import com.ruoyi.qixing.domain.PjProjectReportnumber;
import com.ruoyi.qixing.service.IPjProjectReportnumberService;

/**
 * 项目报告文号Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectReportnumberServiceImpl implements IPjProjectReportnumberService
{
    @Autowired
    private PjProjectReportnumberMapper pjProjectReportnumberMapper;

    /**
     * 查询项目报告文号
     *
     * @param id 项目报告文号主键
     * @return 项目报告文号
     */
    @Override
    public PjProjectReportnumber selectPjProjectReportnumberById(String id)
    {
        return pjProjectReportnumberMapper.selectPjProjectReportnumberById(id);
    }

    /**
     * 查询项目报告文号列表
     *
     * @param pjProjectReportnumber 项目报告文号
     * @return 项目报告文号
     */
    @Override
    public List<PjProjectReportnumber> selectPjProjectReportnumberList(PjProjectReportnumber pjProjectReportnumber)
    {
        return pjProjectReportnumberMapper.selectPjProjectReportnumberList(pjProjectReportnumber);
    }

    /**
     * 新增项目报告文号
     *
     * @param pjProjectReportnumber 项目报告文号
     * @return 结果
     */
    @Override
    public int insertPjProjectReportnumber(PjProjectReportnumber pjProjectReportnumber)
    {
        pjProjectReportnumber.setCreateTime(DateUtils.getNowDate());
        return pjProjectReportnumberMapper.insertPjProjectReportnumber(pjProjectReportnumber);
    }

    /**
     * 修改项目报告文号
     *
     * @param pjProjectReportnumber 项目报告文号
     * @return 结果
     */
    @Override
    public int updatePjProjectReportnumber(PjProjectReportnumber pjProjectReportnumber)
    {
        pjProjectReportnumber.setUpdateTime(DateUtils.getNowDate());
        return pjProjectReportnumberMapper.updatePjProjectReportnumber(pjProjectReportnumber);
    }

    /**
     * 批量删除项目报告文号
     *
     * @param ids 需要删除的项目报告文号主键
     * @return 结果
     */
    @Override
    public int deletePjProjectReportnumberByIds(String[] ids)
    {
        return pjProjectReportnumberMapper.deletePjProjectReportnumberByIds(ids);
    }

    /**
     * 删除项目报告文号信息
     *
     * @param id 项目报告文号主键
     * @return 结果
     */
    @Override
    public int deletePjProjectReportnumberById(String id)
    {
        return pjProjectReportnumberMapper.deletePjProjectReportnumberById(id);
    }
}
