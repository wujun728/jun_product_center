package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 pj_project
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private String id;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String dictProjectType;

    /** 项目类型细分 */
    @Excel(name = "项目类型细分")
    private String dictProjectTypeSub;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refIdCuscode;

    /** 客户(委托单位) */
    @Excel(name = "客户(委托单位)")
    private String refCusname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String projectDesc;

    /** 项目计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectStarttime;

    /** 项目计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectEndtime;

    /** 被评估单位 */
    @Excel(name = "被评估单位")
    private String cusnameTodo;

    /** 项目经理 */
    @Excel(name = "项目经理")
    private String refProjectManager;

    /** 承接(合伙)人 */
    @Excel(name = "承接(合伙)人")
    private String refUndertakePerson;

    /** 承做(合伙)人 */
    @Excel(name = "承做(合伙)人")
    private String refUndertakTpersonDo;

    /** 风险评估等级 */
    @Excel(name = "风险评估等级")
    private String dictRiskAssessment;

    /** 首次承接 */
    @Excel(name = "首次承接")
    private String dictFirstUndertake;

    /** 客户诉求 */
    @Excel(name = "客户诉求")
    private String customerReq;

    /** 项目进度 */
    @Excel(name = "项目进度")
    private BigDecimal projectProgress;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String dictWfState;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String dictProjectStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String delay;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private Long deleted;

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
    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode()
    {
        return projectCode;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setDictProjectType(String dictProjectType)
    {
        this.dictProjectType = dictProjectType;
    }

    public String getDictProjectType()
    {
        return dictProjectType;
    }
    public void setDictProjectTypeSub(String dictProjectTypeSub)
    {
        this.dictProjectTypeSub = dictProjectTypeSub;
    }

    public String getDictProjectTypeSub()
    {
        return dictProjectTypeSub;
    }
    public void setRefIdCuscode(String refIdCuscode)
    {
        this.refIdCuscode = refIdCuscode;
    }

    public String getRefIdCuscode()
    {
        return refIdCuscode;
    }
    public void setRefCusname(String refCusname)
    {
        this.refCusname = refCusname;
    }

    public String getRefCusname()
    {
        return refCusname;
    }
    public void setProjectDesc(String projectDesc)
    {
        this.projectDesc = projectDesc;
    }

    public String getProjectDesc()
    {
        return projectDesc;
    }
    public void setProjectStarttime(Date projectStarttime)
    {
        this.projectStarttime = projectStarttime;
    }

    public Date getProjectStarttime()
    {
        return projectStarttime;
    }
    public void setProjectEndtime(Date projectEndtime)
    {
        this.projectEndtime = projectEndtime;
    }

    public Date getProjectEndtime()
    {
        return projectEndtime;
    }
    public void setCusnameTodo(String cusnameTodo)
    {
        this.cusnameTodo = cusnameTodo;
    }

    public String getCusnameTodo()
    {
        return cusnameTodo;
    }
    public void setRefProjectManager(String refProjectManager)
    {
        this.refProjectManager = refProjectManager;
    }

    public String getRefProjectManager()
    {
        return refProjectManager;
    }
    public void setRefUndertakePerson(String refUndertakePerson)
    {
        this.refUndertakePerson = refUndertakePerson;
    }

    public String getRefUndertakePerson()
    {
        return refUndertakePerson;
    }
    public void setRefUndertakTpersonDo(String refUndertakTpersonDo)
    {
        this.refUndertakTpersonDo = refUndertakTpersonDo;
    }

    public String getRefUndertakTpersonDo()
    {
        return refUndertakTpersonDo;
    }
    public void setDictRiskAssessment(String dictRiskAssessment)
    {
        this.dictRiskAssessment = dictRiskAssessment;
    }

    public String getDictRiskAssessment()
    {
        return dictRiskAssessment;
    }
    public void setDictFirstUndertake(String dictFirstUndertake)
    {
        this.dictFirstUndertake = dictFirstUndertake;
    }

    public String getDictFirstUndertake()
    {
        return dictFirstUndertake;
    }
    public void setCustomerReq(String customerReq)
    {
        this.customerReq = customerReq;
    }

    public String getCustomerReq()
    {
        return customerReq;
    }
    public void setProjectProgress(BigDecimal projectProgress)
    {
        this.projectProgress = projectProgress;
    }

    public BigDecimal getProjectProgress()
    {
        return projectProgress;
    }
    public void setDictWfState(String dictWfState)
    {
        this.dictWfState = dictWfState;
    }

    public String getDictWfState()
    {
        return dictWfState;
    }
    public void setDictProjectStatus(String dictProjectStatus)
    {
        this.dictProjectStatus = dictProjectStatus;
    }

    public String getDictProjectStatus()
    {
        return dictProjectStatus;
    }
    public void setDelay(String delay)
    {
        this.delay = delay;
    }

    public String getDelay()
    {
        return delay;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
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
    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
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
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("dictProjectType", getDictProjectType())
            .append("dictProjectTypeSub", getDictProjectTypeSub())
            .append("refIdCuscode", getRefIdCuscode())
            .append("refCusname", getRefCusname())
            .append("projectDesc", getProjectDesc())
            .append("projectStarttime", getProjectStarttime())
            .append("projectEndtime", getProjectEndtime())
            .append("cusnameTodo", getCusnameTodo())
            .append("refProjectManager", getRefProjectManager())
            .append("refUndertakePerson", getRefUndertakePerson())
            .append("refUndertakTpersonDo", getRefUndertakTpersonDo())
            .append("dictRiskAssessment", getDictRiskAssessment())
            .append("dictFirstUndertake", getDictFirstUndertake())
            .append("customerReq", getCustomerReq())
            .append("projectProgress", getProjectProgress())
            .append("dictWfState", getDictWfState())
            .append("dictProjectStatus", getDictProjectStatus())
            .append("remark", getRemark())
            .append("delay", getDelay())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("creator", getCreator())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
