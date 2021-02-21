package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.SubInventory;
import com.deer.wms.base.system.model.SubInventoryCriteria;
import com.deer.wms.base.system.model.SubInventoryDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/11/09.
 */
public interface SubInventoryService extends Service<SubInventory, Integer> {
    void updateSubInventory(SubInventory subInventory);
    List<SubInventoryDto> findList(SubInventoryCriteria subInventoryCriteria);

    List<SubInventory> findIdNotEqualOne();
}
