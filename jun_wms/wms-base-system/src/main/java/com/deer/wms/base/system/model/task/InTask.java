package com.deer.wms.base.system.model.task;

//用来生成入库任务时接受参数
public class InTask {
    private String boxCode;
    private Integer billInDetailId;
    private Integer cellId;

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public Integer getBillInDetailId() {
        return billInDetailId;
    }

    public void setBillInDetailId(Integer billInDetailId) {
        this.billInDetailId = billInDetailId;
    }

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }
}
