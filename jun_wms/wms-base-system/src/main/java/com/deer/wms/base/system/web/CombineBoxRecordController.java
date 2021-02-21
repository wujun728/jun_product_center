package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.CombineBoxRecord;
import com.deer.wms.base.system.model.CombineBoxRecordCriteria;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import com.deer.wms.base.system.service.CombineBoxRecordService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.text.Convert;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
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
import sun.nio.cs.Surrogate;

import java.util.List;

/**
* Created by  on 2019/11/04.
*/
@Controller
@RequestMapping("/combineBoxRecord")
public class CombineBoxRecordController  extends BaseController{

    private String prefix = "combineBoxRecord";

    @Autowired
    private CombineBoxRecordService combineBoxRecordService;
    @Autowired
    private IBoxItemService boxItemService;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private ICellInfoService cellInfoService;
    @Autowired
    private BoxInfoService boxInfoService;
    @Autowired
    private IItemInfoService itemInfoService;

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("combineBoxRecord:view")
    @GetMapping()
    public String combineBoxRecord()
    {
        return prefix + "/combineBoxRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    CombineBoxRecord combineBoxRecord = combineBoxRecordService.findById(id);
        mmap.put("combineBoxRecord", combineBoxRecord);
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
    public Result add(@RequestBody CombineBoxRecord combineBoxRecord) {
        combineBoxRecordService.save(combineBoxRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        combineBoxRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody CombineBoxRecord combineBoxRecord) {
        combineBoxRecordService.update(combineBoxRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        CombineBoxRecord combineBoxRecord = combineBoxRecordService.findById(id);
        return ResultGenerator.genSuccessResult(combineBoxRecord);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(CombineBoxRecordCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<CombineBoxRecord> list = combineBoxRecordService.findAll();
        return getDataTable(list);
    }

    /**
     * 点击合框按钮，堆垛机取可以合框物料的两框
     */
    @PostMapping("/combineBox")
    @ResponseBody
    @Transactional
    public Result combineBox(@RequestBody CombineBoxRecordCriteria combineBoxRecordCriteria) {
        String error = "";
        int cumsumQuantity = 0;
        try {
            BoxItemCriteria boxItemCriteria = new BoxItemCriteria();
            boxItemCriteria.setIds(Convert.toIntArray(combineBoxRecordCriteria.getBoxIds().trim().replaceAll(" ",",")));
            boxItemCriteria.setOrderByState(1003);
            List<BoxItemDto> boxItemDtos = boxItemService.findList(boxItemCriteria);
            BoxItemDto boxItemDto2 = boxItemDtos.get(boxItemDtos.size()-1);
            for(int i=1;i<boxItemDtos.size();i++){
                BoxItemDto boxItemDto = boxItemDtos.get(i);
                cumsumQuantity += boxItemDto.getQuantity();
                if(!boxItemDto.getBatch().equals(boxItemDtos.get(0).getBatch()) ||
                        !boxItemDto.getItemCode().equals(boxItemDtos.get(0).getItemCode()) ||
                        !boxItemDto.getWorkOrderStockState().equals(boxItemDtos.get(0).getWorkOrderStockState()) ||
                        !boxItemDto.getSubInventoryId().equals(boxItemDtos.get(0).getSubInventoryId()) ||
                        !boxItemDto.getExp().equals(boxItemDtos.get(0).getExp()) ||
                        !boxItemDto.getBoxState().equals(1) ||
                        !boxItemDto.getCellState().equals(1)
                ){
                    error = "请刷新后选择相同物料、批次、子库、过期日期，在货位上，无任务的框！";
                    throw new RuntimeException();
                }
                if(cumsumQuantity>boxItemDto.getMaxPackQty()){
                    error = "合框数量超过单箱最大存储数量！";
                    throw new RuntimeException();
                }
                BoxItemDto boxItemDto1 = boxItemDtos.get(i-1);
                CombineBoxRecord combineBoxRecord = new CombineBoxRecord(boxItemDto1.getBoxCode(),boxItemDto1.getQuantity(),
                        boxItemDto2.getBoxCode(),boxItemDto2.getQuantity(),DateUtils.getTime(),combineBoxRecordCriteria.getLoginPersonCardNo());
                combineBoxRecordService.save(combineBoxRecord);
            }
            for(BoxItemDto boxItemDto : boxItemDtos){
                CellInfo cellInfo = cellInfoService.findById(boxItemDto.getBoxCellId());
                cellInfoService.updateCellInfoState(cellInfo,2);
                BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxItemDto.getBoxCode());
                boxInfo.setBoxState(2);
                boxInfoService.update(boxInfo);
                TaskInfo taskInfo = new TaskInfo(null,new GuidUtils().toString(),
                        MyUtils.connectShelfNameAndRowAndColumn(boxItemDto.getShelfName(),boxItemDto.getsColumn(),boxItemDto.getsRow()),
                        "105",1,0,0,boxItemDto.getBoxCode()
                );
                taskInfo.setCardNo(combineBoxRecordCriteria.getLoginPersonCardNo());
                taskInfo.setTaskStartTime(DateUtils.getTime());
                taskInfo.setIsTop("0");
                taskInfoService.save(taskInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(CommonCode.PARAMETER_ERROR, error);
        }
        return ResultGenerator.genSuccessResult(cumsumQuantity);
    }

    /**
     * 点击合框按钮，堆垛机取可以合框物料的两框
     */
    @PostMapping("/afterCombineBoxBillIn")
    @ResponseBody
    @Transactional
    public Result afterCombineBoxBillIn(@RequestBody CombineBoxRecordCriteria combineBoxRecordCriteria) {
        BoxItem boxItem = boxItemService.getBoxItemByBoxCode(combineBoxRecordCriteria.getBoxCode());
        boxItem.setQuantity(combineBoxRecordCriteria.getQuantity());
        boxItemService.update(boxItem);
        CellInfoDto cellInfoDto = cellInfoService.getBestCell();
        cellInfoService.updateCellInfoState(cellInfoDto,2);
        BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(combineBoxRecordCriteria.getBoxCode());
        boxInfo.setBoxCellId(cellInfoDto.getCellId());
        boxInfo.setBoxState(2);
        boxInfoService.update(boxInfo);
        TaskInfo taskInfo = new TaskInfo(new GuidUtils().toString(),"105",
                MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(),cellInfoDto.getSColumn(),cellInfoDto.getSRow()),
                2,0,combineBoxRecordCriteria.getQuantity(),boxItem.getBoxCode(),"0",null);
        taskInfo.setBarCode(MyUtils.connectPrintString(boxItem.getItemCode(),boxItem.getQuantity(), boxItem.getExp(),
                boxItem.getBatch(),itemInfoService.findByItemCode(boxItem.getItemCode()).getItemName()));
        taskInfo.setCardNo(combineBoxRecordCriteria.getLoginPersonCardNo());
        taskInfoService.save(taskInfo);
        return ResultGenerator.genSuccessResult();
    }
}
