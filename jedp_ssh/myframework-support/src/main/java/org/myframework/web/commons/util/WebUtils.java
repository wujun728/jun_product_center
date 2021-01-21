package org.myframework.web.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.MultiValueMap;
import org.myframework.commons.util.Assert;
import org.myframework.commons.util.PatternMatchUtils;
import org.myframework.commons.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * 常用WEB工具
 * <ol>
 * <li>{@link }</li>
 *
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class WebUtils {

	public final static Charset UTF8 = Charset.forName("UTF-8");

	/**
	 * Default content type: "application/json".
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";

	/**
	 * Default content type for JSONP: "application/javascript".
	 */
	public static final String DEFAULT_JSONP_CONTENT_TYPE = "application/javascript";

	public static final String TEMP_DIR_CONTEXT_ATTRIBUTE = "javax.servlet.context.tempdir";

	public WebUtils() {

	}

	/**
	 * 获取客户端真实IP
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * IP是否符合规则列表
	 * 
	 * @param patterns
	 * @param request
	 * @return
	 */
	public static boolean isMatchIp(String[] patterns,
			HttpServletRequest request) {
		String clientIp = getIpAddr(request);
		return PatternMatchUtils.simpleMatch(patterns, clientIp);
	}

	/**
	 * URL是否符合规则列表
	 * 
	 * @param patterns
	 * @param request
	 * @return
	 */
	public static boolean isMatchUrl(String[] patterns,
			HttpServletRequest request) {
		for (String url : patterns) {
			if (request.getPathInfo().indexOf(url) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取多值MAP
	 *
	 * @param request
	 * @return
	 */
	public static Map<String, List<String>> getMultiValueMap(
			HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, List<String>> valueMap = new HashMap<String, List<String>>();
		for (String paramKey : paramMap.keySet()) {
			String[] values = paramMap.get(paramKey);
			valueMap.put(paramKey, Arrays.asList(values));
		}
		return valueMap;
	}

	/**
	 * 获取单值MAP
	 *
	 * @param request
	 * @return
	 */
	public static Map<String, String> getSingleValueMap(
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String[]> paramMap = request.getParameterMap();
		for (String paramKey : paramMap.keySet()) {
			String[] values = paramMap.get(paramKey);
			String value = StringUtils
					.collectionToDelimitedString(Arrays.asList(values), ",");
			result.put(paramKey, value);
		}
		return result;
	}

	/**
	 * 将OBJECT以application/json 或application/javascript 输出
	 * 
	 * @param obj
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void writeJson(Object obj, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();
		// ObjectMapper mapper = new ObjectMapper();
		// String text = mapper.writeValueAsString( obj );
		String text = JSONObject.toJSONString(obj);
		String jsonpFunction = getJsonpParameterValue(request);
		response.setCharacterEncoding("UTF-8");
		if (jsonpFunction != null) {
			response.setContentType(DEFAULT_JSONP_CONTENT_TYPE);
			text = jsonpFunction + "(" + text + ")";
		} else {
			response.setContentType(DEFAULT_CONTENT_TYPE);
		}
		byte[] bytes = text.getBytes(UTF8);
		out.write(bytes);
	}

	/**
	 * 根据参数名 "jsonp", "callback"对应的值，即JS函数名
	 * 
	 * @param request
	 * @return
	 */
	private static String getJsonpParameterValue(HttpServletRequest request) {
		Set<String> jsonpParameterNames = new LinkedHashSet<String>(
				Arrays.asList("jsonp", "callback"));
		for (String name : jsonpParameterNames) {
			String value = request.getParameter(name);
			if (!StringUtils.isEmpty(value)) {
				return value;
			}
		}
		return null;
	}

	/**
	 * 下载
	 * 
	 * @param response
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	public static void download(HttpServletResponse response, InputStream in,
			String fileName) throws IOException {
		String attachName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		response.setHeader("Content-disposition",
				"attachment; filename=" + attachName);
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	/**
	 * 查看
	 * 
	 * @param response
	 * @param in
	 * @param fileName
	 * @param contentType
	 * @throws IOException
	 */
	public static void view(HttpServletResponse response, InputStream in,
			String fileName, String contentType) throws IOException {
		response.setContentType(contentType);
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static File getTempDir(ServletContext servletContext) {
		Assert.notNull(servletContext, "ServletContext must not be null");
		return (File) servletContext
				.getAttribute(WebUtils.TEMP_DIR_CONTEXT_ATTRIBUTE);
	}
	
	/**
	 * 得到请求对象
	 *
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request;
		}
		return null;
	}

	/**
	 * 将 str 进行URL  encoder
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String urlencoder (String str,String enc) {
		String encStr = null ;
		try {
			encStr = URLEncoder.encode(str, enc);
		} catch (Exception e) {
			return "" ;
		}
		return encStr ;
	}
}
