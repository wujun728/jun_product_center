package com.ruoyi.sms.handler;

import java.util.List;
import java.util.Map;

/**
 * 短信处理器，抽象父类
 * @author wocurr.com
 */
public abstract class AbstractSmsHandler {
	
    /**
     * 发送通知
     * @param phones 手机号
     * @param templateCode 模板code
     * @param params 变量参数
     * @return
     */
    public abstract boolean send(List<String> phones, String templateCode, Map<String, String> params);
}
