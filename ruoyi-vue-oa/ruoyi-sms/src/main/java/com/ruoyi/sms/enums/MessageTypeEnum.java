package com.ruoyi.sms.enums;

/**
 * 消息类型枚举
 *
 * @author wocurr.com
 */
public enum MessageTypeEnum {
	/**
	 * 短信
	 */
	SMS(1),
	/**
	 * 邮件
	 */
	EMAIL(2),
    ;

    private final Integer value;

    MessageTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
		return value;
	}

}
