package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 外出信息对象 oa_poms_workmarks_outsite
 *
 * @author template
 * @date 2026-06-11
 */
public class OaPomsWorkmarksOutsite extends BaseEntity
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

    /** 外出日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "外出日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workDay;

    /** 外出事由(出差、拜访客户) */
    @Excel(name = "外出事由(出差、拜访客户)")
    private String outsiteDesc;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 外出时长 */
    @Excel(name = "外出时长")
    private BigDecimal workTotalTime;

    /** 审批状态 */
    @Excel(name = "审批状态")
    private String dictWfstateOutsite;

    /** 当前处理人 */
    @Excel(name = "当前处理人")
    private String currTodo;

    /** 填报人 */
    @Excel(name = "填报人")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderStatus;

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
    public void setOutsiteDesc(String outsiteDesc)
    {
        this.outsiteDesc = outsiteDesc;
    }

    public String getOutsiteDesc()
    {
        return outsiteDesc;
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
    public void setDictWfstateOutsite(String dictWfstateOutsite)
    {
        this.dictWfstateOutsite = dictWfstateOutsite;
    }

    public String getDictWfstateOutsite()
    {
        return dictWfstateOutsite;
    }
    public void setCurrTodo(String currTodo)
    {
        this.currTodo = currTodo;
    }

    public String getCurrTodo()
    {
        return currTodo;
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
            .append("outsiteDesc", getOutsiteDesc())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("workTotalTime", getWorkTotalTime())
            .append("dictWfstateOutsite", getDictWfstateOutsite())
            .append("currTodo", getCurrTodo())
            .append("creator", getCreator())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .append("createId", getCreateId())
            .toString();
    }
}
