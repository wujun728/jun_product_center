package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.transferReason.TransferReason;
import com.deer.wms.base.system.model.transferReason.TransferReasonCriteria;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2020/04/01.
 */
public interface TransferReasonService extends Service<TransferReason, Integer> {
    List<TransferReason> findList(TransferReasonCriteria transferReasonCriteria);
}
