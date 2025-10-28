package io.github.wujun728.admin.common.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class XssSpringFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        //System.out.println("--------------------->过滤器：请求地址" + requestURI);
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        //跨域设置
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            //通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
            //设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
            //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //请求方式
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            //（预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久
            httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
            //首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        }
        //sql,xss过滤
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            XSSRequestWrapper xssRequestBodyWrapper = new XSSRequestWrapper(request);
            chain.doFilter(xssRequestBodyWrapper, response);
        } else {
            XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper(request);
            chain.doFilter(xssRequestWrapper, response);
        }

        //System.out.println("--------------------->过滤器：结束" + requestURI);
    }

    @Override
    public void destroy() {
    }

}

