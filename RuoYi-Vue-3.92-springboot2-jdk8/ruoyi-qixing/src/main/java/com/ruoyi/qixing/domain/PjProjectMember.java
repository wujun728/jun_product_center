package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目成员与结算对象 pj_project_member
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成员ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 成员名称 */
    @Excel(name = "成员名称")
    private String refMemberName;

    /** 成员项目角色 */
    @Excel(name = "成员项目角色")
    private String dictMemberRole;

    /** 成员工作内容 */
    @Excel(name = "成员工作内容")
    private String memberWorkContent;

    /** 是否参与分成 */
    @Excel(name = "是否参与分成")
    private String dictYesNo;

    /** 成员工作分成比例 */
    @Excel(name = "成员工作分成比例")
    private Long memberParts;

    /** 成员合计投入项目工作日 */
    @Excel(name = "成员合计投入项目工作日")
    private Long memberWorkDays;

    /** 分成金额 */
    @Excel(name = "分成金额")
    private Long memberPartsMoney;

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
    public void setRefMemberName(String refMemberName)
    {
        this.refMemberName = refMemberName;
    }

    public String getRefMemberName()
    {
        return refMemberName;
    }
    public void setDictMemberRole(String dictMemberRole)
    {
        this.dictMemberRole = dictMemberRole;
    }

    public String getDictMemberRole()
    {
        return dictMemberRole;
    }
    public void setMemberWorkContent(String memberWorkContent)
    {
        this.memberWorkContent = memberWorkContent;
    }

    public String getMemberWorkContent()
    {
        return memberWorkContent;
    }
    public void setDictYesNo(String dictYesNo)
    {
        this.dictYesNo = dictYesNo;
    }

    public String getDictYesNo()
    {
        return dictYesNo;
    }
    public void setMemberParts(Long memberParts)
    {
        this.memberParts = memberParts;
    }

    public Long getMemberParts()
    {
        return memberParts;
    }
    public void setMemberWorkDays(Long memberWorkDays)
    {
        this.memberWorkDays = memberWorkDays;
    }

    public Long getMemberWorkDays()
    {
        return memberWorkDays;
    }
    public void setMemberPartsMoney(Long memberPartsMoney)
    {
        this.memberPartsMoney = memberPartsMoney;
    }

    public Long getMemberPartsMoney()
    {
        return memberPartsMoney;
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
            .append("refMemberName", getRefMemberName())
            .append("dictMemberRole", getDictMemberRole())
            .append("memberWorkContent", getMemberWorkContent())
            .append("dictYesNo", getDictYesNo())
            .append("memberParts", getMemberParts())
            .append("memberWorkDays", getMemberWorkDays())
            .append("memberPartsMoney", getMemberPartsMoney())
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
