package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.EbsBack;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.common.core.service.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by  on 2019/09/26.
 */
public interface ServerVisitAddressService extends Service<ServerVisitAddress, Integer> {
    /**
     * 查询所有数据
     * @param criteria
     * @return
     */
    List<ServerVisitAddress> findList(ServerVisitAddressCriteria criteria);

    /**
     * 根据id查询接口地址
     */
    ServerVisitAddress findAddressById(Integer id);

    EbsBack requestItemId(String itemCode,Integer organizationId);

    EbsBack requestServerCode(String requestId, String serviceName, String serviceOperation, Integer organizationId,List<Map<String, String>> lists);

    WebserviceResponse requestMesServer(String methodName,String code);
}
