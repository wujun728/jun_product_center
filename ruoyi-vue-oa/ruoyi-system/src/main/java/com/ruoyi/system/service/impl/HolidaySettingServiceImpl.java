package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.system.mapper.HolidaySettingMapper;
import com.ruoyi.system.domain.HolidaySetting;
import com.ruoyi.system.service.IHolidaySettingService;

/**
 * 假期设置Service业务层处理
 * 
 * @author wocurr.com
 * @date 2025-05-22
 */
@Slf4j
@Service
public class HolidaySettingServiceImpl implements IHolidaySettingService {
    @Autowired
    private HolidaySettingMapper holidaySettingMapper;

    /**
     * 查询假期设置
     * 
     * @param id 假期设置主键
     * @return 假期设置
     */
    @Override
    public HolidaySetting getHolidaySettingById(String id) {
        return holidaySettingMapper.selectHolidaySettingById(id);
    }

    /**
     * 查询假期设置列表
     * 
     * @param holidaySetting 假期设置
     * @return 假期设置
     */
    @Override
    public List<HolidaySetting> listHolidaySetting(HolidaySetting holidaySetting) {
        return holidaySettingMapper.selectHolidaySettingList(holidaySetting);
    }

    @Override
    public List<HolidaySetting> getHolidayByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        return holidaySettingMapper.selectHolidayByIds(ids);
    }

    @Override
    public List<HolidaySetting> getMonthHoliday(HolidaySetting holidaySetting) {
        Date startTime = DateUtils.getFirstDayOfMonth(holidaySetting.getStartDate());
        Date endTime = DateUtils.getLastDayOfMonth(holidaySetting.getStartDate());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("beginStartDate", startTime);
        params.put("endStartDate", endTime);
        holidaySetting.setParams(params);
        return listHolidaySetting(holidaySetting);
    }

    @Override
    public List<HolidaySetting> getYearHoliday(String year) {
        if (StringUtils.isBlank(year)) {
            return null;
        }
        return holidaySettingMapper.getYearHoliday(year);
    }

    /**
     * 新增假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    @Override
    public int saveHolidaySetting(HolidaySetting holidaySetting) {
        holidaySetting.setId(IdUtils.fastSimpleUUID());
        holidaySetting.setCreateId(SecurityUtils.getUserId());
        holidaySetting.setCreateTime(DateUtils.getNowDate());
        return holidaySettingMapper.insertHolidaySetting(holidaySetting);
    }

    /**
     * 修改假期设置
     * 
     * @param holidaySetting 假期设置
     * @return 结果
     */
    @Override
    public int updateHolidaySetting(HolidaySetting holidaySetting) {
        holidaySetting.setUpdateTime(DateUtils.getNowDate());
        holidaySetting.setUpdateId(SecurityUtils.getUserId());
        return holidaySettingMapper.updateHolidaySetting(holidaySetting);
    }

    /**
     * 批量删除假期设置
     * 
     * @param ids 需要删除的假期设置主键
     * @return 结果
     */
    @Override
    public int deleteHolidaySettingByIds(String[] ids) {
        return holidaySettingMapper.deleteHolidaySettingByIds(ids);
    }
}
