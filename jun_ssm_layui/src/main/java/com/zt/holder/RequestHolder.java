package com.zt.holder;

import com.zt.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {
    public static final ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    public static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(SysUser user){
        userHolder.set(user);
    }

    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    public static HttpServletRequest getRequest(){
        return requestHolder.get();
    }

    public static void removeHolder(){
        userHolder.remove();
        requestHolder.remove();
    }
}
