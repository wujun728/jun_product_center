package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2019/12/31.
*/
public class InventoryCheckCriteria extends QueryCriteria {
    private Integer billOutDetailId;
    private Integer type;

    public Integer getBillOutDetailId() {
        return billOutDetailId;
    }

    public void setBillOutDetailId(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public InventoryCheckCriteria() {
    }

    public InventoryCheckCriteria(Integer billOutDetailId, Integer type) {
        this.billOutDetailId = billOutDetailId;
        this.type = type;
    }
}
