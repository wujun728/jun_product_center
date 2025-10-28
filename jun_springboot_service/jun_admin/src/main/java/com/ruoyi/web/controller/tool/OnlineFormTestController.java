package com.ruoyi.web.controller.tool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在线表单测试控制器
 * 用于验证onlineForm模块是否成功整合到jun_admin模块
 */
@RestController
@RequestMapping("/test/online/form")
public class OnlineFormTestController {

    // 注意：已移除对onlineForm模块的依赖，因此注释掉相关引用
    // @Autowired
    // private OnlineFormService onlineFormService;

    /**
     * 测试onlineForm模块是否成功加载
     */
    @GetMapping("/test")
    public String testOnlineFormModule() {
        // 当前环境中已移除onlineForm模块依赖
        return "OnlineForm模块测试控制器 - 当前环境已移除对onlineForm模块的依赖";
    }
}