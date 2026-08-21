package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HolidaySetting;

/**
 * 假期设置Service接口
 * 
 * @author wocurr.com
 */
public interface IHolidaySettingService {
    /**
     * 查询假期设置
     * 
     * @param id 假期设置主键
     * @return 假期设置
     */
    public HolidaySetting getHolidaySettingById(String id);

    /**
     * 查询假期设置列表
     * 
     * @param holidaySetting 假期设置
     * @return 假期设置集合
     */
    public List<HolidaySetting> listHolidaySetting(HolidaySetting holidaySetting);

    /**
     * 查询指定多个id的假期设置列表
     * @param ids
     * @return
     */
    public List<HolidaySetting> getHolidayByIds(List<String> ids);

    /**
     * 查询指定月份的假期设置列表
     * @param holidaySetting
     * @return
     */
    public List<HolidaySetting> getMonthHoliday(HolidaySetting holidaySetting);

    /**
     * 获取年度假日配置
     * @param year
     * @return
     */
    public List<HolidaySetting> getYearHoliday(String year);

    /**
     * 新增假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    public int saveHolidaySetting(HolidaySetting holidaySetting);

    /**
     * 修改假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    public int updateHolidaySetting(HolidaySetting holidaySetting);

    /**
     * 批量删除假期设置
     * 
     * @param ids 需要删除的假期设置主键集合
     * @return 结果
     */
    public int deleteHolidaySettingByIds(String[] ids);
}
