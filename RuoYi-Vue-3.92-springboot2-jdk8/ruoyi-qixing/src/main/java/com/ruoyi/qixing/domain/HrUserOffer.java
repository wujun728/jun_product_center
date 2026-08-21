package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Offer发放对象 hr_user_offer
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserOffer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 入职人员名称 */
    @Excel(name = "入职人员名称")
    private String refJobUsername;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String location;

    /** 部门 */
    @Excel(name = "部门")
    private String refDeptname;

    /** 招聘专员 */
    @Excel(name = "招聘专员")
    private String refUsername1;

    /** Offer状态 */
    @Excel(name = "Offer状态")
    private String dictOfferStatus;

    /** Offer邮件发送 */
    @Excel(name = "Offer邮件发送")
    private String dictYesNo;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jobInTime;

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
    public void setRefJobUsername(String refJobUsername)
    {
        this.refJobUsername = refJobUsername;
    }

    public String getRefJobUsername()
    {
        return refJobUsername;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setRefDeptname(String refDeptname)
    {
        this.refDeptname = refDeptname;
    }

    public String getRefDeptname()
    {
        return refDeptname;
    }
    public void setRefUsername1(String refUsername1)
    {
        this.refUsername1 = refUsername1;
    }

    public String getRefUsername1()
    {
        return refUsername1;
    }
    public void setDictOfferStatus(String dictOfferStatus)
    {
        this.dictOfferStatus = dictOfferStatus;
    }

    public String getDictOfferStatus()
    {
        return dictOfferStatus;
    }
    public void setDictYesNo(String dictYesNo)
    {
        this.dictYesNo = dictYesNo;
    }

    public String getDictYesNo()
    {
        return dictYesNo;
    }
    public void setJobInTime(Date jobInTime)
    {
        this.jobInTime = jobInTime;
    }

    public Date getJobInTime()
    {
        return jobInTime;
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
            .append("refJobUsername", getRefJobUsername())
            .append("location", getLocation())
            .append("refDeptname", getRefDeptname())
            .append("refUsername1", getRefUsername1())
            .append("dictOfferStatus", getDictOfferStatus())
            .append("dictYesNo", getDictYesNo())
            .append("jobInTime", getJobInTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
