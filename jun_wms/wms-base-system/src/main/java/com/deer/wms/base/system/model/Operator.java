package com.deer.wms.base.system.model;

import javax.persistence.*;

public class Operator {
    /**
     * 入库操作员id 
     */
    @Id
    @Column(name = "operator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operatorId;
    /**
     * 操作员名称
     */
    @Column(name = "operator_name")
    private String operatorName;
    /**
     * 操作员卡号
     */
    @Column(name = "operator_card")
    private String operatorCard;

    /**
     * 操作员工号
     */
    @Column(name = "emp_no")
    private String empNo;
    /**
     * 初始化权限
     * 1-无
     * 2-有
     */
    @Column(name="autoverify_permission")
    private Integer autoverifyPermission;
    /**
     * IQC检验权限
     * 1-无
     * 2-有
     */
    @Column(name="check_permission")
    private Integer checkPermission;
    /**
     * 合框权限
     * 1-无
     * 2-有
     */
    @Column(name="combine_permission")
    private Integer combinePermission;
    /**
     * 退货权限
     * 1-无
     * 2-有
     */
    @Column(name="return_item_permission")
    private Integer returnItemPermission;
    /**
     * 品质异常检验权限
     * 1-无
     * 2-有
     */
    @Column(name="quality_check_permission")
    private Integer qualityCheckPermission;
    /**
     * 报废权限
     * 1-无
     * 2-有
     */
    @Column(name="scrap_permission")
    private Integer scrapPermission;
    /**
     * 非工单出库权限
     * 1-无
     * 2-有
     */
    @Column(name="manual_out_permission")
    private Integer manualOutPermission;

    /**
     * 转库权限
     * 1-无
     * 2-有
     */
    @Column(name="transfer_warehouse_permission")
    private Integer transferWarehousePermission;

    /**
     *  注销标志
     *  1-正常使用
     *  2-注销
     */
    @Column(name="logout_flag")
    private Integer logoutFlag;

    public Integer getTransferWarehousePermission() {
        return transferWarehousePermission;
    }

    public void setTransferWarehousePermission(Integer transferWarehousePermission) {
        this.transferWarehousePermission = transferWarehousePermission;
    }

    public Integer getLogoutFlag() {
        return logoutFlag;
    }

    public void setLogoutFlag(Integer logoutFlag) {
        this.logoutFlag = logoutFlag;
    }

    public Integer getCheckPermission() {
        return checkPermission;
    }

    public void setCheckPermission(Integer checkPermission) {
        this.checkPermission = checkPermission;
    }

    public Integer getCombinePermission() {
        return combinePermission;
    }

    public void setCombinePermission(Integer combinePermission) {
        this.combinePermission = combinePermission;
    }

    public Integer getReturnItemPermission() {
        return returnItemPermission;
    }

    public void setReturnItemPermission(Integer returnItemPermission) {
        this.returnItemPermission = returnItemPermission;
    }

    public Integer getQualityCheckPermission() {
        return qualityCheckPermission;
    }

    public void setQualityCheckPermission(Integer qualityCheckPermission) {
        this.qualityCheckPermission = qualityCheckPermission;
    }

    public Integer getScrapPermission() {
        return scrapPermission;
    }

    public void setScrapPermission(Integer scrapPermission) {
        this.scrapPermission = scrapPermission;
    }

    public Integer getManualOutPermission() {
        return manualOutPermission;
    }

    public void setManualOutPermission(Integer manualOutPermission) {
        this.manualOutPermission = manualOutPermission;
    }

    public Integer getAutoverifyPermission() {
        return autoverifyPermission;
    }

    public void setAutoverifyPermission(Integer autoverifyPermission) {
        this.autoverifyPermission = autoverifyPermission;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * 获取入库操作员id 
     *
     * @return operator_id - 入库操作员id 
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置入库操作员id 
     *
     * @param operatorId 入库操作员id 
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return operator_name
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return operator_card
     */
    public String getOperatorCard() {
        return operatorCard;
    }

    /**
     * @param operatorCard
     */
    public void setOperatorCard(String operatorCard) {
        this.operatorCard = operatorCard;
    }
}