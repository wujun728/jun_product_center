package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 离职对象 hr_user_dimission
 *
 * @author template
 * @date 2026-06-11
 */
public class HrUserDimission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 工号 */
    @Excel(name = "工号")
    private String usercode;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 部门 */
    @Excel(name = "部门")
    private String deptname;

    /** 职务 */
    @Excel(name = "职务")
    private String postname;

    /** 员工类型 */
    @Excel(name = "员工类型")
    private String usertype;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEndTime;

    /** 项目组 */
    @Excel(name = "项目组")
    private String projectName;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workLocation;

    /** 离职原因分类 */
    @Excel(name = "离职原因分类")
    private String leanveReasonType;

    /** 离职原因详细 */
    @Excel(name = "离职原因详细")
    private String leanveReasonDetail;

    /** 是否需要离职证明 */
    @Excel(name = "是否需要离职证明")
    private String isNeedLeaveProve;

    /** 离职证明领取方式 */
    @Excel(name = "离职证明领取方式")
    private String proveGetWay;

    /** 离职证明邮寄地址 */
    @Excel(name = "离职证明邮寄地址")
    private String proveGetAdress;

    /** 收件人 */
    @Excel(name = "收件人")
    private String proveGetPeople;

    /** 收件电话 */
    @Excel(name = "收件电话")
    private String proveGetPhone;

    /** 直接上级替换人工号 */
    @Excel(name = "直接上级替换人工号")
    private String leaderUsercode;

    /** 直接上级替换人姓名 */
    @Excel(name = "直接上级替换人姓名")
    private String leaderUsername;

    /** 最后工作日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后工作日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastWorkDay;

    /** 薪资结算日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "薪资结算日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payOnLastDay;

    /** 是否涉及工作交接 */
    @Excel(name = "是否涉及工作交接")
    private String isNeedJobHandover;

    /** 工作交接是否完成 */
    @Excel(name = "工作交接是否完成")
    private String isHandoverFilish;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String wfsate;

    /** 当前节点名称 */
    @Excel(name = "当前节点名称")
    private String currNodename;

    /** 当前节点审批人 */
    @Excel(name = "当前节点审批人")
    private String currNodeUsername;

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
    public void setUsertype(String usertype)
    {
        this.usertype = usertype;
    }

    public String getUsertype()
    {
        return usertype;
    }
    public void setEntryTime(Date entryTime)
    {
        this.entryTime = entryTime;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }
    public void setContractEndTime(Date contractEndTime)
    {
        this.contractEndTime = contractEndTime;
    }

    public Date getContractEndTime()
    {
        return contractEndTime;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setWorkLocation(String workLocation)
    {
        this.workLocation = workLocation;
    }

    public String getWorkLocation()
    {
        return workLocation;
    }
    public void setLeanveReasonType(String leanveReasonType)
    {
        this.leanveReasonType = leanveReasonType;
    }

    public String getLeanveReasonType()
    {
        return leanveReasonType;
    }
    public void setLeanveReasonDetail(String leanveReasonDetail)
    {
        this.leanveReasonDetail = leanveReasonDetail;
    }

    public String getLeanveReasonDetail()
    {
        return leanveReasonDetail;
    }
    public void setIsNeedLeaveProve(String isNeedLeaveProve)
    {
        this.isNeedLeaveProve = isNeedLeaveProve;
    }

    public String getIsNeedLeaveProve()
    {
        return isNeedLeaveProve;
    }
    public void setProveGetWay(String proveGetWay)
    {
        this.proveGetWay = proveGetWay;
    }

    public String getProveGetWay()
    {
        return proveGetWay;
    }
    public void setProveGetAdress(String proveGetAdress)
    {
        this.proveGetAdress = proveGetAdress;
    }

    public String getProveGetAdress()
    {
        return proveGetAdress;
    }
    public void setProveGetPeople(String proveGetPeople)
    {
        this.proveGetPeople = proveGetPeople;
    }

    public String getProveGetPeople()
    {
        return proveGetPeople;
    }
    public void setProveGetPhone(String proveGetPhone)
    {
        this.proveGetPhone = proveGetPhone;
    }

    public String getProveGetPhone()
    {
        return proveGetPhone;
    }
    public void setLeaderUsercode(String leaderUsercode)
    {
        this.leaderUsercode = leaderUsercode;
    }

    public String getLeaderUsercode()
    {
        return leaderUsercode;
    }
    public void setLeaderUsername(String leaderUsername)
    {
        this.leaderUsername = leaderUsername;
    }

    public String getLeaderUsername()
    {
        return leaderUsername;
    }
    public void setLastWorkDay(Date lastWorkDay)
    {
        this.lastWorkDay = lastWorkDay;
    }

    public Date getLastWorkDay()
    {
        return lastWorkDay;
    }
    public void setPayOnLastDay(Date payOnLastDay)
    {
        this.payOnLastDay = payOnLastDay;
    }

    public Date getPayOnLastDay()
    {
        return payOnLastDay;
    }
    public void setIsNeedJobHandover(String isNeedJobHandover)
    {
        this.isNeedJobHandover = isNeedJobHandover;
    }

    public String getIsNeedJobHandover()
    {
        return isNeedJobHandover;
    }
    public void setIsHandoverFilish(String isHandoverFilish)
    {
        this.isHandoverFilish = isHandoverFilish;
    }

    public String getIsHandoverFilish()
    {
        return isHandoverFilish;
    }
    public void setWfsate(String wfsate)
    {
        this.wfsate = wfsate;
    }

    public String getWfsate()
    {
        return wfsate;
    }
    public void setCurrNodename(String currNodename)
    {
        this.currNodename = currNodename;
    }

    public String getCurrNodename()
    {
        return currNodename;
    }
    public void setCurrNodeUsername(String currNodeUsername)
    {
        this.currNodeUsername = currNodeUsername;
    }

    public String getCurrNodeUsername()
    {
        return currNodeUsername;
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
            .append("usertype", getUsertype())
            .append("entryTime", getEntryTime())
            .append("contractEndTime", getContractEndTime())
            .append("projectName", getProjectName())
            .append("workLocation", getWorkLocation())
            .append("leanveReasonType", getLeanveReasonType())
            .append("leanveReasonDetail", getLeanveReasonDetail())
            .append("isNeedLeaveProve", getIsNeedLeaveProve())
            .append("proveGetWay", getProveGetWay())
            .append("proveGetAdress", getProveGetAdress())
            .append("proveGetPeople", getProveGetPeople())
            .append("proveGetPhone", getProveGetPhone())
            .append("leaderUsercode", getLeaderUsercode())
            .append("leaderUsername", getLeaderUsername())
            .append("lastWorkDay", getLastWorkDay())
            .append("payOnLastDay", getPayOnLastDay())
            .append("isNeedJobHandover", getIsNeedJobHandover())
            .append("isHandoverFilish", getIsHandoverFilish())
            .append("createTime", getCreateTime())
            .append("wfsate", getWfsate())
            .append("currNodename", getCurrNodename())
            .append("currNodeUsername", getCurrNodeUsername())
            .append("remark", getRemark())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
