package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.WorkerOrderIssueTimeMapper;
import com.deer.wms.base.system.model.WorkerOrderIssueTime;
import com.deer.wms.base.system.service.WorkerOrderIssueTimeService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by  on 2019/09/28.
 */
@Service
public class WorkerOrderIssueTimeServiceImpl extends AbstractService<WorkerOrderIssueTime, Integer> implements WorkerOrderIssueTimeService {

    @Autowired
    private WorkerOrderIssueTimeMapper workerOrderIssueTimeMapper;

}
