package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserInterview;

/**
 * 面试汇总Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrUserInterviewService 
{
    /**
     * 查询面试汇总
     * 
     * @param id 面试汇总主键
     * @return 面试汇总
     */
    public HrUserInterview selectHrUserInterviewById(String id);

    /**
     * 查询面试汇总列表
     * 
     * @param hrUserInterview 面试汇总
     * @return 面试汇总集合
     */
    public List<HrUserInterview> selectHrUserInterviewList(HrUserInterview hrUserInterview);

    /**
     * 新增面试汇总
     * 
     * @param hrUserInterview 面试汇总
     * @return 结果
     */
    public int insertHrUserInterview(HrUserInterview hrUserInterview);

    /**
     * 修改面试汇总
     * 
     * @param hrUserInterview 面试汇总
     * @return 结果
     */
    public int updateHrUserInterview(HrUserInterview hrUserInterview);

    /**
     * 批量删除面试汇总
     * 
     * @param ids 需要删除的面试汇总主键集合
     * @return 结果
     */
    public int deleteHrUserInterviewByIds(String[] ids);

    /**
     * 删除面试汇总信息
     * 
     * @param id 面试汇总主键
     * @return 结果
     */
    public int deleteHrUserInterviewById(String id);
}
