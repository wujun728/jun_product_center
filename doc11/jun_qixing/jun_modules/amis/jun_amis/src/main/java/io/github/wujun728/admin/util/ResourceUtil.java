package io.github.wujun728.admin.util;


import cn.hutool.core.io.IoUtil;

import java.io.InputStream;

public class ResourceUtil {
    //用相对resources的路径,比如 pdf/css/common.css
    public static InputStream getIn(String path){
        return ResourceUtil.class.getClassLoader().getResourceAsStream(path);
    }

    public static String getStr(String path){
        InputStream in = getIn(path);
        String str = IoUtil.readUtf8(in);
        IoUtil.close(in);
        return str;
    }
}
