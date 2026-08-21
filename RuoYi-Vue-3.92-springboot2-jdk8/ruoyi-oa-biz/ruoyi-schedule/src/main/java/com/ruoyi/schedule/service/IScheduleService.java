package com.ruoyi.schedule.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.ScheduleDTO;
import com.ruoyi.schedule.module.ScheduleInfoResult;
import com.ruoyi.schedule.module.ScheduleParam;

/**
 * 日程Service接口
 *
 * @author wocurr.com
 */
public interface IScheduleService {
    /**
     * 查询日程
     *
     * @param id 日程主键
     * @return 日程
     */
    public Schedule getScheduleById(String id);

    /**
     * 查询日程列表
     *
     * @param schedule 日程
     * @return 日程集合
     */
    public List<Schedule> listSchedule(Schedule schedule);

    /**
     * 新增日程
     *
     * @param schedule 日程
     * @return 结果
     */
    public int saveSchedule(Schedule schedule);

    /**
     * 修改日程
     *
     * @param schedule 日程
     * @return 结果
     */
    public int updateSchedule(Schedule schedule);

    /**
     * 删除日程信息
     *
     * @param id 日程主键
     * @return 结果
     */
    public int deleteScheduleById(String id);

    /**
     * 删除日程
     *
     * @param id
     * @return
     */
    public int remove(String id);

    /**
     * 保存或者更新日程
     *
     * @param schedule
     * @return
     */
    public String saveOrUpdateSchedule(ScheduleParam schedule);

    /**
     * 获取月度日程列表
     *
     * @param schedule
     * @return
     */
    public Map<String, List<ScheduleDTO>> getScheduleMonthMap(Schedule schedule);

    /**
     * 获取有日程的日期列表（按月查询）
     *
     * @param schedule
     * @return
     */
    public List<String> getMonthScheduleList(Schedule schedule);

    /**
     * 获取有日程的日期列表（按天查询）
     *
     * @param schedule
     * @return
     */
    public List<ScheduleDTO> getScheduleDayList(Schedule schedule);

    /**
     * 获取日程详情
     *
     * @param id
     * @return
     */
    public ScheduleInfoResult getScheduleInfoResult(String id);

    /**
     * 日程提醒
     */
    public void remind();

    /**
     * 日程重复
     */
    public void repeat();
}
