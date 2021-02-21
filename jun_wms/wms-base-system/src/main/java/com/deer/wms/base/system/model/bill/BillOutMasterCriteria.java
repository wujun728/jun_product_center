package com.deer.wms.base.system.model.bill;

import com.deer.wms.common.core.service.QueryCriteria;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */

public class BillOutMasterCriteria extends QueryCriteria
{
    private String billNo;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BillOutMasterCriteria() {
    }

    public BillOutMasterCriteria(String billNo) {
        this.billNo = billNo;
    }
}
