package com.deer.wms.quartz.task;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.box.*;
import com.deer.wms.base.system.model.task.PickTaskCriteria;
import com.deer.wms.base.system.model.task.PickTaskDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.WareInfo;
import com.deer.wms.base.system.service.*;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.mailServer.MailService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IWareInfoService;
import com.deer.wms.base.system.service.webSocket.WebSocketServer;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.framework.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时任务调度测试
 * 
 * @author deer
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private BillInRecordService billInRecordService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private BillInCheckRecordService billInCheckRecordService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;
    @Autowired
    private SubInventoryService subInventoryService;
    @Autowired
    private IBillOutMasterService billOutMasterService;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private PickTaskService pickTaskService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private IWareInfoService wareInfoService;
    @Autowired
    private MailService mailService;
    @Autowired
    private WarnInformationService warnInformationService;


    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    /** 定时发送WMS未检验入库的物料给EBS，并回传给WMS */
//    @Scheduled(cron = "0 30 0/1 * * ? ")
    public void getCheckOutFromEBS(){
//        System.out.println("铁憨憨");
        try {
            List<BillInRecordDto> billInRecordSelectParams = billInRecordService.findCheckRecordFromEBS(new BillInRecordCriteria(2,1006));
            if(billInRecordSelectParams.size()>0) {
//                List<Integer> billInDetailIds = new ArrayList<>();
                List<Map<String, String>> selectCheckResultParams = new ArrayList<>();
                for (BillInRecordDto billInRecordDto : billInRecordSelectParams) {
                    Map<String, String> map = new HashMap<>();
                    map.put("poLineLocationId", billInRecordDto.getLineLocationId().toString());
                    selectCheckResultParams.add(map);

//                    billInDetailIds.add(billInRecordDto.getBillInDetailId());
                }

                //查询相关子库信息
                SubInventory qualifiedSubInventory = subInventoryService.findById(TaskTypeConstant.QUALIFIED);
                SubInventory unQualifiedSubInventory = subInventoryService.findById(TaskTypeConstant.UNQUALIFIED);
                //获取EBS的检验结果
                EbsBack entityStr = serverVisitAddressService.requestServerCode("2",
                        TaskTypeConstant.GET_TRANSACTIONS, TaskTypeConstant.QUERY,null, selectCheckResultParams);
                if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
                    JSONArray checkResultJsonArrays = JSONArray.parseArray(entityStr.getRows());
                    //创建回传结果
                    List<Map<String, String>> deliveryParams = new ArrayList<>();
                    List<RequestId> requestIds = new ArrayList<>();
//                    List<Integer> checkIds = new ArrayList<>();
                    List<Integer> billInRecords = new ArrayList<>();
                    RequestIdAuto requestIdAuto = requestIdAutoService.backAutoId("WMS交货数据写入EBS接口");

                    for (int i = 0; i < checkResultJsonArrays.size(); i++) {
                        com.alibaba.fastjson.JSONObject checkResultJsonObject = checkResultJsonArrays.getJSONObject(i);
                        // 接收库存组织ID
                        Integer organizationId = MyUtils.backInteger(checkResultJsonObject.get("organizationId"));
                        // 接收号
                        String receiptNum = MyUtils.backString(checkResultJsonObject.get("receiptNum"));
                        // 检验ID
                        Integer transactionId = MyUtils.backInteger(checkResultJsonObject.get("transactionId"));
                        // 采购订单头ID
                        Integer poHeaderId = MyUtils.backInteger(checkResultJsonObject.get("poHeaderId"));
                        //采购订单行ID
                        Integer poLineId = MyUtils.backInteger(checkResultJsonObject.get("poLineId"));
                        // 发运行ID
                        Integer lineLocationId = MyUtils.backInteger(checkResultJsonObject.get("poLineLocationId"));
                        //  分配行ID
                        Integer poDistributionId = MyUtils.backInteger(checkResultJsonObject.get("poDistributionId"));
                        // 物料Id
                        Integer itemId = MyUtils.backInteger(checkResultJsonObject.get("itemId"));
                        // 接收数量
                        Integer quantity = MyUtils.backDouble(checkResultJsonObject.get("quantity"));
                        // 检验结果
                        String transactionType = MyUtils.backString(checkResultJsonObject.get("transactionType"));
                        //批次
                        String batch = MyUtils.backString(checkResultJsonObject.get("vendorLotNum"));
                        //根据检验Id查找记录，没有则写入检验记录
                        BillInCheckRecord alreadyCheckRecord = billInCheckRecordService.findByTransactionId(new BillInCheckRecordCriteria(receiptNum,transactionId));
                        if(alreadyCheckRecord == null){
                            List<BillInRecordDto> billInRecordDtos = billInRecordService.findList(new BillInRecordCriteria(receiptNum));
                            if(billInRecordDtos.size()>0) {
                                BillInCheckRecord billInCheckRecord = new BillInCheckRecord(organizationId, receiptNum, transactionId,
                                        poHeaderId, poLineId, lineLocationId, poDistributionId, itemId, quantity, transactionType,
                                        DateUtils.getTime(), batch, 2);
                                billInCheckRecordService.save(billInCheckRecord);

//                            checkIds.add(billInCheckRecord.getCheckId());
                                //查询WMS入库记录

                                BillInRecordDto billInRecordDto = billInRecordDtos.get(0);
                                //交货写入请求参数
                                if (transactionType.equals("ACCEPT")) {
                                    deliveryParams.add(MyUtils.delivery(MyUtils.getNinetySecondsAgo(), transactionId.toString(), organizationId.toString(),
                                            qualifiedSubInventory.getSubInventoryCode(), qualifiedSubInventory.getSlotting() == null ? null : qualifiedSubInventory.getSlotting(),
                                            batch, billInRecordDto.getAcceptQuantity().toString(), receiptNum));
                                    billInRecordDto.setState(3);
                                } else {
                                    deliveryParams.add(MyUtils.delivery(MyUtils.getNinetySecondsAgo(), transactionId.toString(), organizationId.toString(),
                                            unQualifiedSubInventory.getSubInventoryCode(), unQualifiedSubInventory.getSlotting() == null ? null : unQualifiedSubInventory.getSlotting(),
                                            batch, billInRecordDto.getAcceptQuantity().toString(), receiptNum));
                                    billInRecordDto.setState(4);
                                }
                                billInRecordService.update(billInRecordDto);
                                //记录入库记录
                                billInRecords.add(billInRecordDto.getBillInRecordId());

                                RequestId requestId = new RequestId(requestIdAuto.getAutoId(), quantity, batch, DateUtils.getTime(), receiptNum,
                                        qualifiedSubInventory.getSubInventoryCode(), qualifiedSubInventory.getSlotting() == null ? null : Integer.parseInt(qualifiedSubInventory.getSlotting()),
                                        organizationId, transactionId, TaskTypeConstant.DELIVERY, TaskTypeConstant.FAIL_WAIT_MANAGE,
                                        "WMS交货数据写入EBS接口失败", "ERROR");
//                            requestIdService.save(requestId);
                                requestIds.add(requestId);
                            }
                        }
                    }
                    if(billInRecords.size()>0){
                        List<BillInRecordDto> billInRecordDtos = billInRecordService.findCheckRecordFromEBS(new BillInRecordCriteria(billInRecords,1007));
                        for(BillInRecordDto selectBillInRecordDto : billInRecordDtos){
                            BoxItem boxItem = boxItemService.getBoxItemByBoxCode(selectBillInRecordDto.getBoxCode());
                            if(selectBillInRecordDto.getState().equals(3)){
                                boxItem.setSubInventoryId(TaskTypeConstant.QUALIFIED);
                            }else{
                                boxItem.setSubInventoryId(TaskTypeConstant.UNQUALIFIED);
                            }
                            boxItemService.update(boxItem);
                        }
                    /*if(checkIds.size()>0) {
                        //修改入库记录状态
                        String boxCode = "";
                        BoxItem boxItem = null;
                        List<BillInCheckRecord> billInCheckRecords = billInCheckRecordService.findByBillInDetailIds(new BillInCheckRecordCriteria(checkIds));
                        for (BillInCheckRecord billInCheckRecord : billInCheckRecords) {
                            List<BillInRecordDto> selectBillInRecordDtoStateTwos = billInRecordService.findList(
                                    new BillInRecordCriteria(2, billInCheckRecord.getBatch(), billInCheckRecord.getPoDistributionId()));
                            boxCode="";
                            for (BillInRecordDto selectBillInRecordDtoTwo : selectBillInRecordDtoStateTwos) {
                                if (billInCheckRecord.getTransaction().equals("ACCEPT")) {
                                    if(!boxCode.equals(selectBillInRecordDtoTwo.getBoxCode())){
                                        boxCode = selectBillInRecordDtoTwo.getBoxCode();
                                        boxItem = boxItemService.getBoxItemByBoxCode(selectBillInRecordDtoTwo.getBoxCode());
                                        boxItem.setSubInventoryId(TaskTypeConstant.QUALIFIED);
                                    }
                                    selectBillInRecordDtoTwo.setState(3);
                                } else {
                                    if(!boxCode.equals(selectBillInRecordDtoTwo.getBoxCode())){
                                        boxCode = selectBillInRecordDtoTwo.getBoxCode();
                                        boxItem = boxItemService.getBoxItemByBoxCode(selectBillInRecordDtoTwo.getBoxCode());
                                        boxItem.setSubInventoryId(TaskTypeConstant.UNQUALIFIED);
                                    }
                                    selectBillInRecordDtoTwo.setState(4);
                                }
                                billInRecordService.update(selectBillInRecordDtoTwo);
                                boxItemService.update(boxItem);
                            }
                        }*/
                        //WMS回传EBS交货数据
                        EbsBack entityStr1 = serverVisitAddressService.requestServerCode(requestIdAuto.getAutoId().toString(),
                                TaskTypeConstant.WMS_DEV_PROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE,null, deliveryParams);
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
                                RequestId requestId = new RequestId(requestIdAuto.getAutoId(),processStatus,lastUpdateDate,lastUpdatedBy,
                                        creationDate,createdBy,quantity,lotNumber,id,transDate,shipmentNum,errorMsg,subInventory,
                                        locatorId,organizationId,transId,TaskTypeConstant.DELIVERY);
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
                            }
                        }else{
                            for(RequestId requestId : requestIds){
                                requestIdService.save(requestId);
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE);
        }
    }

    /**
     * 每四个小时发一次工单发料
     */
    public void timeCalculate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        PickTaskCriteria criteria = new PickTaskCriteria();
        criteria.setStartTime(time);
        criteria.setEndTime(DateUtils.getTime());
        criteria.setState(0);
        criteria.setWorkOrderStockState(1);
        criteria.setPickState(1);
        criteria.setBillOutMasterType(1);
//        System.out.println(time+"  "+DateUtils.getTime());
        String boxCode = "";
        List<PickTaskDto> pickTaskDtos = pickTaskService.findList(criteria);
        for(PickTaskDto pickTaskDto : pickTaskDtos){
            if (!boxCode.equals(pickTaskDto.getBoxCode())) {
                boxCode = pickTaskDto.getBoxCode();
                //11-从货位上拿托盘到点数机
                TaskInfo outCountDevice = new TaskInfo(new GuidUtils().toString(), MyUtils.connectShelfNameAndRowAndColumn(
                        pickTaskDto.getShelfName(), pickTaskDto.getsColumn(), pickTaskDto.getsRow()), null,TaskTypeConstant.CELL_TO_PAPER_COUNTERS,
                        0, pickTaskDto.getQuantity(), boxCode, pickTaskDto.getPriority(), pickTaskDto.getBillOutDetailId());
                outCountDevice.setTaskStartTime(DateUtils.getTime());
                taskInfoService.save(outCountDevice);
                CellInfo cellInfo = cellInfoService.findById(pickTaskDto.getCellId());
                cellInfo.setState(2);
                cellInfoService.update(cellInfo);
                BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(pickTaskDto.getBoxCode());
                boxInfo.setBoxState(2);
                boxInfoService.update(boxInfo);
            }
            pickTaskDto.setPickState(2);
            pickTaskService.update(pickTaskDto);
            BillOutMaster billOutMaster = billOutMasterService.findById(pickTaskDto.getBillId());
            billOutMaster.setState(1);
            billOutMasterService.update(billOutMaster);
        }
//        System.out.println("下发完成");
    }

    /**
     * 定时查询不合格物料滞库时间进行对比，超期则报警
     */
    public void unqualifiedStorageSuggishOverdue(){
        try {
            WareInfo wareInfo = wareInfoService.findById(212);
            List<UnqualifiedOverTakeCanDelayDays> boxItemDtos = boxItemService.findUnqualifiedOverTakeCanDelayDays(
                    new BoxItemCriteria(TaskTypeConstant.UNQUALIFIED, wareInfo.getUnqualifiedStorageDay()));
            if(boxItemDtos.size()>0) {
                String msg = "不合格库存存储时间超过设定时间";
                WarnInformation warnInformation = new WarnInformation(msg,
                        1,TaskTypeConstant.UNQUALIFIED_OVERTAKE_CAN_DELAY_DAYS,DateUtils.getTime());
                warnInformationService.save(warnInformation);
                Map<String,String> maps = MyUtils.backWaringMessage(msg,
                        warnInformation.getWarnId().toString(),TaskTypeConstant.UNQUALIFIED_OVERTAKE_CAN_DELAY_DAYS.toString());
                WebSocketServer.sendObject(maps,null);
                ExcelUtil<UnqualifiedOverTakeCanDelayDays> util = new ExcelUtil<UnqualifiedOverTakeCanDelayDays>(UnqualifiedOverTakeCanDelayDays.class);
//                mailService.analysisSendMail(util.exportExcel(boxItemDtos, "不合格库存超期明细"), "不合格库存超期明细",
//                        "当前有不合格库存存储时间超过设定日期，附件为明细请查收!!");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }
    }
}
