package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.WarnInformation;
import com.deer.wms.base.system.model.WarnInformationCriteria;
import com.deer.wms.base.system.service.WarnInformationService;
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
* Created by  on 2020/01/06.
*/
@Controller
@RequestMapping("/warnInformation")
public class WarnInformationController  extends BaseController{

    private String prefix = "warnInformation";

    @Autowired
    private WarnInformationService warnInformationService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("warnInformation:view")
    @GetMapping()
    public String warnInformation()
    {
        return prefix + "/warnInformation";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    WarnInformation warnInformation = warnInformationService.findById(id);
        mmap.put("warnInformation", warnInformation);
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
    public Result add(@RequestBody WarnInformation warnInformation) {
        warnInformationService.save(warnInformation);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        warnInformationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody WarnInformation warnInformation) {
        warnInformationService.update(warnInformation);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        WarnInformation warnInformation = warnInformationService.findById(id);
        return ResultGenerator.genSuccessResult(warnInformation);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(WarnInformationCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<WarnInformation> list = warnInformationService.findAll();
        return getDataTable(list);
    }

}
