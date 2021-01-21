package org.myframework.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.myframework.commons.util.DateUtils;
import org.myframework.spider.dao.TongsePageReadLogDao;
import org.myframework.spider.dao.TongseResourceDao;
import org.myframework.spider.entity.TongsePageReadLog;
import org.myframework.spider.entity.TongseResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class NextPage {
	
	TongseResourceDao dao;
	
	TongsePageReadLogDao logDao;
	
	@Test
	public void readPageTest()  {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jpa-data.xml");
		dao = ctx.getBean(TongseResourceDao.class);
		logDao  = ctx.getBean(TongsePageReadLogDao.class);
		try {
			readPage("http://www.tongse03.org/list/36_1000.html" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readCurrenPageTest()  {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jpa-data.xml");
		dao = ctx.getBean(TongseResourceDao.class);
		logDao  = ctx.getBean(TongsePageReadLogDao.class);
		try {
			send(readCurrentPage("http://www.tongse03.org/list/36.html" ));
			send(readCurrentPage("http://www.tongse03.org/list/36_2.html" ));
			send(readCurrentPage("http://www.tongse03.org/list/36_3.html" ));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		helper.setSubject(DateUtils.getCurrentDateAsString()+"TONGSE资源 " ); // 标题
		helper.setFrom("18971286027@163.com"); // 来自
		// 邮件内容，第二个参数指定发送的是HTML格式
		helper.setText(sb , true);
		sender.send(mail); // 发送
		System.out.println("邮件发送成功");
	}
	
	private void send(List<TongseResource> ls)throws  Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<ul>");
		for (TongseResource tongseResource : ls) {
			sb.append("<li>");
			sb.append( tongseResource.getHtmlPage());
			sb.append("</li>");
		}
		sb.append("</ul>");
		send163Email(sb.toString());
	}
	
	
	
	public List<TongseResource> readCurrentPage(String page) throws Exception   {
		String pageId = page.substring(page.lastIndexOf("/") + 1, page.length() - 5);
		TongsePageReadLog log = new TongsePageReadLog();
		log.setPageId(pageId);
		log.setIsOk("1");
		Document doc =null ;
		try {
			doc = Jsoup.connect(page).get();
		} catch (Exception e) {
			log.setIsOk("0");
//			logDao.save(log);
			System.out.println("==================timeout try again : ===============" + page);
			readCurrentPage(page);
		}
		logDao.save(log);
		
		Elements listBoxCenters = doc.select("div.listBoxCenter");
		//===================
		List<TongseResource> rsList = new ArrayList<TongseResource>();
		for (Element video : listBoxCenters) {
			rsList.add(record(video));
		}
		dao.save(rsList);
//		Elements pagelinks = doc.select("div#channelshow");
		//===================
//		String currentpage = pagelinks.select("em").text();
//		Elements pages = pagelinks.select("a");
//		String lastpage = pages.get(pages.size() - 2).text();
//		String nextpage = pages.get(pages.size() - 1).absUrl("href");
//		
//		System.out.println("==================currentpage : ===============" + currentpage);
//		System.out.println("==================lastpage : ==============" + lastpage);
//		System.out.println("==================nextpage: =================  " + nextpage + !currentpage.equals(lastpage));
		return rsList ;
	}
	
	@Test
	public void readStockPage( ) throws Exception   {
		String stockUrl=	"http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx?type=s&sortType=C&sortRule=-1&pageSize=10&page=1&style=10&token=44c9d251add88e27b65ed86506f6e5da&_g=0.011216171784326434";
		Document doc =Jsoup.connect(stockUrl).get();
		System.out.println(doc.text());
	}
	
	public void readPage(String page) throws Exception   {
		boolean isnextpage = true ;
		String pageId = page.substring(page.lastIndexOf("/") + 1, page.length() - 5);
		TongsePageReadLog log = new TongsePageReadLog();
		log.setPageId(pageId);
		log.setIsOk("1");
		Document doc =null ;
		try {
			doc = Jsoup.connect(page).get();
		} catch (Exception e) {
			log.setIsOk("0");
//			logDao.save(log);
			System.out.println("==================timeout try again : ===============" + page);
			readPage(page);
		}
		logDao.save(log);
		Elements pagelinks = doc.select("div#channelshow");
		Elements listBoxCenters = doc.select("div.listBoxCenter");
		//===================
		List<TongseResource> rsList = new ArrayList<TongseResource>();
		for (Element video : listBoxCenters) {
			rsList.add(record(video));
		}
		dao.save(rsList);
		//===================
		String currentpage = pagelinks.select("em").text();
		Elements pages = pagelinks.select("a");
		String lastpage = pages.get(pages.size() - 2).text();
		String nextpage = pages.get(pages.size() - 1).absUrl("href");
		
		System.out.println("==================currentpage : ===============" + currentpage);
		System.out.println("==================lastpage : ==============" + lastpage);
		System.out.println("==================nextpage: =================  " + nextpage + !currentpage.equals(lastpage));
		while (isnextpage) {
			isnextpage = !currentpage.equals(lastpage)  ;
			System.out.println("===read============nextpage: =================  " + nextpage);
			readPage(nextpage);
		}
		
	}

	public TongseResource record(Element video) throws Exception {
		Element img = video.select("div.tu").select("img").get(0);
		String imgAbsUrl = img.absUrl("src");

		//
		String playerPage = video.select("div.tu").select("a").get(0).absUrl("href");

		String videoId = playerPage.substring(playerPage.lastIndexOf("/") + 1, playerPage.length() - 5);
		System.out.println("videoId :" + videoId);
		Element imgDom = img.attr("src", imgAbsUrl);
		System.out.println("imgAbsUrl :" + imgDom);
	 	Document doc =null ;
		try {
			doc = Jsoup.connect(playerPage).get();
		} catch (Exception e) {
			doc = Jsoup.connect(playerPage).get();
		}
		//
		Elements players = doc.select("div.numlist");
		StringBuffer sBuffer = new StringBuffer();
		for (Element playDiv : players) {
			Elements hrefs = playDiv.select("li > a");
			for (Element href : hrefs) {
				String playAbsUrl = href.absUrl("href");
				href.attr("href", playAbsUrl);
				sBuffer.append(href);
			}
		}

		System.out.println("playAbsUrl :" + sBuffer.toString());
		String introduce = doc.select("div.js").get(0).text(); 
		// System.out.println("js : " + js);
		// System.out.println("img : " + img.attr("src",imgAbsUrl));
		// System.out.println("players : " + players);

		TongseResource resource = new TongseResource();
		resource.setId(videoId);
		resource.setImgAbsUrl(imgDom.toString());
		resource.setPlayAbsUrl(sBuffer.toString());
		resource.setIntroduce(introduce);
		resource.setHtmlPage(imgDom.toString() + doc.select("div.js")+sBuffer.toString());
//		Thread.sleep(2000);
//		dao.save(resource);
		return resource;
	}
}
