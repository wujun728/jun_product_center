package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.HolidaySetting;

/**
 * 假期设置Mapper接口
 * 
 * @author wocurr.com
 */
public interface HolidaySettingMapper {
    /**
     * 查询假期设置
     * 
     * @param id 假期设置主键
     * @return 假期设置
     */
    public HolidaySetting selectHolidaySettingById(String id);

    /**
     * 查询假期设置列表
     * 
     * @param holidaySetting 假期设置
     * @return 假期设置集合
     */
    public List<HolidaySetting> selectHolidaySettingList(HolidaySetting holidaySetting);

    public List<HolidaySetting> selectHolidayByIds(List<String> ids);

    public List<HolidaySetting> getYearHoliday(String year);

    /**
     * 新增假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    public int insertHolidaySetting(HolidaySetting holidaySetting);

    /**
     * 修改假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    public int updateHolidaySetting(HolidaySetting holidaySetting);

    /**
     * 删除假期设置
     * 
     * @param id 假期设置主键
     * @return 结果
     */
    public int deleteHolidaySettingById(String id);

    /**
     * 批量删除假期设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHolidaySettingByIds(String[] ids);
}
