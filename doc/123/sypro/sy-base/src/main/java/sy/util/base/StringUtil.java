package sy.util.base;

import java.util.UUID;

import org.nutz.lang.Strings;

/**
 * 字符串工具类
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class StringUtil {

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String lowerFirst(String s) {
		return Strings.lowerFirst(s);
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String upperFirst(String s) {
		return Strings.upperFirst(s);
	}

	/**
	 * 获得一个不带-符号的uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
