package io.github.wujun728.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import java.io.IOException;
import java.io.StringWriter;

@Slf4j
public class TestTemplate {
    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        // ve.setProperty(Velocity.OUTPUT_ENCODING, charsetStr);
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, true); // 使用缓存
        ve.setProperty(Velocity.RESOURCE_LOADER, "str");
        ve.setProperty("str.resource.loader.class", MyStringResourceLoader.class.getName());
        ve.init();

        Context context  = new VelocityContext();
        //以下都是 模板中的特殊符号 直接使用的话就会冲突报错
        context.put("jq", "$");
        context.put("gt", "get");
        context.put("st", "set");
        context.put("l", "(");
        context.put("r", ")");

        StringWriter writer = new StringWriter();

        ve.mergeTemplate("test.vm", "UTF-8", context, writer);
        String out = writer.toString();
        try {
            writer.close();
        } catch (IOException e) {
            log.error("根据模板获取内容失败",e);
        }
        log.info(out);
    }
}
