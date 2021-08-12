package com.shuogesha.common.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
 
/**
 * HttpClient工具类，版本：3.1
 *
 * @author qiaojun
 */
public class HttpClient3Util
{
    private static String VERSION = "(3.1)";
 
    private static HttpClient CLIENT;
 
    private static class SingletonHolder
    {
        private static HttpClient3Util INSTANCE = new HttpClient3Util();
    }
 
    public static HttpClient3Util getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
 
    private HttpClient3Util()
    {
        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.getParams().setConnectionTimeout(10000);
        manager.getParams().setSoTimeout(10000);
        manager.getParams().setDefaultMaxConnectionsPerHost(20);
        manager.getParams().setMaxTotalConnections(200);
        // manager.closeIdleConnections(60L);
        // manager.deleteClosedConnections();
 
        // HttpClientParams params = new HttpClientParams();
        // params.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false)); // 重试机制
        HttpClientParams params = new HttpClientParams(DefaultHttpParams.getDefaultParams()); // 使用默认配置
        CLIENT = new HttpClient(params, manager);
    }
 
    public String doGet(String url, Map<String, Object> params)
    {
        return httpGet(url, params, "utf-8");
    }
 
    public String doPostMap(String url, Map<String, Object> params)
    {
        return httpPost(url, params, "utf-8");
    }
 
    public String doPostJson(String url, Object obj)
    {
        return httpPost(url, null != obj ? JSON.toJSONString(obj) : null, "utf-8", 0);
    }
 
    public String doPostStr(String url, String jsonStr)
    {
        return httpPost(url, jsonStr, "utf-8", 0);
    }
 
    public String httpGet(String url, Map<String, Object> params, String chartSet)
    {
        GetMethod method = initGetMethod(url);
        if (null != params && !params.isEmpty())
        {
             method.setParams(assembleMethodParams(params));
        }
        return sendGet(method, chartSet);
    }
 
    public String httpPost(String url, Map<String, Object> params, String chartSet)
    {
        PostMethod method = initPostMethod(url);
        if (null != params && !params.isEmpty())
        {
             method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, chartSet);
            method.setRequestBody(assembleNvParams(params));
        }
        return sendPost(method, chartSet);
    }
 
    public String httpPost(String url, String str, String chartSet, int contentType)
    {
        PostMethod method = initPostMethod(url);
        if (StringUtils.isBlank(str))
        {
             RequestEntity se = null;
            try
            {
                se = new StringRequestEntity(str, 0 == contentType ? "application/json" : "application/x-www-form-urlencoded", chartSet);
            }
            catch (UnsupportedEncodingException e)
            {
             }
            method.setRequestEntity(se);
        }
        return sendPost(method, chartSet);
    }
 
    private String sendGet(GetMethod method, String chartSet)
    {
        String result = null;
        try
        {
            int statusCode = CLIENT.executeMethod(method);
            if(HttpStatus.SC_OK == statusCode)
            {
                result = new String(method.getResponseBodyAsString().getBytes(), chartSet);
            }
            else
            {
             }
        }
        catch (IOException e)
        {
         }
        finally
        {
            method.releaseConnection();
        }
         return result;
    }
 
    private String sendPost(PostMethod method, String chartSet)
    {
        String result = null;
        try
        {
            // LogUtil.printInfo("HTTP(POST) CONTENT: " + Arrays.toString(method.getParameters()));
            int statusCode = CLIENT.executeMethod(method);
            if (HttpStatus.SC_OK == statusCode)
            {
                if (null != method.getResponseBodyAsStream())
                {
                    // result = new String(method.getResponseBodyAsString().getBytes(), chartSet);
                    InputStreamReader isReader = new InputStreamReader(method.getResponseBodyAsStream());
                    BufferedReader bufferReader = new BufferedReader(isReader);
                    result = bufferReader.lines().collect(Collectors.joining());
                    bufferReader.close();
                    isReader.close();
                }
            }
            else
            {
             }
        }
        catch (Exception e)
        {
         }
        finally
        {
            method.releaseConnection();
        }
         return result;
    }
 
    private GetMethod initGetMethod(String url)
    {
         return new GetMethod(StringUtils.deleteWhitespace(url));
    }
 
    private PostMethod initPostMethod(String url)
    {
         return new PostMethod(StringUtils.deleteWhitespace(url));
    }
 
    /**
     * 组装httpPost请求参数
     * @return NameValuePair[]
     */
    private NameValuePair[] assembleNvParams(Map<String, Object> data)
    {
        List<NameValuePair> nameValueList = new ArrayList<>();
        data.forEach((key, value) -> nameValueList.add(new NameValuePair(key, value.toString())));
        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
    }
 
    /**
     * 组装Map请求参数
     * @return HttpMethodParams
     */
    private HttpMethodParams assembleMethodParams(Map<String, Object> data)
    {
        HttpMethodParams params = new HttpMethodParams();
        data.forEach(params::setParameter);
        return params;
    }
}