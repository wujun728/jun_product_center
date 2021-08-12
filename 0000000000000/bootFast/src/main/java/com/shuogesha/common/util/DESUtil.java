package com.shuogesha.common.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.shuogesha.common.model.Token; 

public class DESUtil {
	// DES加密的私钥，必须是8位长的字符串
	private static final byte[] DESkey = "88888888".getBytes();// 设置密钥

	private static final byte[] DESIV = "12345678".getBytes();// 设置向量

	static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
	private static Key key = null;

	static {
		DESKeySpec keySpec;
		try {
			keySpec = new DESKeySpec(DESkey);// 设置密钥参数
			iv = new IvParameterSpec(DESIV);// 设置向量
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		} catch (Exception e) {
			
		}

	}

	public static String encrypt(String data) {
		try {
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
			byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(pasByte).toString().replace("\n", "").replace("\r", "");// 去掉换行回车符
		} catch (Exception e) {
			System.out.println("加密异常：" + e.getMessage());
		}
		return "";
	}

	public static String decrypt(String data) {
		try {
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			deCipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] pasByte = deCipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
			return new String(pasByte, "UTF-8");
		} catch (Exception e) {
			System.out.println("解密异常：" + e.getMessage());
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		String data = "XO7p0jevjpM6m0Zq1bWF9R3FD6h6HVmzQMjxuPpDnhcupMsT3cIW6m3atpH+YQFljMPhpbVbr4AYp81ALhMQV6PamNEyAk6wF2FJGN8OQH23aAeDWxnBH2Yj8YTazjns";

		System.out.println("加密：" + DESUtil.encrypt("boonya"));

		System.out.println("解密：" + DESUtil.decrypt(DESUtil.encrypt("boonya")));

		System.out.println("CShap data Length " + (data.getBytes().length % 8));
		System.out.println("CShap解密：" + DESUtil.decrypt(data));

		Token encryptedToken = new Token();
		encryptedToken.setSignature("wmsadmin");
		encryptedToken.setTimestamp(Calendar.getInstance().getTimeInMillis() + "");
		encryptedToken.setRandom(new Random().nextInt(999999999) + "");
		String text = encryptedToken.toString();
		System.out.println("明文：" + text);
		String encryptData = DESUtil.encrypt(text);
		System.out.println("加密：" + encryptData);

		System.out.println("解密：" + DESUtil.decrypt(DESUtil.encrypt(text)));

		long time = new Date().getTime();
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(new Date(Calendar.getInstance().getTimeInMillis()));
		System.out.println(time);

	}
}
