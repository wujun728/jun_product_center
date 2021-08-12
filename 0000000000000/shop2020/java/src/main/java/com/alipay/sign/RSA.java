package com.alipay.sign;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class RSA {

	private static final String SIGN_TYPE_RSA = "RSA";
	private static final String SIGN_TYPE_RSA2 = "RSA2";
	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
	private static final int DEFAULT_BUFFER_SIZE = 8192;
	
	public static boolean verify(String content,String signType, String sign,
			String ali_public_key, String input_charset) {
		try { 
			java.security.Signature signature = null; 
 			PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(ali_public_key.getBytes()));
			if (SIGN_TYPE_RSA.equals(signType)) {
				signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			} else if (SIGN_TYPE_RSA2.equals(signType)) {
				signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
			} else {
				throw new Exception("不是支持的签名类型 : signType=" + signType);
			}
			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(input_charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(input_charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	

	/**
	 *  生成签名  
	 *  
	 */
	public static String rsaSign(Map map) throws Exception {
		PrivateKey priKey = null;
		java.security.Signature signature = null;
		String signType = map.get("sign_type").toString();
		String privateKey = map.get("privateKey").toString();
		String charset = map.get("charset").toString();
		String content = getSignContent(map);
		// map.put("content", content);
		// System.out.println("请求参数生成的字符串为:" + content);
		if (SIGN_TYPE_RSA.equals(signType)) {
			priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
			signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		} else if (SIGN_TYPE_RSA2.equals(signType)) {
			priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
			signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
		} else {
			throw new Exception("不是支持的签名类型 : : signType=" + signType);
		}
		signature.initSign(priKey);

		if (StringUtils.isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		byte[] signed = signature.sign();

		return new String(Base64.encodeBase64(signed));

	}

	/**
	 * 81 * 验签方法 
	 */
	public static boolean rsaCheck(Map map, String sign) throws Exception {
		java.security.Signature signature = null;
		String signType = map.get("sign_type").toString();
		String privateKey = map.get("privateKey").toString();
		String charset = map.get("charset").toString();
		String content = map.get("content").toString();
		String publicKey = map.get("publicKey").toString();
		System.out.println(">>验证的签名为:" + sign);
		System.out.println(">>生成签名的参数为:" + content);
		PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
		if (SIGN_TYPE_RSA.equals(signType)) {
			signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		} else if (SIGN_TYPE_RSA2.equals(signType)) {
			signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
		} else {
			throw new Exception("不是支持的签名类型 : signType=" + signType);
		}
		signature.initVerify(pubKey);

		if (StringUtils.isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}

		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}

	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if (ins == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		byte[] encodedKey = readText(ins).getBytes();
		encodedKey = Base64.decodeBase64(encodedKey);
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}

	public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		StringWriter writer = new StringWriter();
		io(new InputStreamReader(ins), writer, -1);

		byte[] encodedKey = writer.toString().getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}

	/**
	 * 147 * 把参数合成成字符串 148 * 149 * @param sortedParams 150 * @return 151
	 */
	public static String getSignContent(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		// app_id,method,charset,sign_type,version,bill_type,timestamp,bill_date
		String[] sign_param = sortedParams.get("sign_param").split(",");// 生成签名所需的参数
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < sign_param.length; i++) {
			keys.add(sign_param[i]);
		}
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			/*
			 * if ("biz_content".equals(key)) { content.append( (index == 0 ? ""
			 * : "&") + key + "={\"bill_date\":\"" +
			 * sortedParams.get("bill_date") + "\",") .append("\"bill_type\":\""
			 * + sortedParams.get("bill_type") + "\"}"); index++; } else {
			 */
			String value = sortedParams.get(key);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
			// }
		}
		return content.toString();
	}

	private static String readText(InputStream ins) throws IOException {
		Reader reader = new InputStreamReader(ins);
		StringWriter writer = new StringWriter();

		io(reader, writer, -1);
		return writer.toString();
	}

	private static void io(Reader in, Writer out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE >> 1;
		}

		char[] buffer = new char[bufferSize];
		int amount;

		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}
	
	////////////////////////////////////////////////
//	/**
//	 * RSA签名
//	 * 
//	 * @param content
//	 *            待签名数据
//	 * @param privateKey
//	 *            商户私钥
//	 * @param input_charset
//	 *            编码格式
//	 * @return 签名值
//	 */
//	public static String sign(String content, String privateKey,
//			String input_charset) {
//		try {
//			byte[] decode = Base64.decode(privateKey);
//			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decode);
//			KeyFactory keyf = KeyFactory.getInstance("RSA");
//			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
//
//			java.security.Signature signature = java.security.Signature
//					.getInstance(SIGN_ALGORITHMS);
//
//			signature.initSign(priKey);
//			signature.update(content.getBytes(input_charset));
//
//			byte[] signed = signature.sign();
//
//			return Base64.encode(signed);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	/**
//	 * RSA验签名检查
//	 * 
//	 * @param content
//	 *            待签名数据
//	 * @param sign
//	 *            签名值
//	 * @param ali_public_key
//	 *            支付宝公钥
//	 * @param input_charset
//	 *            编码格式
//	 * @return 布尔值
//	 */
//	public static boolean verify(String content, String sign,
//			String ali_public_key, String input_charset) {
//		try {
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			byte[] encodedKey = Base64.decode(ali_public_key);
//			PublicKey pubKey = keyFactory
//					.generatePublic(new X509EncodedKeySpec(encodedKey));
//			java.security.Signature signature = java.security.Signature
//					.getInstance(SIGN_ALGORITHMS);
//
//			signature.initVerify(pubKey);
//			signature.update(content.getBytes(input_charset));
//
//			boolean bverify = signature.verify(Base64.decode(sign));
//			return bverify;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	/**
//	 * 解密
//	 * 
//	 * @param content
//	 *            密文
//	 * @param private_key
//	 *            商户私钥
//	 * @param input_charset
//	 *            编码格式
//	 * @return 解密后的字符串
//	 */
//	public static String decrypt(String content, String private_key,
//			String input_charset) throws Exception {
//		PrivateKey prikey = getPrivateKey(private_key);
//
//		Cipher cipher = Cipher.getInstance("RSA");
//		cipher.init(Cipher.DECRYPT_MODE, prikey);
//
//		InputStream ins = new ByteArrayInputStream(Base64.decode(content));
//		ByteArrayOutputStream writer = new ByteArrayOutputStream();
//		// rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
//		byte[] buf = new byte[128];
//		int bufl;
//
//		while ((bufl = ins.read(buf)) != -1) {
//			byte[] block = null;
//
//			if (buf.length == bufl) {
//				block = buf;
//			} else {
//				block = new byte[bufl];
//				for (int i = 0; i < bufl; i++) {
//					block[i] = buf[i];
//				}
//			}
//
//			writer.write(cipher.doFinal(block));
//		}
//
//		return new String(writer.toByteArray(), input_charset);
//	}
//
//	/**
//	 * 得到私钥
//	 * 
//	 * @param key
//	 *            密钥字符串（经过base64编码）
//	 * @throws Exception
//	 */
//	public static PrivateKey getPrivateKey(String key) throws Exception {
//
//		byte[] keyBytes;
//
//		keyBytes = Base64.decode(key);
//
//		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//		return privateKey;
//	}

}