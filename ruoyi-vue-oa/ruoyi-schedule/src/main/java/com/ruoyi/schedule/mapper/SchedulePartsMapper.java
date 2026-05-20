package com.ruoyi.schedule.mapper;

import java.util.List;
import com.ruoyi.schedule.domain.ScheduleParts;

/**
 * 日程参与人Mapper接口
 * 
 * @author wocurr.com
 */
public interface SchedulePartsMapper {
    /**
     * 查询日程参与人
     * 
     * @param id 日程参与人主键
     * @return 日程参与人
     */
    public ScheduleParts selectSchedulePartsById(String id);

    /**
     * 查询日程参与人列表
     * 
     * @param scheduleParts 日程参与人
     * @return 日程参与人集合
     */
    public List<ScheduleParts> selectSchedulePartsList(ScheduleParts scheduleParts);

    /**
     * 根据日程id查询日程参与人
     * @param scheduleIds
     * @return
     */
    public List<ScheduleParts> selectSchedulePartsByScheduleIds(List<String> scheduleIds);

    /**
     * 新增日程参与人
     * 
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    public int insertScheduleParts(ScheduleParts scheduleParts);

    /**
     * 批量插入
     * @param list
     * @return
     */
    public int batchInsertScheduleParts(List<ScheduleParts> list);

    /**
     * 修改日程参与人
     * 
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    public int updateScheduleParts(ScheduleParts scheduleParts);

    /**
     * 删除日程参与人
     * 
     * @param id 日程参与人主键
     * @return 结果
     */
    public int deleteSchedulePartsById(Long id);

    /**
     * 批量删除日程参与人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchedulePartsByIds(String[] ids);

    /**
     * 根据日程id删除
     * @param scheduleId
     * @return
     */
    public int deleteByScheduleId(String scheduleId);

    /**
     * 根据日程id更新删除标记
     *
     * @param scheduleIds 日程id集合
     * @return
     */
    int updateDelFlagByScheduleIds(List<String> scheduleIds);
}
