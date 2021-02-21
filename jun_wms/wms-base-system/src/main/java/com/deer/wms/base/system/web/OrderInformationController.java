package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.OrderInformation;
import com.deer.wms.base.system.model.OrderInformationCriteria;
import com.deer.wms.base.system.service.OrderInformationService;
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
* Created by  on 2019/12/07.
*/
@Controller
@RequestMapping("orderInformation")
public class OrderInformationController  extends BaseController{

    private String prefix = "orderInformation";

    @Autowired
    private OrderInformationService orderInformationService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("orderInformation:view")
    @GetMapping()
    public String orderInformation()
    {
        return prefix + "/orderInformation";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    OrderInformation orderInformation = orderInformationService.findById(id);
        mmap.put("orderInformation", orderInformation);
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
    public Result add(@RequestBody OrderInformation orderInformation) {
        orderInformationService.save(orderInformation);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        orderInformationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody OrderInformation orderInformation) {
        orderInformationService.update(orderInformation);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        OrderInformation orderInformation = orderInformationService.findById(id);
        return ResultGenerator.genSuccessResult(orderInformation);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(OrderInformationCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<OrderInformation> list = orderInformationService.findAll();
        return getDataTable(list);
    }

}
