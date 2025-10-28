package io.github.wujun728.admin.page.service.impl;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.service.FormEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("sqlApiFormEvent")
public class SqlApiFormEvent implements FormEvent {
    @Override
    public Result beforeSave(Map<String, Object> obj, String tableName, Form form) {
        log.info("beforeSave:{}",obj);
//        if(obj.get("content") != null){
//            Object content = obj.get("content");
//
//            log.info("content:{}",JSONUtil.toJsonStr(obj.get("content")));
//            obj.put("content",JSONUtil.toJsonStr(obj.get("content")));
//        }
        return null;
    }

    @Override
    public Result afterSave(Map<String, Object> obj, String tableName, Form form) {
        return null;
    }

    @Override
    public void init(Map<String, Object> obj, Form form) {
        String content = (String) obj.get("content");
        log.info("content:{}",content);
        if(StringUtils.isNotBlank(content)){
            obj.put("content",JSONUtil.parseObj(content));
        }
    }
}
