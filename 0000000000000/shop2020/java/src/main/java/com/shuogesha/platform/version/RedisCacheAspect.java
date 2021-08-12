package com.shuogesha.platform.version;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

 
 
/**
 * Redis缓存切面处理
 */
@Aspect
@Component
public class RedisCacheAspect {
	 @Resource
	private StringRedisTemplate strRedisTemplate;

	 private final static Logger logger = LoggerFactory.getLogger(NoRepeatApiAspect.class);

    /**
     * 获取或添加缓存
     */
    @Around("@annotation(com.shuogesha.platform.version.RedisCache)")
    public Object RedisCache(final ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        RedisCache cache = method.getAnnotation(RedisCache.class);
        //根据类名、方法名和参数生成key
        final String key = parseKey(cache.field(), method, jp.getArgs());
        if (logger.isDebugEnabled()) {
            logger.debug("生成key:" + key);
        }
        //得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisCache.class).type();
        //检查redis是否有缓存
        String value = (String) strRedisTemplate.opsForHash().get(modelType.getName(), key);
        Object result;
        if (null == value) {
            //缓存未命中
//            if (logger.isDebugEnabled()) {
//                logger.debug("缓存未命中");
//            }
            //去数据库查询
            result = jp.proceed(jp.getArgs());
            //把序列化结果放入缓存
            strRedisTemplate.opsForHash().put(modelType.getName(), key, serialize(result));
            //    设置失效时间
            strRedisTemplate.expire(modelType.getName(), cache.expire(), TimeUnit.SECONDS);
        } else {
            //缓存命中
//            if (logger.isDebugEnabled()) {
//                logger.debug("缓存命中");
//            }
            //得到被代理方法的返回值类型
            Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();
            //反序列化从缓存中拿到的json
            result = deserialize(value, returnType, modelType);
        }
        return result;
    }

    /**
     * 删除缓存
     */
    @Around("@annotation(com.shuogesha.platform.version.RedisEvict)")
    public Object RedisEvict(final ProceedingJoinPoint jp) throws Throwable {
        //得到被代理的方法
        Method method = getMethod(jp);
        //得到被代理方法上的注解
        Class modelType = method.getAnnotation(RedisEvict.class).type();
//        if (logger.isDebugEnabled()) {
//            logger.debug("清空缓存:" + modelType.getName());
//        }
        //判断是否指定了field
        String[] fields = method.getAnnotation(RedisEvict.class).field();
        if (fields.length == 0) {
            //清除类全限定名对应Hash缓存
        	strRedisTemplate.delete(modelType.getName());
        } else {
            //清除指定的field的缓存
            List<Object> objects = new ArrayList<>();
            for (String field : fields) {
                if (!StringUtils.isEmpty(field)) {
                    objects.add(field);
                }
            }
            if (objects.size() > 0) {
            	strRedisTemplate.opsForHash().delete(modelType.getName(), (Object[]) fields);
            }
        }
        return jp.proceed(jp.getArgs());
    }
    //FastJSON序列化对象
    private String serialize(Object result) {
        return JSON.toJSONString(result);
    }
    //FastJSON反序列化获得对象
    @SuppressWarnings("unchecked")
    private Object deserialize(String json, Class clazz, Class modelType) {
//      
//    	if (clazz.isAssignableFrom(Object.class)) {
//            return  JSON.parse(json);
//        } 
        if (StringUtils.isNotBlank(json)&&json.indexOf("[")>=0&&clazz.isAssignableFrom(Map.class)) {
        	 return  JSON.parse(json); 
        }
        if (clazz.isAssignableFrom(List.class)) { 
             return JSON.parseArray(json, modelType);
        }
        //返回结果是普通对象
        return JSON.parseObject(json, clazz);
    }

    /**
     * 获取被拦截方法对象
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     */
    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;

    }

    private String parseKey(String field, Method method, Object[] args) {
        //SpEL表达式为空默认返回方法名
        if (StringUtils.isEmpty(field)) {
            return method.getName();
        }
        //_号分割
        String SpEL = field.replace("_", "+'_'+");
        //获得被拦截方法参数列表
        LocalVariableTableParameterNameDiscoverer nd = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = nd.getParameterNames(method);
        //使用SpEL进行key的解析
        SpelExpressionParser parser = new SpelExpressionParser();
        //SpEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SpEL上下文中
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return method.getName() + parser.parseExpression(SpEL).getValue(context, String.class);
    }

}