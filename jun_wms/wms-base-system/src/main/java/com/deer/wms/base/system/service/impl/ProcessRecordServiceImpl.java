package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.ProcessRecordMapper;
import com.deer.wms.base.system.model.ProcessRecord;
import com.deer.wms.base.system.service.ProcessRecordService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/12/03.
 */
@Service
@Transactional
public class ProcessRecordServiceImpl extends AbstractService<ProcessRecord, Integer> implements ProcessRecordService {

    @Autowired
    private ProcessRecordMapper processRecordMapper;

}
