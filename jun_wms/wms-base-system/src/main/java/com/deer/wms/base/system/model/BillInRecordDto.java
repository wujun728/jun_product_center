package com.deer.wms.base.system.model;

import javax.persistence.*;


public class BillInRecordDto extends BillInRecord{
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
    /**
     * 物料编码
     */
    private Integer itemId;
    private Integer transactionId;
    private String shipmentNum;
    private String subInventoryCode;
    private String slotting;
    private Integer organizationId;
    private String segment;
    private String expectedArrivalDate;
    private String itemCode;
    private String itemName;
    private String vendorCode;
    private String vendorName;
    private Integer orderQuantity;
    private String lineNum;

    public String getSlotting() {
        return slotting;
    }

    public void setSlotting(String slotting) {
        this.slotting = slotting;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }


    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getSubInventoryCode() {
        return subInventoryCode;
    }

    public void setSubInventoryCode(String subInventoryCode) {
        this.subInventoryCode = subInventoryCode;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getExpectedArrivalDate() {
        return expectedArrivalDate;
    }

    public void setExpectedArrivalDate(String expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }
}