package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.SubinventoryTransferRecord;
import com.deer.wms.base.system.model.SubinventoryTransferRecordCriteria;
import com.deer.wms.base.system.model.SubinventoryTransferRecordDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/10/31.
 */
public interface SubinventoryTransferRecordService extends Service<SubinventoryTransferRecord, Integer> {
    //根据条件查询转库信息
    List<SubinventoryTransferRecordDto> findList(SubinventoryTransferRecordCriteria criteria);
}
