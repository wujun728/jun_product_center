package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

/**
* Created by  on 2019/11/04.
*/
public class CombineBoxRecordCriteria extends QueryCriteria {
    private String boxIds;
    private String loginPersonCardNo;
    private String boxCode;
    private Integer quantity;

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

    public String getBoxIds() {
        return boxIds;
    }

    public void setBoxIds(String boxIds) {
        this.boxIds = boxIds;
    }

    public String getLoginPersonCardNo() {
        return loginPersonCardNo;
    }

    public void setLoginPersonCardNo(String loginPersonCardNo) {
        this.loginPersonCardNo = loginPersonCardNo;
    }
}
