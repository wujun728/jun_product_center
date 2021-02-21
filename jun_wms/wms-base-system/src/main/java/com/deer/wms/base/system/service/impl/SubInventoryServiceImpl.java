package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.SubInventoryMapper;
import com.deer.wms.base.system.model.SubInventory;
import com.deer.wms.base.system.model.SubInventoryCriteria;
import com.deer.wms.base.system.model.SubInventoryDto;
import com.deer.wms.base.system.service.SubInventoryService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/11/09.
 */
@Service
public class SubInventoryServiceImpl extends AbstractService<SubInventory, Integer> implements SubInventoryService {

    @Autowired
    private SubInventoryMapper subInventoryMapper;

    @Override
    public void updateSubInventory(SubInventory subInventory){
        subInventoryMapper.updateSubInventory(subInventory);
    }

    @Override
    public List<SubInventoryDto> findList(SubInventoryCriteria subInventoryCriteria){
        return subInventoryMapper.findList(subInventoryCriteria);
    }

    @Override
    public List<SubInventory> findIdNotEqualOne(){
        return subInventoryMapper.findIdNotEqualOne();
    }
}










