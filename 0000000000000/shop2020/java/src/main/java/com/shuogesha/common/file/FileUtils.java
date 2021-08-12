package com.shuogesha.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ResourceUtils;

/**
 * 文件删除工具类
 * @author zhaohaiyuan
 *
 */
public class FileUtils { 
	
	public static void del(HttpServletRequest request, String videoUrl) {
		try {
			if (StringUtils.isBlank(videoUrl)) {
				return;
			}
			//先判断是否远程
			AliOSSUpload.delete(videoUrl); 
			String path = request.getRealPath("/");   
	  		File lastfile =  new  File(path+ "/" +videoUrl ); // 输出文件 
	  		if (lastfile.exists() && lastfile.isFile()) {
            	lastfile.delete();
            }
		 } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
	}   
	 
	public static void post(String url,TreeMap<String, Object> param) {
		try {
			final HttpClient client = new HttpClient();
 			try {
				GetMethod httpGet = new GetMethod(url);   
			    client.executeMethod(httpGet);  
			} catch (Exception e) {
				} finally {
				// 释放资源
 			} 
	         
		} catch (Exception e) { 
		}

	}
        
}

class MyThread implements Runnable{ 
	public String url;
	public TreeMap<String, Object> param;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
	
	public TreeMap<String, Object> getParam() {
		return param;
	}

	public void setParam(TreeMap<String, Object> param) {
		this.param = param;
	}

	public MyThread() {
		super();
	}

	public MyThread(String url, TreeMap<String, Object> param) {
		super();
		this.url = url;
		this.param = param;
	}

	@Override public void run() { 
		FileUtils.post(url, param);
	}
}
 
 
 
