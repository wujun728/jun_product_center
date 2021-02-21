package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

import java.util.List;

/**
* Created by  on 2019/10/31.
*/
public class SubinventoryTransferRecordCriteria extends QueryCriteria {
    private Integer[] ids;
    private String loginPersonCardNo;
    private String boxCode;
    private String itemCode;
    private Integer toSubInventoryId;
    private String transferReason;
    private String postpone;

    public String getPostpone() {
        return postpone;
    }

    public void setPostpone(String postpone) {
        this.postpone = postpone;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getLoginPersonCardNo() {
        return loginPersonCardNo;
    }

    public void setLoginPersonCardNo(String loginPersonCardNo) {
        this.loginPersonCardNo = loginPersonCardNo;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getToSubInventoryId() {
        return toSubInventoryId;
    }

    public void setToSubInventoryId(Integer toSubInventoryId) {
        this.toSubInventoryId = toSubInventoryId;
    }
}
