package com.ruoyi.sms.api;

import java.util.List;
import java.util.Map;


/**
 * 短信发送服务
 * @author wocurr.com
 */
public interface ISmsService {

	/**
	 * 发送通知（单个）
	 *
	 * @param phone 手机号
	 * @param templateCode 模板code
	 * @param params 变量参数
	 */
	boolean send(String phone, String templateCode, Map<String, String> params);

	/**
	 * 发送通知（批量，单次发送不要超过200个）
	 * @param phones 手机号
	 * @param templateCode 模板code
	 * @param params 变量参数
	 * @return
	 */
	boolean send(List<String> phones, String templateCode, Map<String, String> params);

}
