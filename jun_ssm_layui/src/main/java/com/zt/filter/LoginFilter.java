package com.zt.filter;

import com.zt.holder.RequestHolder;
import com.zt.pojo.SysUser;
import com.zt.util.ConstUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();
        log.info("拦截器请求路径：{}",url);
        SysUser sysUser = (SysUser) request.getSession().getAttribute(ConstUtil.CURRENT_USER);
        if(sysUser == null){
            response.sendRedirect("/login.jsp");
            return;
        }
        //把当前获取的信息存储到全局的Holder中
        RequestHolder.add(sysUser);
        RequestHolder.add(request);
        filterChain.doFilter(request,response);
        return;
    }

    @Override
    public void destroy() {

    }
}
