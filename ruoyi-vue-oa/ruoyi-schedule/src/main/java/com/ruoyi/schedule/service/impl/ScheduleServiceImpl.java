package com.ruoyi.schedule.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.im.chat.enums.BusinessMessageType;
import com.ruoyi.message.service.IBusinessSystemMessageService;
import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.ScheduleDTO;
import com.ruoyi.schedule.domain.ScheduleParts;
import com.ruoyi.schedule.domain.ScheduleType;
import com.ruoyi.schedule.domain.qo.ScheduleUpdateQo;
import com.ruoyi.schedule.enums.PartTypeEnum;
import com.ruoyi.schedule.enums.RemindTimeEnum;
import com.ruoyi.schedule.enums.RepeatTypeEnum;
import com.ruoyi.schedule.mapper.ScheduleMapper;
import com.ruoyi.schedule.mapper.ScheduleSourceTargetMapper;
import com.ruoyi.schedule.module.ScheduleInfoResult;
import com.ruoyi.schedule.module.ScheduleParam;
import com.ruoyi.schedule.service.ISchedulePartsService;
import com.ruoyi.schedule.service.IScheduleService;
import com.ruoyi.schedule.service.IScheduleTypeService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 日程Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private IScheduleTypeService scheduleTypeService;
    @Autowired
    private ISchedulePartsService schedulePartsService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IBusinessSystemMessageService systemMessageService;

    /**
     * 查询日程
     *
     * @param id 日程主键
     * @return 日程
     */
    @Override
    public Schedule getScheduleById(String id) {
        return scheduleMapper.selectScheduleById(id);
    }

    /**
     * 查询日程列表
     *
     * @param schedule 日程
     * @return 日程
     */
    @Override
    public List<Schedule> listSchedule(Schedule schedule) {
        return scheduleMapper.selectScheduleList(schedule);
    }

    @Override
    public void remind() {
        Schedule query = new Schedule();
        query.setStartTime(DateUtils.getNowDate());
        List<Schedule> list = scheduleMapper.selectRemindList(query);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String nowStr = DateUtils.dateTimeNow("yyyy-MM-dd HH:mm");
        // 过滤出需要提醒的日程
        List<Schedule> remindList = list.stream()
                .filter(schedule -> {
                    Date remindTime = schedule.getStartTime();
                    RemindTimeEnum remindTimeEnum = RemindTimeEnum.getRemindTimeEnum(schedule.getRemindType());
                    Date effectiveRemindTime = calculateRemindTime(remindTime, remindTimeEnum);
                    String effectiveRemindTimeStr = DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", effectiveRemindTime);
                    return effectiveRemindTime != null && StringUtils.equals(nowStr, effectiveRemindTimeStr);
                })
                .collect(Collectors.toList());
        //  发送通知
        if (CollectionUtils.isNotEmpty(remindList)) {
            handleRemind(remindList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void repeat() {
        List<Schedule> list = scheduleMapper.selectRepeatList();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        LocalDate currentDate = LocalDate.now();
        // 判断是否为周日
        boolean isSunday = currentDate.getDayOfWeek() == DayOfWeek.SUNDAY;
        // 判断是否为每月1号
        boolean isFirstDayOfMonth = currentDate.getDayOfMonth() == 1;
        // 判断是否为每年1月1号
        boolean isFirstDayOfYear = currentDate.getMonthValue() == 1 && currentDate.getDayOfMonth() == 1;

        List<ScheduleParts> partsList = new ArrayList<>();
        List<Schedule> repeatList = new ArrayList<>();
        List<Schedule> newList = new ArrayList<>();
        for (Schedule original : list) {
            RepeatTypeEnum repeatType = RepeatTypeEnum.getRepeatTypeEnum(original.getRepeatType());
            if (repeatType == null) {
                continue;
            }
            Date startDate = original.getStartTime();
            Date endDate = original.getEndTime();
            Date nextStartDate = null;
            Date nextEndDate = null;
            switch (repeatType) {
                case DAILY:
                    nextStartDate = DateUtils.addDays(startDate, 1);
                    nextEndDate = DateUtils.addDays(endDate, 1);
                    break;
                case WEEKLY:
                    if (isSunday) {
                        nextStartDate = DateUtils.addWeeks(startDate, 1);
                        nextEndDate = DateUtils.addWeeks(endDate, 1);
                    }
                    break;
                case MONTHLY:
                    if (isFirstDayOfMonth) {
                        nextStartDate = DateUtils.addMonths(startDate, 1);
                        nextEndDate = DateUtils.addMonths(endDate, 1);
                    }
                    break;
                case YEARLY:
                    if (isFirstDayOfYear) {
                        nextStartDate = DateUtils.addYears(startDate, 1);
                        nextEndDate = DateUtils.addYears(endDate, 1);
                    }
                    break;
                default:
                    break;
            }
            if (nextStartDate == null || nextEndDate == null) {
                continue;
            }
            // 创建新日程
            String id = IdUtils.fastSimpleUUID();
            Schedule newSchedule = ScheduleSourceTargetMapper.INSTANCE.copy(original);
            newSchedule.setId(id);
            newSchedule.setStartTime(nextStartDate);
            newSchedule.setEndTime(nextEndDate);
            newSchedule.setCreateId(original.getCreateId());
            newSchedule.setCreateTime(DateUtils.getNowDate());
            newSchedule.setUpdateId(null);
            newSchedule.setUpdateTime(null);
            newList.add(newSchedule);
            repeatList.add(original);

            // 复制参与人员
            List<ScheduleParts> schedulePartsList = schedulePartsService.listSchedulePartsByScheduleIds(Arrays.asList(original.getId()));
            if (CollectionUtils.isNotEmpty(schedulePartsList)) {
                schedulePartsList.stream().forEach(scheduleParts -> {
                    scheduleParts.setId(IdUtils.fastSimpleUUID());
                    scheduleParts.setScheduleId(id);
                    partsList.add(scheduleParts);
                });
            }
        }
        if (CollectionUtils.isNotEmpty(newList)) {
            // 批量插入
            scheduleMapper.batchInsertSchedule(newList);

            // 更新当前日程的重复为否
            List<String> ids = repeatList.stream().map(Schedule::getId).collect(Collectors.toList());
            ScheduleUpdateQo qo = new ScheduleUpdateQo();
            qo.setIds(ids);
            scheduleMapper.updateRepeatFlag(qo);

            if (CollectionUtils.isNotEmpty(partsList)) {
                schedulePartsService.saveBatchScheduleParts(partsList);
            }
        }
    }

    /**
     * 新增日程
     *
     * @param schedule 日程
     * @return 结果
     */
    @Override
    public int saveSchedule(Schedule schedule) {
        schedule.setCreateId(SecurityUtils.getUserId());
        schedule.setCreateTime(DateUtils.getNowDate());
        return scheduleMapper.insertSchedule(schedule);
    }

    /**
     * 修改日程
     *
     * @param schedule 日程
     * @return 结果
     */
    @Override
    public int updateSchedule(Schedule schedule) {
        schedule.setUpdateId(SecurityUtils.getUserId());
        schedule.setUpdateTime(DateUtils.getNowDate());
        return scheduleMapper.updateSchedule(schedule);
    }

    /**
     * 删除日程信息
     *
     * @param id 日程主键
     * @return 结果
     */
    @Override
    public int deleteScheduleById(String id) {
        return updateDelFlagByIds(Arrays.asList(id));
    }

    /**
     * 删除日程
     *
     * @param id 需要删除的日程主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("参数错误");
        }
        deleteScheduleById(id);
        schedulePartsService.deleteByScheduleId(id);
        return 0;
    }

    /**
     * 保存日程
     *
     * @param schedule 日程
     * @return 创建的日程id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveOrUpdateSchedule(ScheduleParam schedule) {
        if (schedule == null) {
            throw new BaseException("参数错误");
        }
        // 插入
        if (StringUtils.isBlank(schedule.getId())) {
            String id = IdUtils.fastSimpleUUID();
            schedule.setId(id);
            saveSchedule(schedule);
            handPartUsers(id, schedule.getPartType(), schedule.getJoinUsers());
            return id;
        }
        // 更新
        String id = schedule.getId();
        if (getScheduleById(id) == null) {
            throw new BaseException("日程不存在！");
        }
        schedulePartsService.deleteByScheduleId(id);
        updateSchedule(schedule);
        handPartUsers(id, schedule.getPartType(), schedule.getJoinUsers());
        return id;
    }


    /**
     * 获取月份日程
     *
     * @param schedule 日程
     * @return
     */
    @Override
    public Map<String, List<ScheduleDTO>> getScheduleMonthMap(Schedule schedule) {
        List<Schedule> list = listMonthScheduleList(schedule);
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        List<ScheduleDTO> listDTO = ScheduleSourceTargetMapper.INSTANCE.listToScheduleDTO(list);
        List<String> typeIds = list.stream().map(Schedule::getTypeId).collect(Collectors.toList());
        List<ScheduleType> typeList = scheduleTypeService.listByIds(typeIds);
        Map<String, List<ScheduleType>> typeMap = typeList.stream().collect(Collectors.groupingBy(s -> s.getId()));
        listDTO.stream().forEach(s -> {
            String typeId = s.getTypeId();
            if (typeMap.containsKey(typeId)) {
                s.setScheduleType(typeMap.get(typeId).get(0));
            }
        });
        // 按日期分组
        return listDTO.stream().collect(Collectors.groupingBy(s -> DateUtils.dateTime(s.getStartTime())));
    }

    /**
     * 获取月份日程
     *
     * @param schedule 日程
     * @return
     */
    @Override
    public List<String> getMonthScheduleList(Schedule schedule) {
        List<String> result = new ArrayList<>();
        List<Schedule> list = listMonthScheduleList(schedule);
        list.stream().forEach(s -> {
            String date = DateUtils.dateTime(s.getStartTime());
            if (!result.contains(date)) {
                result.add(date);
            }
        });
        return result;
    }

    /**
     * 获取日程列表
     *
     * @param schedule 日程
     * @return
     */
    @Override
    public List<ScheduleDTO> getScheduleDayList(Schedule schedule) {
        schedule.setCreateId(SecurityUtils.getUserId());
        List<Schedule> list = scheduleMapper.selectMonthScheduleList(schedule);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<ScheduleDTO> listDTO = ScheduleSourceTargetMapper.INSTANCE.listToScheduleDTO(list);
        List<String> typeIds = list.stream().map(Schedule::getTypeId).collect(Collectors.toList());
        List<ScheduleType> typeList = scheduleTypeService.listByIds(typeIds);
        Map<String, List<ScheduleType>> typeMap = typeList.stream().collect(Collectors.groupingBy(s -> s.getId()));
        listDTO.stream().forEach(s -> {
            String typeId = s.getTypeId();
            if (typeMap.containsKey(typeId)) {
                s.setScheduleType(typeMap.get(typeId).get(0));
            }
        });
        return listDTO;
    }

    /**
     * 获取日程详情
     *
     * @param id 日程id
     * @return
     */
    @Override
    public ScheduleInfoResult getScheduleInfoResult(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BaseException("参数错误");
        }
        Schedule schedule = scheduleMapper.selectScheduleById(id);
        if (schedule == null) {
            throw new BaseException("日程不存在！");
        }
        ScheduleInfoResult scheduleInfoResult = ScheduleSourceTargetMapper.INSTANCE.toScheduleInfo(schedule);
        ScheduleType scheduleType = scheduleTypeService.getScheduleTypeById(schedule.getTypeId());
        if (scheduleType != null) {
            scheduleInfoResult.setScheduleType(scheduleType);
        }
        ScheduleParts query = new ScheduleParts();
        query.setScheduleId(id);
        List<ScheduleParts> schedulePartsList = schedulePartsService.listScheduleParts(query);
        if (CollectionUtils.isNotEmpty(schedulePartsList)) {
            List<String> userIds = schedulePartsList.stream().map(ScheduleParts::getUserId).collect(Collectors.toList());
            List<SysUser> users = sysUserService.selectDetailByUserIds(userIds);
            scheduleInfoResult.setJoinUsers(users);
        }
        SysUser createUser = sysUserService.selectUserById(schedule.getCreateId());
        scheduleInfoResult.setCreateBy(createUser.getNickName());
        scheduleInfoResult.setAvatar(createUser.getAvatar());
        return scheduleInfoResult;
    }

    /**
     * 处理参与用户
     *
     * @param id
     * @param partType
     * @param joinUsers
     */
    private void handPartUsers(String id, String partType, List<String> joinUsers) {
        List<String> userList = new ArrayList<>();
        userList.add(SecurityUtils.getUserId());
        if (StringUtils.equals(PartTypeEnum.ORGANIZATION.getCode(), partType)) {
            joinUsers.stream().forEach(userId -> {
                if (!userList.contains(Long.valueOf(userId))) {
                    userList.add(userId);
                }
            });
        }
        schedulePartsService.saveBatchScheduleParts(id, userList);
    }

    /**
     * 获取月度日程
     *
     * @param schedule
     * @return
     */
    private List<Schedule> listMonthScheduleList(Schedule schedule) {
        if (schedule.getStartTime() == null) {
            throw new BaseException("参数错误");
        }
        Date startTime = DateUtils.getFirstDayOfMonth(schedule.getStartTime());
        Date endTime = DateUtils.getLastDayOfMonth(schedule.getStartTime());
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setCreateId(SecurityUtils.getUserId());
        return scheduleMapper.selectMonthScheduleList(schedule);
    }

    /**
     * 批量删除日程信息
     *
     * @param ids 需要删除的日程主键
     * @return
     */
    private int updateDelFlagByIds(List<String> ids) {
        ScheduleUpdateQo qo = new ScheduleUpdateQo();
        qo.setIds(ids);
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserId());
        qo.setUpdateTime(DateUtils.getNowDate());
        return scheduleMapper.updateDelFlagByIds(qo);
    }

    /**
     * 根据开始时间和提醒类型计算提醒时间
     *
     * @param startTime      开始时间
     * @param remindTimeEnum 提醒类型（如提前5分钟、提前1小时等）
     * @return 提醒时间
     */
    private Date calculateRemindTime(Date startTime, RemindTimeEnum remindTimeEnum) {
        if (startTime == null || remindTimeEnum == null) {
            return null;
        }
        int minute = -remindTimeEnum.getMinute();
        Date date = DateUtils.addMinutes(startTime, minute);
        return date;
    }

    /**
     * 发送消息
     *
     * @param remindList 需要提醒的日程列表
     */
    private void handleRemind(List<Schedule> remindList) {
        List<String> scheduleIds = remindList.stream().map(Schedule::getId).collect(Collectors.toList());
        List<ScheduleParts> schedulePartsList = schedulePartsService.listSchedulePartsByScheduleIds(scheduleIds);
        if (CollectionUtils.isEmpty(schedulePartsList)) {
            return;
        }
        List<String> userIds = schedulePartsList.stream().map(ScheduleParts::getUserId).collect(Collectors.toList());
        systemMessageService.sendBusinessSystemMessage(BusinessMessageType.SCHEDULE_REMIND.getCode(), null, userIds, "您有日程快开始了，不要忘了哦！");
    }
}
