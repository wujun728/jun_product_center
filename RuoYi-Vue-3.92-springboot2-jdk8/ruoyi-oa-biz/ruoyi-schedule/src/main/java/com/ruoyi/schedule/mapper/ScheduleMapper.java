package com.ruoyi.schedule.mapper;

import java.util.List;
import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.qo.ScheduleUpdateQo;
import com.ruoyi.schedule.module.ScheduleInfoResult;

/**
 * 日程Mapper接口
 * 
 * @author wocurr.com
 */
public interface ScheduleMapper {
    /**
     * 查询日程
     * 
     * @param id 日程主键
     * @return 日程
     */
    public Schedule selectScheduleById(String id);

    /**
     * 查询日程列表
     * 
     * @param schedule 日程
     * @return 日程集合
     */
    public List<Schedule> selectScheduleList(Schedule schedule);

    /**
     * 查询日程列表
     * @param schedule
     * @return
     */
    public List<Schedule> selectMonthScheduleList(Schedule schedule);

    /**
     * 查询需要提醒的日程
     * @param schedule
     * @return
     */
    public List<Schedule> selectRemindList(Schedule schedule);

    /**
     * 查询需要重复的日程
     * @return
     */
    public List<Schedule> selectRepeatList();

    /**
     * 新增日程
     * 
     * @param schedule 日程
     * @return 结果
     */
    public int insertSchedule(Schedule schedule);

    /**
     * 批量新增日程
     * @param list
     * @return
     */
    public int batchInsertSchedule(List<Schedule> list);

    /**
     * 修改日程
     * 
     * @param schedule 日程
     * @return 结果
     */
    public int updateSchedule(Schedule schedule);

    /**
     * 删除日程
     * 
     * @param id 日程主键
     * @return 结果
     */
    public int deleteScheduleById(String id);

    /**
     * 批量删除日程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScheduleByIds(String[] ids);

    /**
     * 批量删除日程
     *
     * @param qo 更新参数
     * @return 结果
     */
    int updateDelFlagByIds(ScheduleUpdateQo qo);


    /**
     * 批量更新重复标识
     * @param qo
     * @return
     */
    int updateRepeatFlag(ScheduleUpdateQo qo);
}
