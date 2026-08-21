package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目进度与任务(WBS)对象 pj_project_prodess_task
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectProdessTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String taskDetail;

    /** 工时估算 */
    @Excel(name = "工时估算")
    private Long costTime;

    /** 费用估算 */
    @Excel(name = "费用估算")
    private Long costMoney;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskTimeStart;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskTimeEnd;

    /** 任务进度 */
    @Excel(name = "任务进度")
    private Long taskProgress;

    /** 填报人 */
    @Excel(name = "填报人")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setRefProjectName(String refProjectName)
    {
        this.refProjectName = refProjectName;
    }

    public String getRefProjectName()
    {
        return refProjectName;
    }
    public void setRefProjectCode(String refProjectCode)
    {
        this.refProjectCode = refProjectCode;
    }

    public String getRefProjectCode()
    {
        return refProjectCode;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setTaskDetail(String taskDetail)
    {
        this.taskDetail = taskDetail;
    }

    public String getTaskDetail()
    {
        return taskDetail;
    }
    public void setCostTime(Long costTime)
    {
        this.costTime = costTime;
    }

    public Long getCostTime()
    {
        return costTime;
    }
    public void setCostMoney(Long costMoney)
    {
        this.costMoney = costMoney;
    }

    public Long getCostMoney()
    {
        return costMoney;
    }
    public void setTaskTimeStart(Date taskTimeStart)
    {
        this.taskTimeStart = taskTimeStart;
    }

    public Date getTaskTimeStart()
    {
        return taskTimeStart;
    }
    public void setTaskTimeEnd(Date taskTimeEnd)
    {
        this.taskTimeEnd = taskTimeEnd;
    }

    public Date getTaskTimeEnd()
    {
        return taskTimeEnd;
    }
    public void setTaskProgress(Long taskProgress)
    {
        this.taskProgress = taskProgress;
    }

    public Long getTaskProgress()
    {
        return taskProgress;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }
    public void setUpdateId(String updateId)
    {
        this.updateId = updateId;
    }

    public String getUpdateId()
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("refProjectName", getRefProjectName())
            .append("refProjectCode", getRefProjectCode())
            .append("taskName", getTaskName())
            .append("taskDetail", getTaskDetail())
            .append("costTime", getCostTime())
            .append("costMoney", getCostMoney())
            .append("taskTimeStart", getTaskTimeStart())
            .append("taskTimeEnd", getTaskTimeEnd())
            .append("taskProgress", getTaskProgress())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
