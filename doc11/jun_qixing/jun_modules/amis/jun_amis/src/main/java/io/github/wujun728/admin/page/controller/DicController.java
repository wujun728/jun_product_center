package io.github.wujun728.admin.page.controller;

import cn.hutool.core.date.DateUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.page.data.Dic;
import io.github.wujun728.admin.page.data.DicItem;
import io.github.wujun728.admin.page.service.DicCacheService;
import io.github.wujun728.admin.page.service.DicService;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.TemplateUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class DicController {

    @Resource
    private DicService dicService;
    @RequestMapping("/options/{dicCode}")
    public Result options(@PathVariable String dicCode){
        Map<String,Object> data = new HashMap<>();
        data.put("options",dicCacheService.options(dicCode));
        return Result.success(data);
    }
    @Resource
    private DicCacheService dicCacheService;
    @RequestMapping("/dic/javaCode/{dicCode}")
    public Result javaCode(@PathVariable String dicCode){
        Map<String,Object> data = new HashMap<>();
        Dic dic = dicService.get(dicCode);

        Map<String, Object> params = new HashMap<>();
        params.put("dicCode", StringUtil.toFirstUp(dic.getDicCode()));
        params.put("dicName",dic.getDicName());
        List<Map<String,Object>> items = new ArrayList<>();
        for(DicItem item:dic.getItems()){
            Map<String,Object> map = new HashMap<>();
            map.put("value",item.getValue());
            map.put("name",item.getLabel());
            map.put("valueUpper",StringUtil.toSqlColumn(item.getValue()).toUpperCase());
            items.add(map);
        }
        params.put("date", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        params.put("items",items);
        String javaCode = TemplateUtil.getUi("dic.java.vm", params);
        data.put("javaCode",javaCode);
        return Result.success(data);
    }
}
