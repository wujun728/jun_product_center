package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.BillInCheckRecord;
import com.deer.wms.base.system.model.BillInCheckRecordCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface BillInCheckRecordMapper extends Mapper<BillInCheckRecord> {
    BillInCheckRecord findByPoDistributionId(BillInCheckRecordCriteria billInCheckRecordCriteria);
    List<BillInCheckRecord> findByBillInDetailIds(BillInCheckRecordCriteria billInCheckRecordCriteria);

    BillInCheckRecord findByTransactionId(BillInCheckRecordCriteria billInCheckRecordCriteria);
}