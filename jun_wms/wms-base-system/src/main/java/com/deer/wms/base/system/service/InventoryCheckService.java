package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.InventoryCheck;
import com.deer.wms.base.system.model.InventoryCheckCriteria;
import com.deer.wms.common.core.service.Service;


/**
 * Created by  on 2019/12/31.
 */
public interface InventoryCheckService extends Service<InventoryCheck, Integer> {
    InventoryCheck findByBillOutDetailAndType(InventoryCheckCriteria inventoryCheckCriteria);
}
