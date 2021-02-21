package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.BillInRecord;
import com.deer.wms.base.system.model.BillInRecordCriteria;
import com.deer.wms.base.system.model.BillInRecordDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/10/18.
 */
public interface BillInRecordService extends Service<BillInRecord, Integer> {
    /**  根据条件查询未回传EBS的数据  */
    List<BillInRecordDto> findListToEBS(BillInRecordCriteria billInRecordCriteria);

    List<BillInRecordDto> findCheckRecordFromEBS(BillInRecordCriteria billInRecordCriteria);

    /** 根据条件查询入库记录 */
    List<BillInRecordDto> findList(BillInRecordCriteria billInRecordCriteria);
}
