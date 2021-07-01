package com.du.lin.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Lin系统的一些属性
 */
@Component
public class LinProperties {
	public static final String PREFIX = "lin";
	/**
	 * 验证码开关
	 */
	@Value("${lin.kaptchaswich}")
	private boolean kptchaswich = false;

	public boolean isKptchaswich() {
		return kptchaswich;
	}

	public void setKptchaswich(boolean kptchaswich) {
		this.kptchaswich = kptchaswich;
	}


	
	
	
}
