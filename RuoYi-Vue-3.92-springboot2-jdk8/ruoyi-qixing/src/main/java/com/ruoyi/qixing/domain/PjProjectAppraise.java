package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目总结及评价对象 pj_project_appraise
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectAppraise extends BaseEntity
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

    /** 总结&评价人 */
    @Excel(name = "总结&评价人")
    private String refUsername;

    /** 在项目中的角色 */
    @Excel(name = "在项目中的角色")
    private String dictMemberRole;

    /** 总体评级 */
    @Excel(name = "总体评级")
    private String detail1;

    /** 项目完成情况总结 */
    @Excel(name = "项目完成情况总结")
    private String detail2;

    /** 项目经验教训总结 */
    @Excel(name = "项目经验教训总结")
    private String detail3;

    /** 创建人 */
    @Excel(name = "创建人")
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
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setDictMemberRole(String dictMemberRole)
    {
        this.dictMemberRole = dictMemberRole;
    }

    public String getDictMemberRole()
    {
        return dictMemberRole;
    }
    public void setDetail1(String detail1)
    {
        this.detail1 = detail1;
    }

    public String getDetail1()
    {
        return detail1;
    }
    public void setDetail2(String detail2)
    {
        this.detail2 = detail2;
    }

    public String getDetail2()
    {
        return detail2;
    }
    public void setDetail3(String detail3)
    {
        this.detail3 = detail3;
    }

    public String getDetail3()
    {
        return detail3;
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
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("refUsername", getRefUsername())
            .append("dictMemberRole", getDictMemberRole())
            .append("detail1", getDetail1())
            .append("detail2", getDetail2())
            .append("detail3", getDetail3())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
