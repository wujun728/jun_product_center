package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.BillInRecord;
import com.deer.wms.base.system.model.BillInRecordCriteria;
import com.deer.wms.base.system.model.BillInRecordDto;
import com.deer.wms.base.system.service.BillInRecordService;
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
* Created by  on 2019/10/18.
*/
@Controller
@RequestMapping("/billInRecord")
public class BillInRecordController  extends BaseController{

    private String prefix = "manage/inRecord";

    @Autowired
    private BillInRecordService billInRecordService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("billInRecord:view")
    @GetMapping()
    public String billInRecord()
    {
        return prefix + "/inRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    BillInRecord billInRecord = billInRecordService.findById(id);
        mmap.put("billInRecord", billInRecord);
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


    @PostMapping("/insert")
    @ResponseBody
    public Result add(@RequestBody BillInRecord billInRecord) {
        billInRecordService.save(billInRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        billInRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody BillInRecord billInRecord) {
        billInRecordService.update(billInRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        BillInRecord billInRecord = billInRecordService.findById(id);
        return ResultGenerator.genSuccessResult(billInRecord);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(BillInRecordCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<BillInRecord> list = billInRecordService.findAll();
        return getDataTable(list);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(BillInRecordCriteria criteria) {
        startPage();
        List<BillInRecordDto> list = billInRecordService.findList(criteria);
        return getDataTable(list);
    }

}
