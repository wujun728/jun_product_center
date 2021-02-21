package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

/**
* Created by  on 2019/12/05.
*/
public class BillInCheckRecordCriteria {
    private List<Integer> checkIds;
    private Integer poDistributionId;
    private String batch;
    private Integer transactionId;
    private Integer state;
    private String receiptNum;

    public List<Integer> getCheckIds() {
        return checkIds;
    }

    public void setCheckIds(List<Integer> checkIds) {
        this.checkIds = checkIds;
    }

    public Integer getPoDistributionId() {
        return poDistributionId;
    }

    public void setPoDistributionId(Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BillInCheckRecordCriteria() {
    }

    public BillInCheckRecordCriteria(List<Integer> checkIds) {
        this.checkIds = checkIds;
    }

    public BillInCheckRecordCriteria(Integer poDistributionId, String batch) {
        this.poDistributionId = poDistributionId;
        this.batch = batch;
    }

    public BillInCheckRecordCriteria(String receiptNum,Integer transactionId) {
        this.receiptNum = receiptNum;
        this.transactionId = transactionId;
    }

}
