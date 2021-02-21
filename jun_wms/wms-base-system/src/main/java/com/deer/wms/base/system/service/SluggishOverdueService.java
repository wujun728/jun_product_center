package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.SluggishOverdue;
import com.deer.wms.base.system.model.SluggishOverdueCriteria;
import com.deer.wms.base.system.model.SluggishOverdueDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/11/21.
 */
public interface SluggishOverdueService extends Service<SluggishOverdue, Integer> {
    //根据条件查询所有的
    List<SluggishOverdueDto> findList(SluggishOverdueCriteria sluggishOverdueCriteria);

    List<SluggishOverdue> findSluggishByParam(SluggishOverdueCriteria sluggishOverdueCriteria);
}
