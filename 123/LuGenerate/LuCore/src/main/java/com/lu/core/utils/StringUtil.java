/**
 * 
 */
package com.lu.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {
	static final String[] HEX_SPLIT_STRING = { ":", " " };
	static final String Dateoid = "1.3.6.1.2.1.25.1.2.0";
	static final String[] CHANGE2CHINESE_OID = { "1.3.6.1.2.1.25.6.3.1.2", "1.3.6.1.2.1.2.2.1.2", "1.3.6.1.2.1.1.5.0",
			"1.3.6.1.2.1.1.1", "1.3.6.1.2.1.1.4", "1.3.6.1.2.1.25.2.3.1.3", "1.3.6.1.4.1.19849.2.8.2",
			"1.3.6.1.4.1.19849.2.8.3" };

    private static final int INDEX_NOT_FOUND = -1;

    private static final String EMPTY = "";

    private static final int PAD_LIMIT = 8192;


	/**
	 * 异常信息提取
	 * @param e
	 * @return
	 */
	public static String errorMessage(Exception e){
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		e.fillInStackTrace().printStackTrace(printWriter);
		return result.toString();
	}


	/**
	 * 生成随机账号
	 * @return
	 */
	public static String uuid() {
		int machineId = 1; //最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%011d", hashCodeV);
	}

	/**
	 * 生成随机串
	 * @param length	表示生成字符串的长度
	 * @return
	 */
	public static String getRandomStr(int length){
    	String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(base.charAt(random.nextInt(base.length())));
		}
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * @param value
	 * @return
	 */
	public static String acronymToUpperCase(String value){
		char[] cs = value.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 首字母小写
	 * @param value
	 * @return
	 */
	public static String acronymToLowerCase(String value){
		char[] cs = value.toCharArray();
		cs[0] += 32;
		return String.valueOf(cs);
	}

	/**
	 * 用户属性的key生成
	 * @param code
	 * @param key
	 * @return
	 */
	public static String userRedisKey(String code, String key){
		return code + ":" + key;
	}

	/**
	 * 用户状态属性的key生成
	 * @param code
	 * @return
	 */
	public static String userStateRedisKey(String code){
		return "user_state:" + code;
	}

	/**
	 * 用户授权级别属性的key生成
	 * @param code
	 * @return
	 */
	public static String userAuthLevelRedisKey(String code){
		return "user_auth_level:" + code;
	}

	/**
	 * 文章授权级别属性的key生成
	 * @param id
	 * @return
	 */
	public static String articleAuthLevelRedisKey(Long id){
		return "article_auth_level:" + id;
	}

	/**
	 * 	资源授权级别属性的key生成
	 * @param id
	 * @return
	 */
	public static String resourceAuthLevelRedisKey(Long id){
		return "resource_auth_level:" + id;
	}

	/**
	 * 随机生成人名
	 * @return
	 */
	public static String getPeopleName() {
		Random random = new Random();
		String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
				"何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
				"鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
				"罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
				"穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
				"屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "卢", "胡"};
		String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
		String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
		int index = random.nextInt(Surname.length - 1);
		String name = Surname[index]; //获得一个随机的姓氏
		int i = random.nextInt(3);//可以根据这个数设置产生的男女比例
		if(i==2){	//女
			int j = random.nextInt(girl.length()-2);
			if (j % 2 == 0) {
				name = name + girl.substring(j, j + 2);
			} else {
				name = name + girl.substring(j, j + 1);
			}

		}
		else{	//男
			int j = random.nextInt(girl.length()-2);
			if (j % 2 == 0) {
				name = name + boy.substring(j, j + 2);
			} else {
				name = name + boy.substring(j, j + 1);
			}

		}

		return name;
	}

	/**
     * 判断字符串是否是16进制
     * @param value
     * @return
     */
	public static boolean isHex(String value) {
		if ((value == null) || (value.length() == 0)) {
			return false;
		}
		String[] arrayOfString;
		int j = (arrayOfString = HEX_SPLIT_STRING).length;
		for (int i = 0; i < j; i++) {
			String splitStr = arrayOfString[i];
			if (value.indexOf(splitStr) >= 0) {
				value = value + splitStr;
				String rex = "([0-9a-fA-F][0-9a-fA-F][" + splitStr + "])+";
				Pattern p = Pattern.compile(rex);
				if (p.matcher(value).matches()) {
					return true;
				}
			}
		}
		return false;
	}

	public static String hexToChinese(String value, String oid) {
		if (value == null) {
			return null;
		} 
		String rex = "([0-9a-fA-F]{4})(((\\.)([0-9a-fA-F]{4})){2})";
		if (Pattern.matches(rex, value)) {
			value = value.replaceAll("\\.", "");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < value.length(); i += 2) {
				sb.append(value.substring(i, i + 2)).append(" ");
			}
			return sb.toString().trim();
		}
		if (isChineseOid(oid)) { 
			return hexToChinese(value);
		}
		if (oid.endsWith("1.3.6.1.2.1.25.1.2.0")) {
			return transeSnmp4jDate(value);
		}
		if (isHex(value)) {
			value = value.replaceAll(":", " ");
		}
		return value;
	}

	public static String hexToChinese(String value) {
		if (value == null) {
			return null;
		}
		String hexString = value.trim();
		if (!isHex(hexString)) {
			return hexString;
		}
		String[] splitStrs = HEX_SPLIT_STRING;
		String[] splitResult = null;
		String[] arrayOfString1;
		int j = (arrayOfString1 = splitStrs).length;
		for (int i = 0; i < j; i++) {
			String splitStr = arrayOfString1[i];
			if (hexString.indexOf(splitStr) > 0) {
				splitResult = hexString.split(splitStr);
				break;
			}
		}
		String result = null;
		if (splitResult != null) {
			byte[] bs = new byte[splitResult.length];
			int i = 0;
			String[] arrayOfString2;
			int m = (arrayOfString2 = splitResult).length;
			for (int k = 0; k < m; k++) {
				String st = arrayOfString2[k];
				byte tmp = Integer.valueOf(st, 16).byteValue();
				if (Character.isISOControl(tmp)) {
					tmp = 32;
				}
				bs[(i++)] = tmp;
			}
			result = new String(bs).trim();
		}
		return result;
	}

	private static boolean isChineseOid(String oid) {
		if (oid == null) {
			return false;
		}
		String[] arrayOfString;
		int j = (arrayOfString = CHANGE2CHINESE_OID).length;
		for (int i = 0; i < j; i++) {
			String tmpOid = arrayOfString[i];
			if (oid.startsWith(tmpOid)) {
				return true;
			}
		}
		return false;
	}

	public static String transeSnmp4jTime(String timeStr) throws Exception {
		if (timeStr == null) {
			return "";
		}
		String[] tmp = timeStr.split(":");
		StringBuffer sb = new StringBuffer();
		sb.append(tmp[0]).append(" hours, ");
		sb.append(tmp[1]).append(" minutes, ");
		sb.append(tmp[2].substring(0, tmp[2].indexOf("."))).append(" seconds.");
		return sb.toString();
	}

	public static String parseAsciiTo10(String value) {
		String[] nn = null;
		if (value.indexOf(".") >= 0) {
			nn = value.split("\\.");
		}
		if (nn != null) {
			byte[] bs = new byte[nn.length];
			for (int i = 0; i < nn.length; i++) {
				bs[i] = Byte.parseByte(nn[i]);
			}
			String[] tmp = new String(bs).split(" ");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < tmp.length; i++) {
				sb.append(Integer.parseInt(tmp[i], 16));
				if (i < tmp.length - 1) {
					sb.append(".");
				}
			}
			return sb.toString();
		}
		return null;
	}

	public static String parse16To10(String value) {
		if (value == null) {
			return null;
		}
		String[] tmp = null;
		if (value.indexOf(" ") >= 0) {
			tmp = value.split(" ");
		} else {
			tmp = new String[1];
			tmp[0] = value;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tmp.length; i++) {
			sb.append(Integer.parseInt(tmp[i], 16));
			if (i < tmp.length - 1) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	private static String transeSnmp4jDate(String value) {
		if (value == null) {
			return null;
		}
		String[] tmp = null;
		if (value.indexOf(":") >= 0) {
			tmp = value.split(":");
		} else {
			tmp = new String[1];
			tmp[0] = value;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Integer.parseInt(tmp[0] + tmp[1], 16));
		sb.append("-");
		for (int i = 2; i < tmp.length - 1; i++) {
			sb.append(Integer.parseInt(tmp[i], 16));
			if (i < tmp.length - 2) {
				if ((i == 3) && (Integer.parseInt(tmp[4], 16) < 12)) {
					sb.append("上午");
				} else if ((i == 3) && (Integer.parseInt(tmp[4], 16) >= 12)) {
					sb.append("下午");
				} else {
					sb.append("-");
				}
			}
		}
		return sb.toString();
	}


    /**
     * 生成系统流水号
     *
     * @return 长度为20的全数字
     */
    public static String genSystemNo() {
        return genSystemNo(20, true);
    }

    /**
     * 生成系统流水号
     *
     * @param length
     *            指定流水号长度
     *            指定流水号是否全由数字组成
     */
    public static String genSystemNo(int length, boolean isNumber) {
        // replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (uuid.length() > length) {
            uuid = uuid.substring(0, length);
        } else if (uuid.length() < length) {
            for (int i = 0; i < length - uuid.length(); i++) {
                uuid = uuid + Math.round(Math.random() * 9);
            }
        }
        if (isNumber) {
            return uuid.replaceAll("a", "1").replaceAll("b", "2")
                    .replaceAll("c", "3").replaceAll("d", "4")
                    .replaceAll("e", "5").replaceAll("f", "6");
        } else {
            return uuid;
        }
    }

    /**
     * 功能：将半角的符号转换成全角符号.(即英文字符转中文字符) <br>
     *
     * @param str
     *            源字符串
     * @return String
     */
    public static String changeToFull(String str) {
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
        String[] decode = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
                "'", "\"", "，", "〈", "。", "〉", "／", "？" };
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int pos = source.indexOf(str.charAt(i));
            if (pos != -1) {
                result += decode[pos];
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 功能：cs串中是否一个都不包含字符数组searchChars中的字符。
     * <p>
     * <br>
     *
     * @param cs
     *            字符串
     * @param searchChars
     *            字符数组
     * @return boolean 都不包含返回true，否则返回false。
     */
    public static boolean containsNone(CharSequence cs, char... searchChars) {
        if (cs == null || searchChars == null) {
            return true;
        }
        int csLen = cs.length();
        int csLast = csLen - 1;
        int searchLen = searchChars.length;
        int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (Character.isHighSurrogate(ch)) {
                        if (j == searchLast) {
                            // missing low surrogate, fine, like
                            // String.indexOf(String)
                            return false;
                        }
                        if (i < csLast
                                && searchChars[j + 1] == cs.charAt(i + 1)) {
                            return false;
                        }
                    } else {
                        // ch is in the Basic Multilingual Plane
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * <p>
     * 编码为Unicode，格式 '\u0020'.
     * </p>
     *
     * <pre>
     *   StringUtil.unicodeEscaped(' ') = "\u0020"
     *   StringUtil.unicodeEscaped('A') = "\u0041"
     * </pre>
     *
     * <br>
     *
     * @param ch
     *            源字符串
     * @return 转码后的字符串
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    /**
     * <p>
     * 进行tostring操作，如果传入的是null，返回空字符串。
     * </p>
     *
     * <pre>
     * StringUtil.toString(null)         = ""
     * StringUtil.toString("")           = ""
     * StringUtil.toString("bat")        = "bat"
     * StringUtil.toString(Boolean.TRUE) = "true"
     * </pre>
     *
     * @param obj
     *            源
     * @return String
     */
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * check length (minLength<=str.length<=maxLength)
     *
     * @param str
     *            input String
     * @param minLength
     *            minute length
     * @param maxLength
     *            maximum length
     * @return boolean true: minLength<=str.length<=maxLength false: other
     */
    public static boolean checkLength(String str, int minLength, int maxLength) {
        if (isBlank(str))
            return false;
        int len = str.length();
        if (minLength == 0)
            return len <= maxLength;
        else if (maxLength == 0)
            return len >= minLength;
        else
            return (len >= minLength && len <= maxLength);
    }

    /**
     * 检查字符串str是否匹配正则表达式regex
     *
     * @param regex
     * @param str
     * @return
     */
    public static boolean matcherRegex(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * <p>
     * 进行tostring操作，如果传入的是null，返回指定的默认值。
     * </p>
     *
     * <pre>
     * StringUtil.toString(null, null)           = null
     * StringUtil.toString(null, "null")         = "null"
     * StringUtil.toString("", "null")           = ""
     * StringUtil.toString("bat", "null")        = "bat"
     * StringUtil.toString(Boolean.TRUE, "null") = "true"
     * </pre>
     *
     * @param obj
     *            源
     * @param nullStr
     *            如果obj为null时返回这个指定值
     * @return String
     */
    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    /**
     * <p>
     * 只从源字符串中移除指定开头子字符串.
     * </p>
     *
     * <pre>
     * StringUtil.removeStart(null, *)      = null
     * StringUtil.removeStart("", *)        = ""
     * StringUtil.removeStart(*, null)      = *
     * StringUtil.removeStart("www.domain.com", "www.")   = "domain.com"
     * StringUtil.removeStart("domain.com", "www.")       = "domain.com"
     * StringUtil.removeStart("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeStart("abc", "")    = "abc"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param remove
     *            将要被移除的子字符串
     * @return String
     */
    public static String removeStart(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * <p>
     * 只从源字符串中移除指定结尾的子字符串.
     * </p>
     *
     * <pre>
     * StringUtil.removeEnd(null, *)      = null
     * StringUtil.removeEnd("", *)        = ""
     * StringUtil.removeEnd(*, null)      = *
     * StringUtil.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtil.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtil.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param remove
     *            将要被移除的子字符串
     * @return String
     */
    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * <p>
     * 将一个字符串重复N次
     * </p>
     *
     * <pre>
     * StringUtil.repeat(null, 2) = null
     * StringUtil.repeat("", 0)   = ""
     * StringUtil.repeat("", 2)   = ""
     * StringUtil.repeat("a", 3)  = "aaa"
     * StringUtil.repeat("ab", 2) = "abab"
     * StringUtil.repeat("a", -2) = ""
     * </pre>
     *
     * @param str
     *            源字符串
     * @param repeat
     *            重复的次数
     * @return String
     */
    public static String repeat(String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>
     * 将一个字符串重复N次，并且中间加上指定的分隔符
     * </p>
     *
     * <pre>
     * StringUtil.repeat(null, null, 2) = null
     * StringUtil.repeat(null, "x", 2)  = null
     * StringUtil.repeat("", null, 0)   = ""
     * StringUtil.repeat("", "", 2)     = ""
     * StringUtil.repeat("", "x", 3)    = "xxx"
     * StringUtil.repeat("?", ", ", 3)  = "?, ?, ?"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param separator
     *            分隔符
     * @param repeat
     *            重复次数
     * @return String
     */
    public static String repeat(String str, String separator, int repeat) {
        if (str == null || separator == null) {
            return repeat(str, repeat);
        } else {
            // given that repeat(String, int) is quite optimized, better to
            // rely
            // on it than try and splice this into it
            String result = repeat(str + separator, repeat);
            return removeEnd(result, separator);
        }
    }

    /**
     * <p>
     * 将某个字符重复N次.
     * </p>
     *
     * @param ch
     *            某个字符
     * @param repeat
     *            重复次数
     * @return String
     */
    public static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    /**
     * <p>
     * 字符串长度达不到指定长度时，在字符串右边补指定的字符.
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)     = null
     * StringUtil.rightPad("", 3, 'z')     = "zzz"
     * StringUtil.rightPad("bat", 3, 'z')  = "bat"
     * StringUtil.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtil.rightPad("bat", 1, 'z')  = "bat"
     * StringUtil.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            指定的长度
     * @param padChar
     *            进行补充的字符
     * @return String
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定字符
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            扩大后的长度
     * @param padStr
     *            在右边补充的字符串
     * @return String
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充空格
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *)   = null
     * StringUtil.leftPad("", 3)     = "   "
     * StringUtil.leftPad("bat", 3)  = "bat"
     * StringUtil.leftPad("bat", 5)  = "  bat"
     * StringUtil.leftPad("bat", 1)  = "bat"
     * StringUtil.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            扩大后的长度
     * @return String
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定的字符
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)     = null
     * StringUtil.leftPad("", 3, 'z')     = "zzz"
     * StringUtil.leftPad("bat", 3, 'z')  = "bat"
     * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtil.leftPad("bat", 1, 'z')  = "bat"
     * StringUtil.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            扩大后的长度
     *            补充的字符
     * @return String
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    /**
     * <p>
     * 扩大字符串长度，从左边补充指定的字符
     * </p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)      = null
     * StringUtil.leftPad("", 3, "z")      = "zzz"
     * StringUtil.leftPad("bat", 3, "yz")  = "bat"
     * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.leftPad("bat", 1, "yz")  = "bat"
     * StringUtil.leftPad("bat", -1, "yz") = "bat"
     * StringUtil.leftPad("bat", 5, null)  = "  bat"
     * StringUtil.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            扩大后的长度
     * @param padStr
     *            补充的字符串
     * @return String
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>
     * 扩大字符串长度并将现在的字符串居中，被扩大部分用空格填充。
     * <p>
     *
     * <pre>
     * StringUtil.center(null, *)   = null
     * StringUtil.center("", 4)     = "    "
     * StringUtil.center("ab", -1)  = "ab"
     * StringUtil.center("ab", 4)   = " ab "
     * StringUtil.center("abcd", 2) = "abcd"
     * StringUtil.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            扩大后的长度
     * @return String
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * <p>
     * 将字符串长度修改为指定长度，并进行居中显示。
     * </p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, ' ')     = "    "
     * StringUtil.center("ab", -1, ' ')  = "ab"
     * StringUtil.center("ab", 4, ' ')   = " ab"
     * StringUtil.center("abcd", 2, ' ') = "abcd"
     * StringUtil.center("a", 4, ' ')    = " a  "
     * StringUtil.center("a", 4, 'y')    = "yayy"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            指定的长度
     *            长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException
     *             如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, char padChar) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padChar);
        str = rightPad(str, size, padChar);
        return str;
    }

    /**
     * <p>
     * 将字符串长度修改为指定长度，并进行居中显示。
     * </p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, " ")     = "    "
     * StringUtil.center("ab", -1, " ")  = "ab"
     * StringUtil.center("ab", 4, " ")   = " ab"
     * StringUtil.center("abcd", 2, " ") = "abcd"
     * StringUtil.center("a", 4, " ")    = " a  "
     * StringUtil.center("a", 4, "yz")   = "yayz"
     * StringUtil.center("abc", 7, null) = "  abc  "
     * StringUtil.center("abc", 7, "")   = "  abc  "
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            指定的长度
     * @param padStr
     *            长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException
     *             如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, String padStr) {
        if (str == null || size <= 0) {
            return str;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padStr);
        str = rightPad(str, size, padStr);
        return str;
    }

    /**
     * <p>
     * 检查字符串是否全部为小写.
     * </p>
     *
     * <pre>
     * StringUtil.isAllLowerCase(null)   = false
     * StringUtil.isAllLowerCase("")     = false
     * StringUtil.isAllLowerCase("  ")   = false
     * StringUtil.isAllLowerCase("abc")  = true
     * StringUtil.isAllLowerCase("abC")  = false
     * </pre>
     *
     * @param cs
     *            源字符串
     * @return String
     */
    public static boolean isAllLowerCase(String cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLowerCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 检查是否都是大写.
     * </p>
     *
     * <pre>
     * StringUtil.isAllUpperCase(null)   = false
     * StringUtil.isAllUpperCase("")     = false
     * StringUtil.isAllUpperCase("  ")   = false
     * StringUtil.isAllUpperCase("ABC")  = true
     * StringUtil.isAllUpperCase("aBC")  = false
     * </pre>
     *
     * @param cs
     *            源字符串
     * @return String
     */
    public static boolean isAllUpperCase(String cs) {
        if (cs == null || StringUtil.isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 反转字符串.
     * </p>
     *
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     *
     * @param str
     *            源字符串
     * @return String
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * <p>
     * 字符串达不到一定长度时在右边补空白.
     * </p>
     *
     * <pre>
     * StringUtil.rightPad(null, *)   = null
     * StringUtil.rightPad("", 3)     = "   "
     * StringUtil.rightPad("bat", 3)  = "bat"
     * StringUtil.rightPad("bat", 5)  = "bat  "
     * StringUtil.rightPad("bat", 1)  = "bat"
     * StringUtil.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param size
     *            指定的长度
     * @return String
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * 从右边截取字符串.</p>
     *
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = ""
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param len
     *            长度
     * @return String
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * <p>
     * 截取一个字符串的前几个.
     * </p>
     *
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left(*, -ve)     = ""
     * StringUtil.left("", *)      = ""
     * StringUtil.left("abc", 0)   = ""
     * StringUtil.left("abc", 2)   = "ab"
     * StringUtil.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param len
     *            截取的长度
     * @return the String
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * <p>
     * 得到tag字符串中间的子字符串，只返回第一个匹配项。
     * </p>
     *
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween("", "")             = ""
     * StringUtil.substringBetween("", "tag")          = null
     * StringUtil.substringBetween("tagabctag", null)  = null
     * StringUtil.substringBetween("tagabctag", "")    = ""
     * StringUtil.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str
     *            源字符串。
     * @param tag
     *            标识字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>
     * 得到两个字符串中间的子字符串，只返回第一个匹配项。
     * </p>
     *
     * <pre>
     * StringUtil.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween(*, null, *)          = null
     * StringUtil.substringBetween(*, *, null)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "]")         = null
     * StringUtil.substringBetween("", "[", "]")        = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str
     *            源字符串
     * @param open
     *            起字符串。
     * @param close
     *            末字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * <p>
     * 得到两个字符串中间的子字符串，所有匹配项组合为数组并返回。
     * </p>
     *
     * <pre>
     * StringUtil.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtil.substringsBetween(null, *, *)            = null
     * StringUtil.substringsBetween(*, null, *)            = null
     * StringUtil.substringsBetween(*, *, null)            = null
     * StringUtil.substringsBetween("", "[", "]")          = []
     * </pre>
     *
     * @param str
     *            源字符串
     * @param open
     *            起字符串。
     * @param close
     *            末字符串。
     * @return String 子字符串数组, 如果没有符合要求的，返回{@code null}。
     */
    public static String[] substringsBetween(String str, String open,
                                             String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return new String[0];
        }
        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<String>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 功能：切换字符串中的所有字母大小写。<br/>
     *
     * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     *
     * @param str
     *            源字符串
     * @return String
     */
    public static String swapCase(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        char[] buffer = str.toCharArray();

        boolean whitespace = true;

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isLowerCase(ch)) {
                if (whitespace) {
                    buffer[i] = Character.toTitleCase(ch);
                    whitespace = false;
                } else {
                    buffer[i] = Character.toUpperCase(ch);
                }
            } else {
                whitespace = Character.isWhitespace(ch);
            }
        }
        return new String(buffer);
    }

    /**
     * 功能：截取出最后一个标志位之后的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr
     *            被截取的字符串
     * @param expr
     *            分隔符
     * @return String
     */
    public static String substringAfterLast(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }

        int pos = sourceStr.lastIndexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(pos + expr.length());
    }

    /**
     * 功能：截取出最后一个标志位之前的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr
     *            被截取的字符串
     * @param expr
     *            分隔符
     * @return String
     */
    public static String substringBeforeLast(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }
        int pos = sourceStr.lastIndexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(0, pos);
    }

    /**
     * 功能：截取出第一个标志位之后的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     *
     * @param sourceStr
     *            被截取的字符串
     * @param expr
     *            分隔符
     * @return String
     */
    public static String substringAfter(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }

        int pos = sourceStr.indexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(pos + expr.length());
    }

    /**
     * 功能：截取出第一个标志位之前的字符串.<br/>
     * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
     * 如果expr长度为0，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
     * 如果expr在sourceStr中存在不止一个，以第一个位置为准。
     *
     * @param sourceStr
     *            被截取的字符串
     * @param expr
     *            分隔符
     * @return String
     */
    public static String substringBefore(String sourceStr, String expr) {
        if (isEmpty(sourceStr) || expr == null) {
            return sourceStr;
        }
        if (expr.length() == 0) {
            return sourceStr;
        }
        int pos = sourceStr.indexOf(expr);
        if (pos == -1) {
            return sourceStr;
        }
        return sourceStr.substring(0, pos);
    }

    /**
     * 功能：检查这个字符串是不是空字符串。<br/>
     * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
     *
     * <pre>
     * StringUtil.isNotBlank(null)      = true
     * StringUtil.isNotBlank("")        = true
     * StringUtil.isNotBlank(" ")       = true
     * StringUtil.isNotBlank("bob")     = false
     * StringUtil.isNotBlank("  bob  ") = false
     * </pre>
     *
     * @param chkStr
     *            被检查的字符串
     * @return boolean
     */
    public static boolean isEmpty(String chkStr) {
        if (chkStr == null) {
            return true;
        } else {
            return "".equals(chkStr.trim()) ? true : false;
        }
    }

    // Empty checks
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if a CharSequence is empty ("") or null.
     * </p>
     *
     * <p>
     * 检查这个字符串是不是空字符串.
     *
     * </p>
     *
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the
     * CharSequence. That functionality is available in isBlank().
     * </p>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * <p>
     * Checks if a CharSequence is not empty ("") and not null.
     * </p>
     *
     * <pre>
     * StringUtil.isNotEmpty(null)      = false
     * StringUtil.isNotEmpty("")        = false
     * StringUtil.isNotEmpty(" ")       = true
     * StringUtil.isNotEmpty("bob")     = true
     * StringUtil.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null
     * @since 3.0 Changed signature from isNotEmpty(String) to
     *        isNotEmpty(CharSequence)
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * <p>
     * Checks if a CharSequence is whitespace, empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(cs.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if a CharSequence is not empty (""), not null and not whitespace
     * only.
     * </p>
     *
     * <pre>
     * StringUtil.isNotBlank(null)      = false
     * StringUtil.isNotBlank("")        = false
     * StringUtil.isNotBlank(" ")       = false
     * StringUtil.isNotBlank("bob")     = true
     * StringUtil.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null and
     *         not whitespace
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !StringUtil.isBlank(cs);
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters.
     * </p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtil.isAlpha(null)   = false
     * StringUtil.isAlpha("")     = false
     * StringUtil.isAlpha("  ")   = false
     * StringUtil.isAlpha("abc")  = true
     * StringUtil.isAlpha("ab2c") = false
     * StringUtil.isAlpha("ab-c") = false
     * </pre>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if only contains letters, and is non-null
     */
    public static boolean isAlpha(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only Unicode letters or digits.
     * </p>
     *
     * <p>
     * {@code null} will return {@code false}. An empty CharSequence
     * (length()=0) will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtil.isAlphanumeric(null)   = false
     * StringUtil.isAlphanumeric("")     = false
     * StringUtil.isAlphanumeric("  ")   = false
     * StringUtil.isAlphanumeric("abc")  = true
     * StringUtil.isAlphanumeric("ab c") = false
     * StringUtil.isAlphanumeric("ab2c") = true
     * StringUtil.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param cs
     *            the CharSequence to check, may be null
     * @return {@code true} if only contains letters or digits, and is non-null
     */
    public static boolean isAlphanumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetterOrDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks the specified string as a double value
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        if (isBlank(str))
            return false;
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * parses the specified string as a signed boolean value
     *
     * @param str
     *            input string
     * @return boolean true: str = true / TRUE (Ignore upper case or low case)
     *         false: other
     */
    public static boolean isBoolean(String str) {
        if (isBlank(str))
            return false;
        try {
            Boolean.parseBoolean(str.trim());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 如果字符串没有超过最长显示长度返回原字符串，否则从开头截取指定长度并加...返回。
     *
     * @param str
     *            原字符串
     * @param length
     *            字符串最长显示的长度
     * @return 转换后的字符串
     */
    public static String trimString(String str, int length) {
        if (str == null) {
            return "";
        } else if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        } else {
            return str;
        }
    }

    /**
     * 字符串去前后空格，增加为NULL判断
     *
     * <p>
     * methods.
     * </p>
     *
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     *            the String to be trimmed, may be null
     * @return the trimmed string, {@code null} if null String input
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * <pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull("")            = null
     * StringUtil.trimToNull("     ")       = null
     * StringUtil.trimToNull("abc")         = "abc"
     * StringUtil.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * <pre>
     * StringUtil.trimToEmpty(null)          = ""
     * StringUtil.trimToEmpty("")            = ""
     * StringUtil.trimToEmpty("     ")       = ""
     * StringUtil.trimToEmpty("abc")         = "abc"
     * StringUtil.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * 替换指定的字符串为一个字符串<br>
     * 速度比String.replaceAll快3倍左右，比apache-common StringUtils.replace快2倍左右
     *
     * @param text
     * @param replaceStr
     * @param newStr
     * @return
     */
    public static String replaceAll(String text, String replaceStr,
                                    String newStr) {
        if (text.length() == 0)
            return text;
        StringBuilder str = new StringBuilder();
        int index = text.indexOf(replaceStr);
        if (index >= 0) {
            String afterStr = null;
            if (index > 0) {
                String beforeStr = text.substring(0, index);
                afterStr = text.substring(index + replaceStr.length());
                str.append(replaceAll(beforeStr, replaceStr, newStr));
            } else
                afterStr = text.substring(replaceStr.length());
            str.append(newStr);
            str.append(replaceAll(afterStr, replaceStr, newStr));
        }
        if (str.length() == 0)
            return text;
        return str.toString();
    }

    /**
     * 替换指定的字符串数组为一个字符串<br>
     * 速度比String.replaceAll快3倍左右，比apache-common StringUtils.replace快2倍左右
     *
     * @param text
     * @param replaceStrs
     * @param newStr
     * @return
     */
    public static String replaceAll(String text, String[] replaceStrs,
                                    String newStr) {
        if (text.length() == 0)
            return text;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < replaceStrs.length; i++) {
            String replaceStr = replaceStrs[i];
            int index = text.indexOf(replaceStr);
            if (index >= 0) {
                String afterStr = null;
                if (index > 0) {
                    String beforeStr = text.substring(0, index);
                    afterStr = text.substring(index + replaceStr.length());
                    str.append(replaceAll(beforeStr, replaceStrs, newStr));
                } else
                    afterStr = text.substring(replaceStr.length());
                str.append(newStr);
                str.append(replaceAll(afterStr, replaceStrs, newStr));
                break;
            }
        }
        if (str.length() == 0)
            return text;
        return str.toString();
    }

    /**
     * 替换指定的字符串数组为一个字符串数组<br>
     * 速度比String.replaceAll快3倍左右，比apache-common StringUtils.replace快2倍左右
     *
     * @param text
     * @param replaceStrs
     * @param newStrs
     * @return
     */
    public static String replaceAllArray(String text, String[] replaceStrs,
                                         String[] newStrs) {
        if (text.length() == 0)
            return text;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < replaceStrs.length; i++) {
            String replaceStr = replaceStrs[i];
            int index = text.indexOf(replaceStr);
            if (index >= 0) {
                String afterStr = null;
                if (index > 0) {
                    String beforeStr = text.substring(0, index);
                    afterStr = text.substring(index + replaceStr.length());
                    str.append(replaceAllArray(beforeStr, replaceStrs, newStrs));
                } else
                    afterStr = text.substring(replaceStr.length());
                str.append(newStrs[i]);
                str.append(replaceAllArray(afterStr, replaceStrs, newStrs));
                break;
            }
        }
        if (str.length() == 0)
            return text;
        return str.toString();
    }

    /**
     * encode String by UTF-8
     *
     * @param str
     *            input string
     * @return String
     */
    public static String URLEncoder(String str) {
        return URLEncoder(str, "UTF-8");
    }

    /**
     * encode String by the input encoding
     *
     * @param str
     *            input string
     * @return String encode string
     */
    public static String URLEncoder(String str, String encoding) {
        if (isBlank(str))
            return EMPTY;
        try {
            return URLEncoder.encode(str.trim(), encoding);
        } catch (UnsupportedEncodingException e) {
        }
        return EMPTY;
    }

    /**
     * decode string by UTF-8
     *
     * @param str
     *            input string
     * @return String decode string
     */
    public static String URLDecoder(String str) {
        return URLDecoder(str, "UTF-8");
    }

    /**
     * decode string by the input encoding
     *
     * @param str
     * @param encoding
     * @return
     */
    public static String URLDecoder(String str, String encoding) {
        if (isBlank(str))
            return EMPTY;
        try {
            return URLDecoder.decode(str.trim(), encoding);
        } catch (UnsupportedEncodingException e) {
        }
        return EMPTY;
    }

}
