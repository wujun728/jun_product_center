package com.deer.wms.base.system.model;


public class SubinventoryTransferRecordDto extends SubinventoryTransferRecord{
    private String operatorName;
    private String forSubInventoryName;
    private String forSubInventoryCode;
    private String toSubInventoryName;
    private String toSubInventoryCode;
    private String itemName;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getForSubInventoryName() {
        return forSubInventoryName;
    }

    public void setForSubInventoryName(String forSubInventoryName) {
        this.forSubInventoryName = forSubInventoryName;
    }

    public String getForSubInventoryCode() {
        return forSubInventoryCode;
    }

    public void setForSubInventoryCode(String forSubInventoryCode) {
        this.forSubInventoryCode = forSubInventoryCode;
    }

    public String getToSubInventoryName() {
        return toSubInventoryName;
    }

    public void setToSubInventoryName(String toSubInventoryName) {
        this.toSubInventoryName = toSubInventoryName;
    }

    public String getToSubInventoryCode() {
        return toSubInventoryCode;
    }

    public void setToSubInventoryCode(String toSubInventoryCode) {
        this.toSubInventoryCode = toSubInventoryCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}