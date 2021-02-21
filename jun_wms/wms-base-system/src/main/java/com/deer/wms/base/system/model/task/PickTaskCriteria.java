package com.deer.wms.base.system.model.task;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by guo on 2019/07/23.
*/
public class PickTaskCriteria extends QueryCriteria {
    private Integer state;
    private Integer workOrderStockState;
    private Integer pickState;
    private Integer billOutDetailId;
    private String boxCode;
    private Integer billOutMasterType;
    private String billNo;
    private String itemCode;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public Integer getBillOutDetailId() {
        return billOutDetailId;
    }

    public void setBillOutDetailId(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }

    public Integer getPickState() {
        return pickState;
    }

    public void setPickState(Integer pickState) {
        this.pickState = pickState;
    }

    public Integer getWorkOrderStockState() {
        return workOrderStockState;
    }

    public void setWorkOrderStockState(Integer workOrderStockState) {
        this.workOrderStockState = workOrderStockState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBillOutMasterType() {
        return billOutMasterType;
    }

    public void setBillOutMasterType(Integer billOutMasterType) {
        this.billOutMasterType = billOutMasterType;
    }

    public PickTaskCriteria() {
    }

    public PickTaskCriteria(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }

    public PickTaskCriteria(Integer billOutDetailId, String boxCode) {
        this.billOutDetailId = billOutDetailId;
        this.boxCode = boxCode;
    }


}
