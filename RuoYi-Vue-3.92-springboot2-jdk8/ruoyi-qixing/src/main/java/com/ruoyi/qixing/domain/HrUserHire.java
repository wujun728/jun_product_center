package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 录用审批对象 hr_user_hire
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserHire extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 入职人员名称 */
    @Excel(name = "入职人员名称")
    private String refJobUsername;

    /** 入职部门 */
    @Excel(name = "入职部门")
    private String refJobDeptname;

    /** 用工类型 */
    @Excel(name = "用工类型")
    private String dictJobType;

    /** 入职岗位 */
    @Excel(name = "入职岗位")
    private String dictJob;

    /** 直属领导 */
    @Excel(name = "直属领导")
    private String refEntryLeader;

    /** 新员工导师 */
    @Excel(name = "新员工导师")
    private String refEntryTeach;

    /** 薪资 */
    @Excel(name = "薪资")
    private Long money;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String wfstate;

    /** 当前节点 */
    @Excel(name = "当前节点")
    private String currNodename;

    /** 当前审批人 */
    @Excel(name = "当前审批人")
    private String currUsercode;

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
    public void setRefJobUsername(String refJobUsername)
    {
        this.refJobUsername = refJobUsername;
    }

    public String getRefJobUsername()
    {
        return refJobUsername;
    }
    public void setRefJobDeptname(String refJobDeptname)
    {
        this.refJobDeptname = refJobDeptname;
    }

    public String getRefJobDeptname()
    {
        return refJobDeptname;
    }
    public void setDictJobType(String dictJobType)
    {
        this.dictJobType = dictJobType;
    }

    public String getDictJobType()
    {
        return dictJobType;
    }
    public void setDictJob(String dictJob)
    {
        this.dictJob = dictJob;
    }

    public String getDictJob()
    {
        return dictJob;
    }
    public void setRefEntryLeader(String refEntryLeader)
    {
        this.refEntryLeader = refEntryLeader;
    }

    public String getRefEntryLeader()
    {
        return refEntryLeader;
    }
    public void setRefEntryTeach(String refEntryTeach)
    {
        this.refEntryTeach = refEntryTeach;
    }

    public String getRefEntryTeach()
    {
        return refEntryTeach;
    }
    public void setMoney(Long money)
    {
        this.money = money;
    }

    public Long getMoney()
    {
        return money;
    }
    public void setEntryTime(Date entryTime)
    {
        this.entryTime = entryTime;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }
    public void setWfstate(String wfstate)
    {
        this.wfstate = wfstate;
    }

    public String getWfstate()
    {
        return wfstate;
    }
    public void setCurrNodename(String currNodename)
    {
        this.currNodename = currNodename;
    }

    public String getCurrNodename()
    {
        return currNodename;
    }
    public void setCurrUsercode(String currUsercode)
    {
        this.currUsercode = currUsercode;
    }

    public String getCurrUsercode()
    {
        return currUsercode;
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
            .append("refJobUsername", getRefJobUsername())
            .append("refJobDeptname", getRefJobDeptname())
            .append("dictJobType", getDictJobType())
            .append("dictJob", getDictJob())
            .append("refEntryLeader", getRefEntryLeader())
            .append("refEntryTeach", getRefEntryTeach())
            .append("money", getMoney())
            .append("entryTime", getEntryTime())
            .append("wfstate", getWfstate())
            .append("currNodename", getCurrNodename())
            .append("currUsercode", getCurrUsercode())
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
