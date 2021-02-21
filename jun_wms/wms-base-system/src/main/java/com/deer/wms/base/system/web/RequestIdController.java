package com.deer.wms.base.system.web;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.service.RequestIdAutoService;
import com.deer.wms.base.system.service.RequestIdService;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.framework.util.MyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by  on 2019/12/26.
*/
@Controller
@RequestMapping("/requestId")
public class RequestIdController  extends BaseController{

    private String prefix = "manage/callPortRecord";

    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("requestId:view")
    @GetMapping()
    public String requestId()
    {
        return prefix + "/requestId";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
    RequestId requestId = requestIdService.findById(id);
        mmap.put("requestId", requestId);
        return prefix + "/edit";
    }

    /**
    * 新增
    */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    @PostMapping
    @ResponseBody
    public Result add(@RequestBody RequestId requestId) {
        requestIdService.save(requestId);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Long id) {
        requestIdService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody RequestId requestId) {
        requestIdService.update(requestId);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Long id) {
        RequestId requestId = requestIdService.findById(id);
        return ResultGenerator.genSuccessResult(requestId);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(RequestIdCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<RequestId> list = requestIdService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(RequestIdCriteria criteria) {
        startPage();
        List<RequestIdDto> list = requestIdService.selectList(criteria);
        return getDataTable(list);
    }

    @RequiresPermissions("requestId:refresh")
    @GetMapping("/refresh")
    @ResponseBody
    public AjaxResult refresh()
    {
        List<RequestIdDto> requestIdDtos = requestIdService.findProcessing(new RequestIdCriteria(TaskTypeConstant.MANAGING));
        if(requestIdDtos.size()>0){
            List<Map<String, String>> wipOuts = new ArrayList<>();
            List<Map<String, String>> accountAliasOuts = new ArrayList<>();
            List<Map<String, String>> subInventoryTranfers = new ArrayList<>();

            for(RequestIdDto requestIdDto : requestIdDtos){
                Map<String, String> map = new HashMap<>();
                if(requestIdDto.getType().equals(TaskTypeConstant.OUT)){
                    map.put("requestId",requestIdDto.getRequestId()+"");
                    wipOuts.add(map);
                }else if(requestIdDto.getType().equals(TaskTypeConstant.ACCOUNT_ALIAS)){
                    map.put("requestId",requestIdDto.getRequestId()+"");
                    accountAliasOuts.add(map);
                }else if(requestIdDto.getType().equals(TaskTypeConstant.TRANSFER)){
                    map.put("requestId",requestIdDto.getRequestId()+"");
                    subInventoryTranfers.add(map);
                }
            }
            //查询工单发料数据处理情况并修改状态
            if(wipOuts.size()>0) {
                selectEBSSStatus("3", TaskTypeConstant.GET_WIPTRXS_INTF, TaskTypeConstant.QUERY, null, wipOuts);
            }
            if(accountAliasOuts.size()>0) {
                selectEBSSStatus("4", TaskTypeConstant.GET_OTHERS_INTF, TaskTypeConstant.QUERY, null, accountAliasOuts);
            }
            if(subInventoryTranfers.size()>0) {
                selectEBSSStatus("5", TaskTypeConstant.GET_SUBINV_INTF, TaskTypeConstant.QUERY, null, subInventoryTranfers);
            }
        }else{
            return toAjax(true).success("暂无EBS正在处理中数据");
        }
        return toAjax(true).success("刷新成功！");
    }

    private void selectEBSSStatus(String requestAutoId,String serviceName,String operate,Integer organizationId,List<Map<String,String>> list){
        EbsBack entityStr = serverVisitAddressService.requestServerCode(requestAutoId,serviceName,operate,organizationId,list);
        //判断是否请求到参数
        if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
            JSONArray jsonArrays = JSONArray.parseArray(entityStr.getRows());
            for (int i = 0; i < jsonArrays.size(); i++) {
                com.alibaba.fastjson.JSONObject jsonObject = jsonArrays.getJSONObject(i);
                // 处理状态
                Integer requestId = MyUtils.backInteger(jsonObject.get("requestId"));
                String processStatus = MyUtils.backString(jsonObject.get("processStatus"));
                String errorMsg = MyUtils.backString(jsonObject.get("errorMsg"));
                Integer id = MyUtils.backInteger(jsonObject.get("id"));
                RequestIdDto requestIdDto = requestIdService.findProcessing(new RequestIdCriteria(requestId,id)).get(0);
                requestIdDto.setProcessStatus(processStatus);
                requestIdDto.setErrorMsg(errorMsg);
                if(processStatus.equals("SUCCESS")){
                    requestIdDto.setState(TaskTypeConstant.SUCCESS);
                }else if(processStatus.equals("ERROR")){
                    requestIdDto.setState(TaskTypeConstant.FAIL_WAIT_MANAGE);
                }
                requestIdService.update(requestIdDto);
            }
        }
    }

    @RequiresPermissions("requestId:sendEbs")
    @GetMapping("/sendToEbs")
    @ResponseBody
    public AjaxResult sendToEbs()
    {
        List<RequestIdDto> requestIdDtos = requestIdService.findProcessing(new RequestIdCriteria(TaskTypeConstant.FAIL_WAIT_MANAGE));
        if(requestIdDtos.size()>0){
            //创建交货参数对象
            List<Map<String, String>> deliveryParams = new ArrayList<>();
            RequestIdAuto deliveryRequestIdAuto = requestIdAutoService.backAutoId("WMS交货数据写入EBS接口");
            List<RequestId> deliveryRequestIds = new ArrayList<>();
            //创建工单出库对象
            List<Map<String, String>> wipOutParams = new ArrayList<>();
            RequestIdAuto wipOutRequestIdAuto = requestIdAutoService.backAutoId("WMS工单发料写入EBS接口");
            List<RequestId> wipOutRequestIds = new ArrayList<>();
            //创建账户别名出库对象
            List<Map<String, String>> accountAliasOuts = new ArrayList<>();
            RequestIdAuto accountAliasOutsRequestIdAuto = requestIdAutoService.backAutoId("WMS子库存转移写入EBS接口");
            List<RequestId> accountAliasOutsRequestIds = new ArrayList<>();
            //创建子库转移对象
            List<Map<String, String>> subInventoryTranfers = new ArrayList<>();
            RequestIdAuto subInventoryTranfersRequestIdAuto = requestIdAutoService.backAutoId("WMS账户别名发料写入EBS接口");
            List<RequestId> subInventoryTranfersRequestIds = new ArrayList<>();

            try {
                for (RequestIdDto requestIdDto : requestIdDtos) {
                    Map<String, String> map = new HashMap<>();
                    //交货
                    if (TaskTypeConstant.DELIVERY.equals(requestIdDto.getType())) {
                        deliveryParams.add(MyUtils.delivery(MyUtils.getNinetySecondsAgo(), requestIdDto.getTransactionId().toString(), requestIdDto.getOrganizationId().toString(),
                                requestIdDto.getSubInventory(), requestIdDto.getLocatorId() == null ? null : requestIdDto.getLocatorId().toString(),
                                requestIdDto.getLotNumber(), requestIdDto.getQuantity().toString(), requestIdDto.getShipmentNum()));
                        //修改交货数据并写入
                        requestIdDto.setAutoGrowingId(null);
                        requestIdDto.setRequestId(deliveryRequestIdAuto.getAutoId());
                        requestIdDto.setTransactionDate(DateUtils.getTime());
                        requestIdDto.setErrorMsg("WMS交货数据写入EBS接口失败");
                        deliveryRequestIds.add(requestIdDto);
                    }
                    //工单出库
                    else if (TaskTypeConstant.OUT.equals(requestIdDto.getType())) {
                        wipOutParams.add(MyUtils.wipOut(requestIdDto.getOrganizationId().toString(), TaskTypeConstant.MES_BILL_OUT,
                                requestIdDto.getWipEntityId(), requestIdDto.getItemId().toString(), requestIdDto.getQuantity().toString(),
                                requestIdDto.getOperationSeqnum(), requestIdDto.getLotNumber(), requestIdDto.getSubInventory(),
                                requestIdDto.getLocatorId() == null ? null : requestIdDto.getLocatorId().toString(),
                                MyUtils.getNinetySecondsAgo(), requestIdDto.getTransactionUom()));

                        requestIdDto.setRequestId(wipOutRequestIdAuto.getAutoId());
                        requestIdDto.setTransactionDate(DateUtils.getTime());
                        requestIdDto.setErrorMsg("调用WMS工单发料写入EBS接口失败");
                        wipOutRequestIds.add(requestIdDto);
                    }
                    //账户别名出库
                    else if (TaskTypeConstant.ACCOUNT_ALIAS.equals(requestIdDto.getType())) {
                        accountAliasOuts.add(MyUtils.accountAliasOut(requestIdDto.getTransactionTypeId(), requestIdDto.getOrganizationId().toString(),
                                requestIdDto.getItemId().toString(), requestIdDto.getSubInventory(),
                                requestIdDto.getLocatorId() == null ? null : requestIdDto.getLocatorId().toString(),
                                requestIdDto.getTransSourceName(), requestIdDto.getTransSourceId().toString(), requestIdDto.getLotNumber(),
                                requestIdDto.getQuantity().toString(), MyUtils.getNinetySecondsAgo(), requestIdDto.getTransactionUom(),
                                requestIdDto.getSourceHeaderId().toString(), requestIdDto.getSourceLineId().toString()));

                        requestIdDto.setRequestId(accountAliasOutsRequestIdAuto.getAutoId());
                        requestIdDto.setTransactionDate(DateUtils.getTime());
                        requestIdDto.setErrorMsg("调用WMS工单发料写入EBS接口失败");
                        accountAliasOutsRequestIds.add(requestIdDto);
                    }
                    //子库存转移
                    else if (TaskTypeConstant.TRANSFER.equals(requestIdDto.getType())) {
                        subInventoryTranfers.add(MyUtils.subInventoryTransfer(TaskTypeConstant.SUB_INVENTORY_TRANSFER_TYPE, requestIdDto.getOrganizationId().toString(),
                                requestIdDto.getItemId().toString(), requestIdDto.getQuantity().toString(), requestIdDto.getSubInventory(),
                                requestIdDto.getLocatorId() == null ? null : requestIdDto.getLocatorId().toString(), MyUtils.getNinetySecondsAgo(),
                                requestIdDto.getTransactionUom(), requestIdDto.getTransSubInventory(),
                                requestIdDto.getTransLocatorId() == null ? null : requestIdDto.getTransLocatorId().toString(), requestIdDto.getLotNumber(),
                                requestIdDto.getSourceHeaderId().toString(), requestIdDto.getSourceLineId().toString()));

                        requestIdDto.setAutoGrowingId(null);
                        requestIdDto.setRequestId(subInventoryTranfersRequestIdAuto.getAutoId());
                        requestIdDto.setTransactionDate(DateUtils.getTime());
                        requestIdDto.setErrorMsg("WMS调用EBS子库转移接口失败");
                        subInventoryTranfersRequestIds.add(requestIdDto);
                    }
                }
                //交货
                if (deliveryParams.size() > 0) {
                    EbsBack entityStr1 = serverVisitAddressService.requestServerCode(deliveryRequestIdAuto.getAutoId().toString(),
                            TaskTypeConstant.WMS_DEV_PROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, deliveryParams);
                    if (entityStr1 != null && entityStr1.getSuccess().equals("true") && entityStr1.getTotal() > 0) {
                        JSONArray deliveryBackJsonArrays = JSONArray.parseArray(entityStr1.getRows());

                        for (int i = 0; i < deliveryBackJsonArrays.size(); i++) {
                            com.alibaba.fastjson.JSONObject deliveryBackjsonObject = deliveryBackJsonArrays.getJSONObject(i);
                            // 处理状态
                            String processStatus = MyUtils.backString(deliveryBackjsonObject.get("processStatus"));
                            String lastUpdateDate = MyUtils.backString(deliveryBackjsonObject.get("lastUpdateDate"));
                            String lastUpdatedBy = MyUtils.backString(deliveryBackjsonObject.get("lastUpdatedBy"));
                            String errorMsg = MyUtils.backString(deliveryBackjsonObject.get("errorMsg"));
                            String creationDate = MyUtils.backString(deliveryBackjsonObject.get("creationDate"));
                            String createdBy = MyUtils.backString(deliveryBackjsonObject.get("createdBy"));
                            Integer quantity = MyUtils.backDouble(deliveryBackjsonObject.get("quantity"));
                            String subInventory = MyUtils.backString(deliveryBackjsonObject.get("subInventory"));
                            Integer locatorId = MyUtils.backInteger(deliveryBackjsonObject.get("locatorId"));
                            Integer organizationId = MyUtils.backInteger(deliveryBackjsonObject.get("organizationId"));
                            String lotNumber = MyUtils.backString(deliveryBackjsonObject.get("lotNumber"));
                            Integer transId = MyUtils.backInteger(deliveryBackjsonObject.get("transId"));
                            String shipmentNum = MyUtils.backString(deliveryBackjsonObject.get("shipmentNum"));
                            String transDate = MyUtils.backString(deliveryBackjsonObject.get("transDate"));
                            Integer id = MyUtils.backInteger(deliveryBackjsonObject.get("id"));
                            RequestId requestId = new RequestId(deliveryRequestIdAuto.getAutoId(), processStatus, lastUpdateDate, lastUpdatedBy,
                                    creationDate, createdBy, quantity, lotNumber, id, transDate, shipmentNum, errorMsg, subInventory,
                                    locatorId, organizationId, transId, TaskTypeConstant.DELIVERY);
                            if (processStatus.equals("SUCCESS")) {
                                requestId.setState(TaskTypeConstant.SUCCESS);
                            } else if (processStatus.equals("PROCESSING")) {
                                requestId.setState(TaskTypeConstant.MANAGING);
                            } else {
                                requestId.setState(TaskTypeConstant.FAIL_WAIT_MANAGE);
                            }
                            requestIdService.save(requestId);
                        }
                    } else {
                        for (RequestId requestId : deliveryRequestIds) {
                            requestIdService.save(requestId);
                        }
                    }
                }
                //请求工单出库
                if (wipOutParams.size() > 0) {
                    EbsBack entityStr = serverVisitAddressService.requestServerCode(wipOutRequestIdAuto.getAutoId().toString(), TaskTypeConstant.WMS_WIP_PROC,
                            TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, wipOutParams);
                    if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
                        JSONArray wipOutArrays = JSONArray.parseArray(entityStr.getRows());
                        for (int k = 0; k < wipOutArrays.size(); k++) {
                            com.alibaba.fastjson.JSONObject wipOutjsonObject = wipOutArrays.getJSONObject(k);
                            // 处理状态
                            String processStatus = MyUtils.backString(wipOutjsonObject.get("processStatus"));
                            String lastUpdateDate = MyUtils.backString(wipOutjsonObject.get("lastUpdateDate"));
                            String errorMsg = MyUtils.backString(wipOutjsonObject.get("errorMsg"));
                            String creationDate = MyUtils.backString(wipOutjsonObject.get("creationDate"));
                            String lastUpdatedBy = MyUtils.backString(wipOutjsonObject.get("lastUpdatedBy"));
                            String createdBy = MyUtils.backString(wipOutjsonObject.get("createdBy"));
                            Integer itemId = MyUtils.backDouble(wipOutjsonObject.get("itemId"));
                            Integer quantitys = MyUtils.backDouble(wipOutjsonObject.get("quantity"));
                            String subInventorys = MyUtils.backString(wipOutjsonObject.get("subInventory"));
                            Integer locatorId = MyUtils.backInteger(wipOutjsonObject.get("locatorId"));
                            String lotNumber = MyUtils.backString(wipOutjsonObject.get("lotNumber"));
                            Integer organizationId = MyUtils.backInteger(wipOutjsonObject.get("organizationId"));
                            String transDate = MyUtils.backString(wipOutjsonObject.get("transDate"));
                            String transUom = MyUtils.backString(wipOutjsonObject.get("transUom"));
                            Integer transTypeId = MyUtils.backInteger(wipOutjsonObject.get("transTypeId"));
                            Integer wipEntityId = MyUtils.backInteger(wipOutjsonObject.get("wipEntityId"));
                            String operationSeqNum = MyUtils.backString(wipOutjsonObject.get("operationSeqNum"));
                            Integer id = MyUtils.backInteger(wipOutjsonObject.get("id"));
                            RequestId requestId = new RequestId(wipOutRequestIdAuto.getAutoId(), processStatus, lastUpdateDate,
                                    lastUpdatedBy, creationDate, createdBy, itemId, quantitys, lotNumber, id, transDate,
                                    errorMsg, subInventorys, locatorId, organizationId, TaskTypeConstant.OUT, transTypeId + "",
                                    wipEntityId + "", operationSeqNum, transUom);
                            if (processStatus.equals("SUCCESS")) {
                                requestId.setState(TaskTypeConstant.SUCCESS);
                            } else if (processStatus.equals("PROCESSING")) {
                                requestId.setState(TaskTypeConstant.MANAGING);
                            } else {
                                requestId.setState(TaskTypeConstant.FAIL_WAIT_MANAGE);
                            }
                            requestIdService.save(requestId);
                        }
                    } else {
                        for (RequestId requestId : wipOutRequestIds) {
                            requestIdService.save(requestId);
                        }
                    }
                }
                //账户别名出库
                if (accountAliasOuts.size() > 0) {
                    EbsBack entityStr = serverVisitAddressService.requestServerCode(accountAliasOutsRequestIdAuto.getAutoId().toString(),
                            TaskTypeConstant.WMSSUBINV_TRANSPROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, accountAliasOuts);
                    //判断是否请求到参数
                    if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
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
                            String subInventory = MyUtils.backString(jsonObject.get("subInventory"));
                            Integer locatorId = MyUtils.backInteger(jsonObject.get("locatorId"));
                            Integer organizationId = MyUtils.backInteger(jsonObject.get("organizationId"));
                            String transDate = MyUtils.backString(jsonObject.get("transDate"));
                            String transUom = MyUtils.backString(jsonObject.get("transUom"));
                            Integer transTypeId = MyUtils.backInteger(jsonObject.get("transTypeId"));
                            String lotNumber = MyUtils.backString(jsonObject.get("transLotNumber"));
                            Integer sourceHeaderId = MyUtils.backInteger(jsonObject.get("sourceHeaderId"));
                            Integer sourceLineId = MyUtils.backInteger(jsonObject.get("sourceLineId"));
                            String transSourceName = MyUtils.backString(jsonObject.get("transSourceName"));
                            Integer transSourceId = MyUtils.backInteger(jsonObject.get("transSourceId"));
                            Integer id = MyUtils.backInteger(jsonObject.get("id"));
                            RequestId requestId = new RequestId(accountAliasOutsRequestIdAuto.getAutoId(), processStatus, lastUpdateDate, lastUpdatedBy,
                                    creationDate, createdBy, itemId, ebsbackQuantity, lotNumber, id, transDate, errorMsg, subInventory, locatorId,
                                    organizationId, TaskTypeConstant.ACCOUNT_ALIAS, transTypeId.toString(), transUom, sourceHeaderId, sourceLineId,
                                    transSourceName, transSourceId);
                            if (processStatus.equals("SUCCESS")) {
                                requestId.setState(TaskTypeConstant.SUCCESS);
                            } else if (processStatus.equals("PROCESSING")) {
                                requestId.setState(TaskTypeConstant.MANAGING);
                            } else {
                                requestId.setState(TaskTypeConstant.FAIL_WAIT_MANAGE);
                            }
                            requestIdService.save(requestId);
                        }
                    } else {
                        for (RequestId requestId : accountAliasOutsRequestIds) {
                            requestIdService.save(requestId);
                        }
                    }
                }
                //子库存转移
                if (subInventoryTranfers.size() > 0) {
                    EbsBack entityStr = serverVisitAddressService.requestServerCode(subInventoryTranfersRequestIdAuto.getAutoId().toString(),
                            TaskTypeConstant.WMSSUBINV_TRANSPROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, subInventoryTranfers);
                    //判断是否请求到参数
                    if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
                        requestIdService.subInventoryTransfer(entityStr, subInventoryTranfersRequestIdAuto);
                    /*JSONArray jsonArrays = JSONArray.parseArray(entityStr.getRows());
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
                        RequestId requestId = new RequestId(subInventoryTranfersRequestIdAuto.getAutoId(),processStatus,lastUpdateDate,lastUpdatedBy,
                                creationDate,createdBy,itemId,ebsbackQuantity,lotNumber,id,transDate,errorMsg,subInventorys,locatorId,
                                organizationId,TaskTypeConstant.TRANSFER,transTypeId+"",transUom,transSubInventory,transLocatorId,
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
                        requestIdService.save(requestId);
                    }*/
                    } else {
                        for (RequestId requestId : subInventoryTranfersRequestIds) {
                            requestIdService.save(requestId);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
            }
        }else{
            return toAjax(true).success("暂无EBS处理失败数据");
        }
        return toAjax(true).success("刷新成功！");
    }
}
