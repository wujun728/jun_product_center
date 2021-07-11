package me.wuwenbin.noteblogv5.controllers;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.User;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * created by Wuwenbin on 2019-07-26 at 10:09
 *
 * @author wuwenbin
 */
public abstract class BaseController {

    private static final String LOGIN_URL = "/login?sessionInvalid=1";


    /**
     * 基路径
     *
     * @param request
     * @return
     */
    protected static String basePath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        //        return "http://wuwenbin.me/";
    }

    /**
     * 返回值类型为Map<String, Object>
     *
     * @param properties
     * @return
     */
    protected static Map<String, Object> getParameterMap(Map<String, String[]> properties) {
        Map<String, Object> returnMap = new HashMap<>(16);
        Iterator<Map.Entry<String, String[]>> iterator = properties.entrySet().iterator();
        String name;
        String value = "";
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            name = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else {
                String[] values = (String[]) valueObj;
                //用于请求参数中有多个相同名称
                for (String value1 : values) {
                    value = value1 + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 添加排序字段
     *
     * @param page
     * @param orderDirection
     * @param orderField
     * @param <T>
     * @return
     */
    protected <T> void addPageOrder(Page<T> page, String orderDirection, String orderField) {
        String desc = "desc";
        if (desc.equalsIgnoreCase(orderDirection)) {
            page.addOrder(OrderItem.desc(orderField));
        } else {
            page.addOrder(OrderItem.asc(orderField));
        }
    }

    /**
     * 处理结果
     *
     * @param res
     * @param ok
     * @param err
     * @return
     */
    public ResultBeanObj handle(boolean res, String ok, String err) {
        return res ? ResultBeanObj.ok(ok) : ResultBeanObj.error(err);
    }

    /**
     * jsr303验证处理的错误信息
     *
     * @param fieldErrors
     * @return
     */
    protected ResultBeanObj ajaxJsr303(List<FieldError> fieldErrors) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(":").append(error.getDefaultMessage()).append("<br/>");
        }
        return ResultBeanObj.error(message.toString());
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    protected boolean isAjaxRequest(HttpServletRequest request) {
        return StrUtil.isNotBlank(request.getHeader("x-requested-with")) && "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

    /**
     * 是否为 json 请求
     *
     * @param request
     * @return
     */
    protected boolean isJson(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("application/json");
    }

    /**
     * 是否为get请求
     *
     * @param request
     * @return
     */
    protected boolean isGetRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return "GET".equalsIgnoreCase(method);
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    protected boolean isRouter(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("text/html") && !isJson(request) && isAjaxRequest(request) && isGetRequest(request);
    }


    /**
     * 统一处理ajax的请求返回信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void handleAjaxRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //用户未登录或登录时效过期，请重新登录！
        final String message = "\u7528\u6237\u672a\u767b\u5f55\u6216\u767b\u5f55\u65f6\u6548\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\uff01";
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (isRouter(request)) {
            JSONObject jsonObject = JSONUtil.createObj();
            Map<String, Object> respMap = ResultBeanObj.custom(ResultBeanObj.LOGIN_INVALID, message, LOGIN_URL);
            respMap.put("base", basePath(request));
            jsonObject.putAll(respMap);
            response.getWriter().write(jsonObject.toString());
        } else if (isAjaxRequest(request) && !isRouter(request)) {
            JSONObject jsonObject = JSONUtil.createObj();
            Map<String, Object> respMap = ResultBeanObj.custom(ResultBeanObj.LOGIN_INVALID, message, LOGIN_URL);
            respMap.put("base", basePath(request));
            jsonObject.putAll(respMap);
            jsonObject.putAll(respMap);
            response.getWriter().write(jsonObject.toString());
        } else {
            response.sendRedirect(LOGIN_URL.concat("&redirectUrl=").concat(request.getRequestURL().toString()));
        }
    }

    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(NBV5.SESSION_USER_KEY, user);
        //30分钟
        request.getSession().setMaxInactiveInterval(30 * 60);
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(NBV5.SESSION_USER_KEY);
    }

    protected void invalidSessionUser(HttpServletRequest request) {
        request.getSession().removeAttribute(NBV5.SESSION_USER_KEY);
        request.getSession().invalidate();
    }

    protected void updateSessionUser(HttpServletRequest request, User newUser) {
        request.getSession().removeAttribute(NBV5.SESSION_USER_KEY);
        setSessionUser(request, newUser);
    }
}
