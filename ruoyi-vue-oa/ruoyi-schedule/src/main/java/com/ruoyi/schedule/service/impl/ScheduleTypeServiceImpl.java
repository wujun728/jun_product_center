package com.ruoyi.schedule.service.impl;

import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.schedule.domain.ScheduleType;
import com.ruoyi.schedule.domain.qo.ScheduleTypeUpdateQo;
import com.ruoyi.schedule.mapper.ScheduleTypeMapper;
import com.ruoyi.schedule.service.IScheduleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日程分类Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class ScheduleTypeServiceImpl implements IScheduleTypeService {
    @Autowired
    private ScheduleTypeMapper scheduleTypeMapper;

    /**
     * 查询日程分类
     * 
     * @param id 日程分类主键
     * @return 日程分类
     */
    @Override
    public ScheduleType getScheduleTypeById(String id) {
        return scheduleTypeMapper.selectScheduleTypeById(id);
    }

    /**
     * 查询日程分类列表
     * 
     * @param scheduleType 日程分类
     * @return 日程分类
     */
    @Override
    public List<ScheduleType> listScheduleType(ScheduleType scheduleType) {
        return scheduleTypeMapper.selectScheduleTypeList(scheduleType);
    }

    /**
     * 批量查询日程分类
     *
     * @param ids 日程分类主键
     * @return 日程分类
     */
    @Override
    public List<ScheduleType> listByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
             return null;
        }
        return scheduleTypeMapper.selectByIds(ids);
    }

    /**
     * 查询个人日程分类
     *
     * @return 日程分类
     */
    @Override
    public List<ScheduleType> listPersonAll() {
        ScheduleType scheduleType = new ScheduleType();
        scheduleType.setCreateId(SecurityUtils.getUserId());
        return scheduleTypeMapper.selectScheduleTypeList(scheduleType);
    }

    /**
     * 新增日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     */
    @Override
    public int saveScheduleType(ScheduleType scheduleType) {
        scheduleType.setId(IdUtils.fastSimpleUUID());
        scheduleType.setCreateId(SecurityUtils.getUserId());
        scheduleType.setCreateTime(DateUtils.getNowDate());
        return scheduleTypeMapper.insertScheduleType(scheduleType);
    }

    /**
     * 修改日程分类
     * 
     * @param scheduleType 日程分类
     * @return 结果
     *      */
    @Override
    public int updateScheduleType(ScheduleType scheduleType) {
        scheduleType.setUpdateId(SecurityUtils.getUserId());
        scheduleType.setUpdateTime(DateUtils.getNowDate());
        return scheduleTypeMapper.updateScheduleType(scheduleType);
    }

    /**
     * 批量删除日程分类
     * 
     * @param ids 需要删除的日程分类主键
     * @return 结果
     */
    @Override
    public int deleteScheduleTypeByIds(String[] ids) {
        return updateDelFlagByIds(ids);
    }

    /**
     * 批量删除日程分类信息
     *
     * @param ids 需要删除的日程主键
     * @return
     */
    private int updateDelFlagByIds(String[] ids) {
        ScheduleTypeUpdateQo qo = new ScheduleTypeUpdateQo();
        qo.setIds(ids);
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserId());
        qo.setUpdateTime(DateUtils.getNowDate());
        return scheduleTypeMapper.updateDelFlagByIds(qo);
    }
}
