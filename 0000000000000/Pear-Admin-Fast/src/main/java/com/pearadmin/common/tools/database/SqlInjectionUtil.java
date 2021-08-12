package com.pearadmin.common.tools.database;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

/**
 * sql注入处理工具类
 *
 * @author zhoujf
 */
@Slf4j
public class SqlInjectionUtil {
	/**
	 * sign 用于表字典加签的盐值【SQL漏洞】
	 * （上线修改值 20200501，同步修改前端的盐值）
	 */
	private final static String TABLE_DICT_SIGN_SALT = "20200501";
	private final static String XSS_STR = "'|and |exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |;|or |+|,";
	private final static String[] XSS_ARR = XSS_STR.split("\\|");

	/*
	* 针对表字典进行额外的sign签名校验（增加安全机制）
	* @param dictCode:
	* @param sign:
	* @param request:
	* @Return: void
	*/
	public static void checkDictTableSign(String dictCode, String sign, HttpServletRequest request) {
		//表字典SQL注入漏洞,签名校验
		String accessToken = request.getHeader("X-Access-Token");
		String signStr = dictCode + SqlInjectionUtil.TABLE_DICT_SIGN_SALT + accessToken;
		String javaSign = MD5(signStr.getBytes());
		if (!javaSign.equals(sign)) {
			log.error("表字典，SQL注入漏洞签名校验失败 ：" + sign + "!=" + javaSign+ ",dictCode=" + dictCode);
			throw new RuntimeException("无权限访问！");
		}
		log.info(" 表字典，SQL注入漏洞签名校验成功！sign=" + sign + ",dictCode=" + dictCode);
	}

	/** 字节数组，计算MD5值 */
	public static String MD5(byte[] data)
	{
		try
		{
			// 获取data的MD5摘要
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// mdInst.update(content.getBytes());
			digest.update(data);
			byte[] array = digest.digest();

			// 转换为十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < array.length; i++)
			{
				String shaHex = Integer.toHexString(array[i] & 0xFF);
				if (shaHex.length() < 2)
				{
					buf.append(0);
				}
				buf.append(shaHex);
			}
			return buf.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * sql注入过滤处理，遇到注入关键字抛异常
	 *
	 * @param value
	 * @return
	 */
	public static void filterContent(String value) {
		specialFilterContent(value,XSS_STR);
	}

	/**
	 * sql注入过滤处理，遇到注入关键字抛异常
	 *
	 * @param values
	 * @return
	 */
	public static void filterContent(String[] values) {
		//String[] xssArr = XSS_STR.split("\\|");
		for (String value : values) {
			if (value == null || "".equals(value)) {
				return;
			}
			// 统一转为小写
			value = value.toLowerCase();
			for (String s : XSS_ARR) {
				if (value.contains(s)) {
					log.error("请注意，存在SQL注入关键词---> {}", s);
					log.error("请注意，值可能存在SQL注入风险!---> {}", value);
					throw new RuntimeException("请注意，值可能存在SQL注入风险!--->" + value);
				}
			}
		}
	}

	/**
	 * @特殊方法(不通用) 仅用于字典条件SQL参数，注入过滤
	 * @param value
	 * @return
	 */

	public static void specialFilterContent(String value) {
		String specialXssStr = " exec | insert | select | delete | update | drop | count | chr | mid | master | truncate | char | declare |;|+|";
		specialFilterContent(value,specialXssStr);
	}


    /**
     * @特殊方法(不通用) 仅用于Online报表SQL解析，注入过滤
     * @param value
     * @return
     */
	@Deprecated
	public static void specialFilterContentForOnlineReport(String value) {
		String specialXssStr = " exec | insert | delete | update | drop | chr | mid | master | truncate | char | declare |";
		specialFilterContent(value,specialXssStr);
	}

	private static void specialFilterContent(String value,String specialXssStr){
		String[] xssArr = specialXssStr.split("\\|");
		specialFilterContent(value,xssArr);
	}

	private static void specialFilterContent(String value,String[] xssArr){
		if (value == null || "".equals(value)) {
			return;
		}
		// 统一转为小写
		value = value.toLowerCase();
		for (String s : xssArr) {
			if (value.contains(s) || value.startsWith(s.trim())) {
				log.error("请注意，存在SQL注入关键词---> {}", s);
				log.error("请注意，值可能存在SQL注入风险!---> {}", value);
				throw new RuntimeException("请注意，值可能存在SQL注入风险!--->" + value);
			}
		}
	}

}
