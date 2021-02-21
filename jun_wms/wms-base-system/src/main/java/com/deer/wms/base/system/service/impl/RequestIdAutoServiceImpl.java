package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.RequestIdAutoMapper;
import com.deer.wms.base.system.model.RequestIdAuto;
import com.deer.wms.base.system.service.RequestIdAutoService;


import com.deer.wms.common.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/12/28.
 */
@Service
@Transactional
public class RequestIdAutoServiceImpl extends AbstractService<RequestIdAuto, Integer> implements RequestIdAutoService {

    @Autowired
    private RequestIdAutoMapper requestIdAutoMapper;

    @Override
    public RequestIdAuto backAutoId(@Param("memo") String memo){
        RequestIdAuto requestIdAuto = new RequestIdAuto(memo);
        save(requestIdAuto);
        return requestIdAuto;
    }
}
