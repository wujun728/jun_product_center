package com.pearadmin.common.plugin.submit.impl;

import com.alibaba.fastjson.JSONObject;
import com.pearadmin.common.plugin.submit.RepeatSubmitInterceptor;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe: 判断请求url和数据是否和上一次相同，如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor
{
    /**
     * 常用字符串标识
     * */
    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    public final String SESSION_REPEAT_KEY = "repeatData";

    /**
     * 间隔时间，单位:秒 默认10秒
     *
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    private int intervalTime = 10;

    public void setIntervalTime(int intervalTime)
    {
        this.intervalTime = intervalTime;
    }

    @Override
    public boolean isRepeatSubmit(HttpServletRequest request)
    {
        // 本次参数及系统时间
        String nowParams = JSONObject.toJSONString(request.getParameterMap());
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 请求地址（作为存放session的key值）
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
     * @param nowMap 本次参数
     * @param preMap 上次参数
     * @return boolean 是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     * @param nowMap 本次时间
     * @param preMap 上次时间
     * @return 注释
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        return (time1 - time2) < (this.intervalTime * 1000L);
    }
}
