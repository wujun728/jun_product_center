package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目报告文号对象 pj_project_reportnumber
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectReportnumber extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refReportnumberCode;

    /** 项目报告 */
    @Excel(name = "项目报告")
    private String refReportnumberTitle;

    /** 报告文号(生成) */
    @Excel(name = "报告文号(生成)")
    private String reportnumberCode;

    /** 报告号状态 */
    @Excel(name = "报告号状态")
    private String dictRpStatus;

    /** 报告出具人 */
    @Excel(name = "报告出具人")
    private String refReportnumberMan;

    /** 报告审核人 */
    @Excel(name = "报告审核人")
    private String refReportnumberCheckMan;

    /** 签字注册会计师 */
    @Excel(name = "签字注册会计师")
    private String refSignatureAccountant;

    /** 申请人 */
    @Excel(name = "申请人")
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
    public void setRefReportnumberCode(String refReportnumberCode)
    {
        this.refReportnumberCode = refReportnumberCode;
    }

    public String getRefReportnumberCode()
    {
        return refReportnumberCode;
    }
    public void setRefReportnumberTitle(String refReportnumberTitle)
    {
        this.refReportnumberTitle = refReportnumberTitle;
    }

    public String getRefReportnumberTitle()
    {
        return refReportnumberTitle;
    }
    public void setReportnumberCode(String reportnumberCode)
    {
        this.reportnumberCode = reportnumberCode;
    }

    public String getReportnumberCode()
    {
        return reportnumberCode;
    }
    public void setDictRpStatus(String dictRpStatus)
    {
        this.dictRpStatus = dictRpStatus;
    }

    public String getDictRpStatus()
    {
        return dictRpStatus;
    }
    public void setRefReportnumberMan(String refReportnumberMan)
    {
        this.refReportnumberMan = refReportnumberMan;
    }

    public String getRefReportnumberMan()
    {
        return refReportnumberMan;
    }
    public void setRefReportnumberCheckMan(String refReportnumberCheckMan)
    {
        this.refReportnumberCheckMan = refReportnumberCheckMan;
    }

    public String getRefReportnumberCheckMan()
    {
        return refReportnumberCheckMan;
    }
    public void setRefSignatureAccountant(String refSignatureAccountant)
    {
        this.refSignatureAccountant = refSignatureAccountant;
    }

    public String getRefSignatureAccountant()
    {
        return refSignatureAccountant;
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
            .append("refReportnumberCode", getRefReportnumberCode())
            .append("refReportnumberTitle", getRefReportnumberTitle())
            .append("reportnumberCode", getReportnumberCode())
            .append("dictRpStatus", getDictRpStatus())
            .append("refReportnumberMan", getRefReportnumberMan())
            .append("refReportnumberCheckMan", getRefReportnumberCheckMan())
            .append("refSignatureAccountant", getRefSignatureAccountant())
            .append("creator", getCreator())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
