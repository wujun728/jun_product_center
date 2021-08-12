package com.pearadmin.pro.common.tools.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pearadmin.pro.common.constant.SystemConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Describe: Servlet 工具类
 * Author: 就 眠 仪 式
 * CreateTime: 2021/2/3
 * */
public class ServletUtil {

    /**
     * Describe: 获取 HttpServletRequest 对象
     * Param null
     * Return HttpServletRequest
     * */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * Describe: 获取 HttpServletResponse 对象
     * Param null
     * Return HttpServletResponse
     * */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * Describe: 获取 HttpServletSession 对象
     * Param null
     * Return HttpServletSession
     * */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }

    /**
     * Describe: 获取 Request 请求参数
     * Param paramName
     * Return String
     * */
    public static String getParameter(String paramName){
        return ServletUtil.getRequest().getParameter(paramName);
    }

    /**
     * Describe: 获取 Request Body 请求参数
     * Param: paramName
     * Return: String
     * */
    public static JSONObject getBodyParameters(){
        try {
            InputStreamReader reader = new InputStreamReader(getRequest().getInputStream(), SystemConstant.UTF8);
            char[] buff = new char[1024];
            int length = 0;
            String body = null;
            while ((length = reader.read(buff)) != -1) {
                body = new String(buff, 0, length);
            }
            return JSON.parseObject(body);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Describe: Response 对象写出数据
     * Param: msg 消息数据
     * Return null
     * */
    public static void write(String msg) throws IOException {
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type","application/json;charset="+ SystemConstant.UTF8);
        response.setCharacterEncoding(SystemConstant.UTF8);
        response.getWriter().write(msg);
    }

    /**
     * Describe: Response 对象写出 JSON 数据
     * Param: object 消息数据
     * Return null
     * */
    public static void writeJson(Object data) throws IOException{
        write(JSON.toJSONString(data));
    }

    /**
     * Describe: Request 请求参数
     * Param: null
     * Return string
     * */
    public static String getQueryParam(){
        return getRequest().getQueryString();
    }

    /**
     * Describe: Request 请求地址
     * Param: null
     * Return string
     * */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * Describe: Request 客户端地址
     * Param: null
     * Return string
     * */
    public static String getRemoteHost(){
        String remoteHost = getRequest().getRemoteHost();
        if(remoteHost.equals("0:0:0:0:0:0:0:1")){
            remoteHost = "127.0.0.1";
        }
        return remoteHost ;
    }

    /**
     * Describe: Request 请求方法
     * Param: null
     * Return string
     * */
    public static String getMethod(){
        return getRequest().getMethod();
    }

    /**
     * Describe: Request 请求头
     * Param: name
     * Return string
     * */
    public static String getHeader(String name){
        return getRequest().getHeader(name);
    }

    /**
     * Describe: Request Agent
     * Param: name
     * Return string
     * */
    public static String getAgent(){
        return getHeader("User-Agent");
    }

    /**
     * Describe: Request 浏览器类型
     * Param: name
     * Return string
     * */
    public static String getBrowser(){
        String userAgent = getAgent();
        if (userAgent.contains("Firefox")) return "火狐浏览器";
        else if (userAgent.contains("Chrome")) return "谷歌浏览器";
        else if (userAgent.contains("Trident")) return "IE 浏览器";
        else return "你用啥浏览器";
    }

    /**
     * Describe: Request 访问来源 ( 客户端类型 )
     * Param: name
     * Return string
     * */
    public static String getSystem(){
        String userAgent = getAgent();
        if (getAgent().toLowerCase().contains("windows" )) return "Windows";
        else if (userAgent.toLowerCase().contains("mac" )) return "Mac";
        else if (userAgent.toLowerCase().contains("x11" )) return "Unix";
        else if (userAgent.toLowerCase().contains("android" )) return "Android";
        else if (userAgent.toLowerCase().contains("iphone" )) return "IPhone";
        else return "UnKnown, More-Info: " + userAgent;
    }
}
