package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目底稿对象 pj_project_draft
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectDraft extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 底稿ID */
    private String id;

    /** 底稿名称 */
    @Excel(name = "底稿名称")
    private String draftName;

    /** 底稿类型 */
    @Excel(name = "底稿类型")
    private String dictDraftType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 底稿描述 */
    @Excel(name = "底稿描述")
    private String draftDesc;

    /** 底稿输出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "底稿输出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date draftTime;

    /** 底稿输出责任人(承做) */
    @Excel(name = "底稿输出责任人(承做)")
    private String refDraftBy;

    /** 底稿整理进度 */
    @Excel(name = "底稿整理进度")
    private String dictDraftStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderStatus;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setDraftName(String draftName)
    {
        this.draftName = draftName;
    }

    public String getDraftName()
    {
        return draftName;
    }
    public void setDictDraftType(String dictDraftType)
    {
        this.dictDraftType = dictDraftType;
    }

    public String getDictDraftType()
    {
        return dictDraftType;
    }
    public void setRefProjectCode(String refProjectCode)
    {
        this.refProjectCode = refProjectCode;
    }

    public String getRefProjectCode()
    {
        return refProjectCode;
    }
    public void setRefProjectName(String refProjectName)
    {
        this.refProjectName = refProjectName;
    }

    public String getRefProjectName()
    {
        return refProjectName;
    }
    public void setDraftDesc(String draftDesc)
    {
        this.draftDesc = draftDesc;
    }

    public String getDraftDesc()
    {
        return draftDesc;
    }
    public void setDraftTime(Date draftTime)
    {
        this.draftTime = draftTime;
    }

    public Date getDraftTime()
    {
        return draftTime;
    }
    public void setRefDraftBy(String refDraftBy)
    {
        this.refDraftBy = refDraftBy;
    }

    public String getRefDraftBy()
    {
        return refDraftBy;
    }
    public void setDictDraftStatus(String dictDraftStatus)
    {
        this.dictDraftStatus = dictDraftStatus;
    }

    public String getDictDraftStatus()
    {
        return dictDraftStatus;
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
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setOrderStatus(Long orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus()
    {
        return orderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("draftName", getDraftName())
            .append("dictDraftType", getDictDraftType())
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("draftDesc", getDraftDesc())
            .append("draftTime", getDraftTime())
            .append("refDraftBy", getRefDraftBy())
            .append("dictDraftStatus", getDictDraftStatus())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
