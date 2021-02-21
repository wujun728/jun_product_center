package com.deer.wms.base.system.web.task;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.task.*;
import com.deer.wms.base.system.model.ware.*;
import com.deer.wms.base.system.service.*;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.base.system.service.bill.IBillInDetailService;
import com.deer.wms.base.system.service.bill.IBillInMasterService;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.base.system.service.mailServer.MailService;
//import com.deer.wms.base.system.service.rabbitMQ.MsgProducer;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IDoorService;
import com.deer.wms.base.system.service.ware.IShelfInfoService;
import com.deer.wms.base.system.service.ware.ISupplierService;
import com.deer.wms.base.system.service.webSocket.WebSocketServer;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.framework.util.MyUtils;
import com.deer.wms.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 任务 信息操作处理
 *
 * @author guo
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/in/taskInfo")
public class TaskInfoController extends BaseController {
    private String prefix = "task";

    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private IBillInMasterService billInMasterService;
    @Autowired
    private IBillInDetailService billInDetailService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IShelfInfoService shelfInfoService;
    @Autowired
    private IItemInfoService itemInfoService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private IDoorService doorService;
    @Autowired
    private IBillOutDetailService billOutDetailService;
    @Autowired
    private IBillOutMasterService billOutMasterService;
    @Autowired
    private CarrierService carrierService;
    @Autowired
    private PickTaskService pickTaskService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SluggishOverdueService sluggishOverdueService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SubInventoryService subInventoryService;
    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;
    @Autowired
    private WorkerOrderIssueTimeService workerOrderIssueTimeService;
    @Autowired
    private InventoryCheckService inventoryCheckService;
    @Autowired
    private WarnInformationService warnInformationService;
    @Value("${deer.profile}")
    private String from;

   /* @Autowired
    private MsgProducer msgProducer;*/

    private long time = 0;
     /**
     * 用于接收WCS执行任务后返回的信息  并更新任务表中的状态值
     *
     * @param taskId 任务id
     * @param state  任务完成状态 2-报错   3-完成
     * @return
     */
    @ApiOperation("给WCS完成任务后回调此接口并传回任务id与任务状态")
    @ResponseBody
    @GetMapping("/getWcsMessage")
    public Result getWcsMessage(String taskId, Integer state) throws Exception{

        /*SystemParams systemParams = new SystemParams("1","PCB_APS","APS","ESB",
                "getInvItemInfosForPcbAps",1,1L, "S","1.0","fd");
        BaseQueryParams2 baseQueryParams = new BaseQueryParams2(87,"2018-09-26","2019-09-26",1,50);
        List<Map<String, String>> lists = new ArrayList<>();
        EbsBack entityStr = serverVisitAddressService.requestServerCode(systemParams, baseQueryParams, lists);
        ServerVisitAddress serverVisitAddress = serverVisitAddressService.findById(11);
        System.out.println("铁汉和那");
        WebserviceResponse webserviceResponse = null;
        List<Door> lists = doorService.selectDoorList(new Door(null,null,null,2,null,null));
        Carrier carrier = carrierService.findFirstCarrier();
        String code = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<StockWipOutReq\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                "macCode=\"" + lists.get(0).getCode() + "\" \n" +
                "wipEntity =\"" + carrier.getCode() + "\"\n" +
                "berthCode=\"" + lists.get(0).getAddressCode() + "\" \n" +
                "taskCode=\"" + new GuidUtils().toString() + "\">\n" +
                "<item tagCode=\""+lists.get(0).getCode()+"_1000\" tagValue=\"" + 123456 + "\" timeStamp=\"" + DateUtils.dateTimeNow() + "\" />\n" +
                "<item tagCode=\""+lists.get(0).getCode()+"_1001\" tagValue=\"" + 33 + "\" timeStamp=\"" + DateUtils.dateTimeNow() + "\" />\n" +
                "<item tagCode=\""+lists.get(0).getCode()+"_1002\" tagValue=\"" + 1 + "\" timeStamp=\"" + DateUtils.dateTimeNow() + "\" />\n" +
                "<item tagCode=\""+lists.get(0).getCode()+"_1003\" tagValue=\"" + carrier.getCarrierCode() + "\" timeStamp=\"" + DateUtils.dateTimeNow() + "\" /> " +
                "<item tagCode=\""+lists.get(0).getCode()+"_1004\" tagValue=\"" + 12532 + "\" timeStamp=\"" + DateUtils.dateTimeNow() + "\" />\n" +
                "</StockWipOutReq>";
        webserviceResponse = serverVisitAddressService.requestMesServer("StockWipOutReq",code);
        if(webserviceResponse.getErrorCode().equals("0")){
            carrier.setCarrierState(2);
            carrierService.update(carrier);
        }
        System.out.println(webserviceResponse.getErrorCode());
        try {
            List<SluggishOverdueDto> list = sluggishOverdueService.findList();
            List<SluggishOverdueDto> sluggishOverdues = sluggishOverdueService.findList(new SluggishOverdueCriteria(DateUtils.getDate()));
            ExcelUtil<SluggishOverdueDto> util = new ExcelUtil<SluggishOverdueDto>(SluggishOverdueDto.class);
            Object a  = AjaxResult.success(util.exportExcel(sluggishOverdues, "sluggishOverdue"));
            AjaxResult ajaxResult = util.exportExcel(sluggishOverdues, "呆滞过期报表");
            mailService.analysisSendMail(ajaxResult,"WMS测试邮件，请忽略！","附件为呆滞过期报表，请查收!!");
            JSONObject jsonObject = (JSONObject) JSON.toJSON(a);
            String c = jsonObject.getJSONObject("data").getString("msg");
            mailService.sendAttachmentsMail("呆滞过期报表","附件为呆滞过期报表，请查收!!",from+"\\download\\"+c);
        }catch(Exception e){

        }
        Map<String,String> map = new HashMap();
        map.put("msg","报警");
        map.put("type","");
        WebSocketServer.sendInfo("报警！","1");
        WebSocketServer.sendObject(map,null);
        PickTaskCriteria criteria = new PickTaskCriteria();
        criteria.setBillOutDetailId(1);
        criteria.setPickState(2);
        criteria.setBoxCode("FTB0001");
        List<PickTaskDto> pickTaskDtos = pickTaskService.findByState(criteria);
        taskInfoService.finishTask(taskId, state);*/
        try {

//            new MsgProducer(rabbitTemplate).sendMsg(taskId);
            /*msgProducer.sendMsg(taskId);*/
        }catch(Exception e){

        }finally{

        }
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("给WCS获得任务列表")
    @ResponseBody
    @GetMapping("/getTaskinfoForWcs")
    @Async
    public List<TaskInfoWcs> selectTaskInfoForWcs() {
//        List<TaskInfoWcs> taskInfoWcs = null;
        try {
//            taskInfoWcs = taskInfoService.selectTaskInfoForWcsByState();
        /*List<TaskInfoWcs> taskInfoWcss = null;
        if (taskInfos != null) {
            taskInfoWcss = new ArrayList<>();
            TaskInfoWcs taskInfoWcs = null;
            for (TaskInfo taskInfo : taskInfos) {
                taskInfoWcs = new TaskInfoWcs();
                //taskInfoService.updateTaskInfo(task);
                taskInfoWcs.setTaskNo(taskInfo.getTaskId());
                taskInfoWcs.setFromStation(taskInfo.getStartPosition());
                taskInfoWcs.setToStation(taskInfo.getEndPosition());
                taskInfoWcs.setType(taskInfo.getType().toString());
                taskInfoWcs.setState(taskInfo.getState().toString());
                taskInfoWcs.setLevel(taskInfo.getIsTop());
                taskInfoWcs.setBarcode(taskInfo.getBarCode());
                taskInfoWcs.setNumber(taskInfo.getQuantity());
                taskInfoWcs.setCreateTime(DateUtils.getTime());
                taskInfoWcss.add(taskInfoWcs);
            }
        }*/
        if(time != 0 && (time+1000L) <= System.currentTimeMillis()){
            time = System.currentTimeMillis();
            System.out.println(DateUtils.getTime());
            return taskInfoService.selectTaskInfoForWcsByState();
        }else if(time == 0){
            time = System.currentTimeMillis();
        }
        }catch(Exception e){

        }finally{
//            taskInfoWcs = null;
        }
        return null;
    }

    @ApiOperation("方便在出库单页面遍历任务列表")
    @ResponseBody
    @RequestMapping("/getTaskinfoByBillOutMasterId")
    public Result selectTaskinfoByBillOutMasterId(Integer billId) {

        List<TaskInfo> taskInfos = taskInfoService.selectTaskInfoByBillOutMasterId(billId);

        return ResultGenerator.genSuccessResult(taskInfos);
    }

    @ApiOperation("方便在入库单页面遍历任务列表")
    @ResponseBody
    @RequestMapping("/getTaskinfoByBillInMasterId")
    public Result selectTaskinfoByBillInMasterId(Integer billId) {
        List<TaskInfo> taskInfos = taskInfoService.selectTaskInfoByBillInMasterId(billId);
        return ResultGenerator.genSuccessResult(taskInfos);
    }

    @RequiresPermissions("in:task:view")
    @GetMapping()
    public String taskInfo() {
        return prefix + "/taskInfo";
    }

    /**
     * 查询任务列表
     */
    @PostMapping("/findList")
    @ResponseBody
    public TableDataInfo findList(TaskInfoCriteria taskInfoCriteria) {
        startPage();
        List<TaskInfoDto> list = taskInfoService.findList(taskInfoCriteria);
        return getDataTable(list);
    }
    /**
     * 查询任务列表
     */
    @RequiresPermissions("in:task:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TaskInfo taskInfo) {
        startPage();
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @RequiresPermissions("in:task:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaskInfo taskInfo) {
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        ExcelUtil<TaskInfo> util = new ExcelUtil<TaskInfo>(TaskInfo.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 新增任务
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存任务
     */
    @RequiresPermissions("in:task:add")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TaskInfo taskInfo) {
        return toAjax(taskInfoService.insertTaskInfo(taskInfo));
    }

    /**
     * 修改任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        TaskInfo taskInfo = taskInfoService.selectTaskInfoById(id);
        mmap.put("task", taskInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务
     */
    @RequiresPermissions("in:task:edit")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TaskInfo taskInfo) {
        return toAjax(taskInfoService.updateTaskInfo(taskInfo));
    }

    /**
     * 删除任务
     */
    @RequiresPermissions("in:task:remove")
    @Log(title = "任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(taskInfoService.deleteTaskInfoByIds(ids));
    }


    /**
     * 修改保存任务
     */
    @RequiresPermissions("in:task:edit")
    @Log(title = "入库任务", businessType = BusinessType.INSERT)
    @PostMapping("/inWareTaskSave")
    @ResponseBody
    public Result inWareTaskSave(InTask inTask) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setBillInDetailId(inTask.getBillInDetailId());
        taskInfo.setState(0);
        taskInfo.setType(2);
        taskInfo.setBoxCode(inTask.getBoxCode());
        taskInfo.setTaskId(new GuidUtils().toString());
        taskInfo.setStartPosition("0.0");
        taskInfo.setStartPosition(cellInfoService.getPositionByCellId(inTask.getCellId()));
        taskInfoService.save(taskInfo);

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 叫托盘(入料口出半框或者出空框)
     */
    @PostMapping("/getBox")
    @ResponseBody
    @Transactional
    public Result getBox(@RequestBody TaskInfoCriteria criteria) {
        String message = "";
        Integer whetherNullBox = 0;
        try {
            if(criteria.getWhetherNullBox().equals(1)){
                message = cellInfoService.findOutBox(1002,null,null,null,criteria.getLoginPersonCardNo());
            }else {
                message = cellInfoService.findOutBox(1001,criteria.getItemCode(),criteria.getBatch(),criteria.getExp(),criteria.getLoginPersonCardNo());
            }
            if(!message.equals("error")){
                BoxItem boxItem = boxItemService.getBoxItemByBoxCode(message);
                whetherNullBox = boxItem.getQuantity();
            }else{
                return ResultGenerator.genFailResult(CommonCode.NOT_NULL_PALLET);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }
        return ResultGenerator.genSuccessResult(whetherNullBox);
    }

    /**
     * 入空框
     */
    @PostMapping("/inNullBox")
    @ResponseBody
    @Transactional
    public Result inNullBox(@Param("boxCode") String boxCode,@Param("loginPersonCardNo") String loginPersonCardNo) {
        String error = "";
        try {
            String msg = cellInfoService.inNullBox(boxCode,loginPersonCardNo);
            if(!msg.equals("success")){
                error = "无可用货位";
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.PARAMETER_ERROR, error);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 当堆垛机放托盘完成任务时调用此接口
     */
    @PostMapping("/updateTaskInfoState")
    @ResponseBody
    @Transactional
    public Result updateTaskInfoState(HttpServletRequest request) {
        String wholeStr = null;
        JSONArray jsonArrays = null;
        try {
            /*BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }*/
            wholeStr = MyUtils.analysisHttpServletRequest(request);
            jsonArrays = JSONArray.parseArray(wholeStr);
            for(int i=0;i<jsonArrays.size();i++){
                com.alibaba.fastjson.JSONObject jsonObject = jsonArrays.getJSONObject(i);
                String taskId = jsonObject.get("TaskNo").toString().trim();
                Integer state = Integer.parseInt(jsonObject.get("State").toString().trim());
                Integer quantity = MyUtils.backInteger(jsonObject.get("quantityResponse"));
                String barCode = MyUtils.backString(jsonObject.get("BarCode"));
//                System.out.println(wholeStr);
//                System.out.println(taskId+"a   a"+state+"a   a"+quantity);
                TaskInfo taskInfo = taskInfoService.getTaskInfoByTaskId(taskId);
                if (taskInfo != null) {
                    taskInfo.setState(state);
                    //获得该任务的类型(可参考数据库注释)
                    int type = taskInfo.getType();
                    //1-入库任务(将空/半空托盘从货位上移到固定位置)完成
                    if (state == 3 && type == TaskTypeConstant.CELL_TO_OPERATOR_FLOOR) {
                        taskInfo.setTaskEndTime(DateUtils.getTime());
                        //将货位状态改成无货
                        updateCellInfoStateOne(0,taskId);
                        //将托盘与货位解绑
                        updateBoxInfoState(0,taskInfo.getBoxCode());
                    }
                    //2-入空箱
                    else if (state == 3 && type == TaskTypeConstant.IN_NULL_BOX) {
                        //将货位状态改成0
                        taskInfo.setTaskEndTime(DateUtils.getTime());
                        updateCellInfoStateOne(1, taskId);
                        //将容器表中的boxCellId改为cellId
                        BoxInfo boxInfo = boxInfoService.getBoxInfoByTaskId(taskId);
                        //设置托盘状态为1，托盘进入货位
                        boxInfo.setBoxState(1);
                        boxInfoService.update(boxInfo);
                    }
                    //3-入有货箱
                    else if (state == 3 && type == TaskTypeConstant.IN_AVAILABLE_BOX) {
                        //将货位状态改成0
                        updateCellInfoStateOne(1, taskId);
                        //将容器表中的boxCellId改为cellId
                        BoxInfo boxInfo = boxInfoService.getBoxInfoByTaskId(taskId);
                        //设置托盘状态为1，托盘进入货位
                        boxInfo.setBoxState(1);
                        boxInfoService.update(boxInfo);
                    }
                    //11-出库任务完成(货位出库到点数机)
                    else if (state == 3 && type == TaskTypeConstant.CELL_TO_PAPER_COUNTERS) {
                        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(taskInfo.getBoxCode());
                        CellInfo cellInfo = cellInfoService.findById(boxInfo.getBoxCellId());
                        cellInfoService.updateCellInfoState(cellInfo,0);
                        boxInfo.setBoxCellId(null);
                        boxInfo.setBoxState(0);
                        boxInfoService.updateBoxInfo(boxInfo);
                        PickTaskCriteria criteria = new PickTaskCriteria();
                        criteria.setBillOutDetailId(taskInfo.getBillOutDetailId());
                        criteria.setPickState(2);
                        criteria.setBoxCode(taskInfo.getBoxCode());
                        List<PickTaskDto> pickTaskDtos = pickTaskService.findByState(criteria);
                        if(pickTaskDtos.size()>0){
                            //12-下发点数任务
                            PickTaskDto pickTaskDto = pickTaskDtos.get(0);
                            updatePickTaskState(pickTaskDto);
                            TaskInfo count = new TaskInfo(new GuidUtils().toString(), taskInfo.getStartPosition(),
                                    null, TaskTypeConstant.COUNT_TO_CARRIER, 0, pickTaskDto.getPickQuantity(),
                                    pickTaskDto.getBoxCode(), pickTaskDto.getPriority(), pickTaskDto.getBillOutDetailId());
                            taskInfoService.save(count);
                        }
                    }
                    //12类型 2状态 点数任务数量大于实际箱数量
                    /*//查询当前出库箱是否有相同物料的
                    if (state == 2 && type == TaskTypeConstant.COUNT_TO_CARRIER) {
                        BoxItem boxItem = boxItemService.getBoxItemByBoxCode(taskInfo.getBoxCode());
                        BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
                        boxItemCriteria.setBoxState(2);
                        boxItemCriteria.setCellState(2);
                        boxItemCriteria.setWorkOrderStockState(1);
                        boxItemCriteria.setItemCode(boxItem.getItemCode());
                        List<BoxItemDto> boxItemDtos = boxItemService.workerOrderLackOut(boxItemCriteria);
                        String message = "";
                        WarnInformation warnInformation = null;
                        if(boxItemDtos.size()>0){
                            message = "物料编码为"+boxItem.getItemCode()+"的物料,缺少"
                                    +(taskInfo.getQuantity()-quantity)+"张，此物料当前有其他箱等待出库中";
                            warnInformation = new WarnInformation(message,1);
                        }else{
                            boxItemCriteria.setBoxState(1);
                            boxItemCriteria.setCellState(1);
                            boxItemCriteria.setWorkOrderStockState(0);
                            boxItemCriteria.setHasGood(1);
                            List<BoxItemDto> boxItemDtos1 = boxItemService.workerOrderLackOut(boxItemCriteria);
                            if(boxItemDtos1.size()>0){

                            }else{
                                message = "物料编码为"+boxItem.getItemCode()+"的物料已无库存";
                                warnInformation = new WarnInformation(message,2);
                            }
                        }
                        WebSocketServer.sendInfo(message+",出库单Id为"+taskInfo.getBillOutDetailId(),null);
                        warnInformationService.save(warnInformation);
                    }*/
                    //12-点数任务完成
                    else if (state == 3 && type == TaskTypeConstant.COUNT_TO_CARRIER) {
                        taskInfo.setCompleteQuantity(quantity);
                        /*if(!taskInfo.getQuantity().equals(quantity)){
                            List<PickTaskDto> pickTaskDtos = pickTaskService.findList(new PickTaskCriteria(taskInfo.getBillOutDetailId(), taskInfo.getBoxCode()));
                            PickTaskDto pickTaskDto = pickTaskDtos.get(0);
                            pickTaskDto.setPickQuantity(quantity);
                            pickTaskService.update(pickTaskDto);
                            InventoryCheck inventoryCheck = new InventoryCheck(taskInfo.getBoxCode(),null,taskInfo.getQuantity(),
                                    quantity,quantity-taskInfo.getQuantity(),1,3,pickTaskDto.getItemCode(),pickTaskDto.getBatch(),
                                    taskInfo.getBillOutDetailId());
                            inventoryCheckService.save(inventoryCheck);
//                            WebSocketServer.sendInfo("当前箱数量不满足点数任务数量，自动生成盘亏单，请等此工单点数完成后前往指定货位拿取相应数量物料",);
                        }*/
                        PickTaskCriteria criteria = new PickTaskCriteria();
                        criteria.setBillOutDetailId(taskInfo.getBillOutDetailId());
                        criteria.setPickState(2);
                        List<PickTaskDto> pickTaskDtos = pickTaskService.findByState(criteria);
                        if(pickTaskDtos.size()>0) {
                            //13-点数机中托盘寻找合适的货位返回
                            CellInfoDto cellInfoDto = cellInfoService.getBestCell();
                            cellInfoService.updateCellInfoState(cellInfoDto,2);
                            updateBoxIndo(taskInfo.getBoxCode(),cellInfoDto.getCellId());
                            TaskInfo boxBackCellInfo = new TaskInfo(new GuidUtils().toString(), null,MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(),cellInfoDto.getSColumn(),cellInfoDto.getSRow()),
                                    TaskTypeConstant.BOX_TO_CELL_FROM_PAPER_COUNTERS, 0, null, taskInfo.getBoxCode(), taskInfo.getIsTop(), taskInfo.getBillOutDetailId());
                            taskInfoService.save(boxBackCellInfo);
                        }
                        else{
                            /*InventoryCheck inventoryCheck = inventoryCheckService.findByBillOutDetailAndType(new InventoryCheckCriteria(taskInfo.getBillOutDetailId(),1));
                            if(inventoryCheck != null && inventoryCheck.getCheckQuantity() < 0){
                                BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
                                boxItemCriteria.setItemCode(inventoryCheck.getItemCode());
                                boxItemCriteria.setQuantity(inventoryCheck.getQuantity());
                                boxItemCriteria.setWorkOrderStockState(0);
                                boxItemCriteria.setBoxState(1);
                                boxItemCriteria.setCellState(1);
                                List<BoxItemDto> boxItemDtos = boxItemService.workerOrderLackOut(boxItemCriteria);
                                BoxItemDto boxItemDto = boxItemDtos.get(0);
                                PickTask pickTask = new PickTask(boxItemDto.getBoxCode(),inventoryCheck.getCheckQuantity()*(-1),taskInfo.getBillOutDetailId(),
                                        4,boxItemDto.getBatch(),boxItemDto.getSubInventoryId());
                                pickTaskService.save(pickTask);
                                CellInfo cellInfo1 = cellInfoService.findById(boxItemDto.getBoxCellId());
                                cellInfoService.updateCellInfoState(cellInfo1,2);
                                BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxItemDto.getBoxCode());
                                boxInfo.setBoxState(2);
//                                boxInfo.setBoxCellId(TaskTypeConstant.EXCEPTION_CELL_ID);
                                boxInfoService.update(boxInfo);
                                CellInfoDto cellInfo2 = cellInfoService.findByCellId(TaskTypeConstant.EXCEPTION_CELL_ID);
                                TaskInfo cellToCellTask = new TaskInfo(new GuidUtils().toString(),
                                        MyUtils.connectShelfNameAndRowAndColumn(boxItemDto.getShelfName(),boxItemDto.getsColumn(),boxItemDto.getsRow()),
                                        MyUtils.connectShelfNameAndRowAndColumn(cellInfo2.getShelfName(), cellInfo2.getSColumn(),cellInfo2.getSRow()),
                                        TaskTypeConstant.CELL_TO_EXCEPTION_PROCESS_CELL,0,pickTask.getPickQuantity(),boxItemDto.getBoxCode(),"0",taskInfo.getBillOutDetailId());
                                cellToCellTask.setTaskStartTime(DateUtils.getTime());
                                taskInfoService.save(cellToCellTask);
                            }*/
                            //14-载具移动到到AVG出货口
                            TaskInfo outbound = new TaskInfo(new GuidUtils().toString(), null, null, TaskTypeConstant.CARRIER_TO_AVG_FROM_PAPER_COUNTERS, 0,
                                    quantity, taskInfo.getBoxCode(), taskInfo.getIsTop(), taskInfo.getBillOutDetailId());
                            taskInfoService.save(outbound);
                            criteria.setBillOutDetailId(null);
                            criteria.setBoxCode(taskInfo.getBoxCode());
                            List<PickTaskDto> pickTaskDtos1 = pickTaskService.findByState(criteria);
                            if(pickTaskDtos1.size() >0){
                                PickTaskDto pickTaskDto = pickTaskDtos1.get(0);
                                updatePickTaskState(pickTaskDto);
                                //根据当前点数机内的箱号，查询到此箱出料，12-下发点数任务
                                TaskInfo count = new TaskInfo(new GuidUtils().toString(), taskInfo.getStartPosition(), null, TaskTypeConstant.COUNT_TO_CARRIER, 0,
                                        pickTaskDto.getPickQuantity(), pickTaskDto.getBoxCode(), pickTaskDto.getPriority(), pickTaskDto.getBillOutDetailId());
                                taskInfoService.save(count);
                            }else{
                                //13-点数机中托盘寻找合适的货位返回
                                CellInfoDto cellInfoDto = cellInfoService.getBestCell();
                                cellInfoService.updateCellInfoState(cellInfoDto,2);
                                updateBoxIndo(taskInfo.getBoxCode(),cellInfoDto.getCellId());
                                TaskInfo boxBackCellInfo = new TaskInfo(new GuidUtils().toString(), null,MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(),cellInfoDto.getSColumn(),cellInfoDto.getSRow()),
                                        TaskTypeConstant.BOX_TO_CELL_FROM_PAPER_COUNTERS, 0, null, taskInfo.getBoxCode(), taskInfo.getIsTop(), taskInfo.getBillOutDetailId());
                                taskInfoService.save(boxBackCellInfo);
                            }
                        }
                    }
                    //13-点数机中托盘寻找合适的货位返回完成
                    else if (state == 3 && type == TaskTypeConstant.BOX_TO_CELL_FROM_PAPER_COUNTERS) {
                        int outQuantity = 0;
                        PickTaskCriteria pickTaskCriteria = new PickTaskCriteria();
                        pickTaskCriteria.setPickState(3);
                        pickTaskCriteria.setBoxCode(taskInfo.getBoxCode());
                        List<PickTaskDto> pickTaskDtos = pickTaskService.findList(pickTaskCriteria);
                        for(PickTaskDto pickTaskDto : pickTaskDtos){
                            pickTaskDto.setOutTime(DateUtils.getTime());
                            pickTaskDto.setPickState(4);
                            pickTaskService.update(pickTaskDto);
                            outQuantity += pickTaskDto.getPickQuantity();
                        }
                        //将货位状态改为0
                        updateCellInfoStateOne(1,taskId);
                        //将托盘表中绑定已放置的货位Id
                        BoxItem boxItem = boxItemService.getBoxItemByBoxCode(taskInfo.getBoxCode());
                        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(taskInfo.getBoxCode());
//                        boxInfo.setBoxCellId(null);
                        //剩余数量
                        Integer surplus = boxItem.getQuantity()-outQuantity <= 0 ? 0 : boxItem.getQuantity()-outQuantity;
                        Integer forecastStockQuantity = boxItem.getForecastStockQuantity() - outQuantity <= 0 ? 0 : boxItem.getForecastStockQuantity() - outQuantity;
                        if(surplus <= 0){
                            boxItem.setSubInventoryId(0);
                            boxItem.setQuantity(0);
                            boxItem.setForecastStockQuantity(0);
                            boxItem.setPd(null);
                            boxItem.setExp(null);
                            boxItem.setWorkOrderStockState(0);
                            boxInfo.setHasGoods(0);
                        }else{
                            boxItem.setQuantity(surplus);
                            boxItem.setForecastStockQuantity(forecastStockQuantity);
                            if(forecastStockQuantity<=0){
                                boxItem.setWorkOrderStockState(0);
                            }else {
                                boxItem.setWorkOrderStockState(1);
                            }
                            boxInfo.setHasGoods(1);
                        }
                        boxInfo.setBoxState(1);
                        boxInfoService.update(boxInfo);
                        boxItemService.update(boxItem);
                    }
                    taskInfoService.update(taskInfo);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }finally{
            wholeStr = null;
            jsonArrays = null;
        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 当载具到达AGV出库口时调用
     */
    @PostMapping("/updateTaskInfoTypeIsOutAGV")
    @ResponseBody
    @Transactional
    public String updateTaskInfoTypeIsSixState(HttpServletRequest request) {
        String wholeStr = null;
        JSONArray jsonArrays = null;
        try {
            wholeStr = MyUtils.analysisHttpServletRequest(request);
            jsonArrays = JSONArray.parseArray(wholeStr);
            for(int i=0;i<jsonArrays.size();i++){
                com.alibaba.fastjson.JSONObject jsonObject = jsonArrays.getJSONObject(i);
                String taskId = jsonObject.get("TaskNo").toString().trim();
                Integer state = Integer.parseInt(jsonObject.get("State").toString().trim());
                Integer quantity = jsonObject.get("Quantity")!=null?Integer.parseInt(jsonObject.get("Quantity").toString().trim()):null;
                TaskInfo taskInfo = taskInfoService.getTaskInfoByTaskId(taskId);
                    //6-出库任务（将有合适货物的载具调度到AGV）给MES传递接收载具指令(接口stockWipOutReq)完成
                if (taskInfo != null) {
                    taskInfo.setState(state);
                    //14-点数据出货到AGV并点数
                    if (state == 3 && taskInfo.getType() == TaskTypeConstant.CARRIER_TO_AVG_FROM_PAPER_COUNTERS) {
                        WebserviceResponse webserviceResponse = null;
                        List<Door> doors = doorService.selectDoorList(new Door(null, null, null, 2, null, null));
                        String time = DateUtils.getTime();
                        Carrier carrier = carrierService.findFirstCarrier();
//                        BoxItem boxItem = boxItemService.getBoxItemByBoxCode(task.getBoxCode());
                        TaskInfoCriteria criteria = new TaskInfoCriteria(taskInfo.getBillOutDetailId(),TaskTypeConstant.COUNT_TO_CARRIER,null);
                        List<TaskInfoDto> taskInfoDtos = taskInfoService.findList(criteria);
                        List<PickTaskDto> pickTaskDtos = pickTaskService.findByState(new PickTaskCriteria(taskInfo.getBillOutDetailId()));
                        String batch = pickTaskDtos.get(0).getBatch();
                        Integer completeQuantity = 0;
//                        for (TaskInfoDto taskInfoDto : taskInfoDtos) {
//                            completeQuantity += taskInfoDto.getCompleteQuantity();
//                        }
                        TaskInfoDto taskInfoDto = taskInfoDtos.get(0);
                        SubInventory subInventory = subInventoryService.findById(1);
                        RequestIdAuto requestIdAuto = requestIdAutoService.backAutoId("WMS工单发料写入EBS接口");
                        WorkerOrderIssueTime workerOrderIssueTime = workerOrderIssueTimeService.findById(1);
                        List<RequestId> requestIds = new ArrayList<>();
                        List<Map<String, String>> lists = new ArrayList<>();
                        for(PickTaskDto pickTaskDto :pickTaskDtos){
                            lists.add(MyUtils.wipOut(subInventory.getOrganizationId().toString(),TaskTypeConstant.MES_BILL_OUT,
                                    taskInfoDto.getBillNo(),taskInfoDto.getInventoryItemId().toString(),(pickTaskDto.getPickQuantity()*(-1))+"",
                                    workerOrderIssueTime.getOperationSeqnum()==null ?null:workerOrderIssueTime.getOperationSeqnum(),
                                    pickTaskDto.getBatch(),pickTaskDto.getSubInventoryCode(),pickTaskDto.getSlotting() == null ? "":pickTaskDto.getSlotting(),
                                    MyUtils.getNinetySecondsAgo(),taskInfoDto.getUnit()));

                            RequestId requestId = new RequestId(requestIdAuto.getAutoId(),taskInfoDto.getInventoryItemId(),pickTaskDto.getPickQuantity(),
                                    pickTaskDto.getBatch(),pickTaskDto.getSubInventoryCode(), pickTaskDto.getSlotting() == null ? null:Integer.parseInt(pickTaskDto.getSlotting()),
                                    subInventory.getOrganizationId(),TaskTypeConstant.MES_BILL_OUT,taskInfoDto.getBillNo(),workerOrderIssueTime.getOperationSeqnum(),
                                    DateUtils.getTime(),taskInfoDto.getUnit(), TaskTypeConstant.OUT,TaskTypeConstant.FAIL_WAIT_MANAGE,"调用WMS工单发料写入EBS接口失败","ERROR");
//                            requestIdService.save(requestId);
                            requestIds.add(requestId);

                            completeQuantity += pickTaskDto.getPickQuantity();
                        }

                        String code = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                "<StockWipOutReq\n" +
                                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                                "macCode=\"" + doors.get(0).getCode() + "\" \n" +
                                "wipEntity =\"" + taskInfoDto.getBillNo() + "\"\n" +
                                "berthCode=\"" + doors.get(0).getAddressCode() + "\" \n" +
                                "taskCode=\"" + taskInfo.getTaskId() + "\">\n" +
                                "<item tagCode=\"" + doors.get(0).getCode() + "_1000\" tagValue=\"" + taskInfoDto.getItemCode() + "\" timeStamp=\"" + time + "\" />\n" +
                                "<item tagCode=\"" + doors.get(0).getCode() + "_1001\" tagValue=\"" + completeQuantity + "\" timeStamp=\"" + time + "\" />\n" +
                                "<item tagCode=\"" + doors.get(0).getCode() + "_1002\" tagValue=\"" + taskInfoDto.getPriority() + "\" timeStamp=\"" + time + "\" />\n" +
                                "<item tagCode=\"" + doors.get(0).getCode() + "_1003\" tagValue=\"" + carrier.getCarrierCode() + "\" timeStamp=\"" + time + "\" /> " +
                                "<item tagCode=\"" + doors.get(0).getCode() + "_1004\" tagValue=\"" + batch + "\" timeStamp=\"" + time + "\" />\n" +
                                "</StockWipOutReq>";
                        carrier.setCode(code);
                        carrier.setTime(DateUtils.getTime());
                        circulation:
                        for(int j=0;j<4;j++) {
                            webserviceResponse = serverVisitAddressService.requestMesServer("StockWipOutReq", code);
                            if (webserviceResponse.getErrorCode().equals("-1")) {
                                if(j<3) {
                                    Thread.sleep(30 * 1000);
                                }else{
                                    WebSocketServer.sendInfo("出料口呼叫AGV失败，任务编号为"+taskInfo.getTaskId(),null);
                                }
                            } else {
                                carrier.setCarrierState(2);
                                carrierService.update(carrier);
                                EbsBack entityStr = serverVisitAddressService.requestServerCode(requestIdAuto.getAutoId().toString(),TaskTypeConstant.WMS_WIP_PROC,
                                        TaskTypeConstant.SYNCHRONOUS_EXECUTE,null, lists);
                                BillOutMaster billOutMaster = billOutMasterService.selectBillOutMasterByBillOutDetailId(taskInfo.getBillOutDetailId());
                                if(entityStr != null && entityStr.getSuccess().equals("true") &&entityStr.getTotal() > 0){

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
                                        RequestId requestId = new RequestId(requestIdAuto.getAutoId(),processStatus,lastUpdateDate,
                                                lastUpdatedBy,creationDate,createdBy,itemId,quantitys,lotNumber,id,transDate,
                                                errorMsg,subInventorys,locatorId,organizationId,TaskTypeConstant.OUT,transTypeId+"",
                                                wipEntityId+"",operationSeqNum,transUom);
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
                                billOutMaster.setState(2);
                                billOutMasterService.update(billOutMaster);
                                break circulation;
                            }
                        }
                    }
                    taskInfoService.update(taskInfo);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.SERVER_INERNAL_ERROR);
        }finally{
            wholeStr = null;
            jsonArrays = null;
        }
        return "";
    }

    /**
     * 修改托盘状态
     */
    public void updateBoxInfoState(Integer state,String boxCode){
        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxCode);
        boxInfo.setBoxCellId(null);
        boxInfo.setBoxState(state);
        boxInfoService.updateBoxInfo(boxInfo);
    }
    private void updateCellInfoStateOne(Integer state,String taskId){
        CellInfo cellInfo = cellInfoService.getCellInfoByTaskId(taskId);
        cellInfo.setState(state);
        cellInfoService.update(cellInfo);
    }

    /**
     *
     */
    private void updatePickTaskState(PickTaskDto pickTaskDto){
        pickTaskDto.setPickState(3);
        pickTaskService.update(pickTaskDto);
    }

    private void updateBoxIndo(String boxCode,Integer cellId){
        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxCode);
        boxInfo.setBoxCellId(cellId);
        boxInfoService.update(boxInfo);
    }

}
