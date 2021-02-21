package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.RequestId;
import com.deer.wms.base.system.model.RequestIdCriteria;
import com.deer.wms.base.system.model.RequestIdDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface RequestIdMapper extends Mapper<RequestId> {

    List<RequestIdDto> selectList(RequestIdCriteria criteria);

    List<RequestIdDto> findProcessing(RequestIdCriteria requestIdCriteria);
}