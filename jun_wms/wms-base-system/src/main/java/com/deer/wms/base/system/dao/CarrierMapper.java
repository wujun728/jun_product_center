package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.Carrier;
import com.deer.wms.common.core.commonMapper.Mapper;

public interface CarrierMapper extends Mapper<Carrier> {
    Carrier findFirstCarrier();

    Carrier inValidate(String carrierCode);
}