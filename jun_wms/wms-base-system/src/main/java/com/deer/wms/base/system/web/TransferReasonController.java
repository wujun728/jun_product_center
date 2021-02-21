package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.transferReason.TransferReason;
import com.deer.wms.base.system.model.transferReason.TransferReasonCriteria;
import com.deer.wms.base.system.service.TransferReasonService;
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
* Created by  on 2020/04/01.
*/
@Controller
@RequestMapping("/transferReason")
public class TransferReasonController  extends BaseController{

    private String prefix = "transferReason";

    @Autowired
    private TransferReasonService transferReasonService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("transferReason:view")
    @GetMapping()
    public String transferReason()
    {
        return prefix + "/transferReason";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    TransferReason transferReason = transferReasonService.findById(id);
        mmap.put("transferReason", transferReason);
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
    public Result add(@RequestBody TransferReason transferReason) {
        transferReasonService.save(transferReason);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        transferReasonService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody TransferReason transferReason) {
        transferReasonService.update(transferReason);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        TransferReason transferReason = transferReasonService.findById(id);
        return ResultGenerator.genSuccessResult(transferReason);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(TransferReasonCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<TransferReason> list = transferReasonService.findAll();
        return getDataTable(list);
    }

    @GetMapping("/findList")
    @ResponseBody
    public  Result findList(TransferReasonCriteria criteria) {
        List<TransferReason> list = transferReasonService.findList(criteria);
        return ResultGenerator.genSuccessResult(list);
    }
}
