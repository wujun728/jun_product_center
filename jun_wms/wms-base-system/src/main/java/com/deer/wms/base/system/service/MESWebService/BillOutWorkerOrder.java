package com.deer.wms.base.system.service.MESWebService;

public class BillOutWorkerOrder {
    private String macCode;
    private String taskCode;
    //订单号
    private String wipEntity;
    //成品编码
    private String finishedCode;
    //数量
    private Integer quantity;
    /** 优先级 */
    private String priority;
    //物资编码
    private String itemCode;
    //载具编码
    private String shelfCode;

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getWipEntity() {
        return wipEntity;
    }

    public void setWipEntity(String wipEntity) {
        this.wipEntity = wipEntity;
    }

    public String getFinishedCode() {
        return finishedCode;
    }

    public void setFinishedCode(String finishedCode) {
        this.finishedCode = finishedCode;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BillOutWorkerOrder() {
    }

    public BillOutWorkerOrder(String macCode, String taskCode, String wipEntity, String finishedCode, Integer quantity, String priority, String itemCode) {
        this.macCode = macCode;
        this.taskCode = taskCode;
        this.wipEntity = wipEntity;
        this.finishedCode = finishedCode;
        this.quantity = quantity;
        this.priority = priority;
        this.itemCode = itemCode;
    }
}
