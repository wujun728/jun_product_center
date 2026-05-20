package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HolidayWorkSetting;

/**
 * 节假日补班设置Service接口
 * 
 * @author wocurr.com
 */
public interface IHolidayWorkSettingService {
    /**
     * 查询节假日补班设置
     * 
     * @param id 节假日补班设置主键
     * @return 节假日补班设置
     */
    public HolidayWorkSetting getHolidayWorkSettingById(String id);

    /**
     * 查询节假日补班设置列表
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 节假日补班设置集合
     */
    public List<HolidayWorkSetting> listHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting);

    /**
     * 查询指定月份的补班数据
     * @param holidayWorkSetting
     * @return
     */
    public List<HolidayWorkSetting> getMonthWorkHoliday(HolidayWorkSetting holidayWorkSetting);

    /**
     * 新增节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    public int saveHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting);

    /**
     * 修改节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    public int updateHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting);

    /**
     * 批量删除节假日补班设置
     * 
     * @param ids 需要删除的节假日补班设置主键集合
     * @return 结果
     */
    public int deleteHolidayWorkSettingByIds(String[] ids);
}
