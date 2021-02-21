package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.transferReason.TransferReason;
import com.deer.wms.base.system.model.transferReason.TransferReasonCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface TransferReasonMapper extends Mapper<TransferReason> {
    List<TransferReason> findList(TransferReasonCriteria transferReasonCriteria);
}