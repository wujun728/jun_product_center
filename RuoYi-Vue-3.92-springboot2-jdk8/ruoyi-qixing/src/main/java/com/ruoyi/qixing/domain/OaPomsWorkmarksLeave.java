package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工请假对象 oa_poms_workmarks_leave
 *
 * @author template
 * @date 2026-06-11
 */
public class OaPomsWorkmarksLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 员工工号 */
    @Excel(name = "员工工号")
    private String refUsercoce;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String refUsername;

    /** 员工部门 */
    @Excel(name = "员工部门")
    private String refUserdept;

    /** 请假日期 */
    @Excel(name = "请假日期")
    private String leaveDate;

    /** 请假结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "请假结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveDateEnd;

    /** 请假类型 */
    @Excel(name = "请假类型")
    private String dictLeanveType;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String leaveDesc;

    /** 请假小时数 */
    @Excel(name = "请假小时数")
    private Long leaveHours;

    /** 废弃 */
    @Excel(name = "废弃")
    private Long leaveHours2;

    /** 废弃 */
    @Excel(name = "废弃")
    private String dictApproveStatus;

    /** 废弃 */
    @Excel(name = "废弃")
    private String refCurrTodoPerson;

    /** 填报人 */
    @Excel(name = "填报人")
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
    public void setRefUsercoce(String refUsercoce)
    {
        this.refUsercoce = refUsercoce;
    }

    public String getRefUsercoce()
    {
        return refUsercoce;
    }
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setRefUserdept(String refUserdept)
    {
        this.refUserdept = refUserdept;
    }

    public String getRefUserdept()
    {
        return refUserdept;
    }
    public void setLeaveDate(String leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    public String getLeaveDate()
    {
        return leaveDate;
    }
    public void setLeaveDateEnd(Date leaveDateEnd)
    {
        this.leaveDateEnd = leaveDateEnd;
    }

    public Date getLeaveDateEnd()
    {
        return leaveDateEnd;
    }
    public void setDictLeanveType(String dictLeanveType)
    {
        this.dictLeanveType = dictLeanveType;
    }

    public String getDictLeanveType()
    {
        return dictLeanveType;
    }
    public void setLeaveDesc(String leaveDesc)
    {
        this.leaveDesc = leaveDesc;
    }

    public String getLeaveDesc()
    {
        return leaveDesc;
    }
    public void setLeaveHours(Long leaveHours)
    {
        this.leaveHours = leaveHours;
    }

    public Long getLeaveHours()
    {
        return leaveHours;
    }
    public void setLeaveHours2(Long leaveHours2)
    {
        this.leaveHours2 = leaveHours2;
    }

    public Long getLeaveHours2()
    {
        return leaveHours2;
    }
    public void setDictApproveStatus(String dictApproveStatus)
    {
        this.dictApproveStatus = dictApproveStatus;
    }

    public String getDictApproveStatus()
    {
        return dictApproveStatus;
    }
    public void setRefCurrTodoPerson(String refCurrTodoPerson)
    {
        this.refCurrTodoPerson = refCurrTodoPerson;
    }

    public String getRefCurrTodoPerson()
    {
        return refCurrTodoPerson;
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
            .append("refUsercoce", getRefUsercoce())
            .append("refUsername", getRefUsername())
            .append("refUserdept", getRefUserdept())
            .append("leaveDate", getLeaveDate())
            .append("leaveDateEnd", getLeaveDateEnd())
            .append("dictLeanveType", getDictLeanveType())
            .append("leaveDesc", getLeaveDesc())
            .append("leaveHours", getLeaveHours())
            .append("leaveHours2", getLeaveHours2())
            .append("dictApproveStatus", getDictApproveStatus())
            .append("refCurrTodoPerson", getRefCurrTodoPerson())
            .append("creator", getCreator())
            .append("createId", getCreateId())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderState", getOrderState())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
