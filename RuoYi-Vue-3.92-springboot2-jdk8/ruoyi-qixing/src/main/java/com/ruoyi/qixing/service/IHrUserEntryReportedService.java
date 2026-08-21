package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserEntryReported;

/**
 * 入职报道Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrUserEntryReportedService 
{
    /**
     * 查询入职报道
     * 
     * @param id 入职报道主键
     * @return 入职报道
     */
    public HrUserEntryReported selectHrUserEntryReportedById(String id);

    /**
     * 查询入职报道列表
     * 
     * @param hrUserEntryReported 入职报道
     * @return 入职报道集合
     */
    public List<HrUserEntryReported> selectHrUserEntryReportedList(HrUserEntryReported hrUserEntryReported);

    /**
     * 新增入职报道
     * 
     * @param hrUserEntryReported 入职报道
     * @return 结果
     */
    public int insertHrUserEntryReported(HrUserEntryReported hrUserEntryReported);

    /**
     * 修改入职报道
     * 
     * @param hrUserEntryReported 入职报道
     * @return 结果
     */
    public int updateHrUserEntryReported(HrUserEntryReported hrUserEntryReported);

    /**
     * 批量删除入职报道
     * 
     * @param ids 需要删除的入职报道主键集合
     * @return 结果
     */
    public int deleteHrUserEntryReportedByIds(String[] ids);

    /**
     * 删除入职报道信息
     * 
     * @param id 入职报道主键
     * @return 结果
     */
    public int deleteHrUserEntryReportedById(String id);
}
