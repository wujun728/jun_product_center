package com.deer.wms.base.system.model.task;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

/**
* Created by guo on 2019/08/27.
*/
public class TaskInfoCriteria extends QueryCriteria {
    private String itemCode;
    private String batch;
    private String boxCode;
    private Integer billOutDetailId;
    private Integer type;
    private Integer state;
    /**
     * 是否叫空框
     *  1-叫空框
     *  2-叫半框
     */
    private Integer whetherNullBox;
    private String exp;
    private String operatorName;
    private List<Integer> types;
    private String loginPersonCardNo;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getWhetherNullBox() {
        return whetherNullBox;
    }

    public void setWhetherNullBox(Integer whetherNullBox) {
        this.whetherNullBox = whetherNullBox;
    }

    public Integer getBillOutDetailId() {
        return billOutDetailId;
    }

    public void setBillOutDetailId(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public String getLoginPersonCardNo() {
        return loginPersonCardNo;
    }

    public void setLoginPersonCardNo(String loginPersonCardNo) {
        this.loginPersonCardNo = loginPersonCardNo;
    }

    public TaskInfoCriteria() {
    }

    public TaskInfoCriteria(Integer billOutDetailId, Integer type, Integer state) {
        this.billOutDetailId = billOutDetailId;
        this.type = type;
        this.state = state;
    }
}
