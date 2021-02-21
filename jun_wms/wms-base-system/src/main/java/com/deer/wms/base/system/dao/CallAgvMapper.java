package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.CallAgv;
import com.deer.wms.common.core.commonMapper.Mapper;

public interface CallAgvMapper extends Mapper<CallAgv> {
    CallAgv findByStateOne();
}