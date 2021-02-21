package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.RequestIdAuto;
import com.deer.wms.base.system.model.RequestIdAutoCriteria;
import com.deer.wms.base.system.service.RequestIdAutoService;
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
* Created by  on 2019/12/28.
*/
@Controller
@RequestMapping("requestIdAuto")
public class RequestIdAutoController  extends BaseController{

    private String prefix = "requestIdAuto";

    @Autowired
    private RequestIdAutoService requestIdAutoService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("requestIdAuto:view")
    @GetMapping()
    public String requestIdAuto()
    {
        return prefix + "/requestIdAuto";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    RequestIdAuto requestIdAuto = requestIdAutoService.findById(id);
        mmap.put("requestIdAuto", requestIdAuto);
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
    public Result add(@RequestBody RequestIdAuto requestIdAuto) {
        requestIdAutoService.save(requestIdAuto);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        requestIdAutoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody RequestIdAuto requestIdAuto) {
        requestIdAutoService.update(requestIdAuto);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        RequestIdAuto requestIdAuto = requestIdAutoService.findById(id);
        return ResultGenerator.genSuccessResult(requestIdAuto);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(RequestIdAutoCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<RequestIdAuto> list = requestIdAutoService.findAll();
        return getDataTable(list);
    }

}
