package org.myframework.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtils {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EncryptUtils.class);

	private static final String FORMAT_DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	// 系统生成令牌的过期时间
	public static final int TOKEN_EXPIRED_DATE = 3; // days

	private static final String TOKEN_KEY = "com.hollycrm.hollybeacon.imes.tokenKey";

	public static final String TOKEN_PREFIX = "#TOKEN";

	public static final String TOKEN_SEPARATOR = ",";

	public static final String ENCODE_TYPE = "MD5";// MD5,SHA-1

	private static final String addTokenPrefix(String str) {
		return TOKEN_PREFIX + str;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	private static String decodePBEToken(String inputStr) {
		try {
			PBEKeySpec pbeKeySpec = new PBEKeySpec(TOKEN_KEY.toCharArray());
			SecretKeyFactory keyFac = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
					(byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

			int count = 2;

			// 生成pbe算法所需的参数对象，两个参数详见 RSA的 PKCS #5 标准
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);

			// 生成一个加密器
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			// 初始化加密器
			pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

			// 密文
			byte[] ciphertext = hex2Bytes(inputStr);

			// 解密
			byte[] cleartext = pbeCipher.doFinal(ciphertext);

			// 返回明文
			return new String(cleartext);
		} catch (Exception e) {
			LOGGER.error("decodePBEToken error", e);
		}
		return "";
	}

	public static String decodeToken(String inputStr) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(TOKEN_KEY.getBytes());

			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

			// 现在，获取数据并解密
			// 正式执行解密操作
			return new String(cipher.doFinal(hex2Bytes(inputStr)));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("decodeToken error" + e);
			return "";
		}
	}

	/**
	 * 将明文的密码以SHA-1算法加密
	 *
	 * @param passwd
	 *            明文密码
	 * @return String MD5算法加密后的密码签名
	 */
	public static String encodeStr(String str) {
		MessageDigest md = null;
		byte[] bt = str.getBytes();
		String strDes = null;
		try {
			md = MessageDigest.getInstance(ENCODE_TYPE);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Invalid algorithm.", e);
			return null;
		}

		return strDes;
	}

	/**
	 * @param str
	 * @param algorithm
	 * @return
	 */
	public static String encodeStr(String str, String algorithm) {
		MessageDigest md = null;
		byte[] bt = str.getBytes();
		String strDes = null;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Invalid algorithm.", e);
			return null;
		}

		return strDes;
	}

	private static String encodePBEToken(String inputstr) {

		try {
			PBEKeySpec pbeKeySpec = new PBEKeySpec(TOKEN_KEY.toCharArray());
			SecretKeyFactory keyFac = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
					(byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

			int count = 2;

			// 生成pbe算法所需的参数对象，两个参数详见 RSA的 PKCS #5 标准
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);

			// 生成一个加密器
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			// 初始化加密器
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

			// 明文
			byte[] cleartext = inputstr.getBytes();

			// 加密
			byte[] ciphertext = pbeCipher.doFinal(cleartext);

			// 返回密文
			return bytes2Hex(ciphertext);
		} catch (Exception e) {
			LOGGER.error("encodePBEToken error", e);
			return null;
		}
	}

	public static String encodeToken(String inputstr) {

		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(TOKEN_KEY.getBytes());

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

			// 现在，获取数据并加密
			// 正式执行加密操作
			return bytes2Hex(cipher.doFinal(inputstr.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("encodeToken error" + e);
			return "";
		}
	}

	/**
	 *  用户登录验证成功后，系统自动为用户生成TOKEN : 通过可逆加密算法，将username + login
	 * timestamp转换为字符串，作为TOKEN返回给客户端。
	 *
	 * @param username
	 *            用户登录名
	 */
	public static String generateToken(Long userId, String username) {
		String currentDate = getCurrentDateAsString(
				FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);

		String before_token_str = currentDate + TOKEN_SEPARATOR + userId
				+ TOKEN_SEPARATOR + username;

		return addTokenPrefix(encodeToken(before_token_str));
	}

	private static final String getCurrentDateAsString(String formatPattern) {
		Date date = new Date();
		return new SimpleDateFormat(formatPattern).format(date);
	}

	private static byte[] hex2Bytes(String srcStr) {
		int byte_len = srcStr.length() / 2;
		byte[] result = new byte[byte_len];
		for (int i = 0; i < byte_len; i++) {
			String bytestr = srcStr.substring(i * 2, i * 2 + 2);
			result[i] = Integer.valueOf(bytestr, 16).byteValue();
		}
		return result;
	}

	public static final boolean isEncryptToken(String str) {
		return str.startsWith(TOKEN_PREFIX);
	}

	public static void main(String[] args) {
		System.out.println(encodeStr("admin"));

	}

	private static final String removeTokenPrefix(String str) {
		if (str.startsWith(TOKEN_PREFIX)) {
			str = str.substring(TOKEN_PREFIX.length());
		}
		return str;
	}

	/**
	 * 验证用户输入的密码是否与数据库中SHA-1算法加密后的密码相同
	 *
	 * @param passwd
	 *            明文密码
	 * @param encode_passwd
	 *            MD5/SHA-1加密算法后的密码签名
	 * @return true 相同 false 不相同
	 */
	public static boolean validatePassword(String passwd,
			String encode_passwd) {
		if (null == passwd || null == encode_passwd) {
			return false;
		}
		if (encode_passwd.equals(encodeStr(passwd))) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * 验证用户的TOKEN是否正确 : 通过可逆加密算法，将Token拆分为username和login timestamp。
	 * 判断拆分出来的username是否与输入的用户名相同，login时间是否没有超过指定的有效期
	 *
	 * @param username
	 *            用户登录用ID
	 * @param token
	 *            用户的Token
	 *
	 * @return Token以 {@link #TOKEN_SEPARATOR}分割后的字符串数组
	 *
	 * @throws Exception
	 *             Token验证错误时抛出异常
	 */
	public static final String[] validateToken(String username, String token)
			throws Exception {
		Calendar currentTime = Calendar.getInstance();
		String encryptToken = removeTokenPrefix(token);
		// remove prefix
		encryptToken = decodeToken(encryptToken);
		if (null == encryptToken || "".equals(encryptToken)) {
			throw new Exception("Unable to decode encrypted token.");
		}

		String[] sepIndex = encryptToken.split(TOKEN_SEPARATOR);
		if (sepIndex == null || sepIndex.length <= 0) {
			throw new Exception("Unable to decode encrypted token.");
		}

		String strtokenTime = sepIndex[0];
		String strtokenUserid = sepIndex[1];
		String strtokenUsername = sepIndex[2];
		try {
			Long.parseLong(strtokenUserid);
		} catch (Exception e) {
			throw new Exception(
					"Unable to find match user name from encrypted token.");
		}

		if (!username.equalsIgnoreCase(strtokenUsername)) {
			throw new Exception(
					"Logged in username does not match that from encrypted token.");
		}

		Date tokenDate = DateUtils.parse(strtokenTime,
				FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);

		Calendar expiredDate = Calendar.getInstance();
		expiredDate.setTime(tokenDate);
		expiredDate.add(Calendar.DAY_OF_MONTH, TOKEN_EXPIRED_DATE);
		if (expiredDate.before(currentTime)) {
			throw new Exception("Logged in user session expired.");
		}
		return sepIndex;
	}

}
