package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.SubinventoryTransferRecordMapper;
import com.deer.wms.base.system.model.SubinventoryTransferRecord;
import com.deer.wms.base.system.model.SubinventoryTransferRecordCriteria;
import com.deer.wms.base.system.model.SubinventoryTransferRecordDto;
import com.deer.wms.base.system.service.SubinventoryTransferRecordService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/10/31.
 */
@Service
public class SubinventoryTransferRecordServiceImpl extends AbstractService<SubinventoryTransferRecord, Integer> implements SubinventoryTransferRecordService {

    @Autowired
    private SubinventoryTransferRecordMapper subinventoryTransferRecordMapper;

    @Override
    public List<SubinventoryTransferRecordDto> findList(SubinventoryTransferRecordCriteria criteria){
        return subinventoryTransferRecordMapper.findList(criteria);
    }
}
