package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.RequestIdAuto;
import com.deer.wms.common.core.service.Service;


/**
 * Created by  on 2019/12/28.
 */
public interface RequestIdAutoService extends Service<RequestIdAuto, Integer> {
    RequestIdAuto backAutoId(String memo);
}
