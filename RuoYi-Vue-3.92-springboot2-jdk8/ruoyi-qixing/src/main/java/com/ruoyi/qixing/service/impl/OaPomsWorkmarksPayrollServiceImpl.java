package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaPomsWorkmarksPayrollMapper;
import com.ruoyi.qixing.domain.OaPomsWorkmarksPayroll;
import com.ruoyi.qixing.service.IOaPomsWorkmarksPayrollService;

/**
 * 工资审核发放Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaPomsWorkmarksPayrollServiceImpl implements IOaPomsWorkmarksPayrollService
{
    @Autowired
    private OaPomsWorkmarksPayrollMapper oaPomsWorkmarksPayrollMapper;

    /**
     * 查询工资审核发放
     *
     * @param id 工资审核发放主键
     * @return 工资审核发放
     */
    @Override
    public OaPomsWorkmarksPayroll selectOaPomsWorkmarksPayrollById(String id)
    {
        return oaPomsWorkmarksPayrollMapper.selectOaPomsWorkmarksPayrollById(id);
    }

    /**
     * 查询工资审核发放列表
     *
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 工资审核发放
     */
    @Override
    public List<OaPomsWorkmarksPayroll> selectOaPomsWorkmarksPayrollList(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        return oaPomsWorkmarksPayrollMapper.selectOaPomsWorkmarksPayrollList(oaPomsWorkmarksPayroll);
    }

    /**
     * 新增工资审核发放
     *
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 结果
     */
    @Override
    public int insertOaPomsWorkmarksPayroll(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        oaPomsWorkmarksPayroll.setCreateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksPayrollMapper.insertOaPomsWorkmarksPayroll(oaPomsWorkmarksPayroll);
    }

    /**
     * 修改工资审核发放
     *
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 结果
     */
    @Override
    public int updateOaPomsWorkmarksPayroll(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        oaPomsWorkmarksPayroll.setUpdateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksPayrollMapper.updateOaPomsWorkmarksPayroll(oaPomsWorkmarksPayroll);
    }

    /**
     * 批量删除工资审核发放
     *
     * @param ids 需要删除的工资审核发放主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksPayrollByIds(String[] ids)
    {
        return oaPomsWorkmarksPayrollMapper.deleteOaPomsWorkmarksPayrollByIds(ids);
    }

    /**
     * 删除工资审核发放信息
     *
     * @param id 工资审核发放主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksPayrollById(String id)
    {
        return oaPomsWorkmarksPayrollMapper.deleteOaPomsWorkmarksPayrollById(id);
    }
}
