package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserInterviewMapper;
import com.ruoyi.qixing.domain.HrUserInterview;
import com.ruoyi.qixing.service.IHrUserInterviewService;

/**
 * 面试汇总Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserInterviewServiceImpl implements IHrUserInterviewService
{
    @Autowired
    private HrUserInterviewMapper hrUserInterviewMapper;

    /**
     * 查询面试汇总
     *
     * @param id 面试汇总主键
     * @return 面试汇总
     */
    @Override
    public HrUserInterview selectHrUserInterviewById(String id)
    {
        return hrUserInterviewMapper.selectHrUserInterviewById(id);
    }

    /**
     * 查询面试汇总列表
     *
     * @param hrUserInterview 面试汇总
     * @return 面试汇总
     */
    @Override
    public List<HrUserInterview> selectHrUserInterviewList(HrUserInterview hrUserInterview)
    {
        return hrUserInterviewMapper.selectHrUserInterviewList(hrUserInterview);
    }

    /**
     * 新增面试汇总
     *
     * @param hrUserInterview 面试汇总
     * @return 结果
     */
    @Override
    public int insertHrUserInterview(HrUserInterview hrUserInterview)
    {
        hrUserInterview.setCreateTime(DateUtils.getNowDate());
        return hrUserInterviewMapper.insertHrUserInterview(hrUserInterview);
    }

    /**
     * 修改面试汇总
     *
     * @param hrUserInterview 面试汇总
     * @return 结果
     */
    @Override
    public int updateHrUserInterview(HrUserInterview hrUserInterview)
    {
        hrUserInterview.setUpdateTime(DateUtils.getNowDate());
        return hrUserInterviewMapper.updateHrUserInterview(hrUserInterview);
    }

    /**
     * 批量删除面试汇总
     *
     * @param ids 需要删除的面试汇总主键
     * @return 结果
     */
    @Override
    public int deleteHrUserInterviewByIds(String[] ids)
    {
        return hrUserInterviewMapper.deleteHrUserInterviewByIds(ids);
    }

    /**
     * 删除面试汇总信息
     *
     * @param id 面试汇总主键
     * @return 结果
     */
    @Override
    public int deleteHrUserInterviewById(String id)
    {
        return hrUserInterviewMapper.deleteHrUserInterviewById(id);
    }
}
