package org.myframework.support.echarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.myframework.commons.util.StringUtils;
import org.myframework.support.echarts.expression.DefaultResolver;
import org.myframework.support.echarts.expression.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * <ol>Echart配置模版类，根据传入参数键值对来进行文本内容的替换
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年11月23日
 *
 */
public class EchartTemplate {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(EchartTemplate.class);

	/**
	 * @param optionContent 需要被翻译的echart配置文本
	 * @param context 可被替换的内容，
	 * 可以使用的key主要有以下：  categoryData,legendData,keyValData,strData,上述四个键对应的值都是数组类型，数据来源见DataEtl类
	 * 其他可用key都是字符串类型：如：title来源于EchartConfig配置信息，取数来源于EchartService；
	 * @return
	 */
	public String evaluate(String optionContent, Map<String, Object> context) {
		List<String> params = getReplaceParameterList(optionContent);
		Resolver resolver = new DefaultResolver();
		for (String param : params) {
			if (resolver.isIndexed(param)) {
				int idx = resolver.getIndex(param);
				String key = resolver.getProperty(param);
				JSONArray jsonArray = (JSONArray) context.get(key);
				String replacement = jsonArray.getString(idx);
				optionContent = optionContent.replaceFirst("#" + getStringReg(param), replacement);
			} else {
				String key = resolver.getProperty(param);
				Object value = context.get(key);
				if (value instanceof String) {
					optionContent = optionContent.replaceFirst("#" + param,
							(String) value);
				} else if (value instanceof JSONArray){
					optionContent = optionContent.replaceFirst("#" + param,
							((JSONArray)value).toJSONString());
				}else {//没有相应的替换值时，使用?
					optionContent = optionContent.replaceFirst("#" + param, "?");
				}
			}
		}
		logger.debug("context :"+ context);
		logger.debug("will be relpace params :"+ params );
		return optionContent;
	}

	/**
	 * 所有可替换内容都是以#开头，如#title, #keyValData[0]
	 * 
	 * @param content
	 * @return
	 */
	private static List<String> getReplaceParameterList(String content) {
		String[] allowWords = { "\\w", "\\-", "\\[", "\\]" };
		String regex = "\\#([" + StringUtils.join(allowWords, "|") + "]+)";
		// = "\\#([\\w|\\-|\\[|\\]]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		List<String> params = new ArrayList<String>();
		while (matcher.find())
			params.add(matcher.group(1));
		return params;
	}

	/**
	 * @param str
	 * @return
	 */
	protected String getStringReg(String str) {
		if (str.indexOf("[") > -1) {
			str = str.substring(0, str.indexOf("[")) + "\\"
					+ str.substring(str.indexOf("["));
		}
		if (str.indexOf("]") > -1) {
			str = str.substring(0, str.indexOf("]")) + "\\"
					+ str.substring(str.indexOf("]"));
		}
		if (str.indexOf(")") > -1) {
			str = str.substring(0, str.indexOf(")")) + "\\"
					+ str.substring(str.indexOf(")"));
		}
		if (str.indexOf("|") > -1) {
			str = str.substring(0, str.indexOf("|")) + "\\"
					+ str.substring(str.indexOf("|"));
		}
		if (str.indexOf("(") > -1) {
			str = str.substring(0, str.indexOf("(")) + "\\"
					+ str.substring(str.indexOf("("));
		}
		if (str.indexOf("*") > -1) {
			str = str.substring(0, str.indexOf("*")) + "\\"
					+ str.substring(str.indexOf("*"));
		}
		if (str.indexOf("?") > -1) {
			str = str.substring(0, str.indexOf("?")) + "\\"
					+ str.substring(str.indexOf("?"));
		}
		if (str.indexOf("$") > -1) {
			str = str.substring(0, str.indexOf("$")) + "\\"
					+ str.substring(str.indexOf("$"));
		}
		if (str.indexOf("+") > -1) {
			str = str.substring(0, str.indexOf("+")) + "\\"
					+ str.substring(str.indexOf("+"));
		}
		return str;
	}
}
