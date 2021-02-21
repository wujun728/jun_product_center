package com.deer.wms.base.system.model.transferReason;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2020/04/01.
*/
public class TransferReasonCriteria extends QueryCriteria {
    private String transferReason;

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public TransferReasonCriteria() {
    }

    public TransferReasonCriteria(String transferReason) {
        this.transferReason = transferReason;
    }
}
