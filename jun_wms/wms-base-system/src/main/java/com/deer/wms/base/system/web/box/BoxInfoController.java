package com.deer.wms.base.system.web.box;

import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxInfoCriteria;
import com.deer.wms.base.system.model.box.BoxInfoDto;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.enums.BusinessType;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;

import java.util.List;

/**
* Created by guo on 2019/06/24.
*/
@Controller
@RequestMapping("boxInfo")
public class BoxInfoController  extends BaseController{

    private String prefix = "boxInfo";

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private ICellInfoService cellInfoService;

    @Autowired
    private IBoxItemService boxItemService;


    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("system:boxInfo:view")
    @GetMapping()
    public String boxInfo()
    {
        return prefix + "/boxInfo";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    BoxInfo boxInfo = boxInfoService.findById(id);
        mmap.put("boxInfo", boxInfo);
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


    @Log(title = "容器", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody BoxInfo boxInfo) {
        boxInfoService.save(boxInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Integer id) {
        BoxInfo boxInfo = boxInfoService.findById(id);
        boxItemService.deleteByBoxCode(boxInfo.getBoxCode());
        boxInfoService.deleteById(id);
        return success();
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(BoxInfo boxInfo) {
        BoxInfo boxInfo1 = boxInfoService.findById(boxInfo.getBoxId());
        BoxItem boxItem = boxItemService.getBoxItemByBoxCode(boxInfo1.getBoxCode());
        boxItem.setBoxCode(boxInfo.getBoxCode());
        boxItemService.update(boxItem);
        boxInfoService.update(boxInfo);
        return success();
    }

    @PostMapping("/upCell")
    @ResponseBody
    public Result upCell(@RequestBody BoxInfo boxInfo) {
        //手动托盘上架     设置托盘位置状态为在货位  设置货位状态为有货  
        boxInfo.setBoxState(1);
        boxInfoService.update(boxInfo);
        CellInfo cellInfo = cellInfoService.findBy("cellId",boxInfo.getBoxCellId());
        cellInfo.setState(1);
        cellInfoService.update(cellInfo);
        return ResultGenerator.genSuccessResult();
    }



    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        BoxInfo boxInfo = boxInfoService.findById(id);
        return ResultGenerator.genSuccessResult(boxInfo);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(BoxInfoCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<BoxInfo> list = boxInfoService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(BoxInfoCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<BoxInfoDto> list = boxInfoService.findList(criteria);
        return getDataTable(list);
    }
}
