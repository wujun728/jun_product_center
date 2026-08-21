package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 办公用品申领申购对象 oa_office_count2
 *
 * @author template
 * @date 2026-06-11
 */
public class OaOfficeCount2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 办公用品名称 */
    @Excel(name = "办公用品名称")
    private String offiecProductName;

    /** 办公用品类型 */
    @Excel(name = "办公用品类型")
    private String dictProductType;

    /** 办公用品用途 */
    @Excel(name = "办公用品用途")
    private String officeTodo;

    /** 需求数量 */
    @Excel(name = "需求数量")
    private Long reqNum;

    /** 申请原因 */
    @Excel(name = "申请原因")
    private String whyDesc;

    /** 申请人 */
    @Excel(name = "申请人")
    private String creator;

    /** 审批状态 */
    @Excel(name = "审批状态")
    private String dictApprove;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String descApprove;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approvetor;

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
    private String orderState;

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
    public void setOffiecProductName(String offiecProductName)
    {
        this.offiecProductName = offiecProductName;
    }

    public String getOffiecProductName()
    {
        return offiecProductName;
    }
    public void setDictProductType(String dictProductType)
    {
        this.dictProductType = dictProductType;
    }

    public String getDictProductType()
    {
        return dictProductType;
    }
    public void setOfficeTodo(String officeTodo)
    {
        this.officeTodo = officeTodo;
    }

    public String getOfficeTodo()
    {
        return officeTodo;
    }
    public void setReqNum(Long reqNum)
    {
        this.reqNum = reqNum;
    }

    public Long getReqNum()
    {
        return reqNum;
    }
    public void setWhyDesc(String whyDesc)
    {
        this.whyDesc = whyDesc;
    }

    public String getWhyDesc()
    {
        return whyDesc;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setDictApprove(String dictApprove)
    {
        this.dictApprove = dictApprove;
    }

    public String getDictApprove()
    {
        return dictApprove;
    }
    public void setDescApprove(String descApprove)
    {
        this.descApprove = descApprove;
    }

    public String getDescApprove()
    {
        return descApprove;
    }
    public void setApprovetor(String approvetor)
    {
        this.approvetor = approvetor;
    }

    public String getApprovetor()
    {
        return approvetor;
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
    public void setOrderState(String orderState)
    {
        this.orderState = orderState;
    }

    public String getOrderState()
    {
        return orderState;
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
            .append("offiecProductName", getOffiecProductName())
            .append("dictProductType", getDictProductType())
            .append("officeTodo", getOfficeTodo())
            .append("reqNum", getReqNum())
            .append("whyDesc", getWhyDesc())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("dictApprove", getDictApprove())
            .append("descApprove", getDescApprove())
            .append("approvetor", getApprovetor())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderState", getOrderState())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
