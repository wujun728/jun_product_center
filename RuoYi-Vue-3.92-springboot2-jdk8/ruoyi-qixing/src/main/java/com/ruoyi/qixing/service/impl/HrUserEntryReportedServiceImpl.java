package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserEntryReportedMapper;
import com.ruoyi.qixing.domain.HrUserEntryReported;
import com.ruoyi.qixing.service.IHrUserEntryReportedService;

/**
 * 入职报道Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserEntryReportedServiceImpl implements IHrUserEntryReportedService
{
    @Autowired
    private HrUserEntryReportedMapper hrUserEntryReportedMapper;

    /**
     * 查询入职报道
     *
     * @param id 入职报道主键
     * @return 入职报道
     */
    @Override
    public HrUserEntryReported selectHrUserEntryReportedById(String id)
    {
        return hrUserEntryReportedMapper.selectHrUserEntryReportedById(id);
    }

    /**
     * 查询入职报道列表
     *
     * @param hrUserEntryReported 入职报道
     * @return 入职报道
     */
    @Override
    public List<HrUserEntryReported> selectHrUserEntryReportedList(HrUserEntryReported hrUserEntryReported)
    {
        return hrUserEntryReportedMapper.selectHrUserEntryReportedList(hrUserEntryReported);
    }

    /**
     * 新增入职报道
     *
     * @param hrUserEntryReported 入职报道
     * @return 结果
     */
    @Override
    public int insertHrUserEntryReported(HrUserEntryReported hrUserEntryReported)
    {if (hrUserEntryReported.getId() == null || hrUserEntryReported.getId().length() == 0)
        {
            hrUserEntryReported.setId(String.valueOf(IdWorker.getId()));
        }

        hrUserEntryReported.setCreateTime(DateUtils.getNowDate());
        return hrUserEntryReportedMapper.insertHrUserEntryReported(hrUserEntryReported);
    }

    /**
     * 修改入职报道
     *
     * @param hrUserEntryReported 入职报道
     * @return 结果
     */
    @Override
    public int updateHrUserEntryReported(HrUserEntryReported hrUserEntryReported)
    {
        hrUserEntryReported.setUpdateTime(DateUtils.getNowDate());
        return hrUserEntryReportedMapper.updateHrUserEntryReported(hrUserEntryReported);
    }

    /**
     * 批量删除入职报道
     *
     * @param ids 需要删除的入职报道主键
     * @return 结果
     */
    @Override
    public int deleteHrUserEntryReportedByIds(String[] ids)
    {
        return hrUserEntryReportedMapper.deleteHrUserEntryReportedByIds(ids);
    }

    /**
     * 删除入职报道信息
     *
     * @param id 入职报道主键
     * @return 结果
     */
    @Override
    public int deleteHrUserEntryReportedById(String id)
    {
        return hrUserEntryReportedMapper.deleteHrUserEntryReportedById(id);
    }
}
