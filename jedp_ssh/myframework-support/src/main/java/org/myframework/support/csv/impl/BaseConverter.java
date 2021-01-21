package org.myframework.support.csv.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.PatternMatchUtils;
import org.myframework.support.csv.annotation.CsvConverter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@CsvConverter
public abstract class BaseConverter {

	protected static Log logger = LogFactory.getLog(BaseConverter.class);

	/**
	 * 是否导出
	 * @return
	 */
	protected static boolean isExportReq() {
		String uri = getRequest().getRequestURI();
		logger.debug("export import url :" + uri);
		return PatternMatchUtils.simpleMatch("*/rest/*/export", uri )||uri.endsWith("/csv/sqlexport");
	}

	/**
	 * 是否导入
	 * @return
	 */
	protected static boolean isImportReq() {
		String uri = getRequest().getRequestURI();
		logger.debug("export/import url :" + uri);

		return PatternMatchUtils.simpleMatch("*/rest/*/import", uri )||uri.endsWith("/csv/exportInValidRecord")||uri.endsWith("/csv/sqlimport") ;
	}

	/**
	 * 获取request信息，从而获得用户登录信息及浏览器访问信息
	 *
	 * @return
	 */
	protected static HttpServletRequest getRequest() {
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
	 * @return
	 */
	public static String allwaysTrue(Object param) {
		logger.debug("param :" + param);
		return "true" ;
	}

	/**
	 * @return
	 */
	public static String allwaysFalse(Object param) {
		logger.debug("param :" + param);
		CsvImportImpl.setErrmsg("始终验证不通过");
		return "false" ;
	}


}
