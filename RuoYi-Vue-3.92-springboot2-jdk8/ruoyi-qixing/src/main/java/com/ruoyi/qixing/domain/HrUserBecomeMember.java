package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转正对象 hr_user_become_member
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserBecomeMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 员工工号 */
    @Excel(name = "员工工号")
    private String usercode;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String username;

    /** 部门 */
    @Excel(name = "部门")
    private String deptname;

    /** 岗位 */
    @Excel(name = "岗位")
    private String postname;

    /** 试用期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date starttime1;

    /** 试用期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用期结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endtime1;

    /** 实际转正时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际转正时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acttime2;

    /** 试用期导师评价 */
    @Excel(name = "试用期导师评价")
    private String techDesc;

    /** 试用期领导评价 */
    @Excel(name = "试用期领导评价")
    private String leaderDesc;

    /** 转正评价 */
    @Excel(name = "转正评价")
    private String becomeMemDesc;

    /** 转正结论 */
    @Excel(name = "转正结论")
    private String dictBecomeMember;

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
    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsercode()
    {
        return usercode;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setDeptname(String deptname)
    {
        this.deptname = deptname;
    }

    public String getDeptname()
    {
        return deptname;
    }
    public void setPostname(String postname)
    {
        this.postname = postname;
    }

    public String getPostname()
    {
        return postname;
    }
    public void setStarttime1(Date starttime1)
    {
        this.starttime1 = starttime1;
    }

    public Date getStarttime1()
    {
        return starttime1;
    }
    public void setEndtime1(Date endtime1)
    {
        this.endtime1 = endtime1;
    }

    public Date getEndtime1()
    {
        return endtime1;
    }
    public void setActtime2(Date acttime2)
    {
        this.acttime2 = acttime2;
    }

    public Date getActtime2()
    {
        return acttime2;
    }
    public void setTechDesc(String techDesc)
    {
        this.techDesc = techDesc;
    }

    public String getTechDesc()
    {
        return techDesc;
    }
    public void setLeaderDesc(String leaderDesc)
    {
        this.leaderDesc = leaderDesc;
    }

    public String getLeaderDesc()
    {
        return leaderDesc;
    }
    public void setBecomeMemDesc(String becomeMemDesc)
    {
        this.becomeMemDesc = becomeMemDesc;
    }

    public String getBecomeMemDesc()
    {
        return becomeMemDesc;
    }
    public void setDictBecomeMember(String dictBecomeMember)
    {
        this.dictBecomeMember = dictBecomeMember;
    }

    public String getDictBecomeMember()
    {
        return dictBecomeMember;
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
            .append("usercode", getUsercode())
            .append("username", getUsername())
            .append("deptname", getDeptname())
            .append("postname", getPostname())
            .append("starttime1", getStarttime1())
            .append("endtime1", getEndtime1())
            .append("acttime2", getActtime2())
            .append("techDesc", getTechDesc())
            .append("leaderDesc", getLeaderDesc())
            .append("becomeMemDesc", getBecomeMemDesc())
            .append("dictBecomeMember", getDictBecomeMember())
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
