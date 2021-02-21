package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by guo on 2019/08/27.
*/
public class OperatorCriteria extends QueryCriteria {
    private String card;

    private String operatorCard;
    private String empNo;
    private Integer operatorId;

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorCard() {
        return operatorCard;
    }

    public void setOperatorCard(String operatorCard) {
        this.operatorCard = operatorCard;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
