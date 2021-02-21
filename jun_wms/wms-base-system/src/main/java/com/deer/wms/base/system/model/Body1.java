package com.deer.wms.base.system.model;

import java.util.List;
import java.util.Map;

public class Body1 {
    private SystemParams systemParams;
    private BaseQueryParams1 baseQueryParams;
    private List<Map<String,String>> businessData;

    public SystemParams getSystemParams() {
        return systemParams;
    }

    public void setSystemParams(SystemParams systemParams) {
        this.systemParams = systemParams;
    }

    public BaseQueryParams1 getBaseQueryParams() {
        return baseQueryParams;
    }

    public void setBaseQueryParams(BaseQueryParams1 baseQueryParams) {
        this.baseQueryParams = baseQueryParams;
    }

    public List<Map<String, String>> getBusinessData() {
        return businessData;
    }

    public void setBusinessData(List<Map<String, String>> businessData) {
        this.businessData = businessData;
    }

    public Body1() {
    }

    public Body1(SystemParams systemParams, BaseQueryParams1 baseQueryParams, List<Map<String, String>> businessData) {
        this.systemParams = systemParams;
        this.baseQueryParams = baseQueryParams;
        this.businessData = businessData;
    }
}