package com.nbclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
@Controller
@RequestMapping("/database")
public class DatabaseController{
    @GetMapping(value = "/monitoring")
    public ModelAndView databaseMonitoring(){
        return new ModelAndView("database/monitoring");
    }
}
