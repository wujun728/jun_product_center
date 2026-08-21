package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入职报道对象 hr_user_entry_reported
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserEntryReported extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 入职人员名称 */
    @Excel(name = "入职人员名称")
    private String refJobUsername;

    /** 直属领导 */
    @Excel(name = "直属领导")
    private String refUsername1;

    /** 报道部门 */
    @Excel(name = "报道部门")
    private String refJobDeptname;

    /** 入职报告发起人 */
    @Excel(name = "入职报告发起人")
    private String username2;

    /** 导师 */
    @Excel(name = "导师")
    private String refUsername2;

    /** 试用期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime1;

    /** 试用期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用期结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime1;

    /** 合同开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime2;

    /** 合同结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime2;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workLocation;

    /** 考勤班次 */
    @Excel(name = "考勤班次")
    private String workmarkTimes;

    /** 资料是否齐全 */
    @Excel(name = "资料是否齐全")
    private String isFullEntryInfomation;

    /** 入职手续是否办理完成 */
    @Excel(name = "入职手续是否办理完成")
    private String isEntryJobFilish;

    /** 合同是否签订 */
    @Excel(name = "合同是否签订")
    private String isSignContract;

    /** 附件(证件影印件+合同影印件) */
    @Excel(name = "附件(证件影印件+合同影印件)")
    private String files;

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
    public void setRefUsername1(String refUsername1)
    {
        this.refUsername1 = refUsername1;
    }

    public String getRefUsername1()
    {
        return refUsername1;
    }
    public void setRefJobDeptname(String refJobDeptname)
    {
        this.refJobDeptname = refJobDeptname;
    }

    public String getRefJobDeptname()
    {
        return refJobDeptname;
    }
    public void setUsername2(String username2)
    {
        this.username2 = username2;
    }

    public String getUsername2()
    {
        return username2;
    }
    public void setRefUsername2(String refUsername2)
    {
        this.refUsername2 = refUsername2;
    }

    public String getRefUsername2()
    {
        return refUsername2;
    }
    public void setStartTime1(Date startTime1)
    {
        this.startTime1 = startTime1;
    }

    public Date getStartTime1()
    {
        return startTime1;
    }
    public void setEndTime1(Date endTime1)
    {
        this.endTime1 = endTime1;
    }

    public Date getEndTime1()
    {
        return endTime1;
    }
    public void setBeginTime2(Date beginTime2)
    {
        this.beginTime2 = beginTime2;
    }

    public Date getBeginTime2()
    {
        return beginTime2;
    }
    public void setEndTime2(Date endTime2)
    {
        this.endTime2 = endTime2;
    }

    public Date getEndTime2()
    {
        return endTime2;
    }
    public void setWorkLocation(String workLocation)
    {
        this.workLocation = workLocation;
    }

    public String getWorkLocation()
    {
        return workLocation;
    }
    public void setWorkmarkTimes(String workmarkTimes)
    {
        this.workmarkTimes = workmarkTimes;
    }

    public String getWorkmarkTimes()
    {
        return workmarkTimes;
    }
    public void setIsFullEntryInfomation(String isFullEntryInfomation)
    {
        this.isFullEntryInfomation = isFullEntryInfomation;
    }

    public String getIsFullEntryInfomation()
    {
        return isFullEntryInfomation;
    }
    public void setIsEntryJobFilish(String isEntryJobFilish)
    {
        this.isEntryJobFilish = isEntryJobFilish;
    }

    public String getIsEntryJobFilish()
    {
        return isEntryJobFilish;
    }
    public void setIsSignContract(String isSignContract)
    {
        this.isSignContract = isSignContract;
    }

    public String getIsSignContract()
    {
        return isSignContract;
    }
    public void setFiles(String files)
    {
        this.files = files;
    }

    public String getFiles()
    {
        return files;
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
            .append("refUsername1", getRefUsername1())
            .append("refJobDeptname", getRefJobDeptname())
            .append("username2", getUsername2())
            .append("refUsername2", getRefUsername2())
            .append("startTime1", getStartTime1())
            .append("endTime1", getEndTime1())
            .append("beginTime2", getBeginTime2())
            .append("endTime2", getEndTime2())
            .append("workLocation", getWorkLocation())
            .append("workmarkTimes", getWorkmarkTimes())
            .append("isFullEntryInfomation", getIsFullEntryInfomation())
            .append("isEntryJobFilish", getIsEntryJobFilish())
            .append("isSignContract", getIsSignContract())
            .append("files", getFiles())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
