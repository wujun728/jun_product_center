package io.github.wujun728.admin.page.controller;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.page.service.DicCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestAmisController {


    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DicCacheService dicCacheService;

    @RequestMapping("/test")
    public Result test(){
        return Result.success("操作成功");
    }
    @RequestMapping("/headers")
    public Result headers(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,Object> data = new HashMap<>();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            data.put(name,value);
        }
        return Result.success(data);
    }
}
