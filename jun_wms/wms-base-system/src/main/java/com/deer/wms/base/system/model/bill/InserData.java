package com.deer.wms.base.system.model.bill;

import java.util.List;

public class InserData {
    private BillInMaster billInMaster;
    private List<BillInDetail> billInDetailList;



    private BillOutMaster billOutMaster;
    private List<BillOutDetail> billOutDetailList;

    public BillOutMaster getBillOutMaster() {
        return billOutMaster;
    }

    public void setBillOutMaster(BillOutMaster billOutMaster) {
        this.billOutMaster = billOutMaster;
    }

    public List<BillOutDetail> getBillOutDetailList() {
        return billOutDetailList;
    }

    public void setBillOutDetailList(List<BillOutDetail> billOutDetailList) {
        this.billOutDetailList = billOutDetailList;
    }

    public BillInMaster getBillInMaster() {
        return billInMaster;
    }

    public void setBillInMaster(BillInMaster billInMaster) {
        this.billInMaster = billInMaster;
    }

    public List<BillInDetail> getBillInDetailList() {
        return billInDetailList;
    }

    public void setBillInDetailList(List<BillInDetail> billInDetailList) {
        this.billInDetailList = billInDetailList;
    }
}
