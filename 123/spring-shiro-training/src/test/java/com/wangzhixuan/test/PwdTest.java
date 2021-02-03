package com.wangzhixuan.test;

import com.wangzhixuan.commons.shiro.PasswordHash;

/**
 * 生成密码
 * 
 * @author L.cm
 */
public class PwdTest {

	public static void main(String[] args) {
		PasswordHash hash = new PasswordHash();
		hash.setAlgorithmName("MD5");
		hash.setHashIterations(1);
		System.out.println(hash.toHex("test", "test"));
	}
}
