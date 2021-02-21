package com.deer.wms.base.system.service.task;

import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.task.PickTaskCriteria;
import com.deer.wms.base.system.model.task.PickTaskDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by guo on 2019/07/23.
 */
public interface PickTaskService extends Service<PickTask, Integer> {


    /**
     * 根据boxItemId查询PickTask详细信息， 便于修改托盘中的数量信息等等
     *
     * @param boxItemId
     * @return
     */
    public PickTask getPickTaskByBoxItemId(Long boxItemId);

    /**
     * 根据条件查询要出库的东西
     */
    List<PickTaskDto> findList(PickTaskCriteria criteria);

    List<PickTaskDto> findByState(PickTaskCriteria criteria);
}
