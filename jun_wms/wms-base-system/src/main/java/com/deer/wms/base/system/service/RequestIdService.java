package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.*;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/12/26.
 */
public interface RequestIdService extends Service<RequestId, Long> {
    List<RequestIdDto> selectList(RequestIdCriteria criteria);

    List<RequestIdDto> findProcessing(RequestIdCriteria requestIdCriteria);

    void subInventoryTransfer(EbsBack entityStr, RequestIdAuto requestIdAuto) throws Exception;
}
