package com.ruoyi.schedule.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.schedule.domain.ScheduleParts;
import com.ruoyi.schedule.mapper.SchedulePartsMapper;
import com.ruoyi.schedule.service.ISchedulePartsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日程参与人Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class SchedulePartsServiceImpl implements ISchedulePartsService {
    @Autowired
    private SchedulePartsMapper schedulePartsMapper;

    /**
     * 查询日程参与人
     *
     * @param id 日程参与人主键
     * @return 日程参与人
     */
    @Override
    public ScheduleParts getSchedulePartsById(String id) {
        return schedulePartsMapper.selectSchedulePartsById(id);
    }

    /**
     * 查询日程参与人列表
     *
     * @param scheduleParts 日程参与人
     * @return 日程参与人
     */
    @Override
    public List<ScheduleParts> listScheduleParts(ScheduleParts scheduleParts) {
        return schedulePartsMapper.selectSchedulePartsList(scheduleParts);
    }

    @Override
    public List<ScheduleParts> listSchedulePartsByScheduleIds(List<String> scheduleIds) {
        if (CollectionUtils.isEmpty(scheduleIds)) {
            return Collections.emptyList();
        }
        return schedulePartsMapper.selectSchedulePartsByScheduleIds(scheduleIds);
    }

    /**
     * 新增日程参与人
     *
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    @Override
    public int saveScheduleParts(ScheduleParts scheduleParts) {
        scheduleParts.setId(IdUtils.fastSimpleUUID());
        scheduleParts.setCreateTime(DateUtils.getNowDate());
        return schedulePartsMapper.insertScheduleParts(scheduleParts);
    }

    /**
     * 修改日程参与人
     *
     * @param scheduleParts 日程参与人
     * @return 结果
     */
    @Override
    public int updateScheduleParts(ScheduleParts scheduleParts) {
        return schedulePartsMapper.updateScheduleParts(scheduleParts);
    }

    /**
     * 批量删除日程参与人
     *
     * @param ids 需要删除的日程参与人主键
     * @return 结果
     */
    @Override
    public int deleteSchedulePartsByIds(String[] ids) {
        return schedulePartsMapper.deleteSchedulePartsByIds(ids);
    }

    /**
     * 删除日程参与人信息
     *
     * @param scheduleId 日程ID
     * @return 结果
     */
    @Override
    public int deleteByScheduleId(String scheduleId) {
        if (StringUtils.isBlank(scheduleId)) {
            return 0;
        }
        return schedulePartsMapper.updateDelFlagByScheduleIds(Collections.singletonList(scheduleId));
    }

    @Override
    public int saveBatchScheduleParts(List<ScheduleParts> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        return schedulePartsMapper.batchInsertScheduleParts(list);
    }

    /**
     * 批量新增日程参与人
     *
     * @param scheduleId 日程ID
     * @param userIdList 参与人ID列表
     * @return 批量新增结果
     */
    @Override
    public int saveBatchScheduleParts(String scheduleId, List<String> userIdList) {
        if (StringUtils.isBlank(scheduleId) || CollectionUtils.isEmpty(userIdList)) {
            return 0;
        }
        String createId = SecurityUtils.getUserId();
        List<ScheduleParts> list = userIdList.stream().map(userId -> {
            ScheduleParts scheduleParts = new ScheduleParts();
            scheduleParts.setId(IdUtils.fastSimpleUUID());
            scheduleParts.setScheduleId(scheduleId);
            scheduleParts.setUserId(userId);
            scheduleParts.setCreateId(createId);
            scheduleParts.setCreateTime(DateUtils.getNowDate());
            return scheduleParts;
        }).collect(Collectors.toList());
        return schedulePartsMapper.batchInsertScheduleParts(list);
    }
}
