package com.sz.util;

import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON 处理的工具类
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-2-1 下午1:07:58 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 把Java对象转换为JSON数据格式
	 * 
	 * @param object
	 * @return
	 */
	public static String getJson(Object object) {
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, object);
			String dataJson = strWriter.toString();
			return dataJson;
		} catch (Exception e) {
			// logger.error("[JsonUtils]Fail to getjson", e);
		}

		return null;
	}

	/**
	 * 把JSON数据格式转换为JAVA对象
	 * 
	 * @param <T>
	 * @param jsonData
	 * @param clz
	 * @return
	 */
	public static <T> T readValue(String jsonData, Class<T> clz) {
		try {
			return mapper.readValue(jsonData, clz);
		} catch (Exception e) {
			// logger.error("[JsonUtils]Fail to convert json to object: " +
			// jsonData, e);
			e.printStackTrace();
		}
		return null;
	}

	 
}
