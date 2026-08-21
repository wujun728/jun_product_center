package com.ruoyi.schedule.service;

import java.util.List;
import com.ruoyi.schedule.domain.ScheduleParts;

/**
 * 日程参与人Service接口
 * 
 * @author wocurr.com
 */
public interface ISchedulePartsService {
    /**
     * 查询日程参与人
     * 
     * @param id 日程参与人主键
     * @return 日程参与人
     */
    public ScheduleParts getSchedulePartsById(String id);

    /**
     * 查询日程参与人列表
     * 
     * @param scheduleParts 日程参与人
     * @return 日程参与人集合
     */
    public List<ScheduleParts> listScheduleParts(ScheduleParts scheduleParts);

    /**
     * 根据日程id查询参与人
     * @param scheduleIds
     * @return
     */
    public List<ScheduleParts> listSchedulePartsByScheduleIds(List<String> scheduleIds);

    /**
     * 新增日程参与人
     * 
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    public int saveScheduleParts(ScheduleParts scheduleParts);

    /**
     * 修改日程参与人
     *
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    public int updateScheduleParts(ScheduleParts scheduleParts);

    /**
     * 批量删除日程参与人
     * 
     * @param ids 需要删除的日程参与人主键集合
     * @return 结果
     */
    public int deleteSchedulePartsByIds(String[] ids);

    /**
     * 根据日程id删除参与人
     * @param scheduleId
     * @return
     */
    public int deleteByScheduleId(String scheduleId);

    /**
     * 批量插入日程参与人
     * @param list
     * @return
     */
    public int saveBatchScheduleParts(List<ScheduleParts> list);

    /**
     * 批量插入日程参与人
     * @param scheduleId
     * @param userIdList
     * @return
     */
    public int saveBatchScheduleParts(String scheduleId, List<String> userIdList);

}
