package com.itstyle.modules.weixinpay.util;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;

import com.itstyle.common.constants.Constants;
/**
 * 退款认证
 * 创建者 科帮网
 * 创建时间	2017年7月31日
 *
 */
public class ClientCustomSSL {
	 public static String doRefund(String url,String data) throws Exception {  
        /** 
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的 
         */  
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");  
        File certfile = ResourceUtils.getFile("classpath:cert"+ Constants.SF_FILE_SEPARATOR + ConfigUtil.CERT_PATH);
        FileInputStream instream = new FileInputStream(certfile);
        try {  
            keyStore.load(instream, ConfigUtil.MCH_ID.toCharArray());
        } finally {  
            instream.close();  
        }  
        SSLContext sslcontext = SSLContexts.custom()  
                .loadKeyMaterial(keyStore, ConfigUtil.MCH_ID.toCharArray())
                .build();  
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                sslcontext,  
                new String[] { "TLSv1" },  
                null,  
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
        CloseableHttpClient httpclient = HttpClients.custom()  
                .setSSLSocketFactory(sslsf)  
                .build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new StringEntity(data, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();
				String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
				EntityUtils.consume(entity);
				return jsonStr;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}  
}
