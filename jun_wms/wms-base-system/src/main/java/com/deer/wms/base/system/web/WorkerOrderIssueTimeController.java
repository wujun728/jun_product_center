package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.WorkerOrderIssueTime;
import com.deer.wms.base.system.model.WorkerOrderIssueTimeCriteria;
import com.deer.wms.base.system.service.WorkerOrderIssueTimeService;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.framework.util.MyUtils;
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
* Created by  on 2019/09/28.
*/
@Controller
@RequestMapping("workerOrderIssueTime")
public class WorkerOrderIssueTimeController  extends BaseController{

    private String prefix = "system/workerOrderIssueTime";

    @Autowired
    private WorkerOrderIssueTimeService workerOrderIssueTimeService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("workerOrderIssueTime:view")
    @GetMapping()
    public String workerOrderIssueTime()
    {
        return prefix + "/workerOrderIssueTime";
    }

    /**
    * 修改
    */
    @RequiresPermissions("workerOrderIssueTime:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WorkerOrderIssueTime workerOrderIssueTime = workerOrderIssueTimeService.findById(id);
        mmap.put("workerOrderIssueTime", workerOrderIssueTime);
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
    public Result add(@RequestBody WorkerOrderIssueTime workerOrderIssueTime) {
        workerOrderIssueTimeService.save(workerOrderIssueTime);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        workerOrderIssueTimeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody WorkerOrderIssueTime workerOrderIssueTime) {
        if(workerOrderIssueTime.getSluggishExportDate() == null){
            return error("请输入日期!");
        }
        if(workerOrderIssueTime.getSluggishExportDate() > 28){
            return error("日期请输入1到28号!");
        }
        if(workerOrderIssueTime.getLockTime() == null){
            return error("请输入锁定时间!");
        }
        workerOrderIssueTime.setFirstTime(MyUtils.spliteSeconds(workerOrderIssueTime.getFirstTime()));
        workerOrderIssueTime.setSecondTime(MyUtils.spliteSeconds(workerOrderIssueTime.getSecondTime()));
        workerOrderIssueTime.setThirdTime(MyUtils.spliteSeconds(workerOrderIssueTime.getThirdTime()));
        workerOrderIssueTime.setFourthTime(MyUtils.spliteSeconds(workerOrderIssueTime.getFourthTime()));
        workerOrderIssueTime.setFifthTime(MyUtils.spliteSeconds(workerOrderIssueTime.getFifthTime()));
        workerOrderIssueTime.setSixthTime(MyUtils.spliteSeconds(workerOrderIssueTime.getSixthTime()));
        workerOrderIssueTimeService.update(workerOrderIssueTime);
        return toAjax(1);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        WorkerOrderIssueTime workerOrderIssueTime = workerOrderIssueTimeService.findById(id);
        return ResultGenerator.genSuccessResult(workerOrderIssueTime);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo list(WorkerOrderIssueTimeCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<WorkerOrderIssueTime> list = workerOrderIssueTimeService.findAll();
        return getDataTable(list);
    }

}
