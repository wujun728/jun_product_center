package org.myframework.spider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.myframework.commons.util.StringUtils;
import org.myframework.commons.util.http.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsdnHttpLoginTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(CsdnHttpLoginTest.class);
	private String LOGIN_URL = "https://passport.csdn.net/account/login?ref=toolbar";
	private String lt;
	private String execution;
	private String _eventId;
	private String action;

	/**
	 * 获取必要的登陆参数信息
	 * 
	 * @throws IOException
	 */
	private void fetchNecessaryParam() throws Exception {
		// 查看CSDN登陆页面源码发现登陆时需要post5个参数
		// name、password，另外三个在页面的隐藏域中，a good start
		logger.info("获取必要的登陆信息。。。。。");
		// 这样登陆不行，因为第一次需要访问需要拿到上下文context
		// Document doc = Jsoup.connect(LOGIN_URL).get();
		String html = HttpClientUtils.simpleGet(LOGIN_URL);
		Document doc = Jsoup.parse(html);
		Element form = doc.select(".user-pass").get(0);
		lt = form.select("input[name=lt]").get(0).val();
		execution = form.select("input[name=execution]").get(0).val();
		_eventId = form.select("input[name=_eventId]").get(0).val();
		action = "https://passport.csdn.net/" + form.select("#fm1").attr("action");
		logger.info("获取成功。。。。。");
	}

	public static CloseableHttpClient httpclient ;
	
	public static CloseableHttpResponse simplePost(String url, Map<String, ? extends Object> param) throws Exception {
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();
		RequestConfig localConfig = RequestConfig.copy(globalConfig)
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
		httpclient = HttpClients.custom()  
		        .setDefaultRequestConfig(localConfig)
		        .build();
		
		Form form = Form.form();
		Set<String> keys = param.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String name = iterator.next();
			form.add(name, StringUtils.asString(param.get(name)));
		}
		List<NameValuePair> data = form.build();
		HttpPost httpPost = new HttpPost(url);
		 httpPost.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8)); 
		return   httpclient.execute(httpPost) ;
		//.bodyForm(data, Consts.UTF_8).execute();
	}

	@Test
	public void mockLogin() throws Exception {
		logger.info("开始登陆。。。。。");
		fetchNecessaryParam();
		boolean result = false;
		String username = "json20080301";
		String password = "wh20080301";
		Map loginData = new HashMap();
		loginData.put("username", username);
		loginData.put("password", password);
		loginData.put("lt", lt);
		loginData.put("execution", execution);
		loginData.put("_eventId", _eventId);
	
		
		HttpResponse rsp = simplePost(action, loginData) ;

		List<Header> headers = Arrays.asList(rsp.getHeaders("Set-Cookie"));
		String ret = IOUtils.toString(rsp.getEntity().getContent());
		if (ret.indexOf("redirect_back") > -1) {
			logger.info("登陆成功。。。。。");
			
			CloseableHttpResponse closeableHttpResponse =httpclient.execute(new HttpGet( "https://passport.csdn.net/")) ;
//			Request request = Request.Get("http://www.csdn.net/");
//			for (Header header : headers) {
//				String value = header.getValue();
//				request.addHeader(header.getName(), value);
//			}
			InputStream in = closeableHttpResponse.getEntity().getContent();
			ret = IOUtils.toString(in);
			Document doc = Jsoup.parse(ret);
			Element showinfo = doc.select("#showinfo").get(0);
			logger.info(" showinfo " + showinfo.toString());
			result = true;
		} else if (ret.indexOf("登录太频繁") > -1) {
			logger.info("登录太频繁，请稍后再试。。。。。");
		} else {
			logger.info("登陆失败。。。。。");
		}
		// result;
	}
	/**
	 * <script src="/content/loginbox/loginapi.js" ></script> <script> function
	 * redirect_back(){ var redirect = "http://www.csdn.net/"; var data =
	 * {"userId":24366618,"isLocked":false,"mobile":"","userName":"json20080301"
	 * ,"email":"15874857042@163.com","password":
	 * "d5c2b74f3b294fba68c10bf334fad7bd","registerIP":"61.166.152.218",
	 * "isDeleted":false,"isActived":true,"role":0,"registerTime":
	 * "Dec 16, 2011 12:26:11 PM"
	 * ,"userType":0,"lastLoginIP":"218.106.118.147","lastLoginTime":
	 * "Apr 6, 2016 2:58:34 PM"
	 * ,"loginTimes":111,"user_status":0,"passwordStrongLevel":1,"ucSyncStatus":
	 * true,"nickName":"json20080301","avatar":
	 * "http://avatar.csdn.net/A/4/3/1_json20080301.jpg"}; var userInfo =
	 * "RPqIPst30ft5EY0Lcqnl1B2RjaRUIPPhSQqfrhyf5QtmltL9d2MmOAXqBscO7MzEGMtDpWIWu//BHCX5XALNowkrDa9orif0tzqmM/Ql1D7f6cqna9VHl1FfEAW43chXcgoKYh7WTtmkbM+otyUkvA==";
	 * data.userName = data.userName; data.encryptUserInfo = userInfo;
	 * 
	 * csdn.login_param.call = function (){ location.href = redirect; }
	 * 
	 * var _data = {}; _data.status = true; _data.data = data;
	 * 
	 * var oauth = ""; if(oauth == "true"){ csdn.login_back(_data); }else{
	 * csdn.login_data = data; csdn.login_end(); }; }
	 * 
	 * </script>
	 * 
	 * 
	 * 
	 * <body onload="redirect_back();"></body>
	 * 
	 **/

}
