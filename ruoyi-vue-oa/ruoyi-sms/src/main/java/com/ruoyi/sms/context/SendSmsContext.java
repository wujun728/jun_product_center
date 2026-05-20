package com.ruoyi.sms.context;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.sms.factory.SendSmsFactory;
import com.ruoyi.sms.handler.AbstractSmsHandler;

/**
 * 短信处理器上下文
 * @author Shao.x
 *
 */
public class SendSmsContext {

	/**
	 * 获取发送短信处理器
	 * @return
	 * @throws BaseException
	 */
	public static AbstractSmsHandler getSmsHandler(String useRouter) throws BaseException {
		return SendSmsFactory.getInstance().getSmsHandler(useRouter);
	}
	
}
