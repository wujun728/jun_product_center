package com.deer.wms.base.system.model.task;

public class PickTaskDto extends PickTask{
    private String shelfName;
    private Integer cellId;
    private Integer sColumn;
    private Integer sRow;
    private Integer quantity;
    private String priority;
    private Integer billOutDetailId;
    private Integer billId;
    private String slotting;
    private String subInventoryCode;
    private Integer boxQuantity;
    private String itemCode;
    private String batch;
    private String billNo;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getsColumn() {
        return sColumn;
    }

    public void setsColumn(Integer sColumn) {
        this.sColumn = sColumn;
    }

    public Integer getsRow() {
        return sRow;
    }

    public void setsRow(Integer sRow) {
        this.sRow = sRow;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getBillOutDetailId() {
        return billOutDetailId;
    }

    public void setBillOutDetailId(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getSlotting() {
        return slotting;
    }

    public void setSlotting(String slotting) {
        this.slotting = slotting;
    }

    public String getSubInventoryCode() {
        return subInventoryCode;
    }

    public void setSubInventoryCode(String subInventoryCode) {
        this.subInventoryCode = subInventoryCode;
    }

    public Integer getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Integer boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String getBatch() {
        return batch;
    }

    @Override
    public void setBatch(String batch) {
        this.batch = batch;
    }
}