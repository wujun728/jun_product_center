package com.ruoyi.schedule.module;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.ScheduleType;
import lombok.Data;
import java.util.List;

/**
 * 日程月份查询结果
 * @Author wocurr.com
 */
@Data
public class ScheduleInfoResult extends Schedule {
    private static final long serialVersionUID = 1L;

    /**
     * 组织人（创建人）
     */
    private String createUserName;

    /**
     * 组织人头像
     */
    private String avatar;

    /**
     * 分类
     */
    private ScheduleType scheduleType;

    /**
     * 参与用户列表
     */
    private List<SysUser> joinUsers;
}
