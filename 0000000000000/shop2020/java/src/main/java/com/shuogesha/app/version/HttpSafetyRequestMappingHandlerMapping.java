package com.shuogesha.app.version;


import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerMethodMappingNamingStrategy;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 只支持默认的几种请求
 * 
 * @author zhaohaiyuan
 *
 */
public class HttpSafetyRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();
//
	@Override
	protected RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping,
			RequestCondition<?> customCondition) {
		// 如果Controller的方法上RequestMapping没有指定Method，则只支持GET和POST
		RequestMethod[] methods = { RequestMethod.GET, RequestMethod.POST };

		if (requestMapping.method().length != 0) {
			methods = requestMapping.method();
		}

		return  RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(requestMapping.path()))
                .methods(methods)
                .params(requestMapping.params())
                .headers(requestMapping.headers())
                .consumes(requestMapping.consumes())
                .produces(requestMapping.produces())
                .mappingName(requestMapping.name())
                .customCondition(customCondition)
                .options(config)
                .build();
	}
	
	 @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = createRequestMappingInfo(method);
        if (info != null) {
            RequestMappingInfo typeInfo = createRequestMappingInfo(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }
        }
        return info;
    }
	  
    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        RequestCondition<?> condition = (element instanceof Class ?
                getCustomTypeCondition((Class<?>) element) : getCustomMethodCondition((Method) element));
        if(requestMapping == null){
            return null;
        }
        // 只需要处理方法上的RequestMapping，Controller类上的不需要处理
        if(element instanceof Class){
            return super.createRequestMappingInfo(requestMapping, condition);
        } else {
            return createRequestMappingInfo(requestMapping, condition);
        }
    }

    @Override
    public void afterPropertiesSet() { 
        this.config = new RequestMappingInfo.BuilderConfiguration();
        this.config.setUrlPathHelper(getUrlPathHelper());
        this.config.setPathMatcher(getPathMatcher());
        this.config.setSuffixPatternMatch(useSuffixPatternMatch());
        this.config.setTrailingSlashMatch(useTrailingSlashMatch());
        this.config.setRegisteredSuffixPatternMatch(useRegisteredSuffixPatternMatch());
        this.config.setContentNegotiationManager(getContentNegotiationManager());
        super.afterPropertiesSet();
    }
    
	@Override
	public void setHandlerMethodMappingNamingStrategy(
			HandlerMethodMappingNamingStrategy<RequestMappingInfo> namingStrategy) {
		// TODO Auto-generated method stub
		super.setHandlerMethodMappingNamingStrategy(namingStrategy);
	}
	
}
