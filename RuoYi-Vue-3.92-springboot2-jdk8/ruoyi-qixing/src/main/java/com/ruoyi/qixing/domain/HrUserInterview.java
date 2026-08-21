package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试汇总对象 hr_user_interview
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserInterview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 候选人 */
    @Excel(name = "候选人")
    private String refPeopleName;

    /** 面试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "面试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ivTime;

    /** 面试类型 */
    @Excel(name = "面试类型")
    private String dictIvType;

    /** 面试官名称 */
    @Excel(name = "面试官名称")
    private String refIvUsername;

    /** 面试官电话 */
    @Excel(name = "面试官电话")
    private String ivPhone;

    /** 面试结果 */
    @Excel(name = "面试结果")
    private String dictResult;

    /** 面试评价 */
    @Excel(name = "面试评价")
    private String ivEvaluate;

    /** 工作岗位 */
    @Excel(name = "工作岗位")
    private String jobName;

    /** 工作描述 */
    @Excel(name = "工作描述")
    private String jobDesc;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Long workYear;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workLocation;

    /** 工作内容 */
    @Excel(name = "工作内容")
    private String workContent;

    /** 期望薪资 */
    @Excel(name = "期望薪资")
    private Long jobMoney;

    /** 到岗时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到岗时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date getInCompayTime;

    /** 离职原因 */
    @Excel(name = "离职原因")
    private String outJobDesc;

    /** 职业技能特长 */
    @Excel(name = "职业技能特长")
    private String jobSkill;

    /** 候选人优缺点 */
    @Excel(name = "候选人优缺点")
    private String jobInterview;

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
    public void setRefPeopleName(String refPeopleName)
    {
        this.refPeopleName = refPeopleName;
    }

    public String getRefPeopleName()
    {
        return refPeopleName;
    }
    public void setIvTime(Date ivTime)
    {
        this.ivTime = ivTime;
    }

    public Date getIvTime()
    {
        return ivTime;
    }
    public void setDictIvType(String dictIvType)
    {
        this.dictIvType = dictIvType;
    }

    public String getDictIvType()
    {
        return dictIvType;
    }
    public void setRefIvUsername(String refIvUsername)
    {
        this.refIvUsername = refIvUsername;
    }

    public String getRefIvUsername()
    {
        return refIvUsername;
    }
    public void setIvPhone(String ivPhone)
    {
        this.ivPhone = ivPhone;
    }

    public String getIvPhone()
    {
        return ivPhone;
    }
    public void setDictResult(String dictResult)
    {
        this.dictResult = dictResult;
    }

    public String getDictResult()
    {
        return dictResult;
    }
    public void setIvEvaluate(String ivEvaluate)
    {
        this.ivEvaluate = ivEvaluate;
    }

    public String getIvEvaluate()
    {
        return ivEvaluate;
    }
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobName()
    {
        return jobName;
    }
    public void setJobDesc(String jobDesc)
    {
        this.jobDesc = jobDesc;
    }

    public String getJobDesc()
    {
        return jobDesc;
    }
    public void setWorkYear(Long workYear)
    {
        this.workYear = workYear;
    }

    public Long getWorkYear()
    {
        return workYear;
    }
    public void setWorkLocation(String workLocation)
    {
        this.workLocation = workLocation;
    }

    public String getWorkLocation()
    {
        return workLocation;
    }
    public void setWorkContent(String workContent)
    {
        this.workContent = workContent;
    }

    public String getWorkContent()
    {
        return workContent;
    }
    public void setJobMoney(Long jobMoney)
    {
        this.jobMoney = jobMoney;
    }

    public Long getJobMoney()
    {
        return jobMoney;
    }
    public void setGetInCompayTime(Date getInCompayTime)
    {
        this.getInCompayTime = getInCompayTime;
    }

    public Date getGetInCompayTime()
    {
        return getInCompayTime;
    }
    public void setOutJobDesc(String outJobDesc)
    {
        this.outJobDesc = outJobDesc;
    }

    public String getOutJobDesc()
    {
        return outJobDesc;
    }
    public void setJobSkill(String jobSkill)
    {
        this.jobSkill = jobSkill;
    }

    public String getJobSkill()
    {
        return jobSkill;
    }
    public void setJobInterview(String jobInterview)
    {
        this.jobInterview = jobInterview;
    }

    public String getJobInterview()
    {
        return jobInterview;
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
            .append("refPeopleName", getRefPeopleName())
            .append("ivTime", getIvTime())
            .append("dictIvType", getDictIvType())
            .append("refIvUsername", getRefIvUsername())
            .append("ivPhone", getIvPhone())
            .append("dictResult", getDictResult())
            .append("ivEvaluate", getIvEvaluate())
            .append("jobName", getJobName())
            .append("jobDesc", getJobDesc())
            .append("workYear", getWorkYear())
            .append("workLocation", getWorkLocation())
            .append("workContent", getWorkContent())
            .append("jobMoney", getJobMoney())
            .append("getInCompayTime", getGetInCompayTime())
            .append("outJobDesc", getOutJobDesc())
            .append("jobSkill", getJobSkill())
            .append("jobInterview", getJobInterview())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
