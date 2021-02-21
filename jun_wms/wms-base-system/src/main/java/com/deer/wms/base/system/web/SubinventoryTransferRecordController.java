package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.transferReason.TransferReason;
import com.deer.wms.base.system.model.transferReason.TransferReasonCriteria;
import com.deer.wms.base.system.service.*;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.impl.SubinventoryTransferRecordServiceImpl;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.framework.util.MyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by  on 2019/10/31.
*/
@Controller
@RequestMapping("/subinventoryTransferRecord")
public class SubinventoryTransferRecordController  extends BaseController{

    private String prefix = "manage/transferRecord";

    @Autowired
    private SubinventoryTransferRecordService subinventoryTransferRecordService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private BillInRecordService billInRecordService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private SubInventoryService subInventoryService;
    @Autowired
    private RequestIdAutoService requestIdAutoService;
    @Autowired
    private RequestIdService requestIdService;
    @Autowired
    private ServerVisitAddressService serverVisitAddressService;
    @Autowired
    private TransferReasonService transferReasonService;

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("subinventoryTransferRecord:view")
    @GetMapping()
    public String subinventoryTransferRecord()
    {
        return prefix + "/transferRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    SubinventoryTransferRecord subinventoryTransferRecord = subinventoryTransferRecordService.findById(id);
        mmap.put("subinventoryTransferRecord", subinventoryTransferRecord);
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
    public Result add(@RequestBody SubinventoryTransferRecord subinventoryTransferRecord) {
        subinventoryTransferRecordService.save(subinventoryTransferRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        subinventoryTransferRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody SubinventoryTransferRecord subinventoryTransferRecord) {
        subinventoryTransferRecordService.update(subinventoryTransferRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        SubinventoryTransferRecord subinventoryTransferRecord = subinventoryTransferRecordService.findById(id);
        return ResultGenerator.genSuccessResult(subinventoryTransferRecord);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(SubinventoryTransferRecordCriteria criteria) {
        startPage();
        List<SubinventoryTransferRecordDto> list = subinventoryTransferRecordService.findList(criteria);
        return getDataTable(list);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(SubinventoryTransferRecordCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<SubinventoryTransferRecord> list = subinventoryTransferRecordService.findAll();
        return getDataTable(list);
    }

    /**
     * 初始化库存不合格
     * @param subinventoryTransferRecordCriteria
     * @return
     */
    @PostMapping("/initializeUnqualified")
    @ResponseBody
    public Result initializeUnqualified(@RequestBody SubinventoryTransferRecordCriteria subinventoryTransferRecordCriteria) {
        List<BoxItemDto> boxItemDtos = boxItemService.findList(new BoxItemCriteria(subinventoryTransferRecordCriteria.getIds()));
        for(BoxItemDto boxItemDto : boxItemDtos){
            List<BillInRecordDto> billInRecords = billInRecordService.findList(new BillInRecordCriteria(boxItemDto.getItemCode(),boxItemDto.getBatch(),0));
            if(billInRecords.size()<=0){
                return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE,"请选择初始化库存箱");
            }
            SubinventoryTransferRecord subinventoryTransferRecord = new SubinventoryTransferRecord(
                boxItemDto.getBoxCode(),boxItemDto.getItemCode(), boxItemDto.getBatch(),boxItemDto.getQuantity(), DateUtils.getTime(),
                    subinventoryTransferRecordCriteria.getLoginPersonCardNo(),boxItemDto.getSubInventoryId(),
                    TaskTypeConstant.UNQUALIFIED,"初始化库存转移至不合格库");
            subinventoryTransferRecordService.save(subinventoryTransferRecord);
            boxItemDto.setSubInventoryId(TaskTypeConstant.UNQUALIFIED);
            boxItemService.update(boxItemDto);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/subInventoryTransfer")
    @ResponseBody
    @Transactional
    public Result subInventoryTransfer(@RequestBody SubinventoryTransferRecordCriteria subinventoryTransferRecordCriteria) {
        String errMsg = "";
        try {
            if((subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.QUALIFIED) ||
                subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.UNQUALIFIED))) {
                if (subinventoryTransferRecordCriteria.getTransferReason().trim().equals("")) {
                    errMsg = "请输入或者选择原因！";
                    throw new RuntimeException();
                } else {
                    List<TransferReason> transferReasons = transferReasonService.findList(new TransferReasonCriteria(subinventoryTransferRecordCriteria.getTransferReason().trim()));
                    if (transferReasons.size() <= 0) {
                        TransferReason transferReason = new TransferReason(subinventoryTransferRecordCriteria.getTransferReason(),
                                DateUtils.getTime(), subinventoryTransferRecordCriteria.getLoginPersonCardNo());
                        transferReasonService.save(transferReason);
                    }
                }
            }
            if(subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.POSTPONE) &&
                    MyUtils.calculateDateDiffer(
                            new SimpleDateFormat("yyyy-mm-dd").parse(subinventoryTransferRecordCriteria.getPostpone()),
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.getTime())) <= 1l
            ){
                errMsg = "请选择时间或选择时间至今间隔1天及以上";
                throw new RuntimeException();
            }
            List<BoxItemDto> boxItemDtos = boxItemService.findBoxItemList(new BoxItemCriteria(subinventoryTransferRecordCriteria.getIds()));
            String bool = cellInfoService.judgeBoxItemState(boxItemDtos);
            if (!bool.equals("success")) {
                return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE, bool);
            }

            //定义传给EBS的集合
            List<Map<String, String>> lists = new ArrayList<>();
            //获取转移到的子库信息
            SubInventory subInventory = subInventoryService.findById(subinventoryTransferRecordCriteria.getToSubInventoryId());
            //获取自增长请求Id
            RequestIdAuto requestIdAuto = requestIdAutoService.backAutoId("WMS转库数据写入EBS接口");
            //定义请求接口记录集合
            List<RequestId> requestIds = new ArrayList<>();

            /*for (BoxItemDto boxItemDto : boxItemDtos) {
                //保存转库记录
                SubinventoryTransferRecord subinventoryTransferRecord = new SubinventoryTransferRecord(boxItemDto.getBoxCode(),
                        boxItemDto.getItemCode(), boxItemDto.getBatch(), boxItemDto.getQuantity(), DateUtils.getTime(),
                        subinventoryTransferRecordCriteria.getLoginPersonCardNo(), boxItemDto.getSubInventoryId(),
                        subinventoryTransferRecordCriteria.getToSubInventoryId(), null);
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
                //转到合格库 2
                if (subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.QUALIFIED)) {
                    //判断当前箱是否是不合格库 合格就执行 不合格提示操作人员重新选择
                    if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.UNQUALIFIED)){
                        //写入转库原因
                        subinventoryTransferRecord.setTransferMemo(subinventoryTransferRecordCriteria.getTransferReason());
                    }else{
                        errMsg = "当前选择不符合规则，如需转至合格库，请选择不合格物料";
                        throw new RuntimeException();
                    }
                }
                //转到延期库 4
                else if (subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.POSTPONE)) {
                    if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.QUALIFIED)
                        || boxItemDto.getSubInventoryId().equals(TaskTypeConstant.OVER_DUE)) {
                        //转到延期库须填写延期日期
                    boxItemDto.setExp(subinventoryTransferRecordCriteria.getPostpone());
                        //转库原因
                        subinventoryTransferRecord.setTransferMemo("延期");
                    }else{
                        errMsg = "当前选择不符合规则，如需转至延期库，请选择合格箱或者过期物料";
                        throw new RuntimeException();
                    }
                }
                //转到不合格库 5
                else if (subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.UNQUALIFIED)) {
                    //转库原因
                    if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.QUALIFIED)){
                        //写入转库原因
                        subinventoryTransferRecord.setTransferMemo(subinventoryTransferRecordCriteria.getTransferReason());
                    }else{
                        errMsg = "当前选择不符合规则，如需转至不合格库，请选择合格物料";
                        throw new RuntimeException();
                    }
                }
                //转到预测备料库 7
                else if (subinventoryTransferRecordCriteria.getToSubInventoryId().equals(TaskTypeConstant.FORECAST_PREPARATION)) {
                    //转库原因
                    if(boxItemDto.getSubInventoryId().equals(TaskTypeConstant.QUALIFIED)
                        || boxItemDto.getSubInventoryId().equals(TaskTypeConstant.POSTPONE)) {
                        //输入工单号

                        subinventoryTransferRecord.setTransferMemo("此处工单号"+"预测备料");
                    }else{
                        errMsg = "当前选择不符合规则，如需转至预测备料库，请选择合格或者延期物料";
                        throw new RuntimeException();
                    }
                }
                //保存转库记录
                subinventoryTransferRecordService.save(subinventoryTransferRecord);
                //修改单箱绑定的子库
                boxItemDto.setSubInventoryId(subInventory.getSubInventoryId());
                boxItemService.update(boxItemDto);
            }
            EbsBack entityStr = serverVisitAddressService.requestServerCode(requestIdAuto.getAutoId().toString(),
                    TaskTypeConstant.WMSSUBINV_TRANSPROC, TaskTypeConstant.SYNCHRONOUS_EXECUTE, null, lists);
            //判断是否请求到参数
            if (entityStr != null && entityStr.getSuccess().equals("true") && entityStr.getTotal() > 0) {
                requestIdService.subInventoryTransfer(entityStr, requestIdAuto);
            }
            //失败则循环保存上面定义的请求记录
            else {
                for (RequestId requestId : requestIds) {
                    requestIdService.save(requestId);
                }
            }*/
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.GENERAL_WARING_CODE,errMsg);
        }
        return ResultGenerator.genSuccessResult();
    }

}
