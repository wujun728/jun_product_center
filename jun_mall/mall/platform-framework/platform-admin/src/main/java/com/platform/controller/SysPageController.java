package com.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@Controller
public class SysPageController {

    @RequestMapping("{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url + ".html";
    }

}
