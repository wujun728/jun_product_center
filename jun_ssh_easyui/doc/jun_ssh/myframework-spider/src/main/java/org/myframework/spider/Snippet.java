package org.myframework.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.myframework.commons.util.http.HttpClientUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.xml.sax.SAXException;

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class Snippet {
	
	static String LOGIN_URL = "https://passport.meituan.com/account/unitivelogin?service=www&continue=http%3A%2F%2Fwww.meituan.com%2Faccount%2Fsettoken%3Fcontinue%3Dhttp%253A%252F%252Fwh.meituan.com%252Fdianying%252Fzuixindianying&mtt=1.movie%2Fmovies.0.0.ino309ad&_nsmobilelogin=true";

	@Test
	public void test() throws Exception {
		String html = HttpClientUtils.simpleGet(LOGIN_URL);
		Document doc = Jsoup.parse(html);
		Elements elm = doc.select("#J-normal-form");
		Elements elms = elm.select("input");
		Map<String, String> param = new HashMap<String, String>();
		for (Element element : elms) {
			String name = element.attr("name");
			String value = element.attr("value");
			param.put(name, value);
			System.out.println(name + ">>> " + value);
		}
		param.put("email", "18971286027");
		param.put("password", "wh20080301");
		String data = HttpClientUtils.simplePost(LOGIN_URL, param);

		//
		doc = Jsoup.parse(data);
		elm = doc.select("form");
		elms = elm.select("input");
		param = new HashMap<String, String>();
		String actionUrl = elm.attr("action");
		for (Element element : elms) {
			String name = element.attr("name");
			String value = element.attr("value");
			param.put(name, value);
			System.out.println(name + ">>> " + value);
		}
		System.out.println("actionUrl >>> " + actionUrl);
		try {
			try {
				data = HttpClientUtils.simplePost(actionUrl, param);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			data = HttpClientUtils.simpleGet("http://wh.meituan.com/combo/userinfo");
		}
		System.out.println(">>> " + data);
	}

	private String LOGIN_URL1 = "https://passport.csdn.net/account/login?ref=toolbar";

	@Test
	public void testTongse() throws MalformedURLException, IOException, Exception {
		Document doc = Jsoup.connect("http://www.tongse03.org/search.asp?searchword=%BE%DE%C8%E9").get();
		Elements elms = doc.select("div.search1-left").select("li");
		StringBuffer sb = new StringBuffer();
		for (Element element : elms) {
			// Elements title = element.select("div.searchlefttitle1" );
			Elements IMG = element.select("div.searchleftimg1 > a > img");
			IMG.attr("src", "http://www.tongse03.org/" + IMG.attr("src"));
			Elements eleA = element.select("div.searchleftimg1 > a");
			String href = eleA.attr("href");
			String id = href.substring(href.lastIndexOf("/") + 1, href.length() - 5);
			String newHref = String.format("http://www.tongse03.org/player/%s.html?%s-0-0", id, id);
			eleA.attr("href", newHref);
			eleA.removeAttr("title");
			sb.append(element);
		}

		send163Email(sb.toString());
	}

	private void send163Email(String  sb) throws MessagingException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.163.com");
		sender.setPort(25); // 默认就是25
		sender.setUsername("18971286027@163.com");
		sender.setPassword("wh20080301");
		sender.setDefaultEncoding("UTF-8");

		// 配置文件对象
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); // 是否进行验证
		Session session = Session.getInstance(props);
		sender.setSession(session); // 为发送器指定会话
		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setTo("18971286027@163.com"); // 发送给谁
		helper.setSubject("TONGSE资源"); // 标题
		helper.setFrom("18971286027@163.com"); // 来自
		// 邮件内容，第二个参数指定发送的是HTML格式
		helper.setText(sb , true);
		sender.send(mail); // 发送
		System.out.println("邮件发送成功");
	}

	@Test
	public void testUserHttpUnit() throws MalformedURLException, IOException, Exception {
		String html = HttpClientUtils.simpleGet(LOGIN_URL1);
		Document doc = Jsoup.parse(html);
		Elements form0 = doc.select("#fm1");
		Elements elms = form0.select("input");
		WebRequest request = new PostMethodWebRequest(LOGIN_URL1);
		for (Element element : elms) {
			String name = element.attr("name");
			String value = element.attr("value");
			if (name.equals("username"))
				request.setParameter("username", "json20080301");
			else if (name.equals("password"))
				request.setParameter("password", "wh20080301");
			else
				request.setParameter(name, value);
			System.out.println(name + ">>> " + value);
		}
		//
		HttpUnitOptions.setScriptingEnabled(false);
		WebConversation wc = new WebConversation();
		WebResponse response = null;
		try {
			response = wc.getResponse(request);
			System.out.println(response.getText());
			System.out.println("----------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}
}
