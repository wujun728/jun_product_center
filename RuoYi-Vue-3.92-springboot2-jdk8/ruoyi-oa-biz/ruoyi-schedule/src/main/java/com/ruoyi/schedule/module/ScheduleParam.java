package com.ruoyi.schedule.module;

import com.ruoyi.schedule.domain.Schedule;
import lombok.Data;

import java.util.List;

/**
 * @Author wocurr.com
 */
@Data
public class ScheduleParam extends Schedule {

    /**
     * 参与人员
     */
    private List<String> joinUsers;


}
