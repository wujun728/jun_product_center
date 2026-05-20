package com.ruoyi.schedule.mapper;

import java.util.List;
import com.ruoyi.schedule.domain.ScheduleType;
import com.ruoyi.schedule.domain.qo.ScheduleTypeUpdateQo;

/**
 * 日程分类Mapper接口
 * 
 * @author wocurr.com
 */
public interface ScheduleTypeMapper {
    /**
     * 查询日程分类
     * 
     * @param id 日程分类主键
     * @return 日程分类
     */
    public ScheduleType selectScheduleTypeById(String id);

    /**
     * 查询日程分类列表
     * 
     * @param scheduleType 日程分类
     * @return 日程分类集合
     */
    public List<ScheduleType> selectScheduleTypeList(ScheduleType scheduleType);

    /**
     * 根据ids查询日程分类
     * @param ids
     * @return
     */
    public List<ScheduleType> selectByIds(List<String> ids);


    /**
     * 新增日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     */
    public int insertScheduleType(ScheduleType scheduleType);

    /**
     * 修改日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     */
    public int updateScheduleType(ScheduleType scheduleType);

    /**
     * 删除日程分类
     * 
     * @param id 日程分类主键
     * @return 结果
     */
    public int deleteScheduleTypeById(String id);

    /**
     * 批量删除日程分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScheduleTypeByIds(String[] ids);

    /**
     * 批量删除日程分类
     *
     * @param qo 更新参数
     * @return 结果
     */
    int updateDelFlagByIds(ScheduleTypeUpdateQo qo);
}
