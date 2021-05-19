package com.platform.xss;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class XssFilter implements Filter {
    /**
     * 需要排除过滤的url
     */
    private String excludedPages;
    private String[] excludedPageArray;

    @Override
    public void init(FilterConfig config) throws ServletException {
        excludedPages = config.getInitParameter("excludedPages");
        if (StringUtils.isNotEmpty(excludedPages)) {
            excludedPageArray = excludedPages.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);

        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {//判断是否在过滤url之外
            if (((HttpServletRequest) request).getServletPath().equals(page)) {
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {//排除过滤url
            chain.doFilter(request, response);
        } else {
            chain.doFilter(xssRequest, response);
        }
    }

    @Override
    public void destroy() {
    }
}