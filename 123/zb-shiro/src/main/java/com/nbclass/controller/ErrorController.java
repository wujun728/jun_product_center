package com.nbclass.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    /*shiro无权限时进入*/
    @RequestMapping("/403")
    public String noPermission(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return "error/403";
    }
}
