package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.BillInRecord;
import com.deer.wms.base.system.model.BillInRecordCriteria;
import com.deer.wms.base.system.model.BillInRecordDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface BillInRecordMapper extends Mapper<BillInRecord> {
    List<BillInRecordDto> findListToEBS(BillInRecordCriteria billInRecordCriteria);
    List<BillInRecordDto> findCheckRecordFromEBS(BillInRecordCriteria billInRecordCriteria);

    List<BillInRecordDto> findList(BillInRecordCriteria billInRecordCriteria);
}