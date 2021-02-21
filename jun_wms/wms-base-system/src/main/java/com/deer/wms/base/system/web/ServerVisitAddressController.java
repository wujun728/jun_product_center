package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.ServerVisitAddress;
import com.deer.wms.base.system.model.ServerVisitAddressCriteria;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.common.core.domain.AjaxResult;
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
* Created by  on 2019/09/26.
*/
@Controller
@RequestMapping("/serverVisitAddress")
public class ServerVisitAddressController  extends BaseController{

    private String prefix = "system/serverAddress";

    @Autowired
    private ServerVisitAddressService serverVisitAddressService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("serverVisitAddress:view")
    @GetMapping()
    public String serverVisitAddress()
    {
        return prefix + "/serverVisitAddress";
    }

    /**
    * 修改
    */
    @RequiresPermissions("serverVisitAddress:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    ServerVisitAddress serverVisitAddress = serverVisitAddressService.findById(id);
        mmap.put("serverVisitAddress", serverVisitAddress);
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
    public Result add(@RequestBody ServerVisitAddress serverVisitAddress) {
        serverVisitAddressService.save(serverVisitAddress);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        serverVisitAddressService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(ServerVisitAddress serverVisitAddress) {
        serverVisitAddressService.update(serverVisitAddress);
        return toAjax(1);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        ServerVisitAddress serverVisitAddress = serverVisitAddressService.findById(id);
        return ResultGenerator.genSuccessResult(serverVisitAddress);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(ServerVisitAddressCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<ServerVisitAddress> list = serverVisitAddressService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(ServerVisitAddressCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<ServerVisitAddress> list = serverVisitAddressService.findList(criteria);
        return getDataTable(list);
    }
}
