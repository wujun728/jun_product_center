package org.myframework.commons.util.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.myframework.commons.util.StringUtils;

/**
 *
 * <ol>
 * 对于HTTPCLIENT类的简易封装:
 * 
 * <li>GET请求 contentType=application/x-www-form-urlencoded</li>
 * <li>POST请求 contentType=application/x-www-form-urlencoded</li>
 * <li>FILE POST请求 Content-Type: multipart/form-data; boundary=${bound}</li>
 * <li>Stream POST请求 如微信类的HTTP请求 Content-Type= "application/octet-stream"</li>
 * <li>相关网络资料： HTTP协议详解:
 * {@link http://blog.csdn.net/lmh12506/article/details/7794512} HTTP
 * CONTENT-TYPE {@link http://tool.oschina.net/commons} JQUERY提交AJAX时如何使用
 * {@link http://www.w3cschool.cc/jquery/ajax-ajax.html}</li>
 * <li>其他REST调用的封装 @see org.springframework.web.client.RestTemplate</li>
 * <li>@see RestTemplateTest</li> 
 * POST调用方法代码举例： RestTemplate restTemplate = new
 * RestTemplate(); MultiValueMap<String, String> request = new
 * LinkedMultiValueMap<String, String>(); request.add("roleId", "2");
 * request.add("roleName", "wanghui3"); Map map = restTemplate.postForObject(url
 * , request, Map.class);
 * 
 * <li>jquery post时content-type的几种取值  http://zccst.iteye.com/blog/2180127</li>
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年7月2日
 *
 */
public class HttpClientUtils {
	/**
	 * 微信类HTTP请求
	 * <li>Stream POST请求 如微信类的HTTP请求 Content-Type= "application/octet-stream"
	 * </li>
	 * 
	 * <li>JQUERY请求时需要设置contentType:"application/octet-stream"
	 * $.ajax({
				url : {URL},
				type : 'POST',
				contentType:"application/octet-stream", 
				data : {DATA},
				success : function(data) {
				    $("#content").html(data);
		    	}
		});
	 * </li>
	 * 
	 * @param param
	 * @param url
	 * @param xml
	 *            HTTP 内容主体的内容的 Stream 对象。
	 * @return
	 * @throws Exception
	 */
	public static String simplePostBody(String url,
			Map<String, ? extends Object> param, String xml) throws Exception {
		//
		if (param != null) {
			Set<String> keys = param.keySet();
			if (url.indexOf("?") == -1)
				url = url + "?1=1";
			for (Iterator<String> iterator = keys.iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				url = url + "&" + name + "="
						+ StringUtils.asString(param.get(name));
			}
		}
		String rtn = Request.Post(url)
				.bodyString(xml, ContentType.DEFAULT_BINARY).execute()
				.returnContent().asString();
		return rtn;
	}

	/**
	 * 上传文件
	 * <li>http文件上传协议详解
	 * {@link http://blog.csdn.net/five3/article/details/7181521}</li>
	 * 
	 * <li> 
	 * <form method="POST" enctype="multipart/form-data"  ></form>
	 * 
	 * http协议头包含：contentType:"multipart/form-data"
	 * 需要浏览器支持 FormData 对象， 目前新版的Firefox 与 Chrome 等支持HTML5的浏览器完美的支持这个对象 
	 * 开源JS插件为了兼容各种浏览器，只能使用iframe提交FORM表单的方式来实现AJAX提交的效果
	 * http://zccst.iteye.com/blog/2180127
	 * </li>
	 * @param param
	 * @param url
	 * @param file
	 * @param fileInputName
	 *            对应FORM表单的file控件
	 * @return
	 * @throws Exception
	 */
	public static String simplePostFile(String url,
			Map<String, ? extends Object> param, File file,
			String fileInputName) throws Exception {
		fileInputName = fileInputName == null ? "file" : fileInputName;
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addPart(fileInputName, new FileBody(file));
		//
		if (param != null) {
			Set<String> keys = param.keySet();
			for (Iterator<String> iterator = keys.iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				builder.addTextBody(name,
						StringUtils.asString(param.get(name)));
			}
		}
		HttpEntity entity = builder.build();
		String rtn = Request.Post(url).body(entity).execute().returnContent()
				.asString();
		return rtn;
	}

	/**
	 *
	 * 常规表单POST(默认的FORM提交方式)
	 * <li>Http协议详解 GET,post提交的差异
	 * {@link http://blog.csdn.net/chao_xun/article/details/39611087}
	 * </li>
	 * 
	 * <li>
	 * http协议头包含：contentType:"application/x-www-form-urlencoded;charset=utf-8"
	 * JQuery 和 QWrap 的 Ajax，Content-Type 
	 * 默认值都是「application/x-www-form-urlencoded;charset=utf-8」
	 * </li>
	 * @param param
	 *            对应表单input输入
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String simplePost(String url,
			Map<String, ? extends Object> param) throws Exception {
		Form form = Form.form();
		Set<String> keys = param.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String name = iterator.next();
			form.add(name, StringUtils.asString(param.get(name)));
		}
		List<NameValuePair> data = form.build();
		String rtn = Request.Post(url).bodyForm(data, Consts.UTF_8).execute()
				.returnContent().asString();
		return rtn;
	}

	/**
	 * 常规GET
	 * 
	 * @param param
	 *            对应表单input输入
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String simpleGet(String url,
			Map<String, ? extends Object> param) throws Exception {
		Set<String> keys = param.keySet();
		if (url.indexOf("?") == -1)
			url = url + "?1=1";
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String name = iterator.next();
			url = url + "&" + name + "="
					+ StringUtils.asString(param.get(name));
		}
		String rtn = Request.Get(url).execute().returnContent().asString();
		return rtn;
	}

	/**
	 * 常规GET
	 * 
	 * @param param
	 *            对应表单input输入
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String simpleGet(String url) throws Exception {
		String rtn = Request.Get(url).execute().returnContent().asString();
		return rtn;
	}

	/**
	 * 上传文件
	 * <li>http文件上传协议详解
	 * {@link http://blog.csdn.net/five3/article/details/7181521}</li>
	 * 
	 * @param url
	 * @param file
	 * @param fileInputName
	 *            对应FORM表单的file控件
	 * @return
	 * @throws Exception
	 */
	public static String simplePostFile(String url, File file,
			String fileInputName) throws Exception {
		return simplePostFile(url, null, file, fileInputName);
	}

	/**
	 * 微信类HTTP请求
	 * <li>Stream POST请求 如微信类的HTTP请求 Content-Type= "application/octet-stream"
	 * </li>
	 * 
	 * @param url
	 * @param xml
	 *            HTTP 内容主体的内容的 Stream 对象。
	 * @return
	 * @throws Exception
	 */
	public static String simplePostBody(String url, String xml)
			throws Exception {
		String rtn = Request.Post(url)
				.bodyString(xml, ContentType.DEFAULT_BINARY).execute()
				.returnContent().asString();
		return rtn;
	}

	/**
	 * 微信类HTTP请求
	 * <li>Stream POST请求 如微信类的HTTP请求 Content-Type= "application/octet-stream"
	 * </li>
	 * 
	 * @param url
	 * @param xml
	 *            HTTP 内容主体的内容的 Stream 对象。
	 * @return // ContentType.DEFAULT_BINARY
	 * @throws Exception
	 */

	public static String simplePostBody(String url, String xml,
			ContentType contentType) throws Exception {
		String rtn = Request.Post(url).bodyString(xml, contentType).execute()
				.returnContent().asString();
		return rtn;
	}
	//

	public static String buildGetUrl(String url,
			Map<String, ? extends Object> param)
					throws UnsupportedEncodingException {
		//
		if (param != null) {
			Set<String> keys = param.keySet();
			if (url.indexOf("?") == -1)
				url = url + "?1=1";
			for (Iterator<String> iterator = keys.iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				String value = StringUtils.asString(param.get(name));
				url = url + "&" + name + "="
						+ URLEncoder.encode(value, "utf-8");
			}
		}
		return url;
	}

	public static String buildGetUrl(String url, String urlParam) {
		if (url.indexOf("?") == -1)
			url = url + "?1=1";
		return url + urlParam;
	}
	
}
