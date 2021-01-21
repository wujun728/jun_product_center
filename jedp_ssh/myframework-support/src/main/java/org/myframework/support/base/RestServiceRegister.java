package org.myframework.support.base;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerMethodMappingNamingStrategy;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javassist.Modifier;


/**
 * 扫描@RestService注解类，并发布成rest服务，
 * 默认服务名为:/<类名>/<方法名>,默认HTTP请求方式为GET或POST
 * <ol>
 * <li>{@link RestService }</li>
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public class RestServiceRegister extends RequestMappingHandlerMapping {

	private HandlerMethodMappingNamingStrategy<RequestMappingInfo> namingStrategy;


	private final Map<RequestMappingInfo, HandlerMethod> handlerMethods = new LinkedHashMap<RequestMappingInfo, HandlerMethod>();

	private final MultiValueMap<String, RequestMappingInfo> urlMap = new LinkedMultiValueMap<String, RequestMappingInfo>();

	private final MultiValueMap<String, HandlerMethod> nameMap = new LinkedMultiValueMap<String, HandlerMethod>();

	private Properties prop;

	private static final String REDIRECT = UrlBasedViewResolver.REDIRECT_URL_PREFIX;

	private static final String FORWARD = UrlBasedViewResolver.FORWARD_URL_PREFIX;

	private Map<String, String> mappings = new HashMap<String, String>();

	private Set<String> paths = new HashSet<String>();

	public RestServiceRegister() throws IOException {
		if(prop==null){
			prop = new Properties();
			InputStream in = getClass().getResourceAsStream("/application.properties");
			prop.load(in);
		}
		setOrder(2);
	}

	/**
	 * Detects &#64;RestService annotations in handler beans.
	 *
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#isHandler(java.lang.Class)
	 */
	@Override
	protected boolean isHandler(Class<?> beanType) {
		return AnnotationUtils.findAnnotation(beanType, Controller.class) != null;
	}

	@Override
	protected RequestMappingInfo getMappingForMethod(Method method,
			Class<?> handlerType) {

		//public方法才能发布rest服务
		if(!Modifier.isPublic( method.getModifiers())){
			//logger.info("method is not public , can not register rest service .." + method.toGenericString());
			return null ;
		}
		//logger.info("register rest service .." + method.toGenericString());
		RequestMappingInfo clzInfo = null;
		RequestMappingInfo methodInfo = null;
		RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
		RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
		RequestCondition<?> methodCondition = getCustomMethodCondition(method);
		RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);

		if (methodAnnotation != null) {
			methodInfo = createRequestMappingInfo(methodAnnotation, methodCondition);
		}else{
			methodInfo = createMethodRequestMappingInfo(method, methodCondition);
		}

		if (typeAnnotation != null) {
			clzInfo = createRequestMappingInfo(typeAnnotation, typeCondition);
		}else{
			clzInfo = createClassRequestMappingInfo(handlerType, typeCondition);
		}

		return clzInfo.combine(methodInfo);

	}

	/**
	 * 根据类名构造RequestMappingInfo
	 * @see RequestMappingInfo
	 * @param handlerType
	 * @param customCondition
	 * @return
	 */
	protected RequestMappingInfo createClassRequestMappingInfo(
			Class<?> handlerType, RequestCondition<?> customCondition) {
		String path = handlerType.getName();//handlerType.getName().replaceAll(".", "_");
		return new RequestMappingInfo(path,
				new PatternsRequestCondition(new String[] {path},
						getUrlPathHelper(), getPathMatcher(),
						useSuffixPatternMatch(), useTrailingSlashMatch(),
						getFileExtensions()),
				new RequestMethodsRequestCondition(),
				new ParamsRequestCondition(new String[] {}),
				new HeadersRequestCondition(new String[] {}),
				new ConsumesRequestCondition(new String[] {}, new String[] {}),
				new ProducesRequestCondition(new String[] {}, new String[] {},
						getContentNegotiationManager()), customCondition);
	}

	/**
	 * 根据方法名构造RequestMappingInfo
	 * @see RequestMappingInfo
	 * @param method
	 * @param customCondition
	 * @return
	 */
	protected RequestMappingInfo createMethodRequestMappingInfo(
			Method method, RequestCondition<?> customCondition) {
		return new RequestMappingInfo(method.getName(),
				new PatternsRequestCondition(new String[] {method.getName()},
						getUrlPathHelper(), getPathMatcher(),
						useSuffixPatternMatch(), useTrailingSlashMatch(),
						getFileExtensions()),
				new RequestMethodsRequestCondition(RequestMethod.POST,RequestMethod.GET),
				new ParamsRequestCondition(new String[] {}),
				new HeadersRequestCondition(new String[] {}),
				new ConsumesRequestCondition(new String[] {}, new String[] {}),
				new ProducesRequestCondition(new String[] {}, new String[] {},
						getContentNegotiationManager()), customCondition);
	}

	@Override
	protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
		HandlerMethod newHandlerMethod = createHandlerMethod(handler, method);
		HandlerMethod oldHandlerMethod = this.handlerMethods.get(mapping);
		if (oldHandlerMethod != null && !oldHandlerMethod.equals(newHandlerMethod)) {
			throw new IllegalStateException("Ambiguous mapping found. Cannot map '" + newHandlerMethod.getBean() +
					"' bean method \n" + newHandlerMethod + "\nto " + mapping + ": There is already '" +
					oldHandlerMethod.getBean() + "' bean method\n" + oldHandlerMethod + " mapped.");
		}

		this.handlerMethods.put(mapping, newHandlerMethod);
		if (logger.isInfoEnabled()) {
			logger.info("Mapped \"" + mapping + "\" onto " + newHandlerMethod);
		}

		Set<String> patterns = getMappingPathPatterns(mapping);
		for (String pattern : patterns) {
			if (!getPathMatcher().isPattern(pattern)) {
				ActionLog actionLog = method.getAnnotation(ActionLog.class);
				if(actionLog != null){
//					RestUtil.addRestMap(pattern,actionLog.content());
				}else{
//					RestUtil.addRestMap(pattern,"");
				}
				this.urlMap.add(pattern, mapping);
			}
		}

		if (this.namingStrategy != null) {
			String name = this.namingStrategy.getName(newHandlerMethod, mapping);
			updateNameMap(name, newHandlerMethod);
		}
	}

	private void addMatchingMappings(Collection<RequestMappingInfo> mappings, List<Match> matches, HttpServletRequest request) {
		for (RequestMappingInfo mapping : mappings) {
			RequestMappingInfo match = getMatchingMapping(mapping, request);
			if (match != null) {
				matches.add(new Match(match, this.handlerMethods.get(mapping)));
			}
		}
	}

	/**
	 * A thin wrapper around a matched HandlerMethod and its mapping, for the purpose of
	 * comparing the best match with a comparator in the context of the current request.
	 */
	private class Match {

		private final RequestMappingInfo mapping;

		private final HandlerMethod handlerMethod;

		public Match(RequestMappingInfo mapping, HandlerMethod handlerMethod) {
			this.mapping = mapping;
			this.handlerMethod = handlerMethod;
		}

		@Override
		public String toString() {
			return this.mapping.toString();
		}
	}

	private class MatchComparator implements Comparator<Match> {

		private final Comparator<RequestMappingInfo> comparator;

		public MatchComparator(Comparator<RequestMappingInfo> comparator) {
			this.comparator = comparator;
		}

		@Override
		public int compare(Match match1, Match match2) {
			return this.comparator.compare(match1.mapping, match2.mapping);
		}
	}

	private void updateNameMap(String name, HandlerMethod newHandlerMethod) {

		List<HandlerMethod> handlerMethods = this.nameMap.get(name);
		if (handlerMethods != null) {
			for (HandlerMethod handlerMethod : handlerMethods) {
				if (handlerMethod.getMethod().equals(newHandlerMethod.getMethod())) {
					logger.trace("Mapping name already registered. Multiple controller instances perhaps?");
					return;
				}
			}
		}

		logger.trace("Mapping name=" + name);
		this.nameMap.add(name, newHandlerMethod);

		if (this.nameMap.get(name).size() > 1) {
			if (logger.isDebugEnabled()) {
				logger.debug("Mapping name clash for handlerMethods=" + this.nameMap.get(name) +
						". Consider assigning explicit names.");
			}
		}
	}



}

