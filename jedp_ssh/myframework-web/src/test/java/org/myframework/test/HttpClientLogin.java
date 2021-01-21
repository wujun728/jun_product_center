package org.myframework.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.myframework.commons.util.http.SimpleHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientLogin {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(HttpClientLogin.class);
	private static final String LOGIN_URL = "https://passport.csdn.net/";
	private String password ="wh20080301";
	private String lt;
	private String execution;
	private String _eventId;
	String username ="json20080301";

	@Test
	public void testWithSameHttpClientContext() throws Exception {
		String rtn = new SimpleHttpClient().simpleGet(
				"http://127.0.0.1:8021/hollybeacon-web/rest/security/loginByOa?orgId=ORG000000"
						+ "&userCode=admin&encodedPassword=f5aa100da00005471f59e52cbe761a80");
		rtn = new SimpleHttpClient().simpleGet(
				"http://127.0.0.1:8021/hollybeacon-web/rest/security/hello");
		org.junit.Assert.assertEquals("ok", rtn);
	}

	@Test
	public void testWithSameHttpClientContext2() throws Exception {
		SimpleHttpClient HttpClientLogin = new SimpleHttpClient();
		String rtn = HttpClientLogin.simpleGet(
				"http://127.0.0.1:8021/hollybeacon-web/rest/security/loginByOa?orgId=ORG000000"
						+ "&userCode=admin&encodedPassword=f5aa100da00005471f59e52cbe761a80");
		rtn = HttpClientLogin.simpleGet(
				"http://127.0.0.1:8021/hollybeacon-web/rest/security/hello");
		org.junit.Assert.assertEquals("ok", rtn);
	}

	@Test
	public void testCsdnLogin() throws Exception {
		HttpClientLogin httpClientLogin = new HttpClientLogin();
		  httpClientLogin.mockLogin();
		System.out.println(  getSimpleHttpClient().simpleGet("https://www.csdn.net/"));
		 
	}

	SimpleHttpClient simpleHttpClient = new SimpleHttpClient();

	public SimpleHttpClient getSimpleHttpClient() {
		return simpleHttpClient;
	}

	private void fetchNecessaryParam() throws Exception {
		// 查看CSDN登陆页面源码发现登陆时需要post5个参数
		// name、password，另外三个在页面的隐藏域中，a good start
		logger.info("获取必要的登陆信息。。。。。");
		// 这样登陆不行，因为第一次需要访问需要拿到上下文context
		// Document doc = Jsoup.connect(LOGIN_URL).get();
		String html = simpleHttpClient.simpleGet(LOGIN_URL);
		Document doc = Jsoup.parse(html);
		Element form = doc.select(".user-pass").get(0);
		lt = form.select("input[name=lt]").get(0).val();
		execution = form.select("input[name=execution]").get(0).val();
		_eventId = form.select("input[name=_eventId]").get(0).val();
		logger.info("获取成功。。。。。");
	}

	public boolean mockLogin() throws Exception {
		logger.info("开始登陆。。。。。");
		boolean result = false;
		fetchNecessaryParam();
		Map<String, String> map = new HashMap<String, String>();

		map.put("username", username);
		map.put("password", password);
		map.put("lt", lt);
		map.put("execution", execution);
		map.put("_eventId", _eventId);
		String ret = simpleHttpClient.simplePost(LOGIN_URL, map);
		System.out.println(ret);
		if (ret.indexOf("redirect_back") > -1) {
			logger.info("登陆成功。。。。。");
			result = true;
		} else if (ret.indexOf("登录太频繁") > -1) {
			logger.info("登录太频繁，请稍后再试。。。。。");
		} else {
			logger.info("登陆失败。。。。。");
		}
		return result;
	}

}
