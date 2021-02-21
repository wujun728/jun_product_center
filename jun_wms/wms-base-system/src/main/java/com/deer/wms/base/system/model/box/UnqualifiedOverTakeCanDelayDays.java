package com.deer.wms.base.system.model.box;

import com.deer.wms.common.annotation.Excel;

public class UnqualifiedOverTakeCanDelayDays {
    @Excel(name="箱号",type=Excel.Type.EXPORT,column = 0)
    private String boxCode;
    @Excel(name="物料编码",type=Excel.Type.EXPORT,column = 1)
    private String itemCode;
    @Excel(name="批次",type=Excel.Type.EXPORT,column = 2)
    private String batch;
    @Excel(name="数量",type=Excel.Type.EXPORT,column = 3)
    private Integer quantity;
    @Excel(name="生产日期",type=Excel.Type.EXPORT,column = 4)
    private String pd;
    @Excel(name="失效日期",type=Excel.Type.EXPORT,column = 5)
    private String exp;
    @Excel(name="入库时间",type=Excel.Type.EXPORT,column = 6)
    private String inTime;
    @Excel(name="转库时间",type=Excel.Type.EXPORT,column = 7)
    private String createTime;
    @Excel(name="转库原因",type=Excel.Type.EXPORT,column = 8)
    private String transferMemo;
    @Excel(name="子库",type=Excel.Type.EXPORT,column = 9)
    private String subInventoryCode;
    @Excel(name="子库名称",type=Excel.Type.EXPORT,column = 10)
    private String subInventoryName;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTransferMemo() {
        return transferMemo;
    }

    public void setTransferMemo(String transferMemo) {
        this.transferMemo = transferMemo;
    }

    public String getSubInventoryCode() {
        return subInventoryCode;
    }

    public void setSubInventoryCode(String subInventoryCode) {
        this.subInventoryCode = subInventoryCode;
    }

    public String getSubInventoryName() {
        return subInventoryName;
    }

    public void setSubInventoryName(String subInventoryName) {
        this.subInventoryName = subInventoryName;
    }
}
