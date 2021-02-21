package com.deer.wms.base.system.web.bill;

import com.deer.wms.base.system.model.bill.BillOutDetail;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.bill.BillOutMasterDto;
import com.deer.wms.base.system.model.bill.InserData;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 出库单 信息操作处理
 *
 * @author cai
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/out/billOutMaster")
public class BillOutMasterController extends BaseController {

    private String prefix = "out/billOutMaster";

    @Autowired
    private IBillOutMasterService billOutMasterService;

    @Autowired
    private IBillOutDetailService billOutDetailService;

    @Autowired
    private IBoxItemService boxItemService;

    /**
     * 保存出库信息 , 出库单与出库详情
     */
    @RequiresPermissions("out:billOutMaster:add")
    @Log(title = "出库单", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    @ResponseBody
    @Transactional
    public Result insert(@RequestBody InserData inserData){
        //判断库存货物数量是否足够出库 不够就返回“false”
       List<BillOutDetail> billOutDetailList = inserData.getBillOutDetailList();
        for(BillOutDetail billOutDetail : billOutDetailList){
            List<BoxItemDto> boxItemDtos = boxItemService.getBoxItemDtoByitemCode(billOutDetail.getItemCode());
            Double quantitys = 0.0;
            for(BoxItemDto boxItemDto : boxItemDtos){
                quantitys += boxItemDto.getQuantity();
                if(billOutDetail.getQuantity() > quantitys){
                    return ResultGenerator.genSuccessResult("false");
                }
            }
        }
        String createUserName = ShiroUtils.getLoginName();
        Integer userId = ShiroUtils.getUserId();
        BillOutMaster billOutMaster = inserData.getBillOutMaster();
        billOutMaster.setCreateUserName(createUserName);
        billOutMaster.setCreateUserId(userId.intValue());
        billOutMasterService.save(billOutMaster);
        Integer billId = billOutMaster.getBillId();
        List<BillOutDetail> billOutDetails = inserData.getBillOutDetailList();
        for(BillOutDetail billOutDetail : billOutDetails ){
            billOutDetail.setBillId(billId);
            billOutDetailService.saveBillOutDetail(billOutDetail);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除入库单
     */
    @RequiresPermissions("out:billOutMaster:remove")
    @Log(title = "出库单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        return toAjax(billOutMasterService.deleteBillOutMasterByIds(ids));
    }

    /**
     * 修改保存入库单
     */
    @RequiresPermissions("out:billOutMaster:edit")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BillOutMaster billOutMaster)
    {
        return toAjax(billOutMasterService.updateBillOutMaster(billOutMaster));
    }

    /**
     * 修改入库单
     */
    @GetMapping("/edit/{billId}")
    public String edit(@PathVariable("billId") Integer billId, ModelMap mmap)
    {
        BillOutMaster billOutMaster = billOutMasterService.selectBillOutMasterById(billId);
        mmap.put("billOutMaster", billOutMaster);
        return prefix + "/edit";
    }

    /**
     * 查看详情
     */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    /**
     * 新增出库单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @RequiresPermissions("out:billOutMaster:view")
    @GetMapping("/page")
    public String billInMaster()
    {
        return prefix + "/billOutMaster";
    }

    /**
     * 根据billId查询出库单信息
     */
    @RequiresPermissions("out:billOutMaster:findBillOutMasterDtoByBillId")
    @PostMapping("/findBillOutMasterDtoByBillId")
    @ResponseBody
    public BillOutMasterDto findBillOutMasterDtoByBillId(Integer billId){
        BillOutMasterDto billOutMasterDto = billOutMasterService.findBillOutMasterDtoByBillId(billId);
        return billOutMasterDto;
    }

    /**
     * 查询出库单列表
     */
//    @RequiresPermissions("out:billOutMaster:list")
    @PostMapping("/findList")
    @ResponseBody
    public TableDataInfo list() {
        startPage();
        List<BillOutMasterDto> list = billOutMasterService.selectBillOutMasterList();
        return getDataTable(list);
    }


    @GetMapping("/toNonWorkOrderOut")
    public String toNonWorkOrderOut()
    {
        return prefix + "/nonWorkOrderOut";
    }
}











