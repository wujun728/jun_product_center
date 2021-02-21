package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.BillInRecordMapper;
import com.deer.wms.base.system.model.BillInRecord;
import com.deer.wms.base.system.model.BillInRecordCriteria;
import com.deer.wms.base.system.model.BillInRecordDto;
import com.deer.wms.base.system.service.BillInRecordService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/10/18.
 */
@Service
public class BillInRecordServiceImpl extends AbstractService<BillInRecord, Integer> implements BillInRecordService {

    @Autowired
    private BillInRecordMapper billInRecordMapper;

    @Override
    public List<BillInRecordDto> findListToEBS(BillInRecordCriteria billInRecordCriteria){
        return billInRecordMapper.findListToEBS(billInRecordCriteria);
    }

    @Override
    public List<BillInRecordDto> findCheckRecordFromEBS(BillInRecordCriteria billInRecordCriteria){
        return billInRecordMapper.findCheckRecordFromEBS(billInRecordCriteria);
    }

    @Override
    public List<BillInRecordDto> findList(BillInRecordCriteria billInRecordCriteria){
        return billInRecordMapper.findList(billInRecordCriteria);
    }
}
