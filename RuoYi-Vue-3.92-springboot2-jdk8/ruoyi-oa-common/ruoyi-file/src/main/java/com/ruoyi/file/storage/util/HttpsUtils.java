package com.ruoyi.file.storage.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpsUtils {
    private static final int MAX_TIMEOUT = 6000;
    private static final Logger logger = LoggerFactory.getLogger(HttpsUtils.class);
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // Validate connections after 1 sec of inactivity
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url url
     * @return 返回数据流
     */
    public static InputStream doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据， 带请求头
     *
     * @param url    url
     * @param header header
     * @return 返回数据流
     */
    public static InputStream doGet(String url, Map<String, Object> header) {
        HttpEntity httpEntity = doGetHttpEntity(url, new HashMap<String, Object>(), header);
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return inputStream;
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据， 带请求头
     *
     * @param url url
     * @return 返回字符串结果
     */
    public static String doGetString(String url) {
        return doGetString(url, null);
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据， 带请求头
     *
     * @param url    url
     * @param header header
     * @return 返回字符串结果
     */
    public static String doGetString(String url, Map<String, Object> header) {
        HttpEntity httpEntity = doGetHttpEntity(url, new HashMap<String, Object>(), header);
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 发送 GET 请求（HTTP），带输入数据， 带请求头
     *
     * @param url    url
     * @param params params
     * @param header header
     * @return 返回字符串结果
     */
    public static String doGetString(String url, Map<String, Object> params, Map<String, Object> header) {
        HttpEntity httpEntity = doGetHttpEntity(url, params, header);
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }


    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url    url
     * @param params 参数
     * @param header 请求头
     * @return 返回
     */
    public static HttpEntity doGetHttpEntity(String url, Map<String, Object> params, Map<String, Object> header) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        InputStream instream = null;
        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            if (header != null) {
                httpGet.setHeader("Referer", (String) header.get(HttpHeaders.REFERER));
                httpGet.setHeader("Accept", (String) header.get(HttpHeaders.ACCEPT));
                httpGet.setHeader("Accept-Encoding", (String) header.get(HttpHeaders.ACCEPT_ENCODING));
                httpGet.setHeader("Accept-Language", (String) header.get(HttpHeaders.ACCEPT_LANGUAGE));
                httpGet.setHeader("Cache-Control", (String) header.get(HttpHeaders.CACHE_CONTROL));
                httpGet.setHeader("Connection", (String) header.get(HttpHeaders.CONNECTION));
                httpGet.setHeader("Pragma", (String) header.get(HttpHeaders.PRAGMA));
                httpGet.setHeader("Cookie", (String) header.get("Cookie"));
                httpGet.setHeader("User-Agent", (String) header.get(HttpHeaders.USER_AGENT));
                httpGet.setHeader("token", (String) header.get("token"));

            }
            HttpResponse response = httpClient.execute(httpGet);
            httpEntity = response.getEntity();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return httpEntity;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl url
     * @param header 请求头
     * @return 返回
     */
    public static String doPost(String apiUrl, Map<String, Object> header) {
        return doPost(apiUrl, new HashMap<String, Object>(), header);
    }

    /**
     * 发送 POST 请求，K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @param header 请求头
     * @return 返回
     */
    public static String doPost(String apiUrl, Map<String, Object> params, Map<String, Object> header) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            if (header != null) {
                httpPost.setHeader("Referer", (String) header.get(HttpHeaders.REFERER));
                httpPost.setHeader("Accept", (String) header.get(HttpHeaders.ACCEPT));
                httpPost.setHeader("Accept-Encoding", (String) header.get(HttpHeaders.ACCEPT_ENCODING));
                httpPost.setHeader("Accept-Language", (String) header.get(HttpHeaders.ACCEPT_LANGUAGE));
                httpPost.setHeader("Cache-Control", (String) header.get(HttpHeaders.CACHE_CONTROL));
                httpPost.setHeader("Connection", (String) header.get(HttpHeaders.CONNECTION));
                httpPost.setHeader("Pragma", (String) header.get(HttpHeaders.PRAGMA));
                httpPost.setHeader("Cookie", (String) header.get("Cookie"));
                httpPost.setHeader("User-Agent", (String) header.get(HttpHeaders.USER_AGENT));
                httpPost.setHeader("token", (String) header.get("token"));

            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求，JSON形式
     *
     * @param apiUrl url
     * @param json   json对象
     * @return 返回
     */
    public static String doPost(String apiUrl, Object json) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return 返回
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (GeneralSecurityException e) {
            logger.error(e.getMessage());
        }
        return sslsf;
    }
}
