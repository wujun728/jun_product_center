package com.lu.web.core.beetl;

import com.lu.core.utils.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-14 20:18:00
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    protected void initOther() {
        //全局共享变量
        Map<String, Object> shared = new HashMap<>();
        shared.put("systemName", "庐布特");
        shared.put("ctxPath", "");
        shared.put("driver", "com.mysql.jdbc.Driver");

        groupTemplate.setSharedVars(shared);

        //全局共享变量
//        BeetlConfiguration beetlConfiguration = beetlConfig.beetlConfiguration();
//        Map<String, Object> shared = beetlConfiguration.getGroupTemplate().getSharedVars();
//        shared.put("avatar", ToolUtil.isEmpty(ShiroKit.getUser().getAvatar()) ? SystemCache.baseProperties.getAvatar() : ShiroKit.getUser().getAvatar());
//        shared.put("name", ShiroKit.getUser().getName());
//        beetlConfiguration.getGroupTemplate().setSharedVars(shared);


        //全局共享方法
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());

    }
}
