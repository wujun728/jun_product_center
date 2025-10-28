package io.github.wujun728.admin.common.service.impl;

import io.github.wujun728.admin.common.service.DbTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service("dbTemplateService")
@Slf4j
public class DbTemplateServiceImpl implements DbTemplateService {

    @Resource
    private VelocityEngine dbVelocityEngine;

    @Override
    public String getValue(String templateCode, Map<String, Object> params) {

        Context context  = new VelocityContext();
        //以下都是 模板中的特殊符号 直接使用的话就会冲突报错
        context.put("jq", "$");
        context.put("gt", "get");
        context.put("st", "set");
        context.put("l", "(");
        context.put("r", ")");
        for(Map.Entry<String, Object> en: params.entrySet()){
            context.put(en.getKey(),en.getValue());
        }
        StringWriter writer = new StringWriter();
        dbVelocityEngine.mergeTemplate(templateCode, "UTF-8", context, writer);
        String out = writer.toString();
        try {
            writer.close();
        } catch (IOException e) {
            log.error("根据模板获取内容失败",e);
        }
        return out;
    }


}
