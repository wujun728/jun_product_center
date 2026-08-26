package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.HolidayWorkSetting;

/**
 * 节假日补班设置Mapper接口
 * 
 * @author wocurr.com
 */
public interface HolidayWorkSettingMapper {
    /**
     * 查询节假日补班设置
     * 
     * @param id 节假日补班设置主键
     * @return 节假日补班设置
     */
    public HolidayWorkSetting selectHolidayWorkSettingById(String id);

    /**
     * 查询节假日补班设置列表
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 节假日补班设置集合
     */
    public List<HolidayWorkSetting> selectHolidayWorkSettingList(HolidayWorkSetting holidayWorkSetting);

    /**
     * 新增节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    public int insertHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting);

    /**
     * 修改节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    public int updateHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting);

    /**
     * 删除节假日补班设置
     * 
     * @param id 节假日补班设置主键
     * @return 结果
     */
    public int deleteHolidayWorkSettingById(String id);

    /**
     * 批量删除节假日补班设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHolidayWorkSettingByIds(String[] ids);
}
