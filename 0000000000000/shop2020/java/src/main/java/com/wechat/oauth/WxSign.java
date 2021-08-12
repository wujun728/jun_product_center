package com.wechat.oauth;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

 
/**
 * paySign
 *
 */
public class WxSign {

	private static String characterEncoding = "UTF-8";

	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, Object> parameters,
			String key) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);  
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		return sign;
	}

	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util
				.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	  public static String getRequestXml(SortedMap<String,Object> parameters){
	        StringBuffer sb = new StringBuffer();
	        sb.append("<xml>");
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while(it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String key = (String)entry.getKey();
	            String value = (String)entry.getValue();
	            if ("attach".equalsIgnoreCase(key)||"body".equalsIgnoreCase(key)||"sign".equalsIgnoreCase(key)) {
	                sb.append("<"+key+">"+"<![CDATA["+value+"]]></"+key+">");
	            }else {
	                sb.append("<"+key+">"+value+"</"+key+">");
	            }
	        }
	        sb.append("</xml>");
	        return sb.toString();
	    }
	  
	  
	  //请求方法
	  public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
	        try {
	           
	            URL url = new URL(requestUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	          
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            // 设置请求方式（GET/POST）
	            conn.setRequestMethod(requestMethod);
	            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	            // 当outputStr不为null时向输出流写数据
	            if (null != outputStr) {
	                OutputStream outputStream = conn.getOutputStream();
	                // 注意编码格式
	                outputStream.write(outputStr.getBytes("UTF-8"));
	                outputStream.close();
	            }
	            // 从输入流读取返回内容
	            InputStream inputStream = conn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String str = null;
	            StringBuffer buffer = new StringBuffer();
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }
	            // 释放资源
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            inputStream = null;
	            conn.disconnect();
	            return buffer.toString();
	        } catch (ConnectException ce) {
	            System.out.println("连接超时：{}"+ ce);
	        } catch (Exception e) {
	        	System.out.println("https请求异常：{}"+ e);
	        }
	        return null;
	    }
	   
	  //xml解析
	  public static Map doXMLParse(String strxml) throws JDOMException, IOException {
	        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

	        if(null == strxml || "".equals(strxml)) {
	            return null;
	        }
	        
	        Map m = new HashMap();
	        
	        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
	        SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(in);
	        Element root = doc.getRootElement();
	        List list = root.getChildren();
	        Iterator it = list.iterator();
	        while(it.hasNext()) {
	            Element e = (Element) it.next();
	            String k = e.getName();
	            String v = "";
	            List children = e.getChildren();
	            if(children.isEmpty()) {
	                v = e.getTextNormalize();
	            } else {
	                v = getChildrenText(children);
	            }
	            
	            m.put(k, v);
	        }
	        
	        //关闭流
	        in.close();
	        
	        return m;
	    }
	  
	  public static String getChildrenText(List children) {
	        StringBuffer sb = new StringBuffer();
	        if(!children.isEmpty()) {
	            Iterator it = children.iterator();
	            while(it.hasNext()) {
	                Element e = (Element) it.next();
	                String name = e.getName();
	                String value = e.getTextNormalize();
	                List list = e.getChildren();
	                sb.append("<" + name + ">");
	                if(!list.isEmpty()) {
	                    sb.append(getChildrenText(list));
	                }
	                sb.append(value);
	                sb.append("</" + name + ">");
	            }
	        }
	        
	        return sb.toString();
	    }
	 
}
