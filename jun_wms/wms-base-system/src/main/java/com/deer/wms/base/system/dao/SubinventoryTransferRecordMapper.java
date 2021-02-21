package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.SubinventoryTransferRecord;
import com.deer.wms.base.system.model.SubinventoryTransferRecordCriteria;
import com.deer.wms.base.system.model.SubinventoryTransferRecordDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface SubinventoryTransferRecordMapper extends Mapper<SubinventoryTransferRecord> {
    List<SubinventoryTransferRecordDto> findList(SubinventoryTransferRecordCriteria criteria);
}