package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

/**
* Created by  on 2019/10/18.
*/
public class BillInRecordCriteria extends QueryCriteria {
    private Integer state;
    private String barCode;
    private Integer orderParam;
    private List<Integer> billInDetailIds;
    private String boxCode;
    /**
     * 采购订单头ID
     */
    private Integer poHeaderId;
    /**
     * 采购订单行ID
     */
    private Integer poLineId;
    /**
     * 发运行ID
     */
    private Integer lineLocationId;
    /**
     * 分配行ID
     */
    private Integer poDistributionId;
    private String itemCode;
    private String batch;
    private String segment;
    private String acceptTime;
    private String receiptNum;

    private List<Integer> billInRecordIds;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(Integer orderParam) {
        this.orderParam = orderParam;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Integer getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    public Integer getLineLocationId() {
        return lineLocationId;
    }

    public void setLineLocationId(Integer lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    public Integer getPoDistributionId() {
        return poDistributionId;
    }

    public void setPoDistributionId(Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    public List<Integer> getBillInDetailIds() {
        return billInDetailIds;
    }

    public void setBillInDetailIds(List<Integer> billInDetailIds) {
        this.billInDetailIds = billInDetailIds;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {

        this.acceptTime = acceptTime;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public List<Integer> getBillInRecordIds() {
        return billInRecordIds;
    }

    public void setBillInRecordIds(List<Integer> billInRecordIds) {
        this.billInRecordIds = billInRecordIds;
    }

    public BillInRecordCriteria() {
    }

    public BillInRecordCriteria(Integer state, String barCode) {
        this.state = state;
        this.barCode = barCode;
    }

    public BillInRecordCriteria(Integer state,Integer orderParam) {
        this.state = state;
        this.orderParam = orderParam;
    }

    public BillInRecordCriteria(Integer orderParam, List<Integer> billInDetailIds) {
        this.orderParam = orderParam;
        this.billInDetailIds = billInDetailIds;
    }

    public BillInRecordCriteria(Integer orderParam, List<Integer> billInDetailIds, String boxCode) {
        this.orderParam = orderParam;
        this.billInDetailIds = billInDetailIds;
        this.boxCode = boxCode;
    }

    public BillInRecordCriteria(Integer state, Integer poHeaderId, Integer poLineId, Integer lineLocationId, Integer poDistributionId) {
        this.state = state;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.lineLocationId = lineLocationId;
        this.poDistributionId = poDistributionId;
    }

    public BillInRecordCriteria(Integer state, String batch, Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
        this.state = state;
        this.batch = batch;
    }

    public BillInRecordCriteria(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public BillInRecordCriteria(List<Integer> billInRecordIds,Integer orderParam) {
        this.billInRecordIds = billInRecordIds;
        this.orderParam = orderParam;
    }

    public BillInRecordCriteria(String itemCode, String batch,Integer state) {
        this.itemCode = itemCode;
        this.batch = batch;
        this.state = state;
    }
}
