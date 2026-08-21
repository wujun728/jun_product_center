package com.ruoyi.schedule.domain;

import lombok.Data;

/**
 * @Author wocurr.com
 */
@Data
public class ScheduleDTO extends Schedule {

    /**
     * 日程类型
     */
    private ScheduleType scheduleType;
}
