package com.deer.wms.base.system.model;

import com.deer.wms.common.annotation.Excel;

import javax.persistence.*;

public class SluggishOverdueDto extends SluggishOverdue{
    @Excel(name="物料描述",type=Excel.Type.EXPORT,column = 1)
    private String itemName;
    @Excel(name="存储期限",type=Excel.Type.EXPORT,column = 7)
    private Integer saveDay;
    @Excel(name="剩余天数",type=Excel.Type.EXPORT,column = 8)
    private Integer surplusValidDay;
    @Excel(name="本月消耗数量",type=Excel.Type.EXPORT,column = 14)
    private Integer consumeQuantity;
    @Excel(name="单位",type=Excel.Type.EXPORT,column = 2)
    private String unit;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSaveDay() {
        return saveDay;
    }

    public void setSaveDay(Integer saveDay) {
        this.saveDay = saveDay;
    }

    public Integer getSurplusValidDay() {
        return surplusValidDay;
    }

    public void setSurplusValidDay(Integer surplusValidDay) {
        this.surplusValidDay = surplusValidDay;
    }

    public Integer getConsumeQuantity() {
        return consumeQuantity;
    }

    public void setConsumeQuantity(Integer consumeQuantity) {
        this.consumeQuantity = consumeQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}