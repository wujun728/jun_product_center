package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.InventoryCheckMapper;
import com.deer.wms.base.system.model.InventoryCheck;
import com.deer.wms.base.system.model.InventoryCheckCriteria;
import com.deer.wms.base.system.service.InventoryCheckService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/12/31.
 */
@Service
@Transactional
public class InventoryCheckServiceImpl extends AbstractService<InventoryCheck, Integer> implements InventoryCheckService {

    @Autowired
    private InventoryCheckMapper inventoryCheckMapper;

    @Override
    public InventoryCheck findByBillOutDetailAndType(InventoryCheckCriteria inventoryCheckCriteria){
        return inventoryCheckMapper.findByBillOutDetailAndType(inventoryCheckCriteria);
    }
}
