package com.wechat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP请求类
 * @author LiHong
 */
public class HttpInvoker {

	/**
	 * GET请求
	 * @param getUrl
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpGet(String getUrl) throws IOException { 
		URL getURL = new URL(getUrl); 
		HttpURLConnection connection = (HttpURLConnection) getURL.openConnection(); 
		connection.connect();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		connection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	}
	
	/**
	 * POST请求
	 * @param postUrl
	 * @param postHeaders
	 * @param postEntity
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpPost(String postUrl,Map<String, String> postHeaders, String postEntity) throws IOException {
		
		URL postURL = new URL(postUrl); 
		HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection(); 
		httpURLConnection.setDoOutput(true);                 
		httpURLConnection.setDoInput(true); 
		httpURLConnection.setRequestMethod("POST"); 
		httpURLConnection.setUseCaches(false); 
		httpURLConnection.setInstanceFollowRedirects(true); 
		httpURLConnection.setRequestProperty(" Content-Type ", " application/x-www-form-urlencoded ");

		if(postHeaders != null) {
			for(String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
			}
		}
		if(postEntity != null) {
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream()); 
			out.writeBytes(postEntity); 
			out.flush(); 
			out.close(); // flush and close 
		}
		//connection.connect(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); 
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		httpURLConnection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	} 

}
