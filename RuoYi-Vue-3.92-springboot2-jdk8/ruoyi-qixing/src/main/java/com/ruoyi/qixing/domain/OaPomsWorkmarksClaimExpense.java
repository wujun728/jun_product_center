package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用报销对象 oa_poms_workmarks_claim_expense
 *
 * @author template
 * @date 2026-06-11
 */
public class OaPomsWorkmarksClaimExpense extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 费用编号 */
    @Excel(name = "费用编号")
    private String costCode;

    /** 工号 */
    @Excel(name = "工号")
    private String usercode;

    /** 报销人(受款人) */
    @Excel(name = "报销人(受款人)")
    private String refUsername;

    /** 报销人部门(走部门) */
    @Excel(name = "报销人部门(走部门)")
    private String deptname;

    /** 费用金额 */
    @Excel(name = "费用金额")
    private Long money;

    /** 货币币种 */
    @Excel(name = "货币币种")
    private String dictMoneyType;

    /** 报销人岗位类型 */
    @Excel(name = "报销人岗位类型")
    private String userPost;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refTodoPersonCode;

    /** 办理人(可代办) */
    @Excel(name = "办理人(可代办)")
    private String refTodoPreson;

    /** 办理人部门 */
    @Excel(name = "办理人部门")
    private String refTodoDeptname;

    /** 是否列入预算 */
    @Excel(name = "是否列入预算")
    private String dictYeNo;

    /** 是否项目费用 */
    @Excel(name = "是否项目费用")
    private String dictBelongProject;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 关联项目名称(项目费用) */
    @Excel(name = "关联项目名称(项目费用)")
    private String refProjectName;

    /** 费用类型 */
    @Excel(name = "费用类型")
    private String dictCostType;

    /** 费用明细 */
    @Excel(name = "费用明细")
    private String costDetail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
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
    public void setCostCode(String costCode)
    {
        this.costCode = costCode;
    }

    public String getCostCode()
    {
        return costCode;
    }
    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsercode()
    {
        return usercode;
    }
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setDeptname(String deptname)
    {
        this.deptname = deptname;
    }

    public String getDeptname()
    {
        return deptname;
    }
    public void setMoney(Long money)
    {
        this.money = money;
    }

    public Long getMoney()
    {
        return money;
    }
    public void setDictMoneyType(String dictMoneyType)
    {
        this.dictMoneyType = dictMoneyType;
    }

    public String getDictMoneyType()
    {
        return dictMoneyType;
    }
    public void setUserPost(String userPost)
    {
        this.userPost = userPost;
    }

    public String getUserPost()
    {
        return userPost;
    }
    public void setRefTodoPersonCode(String refTodoPersonCode)
    {
        this.refTodoPersonCode = refTodoPersonCode;
    }

    public String getRefTodoPersonCode()
    {
        return refTodoPersonCode;
    }
    public void setRefTodoPreson(String refTodoPreson)
    {
        this.refTodoPreson = refTodoPreson;
    }

    public String getRefTodoPreson()
    {
        return refTodoPreson;
    }
    public void setRefTodoDeptname(String refTodoDeptname)
    {
        this.refTodoDeptname = refTodoDeptname;
    }

    public String getRefTodoDeptname()
    {
        return refTodoDeptname;
    }
    public void setDictYeNo(String dictYeNo)
    {
        this.dictYeNo = dictYeNo;
    }

    public String getDictYeNo()
    {
        return dictYeNo;
    }
    public void setDictBelongProject(String dictBelongProject)
    {
        this.dictBelongProject = dictBelongProject;
    }

    public String getDictBelongProject()
    {
        return dictBelongProject;
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
    public void setDictCostType(String dictCostType)
    {
        this.dictCostType = dictCostType;
    }

    public String getDictCostType()
    {
        return dictCostType;
    }
    public void setCostDetail(String costDetail)
    {
        this.costDetail = costDetail;
    }

    public String getCostDetail()
    {
        return costDetail;
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
            .append("costCode", getCostCode())
            .append("usercode", getUsercode())
            .append("refUsername", getRefUsername())
            .append("deptname", getDeptname())
            .append("money", getMoney())
            .append("dictMoneyType", getDictMoneyType())
            .append("userPost", getUserPost())
            .append("refTodoPersonCode", getRefTodoPersonCode())
            .append("refTodoPreson", getRefTodoPreson())
            .append("refTodoDeptname", getRefTodoDeptname())
            .append("dictYeNo", getDictYeNo())
            .append("dictBelongProject", getDictBelongProject())
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("dictCostType", getDictCostType())
            .append("costDetail", getCostDetail())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
