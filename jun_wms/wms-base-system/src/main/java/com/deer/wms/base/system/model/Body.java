package com.deer.wms.base.system.model;

import java.util.List;
import java.util.Map;

public class Body{
    private SystemParams systemParams;
    private BaseQueryParams baseQueryParams;
    private List<Map<String,String>> businessData;

    public SystemParams getSystemParams() {
        return systemParams;
    }

    public void setSystemParams(SystemParams systemParams) {
        this.systemParams = systemParams;
    }

    public BaseQueryParams getBaseQueryParams() {
        return baseQueryParams;
    }

    public void setBaseQueryParams(BaseQueryParams baseQueryParams) {
        this.baseQueryParams = baseQueryParams;
    }

    public List<Map<String, String>> getBusinessData() {
        return businessData;
    }

    public void setBusinessData(List<Map<String, String>> businessData) {
        this.businessData = businessData;
    }

    public Body() {
    }

    public Body(SystemParams systemParams, BaseQueryParams baseQueryParams, List<Map<String, String>> businessData) {
        this.systemParams = systemParams;
        this.baseQueryParams = baseQueryParams;
        this.businessData = businessData;
    }
}