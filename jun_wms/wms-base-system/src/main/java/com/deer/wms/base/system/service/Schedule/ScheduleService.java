package com.deer.wms.base.system.service.Schedule;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.bill.*;
import com.deer.wms.base.system.model.box.*;
import com.deer.wms.base.system.model.task.*;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import com.deer.wms.base.system.model.ware.WareInfo;
import com.deer.wms.base.system.service.*;
import com.deer.wms.base.system.service.bill.IBillInDetailService;
import com.deer.wms.base.system.service.bill.IBillInMasterService;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.mailServer.MailService;
import com.deer.wms.base.system.service.task.ITaskInfoService;

import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IWareInfoService;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.framework.util.MyUtils;
import com.deer.wms.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduleService{
    @Autowired
    private IBillOutDetailService billOutDetailService;
    @Autowired
    private IBillOutMasterService billOutMasterService;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private WorkerOrderIssueTimeService workerOrderIssueTimeService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private PickTaskService pickTaskService;
    @Autowired
    private SubinventoryTransferRecordService subinventoryTransferRecordService;
    @Autowired
    private SluggishOverdueService sluggishOverdueService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private IWareInfoService wareInfoService;
    @Autowired
    private IBillInMasterService billInMasterService;
    @Autowired
    private IBillInDetailService billInDetailService;
    @Autowired
    private BillInRecordService billInRecordService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private BillInCheckRecordService billInCheckRecordService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;
    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private SubInventoryService subInventoryService;


    /**
     * 定时下发工单任务
     */
    /*@Scheduled(cron = "0 0/1 * * * ? ")
    private void MesScheduledWorkerOrder(){
        try {
            WorkerOrderIssueTime workerOrderIssueTime = workerOrderIssueTimeService.findById(1);
            String time = DateUtils.getNowTime();
//        String nowStr = timeCalculate();
            if (workerOrderIssueTime.getAutoExecute().equals(1)) {
                if (time.equals(workerOrderIssueTime.getFirstTime())) {
                    timeCalculate();
                } else if (time.equals(workerOrderIssueTime.getSecondTime())) {
                    timeCalculate();
                } else if (time.equals(workerOrderIssueTime.getThirdTime())) {
                    timeCalculate();
                } else if (time.equals(workerOrderIssueTime.getFourthTime())) {
                    timeCalculate();
                } else if (time.equals(workerOrderIssueTime.getFifthTime())) {
                    timeCalculate();
                } else if (time.equals(workerOrderIssueTime.getSixthTime())) {
                    timeCalculate();
                }
            }
            *//*System.out.println("鸹貔"+ time + "   "  +nowStr);*//*
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    /*private void timeCalculate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        PickTaskCriteria criteria = new PickTaskCriteria();
        criteria.setStartTime(time);
        criteria.setEndTime(DateUtils.getTime());
        criteria.setState(0);
        criteria.setBillOutMasterType(1);
        criteria.setWorkOrderStockState(1);
        criteria.setPickState(1);
        System.out.println(time+"  "+DateUtils.getTime());
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
        System.out.println("下发完成");
    }*/

    /**
     * 每天检测物料是否过期，过期自动转移物料至过期库
     */
//    @Scheduled(cron = "0 41 0 1/1 * ? ")
    private void transferOverdue(){
//        System.out.println("铁憨憨");
        try {
            WareInfo wareInfo = wareInfoService.findById(212);
            BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
            boxItemCriteria.setWillOverdueDay(wareInfo.getStockWaring());
            List<BoxItemDto> boxItemDtos = boxItemService.findWillOverdue(boxItemCriteria);
            int count = 1;
            List<OverdueList> overdueLists = new ArrayList<>();
            OverdueList overdueList = null;
            PickTaskCriteria pickTaskCriteria = new PickTaskCriteria();
            if(boxItemDtos.size()>0){
                //定义传给EBS的集合
                List<Map<String, String>> lists = new ArrayList<>();
                //获取转移到的子库信息
                SubInventory subInventory = subInventoryService.findById(TaskTypeConstant.OVER_DUE);
                //获取自增长请求Id
                RequestIdAuto requestIdAuto = requestIdAutoService.backAutoId("WMS转库数据写入EBS接口");
                //定义请求接口记录集合
                List<RequestId> requestIds = new ArrayList<>();
                for(BoxItemDto boxItemDto : boxItemDtos){
                    overdueList = new OverdueList(count,boxItemDto.getItemCode(),boxItemDto.getItemName(),boxItemDto.getSupplierName(),
                            boxItemDto.getPd(),boxItemDto.getBatch(),boxItemDto.getUnit(),boxItemDto.getQuantity());
                    if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.QUALIFIED)){
                        overdueList.setExp(boxItemDto.getExp());
                    }else if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.POSTPONE)){
                        overdueList.setPostpone(boxItemDto.getExp());
                    }
                    overdueLists.add(overdueList);
                    if(boxItemDto.getSurplusDay()<1){
                        boxItemCriteria.setItemCode(boxItemDto.getItemCode());
                        boxItemCriteria.setExp(boxItemDto.getExp());
                        boxItemCriteria.setBatch(boxItemDto.getBatch());
                        List<BoxItemDto> boxItems = boxItemService.findBoxItemList(boxItemCriteria);
                        if(boxItems.size()>0) {
                            for (BoxItem boxItem : boxItems) {
                                pickTaskCriteria.setBoxCode(boxItem.getBoxCode());
                                pickTaskCriteria.setPickState(1);
                                List<PickTaskDto> pickTaskDtos = pickTaskService.findList(pickTaskCriteria);
                                for (PickTaskDto pickTaskDto : pickTaskDtos) {
                                    BillOutMaster billOutMaster = billOutMasterService.selectBillOutMasterByBillOutDetailId(pickTaskDto.getBillOutDetailId());
                                    billOutMaster.setState(3);
                                    billOutMasterService.update(billOutMaster);
                                    pickTaskDto.setPickState(5);
                                    pickTaskService.update(pickTaskDto);
                                }
                                SubinventoryTransferRecord subinventoryTransferRecord = new SubinventoryTransferRecord(
                                        boxItem.getBoxCode(), boxItem.getItemCode(), boxItem.getBatch(),  boxItem.getQuantity(),
                                        DateUtils.getTime(), TaskTypeConstant.VIRTUAL_CARD_NO, boxItem.getSubInventoryId(),
                                        TaskTypeConstant.OVER_DUE, "过期"
                                );
                                //往传给EBS的集合添加数据
                                lists.add(MyUtils.subInventoryTransfer(TaskTypeConstant.SUB_INVENTORY_TRANSFER_TYPE, subInventory.getOrganizationId().toString(),
                                        boxItemDto.getInventoryItemId().toString(), boxItemDto.getQuantity().toString(), boxItemDto.getSubInventoryCode(),
                                        boxItemDto.getSlotting() == null ? null : boxItemDto.getSlotting(), MyUtils.getNinetySecondsAgo(), boxItemDto.getUnit(),
                                        subInventory.getSubInventoryCode(), subInventory.getSlotting() == null ? null : subInventory.getSlotting(),
                                        boxItemDto.getBatch(), boxItemDto.getBillId().toString(), boxItemDto.getBillInDetailId().toString()));
                                //往请求记录的集合中添加数据
                                RequestId requestId = new RequestId(requestIdAuto.getAutoId(), boxItemDto.getInventoryItemId(), boxItemDto.getQuantity(),
                                        boxItemDto.getBatch(), DateUtils.getTime(), "WMS调用EBS子库转移接口失败", boxItemDto.getSubInventoryCode(),
                                        boxItemDto.getSlotting() == null ? null : Integer.parseInt(boxItemDto.getSlotting()), subInventory.getOrganizationId(), TaskTypeConstant.TRANSFER,
                                        TaskTypeConstant.FAIL_WAIT_MANAGE, TaskTypeConstant.SUB_INVENTORY_TRANSFER_TYPE, boxItemDto.getUnit(),
                                        subInventory.getSubInventoryCode(), subInventory.getSlotting() == null ? null : Integer.parseInt(subInventory.getSlotting()),
                                        boxItemDto.getBillId(), boxItemDto.getBillInDetailId(), "ERROR");
                                requestIds.add(requestId);
                                subinventoryTransferRecordService.save(subinventoryTransferRecord);
                                boxItem.setSubInventoryId(TaskTypeConstant.OVER_DUE);
                                boxItemService.update(boxItem);
                            }
                        }
                    }
                }
                //EBS子库转移
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

                ExcelUtil<OverdueList> util = new ExcelUtil<OverdueList>(OverdueList.class);
                mailService.analysisSendMail(util.exportExcel(overdueLists, "过期清单"),"过期清单","附件为过期清单，请查收!!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 定时每月呆滞过期报表
     */
//    @Scheduled(cron = "0 21 0 1/1 * ? ")
//    @Scheduled(cron = "59 19 9 * * ? ")
    @Async
    public void sluggishOverdue(){
//        System.out.println("铁憨憨");
        try {
            WorkerOrderIssueTime workerOrderIssueTime = workerOrderIssueTimeService.findById(1);
            if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == workerOrderIssueTime.getSluggishExportDate()){
                BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
                boxItemCriteria.setSluggishExportParam(workerOrderIssueTime.getSluggishExportParam());
                List<BoxItemDto> boxItems = boxItemService.findSluggishOverdue(boxItemCriteria);
                if(boxItems.size()>0){
                    //上次申报数量
                    Integer lastQuantity = 0;
                    String firstDeclareTime = null;
                    Integer firstDeclareQuantity = 0;
                    String lastOutTime = null;
                    SluggishOverdueCriteria sluggishOverdueCriteria = new SluggishOverdueCriteria();
                    TaskInfoCriteria taskInfoCriteria = new TaskInfoCriteria();
                    List<Integer> lists = new ArrayList<>();
                    lists.add(TaskTypeConstant.CELL_TO_OPERATOR_FLOOR);
                    lists.add(TaskTypeConstant.CELL_TO_PAPER_COUNTERS);
                    taskInfoCriteria.setTypes(lists);
                    for(BoxItem boxItem : boxItems){
                        sluggishOverdueCriteria.setItemCode(boxItem.getItemCode());
                        sluggishOverdueCriteria.setBatch(boxItem.getBatch());
                        sluggishOverdueCriteria.setExp(boxItem.getExp());
                        List<SluggishOverdue> sluggishOverdueOne = sluggishOverdueService.findSluggishByParam(sluggishOverdueCriteria);
                        taskInfoCriteria.setItemCode(boxItem.getItemCode());
                        taskInfoCriteria.setBatch(boxItem.getBatch());
                        taskInfoCriteria.setExp(boxItem.getExp());
                        TaskInfo taskInfo = taskInfoService.findByItemCodeAndBatchAndExp(taskInfoCriteria);
                        if(taskInfo != null){
                            lastOutTime = taskInfo.getTaskStartTime();
                        }
                        if(sluggishOverdueOne.size() == 1){
                            lastQuantity = sluggishOverdueOne.get(0).getQuantity();
                            firstDeclareQuantity = sluggishOverdueOne.get(0).getQuantity();
                            firstDeclareTime = sluggishOverdueOne.get(0).getCreateTime();
                        }else if(sluggishOverdueOne.size()>1){
                            lastQuantity = sluggishOverdueOne.get(sluggishOverdueOne.size()-1).getQuantity();
                            firstDeclareQuantity = sluggishOverdueOne.get(0).getQuantity();
                            firstDeclareTime = sluggishOverdueOne.get(0).getCreateTime();
                        }
                        SluggishOverdue sluggishOverTwo = new SluggishOverdue(boxItem.getItemCode(),boxItem.getBatch(),
                                lastQuantity,boxItem.getQuantity(),boxItem.getPd(),boxItem.getExp(),boxItem.getInTime(),
                                DateUtils.getTime(),lastOutTime,workerOrderIssueTime.getSluggishExportParam(),
                                firstDeclareTime,firstDeclareQuantity);
                        sluggishOverdueService.save(sluggishOverTwo);
                    }
                }
                List<SluggishOverdueDto> sluggishOverdues = sluggishOverdueService.findList(new SluggishOverdueCriteria(DateUtils.getDate()));
                ExcelUtil<SluggishOverdueDto> util = new ExcelUtil<SluggishOverdueDto>(SluggishOverdueDto.class);
                mailService.analysisSendMail(util.exportExcel(sluggishOverdues, "呆滞过期报表"),"呆滞过期报表","附件为呆滞过期报表，请查收!!");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String args[]){
        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        System.out.println("鸹貔"+ DateUtils.getDate() );*/
        //获取当前几号
//        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        System.out.print(Long.valueOf(DateUtils.dateTimeNow()));

    }
}
