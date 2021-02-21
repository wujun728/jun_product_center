package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2019/12/26.
*/
public class RequestIdCriteria extends QueryCriteria {
    private Integer type;
    private Integer state;
    private Integer requestId;
    private Integer id;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public RequestIdCriteria(Integer state) {
        this.state = state;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RequestIdCriteria() {
    }

    public RequestIdCriteria(Integer requestId, Integer id) {
        this.requestId = requestId;
        this.id = id;
    }
}
