package com.deer.wms.base.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "worker_order_issue_time")
public class WorkerOrderIssueTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_time")
    private String firstTime;

    @Column(name = "second_time")
    private String secondTime;

    @Column(name = "third_time")
    private String thirdTime;

    @Column(name = "fourth_time")
    private String fourthTime;

    @Column(name = "fifth_time")
    private String fifthTime;

    @Column(name = "sixth_time")
    private String sixthTime;

    /**
     * 1-执行 0- 不执行
     */
    @Column(name = "auto_execute")
    private Integer autoExecute;

    /**
     * 工序号
     */
    @Column(name="operation_seqnum")
    private String operationSeqnum;

    /**
     * 呆滞过期导出日期
     */
    @Column(name = "sluggish_export_date")
    private Integer sluggishExportDate;

    /**
     * 1-到货四个月未使用
     * 2-有效期剩余180天
     * 3-其他
     */
    @Column(name = "sluggish_export_param")
    private Integer sluggishExportParam;

    /**
     * 锁定时间(单位:分钟)
     * @return
     */
    @Column(name="lock_time")
    private Integer lockTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return first_time
     */
    public String getFirstTime() {
        return firstTime;
    }

    /**
     * @param firstTime
     */
    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    /**
     * @return second_time
     */
    public String getSecondTime() {
        return secondTime;
    }

    /**
     * @param secondTime
     */
    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }

    /**
     * @return third_time
     */
    public String getThirdTime() {
        return thirdTime;
    }

    /**
     * @param thirdTime
     */
    public void setThirdTime(String thirdTime) {
        this.thirdTime = thirdTime;
    }

    /**
     * @return fourth_time
     */
    public String getFourthTime() {
        return fourthTime;
    }

    /**
     * @param fourthTime
     */
    public void setFourthTime(String fourthTime) {
        this.fourthTime = fourthTime;
    }

    /**
     * @return fifth_time
     */
    public String getFifthTime() {
        return fifthTime;
    }

    /**
     * @param fifthTime
     */
    public void setFifthTime(String fifthTime) {
        this.fifthTime = fifthTime;
    }

    /**
     * @return sixth_time
     */
    public String getSixthTime() {
        return sixthTime;
    }

    /**
     * @param sixthTime
     */
    public void setSixthTime(String sixthTime) {
        this.sixthTime = sixthTime;
    }

    /**
     * 获取1-执行 0- 不执行
     *
     * @return auto_execute - 1-执行 0- 不执行
     */
    public Integer getAutoExecute() {
        return autoExecute;
    }

    public String getOperationSeqnum() {
        return operationSeqnum;
    }

    public void setOperationSeqnum(String operationSeqnum) {
        this.operationSeqnum = operationSeqnum;
    }

    /**
     * 设置1-执行 0- 不执行
     *
     * @param autoExecute 1-执行 0- 不执行
     */
    public void setAutoExecute(Integer autoExecute) {
        this.autoExecute = autoExecute;
    }

    public Integer getSluggishExportDate() {
        return sluggishExportDate;
    }

    public void setSluggishExportDate(Integer sluggishExportDate) {
        this.sluggishExportDate = sluggishExportDate;
    }

    public Integer getSluggishExportParam() {
        return sluggishExportParam;
    }

    public void setSluggishExportParam(Integer sluggishExportParam) {
        this.sluggishExportParam = sluggishExportParam;
    }

    public Integer getLockTime() {
        return lockTime;
    }

    public void setLockTime(Integer lockTime) {
        this.lockTime = lockTime;
    }
}