package org.zhanghua.ssm.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.zhanghua.ssm.common.utils.PropertiesLoader;

/**
 * 常量类
 * 
 * @author Wujun
 * 
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("application.properties");

	/**
	 * 登录用户（当前用户）
	 */
	public static final String CURRENT_USER = "currentUser";

	/**
	 * 菜单导航
	 */
	public static final String MENUS = "menus";

	/**
	 * 加密算法
	 */
	public static final String HASH_ALGORITHM = Global.getConfig("shiro.hashAlgorithmName");

	/**
	 * 加密循环次数
	 */
	public static final Integer HASH_INTERATIONS = Integer.parseInt(Global.getConfig("shiro.hashIterations"));

	/**
	 * 产生盐循环次数
	 */
	public static final Integer SALT_INTERATIONS = Integer.parseInt(Global.getConfig("shiro.saltIterations"));


	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * 获取配置
	 * 
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 获取工程路径
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)) {
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()) {
						break;
					}
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
	}

}
