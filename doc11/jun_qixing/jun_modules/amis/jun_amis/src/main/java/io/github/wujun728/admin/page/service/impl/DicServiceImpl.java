package io.github.wujun728.admin.page.service.impl;

import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.data.Dic;
import io.github.wujun728.admin.page.data.DicItem;
import io.github.wujun728.admin.page.service.DicCacheService;
import io.github.wujun728.admin.page.service.DicService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dicService")
public class DicServiceImpl implements DicService {
    @Resource
    private JdbcService jdbcService;
    @Lazy
    @Resource
    private DicCacheService dicCacheService;
    @Override
    public List<Map<String, Object>> options(String code) {
        List<Map<String, Object>> options = jdbcService.find("select label,value,color,bg_color from dic_item where parent_id in(" +
                "select id from dic where dic_code = ? " +
                ") order by seq asc,id asc ", code);
        return options;
    }

    @Override
    public String getLabel(String code, String value) {
        return dicCacheService.getLabel(code,value);
    }

    @Override
    public Dic get(String code) {
        Dic dic = jdbcService.findOne(Dic.class, "dicCode", code);
        if(dic != null){
            List<DicItem> dicItems = jdbcService.find("select * " +
                    "from dic_item where parent_id = ? " +
                    "order by seq asc,id asc", DicItem.class, dic.getId());
            dic.setItems(dicItems);
        }
        return dic;
    }

    @Override
    public List<String> getLabels(String code) {
        List<Map<String, Object>> options = dicCacheService.options(code);
        List<String> labels = new ArrayList<>();
        for(Map<String,Object> option:options){
            labels.add((String) option.get("label"));
        }
        return labels;
    }

    @Override
    public Map<String, String> labelValueMap(String code) {
        List<Map<String, Object>> options = dicCacheService.options(code);
        Map<String,String> map = new HashMap<>();
        for(Map<String,Object> option:options){
            map.put((String)option.get("label"),(String)option.get("value"));
        }
        return map;
    }

    @Override
    public Map<String, String> valueLabelMap(String code) {
        List<Map<String, Object>> options = dicCacheService.options(code);
        Map<String,String> map = new HashMap<>();
        for(Map<String,Object> option:options){
            map.put((String)option.get("value"),(String)option.get("label"));
        }
        return map;
    }
}
