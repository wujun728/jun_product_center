package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.SubInventory;
import com.deer.wms.base.system.model.SubInventoryCriteria;
import com.deer.wms.base.system.model.SubInventoryDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface SubInventoryMapper extends Mapper<SubInventory> {
    void updateSubInventory(SubInventory subInventory);
    List<SubInventoryDto> findList(SubInventoryCriteria subInventoryCriteria);
    List<SubInventory> findIdNotEqualOne();

}