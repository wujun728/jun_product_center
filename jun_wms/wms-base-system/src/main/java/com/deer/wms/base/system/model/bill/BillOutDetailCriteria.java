package com.deer.wms.base.system.model.bill;

import com.deer.wms.common.core.service.QueryCriteria;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */

public class BillOutDetailCriteria extends QueryCriteria
{
    private Integer State;

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
