package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资审核发放对象 oa_poms_workmarks_payroll
 *
 * @author template
 * @date 2026-06-11
 */
public class OaPomsWorkmarksPayroll extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 工号 */
    @Excel(name = "工号")
    private String usercode;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String refUsername;

    /** 部门 */
    @Excel(name = "部门")
    private String deptname;

    /** 岗位 */
    @Excel(name = "岗位")
    private String workrole;

    /** 所属月份 */
    @Excel(name = "所属月份")
    private String month;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private BigDecimal payrollAct;

    /** 工资期间 */
    @Excel(name = "工资期间")
    private String payrollDatatimeSe;

    /** 标准月薪 */
    @Excel(name = "标准月薪")
    private BigDecimal payrollBase;

    /** 本月月薪 */
    @Excel(name = "本月月薪")
    private BigDecimal payrollCurMouth;

    /** 税前补发 */
    @Excel(name = "税前补发")
    private BigDecimal taxBeforeBufa;

    /** 税前扣款 */
    @Excel(name = "税前扣款")
    private BigDecimal taxBeforeKoukuan;

    /** 请假小时数 */
    @Excel(name = "请假小时数")
    private BigDecimal hoursQingjia;

    /** 缺勤小时数 */
    @Excel(name = "缺勤小时数")
    private BigDecimal hoursQueqing;

    /** 收入 */
    @Excel(name = "收入")
    private BigDecimal moneyAllIn;

    /** 社保扣款 */
    @Excel(name = "社保扣款")
    private BigDecimal sbKoukuan;

    /** 公积金扣款 */
    @Excel(name = "公积金扣款")
    private BigDecimal gjjKoukuan;

    /** 个税 */
    @Excel(name = "个税")
    private BigDecimal geshui;

    /** 税后补发 */
    @Excel(name = "税后补发")
    private BigDecimal taxAfterBufa;

    /** 税后扣款 */
    @Excel(name = "税后扣款")
    private BigDecimal taxAfterKoukuan;

    /** 实际出勤天数 */
    @Excel(name = "实际出勤天数")
    private Long workDayCount;

    /** 当月应出勤天数 */
    @Excel(name = "当月应出勤天数")
    private Long sholdbeWorkDayCount;

    /** 工作时长(小时) */
    @Excel(name = "工作时长(小时)")
    private BigDecimal workTotalHours;

    /** 应出勤时长(小时) */
    @Excel(name = "应出勤时长(小时)")
    private BigDecimal shoulbeWorkHours;

    /** 工资审核状态 */
    @Excel(name = "工资审核状态")
    private String payrollState;

    /** 工资审核意见 */
    @Excel(name = "工资审核意见")
    private String payrollStateMsg;

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
    private String orderStatus;

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
    public void setWorkrole(String workrole)
    {
        this.workrole = workrole;
    }

    public String getWorkrole()
    {
        return workrole;
    }
    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getMonth()
    {
        return month;
    }
    public void setPayrollAct(BigDecimal payrollAct)
    {
        this.payrollAct = payrollAct;
    }

    public BigDecimal getPayrollAct()
    {
        return payrollAct;
    }
    public void setPayrollDatatimeSe(String payrollDatatimeSe)
    {
        this.payrollDatatimeSe = payrollDatatimeSe;
    }

    public String getPayrollDatatimeSe()
    {
        return payrollDatatimeSe;
    }
    public void setPayrollBase(BigDecimal payrollBase)
    {
        this.payrollBase = payrollBase;
    }

    public BigDecimal getPayrollBase()
    {
        return payrollBase;
    }
    public void setPayrollCurMouth(BigDecimal payrollCurMouth)
    {
        this.payrollCurMouth = payrollCurMouth;
    }

    public BigDecimal getPayrollCurMouth()
    {
        return payrollCurMouth;
    }
    public void setTaxBeforeBufa(BigDecimal taxBeforeBufa)
    {
        this.taxBeforeBufa = taxBeforeBufa;
    }

    public BigDecimal getTaxBeforeBufa()
    {
        return taxBeforeBufa;
    }
    public void setTaxBeforeKoukuan(BigDecimal taxBeforeKoukuan)
    {
        this.taxBeforeKoukuan = taxBeforeKoukuan;
    }

    public BigDecimal getTaxBeforeKoukuan()
    {
        return taxBeforeKoukuan;
    }
    public void setHoursQingjia(BigDecimal hoursQingjia)
    {
        this.hoursQingjia = hoursQingjia;
    }

    public BigDecimal getHoursQingjia()
    {
        return hoursQingjia;
    }
    public void setHoursQueqing(BigDecimal hoursQueqing)
    {
        this.hoursQueqing = hoursQueqing;
    }

    public BigDecimal getHoursQueqing()
    {
        return hoursQueqing;
    }
    public void setMoneyAllIn(BigDecimal moneyAllIn)
    {
        this.moneyAllIn = moneyAllIn;
    }

    public BigDecimal getMoneyAllIn()
    {
        return moneyAllIn;
    }
    public void setSbKoukuan(BigDecimal sbKoukuan)
    {
        this.sbKoukuan = sbKoukuan;
    }

    public BigDecimal getSbKoukuan()
    {
        return sbKoukuan;
    }
    public void setGjjKoukuan(BigDecimal gjjKoukuan)
    {
        this.gjjKoukuan = gjjKoukuan;
    }

    public BigDecimal getGjjKoukuan()
    {
        return gjjKoukuan;
    }
    public void setGeshui(BigDecimal geshui)
    {
        this.geshui = geshui;
    }

    public BigDecimal getGeshui()
    {
        return geshui;
    }
    public void setTaxAfterBufa(BigDecimal taxAfterBufa)
    {
        this.taxAfterBufa = taxAfterBufa;
    }

    public BigDecimal getTaxAfterBufa()
    {
        return taxAfterBufa;
    }
    public void setTaxAfterKoukuan(BigDecimal taxAfterKoukuan)
    {
        this.taxAfterKoukuan = taxAfterKoukuan;
    }

    public BigDecimal getTaxAfterKoukuan()
    {
        return taxAfterKoukuan;
    }
    public void setWorkDayCount(Long workDayCount)
    {
        this.workDayCount = workDayCount;
    }

    public Long getWorkDayCount()
    {
        return workDayCount;
    }
    public void setSholdbeWorkDayCount(Long sholdbeWorkDayCount)
    {
        this.sholdbeWorkDayCount = sholdbeWorkDayCount;
    }

    public Long getSholdbeWorkDayCount()
    {
        return sholdbeWorkDayCount;
    }
    public void setWorkTotalHours(BigDecimal workTotalHours)
    {
        this.workTotalHours = workTotalHours;
    }

    public BigDecimal getWorkTotalHours()
    {
        return workTotalHours;
    }
    public void setShoulbeWorkHours(BigDecimal shoulbeWorkHours)
    {
        this.shoulbeWorkHours = shoulbeWorkHours;
    }

    public BigDecimal getShoulbeWorkHours()
    {
        return shoulbeWorkHours;
    }
    public void setPayrollState(String payrollState)
    {
        this.payrollState = payrollState;
    }

    public String getPayrollState()
    {
        return payrollState;
    }
    public void setPayrollStateMsg(String payrollStateMsg)
    {
        this.payrollStateMsg = payrollStateMsg;
    }

    public String getPayrollStateMsg()
    {
        return payrollStateMsg;
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
    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("usercode", getUsercode())
            .append("refUsername", getRefUsername())
            .append("deptname", getDeptname())
            .append("workrole", getWorkrole())
            .append("month", getMonth())
            .append("payrollAct", getPayrollAct())
            .append("payrollDatatimeSe", getPayrollDatatimeSe())
            .append("payrollBase", getPayrollBase())
            .append("payrollCurMouth", getPayrollCurMouth())
            .append("taxBeforeBufa", getTaxBeforeBufa())
            .append("taxBeforeKoukuan", getTaxBeforeKoukuan())
            .append("hoursQingjia", getHoursQingjia())
            .append("hoursQueqing", getHoursQueqing())
            .append("moneyAllIn", getMoneyAllIn())
            .append("sbKoukuan", getSbKoukuan())
            .append("gjjKoukuan", getGjjKoukuan())
            .append("geshui", getGeshui())
            .append("taxAfterBufa", getTaxAfterBufa())
            .append("taxAfterKoukuan", getTaxAfterKoukuan())
            .append("workDayCount", getWorkDayCount())
            .append("sholdbeWorkDayCount", getSholdbeWorkDayCount())
            .append("workTotalHours", getWorkTotalHours())
            .append("shoulbeWorkHours", getShoulbeWorkHours())
            .append("payrollState", getPayrollState())
            .append("payrollStateMsg", getPayrollStateMsg())
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
