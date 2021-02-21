package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.SluggishOverdueMapper;
import com.deer.wms.base.system.model.SluggishOverdue;
import com.deer.wms.base.system.model.SluggishOverdueCriteria;
import com.deer.wms.base.system.model.SluggishOverdueDto;
import com.deer.wms.base.system.service.SluggishOverdueService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/11/21.
 */
@Service
public class SluggishOverdueServiceImpl extends AbstractService<SluggishOverdue, Integer> implements SluggishOverdueService {

    @Autowired
    private SluggishOverdueMapper sluggishOverdueMapper;

    @Override
    public List<SluggishOverdueDto> findList(SluggishOverdueCriteria sluggishOverdueCriteria){
        return sluggishOverdueMapper.findList(sluggishOverdueCriteria);
    }

    @Override
    public List<SluggishOverdue> findSluggishByParam(SluggishOverdueCriteria sluggishOverdueCriteria){
        return sluggishOverdueMapper.findSluggishByParam(sluggishOverdueCriteria);
    }
}
