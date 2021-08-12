package com.shuogesha.mq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuogesha.mq.order.OrderDelayedReceiver;

import sun.misc.BASE64Encoder;

@Component
public class MQUtils {
	private static Logger log = LoggerFactory.getLogger(OrderDelayedReceiver.class); 

	@Value("${spring.rabbitmq.host}")
	private String host;
	@Value("${spring.rabbitmq.port}")
	private String port;
	@Value("${spring.rabbitmq.username}")
	private String username;
	@Value("${spring.rabbitmq.password}")
	private String password;
	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;

	/**
	 * 队列任务总数
	 *
	 * @param queueName
	 * @return
	 */
	public int getMessageCount(String queueName) throws IOException {
		String apiMessage = getApiMessage(queueName);
		if (Objects.equals(apiMessage, "")) {
			log.error("请求RabbitMQ API时出错！！");
			return 0;
		}
		JSONObject jsonObject = JSON.parseObject(apiMessage);
		return Integer.parseInt(jsonObject.get("messages").toString());
	}

	/**
	 * 队列ready任务数
	 *
	 * @param queueName
	 * @return
	 */
	public int getMessageReadyCount(String queueName) throws IOException {
		String apiMessage = getApiMessage(queueName);
		if (Objects.equals(apiMessage, "")) {
			log.error("请求RabbitMQ API时出错！！");
			return 0;
		}
		JSONObject jsonObject = JSON.parseObject(apiMessage);
		return Integer.parseInt(jsonObject.get("messages_ready").toString());
	}

	/**
	 * 队列unack数MQ
	 *
	 * @param queueName
	 * @return
	 */
	public int getMessagesUnacknowledgedCount(String queueName) throws IOException {
		String apiMessage = getApiMessage(queueName);
		if (Objects.equals(apiMessage, "")) {
			log.error("请求RabbitMQ API时出错！！");
			return 0;
		}
		JSONObject jsonObject = JSON.parseObject(apiMessage);
		return Integer.parseInt(jsonObject.get("messages_unacknowledged").toString());
	}

	/**
	 * 获取队列消息总数、ready消息数、unack消息数
	 *
	 * @param queueName
	 * @return Map<String,Integer>
	 */
	public Map<String, Integer> getMQCountMap(String queueName) throws IOException {
		String apiMessage = getApiMessage(queueName);
		JSONObject jsonObject = JSON.parseObject(apiMessage);
		Map<String, Integer> map = new HashMap<>();
		map.put("messages", Integer.parseInt(jsonObject.get("messages").toString()));
		map.put("messages_ready", Integer.parseInt(jsonObject.get("messages_ready").toString()));
		map.put("messages_unacknowledged", Integer.parseInt(jsonObject.get("messages_unacknowledged").toString()));
		return map;
	}

	public String getApiMessage(String queueName) throws IOException {
		// 发送一个GET请求
		HttpURLConnection httpConn = null;
		BufferedReader in = null;

		String urlString = "http://" + host + ":" + port + "/api/queues/" + virtualHost + "/" + queueName;
		URL url = new URL(urlString);
		httpConn = (HttpURLConnection) url.openConnection();
		// 设置用户名密码
		String auth = username + ":" + password;
		BASE64Encoder enc = new BASE64Encoder();
		String encoding = enc.encode(auth.getBytes());
		httpConn.setDoOutput(true);
		httpConn.setRequestProperty("Authorization", "Basic " + encoding);
		// 建立实际的连接
		httpConn.connect();
		// 读取响应
		if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			StringBuilder content = new StringBuilder();
			String tempStr = "";
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			while ((tempStr = in.readLine()) != null) {
				content.append(tempStr);
			}
			in.close();
			httpConn.disconnect();
			return content.toString();
		} else {
			httpConn.disconnect();
			return "";
		}
	}
}
