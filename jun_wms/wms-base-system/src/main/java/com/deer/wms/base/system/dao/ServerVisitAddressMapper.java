package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.ServerVisitAddress;
import com.deer.wms.base.system.model.ServerVisitAddressCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface ServerVisitAddressMapper extends Mapper<ServerVisitAddress> {
    List<ServerVisitAddress> findList(ServerVisitAddressCriteria criteria);
    ServerVisitAddress findAddressById(Integer id);
}