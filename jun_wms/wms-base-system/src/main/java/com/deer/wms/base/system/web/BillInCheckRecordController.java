package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.BillInCheckRecord;
import com.deer.wms.base.system.model.BillInCheckRecordCriteria;
import com.deer.wms.base.system.service.BillInCheckRecordService;
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
* Created by  on 2019/12/05.
*/
@Controller
@RequestMapping("billInCheckRecord")
public class BillInCheckRecordController  extends BaseController{

    private String prefix = "billInCheckRecord";

    @Autowired
    private BillInCheckRecordService billInCheckRecordService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("billInCheckRecord:view")
    @GetMapping()
    public String billInCheckRecord()
    {
        return prefix + "/billInCheckRecord";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    BillInCheckRecord billInCheckRecord = billInCheckRecordService.findById(id);
        mmap.put("billInCheckRecord", billInCheckRecord);
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
    public Result add(@RequestBody BillInCheckRecord billInCheckRecord) {
        billInCheckRecordService.save(billInCheckRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        billInCheckRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody BillInCheckRecord billInCheckRecord) {
        billInCheckRecordService.update(billInCheckRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        BillInCheckRecord billInCheckRecord = billInCheckRecordService.findById(id);
        return ResultGenerator.genSuccessResult(billInCheckRecord);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(BillInCheckRecordCriteria criteria) {
//        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<BillInCheckRecord> list = billInCheckRecordService.findAll();
        return getDataTable(list);
    }

}
