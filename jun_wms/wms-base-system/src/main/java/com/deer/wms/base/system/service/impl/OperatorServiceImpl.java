package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.OperatorMapper;
import com.deer.wms.base.system.model.Operator;
import com.deer.wms.base.system.model.OperatorCriteria;
import com.deer.wms.base.system.service.OperatorService;


import com.deer.wms.common.constant.UserConstants;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guo on 2019/08/27.
 */
@Service
public class OperatorServiceImpl extends AbstractService<Operator, Integer> implements OperatorService {

    @Autowired
    private OperatorMapper operatorMapper;

    public Operator findByCard(String card){
        return operatorMapper.findByCard(card);
    }

    public List<Operator> findList(OperatorCriteria operator){
        return operatorMapper.findList(operator);
    }

    @Override
    public String checkOperatorCard(String OperatorCard){
        int count = operatorMapper.checkOperatorCard(OperatorCard);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    public String checkOperatorCardUnique(OperatorCriteria operatorCriteria){
        Operator operator = operatorMapper.findByOperatorCard(operatorCriteria.getOperatorCard());
        if (StringUtils.isNotNull(operator) && operator.getOperatorId() != operatorCriteria.getOperatorId())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }
}
