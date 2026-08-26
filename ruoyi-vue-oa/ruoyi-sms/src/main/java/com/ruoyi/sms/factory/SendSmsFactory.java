package com.ruoyi.sms.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.sms.handler.AliyunSmsHandler;
import com.ruoyi.sms.handler.AbstractSmsHandler;
import com.ruoyi.sms.handler.TencentSmsHandler;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;

/**
 * 短信处理器工厂
 * @author wocurr.com
 */
public class SendSmsFactory {
	
	private Map<String, Class<? extends AbstractSmsHandler>> strategyMap = new ConcurrentHashMap<>();
	
	/**
	 * 扩展实现
	 */
	private SendSmsFactory() {
		strategyMap.put("aliyun", AliyunSmsHandler.class);
		strategyMap.put("tencent", TencentSmsHandler.class);
	}
	
	private static class InnerFactory {
		private final static SendSmsFactory msgFactory = new SendSmsFactory();
	}
	
	public static SendSmsFactory getInstance() {
		return InnerFactory.msgFactory;
	}
	
	public AbstractSmsHandler getSmsHandler(String useRoute) throws BaseException {
		Class<? extends AbstractSmsHandler> clazz = strategyMap.get(useRoute);
		if(clazz == null) {
			throw new BaseException("类型不支持");
		}
		return ApplicationContextHelper.getBean(clazz);
	}
}
