package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.ProcessRecord;
import com.deer.wms.base.system.model.ProcessRecordCriteria;
import com.deer.wms.base.system.service.ProcessRecordService;
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
* Created by  on 2019/12/03.
*/
@Controller
@RequestMapping("processRecord")
public class ProcessRecordController  extends BaseController{

    private String prefix = "processRecord";

    @Autowired
    private ProcessRecordService processRecordService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("processRecord:view")
    @GetMapping()
    public String processRecord()
    {
        return prefix + "/processRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    ProcessRecord processRecord = processRecordService.findById(id);
        mmap.put("processRecord", processRecord);
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
    public Result add(@RequestBody ProcessRecord processRecord) {
        processRecordService.save(processRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        processRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ProcessRecord processRecord) {
        processRecordService.update(processRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        ProcessRecord processRecord = processRecordService.findById(id);
        return ResultGenerator.genSuccessResult(processRecord);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(ProcessRecordCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<ProcessRecord> list = processRecordService.findAll();
        return getDataTable(list);
    }

}
