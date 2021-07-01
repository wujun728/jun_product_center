package com.du.lin.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.du.lin.config.properties.LinProperties;
@Component
public class LinTools {
	@Autowired
	private LinProperties linProperties;

	/**
	 * 获取验证码开关
	 * @return
	 */
	public boolean getKaptchaSwich(){
		return linProperties.isKptchaswich();
	}

	/**
	 * 生成随机盐值
	 * @return
	 */
	public String getSalt(){
		String random = String.valueOf(new Random().nextLong());
		String randomMD5 = MD5Util.encrypt(random);
		return randomMD5.substring(0, 5);
	}
	
}
