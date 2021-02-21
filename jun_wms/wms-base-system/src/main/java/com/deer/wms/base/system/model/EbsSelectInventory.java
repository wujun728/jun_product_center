package com.deer.wms.base.system.model;

public class EbsSelectInventory {

    private String itemCode;
    private Integer quantity;
    private Integer subInventoryId;
    private String batch;
    private String exp;
    private String slotting;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSubInventoryId() {
        return subInventoryId;
    }

    public void setSubInventoryId(Integer subInventoryId) {
        this.subInventoryId = subInventoryId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSlotting() {
        return slotting;
    }

    public void setSlotting(String slotting) {
        this.slotting = slotting;
    }
}
