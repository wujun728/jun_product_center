package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.CombineBoxRecordMapper;
import com.deer.wms.base.system.model.CombineBoxRecord;
import com.deer.wms.base.system.service.CombineBoxRecordService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/11/04.
 */
@Service
public class CombineBoxRecordServiceImpl extends AbstractService<CombineBoxRecord, Integer> implements CombineBoxRecordService {

    @Autowired
    private CombineBoxRecordMapper combineBoxRecordMapper;

}
