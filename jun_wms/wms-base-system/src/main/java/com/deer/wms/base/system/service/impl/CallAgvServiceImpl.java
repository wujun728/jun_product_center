package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.CallAgvMapper;
import com.deer.wms.base.system.model.CallAgv;
import com.deer.wms.base.system.service.CallAgvService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2020/01/14.
 */
@Service
@Transactional
public class CallAgvServiceImpl extends AbstractService<CallAgv, Integer> implements CallAgvService {

    @Autowired
    private CallAgvMapper callAgvMapper;

    @Override
    public CallAgv findByStateOne(){
        return callAgvMapper.findByStateOne();
    }
}
