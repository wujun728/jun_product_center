package com.lu.web.modular.generate.controller;

import com.alibaba.fastjson.JSONObject;
import com.lu.core.tips.ErrorTip;
import com.lu.modulargenerate.model.CodeStrategy;
import com.lu.modulargenerate.service.GeneratorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * @program LuGenerate
 * @description: 模块构建
 * @author: zhanglu
 * @create: 2019-12-14 15:52:00
 */
@Controller
@RequestMapping("/generate/modular")
public class ModularController {

    @Autowired
    private GeneratorCodeService modularService;

    /**
     * 检查数据源连接
     */
    @GetMapping("/check")
    @ResponseBody
    public Object check(String driver, String url, String username, String password) throws Exception {
        return modularService.check(driver, url, username, password);
    }

    /**
     * 获取表信息
     */
    @GetMapping("/get")
    @ResponseBody
    public Object get(String driver, String url, String username, String password){
        try {
            return modularService.getTableInfo(driver, url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorTip(-1, e + "");
        }
    }

    /**
     * 构建模块，返回文件
     */
    @GetMapping("/execute")
    @ResponseBody
    public void execute(HttpServletResponse response, String codeStrategyData, String datasourceData) throws Exception{
        codeStrategyData = URLDecoder.decode(codeStrategyData, "UTF-8");
        datasourceData = URLDecoder.decode(datasourceData, "UTF-8");
        modularService.execute(response, codeStrategyData, datasourceData);
    }

}
