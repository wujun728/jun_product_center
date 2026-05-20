package com.ruoyi.biz.controller;

import com.ruoyi.biz.domain.CommonFlowSubmit;
import com.ruoyi.biz.service.IBizFLowService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 业务流程控制器</p>
 *
 * @Author wocurr.com
 */
@RestController
@RequestMapping("/biz/flow")
public class BizFlowController {

    @Autowired
    private IBizFLowService bizFLowService;

    /**
     * 提交
     *
     * @return 提交结果
     */
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody CommonFlowSubmit submit) {
        bizFLowService.submit(submit);
        return AjaxResult.success();
    }
}
