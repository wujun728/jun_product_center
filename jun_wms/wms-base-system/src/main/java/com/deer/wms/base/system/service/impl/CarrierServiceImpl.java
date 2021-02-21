package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.CarrierMapper;
import com.deer.wms.base.system.model.Carrier;
import com.deer.wms.base.system.service.CarrierService;


import com.deer.wms.common.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/10/11.
 */
@Service
public class CarrierServiceImpl extends AbstractService<Carrier, Integer> implements CarrierService {

    @Autowired
    private CarrierMapper carrierMapper;

    @Override
    public Carrier findFirstCarrier(){
        return carrierMapper.findFirstCarrier();
    }

    @Override
    public Carrier inValidate(@Param("carrierCode") String carrierCode){
        return carrierMapper.inValidate(carrierCode);
    }
}
