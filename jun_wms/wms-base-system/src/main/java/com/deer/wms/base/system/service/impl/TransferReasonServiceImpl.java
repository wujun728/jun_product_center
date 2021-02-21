package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.TransferReasonMapper;
import com.deer.wms.base.system.model.transferReason.TransferReason;
import com.deer.wms.base.system.model.transferReason.TransferReasonCriteria;
import com.deer.wms.base.system.service.TransferReasonService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2020/04/01.
 */
@Service
@Transactional
public class TransferReasonServiceImpl extends AbstractService<TransferReason, Integer> implements TransferReasonService {

    @Autowired
    private TransferReasonMapper transferReasonMapper;

    @Override
    public List<TransferReason> findList(TransferReasonCriteria transferReasonCriteria){
        return transferReasonMapper.findList(transferReasonCriteria);
    }

}
