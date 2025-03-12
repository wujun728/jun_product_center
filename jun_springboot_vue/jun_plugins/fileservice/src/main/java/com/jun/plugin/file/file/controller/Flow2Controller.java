package com.jun.plugin.file.file.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图
 *
 * @version V1.0
 * @date 2020年3月18日
 */
@Slf4j
@Api(tags = "视图")
@Controller
@RequestMapping("/admin111")
public class Flow2Controller {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
     
    @GetMapping("/view/flow/processList")
    public String sysFilesByUser() {
    	log.info("message 111");
    	return "admin/view/flow/processList";
    }
    
    //localhost:8080/index/goto/file
    @GetMapping("/goto/{pagePath}")
    public String gotPage(@PathVariable String pagePath) {
    	return pagePath;
    }
}
