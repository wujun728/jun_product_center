package com.deer.wms.base.system.model.item;

public class ItemInfoDto extends ItemInfo {
    private String itemTypeName;
    private String operatorNameOne;
    private String empNoOne;
    private String operatorNameTwo;
    private String empNoTwo;

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getOperatorNameOne() {
        return operatorNameOne;
    }

    public void setOperatorNameOne(String operatorNameOne) {
        this.operatorNameOne = operatorNameOne;
    }

    public String getEmpNoOne() {
        return empNoOne;
    }

    public void setEmpNoOne(String empNoOne) {
        this.empNoOne = empNoOne;
    }

    public String getOperatorNameTwo() {
        return operatorNameTwo;
    }

    public void setOperatorNameTwo(String operatorNameTwo) {
        this.operatorNameTwo = operatorNameTwo;
    }

    public String getEmpNoTwo() {
        return empNoTwo;
    }

    public void setEmpNoTwo(String empNoTwo) {
        this.empNoTwo = empNoTwo;
    }
}
