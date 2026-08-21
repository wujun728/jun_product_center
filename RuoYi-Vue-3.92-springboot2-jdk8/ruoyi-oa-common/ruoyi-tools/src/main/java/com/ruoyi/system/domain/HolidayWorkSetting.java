package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 节假日补班设置对象 t_holiday_work_setting
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HolidayWorkSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 年度
     */
    @Excel(name = "年度")
    private String year;

    /**
     * 补班日期
     */
    @Excel(name = "补班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 关联节假日ID
     */
    @Excel(name = "关联节假日ID")
    private String holidaySettingId;

    /**
     * 删除状态，0-否，1-是
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 更新人
     */
    private String updateId;

    /**
     * 节假日名称
     */
    private String holidayName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId()).append("year", getYear()).append("workDate", getWorkDate()).append("holidaySettingId", getHolidaySettingId()).append("delFlag", getDelFlag()).append("remark", getRemark()).append("createId", getCreateId()).append("createTime", getCreateTime()).append("updateId", getUpdateId()).append("updateTime", getUpdateTime()).toString();
    }
}
