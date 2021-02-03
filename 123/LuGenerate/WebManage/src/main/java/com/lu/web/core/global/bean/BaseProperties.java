package com.lu.web.core.global.bean;

import org.springframework.context.annotation.Configuration;

/**
 * @program 戒毒执法平台
 * @description: 系统基本配置
 * @author: zhanglu
 * @create: 2019-11-18 14:17:00
 */
@Configuration
public class BaseProperties {

    //根目录
    private String rootPath;

    //

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
