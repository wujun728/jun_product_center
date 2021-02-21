package com.deer.wms.base.system.model.item;

public class BaseQueryParams2 {
    //库存组织
    private Integer organizationId;

    private String lastUpdateDateF;
    private String lastUpdateDateT;

    private Integer startPosition;
    private Integer rowsCnt;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getLastUpdateDateF() {
        return lastUpdateDateF;
    }

    public void setLastUpdateDateF(String lastUpdateDateF) {
        this.lastUpdateDateF = lastUpdateDateF;
    }

    public String getLastUpdateDateT() {
        return lastUpdateDateT;
    }

    public void setLastUpdateDateT(String lastUpdateDateT) {
        this.lastUpdateDateT = lastUpdateDateT;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getRowsCnt() {
        return rowsCnt;
    }

    public void setRowsCnt(Integer rowsCnt) {
        this.rowsCnt = rowsCnt;
    }

    public BaseQueryParams2() {
    }

    public BaseQueryParams2(Integer organizationId, String lastUpdateDateF, String lastUpdateDateT, Integer startPosition, Integer rowsCnt) {
        this.organizationId = organizationId;
        this.lastUpdateDateF = lastUpdateDateF;
        this.lastUpdateDateT = lastUpdateDateT;
        this.startPosition = startPosition;
        this.rowsCnt = rowsCnt;
    }
}