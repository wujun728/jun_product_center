package com.ruoyi.schedule.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 日程参与人对象 t_schedule_parts
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleParts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 日程ID
     */
    @Excel(name = "日程ID")
    private String scheduleId;

    /**
     * 参与用户ID
     */
    @Excel(name = "参与用户ID")
    private String userId;

    /**
     * 状态，0-未确认，1-已确认，2-已完成
     */
    @Excel(name = "状态，0-未确认，1-已确认，2-已完成")
    private String status;

    /**
     * 删除状态，0-否，1-是
     */
    private String delFlag;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("scheduleId", getScheduleId())
                .append("userId", getUserId())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
