package com.deer.wms.base.system.model;

public class BaseQueryParams {
    //库存组织
    private Integer organizationId;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public BaseQueryParams() {
    }

    public BaseQueryParams(Integer organizationId) {
        this.organizationId = organizationId;
    }
}