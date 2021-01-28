package org.myframework.web.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.myframework.commons.util.StringUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

 
/**
 *
 * <ol>XSS注入拦截
 * <li>{@link  }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年3月14日
 *
 */
public class XssFilter implements Filter {

	/**
	 * 需要排除的页面
	 */
	private String excludedPages;

	private String[] excludedPageArray;

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		boolean isExcludedPage = false;

		HttpServletRequest request2 = (HttpServletRequest) request;
		String ctx_path = request2.getContextPath();
		String request_uri = request2.getRequestURI();
		String action = request_uri.substring(ctx_path.length()).replaceAll("//", "/");

		// 判断是否在过滤url之外
		for (String page : excludedPageArray) {
			if (page.equals(action)) {
				isExcludedPage = true;
				break;
			}
		}

		if (isExcludedPage) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(new XssRequestWrapper(request2), response);
		}

	}

	/**
	 * 自定义过滤规则
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		excludedPages = filterConfig.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {
			excludedPageArray = excludedPages.replaceAll("[\\s]", "")
					.split(",");
		}
	}

	/**
	 * 
	 * <ol>装饰器模式加强request处理
	 * <li>{@link  }</li>
	 * 
	 * </ol>
	 * @see
	 * @author Wujun
	 * @since 1.0
	 * @2016年3月14日
	 *
	 */
	static class XssRequestWrapper extends HttpServletRequestWrapper {

		private static Policy policy = null;

		static {
			try {
				policy = Policy.getInstance( XssRequestWrapper.class.getClassLoader()
						.getResourceAsStream("antisamy-anythinggoes.xml"));
			} catch (PolicyException e) {
				 
			}
		}

		public XssRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		@SuppressWarnings("rawtypes")
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> request_map = super.getParameterMap();
			Iterator iterator = request_map.entrySet().iterator();
			System.out.println("request_map" + request_map.size());
			while (iterator.hasNext()) {
				Map.Entry me = (Map.Entry) iterator.next();
				// System.out.println(me.getKey()+":");
				String[] values = (String[]) me.getValue();
				for (int i = 0; i < values.length; i++) {
					System.out.println(values[i]);
					values[i] = xssClean(values[i]);
				}
			}
			return request_map;
		}

		@Override
		public String[] getParameterValues(String paramString) {
			String[] arrayOfString1 = super.getParameterValues(paramString);
			if (arrayOfString1 == null)
				return null;
			int i = arrayOfString1.length;
			String[] arrayOfString2 = new String[i];
			for (int j = 0; j < i; j++)
				arrayOfString2[j] = xssClean(arrayOfString1[j]);
			return arrayOfString2;
		}

		@Override
		public String getParameter(String paramString) {
			String str = super.getParameter(paramString);
			if (str == null)
				return null;
			return xssClean(str);
		}

		@Override
		public String getHeader(String paramString) {
			String str = super.getHeader(paramString);
			if (str == null)
				return null;
			return xssClean(str);
		}

		private String xssClean(String value) {
			AntiSamy antiSamy = new AntiSamy();
			try {
				// CleanResults cr = antiSamy.scan(dirtyInput, policyFilePath);
				final CleanResults cr = antiSamy.scan(value, policy);
				// 安全的HTML输出
				return cr.getCleanHTML() ;
			} catch (ScanException e) {
				e.printStackTrace();
			} catch (PolicyException e) {
				e.printStackTrace();
			}
			return value;
		}
	}

}
