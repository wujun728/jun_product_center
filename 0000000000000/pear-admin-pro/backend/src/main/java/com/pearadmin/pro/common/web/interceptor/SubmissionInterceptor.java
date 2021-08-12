package com.pearadmin.pro.common.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.common.web.interceptor.annotation.Submission;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.common.web.domain.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 重 复 提 交 拦 截 器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Component
public class SubmissionInterceptor extends HandlerInterceptorAdapter {

    /**
     * 参数
     * */
    public final String REPEAT_PARAMS = "repeatParams";

    /**
     * 时间
     * */
    public final String REPEAT_TIME = "repeatTime";

    /**
     * 数据
     * */
    public final String SESSION_REPEAT_KEY = "repeatData";

    /**
     * 间隔时间，单位:秒 默认10秒
     *
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    private int intervalTime = 10;

    /**
     * 设置，间隔时间
     * */
    public void setIntervalTime(int intervalTime)
    {
        this.intervalTime = intervalTime;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Submission annotation = method.getAnnotation(Submission.class);
            if(annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    ServletUtil.writeJson(Result.failure(ResultCode.REPEAT_SUBMIT));
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    public boolean isRepeatSubmit(HttpServletRequest request) throws Exception{
        String nowParams = JSON.toJSONString(request.getParameterMap());
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        String url = request.getRequestURI();

        HttpSession session = request.getSession();
        Object sessionObj = session.getAttribute(SESSION_REPEAT_KEY);
        if (sessionObj != null)
        {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url))
            {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap))
                {
                    return true;
                }
            }
        }
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        sessionMap.put(url, nowDataMap);
        session.setAttribute(SESSION_REPEAT_KEY, sessionMap);
        return false;
    }


    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < (this.intervalTime * 1000))
        {
            return true;
        }
        return false;
    }
}
