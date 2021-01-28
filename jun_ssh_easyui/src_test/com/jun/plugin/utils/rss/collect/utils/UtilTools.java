package com.jun.plugin.utils.rss.collect.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Wujun
 * @Url www.cntaige.com
 * @Date 2014年11月11日
 */
public class UtilTools {
	// 公共工具
	public static InputStream getImageInputStream(String httpUrl)
			throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setConnectTimeout(20000);
		httpURLConnection.setReadTimeout(20000);
		InputStream inputStream = httpURLConnection.getInputStream();
		return inputStream;
	}

	public static String getNetImage(String httpUrl, String pathName)
			throws IOException {
		// 返回写入图片的路径
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte data[] = new byte[1024];
		InputStream inputStream = getImageInputStream(httpUrl);
		int len = 0;
		while ((len = inputStream.read(data)) != -1) {
			byteArrayOutputStream.write(data, 0, len);
		}
		File file = new File(pathName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(pathName);
		fileOutputStream.write(byteArrayOutputStream.toByteArray());
		if (fileOutputStream != null) {
			fileOutputStream.close();
		}
		if (byteArrayOutputStream != null) {
			byteArrayOutputStream.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}
		return pathName;
	}

	public static void deleteAllFile(String pathName) {
		// 删除所有文件及文件夹
		File file = new File(pathName);
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
			return;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFile(files[i].getPath());// 递归
		}
		file.delete();
	}

	public static String getNowTime() {
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.CHINA)
				.format(new Date());
	}

	public static String getNowTimeNumber() {
		return new SimpleDateFormat("yyyMMddHHmmss", Locale.CHINA)
				.format(new Date());
	}
}
