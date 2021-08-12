package com.shuogesha.app.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.app.version.AccessToken;
import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UnifiedUserTokenService;
import com.shuogesha.platform.web.util.ResponseUtils;

 
 /**
 * token 拦截器
 * @author zhaohy
 *
 */
@Configuration
public class UserAccessApiInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AccessToken accessToken = method.getAnnotation(AccessToken.class);
        AccessTokenRequired accessTokenRequired = method.getAnnotation(AccessTokenRequired.class);
        if (accessTokenRequired != null||accessToken!=null) {
           String accessTokenStr = request.getParameter("token");
           if(StringUtils.isBlank(accessTokenStr)){//允许head里面携带token
        	   accessTokenStr = request.getHeader("token");
   			}
           //允许head里面携带token
           if(accessTokenRequired!=null&&StringUtils.isBlank(accessTokenStr)){ 
			   ResponseUtils.renderJson(response, new JsonResult(ResultCode.APP_AUTH1_ERROR, "授权参数错误",null).toString()); 
               return false;
           }
           if(StringUtils.isNotBlank(accessTokenStr)){
//        	   String token =Base64.decode(accessTokenStr);
               if(accessTokenRequired!=null&&!unifiedUserTokenService.verifyMemberToken(accessTokenStr)){ 
    	   		   ResponseUtils.renderJson(response, new JsonResult(ResultCode.APP_AUTH2_ERROR, "授权参数错误",null).toString());  
    	   		   return false;
               }
               String uid = unifiedUserTokenService.getUid(accessTokenStr);
               if(StringUtils.isNotBlank(uid)) {
                   ApiUtils.setUnifiedUser(request, unifiedUserService.findById(Long.valueOf(uid)));
               }
           }
        }
        return true;
    }
	
	@Autowired
	UnifiedUserTokenService unifiedUserTokenService;
	@Autowired
	UnifiedUserService unifiedUserService;

}
