package com.deer.wms.base.system.web;

import com.deer.wms.base.system.model.Carrier;
import com.deer.wms.base.system.model.CarrierCriteria;
import com.deer.wms.base.system.service.CarrierService;
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
* Created by  on 2019/10/11.
*/
@Controller
@RequestMapping("carrier")
public class CarrierController  extends BaseController{

    private String prefix = "carrier";

    @Autowired
    private CarrierService carrierService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("carrier:view")
    @GetMapping()
    public String carrier()
    {
        return prefix + "/carrier";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    Carrier carrier = carrierService.findById(id);
        mmap.put("carrier", carrier);
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
    public Result add(@RequestBody Carrier carrier) {
        carrierService.save(carrier);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        carrierService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Carrier carrier) {
        carrierService.update(carrier);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        Carrier carrier = carrierService.findById(id);
        return ResultGenerator.genSuccessResult(carrier);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(CarrierCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<Carrier> list = carrierService.findAll();
        return getDataTable(list);
    }

}
