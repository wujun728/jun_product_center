package org.myframework.commons.util.http;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.myframework.commons.util.StringUtils;

/**
 * 
 * <ol>可以用于爬虫程序，访问模式和浏览器类似，持有cookie
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年4月25日
 *
 */
public class SimpleHttpClient {

//	HttpClientContext context;

	CloseableHttpClient httpclient;

	public SimpleHttpClient() {
		// context = HttpClientContext.create();
		// context.setCookieStore(new BasicCookieStore());
		RequestConfig globalConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
		httpclient = HttpClients.custom()
				// .setDefaultCookieStore(new BasicCookieStore())
				.setDefaultRequestConfig(globalConfig).build();
	}

	public String simplePost(String url,
			Map<String, ? extends Object> param) throws Exception {
		Form form = Form.form();
		Set<String> keys = param.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String name = iterator.next();
			form.add(name, StringUtils.asString(param.get(name)));
		}
		List<NameValuePair> data = form.build();
		//
		HttpPost loginPost = new HttpPost(url);
		loginPost.setEntity(new UrlEncodedFormEntity(data,"UTF-8"));  
	    CloseableHttpResponse  response = httpclient.execute(loginPost);
		String rtn;
		try {
			HttpEntity entity = response.getEntity();
			rtn = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
		} finally {
			response.close();
		}
		return rtn;
	}
	
	public String simpleGet(String url ) throws Exception {
		HttpGet httpGet = new HttpGet(url);
	    CloseableHttpResponse  response = httpclient.execute(httpGet);
		String rtn;
		try {
			HttpEntity entity = response.getEntity();
			rtn = EntityUtils.toString(entity);
			EntityUtils.consume(entity); 
		} finally {
			response.close();
		}
		return rtn;
	}
}
