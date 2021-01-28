package com.jun.plugin.utils.rss.collect.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Wujun
 * 
 * @Date 2014年3月24日
 */
public class JsoupHtml {
	// 解析html
	private Document document;
	private String docString;

	public JsoupHtml(String url) throws IOException {
		docString = httpGetRequest(url);
		document = Jsoup.parse(docString);
	}

	public String getDoc() {
		return docString;
	}

	public Document getDocument() {
		return document;
	}

	public String getText(String select) {
		// 获取内容
		return select(select).text();
	}

	public Elements select(String cssQuery) {
		return document.select(cssQuery);
	}

	public Elements getElementsClass(String className) {
		return document.getElementsByClass(className);
	}

	public Elements getElementsTag(String tag) {
		return document.getElementsByTag(tag);
	}

	public static String httpGetRequest(String httpUrl) throws IOException {
		// httpGet请求
		URL url = new URL(httpUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setDoInput(true);
		urlConnection.setRequestProperty("accept", "*/*");
		urlConnection.setRequestProperty("connection", "Keep-Alive");
		urlConnection.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		urlConnection.setRequestProperty("Charsert", "UTF-8");
		urlConnection.setConnectTimeout(100 * 1000);
		urlConnection.setRequestMethod("GET");
		urlConnection.setUseCaches(false);

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		StringBuffer stringBuffer = new StringBuffer();
		String tempString = null;
		while ((tempString = bufferedReader.readLine()) != null) {
			stringBuffer.append(tempString);
		}
		bufferedReader.close();
		urlConnection.disconnect();
		return stringBuffer.toString().trim();
	}
}
