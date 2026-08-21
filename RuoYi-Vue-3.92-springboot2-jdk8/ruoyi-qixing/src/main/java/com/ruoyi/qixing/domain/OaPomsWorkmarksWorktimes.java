package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考勤记录对象 oa_poms_workmarks_worktimes
 *
 * @author template
 * @date 2026-06-11
 */
public class OaPomsWorkmarksWorktimes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 工号 */
    @Excel(name = "工号")
    private String usercode;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String refUsername;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workDay;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 工作时长 */
    @Excel(name = "工作时长")
    private BigDecimal workTotalTime;

    /** 入场地点 */
    @Excel(name = "入场地点")
    private String leaveInCase;

    /** 入场经纬度 */
    @Excel(name = "入场经纬度")
    private String leaveInXy;

    /** 离场地点 */
    @Excel(name = "离场地点")
    private String leanveOutCase;

    /** 离场经纬度 */
    @Excel(name = "离场经纬度")
    private String leanveOutXy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsercode()
    {
        return usercode;
    }
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setWorkDay(Date workDay)
    {
        this.workDay = workDay;
    }

    public Date getWorkDay()
    {
        return workDay;
    }
    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setWorkTotalTime(BigDecimal workTotalTime)
    {
        this.workTotalTime = workTotalTime;
    }

    public BigDecimal getWorkTotalTime()
    {
        return workTotalTime;
    }
    public void setLeaveInCase(String leaveInCase)
    {
        this.leaveInCase = leaveInCase;
    }

    public String getLeaveInCase()
    {
        return leaveInCase;
    }
    public void setLeaveInXy(String leaveInXy)
    {
        this.leaveInXy = leaveInXy;
    }

    public String getLeaveInXy()
    {
        return leaveInXy;
    }
    public void setLeanveOutCase(String leanveOutCase)
    {
        this.leanveOutCase = leanveOutCase;
    }

    public String getLeanveOutCase()
    {
        return leanveOutCase;
    }
    public void setLeanveOutXy(String leanveOutXy)
    {
        this.leanveOutXy = leanveOutXy;
    }

    public String getLeanveOutXy()
    {
        return leanveOutXy;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setUpdateId(String updateId)
    {
        this.updateId = updateId;
    }

    public String getUpdateId()
    {
        return updateId;
    }
    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("usercode", getUsercode())
            .append("refUsername", getRefUsername())
            .append("workDay", getWorkDay())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("workTotalTime", getWorkTotalTime())
            .append("leaveInCase", getLeaveInCase())
            .append("leaveInXy", getLeaveInXy())
            .append("leanveOutCase", getLeanveOutCase())
            .append("leanveOutXy", getLeanveOutXy())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("creator", getCreator())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .append("createId", getCreateId())
            .toString();
    }
}
