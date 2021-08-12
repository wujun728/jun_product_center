package com.shuogesha.cms.version;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Foot;
import com.shuogesha.cms.entity.Product;
import com.shuogesha.cms.service.FootService;
import com.shuogesha.cms.service.MessageService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.platform.web.util.RequestUtils;

/**
 * 消息推送通知
 * @author zhaohaiyuan
 *
 */
@Order //指定切面的优先级，值越小级别越高
@Aspect
@Component 
public class FootAspect {
	//声明该方法是一个前置通知
	//@Before("execution(public .*(int, int))")
	//执行完方法之后掉用，无论是否存在异常
	//@After("execution(public .*(int, int))")
	//AfterReturning、AfterThrowing、Around

	//执行完方法
	@AfterReturning(returning="rvt", pointcut="execution(* com.shuogesha.app.action.ApiProductAct.get_product(..))")
	public  void afterMethod(JoinPoint joinPoint,Object rvt) { 
//			String methodName=joinPoint.getSignature().getName();
// 			 // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
	        Object[] args = joinPoint.getArgs(); // 参数值
	        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames(); // 参数名
	        
	        try {
				JsonResult map = (JsonResult) rvt;
				if(map!=null&&"200".equals(map.getCode())) {//只有返回成功的时间
					Product bean=(Product) map.getData();
					if(bean!=null) {//进入发送通知的环节
						Foot foot = new Foot();
						foot.setName(bean.getName());
						foot.setContent(JSON.toJSONString(bean));
						foot.setUserId(ApiUtils.getUnifiedUserId((HttpServletRequest) args[1]));
						foot.setDateline(RequestUtils.getNow()); 
						foot.setRefer(Product.class.getSimpleName());
						foot.setReferid(bean.getId());
						footService.saveByFoot(foot); 
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			} 
	}
	  
	@Autowired
	public MessageService messageService; 
	@Autowired
	private FootService footService; 
}
