package sy.util.base;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author 孙宇
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

	/**
	 * 获得hibernate批量操作的最大数值
	 * 
	 * @return
	 */
	public static final String getBatchSize() {
		return get("hibernate.jdbc.batch_size");
	}

}
