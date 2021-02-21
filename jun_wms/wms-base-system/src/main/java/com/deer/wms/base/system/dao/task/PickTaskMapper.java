package com.deer.wms.base.system.dao.task;

import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.task.PickTaskCriteria;
import com.deer.wms.base.system.model.task.PickTaskDto;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface PickTaskMapper extends Mapper<PickTask> {


    /**
     * 根据boxItemId查询PickTask详细信息， 便于修改托盘中的数量信息等等
     *
     * @param boxItemId
     * @return
     */
    public PickTask getPickTaskByBoxItemId(Long boxItemId);

    List<PickTaskDto> findList(PickTaskCriteria criteria);

    List<PickTaskDto> findByState(PickTaskCriteria criteria);
}