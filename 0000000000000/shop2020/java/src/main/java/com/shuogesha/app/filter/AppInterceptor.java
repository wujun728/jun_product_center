package com.shuogesha.app.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.service.AppService;
import com.shuogesha.platform.web.util.ResponseUtils;

/**
 * 拦截全部请求，需要带appid
 * @author zhaohy
 *
 */
@Configuration
public class AppInterceptor extends HandlerInterceptorAdapter { 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//测试默认关闭
//		if (debug) {
//			return true;
//		}
		// 验证appid 是否有效
		String appid = request.getParameter("appid"); 
		if(StringUtils.isBlank(appid)){//允许head里面携带appid
			appid = request.getHeader("appid");
		}  
		if(StringUtils.isBlank(appid)){ 
			ResponseUtils.renderJson(response, new JsonResult(ResultCode.PARAMS_ERROR, "参数错误",null).toString()); 
			return false;
		} 
		App app=appService.findById(appid);
		if(app==null) {
			ResponseUtils.renderJson(response, new JsonResult(ResultCode.PARAMS_ERROR, "参数错误",null).toString());  
			return false;
		}
 		ApiUtils.setApp(request, app);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","Access-Control");
		response.setHeader("Allow","POST");
		return true;
	}
	 
	private boolean debug=true;//debug
	
	@Autowired
	private AppService appService;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
