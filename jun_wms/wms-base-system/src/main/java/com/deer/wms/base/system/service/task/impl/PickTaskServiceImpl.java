package com.deer.wms.base.system.service.task.impl;

import com.deer.wms.base.system.dao.task.PickTaskMapper;
import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.task.PickTaskCriteria;
import com.deer.wms.base.system.model.task.PickTaskDto;
import com.deer.wms.base.system.service.task.PickTaskService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guo on 2019/07/23.
 */
@Service
@Transactional
public class PickTaskServiceImpl extends AbstractService<PickTask, Integer> implements PickTaskService {

    @Autowired
    private PickTaskMapper pickTaskMapper;

    /**
     * 根据boxItemId查询PickTask详细信息， 便于修改托盘中的数量信息等等
     *
     * @param boxItemId
     * @return
     */
    @Override
    public PickTask getPickTaskByBoxItemId(Long boxItemId) {
        return pickTaskMapper.getPickTaskByBoxItemId(boxItemId);
    }

    @Override
    public List<PickTaskDto> findList(PickTaskCriteria criteria) {
        return pickTaskMapper.findList(criteria);
    }

    public List<PickTaskDto> findByState(PickTaskCriteria criteria){
        return pickTaskMapper.findByState(criteria);
    }
}
