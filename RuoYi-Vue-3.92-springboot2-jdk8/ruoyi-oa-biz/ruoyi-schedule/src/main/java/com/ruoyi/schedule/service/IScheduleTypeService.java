package com.ruoyi.schedule.service;

import java.util.List;
import com.ruoyi.schedule.domain.ScheduleType;

/**
 * 日程分类Service接口
 * 
 * @author wocurr.com
 */
public interface IScheduleTypeService {
    /**
     * 查询日程分类
     * 
     * @param id 日程分类主键
     * @return 日程分类
     */
    public ScheduleType getScheduleTypeById(String id);

    /**
     * 查询日程分类列表
     * 
     * @param scheduleType 日程分类
     * @return 日程分类集合
     */
    public List<ScheduleType> listScheduleType(ScheduleType scheduleType);

    /**
     * 根据id集合查询日程分类
     * @param ids
     * @return
     */
    public List<ScheduleType> listByIds(List<String> ids);

    /**
     * 获取个人所有日程分类
     * @return
     */
    public List<ScheduleType> listPersonAll();

    /**
     * 新增日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     */
    public int saveScheduleType(ScheduleType scheduleType);

    /**
     * 修改日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     */
    public int updateScheduleType(ScheduleType scheduleType);

    /**
     * 批量删除日程分类
     * 
     * @param ids 需要删除的日程分类主键集合
     * @return 结果
     */
    public int deleteScheduleTypeByIds(String[] ids);
}
