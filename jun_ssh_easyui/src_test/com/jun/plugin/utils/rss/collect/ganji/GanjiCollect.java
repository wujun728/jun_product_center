package com.jun.plugin.utils.rss.collect.ganji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jun.plugin.utils.rss.collect.Main;
import com.jun.plugin.utils.rss.collect.utils.JsoupHtml;
import com.jun.plugin.utils.rss.collect.utils.UtilTools;

/**
 * @author Wujun
 * @Url www.cntaige.com
 * @Date 2014年11月11日
 */
public class GanjiCollect {
	private String cityUrl;// 要采集的城市地址
	public static String GAN_JI_HONE_URL = "http://www.ganji.com";
	private JsoupHtml jsoupHtml;
	private int prePageNum;
	private List<HashMap<String, String>> list;// 保存分类
	private GanjiDB ganjiDB;

	public GanjiCollect() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<HashMap<String, String>>();
		ganjiDB = GanjiDB.getInstance();
	}

	public void start() {
		System.out.println("正在获取赶集网城市列表...");
		getCategoryUrl(getCityUrl());
		getInputContent();
		ganjiDB.startCollectInfo(this);
		ganjiDB.saveExcel(this);
	}

	public String getCityUrl() {
		// 获取要采集的城市
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();// 用于存放城市的名称及地址
		try {
			JsoupHtml jsoupHtml = new JsoupHtml(
					"http://www.ganji.com/index.htm");
			for (Element dlElement : jsoupHtml.select(".all-city dl")) {
				Elements dtElements = dlElement.select("dt");
				Elements ddElements = dlElement.select("dd");
				for (int i = 0; i < dtElements.size(); i++) {
					System.out.println("--------" + dtElements.get(i).text()
							+ "--------");
					Elements aHrefElements = ddElements.get(i).select("a");
					for (Element aHrefElement : aHrefElements) {
						HashMap<String, String> hashMap = new HashMap<String, String>();// 存放名称及地址
						hashMap.put("cityName", aHrefElement.text().trim());// 名称
						hashMap.put("cityUrl", aHrefElement.attr("href").trim());// 地址
						list.add(hashMap);
						System.out.print(" " + list.indexOf(hashMap) + "、"
								+ aHrefElement.text().trim() + ".");
					}
					System.out.println("\r\n");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 用户输入要采集的城市编号
		int num = 0;
		System.out.println("请输入要采集的城市编号：");
		while (true) {
			try {
				num = Main.getScanner().nextInt();
				if (num < 0 || num > list.size() - 1) {
					System.out.println("无此编号的城市,请重新输入：");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("请输入正确的城市编号：");
				continue;
			}
			cityUrl = list.get(num).get("cityUrl");
			break;
		}
		String cityName = list.get(num).get("cityName");
		System.out.println("您要采集编号为  " + num + " " + cityName + "城市的信息。");
		return cityName;
	}

	public void getCompanyInfo(String url) {
		try {
			jsoupHtml = new JsoupHtml(url);
			if (!jsoupHtml.getText(".c-title").equals(""))// 是否是vip的公司
				getNormalCompanyInfo(jsoupHtml);
			else
				getVipCompanyInfo(jsoupHtml);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	private void getVipCompanyInfo(JsoupHtml jsoupHtml) {
		// 获取vip公司的信息
		for (Element element : jsoupHtml.getElementsClass("content")) {
			Elements elements = element.select("li");
			if (element.getElementById("company_description") != null) {
				elements.select("span").remove();
				String comName = elements.get(0).text().trim().split("：")[1];// 公司名称
				String category = elements.get(2).text().trim().split("：")[1];// 行业
				String telName = elements.get(4).text().trim().split("：")[1];// 联系人
				String telPhone = GAN_JI_HONE_URL
						+ elements.select("img").attr("src");// 电话
				Elements elements2 = element.select("p");// 地址和公司介绍p标签
				String addr = elements2.get(1).text().trim().split("：")[1];// 地址
				String companyIntr = elements2.get(0).text();// 公司介绍
				ganjiDB.insertCompanyInfo(comName, category, telName, telPhone,
						addr, companyIntr);
			}
		}
	}

	private void getNormalCompanyInfo(JsoupHtml jsoupHtml) {
		// 获取普通公司信息
		for (Element element : jsoupHtml.getElementsClass("c-introduce")) {
			Elements elements = element.getElementsByTag("li");
			elements.select("em").remove();
			elements.select("span").remove();
			String comName = elements.get(0).text();// 公司名称
			String category = elements.get(2).text();// 行业
			String telName = elements.get(4).text();// 联系人
			String telPhone = GAN_JI_HONE_URL
					+ elements.select("img").attr("src");// 电话
			String addr = elements.get(6).text();// 地址
			String companyIntr = element.select("#company_description").text();// 公司介绍
			ganjiDB.insertCompanyInfo(comName, category, telName, telPhone,
					addr, companyIntr);
		}
	}

	private void getCompanyUrl(String url) {
		// 获取公司信息url
		System.out.println("--------------------------");
		System.out.println(UtilTools.getNowTime());
		System.out.println("开始采集公司链接");
		if (prePageNum == 0)
			System.out.println("采集第1页");
		else
			System.out.println("采集第" + prePageNum + "页");
		try {
			jsoupHtml = new JsoupHtml(url);
			for (Element element : jsoupHtml.getElementsTag("a")) {
				String url_ = element.attr("href");
				if (url_.matches(".*/gongsi.*")) {
					int su = url_.lastIndexOf("?");
					String companyUrl = url_;
					if (su != -1)
						companyUrl = url_.substring(0, su);
					ganjiDB.insertCompanyLinks(companyUrl,
							element.attr("title"));
				}
				String reg = ".*/o[0-9]*/";
				if (url_.matches(reg)) {
					String[] pys = url_.split("/o");
					int pageNum = Integer.parseInt(pys[1].substring(0,
							pys[1].length() - 1));
					if (pageNum > prePageNum) {
						prePageNum = pageNum;
						String pageUrl = cityUrl + url_;
						getCompanyUrl(pageUrl);
					}
				}
			}

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public String getImage(String url) {
		// 下载电话图片
		try {
			String path = Main.getCollectPath() + "cache/"
					+ url.substring(url.indexOf("=") + 1) + ".png";
			return UtilTools.getNetImage(url, path);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			// e.printStackTrace();
		}
		return null;
	}

	private void showCategory() {
		// 显示所有分类目录
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("分类编号：\n-----------------------------------------------------------------\n");
		for (int i = 0; i < list.size(); i++) {
			stringBuilder
					.append(i + "：" + list.get(i).get("title") + "   |   ");
			if ((i + 1) % 3 == 0)
				stringBuilder
						.append("\n-----------------------------------------------------------------\n");
		}
		stringBuilder
				.append("\n-----------------------------------------------------------------");
		System.out.println(stringBuilder.toString());

	}

	private void getInputContent() {
		// 获取输入的内容
		System.out.println("请输入要采集的分类编号：");
		while (true) {
			try {
				int index = Main.getScanner().nextInt();// 输入分类编号采集
				if (index < 0 || index > list.size() - 1) {
					System.out.println("请输入正确的编号：");
					continue;
				}
				collectNumIndex(index);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("请输入正确的编号：");
			}
		}
		Main.getScanner().close();
	}

	private void collectNumIndex(int index) {
		// 按分类编号采集
		String url = list.get(index).get("url");
		System.out.println("您要采集的分类：" + list.get(index).get("title"));
		System.out.println("分类链接：" + url);
		getCompanyUrl(url);
	}

	private void getCategoryUrl(String city) {
		// 获取分类目录
		System.out.println("正在获取'" + city + "'城市的分类目录...");
		try {
			jsoupHtml = new JsoupHtml("http://nn.ganji.com/zhaopin/");
			Elements elements = jsoupHtml.getElementsTag("dt");
			for (Element element : elements) {
				String zp = element.select("a").attr("href");
				HashMap<String, String> hashMap = new HashMap();
				String title = element.select("a").text().trim();
				if (title.equals(""))
					continue;
				hashMap.put("title", title);
				hashMap.put("url", cityUrl + zp);
				list.add(hashMap);
			}
			showCategory();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
