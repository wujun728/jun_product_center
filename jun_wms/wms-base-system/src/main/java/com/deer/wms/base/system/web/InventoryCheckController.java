package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.InventoryCheck;
import com.deer.wms.base.system.model.InventoryCheckCriteria;
import com.deer.wms.base.system.service.InventoryCheckService;
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

import java.util.List;

/**
* Created by  on 2019/12/31.
*/
@Controller
@RequestMapping("inventoryCheck")
public class InventoryCheckController  extends BaseController{

    private String prefix = "inventoryCheck";

    @Autowired
    private InventoryCheckService inventoryCheckService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("inventoryCheck:view")
    @GetMapping()
    public String inventoryCheck()
    {
        return prefix + "/inventoryCheck";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    InventoryCheck inventoryCheck = inventoryCheckService.findById(id);
        mmap.put("inventoryCheck", inventoryCheck);
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
    public Result add(@RequestBody InventoryCheck inventoryCheck) {
        inventoryCheckService.save(inventoryCheck);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        inventoryCheckService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody InventoryCheck inventoryCheck) {
        inventoryCheckService.update(inventoryCheck);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        InventoryCheck inventoryCheck = inventoryCheckService.findById(id);
        return ResultGenerator.genSuccessResult(inventoryCheck);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(InventoryCheckCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<InventoryCheck> list = inventoryCheckService.findAll();
        return getDataTable(list);
    }

}
