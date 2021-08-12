package com.shuogesha.platform.version;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

/**
 * 拦截请求切面处理
 */
@Aspect
@Component
public class NoRepeatApiAspect {
	@Resource
	private StringRedisTemplate strRedisTemplate;

	private final static Logger logger = LoggerFactory.getLogger(NoRepeatApiAspect.class);

	@Around("@annotation(noRepeatApi)")
	public Object around(ProceedingJoinPoint joinPoint, NoRepeatApi noRepeatApi) throws Throwable {
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
			HttpServletRequest request = attributes.getRequest();
			request.getRequestURI();
			String key = sessionId + "-" + request.getRequestURL() + "_" + request.getQueryString();

			if (logger.isDebugEnabled()) {
				logger.debug("请求key:" + key);
			}
			// 如果缓存中有这个url视为重复提交
			Long increment = strRedisTemplate.opsForValue().increment(key, 1);
			if (increment <= 1) {
				// 设置过期时间
				strRedisTemplate.expire(key, noRepeatApi.timeout(), TimeUnit.SECONDS);
			}
			if (increment > 1) {
				if (logger.isDebugEnabled()) {
					logger.debug("请求过于频繁，请稍后重试:" + key);
				}  
				return new JsonResult(ResultCode.TOO_FREQUENT, null);
			}
			// 执行目标方法
			Object proceed = joinPoint.proceed();
			return proceed;
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("验证重复提交时出现未知异常!"); 
			return new JsonResult(ResultCode.UNKNOWN_ERROR, null);
		}
	}

	private Method getMethod(ProceedingJoinPoint pjp) {
		// 获取参数的类型
		Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
		Method method = null;
		try {
			method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return method;

	}

}