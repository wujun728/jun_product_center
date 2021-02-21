package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2019/11/09.
*/
public class SubInventoryCriteria extends QueryCriteria {
    private String subInventoryName;
    private String subInventoryCode;

    public String getSubInventoryName() {
        return subInventoryName;
    }

    public void setSubInventoryName(String subInventoryName) {
        this.subInventoryName = subInventoryName;
    }

    public String getSubInventoryCode() {
        return subInventoryCode;
    }

    public void setSubInventoryCode(String subInventoryCode) {
        this.subInventoryCode = subInventoryCode;
    }
}
