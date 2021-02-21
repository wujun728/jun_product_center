package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.BillInCheckRecordMapper;
import com.deer.wms.base.system.model.BillInCheckRecord;
import com.deer.wms.base.system.model.BillInCheckRecordCriteria;
import com.deer.wms.base.system.service.BillInCheckRecordService;


import com.deer.wms.common.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/12/05.
 */
@Service
@Transactional
public class BillInCheckRecordServiceImpl extends AbstractService<BillInCheckRecord, Integer> implements BillInCheckRecordService {

    @Autowired
    private BillInCheckRecordMapper billInCheckRecordMapper;

    @Override
    public BillInCheckRecord findByPoDistributionId(BillInCheckRecordCriteria billInCheckRecordCriteria){
        return billInCheckRecordMapper.findByPoDistributionId(billInCheckRecordCriteria);
    }

    @Override
    public List<BillInCheckRecord> findByBillInDetailIds(BillInCheckRecordCriteria billInCheckRecordCriteria){
        return billInCheckRecordMapper.findByBillInDetailIds(billInCheckRecordCriteria);
    }

    @Override
    public BillInCheckRecord findByTransactionId(BillInCheckRecordCriteria billInCheckRecordCriteria){
        return billInCheckRecordMapper.findByTransactionId(billInCheckRecordCriteria);
    }
}
