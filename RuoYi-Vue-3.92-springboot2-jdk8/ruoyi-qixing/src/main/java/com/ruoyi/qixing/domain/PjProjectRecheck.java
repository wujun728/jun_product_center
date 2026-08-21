package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目复核对象 pj_project_recheck
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectRecheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成员ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refPcode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refPname;

    /** 项目经理名称 */
    @Excel(name = "项目经理名称")
    private String refPmanager;

    /** 项目报告 */
    @Excel(name = "项目报告")
    private String refPreport;

    /** 项目报告责任人 */
    @Excel(name = "项目报告责任人")
    private String refPmanager2;

    /** 项目复核责任人 */
    @Excel(name = "项目复核责任人")
    private String recheckMan;

    /** 项目复核意见 */
    @Excel(name = "项目复核意见")
    private String recheckAdvice;

    /** 项目复核状态 */
    @Excel(name = "项目复核状态")
    private String recheckState;

    /** 当前处理人 */
    @Excel(name = "当前处理人")
    private String currMan;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String dictWfState;

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
    public void setRefPcode(String refPcode)
    {
        this.refPcode = refPcode;
    }

    public String getRefPcode()
    {
        return refPcode;
    }
    public void setRefPname(String refPname)
    {
        this.refPname = refPname;
    }

    public String getRefPname()
    {
        return refPname;
    }
    public void setRefPmanager(String refPmanager)
    {
        this.refPmanager = refPmanager;
    }

    public String getRefPmanager()
    {
        return refPmanager;
    }
    public void setRefPreport(String refPreport)
    {
        this.refPreport = refPreport;
    }

    public String getRefPreport()
    {
        return refPreport;
    }
    public void setRefPmanager2(String refPmanager2)
    {
        this.refPmanager2 = refPmanager2;
    }

    public String getRefPmanager2()
    {
        return refPmanager2;
    }
    public void setRecheckMan(String recheckMan)
    {
        this.recheckMan = recheckMan;
    }

    public String getRecheckMan()
    {
        return recheckMan;
    }
    public void setRecheckAdvice(String recheckAdvice)
    {
        this.recheckAdvice = recheckAdvice;
    }

    public String getRecheckAdvice()
    {
        return recheckAdvice;
    }
    public void setRecheckState(String recheckState)
    {
        this.recheckState = recheckState;
    }

    public String getRecheckState()
    {
        return recheckState;
    }
    public void setCurrMan(String currMan)
    {
        this.currMan = currMan;
    }

    public String getCurrMan()
    {
        return currMan;
    }
    public void setDictWfState(String dictWfState)
    {
        this.dictWfState = dictWfState;
    }

    public String getDictWfState()
    {
        return dictWfState;
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
            .append("refPcode", getRefPcode())
            .append("refPname", getRefPname())
            .append("refPmanager", getRefPmanager())
            .append("refPreport", getRefPreport())
            .append("refPmanager2", getRefPmanager2())
            .append("recheckMan", getRecheckMan())
            .append("recheckAdvice", getRecheckAdvice())
            .append("recheckState", getRecheckState())
            .append("currMan", getCurrMan())
            .append("dictWfState", getDictWfState())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
