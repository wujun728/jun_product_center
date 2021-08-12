package com.pearadmin.common.web.session;


/**
 * Describe: HttpSessionContext对象持有者
 * Author: Heiky
 * CreateTime: 2020/12/17
 */
public class HttpSessionContextHolder {

    public static HttpSessionContext currentSessionContext() {
        return HttpSessionContext.getInstance();
    }

}
