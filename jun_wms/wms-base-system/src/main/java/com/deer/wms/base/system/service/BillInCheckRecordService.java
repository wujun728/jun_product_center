package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.BillInCheckRecord;
import com.deer.wms.base.system.model.BillInCheckRecordCriteria;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/12/05.
 */
public interface BillInCheckRecordService extends Service<BillInCheckRecord, Integer> {
    //根据分配行查询相关结果
    BillInCheckRecord findByPoDistributionId(BillInCheckRecordCriteria billInCheckRecordCriteria);
    //根据条件查询结果
    List<BillInCheckRecord> findByBillInDetailIds(BillInCheckRecordCriteria billInCheckRecordCriteria);
    //根据检验Id查询结果
    BillInCheckRecord findByTransactionId(BillInCheckRecordCriteria billInCheckRecordCriteria);
}
