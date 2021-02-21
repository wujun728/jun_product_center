package com.deer.wms.base.system.model.box;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

public class BoxItemCriteria extends QueryCriteria {
    private String boxCode;

    private String itemName;

    private String itemCode;

    private String batch;

    private String shelfName;

    private Integer sRow;

    private Integer sColumn;

    private Integer boxState;

    private Integer quantity;

    private Integer boxCellId;

    private Integer cellState;
    private Integer subInventoryId;
    private String exp;
    private Integer boxCodeId;
    private String loginPersonCardNo;
    private String supplierCode;
    private String bool;
    private Integer[] ids;
    private Integer orderByState;
    private Integer quantitys;
    private Integer accountAliasId;
    private Integer workOrderStockState;
    private Integer sluggishExportParam;
    private Integer willOverdueDay;
    private Integer hasGood;
    //不合格库可滞库天数
    private Integer unqualifiedStorageDay;

    public Integer getUnqualifiedStorageDay() {
        return unqualifiedStorageDay;
    }

    public void setUnqualifiedStorageDay(Integer unqualifiedStorageDay) {
        this.unqualifiedStorageDay = unqualifiedStorageDay;
    }

    public Integer getHasGood() {
        return hasGood;
    }

    public void setHasGood(Integer hasGood) {
        this.hasGood = hasGood;
    }

    public Integer getWillOverdueDay() {
        return willOverdueDay;
    }

    public void setWillOverdueDay(Integer willOverdueDay) {
        this.willOverdueDay = willOverdueDay;
    }

    public Integer getSluggishExportParam() {
        return sluggishExportParam;
    }

    public void setSluggishExportParam(Integer sluggishExportParam) {
        this.sluggishExportParam = sluggishExportParam;
    }

    public Integer getWorkOrderStockState() {
        return workOrderStockState;
    }

    public void setWorkOrderStockState(Integer workOrderStockState) {
        this.workOrderStockState = workOrderStockState;
    }

    public Integer getAccountAliasId() {
        return accountAliasId;
    }

    public void setAccountAliasId(Integer accountAliasId) {
        this.accountAliasId = accountAliasId;
    }

    public Integer getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(Integer quantitys) {
        this.quantitys = quantitys;
    }

    public Integer getOrderByState() {
        return orderByState;
    }

    public void setOrderByState(Integer orderByState) {
        this.orderByState = orderByState;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getBoxCodeId() {
        return boxCodeId;
    }

    public void setBoxCodeId(Integer boxCodeId) {
        this.boxCodeId = boxCodeId;
    }

    public String getLoginPersonCardNo() {
        return loginPersonCardNo;
    }

    public void setLoginPersonCardNo(String loginPersonCardNo) {
        this.loginPersonCardNo = loginPersonCardNo;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getCellState() {
        return cellState;
    }

    public void setCellState(Integer cellState) {
        this.cellState = cellState;
    }

    public Integer getSubInventoryId() {
        return subInventoryId;
    }

    public void setSubInventoryId(Integer subInventoryId) {
        this.subInventoryId = subInventoryId;
    }

    public Integer getBoxCellId() {
        return boxCellId;
    }

    public void setBoxCellId(Integer boxCellId) {
        this.boxCellId = boxCellId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Integer getsRow() {
        return sRow;
    }

    public void setsRow(Integer sRow) {
        this.sRow = sRow;
    }

    public Integer getsColumn() {
        return sColumn;
    }

    public void setsColumn(Integer sColumn) {
        this.sColumn = sColumn;
    }

    public Integer getBoxState() {
        return boxState;
    }

    public void setBoxState(Integer boxState) {
        this.boxState = boxState;
    }

    public BoxItemCriteria() {
    }

    public BoxItemCriteria(Integer[] ids) {
        this.ids = ids;
    }

    public BoxItemCriteria(Integer subInventoryId, Integer unqualifiedStorageDay) {
        this.subInventoryId = subInventoryId;
        this.unqualifiedStorageDay = unqualifiedStorageDay;
    }

}
