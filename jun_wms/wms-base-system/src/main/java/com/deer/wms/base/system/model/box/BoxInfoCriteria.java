package com.deer.wms.base.system.model.box;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by guo on 2019/06/24.
*/
public class BoxInfoCriteria extends QueryCriteria {
    private String boxCode;
    private String boxMemo;

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBoxMemo() {
        return boxMemo;
    }

    public void setBoxMemo(String boxMemo) {
        this.boxMemo = boxMemo;
    }
}
