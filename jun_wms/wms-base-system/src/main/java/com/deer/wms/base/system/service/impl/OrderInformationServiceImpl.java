package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.OrderInformationMapper;
import com.deer.wms.base.system.model.OrderInformation;
import com.deer.wms.base.system.service.OrderInformationService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/12/07.
 */
@Service
@Transactional
public class OrderInformationServiceImpl extends AbstractService<OrderInformation, Integer> implements OrderInformationService {

    @Autowired
    private OrderInformationMapper orderInformationMapper;

}
