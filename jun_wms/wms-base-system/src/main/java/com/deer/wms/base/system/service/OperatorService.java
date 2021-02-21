package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.Operator;
import com.deer.wms.base.system.model.OperatorCriteria;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by guo on 2019/08/27.
 */
public interface OperatorService extends Service<Operator, Integer> {
    Operator findByCard(String card);

    List<Operator> findList(OperatorCriteria operator);

    /**
     * 校验卡号是否唯一
     *
     */
    public String checkOperatorCard(String OperatorCard);

    String checkOperatorCardUnique(OperatorCriteria operatorCriteria);
}
