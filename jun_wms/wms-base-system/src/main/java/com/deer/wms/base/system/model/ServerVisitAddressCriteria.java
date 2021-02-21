package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2019/09/26.
*/
public class ServerVisitAddressCriteria extends QueryCriteria {
    private Integer visitAddressId;

    public Integer getVisitAddressId() {
        return visitAddressId;
    }

    public void setVisitAddressId(Integer visitAddressId) {
        this.visitAddressId = visitAddressId;
    }
}
