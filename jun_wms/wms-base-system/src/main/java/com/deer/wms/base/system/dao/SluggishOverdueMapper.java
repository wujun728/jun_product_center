package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.SluggishOverdue;
import com.deer.wms.base.system.model.SluggishOverdueCriteria;
import com.deer.wms.base.system.model.SluggishOverdueDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface SluggishOverdueMapper extends Mapper<SluggishOverdue> {
    List<SluggishOverdueDto> findList(SluggishOverdueCriteria sluggishOverdueCriteria);

    List<SluggishOverdue> findSluggishByParam(SluggishOverdueCriteria sluggishOverdueCriteria);
}