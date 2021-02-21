package com.deer.wms.base.system.web.bill;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.bill.*;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import com.deer.wms.base.system.model.ware.Door;
import com.deer.wms.base.system.service.*;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.base.system.service.bill.IBillInDetailService;
import com.deer.wms.base.system.service.bill.IBillInMasterService;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IDoorService;
import com.deer.wms.base.system.service.webSocket.WebSocketServer;
import com.deer.wms.base.system.web.box.BoxItemController;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.framework.util.MyUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/out/billOutDetail")
public class BillOutDetailController extends BaseController {

    @Autowired
    private IBillOutDetailService billOutDetailService;
    @Autowired
    private IBillOutMasterService billOutMasterService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private IDoorService doorService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private SubinventoryTransferRecordService subinventoryTransferRecordService;
    @Autowired
    private IItemInfoService itemInfoService;
    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private SubInventoryService subInventoryService;
    @Autowired
    private AccountAliasService accountAliasService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;
    @Autowired
    private IBillInDetailService billInDetailService;
    @Autowired
    private CarrierService carrierService;
    @Autowired
    private CallAgvService callAgvService;
    @Autowired
    private PickTaskService pickTaskService;

    private int state = 1;

    private String prefix = "out/billInDetail";

    /**
     * 根据BillOutDetailId删除BillOutDetail
     *
     */
    @RequiresPermissions("out:billOutDetail:deleteBillOutDetailByBillOutDetailId")
    @GetMapping("/deleteBillOutDetailByBillOutDetailId")
    @ResponseBody
    public Result deleteBillOutDetailByBillOutDetailId(Integer billOutDetailId){
        billOutDetailService.deleteBillOutDetailByBillOutDetailId(billOutDetailId);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据出库单ID查询出库单详情
     */
    @RequiresPermissions("out:billOutDetail:findListByBillId")
    @PostMapping("/findListByBillId")
    @ResponseBody
    public TableDataInfo list(Integer billId) {
        startPage();
        List<BillOutDetailDto> list = billOutDetailService.findListByBillId(billId);
        return getDataTable(list);
    }


    @PostMapping("/supplyWCSTransfer")
    @ResponseBody
    public Result selectInventory(HttpServletRequest request) {
        String wholeStr = null;
        com.alibaba.fastjson.JSONObject jsonObject = null;
        String AGVCacheAStatus = null;
        String LiftAStatus = null;
        String RiseTurnStatus = null;
        String QRCode = null;
        try {
            wholeStr = MyUtils.analysisHttpServletRequest(request);
            jsonObject = JSONObject.parseObject(wholeStr);
            //出库段入料口位置，AGVCacheAStatus 1-有载具 0-无载具，
            AGVCacheAStatus = jsonObject.get("AGVCacheAStatus") == null ? "" : jsonObject.get("AGVCacheAStatus").toString().trim();
            //出库段入料口提升滚轮，LiftAStatus 1-升降机在下 0-升降机在上
            LiftAStatus = jsonObject.get("LiftAStatus") == null ? "" : jsonObject.get("LiftAStatus").toString().trim();
            //出库段入料口顶升转向输送线，RiseTurnStatus 1-有载具  0-无载具
            RiseTurnStatus = jsonObject.get("RiseTurnStatus") == null ? "" : jsonObject.get("RiseTurnStatus").toString().trim();
            //入点数机载具编码
            QRCode = jsonObject.get("QRCode") == null ? "" : jsonObject.get("QRCode").toString().trim();
            //出库段出料口状态
            if(AGVCacheAStatus.equals("0") && LiftAStatus.equals("1") && RiseTurnStatus.equals("0")){
                //立体库拿取空载具
//                CallAgv callagv = callAgvService.findByStateOne();
                if(state == 1) {
                    state = 2;
//                    EmptyShelfInReq();
//                    Thread.sleep(5000);
                }
            }
            else if(AGVCacheAStatus.equals("1") && LiftAStatus.equals("0") && RiseTurnStatus.equals("0")){
                //立体库取空载具成功
//                CallAgv callagv = callAgvService.findByStateOne();
//                callagv.setState(2);
//                callAgvService.update(callagv);
                state = 1;
//                StockTakeShelf();
            }
            //验证载具编码
            if(!QRCode.equals("")){
                /*Carrier carrier = carrierService.inValidate(QRCode);
                if(carrier == null){
                    WebSocketServer.sendInfo("当前进入点数机中载具与实际排队不符！",null);
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }finally{
            wholeStr = null;
            jsonObject = null;
            AGVCacheAStatus = null;
            LiftAStatus = null;
            RiseTurnStatus = null;
        }
        return ResultGenerator.genSuccessResult("OK");
    }
    /**
     * WCS调用此接口叫空载具
     * @return
     */
    /*@GetMapping("/EmptyShelfInReq")
    @ResponseBody
    @Transactional*/
    private WebserviceResponse EmptyShelfInReq() {
        WebserviceResponse webserviceResponse =null;
        try {
            List<Door> lists = doorService.selectDoorList(new Door(null,null,null,1,null,null));
            /*Client client = webserviceAddress();
            Object[] objects;*/
            String code = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<EmptyShelfInReq\n" +
                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                    "macCode=\""+ lists.get(0).getCode() +"\" \n" +
                    "berthCode=\""+ lists.get(0).getAddressCode() +"\" \n" +
                    "taskCode=\""+ new GuidUtils().toString() +"\">\n" +
                    "</EmptyShelfInReq >";
            /*objects = client.invoke("macIntf", "EmptyShelfInReq",code);
            webserviceResponse = analysisObject(objects[0]);
            System.out.println("返回数据:" + objects[0].toString());*/

            webserviceResponse = serverVisitAddressService.requestMesServer("EmptyShelfInReq",code);
            if(webserviceResponse.getErrorMsg().equals("OK") && webserviceResponse.getErrorCode().equals("0")){
                state = 2;
//                CallAgv callAgv = new CallAgv(code,1);
//                callAgvService.save(callAgv);
            }else{
                state = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            webserviceResponse = new WebserviceResponse(null,"-1","调用WMS接口出错",null);
        }
        return webserviceResponse;
    }

    /**
     * 立体库拿取空载具成功
     * @return
     */
    /*@GetMapping("/StockTakeShelf")
    @ResponseBody*/
    public WebserviceResponse StockTakeShelf() {
        WebserviceResponse webserviceResponse =null;
        try {
            List<Door> lists = doorService.selectDoorList(new Door(null,null,null,1,null,null));
            /*String MesIpAddress = serverVisitAddressService.findAddressById(1);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(MesIpAddress);
            Client client = webserviceAddress();
            Object[] objects;*/
            String code = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<StockTakeShelf\n" +
                    "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                    "macCode=\""+ lists.get(0).getCode() +"\" \n" +
                    "berthCode=\""+ lists.get(0).getAddressCode() +"\" \n" +
                    "taskCode=\""+ new GuidUtils().toString() +"\">\n" +
                    "</StockTakeShelf >";
            /*objects = client.invoke("macIntf", "StockTakeShelf",code);
            webserviceResponse = analysisObject(objects[0]);
            System.out.println("返回数据:" + objects[0].toString());*/
            webserviceResponse = serverVisitAddressService.requestMesServer("StockTakeShelf",code);
//            System.out.println(webserviceResponse.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            webserviceResponse = new WebserviceResponse(null,"-1","调用WMS接口出错",null);
        }
        return webserviceResponse;
    }

    /*private Client webserviceAddress(){
        ServerVisitAddress MesIpAddress = serverVisitAddressService.findAddressById(1);
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(MesIpAddress.getVisitAddress());
    }
    public WebserviceResponse analysisObject(Object object) throws Exception{
        Field taskCode = object.getClass().getDeclaredField("x003CTaskCodeX003EKBackingField");
        taskCode.setAccessible(true);
        Field errorCode = object.getClass().getDeclaredField("x003CErrorCodeX003EKBackingField");
        errorCode.setAccessible(true);
        Field errorMsg = object.getClass().getDeclaredField("x003CErrorMsgX003EKBackingField");
        errorMsg.setAccessible(true);
        Field resultData = object.getClass().getDeclaredField("x003CResultDataX003EKBackingField");
        resultData.setAccessible(true);
        String taskCode1 = taskCode.get(object).toString();
        String errorCode1 = errorCode.get(object).toString();
        String errorMsg1 =  errorMsg.get(object).toString();
        String resultData1 = resultData.get(object)==null?"":resultData.get(object).toString();
        WebserviceResponse webserviceResponse = new WebserviceResponse(
                taskCode.get(object).toString(),errorCode.get(object).toString(),
                errorMsg.get(object).toString(), resultData1);
        return webserviceResponse;
    }*/

    /**
     * 退货
     */
    @PostMapping("/returnItem")
    @ResponseBody
    @Transactional
    public Result returnItem(@RequestBody BoxItemCriteria boxItemCriteria) {
        String error = "";
        try {
            boxItemCriteria.setOrderByState(1003);
            Operator operator = operatorService.findByCard(boxItemCriteria.getLoginPersonCardNo());
            List<BoxItemDto> boxItemDtos = boxItemService.findList(boxItemCriteria);
            BillOutMaster billOutMaster = new BillOutMaster(null, null, null, DateUtils.getTime(), operator.getOperatorName(), operator.getOperatorId(),
                    1, "退货出库", null, 2);
            billOutMasterService.save(billOutMaster);
            BillOutDetail billOutDetail = new BillOutDetail(null, billOutMaster.getBillId(), boxItemDtos.get(0).getItemCode(),
                    boxItemCriteria.getQuantity(), null, null, null);
            billOutDetailService.save(billOutDetail);
            String bool = cellInfoService.judgeBoxItemState(boxItemDtos);
            if (!bool.equals("success")) {
                error = bool;
                throw new RuntimeException();
            }
            for (BoxItemDto boxItemDto : boxItemDtos) {
                cellInfoService.updateCellStateAndBoxStateAndSendTaskInfo(boxItemDto, billOutDetail.getBillOutDetailId(), boxItemCriteria.getLoginPersonCardNo());
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE,error);
        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 确认退货
     */
    @PostMapping("/ensureReturnItem")
    @ResponseBody
    public Result ensureReturnItem(@RequestBody BoxItemCriteria boxItemCriteria) {
        //EBS订单退货接口

        /*String msg = cellInfoService.inNullBox(boxItemCriteria.getBoxCode());
        if(!msg.equals("success")){
            return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE,"无可用货位");
        }*/
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 报废
     */
    @PostMapping("/scrapItemOutBox")
    @ResponseBody
    @Transactional
    public Result scrapItemOutBox(@RequestBody BoxItemCriteria boxItemCriteria) {
        String error = "";
        int count = 0;
        try {
            Operator operator = operatorService.findByCard(boxItemCriteria.getLoginPersonCardNo());
            boxItemCriteria.setBoxState(1);
            boxItemCriteria.setCellState(1);
            boxItemCriteria.setWorkOrderStockState(0);
            List<BoxItemDto> boxItemDtos = boxItemService.findList(boxItemCriteria);
            if(boxItemDtos.size()<1){
                error = "立体库无可报废物料或需要的报废物料锁定中";
                throw new RuntimeException();
            }
            int quantitys = 0;

            List<Map<String, String>> lists = new ArrayList<>();
//            SubInventory subInventory = subInventoryService.findById(1);
            SubInventory  subInventory = subInventoryService.findById(TaskTypeConstant.SCRAP);
            RequestIdAuto requestIdAuto = requestIdAutoService.backAutoId("WMS转库数据写入EBS接口");
            List<RequestId> requestIds = new ArrayList<>();
            //记录单据
            BillOutMaster billOutMaster = new BillOutMaster(null, null, null, DateUtils.getTime(),
                    operator.getOperatorName(), operator.getOperatorId(),1, "报废出库", null, 3);
            billOutMasterService.save(billOutMaster);
            BillOutDetail billOutDetail = new BillOutDetail(null, billOutMaster.getBillId(), boxItemDtos.get(0).getItemCode(),
                    0, null, null, null);
            for (BoxItemDto boxItemDto : boxItemDtos) {
                //判断报废库存是否符合规则
                if(!boxItemDto.getSubInventoryId().equals(TaskTypeConstant.QUALIFIED) ||
                        !boxItemDto.getSubInventoryId().equals(TaskTypeConstant.UNQUALIFIED) ||
                        !boxItemDto.getSubInventoryId().equals(TaskTypeConstant.OVER_DUE) ||
                        !boxItemDto.getSubInventoryId().equals(TaskTypeConstant.POSTPONE)){
                    error = "当前物料及批次中部分在"+boxItemDto.getSubInventoryName()+boxItemDto.getSubInventoryCode();
                    throw new RuntimeException();
                }
                PickTask pickTask = new PickTask(boxItemDto.getBoxCode(), boxItemDto.getQuantity(),billOutDetail.getBillOutDetailId(),
                        4,boxItemDto.getBatch(),boxItemDto.getSubInventoryId(),DateUtils.getTime());
                pickTask.setOutTime(DateUtils.getTime());
                pickTaskService.save(pickTask);
                //下发出库任务
                cellInfoService.updateCellStateAndBoxStateAndSendTaskInfo(boxItemDto, billOutDetail.getBillOutDetailId(), boxItemCriteria.getLoginPersonCardNo());
                //写入转库记录
                SubinventoryTransferRecord subinventoryTransferRecord = new SubinventoryTransferRecord(
                        boxItemDto.getBoxCode(),boxItemDto.getItemCode(),boxItemDto.getBatch(),boxItemDto.getQuantity(),
                        DateUtils.getTime(),boxItemCriteria.getLoginPersonCardNo(),boxItemDto.getSubInventoryId(), TaskTypeConstant.SCRAP,"报废原因:人工报废"
                );
                subinventoryTransferRecordService.save(subinventoryTransferRecord);
                //统计数量及箱数
                quantitys += boxItemDto.getQuantity();
                count++;
                //写入EBS交互参数
                BillInDetailCriteria billInDetailCriteria = new BillInDetailCriteria();
                billInDetailCriteria.setItemId(boxItemDto.getInventoryItemId());
                billInDetailCriteria.setBatch(boxItemDto.getBatch());
                BillInDetailDto billInDetailDto = billInDetailService.findList(billInDetailCriteria).get(0);
                lists.add(MyUtils.subInventoryTransfer(TaskTypeConstant.SUB_INVENTORY_TRANSFER_TYPE,subInventory.getOrganizationId().toString(),
                        boxItemDto.getInventoryItemId().toString(),boxItemDto.getQuantity().toString(),boxItemDto.getSubInventoryCode(),
                        boxItemDto.getSlotting()==null?null:boxItemDto.getSlotting(),MyUtils.getNinetySecondsAgo(),boxItemDto.getUnit(),
                        subInventory.getSubInventoryCode(),subInventory.getSlotting()==null?null:subInventory.getSlotting(),
                        boxItemDto.getBatch(),billInDetailDto.getBillId().toString(),billInDetailDto.getBillInDetailId().toString()));

                RequestId requestId = new RequestId(requestIdAuto.getAutoId(),boxItemDto.getInventoryItemId(),boxItemDto.getQuantity(),
                        boxItemDto.getBatch(),DateUtils.getTime(),"WMS调用EBS子库转移接口失败",boxItemDto.getSubInventoryCode(),
                        boxItemDto.getSlotting()==null?null:Integer.parseInt(boxItemDto.getSlotting()),subInventory.getOrganizationId(),TaskTypeConstant.TRANSFER,
                        TaskTypeConstant.FAIL_WAIT_MANAGE,TaskTypeConstant.SUB_INVENTORY_TRANSFER_TYPE,boxItemDto.getUnit(),
                        subInventory.getSubInventoryCode(),subInventory.getSlotting()==null?null:Integer.parseInt(subInventory.getSlotting()),
                        billInDetailDto.getBillId(),billInDetailDto.getBillInDetailId(),"ERROR");
//                requestIdService.save(requestId);
                requestIds.add(requestId);
            }
            billOutDetail.setQuantity(quantitys);
            billOutDetailService.save(billOutDetail);
            //EBS物料报废（子库转移）接口
            EbsBack entityStr = serverVisitAddressService.requestServerCode(requestIdAuto.getAutoId().toString(),
                    TaskTypeConstant.WMSSUBINV_TRANSPROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, lists);
            //判断是否请求到参数
            if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
                requestIdService.subInventoryTransfer(entityStr,requestIdAuto);
            }else{
                for(RequestId requestId : requestIds){
                    requestIdService.save(requestId);
                }
            }
            //给页面回传报废总数量
            boxItemCriteria.setQuantitys(quantitys);
            //给页面回传总箱数
            boxItemCriteria.setQuantity(count);
        /*String msg = cellInfoService.inNullBox(boxItemCriteria.getBoxCode());*/
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE,error);
        }
        return ResultGenerator.genSuccessResult(boxItemCriteria);
    }



    /**
     * 非工单出库
     */
    @PostMapping("/nonWorkerOrderItemOutBox")
    @ResponseBody
    @Transactional
    public Result nonWorkerOrderItemOutBox(@RequestBody BoxItemCriteria boxItemCriteria) {
        String error = "";
        int count = 0;
        try {
            Operator operator = operatorService.findByCard(boxItemCriteria.getLoginPersonCardNo());
            boxItemCriteria.setOrderByState(1004);
            boxItemCriteria.setBoxState(1);
            boxItemCriteria.setCellState(1);
            boxItemCriteria.setWorkOrderStockState(0);
            List<BoxItemDto> boxItemDtos = boxItemService.findList(boxItemCriteria);
            int quantitys = 0;
            int quantity = boxItemCriteria.getQuantitys();
            List<BoxItemDto> lists = null;
            if(boxItemDtos.size()<1){
                return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE,"此物料当前无可出框");
            }else {
                Circulation:
                for (int i = 0; i < boxItemDtos.size(); i++) {
                    BoxItemDto boxItemDto = boxItemDtos.get(i);
                    quantitys += boxItemDto.getQuantity();
                    //当需要的数量小于等于托盘中可出数量
                    if (quantity <= quantitys) {
                        lists = boxItemDtos.subList(0, count + 1);
                        break Circulation;
                    }
                    if ((i == (boxItemDtos.size() - 1)) && (quantity > quantitys)) {
                        return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE, "数量不足,当前立体库内可出数量为" + quantitys);
                    }
                    count++;
                }
            }
            BillOutMaster billOutMaster = new BillOutMaster(null, null, null, DateUtils.getTime(), operator.getOperatorName(), operator.getOperatorId(),
                    1, "非工单出库", null, 4);
            billOutMaster.setAccountAliasId(boxItemCriteria.getAccountAliasId());
            billOutMasterService.save(billOutMaster);
            BillOutDetail billOutDetail = new BillOutDetail(null, billOutMaster.getBillId(), boxItemCriteria.getItemCode(),
                    boxItemCriteria.getQuantitys(), null, null, null);
            billOutDetailService.save(billOutDetail);
            int q = 0;
            for(BoxItemDto boxItemDto : lists) {
                q += boxItemDto.getQuantity();
                cellInfoService.updateCellStateAndBoxStateAndSendTaskInfo(boxItemDto, billOutDetail.getBillOutDetailId(), boxItemCriteria.getLoginPersonCardNo());

            }
            /*AccountAlias accountAlias = accountAliasService.findByDispositionId(boxItemCriteria.getAccountAliasId());
            ItemInfo itemInfo = itemInfoService.findByItemCode(boxItemCriteria.getItemCode());
            SubInventory subInventory = subInventoryService.findById(1);
            Map<String,String> maps = new HashMap<>();
            List<Map<String,String>> listss = new ArrayList<>();
            maps.put("organizationId",subInventory.getOrganizationId().toString());
            maps.put("transactionTypeId",TaskTypeConstant.TRANSACTION_OUT);
            maps.put("transactionSourceName",accountAlias.getAccountAlias());
            maps.put("transactionSourceId",accountAlias.getDispositionId().toString());
            maps.put("inventoryItemId",itemInfo.getInventoryItemId().toString());
            maps.put("transactionQuantity",boxItemCriteria.getQuantitys().toString());
            maps.put("transactionDate",DateUtils.getDate());
            maps.put("transactionUom",itemInfo.getUnit());
//            maps.put("subinventoryCode",q);
//            maps.put("locatorId",q);
            maps.put("sourceCode",TaskTypeConstant.SOURCE);
            maps.put("source_header_id",TaskTypeConstant.SOURCE);
            maps.put("source_line_id",TaskTypeConstant.SOURCE);
            listss.add(maps);
            RequestId requestId = requestIdService.returnRequestId("WMS账户别名物料发放写入EBS接口");
            requestId.setType(1);
            //EBS库存扣减
            EbsBack entityStr = serverVisitAddressService.requestServerCode(requestId.getAutoGrowingId().toString(),
                    "",TaskTypeConstant.SYNCHRONOUS_EXECUTE,null,listss);
//			判断是否请求到参数
            if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
            }else{
                error="同步EBS失败";
                throw new RuntimeException();
            }*/
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE,error);
        }
        return ResultGenerator.genSuccessResult(count+1);
    }

    /**
     * 非工单出库
     */
    @PostMapping("/nonWorkerOrderOutFinish")
    @ResponseBody
    @Transactional
    public Result nonWorkerOrderOutFinish(@RequestBody BoxItemCriteria boxItemCriteria) {
        String error = "";
        try {
            CellInfoDto cellInfoDto = cellInfoService.getBestCell();
            BoxItem boxItem = boxItemService.getBoxItemByBoxCode(boxItemCriteria.getBoxCode());
            boxItem.setQuantity(boxItemCriteria.getQuantitys() - boxItemCriteria.getQuantity());
            boxItemService.update(boxItem);
            BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxItem.getBoxCode());
            boxInfo.setBoxState(2);
            boxInfo.setBoxCellId(cellInfoDto.getCellId());
            boxInfoService.update(boxInfo);
            TaskInfo taskInfo = new TaskInfo(
                    null, new GuidUtils().toString(), null,
                    MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(), cellInfoDto.getSColumn(), cellInfoDto.getSRow()),
                    2, 0, boxItem.getQuantity(), boxItem.getBoxCode());
            taskInfo.setCompleteQuantity(boxItem.getQuantity());
            taskInfo.setCardNo(boxItemCriteria.getLoginPersonCardNo());
            taskInfo.setBarCode(MyUtils.connectPrintString(boxItem.getItemCode(),boxItem.getQuantity(),boxItem.getExp(),boxItem.getBatch(),itemInfoService.findByItemCode(boxItem.getItemCode()).getItemName()));
            taskInfo.setTaskStartTime(DateUtils.getTime());
            taskInfoService.save(taskInfo);
            cellInfoService.updateCellInfoState(cellInfoDto,2);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE,error);
        }
        return ResultGenerator.genSuccessResult();
    }
}