package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目借阅对象 pj_project_borrow
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectBorrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refUserCode;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String refUserName;

    /** 借阅原因 */
    @Excel(name = "借阅原因")
    private String borrowDesc;

    /** 借阅结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借阅结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String dictBorrowState;

    /** 提交人 */
    @Excel(name = "提交人")
    private String creator;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** 更新人 */
    @Excel(name = "更新人")
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
    public void setRefUserCode(String refUserCode)
    {
        this.refUserCode = refUserCode;
    }

    public String getRefUserCode()
    {
        return refUserCode;
    }
    public void setRefUserName(String refUserName)
    {
        this.refUserName = refUserName;
    }

    public String getRefUserName()
    {
        return refUserName;
    }
    public void setBorrowDesc(String borrowDesc)
    {
        this.borrowDesc = borrowDesc;
    }

    public String getBorrowDesc()
    {
        return borrowDesc;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setDictBorrowState(String dictBorrowState)
    {
        this.dictBorrowState = dictBorrowState;
    }

    public String getDictBorrowState()
    {
        return dictBorrowState;
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
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("refUserCode", getRefUserCode())
            .append("refUserName", getRefUserName())
            .append("borrowDesc", getBorrowDesc())
            .append("endTime", getEndTime())
            .append("dictBorrowState", getDictBorrowState())
            .append("creator", getCreator())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
