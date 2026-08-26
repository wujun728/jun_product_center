package com.ruoyi.biz.controller;

import com.ruoyi.biz.domain.CommonForm;
import com.ruoyi.biz.service.IBizFormService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p> 业务表单控制器</p>
 *
 * @Author wocurr.com
 */
@RestController
@RequestMapping("/biz/form")
public class BizFormController {

    @Autowired
    private IBizFormService bizFormService;

    /**
     * 保存
     *
     * @param commonForm 表单参数
     * @return 保存结果
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody CommonForm commonForm) {
        return AjaxResult.success("操作成功", bizFormService.save(commonForm));
    }

    /**
     * 更新
     *
     * @param commonForm 表单参数
     * @return 更新结果
     */
    @PostMapping("/update")
    public AjaxResult update( @RequestBody CommonForm commonForm) {
        bizFormService.update(commonForm);
        return AjaxResult.success();
    }

    /**
     * 获取业务表单
     *
     * @param commonForm 表单参数
     * @return 业务表单
     */
    @GetMapping("/info")
    public AjaxResult getBizForm(CommonForm commonForm) {
        return AjaxResult.success(bizFormService.getBizForm(commonForm));
    }
}