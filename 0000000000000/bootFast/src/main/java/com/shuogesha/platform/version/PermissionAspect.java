package com.shuogesha.platform.version;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.filter.AdminInterceptor;
import com.shuogesha.platform.web.CmsUtils;

/**
 * 权限拦截切面
 * 
 * @author zhaohaiyuan
 *
 */
@Aspect
@Component
public class PermissionAspect {

	private final static Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

	// 切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
	@Pointcut(value = "@annotation(com.shuogesha.platform.version.Permission)")
	private void permissionCut() {
	};

	// 定义了切面的处理逻辑。即方法上加了@PermissionCheck
	@Around("permissionCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		try {
			// 获取request方便获取当前登录用户
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			HttpServletResponse response = attributes.getResponse();
			String requestURI = request.getRequestURI();
			// 1.记录日志信息
			Signature signature = pjp.getSignature();
			String className = pjp.getTarget().getClass().getSimpleName();
			String methodName = signature.getName();
			logger.info("className:{},methodName:{},URL:{}", className, methodName, requestURI);
			// 2.角色权限校验
			MethodSignature methodSignature = (MethodSignature) signature;
			Method targetMethod = methodSignature.getMethod();
			if (targetMethod.isAnnotationPresent(Permission.class)) {
				// 获取方法上注解中表明的权限
				Permission permission = targetMethod.getAnnotation(Permission.class);
				String perm = permission.value();
				// 获取管理员用户
				User user = CmsUtils.getUser(request);
				if (StringUtils.isNotEmpty(perm)) {
					String[] perms = perm.split(",");// 接口允许的权限
					List<String> list = Arrays.asList(perms);
					if (!checkPerm(list, user)) {// 没有权限进行访问操作
						return new JsonResult(ResultCode.NOT_SUPPORTED,ResultCode.NOT_SUPPORTED.msg(), null);
					}
					// 执行业务逻辑，放行
					return pjp.proceed();
				}
			}
		}catch (Exception e) {
 		}
		// 执行业务逻辑，放行
		return pjp.proceed();
	}

	/**
	 * 判断是否有权限
	 * 
	 * @param list
	 * @param user
	 * @return
	 */
	public boolean checkPerm(List<String> url, User user) {
		boolean result = false;
		if (user != null) {
			if (user.getUsername().equals(AdminInterceptor.admin)) {// 如果是特殊的管理员

			} else if (user.isAdmin()) {// 如果是超级管理员
				result = true;
			} else {
				if (user.getRoles() != null) {//循环角色是否有拥有全部权限
					for (Role role : user.getRoles()) {
						if (role.isAllperms()) {
							result = true;
							break;
						}
					}
				}
				List<String> list = user.getPerms();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						if (url.contains(list.get(i))) {// 只要权限其中一个满足即可
							result = true;
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
