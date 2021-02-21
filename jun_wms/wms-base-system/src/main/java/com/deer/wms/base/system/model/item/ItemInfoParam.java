package com.deer.wms.base.system.model.item;

import com.deer.wms.common.core.service.QueryCriteria;

public class ItemInfoParam extends QueryCriteria {
    private Long itemTypeId;
    private String itemCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }
}
