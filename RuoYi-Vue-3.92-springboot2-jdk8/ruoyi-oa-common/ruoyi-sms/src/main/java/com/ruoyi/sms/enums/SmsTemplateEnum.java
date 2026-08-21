package com.ruoyi.sms.enums;

/**
 * 短信模板枚举
 *
 * @author wocurr.com
 */
public enum SmsTemplateEnum {
	/**
	 * 验证码（变量：code）
	 */
	CODE("SMS_218670292"),
    /**
     * 密码重置通知（变量参数：password）
     */
    REPWD("SMS_218905847"),
    /**
     * 分配用户账号（变量参数：username,password）
     */
    ALLOT_USER("SMS_219140149"),
    ;

    private final String value;

    SmsTemplateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
		return value;
	}

}
