package com.deer.wms.base.system.model.item;

import com.deer.wms.base.system.model.SystemParams;

import java.util.List;
import java.util.Map;

public class Body2 {
    private SystemParams systemParams;
    private BaseQueryParams2 baseQueryParams;
    private List<Map<String,String>> businessData;

    public SystemParams getSystemParams() {
        return systemParams;
    }

    public void setSystemParams(SystemParams systemParams) {
        this.systemParams = systemParams;
    }

    public BaseQueryParams2 getBaseQueryParams() {
        return baseQueryParams;
    }

    public void setBaseQueryParams(BaseQueryParams2 baseQueryParams) {
        this.baseQueryParams = baseQueryParams;
    }

    public List<Map<String, String>> getBusinessData() {
        return businessData;
    }

    public void setBusinessData(List<Map<String, String>> businessData) {
        this.businessData = businessData;
    }

    public Body2() {
    }

    public Body2(SystemParams systemParams, BaseQueryParams2 baseQueryParams, List<Map<String, String>> businessData) {
        this.systemParams = systemParams;
        this.baseQueryParams = baseQueryParams;
        this.businessData = businessData;
    }
}