package com.deer.wms.base.system.model.box;

import com.deer.wms.common.annotation.Excel;

public class OverdueList {
    @Excel(name="序号",type=Excel.Type.EXPORT,column = 0)
    private Integer sequence;
    @Excel(name="物料编码",type=Excel.Type.EXPORT,column = 1)
    private String itemCode;
    @Excel(name="物料描述",type=Excel.Type.EXPORT,column = 2)
    private String itemName;
    @Excel(name="供应商名称",type=Excel.Type.EXPORT,column = 3)
    private String supplierName;
    @Excel(name="生产日期",type=Excel.Type.EXPORT,column = 4)
    private String pd;
    @Excel(name="失效日期",type=Excel.Type.EXPORT,column = 5)
    private String exp;
    @Excel(name="延期日期",type=Excel.Type.EXPORT,column = 6)
    private String postpone;
    @Excel(name="批次",type=Excel.Type.EXPORT,column = 7)
    private String batch;
    @Excel(name="失效日期",type=Excel.Type.EXPORT,column = 8)
    private String unit;
    @Excel(name="过期数量",type=Excel.Type.EXPORT,column = 9)
    private Integer quantity;
    @Excel(name="备注",type=Excel.Type.EXPORT,column = 10)
    private String memo;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getPostpone() {
        return postpone;
    }

    public void setPostpone(String postpone) {
        this.postpone = postpone;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public OverdueList() {
    }

    public OverdueList(Integer sequence, String itemCode, String itemName, String supplierName, String pd,String batch, String unit, Integer quantity) {
        this.sequence = sequence;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierName = supplierName;
        this.pd = pd;
        this.batch = batch;
        this.unit = unit;
        this.quantity = quantity;
    }
}
