package com.deer.wms.base.system.web;


import com.deer.wms.base.system.model.Operator;
import com.deer.wms.base.system.model.OperatorCriteria;
import com.deer.wms.base.system.service.OperatorService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.enums.BusinessType;import com.deer.wms.common.utils.poi.ExcelUtil;
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
* Created by guo on 2019/08/27.
*/
@Controller
@RequestMapping("/system/operator")
public class OperatorController  extends BaseController{

    private String prefix = "system/operator";

    @Autowired
    private OperatorService operatorService;



    /**
    * 详情
    */
    @GetMapping("/detail")
    public String detail()
    {
        return prefix + "/detail";
    }

    @RequiresPermissions("system:operator:view")
    @GetMapping()
    public String operator()
    {
        return prefix + "/operator";
    }

    /**
    * 修改
    */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
    Operator operator = operatorService.findById(id);
        mmap.put("operator", operator);
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

    /**
     * 新增操作员
     */
    @RequiresPermissions("system:operator:add")
    @Log(title = "保存操作员", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(Operator operator) {
        OperatorCriteria criteria = new OperatorCriteria();
        criteria.setEmpNo(operator.getEmpNo());
        criteria.setOperatorCard(operator.getOperatorCard());
        List<Operator> operators = operatorService.findList(criteria);
        if(operators.size()>0){
            return error("此操作员已录入!");
        }else{
            operator.setLogoutFlag(1);
            operatorService.save(operator);
            return success();
        }
    }

    @PostMapping("/insert")
    @ResponseBody
    public Result add(@RequestBody Operator operator) {
        operatorService.save(operator);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        operatorService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("system:operator:remove")
    @Log(title = "删除操作员", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Integer id)
    {
        try
        {
            Operator operator = new Operator();
            operator.setLogoutFlag(2);
            operator.setOperatorId(id);
            operatorService.update(operator);
//            operatorService.deleteById(id);
            return toAjax(1);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Operator operator) {
        operatorService.update(operator);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("system:operator:edit")
    @Log(title = "操作员设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Operator operator) {
        operatorService.update(operator);
        return toAjax(1);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable Integer id) {
        Operator operator = operatorService.findById(id);
        return ResultGenerator.genSuccessResult(operator);
    }

    @PostMapping("/list")
    @ResponseBody
    public  TableDataInfo list(OperatorCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<Operator> list = operatorService.findAll();
        return getDataTable(list);
    }

    /**
     * 刷卡验证接口
     * @param criteria
     * @return
     */
    @PostMapping("/findByCard")
    @ResponseBody
    public  Result findByCard(@RequestBody OperatorCriteria criteria) {
        Operator operator = operatorService.findByCard(criteria.getCard());
        if(operator == null){
            return ResultGenerator.genFailResult(CommonCode.GENERAL_WARING_CODE);
        }
        return ResultGenerator.genSuccessResult(operator);
    }

    @PostMapping("/findList")
    @ResponseBody
    public  TableDataInfo findList(OperatorCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<Operator> list = operatorService.findList(criteria);
        return getDataTable(list);
    }

    /**
     * 导出操作员
     */
    @Log(title = "操作员管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:operator:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OperatorCriteria criteria)
    {
        List<Operator> list = operatorService.findList(criteria);
        ExcelUtil<Operator> util = new ExcelUtil<Operator>(Operator.class);
        return util.exportExcel(list, "操作员");
    }

    /**
     * 校验卡号是否存在
     */
    @PostMapping("/checkOperatorCard")
    @ResponseBody
    public String checkOperatorCard(OperatorCriteria operatorCriteria)
    {
        return operatorService.checkOperatorCard(operatorCriteria.getOperatorCard());
    }
    /**
     * 校验卡号是否存在
     */
    @PostMapping("/checkOperatorCardUnique")
    @ResponseBody
    public String checkOperatorCardUnique(OperatorCriteria operatorCriteria)
    {
        return operatorService.checkOperatorCardUnique(operatorCriteria);
    }

}
