package io.github.wujun728.admin.page.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.PageKey;
import io.github.wujun728.admin.page.data.CustomPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/admin/custom")
@Controller
public class CustomPageController {
    @Resource
    JdbcService jdbcService;
    @RequestMapping("/page/{code}")
    public String page(Model model, @PathVariable String code,@RequestParam Map<String,Object> data){
        StringBuilder params = new StringBuilder("");
        data.entrySet().forEach(entry -> {
            params.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        });
        model.addAttribute("js","/admin/custom/js/"+code+".js?_rt="+System.currentTimeMillis()+params.toString());
        return "page";
    }
    @RequestMapping(value = "/js/{code}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public String js(@PathVariable String code, @RequestParam Map<String,Object> data){
        CustomPage customPage = jdbcService.findOne(CustomPage.class, "code", code);
        String content = customPage.getContent();
        JSONObject json = JSONUtil.parseObj(content);
        if(json.containsKey("data")){
            JSONObject oldData = json.getJSONObject("data");
            oldData.putAll(data);
            json.set("data",oldData);
        }else{
            json.set("data",data);
        }
        return StrUtil.format("{}={}",PageKey.AMIS_JSON,json.toStringPretty());
    }
    @RequestMapping("/editor/{code}")
    public String editor(Model model, @PathVariable String code){
        model.addAttribute("js","/admin/custom/js/"+code+".js");
        model.addAttribute("code",code);
        return "editor";
    }
    @RequestMapping("/saveSchema/{code}")
    @ResponseBody
    public Result saveSchema(@PathVariable String code, @RequestParam Map<String,Object> data){
        CustomPage customPage = jdbcService.findOne(CustomPage.class, "code", code);
        String schema = (String) data.get("schema");
        customPage.setContent(schema);
        jdbcService.saveOrUpdate(customPage);
        return Result.success();
    }
}
