package org.myframework.web.bind;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Order(2)
@ControllerAdvice(basePackages = "com.hollycrm")
public class FastJsonJsonpAdvice implements ResponseBodyAdvice<Object> {

	public FastJsonJsonpAdvice() {
    }

	private final String[] jsonpQueryParamNames = {"callback", "jsonp"};


	protected void beforeBodyWriteInternal(  ServerHttpRequest request, ServerHttpResponse response) {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		for (String name : this.jsonpQueryParamNames) {
			String value = servletRequest.getParameter(name);
			if (value != null) {
				MediaType contentTypeToUse =  new MediaType("application", "javascript");
				response.getHeaders().setContentType(contentTypeToUse);
				return;
			}
		}
	}

	private String getJsonpParameterValue(HttpServletRequest request) {
		if (this.jsonpQueryParamNames != null) {
			for (String name : this.jsonpQueryParamNames) {
				String value = request.getParameter(name);
				if (!StringUtils.isEmpty(value)) {
					return value;
				}
			}
		}
		return null;
	}

	protected MediaType getContentType(MediaType contentType, ServerHttpRequest request, ServerHttpResponse response) {
		return new MediaType("application", "javascript");
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			MediaType selectedContentType,  Class<? extends HttpMessageConverter<?>>  selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		beforeBodyWriteInternal(request, response);
		String jsonpParameterValue = getJsonpParameterValue(((ServletServerHttpRequest)request).getServletRequest());
		if (jsonpParameterValue != null) {
			JSONPObject jsonp = new JSONPObject(jsonpParameterValue);
			jsonp.addParameter(body);
			return jsonp;
		}
		return body;
	}

	public static void main(String[] args) {
		JSONPObject Jsonp = new JSONPObject("function");
		ServiceResult sr = new ServiceResult();
		sr.setContent("111");
		Jsonp.addParameter(sr);
		System.out.println( JSON.toJSONString(Jsonp));

	}
}
