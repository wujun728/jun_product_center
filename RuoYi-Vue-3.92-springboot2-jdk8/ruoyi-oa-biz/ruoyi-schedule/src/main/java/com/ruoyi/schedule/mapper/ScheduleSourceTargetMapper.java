package com.ruoyi.schedule.mapper;

import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.ScheduleDTO;
import com.ruoyi.schedule.module.ScheduleInfoResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转换实体
 *
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface ScheduleSourceTargetMapper {
    ScheduleSourceTargetMapper INSTANCE = Mappers.getMapper(ScheduleSourceTargetMapper.class);

    ScheduleInfoResult toScheduleInfo(Schedule vo);

    ScheduleDTO toScheduleDTO(Schedule vo);

    Schedule copy(Schedule vo);

    List<ScheduleDTO> listToScheduleDTO(List<Schedule> list);
}
