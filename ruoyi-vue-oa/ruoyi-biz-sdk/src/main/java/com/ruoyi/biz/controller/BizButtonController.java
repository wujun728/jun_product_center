package com.ruoyi.biz.controller;

import com.ruoyi.biz.domain.CommonButton;
import com.ruoyi.biz.service.IBizButtonService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 业务按钮控制器</p>
 *
 * @Author wocurr.com
 */
@RestController
@RequestMapping("/biz/button")
public class BizButtonController {

    @Autowired
    private IBizButtonService bizButtonService;

    /**
     * 获取业务按钮
     *
     * @return 提交结果
     */
    @GetMapping("/list")
    public AjaxResult getButtons(CommonButton button) {
        return AjaxResult.success(bizButtonService.getButtons(button));
    }
}
