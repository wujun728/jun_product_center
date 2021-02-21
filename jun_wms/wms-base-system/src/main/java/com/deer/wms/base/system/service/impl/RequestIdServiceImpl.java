package com.deer.wms.base.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.dao.RequestIdMapper;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.service.RequestIdService;


import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.framework.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/12/26.
 */
@Service
@Transactional
public class RequestIdServiceImpl extends AbstractService<RequestId, Long> implements RequestIdService {

    @Autowired
    private RequestIdMapper requestIdMapper;

    @Override
    public List<RequestIdDto> selectList(RequestIdCriteria requestIdCriteria){
        return requestIdMapper.selectList(requestIdCriteria);
    }

    @Override
    public  List<RequestIdDto> findProcessing(RequestIdCriteria criteria){
        return requestIdMapper.findProcessing(criteria);
    }

    /**
     * 子库转移循环处理获得的数据
     */
    @Override
    public void subInventoryTransfer(EbsBack entityStr, RequestIdAuto requestIdAuto) throws Exception{
        JSONArray jsonArrays = JSONArray.parseArray(entityStr.getRows());
        for (int i = 0; i < jsonArrays.size(); i++) {
            com.alibaba.fastjson.JSONObject jsonObject = jsonArrays.getJSONObject(i);
            // 处理状态
            String processStatus = MyUtils.backString(jsonObject.get("processStatus"));
            String lastUpdateDate = MyUtils.backString(jsonObject.get("lastUpdateDate"));
            String creationDate = MyUtils.backString(jsonObject.get("creationDate"));
            String errorMsg = MyUtils.backString(jsonObject.get("errorMsg"));
            String lastUpdatedBy = MyUtils.backString(jsonObject.get("lastUpdatedBy"));
            String createdBy = MyUtils.backString(jsonObject.get("createdBy"));
            Integer itemId = MyUtils.backDouble(jsonObject.get("itemId"));
            Integer ebsbackQuantity = MyUtils.backDouble(jsonObject.get("quantity"));
            String subInventorys = MyUtils.backString(jsonObject.get("subInventory"));
            Integer locatorId = MyUtils.backInteger(jsonObject.get("locatorId"));
            Integer organizationId = MyUtils.backInteger(jsonObject.get("organizationId"));
            String transDate = MyUtils.backString(jsonObject.get("transDate"));
            String transUom = MyUtils.backString(jsonObject.get("transUom"));
            Integer transTypeId = MyUtils.backInteger(jsonObject.get("transTypeId"));
            String lotNumber = MyUtils.backString(jsonObject.get("transLotNumber"));
            Integer sourceHeaderId = MyUtils.backInteger(jsonObject.get("sourceHeaderId"));
            Integer sourceLineId = MyUtils.backInteger(jsonObject.get("sourceLineId"));
            String transSubInventory = MyUtils.backString(jsonObject.get("transSubInventory"));
            Integer transLocatorId = MyUtils.backInteger(jsonObject.get("transLocatorId"));
            Integer id = MyUtils.backInteger(jsonObject.get("id"));
            RequestId requestId = new RequestId(requestIdAuto.getAutoId(),processStatus,lastUpdateDate,lastUpdatedBy,
                    creationDate,createdBy,itemId,ebsbackQuantity,lotNumber,id,transDate,errorMsg,subInventorys,locatorId,
                    organizationId, TaskTypeConstant.TRANSFER,transTypeId+"",transUom,transSubInventory,transLocatorId,
                    sourceHeaderId,sourceLineId) ;
            if(processStatus.equals("SUCCESS")){
                requestId.setState(TaskTypeConstant.SUCCESS);
            }
            else if(processStatus.equals("PROCESSING")){
                requestId.setState(TaskTypeConstant.MANAGING);
            }
            else{
                requestId.setState(TaskTypeConstant.FAIL_WAIT_MANAGE);
            }
            //保存请求EBS后的数据
            save(requestId);
        }
    }
}
