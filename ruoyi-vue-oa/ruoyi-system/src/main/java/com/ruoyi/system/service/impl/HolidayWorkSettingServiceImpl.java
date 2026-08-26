package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.HolidaySetting;
import com.ruoyi.system.service.IHolidaySettingService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.system.mapper.HolidayWorkSettingMapper;
import com.ruoyi.system.domain.HolidayWorkSetting;
import com.ruoyi.system.service.IHolidayWorkSettingService;

/**
 * 节假日补班设置Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class HolidayWorkSettingServiceImpl implements IHolidayWorkSettingService {
    @Autowired
    private HolidayWorkSettingMapper holidayWorkSettingMapper;
    @Autowired
    private IHolidaySettingService holidaySettingService;

    /**
     * 查询节假日补班设置
     * 
     * @param id 节假日补班设置主键
     * @return 节假日补班设置
     */
    @Override
    public HolidayWorkSetting getHolidayWorkSettingById(String id) {
        return holidayWorkSettingMapper.selectHolidayWorkSettingById(id);
    }

    /**
     * 查询节假日补班设置列表
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 节假日补班设置
     */
    @Override
    public List<HolidayWorkSetting> listHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting) {
        List<HolidayWorkSetting> list = holidayWorkSettingMapper.selectHolidayWorkSettingList(holidayWorkSetting);
        if(CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<String> holidayIds = list.stream().map(HolidayWorkSetting::getHolidaySettingId).collect(Collectors.toList());
        List<HolidaySetting> holidaySettingList = holidaySettingService.getHolidayByIds(holidayIds);
        Map<String, HolidaySetting> holidaySettingMap = holidaySettingList.stream().collect(Collectors.toMap(HolidaySetting::getId, HolidaySetting -> HolidaySetting));
        list.stream().forEach(w -> {
            if (holidaySettingMap.containsKey(w.getHolidaySettingId())) {
                w.setHolidayName(holidaySettingMap.get(w.getHolidaySettingId()).getHolidayName());
            }
        });
        return list;
    }

    @Override
    public List<HolidayWorkSetting> getMonthWorkHoliday(HolidayWorkSetting holidayWorkSetting) {
        Date startTime = DateUtils.getFirstDayOfMonth(holidayWorkSetting.getWorkDate());
        Date endTime = DateUtils.getLastDayOfMonth(holidayWorkSetting.getWorkDate());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("beginStartDate", startTime);
        params.put("endStartDate", endTime);
        holidayWorkSetting.setParams(params);
        return holidayWorkSettingMapper.selectHolidayWorkSettingList(holidayWorkSetting);
    }

    /**
     * 新增节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    @Override
    public int saveHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting) {
        holidayWorkSetting.setId(IdUtils.fastSimpleUUID());
        holidayWorkSetting.setCreateTime(DateUtils.getNowDate());
        holidayWorkSetting.setCreateId(SecurityUtils.getUserId());
        return holidayWorkSettingMapper.insertHolidayWorkSetting(holidayWorkSetting);
    }

    /**
     * 修改节假日补班设置
     * 
     * @param holidayWorkSetting 节假日补班设置
     * @return 结果
     */
    @Override
    public int updateHolidayWorkSetting(HolidayWorkSetting holidayWorkSetting) {
        holidayWorkSetting.setUpdateId(SecurityUtils.getUserId());
        holidayWorkSetting.setUpdateTime(DateUtils.getNowDate());
        return holidayWorkSettingMapper.updateHolidayWorkSetting(holidayWorkSetting);
    }

    /**
     * 批量删除节假日补班设置
     * 
     * @param ids 需要删除的节假日补班设置主键
     * @return 结果
     */
    @Override
    public int deleteHolidayWorkSettingByIds(String[] ids) {
        return holidayWorkSettingMapper.deleteHolidayWorkSettingByIds(ids);
    }
}
