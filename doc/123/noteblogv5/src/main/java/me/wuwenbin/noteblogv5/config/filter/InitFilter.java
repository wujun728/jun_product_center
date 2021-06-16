package me.wuwenbin.noteblogv5.config.filter;

import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于判断是否已经安装好了笔记博客系统的过滤作用
 * created by Wuwenbin on 2019-08-09 at 10:03
 *
 * @author wuwenbin
 */
public class InitFilter extends HandlerInterceptorAdapter {

    private static final String INIT_URL = "/init";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (NbUtils.noteBlogIsInstalled()) {
            return true;
        } else {
            response.sendRedirect(INIT_URL);
            return false;
        }
    }
}
