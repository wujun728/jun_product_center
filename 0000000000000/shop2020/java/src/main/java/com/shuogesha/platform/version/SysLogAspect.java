package com.shuogesha.platform.version;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.mq.log.SystemLogSender;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.util.RequestUtils;

/**
 * 日志操作切面处理类
 * 
 * @author zhaohaiyuan
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	//切点
	// 切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
	@Pointcut(value = "@annotation(com.shuogesha.platform.version.SysLog)")
 	public void systemLogAspectAct() {
		System.out.println("11");
		
	}
	//执行完进行记录日志操作
//    @AfterThrowing(pointcut = "systemLogAspectAct()")
	@After("systemLogAspectAct()")
    public void doAfterThrowing(JoinPoint joinPoint){ 
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
 		HttpServletRequest request = attributes.getRequest();
		
		 //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();  
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		if (targetMethod.isAnnotationPresent(SysLog.class)) {
			SystemLog bean=new SystemLog();
			// 获取方法上注解中表明的权限
			SysLog sysLog = targetMethod.getAnnotation(SysLog.class);
			String content = sysLog.description();
			String type = sysLog.type();
			bean.setContent(content);
			bean.setType(type);
			if(SystemLog.APP.equals(type)) {
				UnifiedUser user=ApiUtils.getUnifiedUser(request);
				if(user!=null) {
					bean.setUserId(user.getId());
					bean.setUsername(user.getUsername());
					bean.setName(user.getId().toString()); 
				} 
			}else {
				User user = CmsUtils.getUser(request);
				if(user!=null) {
					bean.setUserId(user.getId());
					bean.setUsername(user.getUsername());
		 			bean.setName(user.getId().toString()); 
				} 
			}
  			bean.setIp(RequestUtils.getIpAddr(request));
 			bean.setDateline(new Date()); 
			 //获取切入点所在的方法
	        Method method = signature.getMethod(); 
	        //获取请求的类名
	        String className = joinPoint.getTarget().getClass().getName(); 
	        //获取请求的方法名
	        String methodName = method.getName(); 
 			String url = request.getRequestURL().toString();
	        bean.setUrl(url);
	        bean.setMethod(className+"."+methodName);
	        //直接保存到数据库
//	        systemLogService.save(bean);
	        //放入队列里面慢慢的存
	        systemLogSender.sendLog(bean);
	        
 		}
        
    }
    
    @Autowired
	private SystemLogService systemLogService; 
    @Autowired
   	private SystemLogSender systemLogSender;
}
