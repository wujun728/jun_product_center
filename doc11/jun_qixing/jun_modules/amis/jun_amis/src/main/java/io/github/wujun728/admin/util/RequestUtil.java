package io.github.wujun728.admin.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getBasePath(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String path = url.substring(0,url.length()-uri.length());
        return path;
    }
}
