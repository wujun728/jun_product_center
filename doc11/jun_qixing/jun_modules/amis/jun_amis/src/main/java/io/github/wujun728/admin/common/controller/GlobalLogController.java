package io.github.wujun728.admin.common.controller;

import cn.hutool.core.date.DateUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.data.GlobalLog;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.TemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/operationLog")
public class GlobalLogController {
    @Resource
    private JdbcService jdbcService;
    @RequestMapping("/page/{modelName}/{id}")
    public String log(Model model, @PathVariable String modelName, @PathVariable Long id){
        model.addAttribute("js","/admin/operationLog/js/"+modelName+"/"+id+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping(value="/js/{modelName}/{id}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public String js(Model model, @PathVariable String modelName, @PathVariable Long id){
        Map<String, Object> params = new HashMap<>();
        params.put("modelName",modelName);
        params.put("id",id);
        return TemplateUtil.getUi("operationLog.js.vm",params);
    }
    @RequestMapping("/data/{modelName}/{id}")
    @ResponseBody
    public Result data(Model model, @PathVariable String modelName, @PathVariable Long id){
        List<GlobalLog> globalLogs = jdbcService.find("select * from global_log where table_name = ? and ref_id = ? order by create_time desc ", GlobalLog.class, StringUtil.toSqlColumn(modelName).toLowerCase(), id);
        List<Map<String,Object>> items = new ArrayList<>();
        for(GlobalLog globalLog:globalLogs){
            Map<String,Object> item = new HashMap<>();
            item.put("time", DateUtil.format(globalLog.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            item.put("title",globalLog.getRemark());
            if(globalLog.getOptionType().equals("删除")){
                item.put("detail",globalLog.getBeforeValue());
            }else if(globalLog.getOptionType().equals("创建")){
                item.put("detail",globalLog.getAfterValue());
            }
            items.add(item);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("items",items);
        return Result.success(data);
    }
}
