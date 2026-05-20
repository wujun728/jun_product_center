package com.ruoyi.schedule.domain.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 日程更新对象
 *
 * @author wocurr.com
 */
@Data
public class ScheduleUpdateQo {

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

    /**
     * 更新人ID
     */
    private String updateId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 主键ID集合
     */
    private List<String> ids;
}
