package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.WorkerOrderIssueTime;
import com.deer.wms.common.core.service.Service;


/**
 * Created by  on 2019/09/28.
 */
public interface WorkerOrderIssueTimeService extends Service<WorkerOrderIssueTime, Integer> {

    /**
     * 根据id查询工单下发时间节点（此数据库作为数据维护，不可新增删除）
     */
}
