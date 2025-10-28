package io.github.wujun728.admin.page.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import io.github.wujun728.admin.common.constants.EventType;
import io.github.wujun728.admin.common.data.DataListener;
import io.github.wujun728.admin.common.service.DataListenerTask;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.data.Dic;
import io.github.wujun728.admin.page.service.DicCacheService;
import io.github.wujun728.admin.page.service.DicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("dicCacheService")
@Slf4j
public class DicCacheServiceImpl implements DataListenerTask, DicCacheService {
    private Cache<String,List<Map<String,Object>>> cache = CacheUtil.newLFUCache(1000);
    @Resource
    private DicService dicService;
    @Resource
    private JdbcService jdbcService;
    @Override
    public void call(DataListener dataListener, Map<String, Object> context) {

        String eventType = (String) context.get("eventType");
        String tableName = (String) context.get("tableName");
        List<String> codes = new ArrayList<>();
        if(tableName.equalsIgnoreCase("dic")){
            if(EventType.NEW.equals(eventType)){
                Map<String,Object> obj = (Map<String, Object>) context.get("obj");
                codes.add((String) obj.get("dicCode"));
            }else if(EventType.UPDATE.equals(eventType)){
                Map<String,Object> beforeObj = (Map<String, Object>) context.get("beforeObj");
                Map<String,Object> afterObj = (Map<String, Object>) context.get("afterObj");

                codes.add((String) beforeObj.get("dicCode"));
                codes.add((String) afterObj.get("dicCode"));
            }else if(EventType.DELETE.equals(eventType)){
                Map<String,Object> obj = (Map<String, Object>) context.get("obj");
                codes.add((String) obj.get("dicCode"));
            }
        }else if(tableName.equalsIgnoreCase("dic_item")){
            Long dicId = null;
            if(EventType.NEW.equals(eventType)){
                Map<String,Object> obj = (Map<String, Object>) context.get("obj");
                dicId = (Long) obj.get("parentId");
            }else if(EventType.UPDATE.equals(eventType)){
                Map<String,Object> beforeObj = (Map<String, Object>) context.get("beforeObj");
                dicId = (Long) beforeObj.get("parentId");
            }else if(EventType.DELETE.equals(eventType)){
                Map<String,Object> obj = (Map<String, Object>) context.get("obj");
                dicId = (Long) obj.get("parentId");
            }
            if(dicId != null){
                Dic dic = jdbcService.getById(Dic.class, dicId);
                if(dic != null){
                    codes.add(dic.getDicCode());
                }
            }
        }

        log.info("清理数据字典缓存,{}",codes);
        for(String code:codes){
            cache.remove(code);
        }
    }

    @Override
    public List<Map<String, Object>> options(String dicCode) {
        List<Map<String, Object>> options = cache.get(dicCode);
        if(options == null){
            synchronized (this){
                options = cache.get(dicCode);
                if(options == null){
                    options = dicService.options(dicCode);
                    if(!options.isEmpty()){
                        log.info("重建数据字典缓存,{},{}",dicCode,options);
                        cache.put(dicCode,options);
                    }
                }
            }
        }
        return options;
    }

    @Override
    public String getLabel(String code, String value) {
        if(StringUtils.isBlank(value) || StringUtils.isBlank(code)){
            return null;
        }
        List<Map<String, Object>> options = options(code);
        for(Map<String,Object> option:options){
            String label = (String) option.get("label");
            String optionValue = (String) option.get("value");
            if(value.equals(optionValue)){
                return label;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
