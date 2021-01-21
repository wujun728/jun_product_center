package com.jun.plugin.utils.rss.collect;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.jun.plugin.utils.rss.collect.ganji.GanjiCollect;
import com.jun.plugin.utils.rss.collect.utils.JsoupHtml;

public class Main {
	public static final String DB_PATH = getCollectPath() + "collect.db";// 数据库路径

	public static void main(String[] args) {
		showInfo();
		long startTime = System.currentTimeMillis();
		GanjiCollect ganjiCollect = new GanjiCollect();
		ganjiCollect.start();
		long endTime = System.currentTimeMillis();
		System.out.println("--------------------------");
		System.out.println("此次采集总耗时："
				+ ((endTime - startTime) / 60 / 60 / 1000f) + " 小时");
		try {
			JsoupHtml.httpGetRequest("http://www.cntaige.com/collect/");// 记录总使用次数
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public static void showInfo() {
		System.out
				.println("--------------------------------------------------------");
		System.out.println("Quick企业信息采集器  Ver0.05");
		System.out
				.println("Powered by CnTaiGe's Blog URL http://www.cntaige.com/");
		System.out
				.println("获取更多软件信息请关注微信订阅号:“CnTaiGe软件”，微信帐号:cntaige_software");
		System.out
				.println("--------------------------------------------------------");
		System.out.println("请勿删除数据库:" + DB_PATH);
		System.out.println("按回车键获取城市列表...");
		getScanner().nextLine();
	}

	public static String getCollectPath() {
		// 创建数据目录
		File file = new File(System.getProperty("user.dir")
				+ "/collect_cntaige_com/");
		if (!file.exists())
			file.mkdirs();
		return file.getPath() + "/";
	}

	public static Scanner getScanner() {
		return new Scanner(System.in);
	}
}
