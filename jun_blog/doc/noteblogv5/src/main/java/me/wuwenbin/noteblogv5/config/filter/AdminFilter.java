package me.wuwenbin.noteblogv5.config.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuwen
 */
public class AdminFilter extends BaseController implements HandlerInterceptor {

    private static final String FRONTEND_INDEX = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User sessionUser = getSessionUser(request);
        if (sessionUser == null) {
            handleAjaxRequest(request, response);
            return false;
        } else if (sessionUser.getRole() == RoleEnum.ADMIN) {
            return true;
        } else {
            if (isAjaxRequest(request)) {
                JSONObject jsonObject = JSONUtil.createObj();
                jsonObject.putAll(ResultBeanObj.error("非法访问，即将跳转首页！", FRONTEND_INDEX));
                response.getWriter().write(jsonObject.toString());
            } else {
                response.sendRedirect(FRONTEND_INDEX);
            }
            return false;
        }
    }
}
