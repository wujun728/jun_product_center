package com.jun.plugin.generator.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
@Controller
@RequestMapping("/dev/build")
public class BuildController {

    @GetMapping("/index")
    public String index(Model model){
        return "/devtools/build/index";
    }

    @GetMapping("/test111")
    @ResponseBody
    public String test1(Model model){
        return "1111";
    }
}
