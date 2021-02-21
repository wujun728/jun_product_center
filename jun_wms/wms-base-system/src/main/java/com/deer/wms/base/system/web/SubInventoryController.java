package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.SubInventory;
import com.deer.wms.base.system.model.SubInventoryCriteria;
import com.deer.wms.base.system.model.SubInventoryDto;
import com.deer.wms.base.system.service.SubInventoryService;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.framework.util.ShiroUtils;
import com.deer.wms.system.domain.SysUser;
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
* Created by  on 2019/11/09.
*/
@Controller
@RequestMapping("/subInventory")
public class SubInventoryController  extends BaseController{

    private String prefix = "system/subInventory";

    @Autowired
    private SubInventoryService subInventoryService;

    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("subInventory:view")
    @GetMapping()
    public String subInventory()
    {
        return prefix + "/subInventory";
    }

    /**
    * 修改
    */
    @RequiresPermissions("subInventory:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    SubInventory subInventory = subInventoryService.findById(id);
        mmap.put("subInventory", subInventory);
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

    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult add(SubInventory subInventory) {
        SysUser sysUser = ShiroUtils.getSysUser();
        subInventory.setUpdateTime(DateUtils.getTime());
        subInventory.setUpdateUserId(sysUser.getUserId().intValue());
        subInventoryService.save(subInventory);
        return success("保存成功");
    }

    @RequiresPermissions("subInventory:delete")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Integer id) {
        subInventoryService.deleteById(id);
        return success("删除成功");
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SubInventory subInventory) {
        SysUser sysUser = ShiroUtils.getSysUser();
        subInventory.setUpdateTime(DateUtils.getTime());
        subInventory.setUpdateUserId(sysUser.getUserId().intValue());
        if(subInventory.getSlotting().equals("")){
            subInventory.setSlotting(null);
        }
        subInventoryService.update(subInventory);
        return success("修改成功");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        SubInventory subInventory = subInventoryService.findById(id);
        return ResultGenerator.genSuccessResult(subInventory);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(SubInventoryCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
//        List<SubInventory> list = subInventoryService.findAll();
        List<SubInventoryDto> list = subInventoryService.findList(criteria);
        return getDataTable(list);
    }

    @PostMapping("/findIdNotEqualOne")
    @ResponseBody
    public  Result findIdNotEqualOne() {
        List<SubInventory> list = subInventoryService.findIdNotEqualOne();
        return ResultGenerator.genSuccessResult(list);
    }

}
