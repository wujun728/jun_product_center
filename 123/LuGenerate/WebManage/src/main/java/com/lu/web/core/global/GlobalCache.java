package com.lu.web.core.global;

import com.lu.web.core.global.bean.BaseProperties;

/**
 * @program 戒毒执法平台
 * @description: 系统配置缓存
 * @author: zhanglu
 * @create: 2019-11-18 14:17:00
 */
public class GlobalCache {

    //系统基本配置
    public static BaseProperties baseProperties = new BaseProperties();

    public static void init() throws Exception {

        //项目根目录
        String rootPath = System.getProperty("user.dir");
        System.out.println("项目根目录 ---> " + rootPath);

        baseProperties.setRootPath(rootPath);
    }

}
